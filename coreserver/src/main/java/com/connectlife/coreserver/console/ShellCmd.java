/**
 *  ShellCmd.java
 *  coreserver
 *
 *  Created by ericpinet on 2016-02-07.
 *  Copyright (c) 2016 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.connectlife.coreserver.console;

import java.io.InterruptedIOException;

/**
 * Interface of all command in shell.
 * 
 * @author ericpinet
 * <br> 2016-02-07
 */
public interface ShellCmd {
	
	/**
	 * Get the command format. Ex: exit.
	 * 
	 * @return Return a string with the command.
	 */
	public String getCommand();
	
	/**
	 * Return the help string of the command. 
	 * 
	 * @return Help string.
	 */
	public String getHelp();
	
	/**
	 * Check if the line is compatible with this command.
	 * 
	 * @param _line Line enter by the user.
	 * @return True if the line in compatible with this command.
	 */
	public boolean checkLineForCommandCompatibility(String _line);
	
	/**
	 * Execute the command with this shell line. 
	 * 
	 * @param _line The shell line enter by the user.
	 * @return The shell string return to display at the user.
	 * @throws InterruptedIOException The execution can cause a inturruption of the console screen. (Exit/Quit/Shutdown)
	 */
	public String execute(String _line) throws InterruptedIOException;
}
