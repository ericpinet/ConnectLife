/**
 *  ShellCmdSaveEnv.java
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

/**
 * Shell command for save environment from the console.
 * 
 * @author ericpinet
 * <br> 2016-02-07
 */
public class ShellCmdSaveEnv implements ShellCmd {
	
	/**
	 * Logger for the shell
	 */
    private static final Logger m_logger = LogManager.getLogger(ShellCmdSaveEnv.class);

	/**
	 * Shell command.
	 */
	private static final String SHELL_CMD = "save env";
	
	/**
	 * Shell help string.
	 */
	private static final String SHELL_CMD_HELP = SHELL_CMD + " - save the environment file if it's not already saved.\n";
	 
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
    	m_logger.debug(SHELL_CMD);
    	
    	// TODO: Complete command
    	response = SHELL_CMD + " not supported yet!";
    	
    	return response;
	}

}
