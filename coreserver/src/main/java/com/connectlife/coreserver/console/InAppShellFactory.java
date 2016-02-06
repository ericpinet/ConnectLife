/**
 *  InAppShellFactory.java
 *  coreserver
 *
 *  Created by ericpinet on 2015-09-11.
 *  Copyright (c) 2015 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.connectlife.coreserver.console;

// external
import org.apache.sshd.common.Factory;
import org.apache.sshd.server.Command;
import org.apache.sshd.server.Environment;
import org.apache.sshd.server.ExitCallback;

import jline.console.ConsoleReader;
import jline.console.completer.StringsCompleter;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.Appender;
import org.apache.logging.log4j.core.appender.RollingFileAppender;

// internal
import com.connectlife.coreserver.Application;
import com.connectlife.coreserver.Consts;
import com.connectlife.coreserver.config.ConfigItem;
import com.connectlife.coreserver.config.ConfigItem.ConfigType;
import com.connectlife.coreserver.tools.errormanagement.StdOutErrLog;

/**
 * Factory use to build shell interface sshd. 
 * 
 * @author ericpinet
 * <br> 2015-09-11
 */
@SuppressWarnings("rawtypes")
public class InAppShellFactory implements Factory {
	
	/**
	 * Create the new Shell.
	 * 
	 * @return Command for the shell.
	 * @see org.apache.sshd.common.Factory#create()
	 */
    public Command create() {
        return new InAppShell();
    }

    /**
     * SSH shell manager.
     * 
     * @author ericpinet
     * <br> 2015-09-11
     */
    private static class InAppShell implements Command, Runnable {

    	/**
    	 * Logger for the shell
    	 */
        private static final Logger m_logger = LogManager.getLogger(InAppShell.class);

        /**
         * Use to check the OS for a fix.
         */
        public static final boolean IS_MAC_OSX = System.getProperty("os.name").startsWith("Mac OS X");

        /**
         * Shell m_thread name.
         */
        private static final String SHELL_THREAD_NAME = "ConnectLifeSSHD";
        
        /**
         * Shell prompt.
         */
        private static final String SHELL_PROMPT = "cli> ";
        
        /**
         * List of all command.
         */
        private static final String SHELL_CMD_OUTPUT_ENV = "output env";
        private static final String SHELL_CMD_SHUTDOWN = "shutdown";
        private static final String SHELL_CMD_QUIT = "quit";
        private static final String SHELL_CMD_EXIT = "exit";
        private static final String SHELL_CMD_VERSION = "version";
        private static final String SHELL_CMD_HELP = "help";
        private static final String SHELL_CMD_OUTPUT_ALL_CONFIGS = "output configs";
        private static final String SHELL_CMD_OUTPUT_CONFIG = "output config";
        private static final String SHELL_CMD_SET_CONFIG = "set config";
        private static final String SHELL_CMD_RESTORE_FACTORY_CONFIG = "restore factory config";
        private static final String SHELL_CMD_OUTPUT_LOG = "output log";
        private static final String SHELL_CMD_REGISTER_ACCESSORY = "register device";
        private static final String SHELL_CMD_UNREGISTER_ACCESSORY = "unregistrer device";

        /**
         * Input stream of the console.
         */
        private InputStream m_in;
        
        /**
         * Output stream of the console.
         */
        private OutputStream m_out;
        
        /**
         * Output stream for error.
         */
        @SuppressWarnings("unused")
		private OutputStream m_err;
        
        /**
         * Exit m_callback
         */
        private ExitCallback m_callback;
        
        /**
         * Environment console.
         */
        @SuppressWarnings("unused")
		private Environment m_environment;
        
        /**
         * m_thread of the console.
         */
        private Thread m_thread;
        
        /**
         * Console m_reader.
         */
        private ConsoleReader m_reader;
        
        /**
         * Printer m_writer for the console.
         */
        private PrintWriter m_writer;

        /**
         * Set the input stream.
         * 
         * @param in Input stream for the shell.
         * @see org.apache.sshd.server.Command#setInputStream(java.io.InputStream)
         */
        public void setInputStream(InputStream in) {
            this.m_in = in;
        }

        /**
         * Set the output stream.
         * 
         * @param out Output stream for the shell.
         * @see org.apache.sshd.server.Command#setOutputStream(java.io.OutputStream)
         */
        public void setOutputStream(OutputStream out) {
            this.m_out = out;
        }

        /**
         * Set error stream.
         * 
         * @param err Error stream for the shell.
         * @see org.apache.sshd.server.Command#setErrorStream(java.io.OutputStream)
         */
        public void setErrorStream(OutputStream err) {
            this.m_err = err;
        }

        /**
         * Set the exit m_callback.
         * 
         * @param callback ExitCallback of the shell.
         * @see org.apache.sshd.server.Command#setExitCallback(org.apache.sshd.server.ExitCallback)
         */
        public void setExitCallback(ExitCallback callback) {
            this.m_callback = callback;
        }

