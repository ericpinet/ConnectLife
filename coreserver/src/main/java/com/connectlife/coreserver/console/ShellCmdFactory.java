/**
 *  ShellCmdFactory.java
 *  coreserver
 *
 *  Created by ericpinet on 2016-02-07.
 *  Copyright (c) 2016 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.connectlife.coreserver.console;

import java.util.List;
import java.util.Vector;

/**
 * Shell command factory. Build the list of all supported command in the system.
 * 
 * @author ericpinet
 * <br> 2016-02-07
 */
public abstract class ShellCmdFactory {
	
	/**
	 * Return all shell command supported in the system.
	 * @return All shell command supported.
	 */
	public static List<ShellCmd> getCommands(){
		List<ShellCmd> commands = new Vector<ShellCmd>();
		
		commands.add(new ShellCmdExit());
		commands.add(new ShellCmdHelp());
		commands.add(new ShellCmdOutputAllConfig());
		commands.add(new ShellCmdOutputConfig());
		commands.add(new ShellCmdOutputEnv());
		commands.add(new ShellCmdOutputLog());
		commands.add(new ShellCmdQuit());
		commands.add(new ShellCmdRegisterAccessory());
		commands.add(new ShellCmdRestoreFactoryConfig());
		commands.add(new ShellCmdSetConfig());
		commands.add(new ShellCmdShutdown());
		commands.add(new ShellCmdUnregisterAccessory());
		commands.add(new ShellCmdVersion());
		
		return commands;
	}

}
