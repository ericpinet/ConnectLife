/**
 *  CmdDefault.java
 *  coreserver
 *
 *  Created by ericpinet on 2016-03-28.
 *  Copyright (c) 2016 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.connectlife.coreserver.environment.cmd;

import com.connectlife.coreserver.environment.EnvironmentContext;

/**
 * Command default.
 * 
 * @author ericpinet
 * <br> 2016-03-28
 */
public abstract class CmdDefault implements Cmd {
	
	/**
	 * The environment context for the execution of this command.
	 */
	protected EnvironmentContext m_context;
	
	/**
	 * Flag indicate if the data was changed during the execution of the command.
	 */
	protected boolean m_data_is_changed;
	
	/**
	 * Set the context of the execution.
	 * 
	 * @param _context Context execution.
	 */
	public void setContext(EnvironmentContext _context){
		m_context = _context;
		m_data_is_changed = false;
	}
	
	/**
	 * Valid the context for the execution of this command.
	 * 
	 * @throws Exception Exception if something goes wrong.
	 */
	public void validContext() throws Exception {
		if( null == m_context ){
			throw new Exception("The context wasn't set in the action. It's impossible to execute action without valid context.");
		}
		else{
			if( false == m_context.isInit() ){
				throw new Exception("The context wasn't initialized in the action. It's impossible to execute action without valid context.");
			}
		}
	}

	/**
	 * Execute the action on the context. 
	 * 
	 * @throws Exception If something goes wrong.
	 * @see com.connectlife.coreserver.environment.cmd.Cmd#execute()
	 */
	@Override
	public void execute() throws Exception {
		throw new Exception("Not yet implemented.");
	}
	
	/**
	 * Return true if the data of the environment was changed by the command.
	 * 
	 * @return True if data changed.
	 * @see com.connectlife.coreserver.environment.cmd.Cmd#isDataChanged()
	 */
	@Override
	public boolean isDataChanged() {
		return m_data_is_changed;
	}
}