        /**
         * Start the shell.
         * 
         * @param env			Environment of the shell.
         * @throws IOException  IOException can occur during the start of shell.
         * @see org.apache.sshd.server.Command#start(org.apache.sshd.server.Environment)
         */
        public void start(Environment env) throws IOException {
            m_environment = env;
            m_thread = new Thread(this, SHELL_THREAD_NAME);
            m_thread.start();
        }

        /**
         * Destroy the shell.
         * 
         * @see org.apache.sshd.server.Command#destroy()
         */
        public void destroy() {
            if (m_reader != null)
                m_reader.shutdown();
            m_thread.interrupt();
        }

        /**
         * Run the session shell.
         * 
         * @see java.lang.Runnable#run()
         */
        public void run() {
            
        	try {
        		
        		// fix for mac os x
                m_reader = new ConsoleReader(m_in, new FilterOutputStream(m_out) {
                    public void write(final int i) throws IOException {
                        super.write(i);

                        // workaround for MacOSX!! reset line after CR..
                        if (IS_MAC_OSX && i == ConsoleReader.CR.toCharArray()[0]) {
                            super.write(ConsoleReader.RESET_LINE);
                        }
                    }
                });
                
                // setup m_reader.
                m_reader.setPrompt(SHELL_PROMPT);
                m_reader.addCompleter(new StringsCompleter(	SHELL_CMD_OUTPUT_ENV,
                											SHELL_CMD_SHUTDOWN,
                										 	SHELL_CMD_QUIT,
                										 	SHELL_CMD_EXIT, 
                										 	SHELL_CMD_VERSION, 
                										 	SHELL_CMD_HELP,
                										 	SHELL_CMD_OUTPUT_ALL_CONFIGS,
                										 	SHELL_CMD_OUTPUT_CONFIG,
                										 	SHELL_CMD_SET_CONFIG,
                										 	SHELL_CMD_RESTORE_FACTORY_CONFIG,
                										 	SHELL_CMD_OUTPUT_LOG,
                										 	SHELL_CMD_REGISTER_ACCESSORY,
                										 	SHELL_CMD_UNREGISTER_ACCESSORY
                										 	));
                
                m_writer = new PrintWriter(m_reader.getOutput());
                m_writer.println("****************************************************");
                m_writer.println("*        Welcome to ConnectLife Shell              *");
                m_writer.println("****************************************************");
                m_writer.flush();

                String line;
                while ((line = m_reader.readLine()) != null) {
                    handleUserInput(line.trim());
                }

            } catch (InterruptedIOException e) {
                // Ignore
            } catch (Exception e) {
                m_logger.error("Error executing shell...", e);
    			StdOutErrLog.tieSystemOutAndErrToLog();
    			e.printStackTrace();
            } finally {
                m_callback.onExit(0);
            }
        }

