/**
 *  InAppShellFactory.java
 *  coreserver
 *
 *  Created by ericpinet on 2015-09-11.
 *  Copyright (c) 2015 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.connectlife.coreserver.console;

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
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
        private static final List<ShellCmd> m_commands = ShellCmdFactory.getCommands();

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
                Vector<String> params = new Vector<String>();
                Iterator<ShellCmd> it = m_commands.iterator();
                while(it.hasNext()){
                	ShellCmd cmd = it.next();
                	params.add(cmd.getCommand());
                }                
                m_reader.addCompleter(new StringsCompleter(params));
                
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
            	// do nothing, the user quit the console.
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
         * @param _line Line enter by the user in cmd shell.
         * @throws InterruptedIOException Exception can occur during the handle of user input.
         */
        private void handleUserInput(String _line) throws InterruptedIOException {
        	
        	String response = "";
        	boolean cmd_executed = false;
        	
        	Iterator<ShellCmd> it = m_commands.iterator();
            while(it.hasNext() && false == cmd_executed){
            	ShellCmd cmd = it.next();
            	
            	if( cmd.checkLineForCommandCompatibility(_line) ){
            		response = cmd.execute(_line);
            		cmd_executed = true;
            	}
            }

            if(false == cmd_executed){
            	// UNKNOW CMD
                response = "Command not found: \"" + _line + "\"";
            }
            
            m_writer.println(response);
            m_writer.flush();
            
        }
    }
}
