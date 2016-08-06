/**
 *  ShellCmdOutputConfig.java
 *  coreserver
 *
 *  Created by ericpinet on 2016-02-07.
 *  Copyright (c) 2016 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.connectlife.coreserver.console;

import java.io.InterruptedIOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xnap.commons.i18n.I18n;
import org.xnap.commons.i18n.I18nFactory;

import com.connectlife.coreserver.Application;
import com.connectlife.coreserver.config.ConfigItem;

/**
 * Shell command for output the specific configuration of the system.
 * 
 * @author ericpinet
 * <br> 2016-02-07
 */
public class ShellCmdOutputConfig implements ShellCmd {
	
	/**
	 * Logger for the shell
	 */
    private static final Logger m_logger = LogManager.getLogger(ShellCmdOutputConfig.class);
    
    /**
	 * Initialization of translation system.
	 */
	private static I18n i18n = I18nFactory.getI18n(ShellCmdOutputConfig.class);

	/**
	 * Shell command.
	 */
	private static final String SHELL_CMD = "output config";
	
	/**
	 * Shell help string.
	 */
	private static final String SHELL_CMD_HELP = SHELL_CMD + i18n.tr(" - return the specific configuration of the system.\n");
	 
	/**
	 * Get the shell command.
	 * 
	 * @return Shell command.
	 * @see com.connectlife.coreserver.console.ShellCmd#getCommand()
	 */
	@Override
	public String getCommand() {
		return SHELL_CMD;
	}

	/**
	 * Return the help string of the shell command.
	 * 
	 * @return Help string.
	 * @see com.connectlife.coreserver.console.ShellCmd#getHelp()
	 */
	@Override
	public String getHelp() {
		return SHELL_CMD_HELP;
	}
	
	/**
	 * Check if the line is compatible with this command.
	 * 
	 * @param _line Line enter by the user.
	 * @return True if the line in compatible with this command.
	 */
	public boolean checkLineForCommandCompatibility(String _line){
		return _line.startsWith(SHELL_CMD);
	}

	/**
	 * Execute the command with this shell line. 
	 * 
	 * @param _line The shell line enter by the user.
	 * @return The shell string return to display at the user.
	 * @throws InterruptedIOException The execution can cause a inturruption of the console screen. (Exit/Quit/Shutdown)
	 * @see com.connectlife.coreserver.console.ShellCmd#execute(java.lang.String)
	 */
	@Override
	public String execute(String _line) throws InterruptedIOException {
		String response = "";
		m_logger.info(SHELL_CMD);
    	
    	// this section is to get the section and item from the text.
    	int minLength = (SHELL_CMD+" [*][*]").length();
    	int section_start_at = _line.indexOf("[");
    	int section_end_at   = _line.indexOf("]");
    	int item_start_at    = _line.indexOf("[",section_end_at);
    	int item_end_at      = _line.indexOf("]", item_start_at);
    	
    	if((_line.length() < minLength) || (_line.charAt(SHELL_CMD.length()) != ' ') || (section_start_at + 1 >= section_end_at) || (section_end_at + 1 != item_start_at) || (item_start_at + 1 >= item_end_at) || (_line.length() != item_end_at + 1))
    	{
    		response = "Format error! Please use format like : " + SHELL_CMD+" [section][item]";
    	}
    	else
    	{
        	String section = _line.substring(section_start_at+1, section_end_at).toUpperCase();
        	String item    = _line.substring(item_start_at+1, item_end_at).toUpperCase();
        	
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
    	return response;
	}

}