        /**
         * Handle user input of the shell.
         * 
         * @param line Line enter by the user in cmd shell.
         * @throws InterruptedIOException Exception can occur during the handle of user input.
         */
        private void handleUserInput(String line) throws InterruptedIOException {

            if ( line.equalsIgnoreCase(SHELL_CMD_QUIT) ||
                 line.equalsIgnoreCase(SHELL_CMD_EXIT))
                throw new InterruptedIOException();

            String response = "";
            
            if (line.equalsIgnoreCase(SHELL_CMD_OUTPUT_ENV)){
            	// SHUTDOWN
            	m_logger.info(SHELL_CMD_OUTPUT_ENV);
                response = Application.getApp().getEnvironment().getJsonEnvironment();
                
            }else if (line.equalsIgnoreCase(SHELL_CMD_SHUTDOWN)){
            	// SHUTDOWN
            	m_logger.info(SHELL_CMD_SHUTDOWN);
                response = "shutdown m_in progress...";
                
                Application.getApp().shutdown();
                
                throw new InterruptedIOException();
                
            }else if (line.equalsIgnoreCase(SHELL_CMD_VERSION)){
            	// VERSION
            	m_logger.info(SHELL_CMD_VERSION);
                response = Consts.APP_NAME + " " + Consts.APP_VERSION;
            }
            else if (line.equalsIgnoreCase(SHELL_CMD_HELP)){
            	// HELP
            	m_logger.info(SHELL_CMD_HELP);
                response = SHELL_CMD_QUIT + " - exit console cli.\n";
                response += SHELL_CMD_EXIT + " - exit console cli.\n";
                response += SHELL_CMD_OUTPUT_ENV + " - output the system environment (JSON).\n";
                response += SHELL_CMD_SHUTDOWN + " - shutdown the system.\n";
                response += SHELL_CMD_VERSION + " - return the version of the system.\n";
                response += SHELL_CMD_OUTPUT_ALL_CONFIGS + " - return the configurations of the system.\n";
                response += SHELL_CMD_OUTPUT_CONFIG + " - return the specific configuration of the system.\n";
                response += SHELL_CMD_SET_CONFIG + " - Modify the configuration of the system.\n";
                response += SHELL_CMD_RESTORE_FACTORY_CONFIG + " - Restore the factory configurations of the system.\n";
                response += SHELL_CMD_OUTPUT_LOG + " - Output the log of the system.\n";
                response += SHELL_CMD_REGISTER_ACCESSORY + " - Register accessory in the environment.\n";
                response += SHELL_CMD_UNREGISTER_ACCESSORY + " - Unregister accessory from the environment.\n";
            }
            else if(line.equalsIgnoreCase(SHELL_CMD_OUTPUT_ALL_CONFIGS)){
            	// OUTPUT CONFIGS
            	m_logger.info(SHELL_CMD_OUTPUT_ALL_CONFIGS);
            	List<ConfigItem> configs = Application.getApp().getConfig().getConfigs();
            	for(int i=0; i<configs.size(); i++)
            	{
                	response += String.format("%1$-" + 20 + "s", configs.get(i).getSection())+ " " + 
                				String.format("%1$-" + 20 + "s", configs.get(i).getItem()) + " " + 
                				String.format("%1$-" + 20 + "s", configs.get(i).getType().toString()) + " " +
                				String.format("%1$-" + 20 + "s", configs.get(i).getValueToString()) +"\n"; 
            	}
            }
            else if(line.toLowerCase().startsWith(SHELL_CMD_OUTPUT_CONFIG)){
            	// OUTPUT CONFIG
            	m_logger.info(SHELL_CMD_OUTPUT_CONFIG);
            	
            	// this section is to get the section and item from the text.
            	int minLength = (SHELL_CMD_OUTPUT_CONFIG+" [*][*]").length();
            	int section_start_at = line.indexOf("[");
            	int section_end_at   = line.indexOf("]");
            	int item_start_at    = line.indexOf("[",section_end_at);
            	int item_end_at      = line.indexOf("]", item_start_at);
            	
            	if((line.length() < minLength) || (line.charAt(SHELL_CMD_OUTPUT_CONFIG.length()) != ' ') || (section_start_at + 1 >= section_end_at) || (section_end_at + 1 != item_start_at) || (item_start_at + 1 >= item_end_at) || (line.length() != item_end_at + 1))
            	{
            		response = "Format error! Please use format like : " + SHELL_CMD_OUTPUT_CONFIG+" [section][item]";
            	}
            	else
            	{
                	String section = line.substring(section_start_at+1, section_end_at).toUpperCase();
                	String item    = line.substring(item_start_at+1, item_end_at).toUpperCase();
                	
                	ConfigItem config = Application.getApp().getConfig().getConfig(section, item);
                	if(config == null){
                		response = "This configuration doesn't exist.";
                	}
                	else{
                    	response = String.format("%1$-" + 20 + "s", config.getSection())+ " " +
	             				   String.format("%1$-" + 20 + "s", config.getItem()) + " " +
	             				   String.format("%1$-" + 20 + "s", config.getType().toString()) + " " +
	             				   String.format("%1$-" + 20 + "s", config.getValueToString()) +"\n"; 		
                	}
            	}
            }
            else if (line.toLowerCase().startsWith(SHELL_CMD_SET_CONFIG)){
            	// SET CONFIG
            	m_logger.info(SHELL_CMD_SET_CONFIG);
            	
            	// this section is to get the section and item from the text.
            	int minLength = (SHELL_CMD_SET_CONFIG+" [*][*] *").length();
            	int section_start_at = line.indexOf("[");
            	int section_end_at   = line.indexOf("]");
            	int item_start_at    = line.indexOf("[",section_end_at);
            	int item_end_at      = line.indexOf("]", item_start_at);
            	String strValue      = item_end_at+2 < line.length() ? line.substring(item_end_at+2) : "";
            	boolean   isValideValue = true;
            	response = null;
            	if (line.length() < minLength || 
            		strValue.equals("") ||
            		(line.charAt(SHELL_CMD_SET_CONFIG.length()) != ' ') || 
            		(section_start_at + 1 >= section_end_at) || 
            		(section_end_at + 1 != item_start_at) || 
            		(item_start_at + 1 >= item_end_at) || 
            		(line.length() <= item_end_at + 1) || 
            		(line.charAt(item_end_at+1) != ' '))
            	{
            		response = "Format error! Please use format like : " + SHELL_CMD_SET_CONFIG+" [section][item] value";
            	}
            	else
            	{
                	String section = line.substring(section_start_at+1, section_end_at).toUpperCase();
                	String item    = line.substring(item_start_at+1, item_end_at).toUpperCase();
                	
                	ConfigItem config = Application.getApp().getConfig().getConfig(section, item);
                	if(config == null){
                		response = "This configuration doesn't exist.";
                	}
                	else{
                    	if(config.getType() == ConfigType.INTEGER){
                			isValideValue = strValue.matches("^\\d+$");
                		}
                    	
                    	if(!isValideValue){
                    		response = "Format error! The value is not valide !";
                    	}
                    	else{
                    		if(Application.getApp().getConfig().setConfig(new ConfigItem(section, item, strValue))){
                    			response = "Config is updated.";	
                    		}
                    	}
                	} 
            	}
            }
            else if(line.equalsIgnoreCase(SHELL_CMD_RESTORE_FACTORY_CONFIG)){
            	// RESTORE FACTORY CONFIG
            	m_logger.info(SHELL_CMD_RESTORE_FACTORY_CONFIG);
            	
            	if(Application.getApp().getConfig().RestoreFactory()){
            		response = "The Configurations are restored.";
            	}
            }
            else if(line.equalsIgnoreCase(SHELL_CMD_OUTPUT_LOG)){
            	// OUTPUT LOG
            	m_logger.info(SHELL_CMD_OUTPUT_LOG);
            	org.apache.logging.log4j.core.Logger loggerImpl = (org.apache.logging.log4j.core.Logger)m_logger;
            	Appender appender = (loggerImpl).getAppenders().get("File");
            	String fileName = ((RollingFileAppender)appender).getFileName();
            	try{
            		response = new String(Files.readAllBytes(Paths.get(fileName)));  
            	}
            	catch (Exception e){
            		m_logger.warn(e.getMessage());
            	}
            }
            else if(line.toLowerCase().startsWith(SHELL_CMD_REGISTER_ACCESSORY)){
            	// REGISTER DEVICE
            	m_logger.info(SHELL_CMD_REGISTER_ACCESSORY);
            	
            	// this section is to get the accessory and room from the arg.
            	int minLength = (SHELL_CMD_REGISTER_ACCESSORY+" [*][*]").length();
            	int accessory_start_at = line.indexOf("[");
            	int accessory_end_at   = line.indexOf("]");
            	int room_start_at    = line.indexOf("[",accessory_end_at);
            	int room_end_at      = line.indexOf("]", room_start_at);
            	
            	if((line.length() < minLength) || (line.charAt(SHELL_CMD_REGISTER_ACCESSORY.length()) != ' ') || (accessory_start_at + 1 >= accessory_end_at) || (accessory_end_at + 1 != room_start_at) || (room_start_at + 1 >= room_end_at) || (line.length() != room_end_at + 1))
            	{
            		response = "Format error! Please use format like : " + SHELL_CMD_REGISTER_ACCESSORY+" [accessory serial number][room uid]";
            	}
            	else
            	{
                	//String accessory = line.substring(accessory_start_at+1, accessory_end_at).toUpperCase();
                	//String room      = line.substring(room_start_at+1, room_end_at).toUpperCase();
                	
                	// TODO: Complete this function.
                	
                	response = "This command is not implemented yet.";
                	
            	}
            }
            else if(line.toLowerCase().startsWith(SHELL_CMD_UNREGISTER_ACCESSORY)){
            	// UNREGISTER DEVICE
            	m_logger.info(SHELL_CMD_UNREGISTER_ACCESSORY);
            	
            	// this section is to get the accessory and room from the arg.
            	int minLength = (SHELL_CMD_UNREGISTER_ACCESSORY+" [*][*]").length();
            	int accessory_start_at = line.indexOf("[");
            	int accessory_end_at   = line.indexOf("]");
            	int room_start_at    = line.indexOf("[",accessory_end_at);
            	int room_end_at      = line.indexOf("]", room_start_at);
            	
            	if((line.length() < minLength) || (line.charAt(SHELL_CMD_UNREGISTER_ACCESSORY.length()) != ' ') || (accessory_start_at + 1 >= accessory_end_at) || (accessory_end_at + 1 != room_start_at) || (room_start_at + 1 >= room_end_at) || (line.length() != room_end_at + 1))
            	{
            		response = "Format error! Please use format like : " + SHELL_CMD_UNREGISTER_ACCESSORY+" [accessory serial number][room uid]";
            	}
            	else
            	{
                	//String accessory = line.substring(accessory_start_at+1, accessory_end_at).toUpperCase();
                	//String room      = line.substring(room_start_at+1, room_end_at).toUpperCase();
                	
                	// TODO: Complete this function.
                	
                	response = "This command is not implemented yet.";
                	
            	}
            }
            else{	
            	// UNKNOW CMD
            	m_logger.info(line);
                response = "Command not found: \"" + line + "\"";
            }

            m_writer.println(response);
            m_writer.flush();
        }
    }
}