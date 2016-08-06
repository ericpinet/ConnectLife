/**
 *  ShellCmdRestoreFactoryConfig.java
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

/**
 * Shell command for restore the factory configuration of the system.
 * 
 * @author ericpinet
 * <br> 2016-02-07
 */
public class ShellCmdRestoreFactoryConfig implements ShellCmd {
	
	/**
	 * Logger for the shell
	 */
    private static final Logger m_logger = LogManager.getLogger(ShellCmdRestoreFactoryConfig.class);
    
    /**
	 * Initialization of translation system.
	 */
	private static I18n i18n = I18nFactory.getI18n(ShellCmdRestoreFactoryConfig.class);

	/**
	 * Shell command.
	 */
	private static final String SHELL_CMD = "restore factory config";
	
	/**
	 * Shell help string.
	 */
	private static final String SHELL_CMD_HELP = SHELL_CMD + i18n.tr(" - Restore the factory configurations of the system.\n");
	 
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
    	
    	if(Application.getApp().getConfig().RestoreFactory()){
    		response = i18n.tr("Restore the factory configuration.");
    	}
    	else{
    		response = i18n.tr("Error. Unable to restore factory configuration.");
    	}
    	
    	return response;
	}
}
