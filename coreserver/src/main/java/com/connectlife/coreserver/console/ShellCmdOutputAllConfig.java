/**
 *  ShellCmdOutputAllConfig.java
 *  coreserver
 *
 *  Created by ericpinet on 2016-02-07.
 *  Copyright (c) 2016 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.connectlife.coreserver.console;

import java.io.InterruptedIOException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xnap.commons.i18n.I18n;

import com.connectlife.coreserver.Application;
import com.connectlife.coreserver.config.ConfigItem;

/**
 * Shell command for output all configs of the system.
 * 
 * @author ericpinet
 * <br> 2016-02-07
 */
public class ShellCmdOutputAllConfig implements ShellCmd {
	
	/**
	 * Logger for the shell
	 */
    private final Logger m_logger = LogManager.getLogger(getClass().getName());
    
    /**
	 * Initialization of translation system.
	 */
	private static I18n i18n = Application.i18n;

	/**
	 * Shell command.
	 */
	private static final String SHELL_CMD = "output all config";
	
	/**
	 * Shell help string.
	 */
	private static final String SHELL_CMD_HELP = SHELL_CMD + i18n.tr(" - return the configurations of the system.\n");
	 
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
		return _line.equals(SHELL_CMD);
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
    	List<ConfigItem> configs = Application.getApp().getConfig().getConfigs();
    	for(int i=0; i<configs.size(); i++)
    	{
        	response += String.format("%1$-" + 20 + "s", configs.get(i).getSection())+ " " + 
        				String.format("%1$-" + 20 + "s", configs.get(i).getItem()) + " " + 
        				String.format("%1$-" + 20 + "s", configs.get(i).getType().toString()) + " " +
        				String.format("%1$-" + 20 + "s", configs.get(i).getValueToString()) +"\n"; 
    	}
    	return response;
	}

}
