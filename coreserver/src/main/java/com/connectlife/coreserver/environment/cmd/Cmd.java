/**
 *  Cmd.java
 *  coreserver
 *
 *  Created by ericpinet on 2016-03-28.
 *  Copyright (c) 2016 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.connectlife.coreserver.environment.cmd;

import com.connectlife.coreserver.environment.EnvironmentContext;

/**
 * Interface of all command in environment.
 * 
 * @author ericpinet
 * <br> 2016-03-28
 */
public interface Cmd {
	
	/**
	 * Set the context of the execution.
	 * 
	 * @param _context Context execution.
	 */
	public void setContext(EnvironmentContext _context);
	
	/**
	 * Valid the context for the execution of this command.
	 * 
	 * @throws Exception Exception if something goes wrong.
	 */
	public void validContext() throws Exception;

	/**
	 * Execute the cmd on the environment.
	 * 
	 * @throws Exception Exception when something goes wrong.
	 */
	public void execute() throws Exception;
	
	/**
	 * Return true if the data of the environment was changed by the command.
	 * 
	 * @return True if data changed.
	 */
	public boolean isDataChanged();
}
