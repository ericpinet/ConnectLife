/**
 *  ShellCmdSetConfig.java
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

import com.connectlife.coreserver.Application;
import com.connectlife.coreserver.config.ConfigItem;
import com.connectlife.coreserver.config.ConfigItem.ConfigType;

/**
 * Shell command for modify the configuration of the system.
 * 
 * @author ericpinet
 * <br> 2016-02-07
 */
public class ShellCmdSetConfig implements ShellCmd {
	
	/**
	 * Logger for the shell
	 */
    private static final Logger m_logger = LogManager.getLogger(ShellCmdSetConfig.class);

	/**
	 * Shell command.
	 */
	private static final String SHELL_CMD = "set config";
	
	/**
	 * Shell help string.
	 */
	private static final String SHELL_CMD_HELP = SHELL_CMD + " - Modify the configuration of the system.\n";
	 
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
	 * @throws InterruptedIOException The execution can cause a interruption of the console screen. (Exit/Quit/Shutdown)
	 * @see com.connectlife.coreserver.console.ShellCmd#execute(java.lang.String)
	 */
	@Override
	public String execute(String _line) throws InterruptedIOException {
		String response = "";
		m_logger.info(SHELL_CMD);
    	
    	// this section is to get the section and item from the text.
    	int minLength = (SHELL_CMD+" [*][*] *").length();
    	int section_start_at = _line.indexOf("[");
    	int section_end_at   = _line.indexOf("]");
    	int item_start_at    = _line.indexOf("[",section_end_at);
    	int item_end_at      = _line.indexOf("]", item_start_at);
    	String strValue      = item_end_at+2 < _line.length() ? _line.substring(item_end_at+2) : "";
    	boolean   isValideValue = true;
    	response = null;
    	if (_line.length() < minLength || 
    		strValue.equals("") ||
    		(_line.charAt(SHELL_CMD.length()) != ' ') || 
    		(section_start_at + 1 >= section_end_at) || 
    		(section_end_at + 1 != item_start_at) || 
    		(item_start_at + 1 >= item_end_at) || 
    		(_line.length() <= item_end_at + 1) || 
    		(_line.charAt(item_end_at+1) != ' '))
    	{
    		response = "Format error! Please use format like : " + SHELL_CMD+" [section][item] value";
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
    	return response;
	}

}
