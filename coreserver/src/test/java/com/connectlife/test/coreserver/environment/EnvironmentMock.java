/**
 *  ConsoleMock.java
 *  coreserver
 *
 *  Created by ericpinet on 2015-10-10.
 *  Copyright (c) 2015 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.connectlife.test.coreserver.environment;

import java.util.Observable;

import com.clapi.data.*;
import com.connectlife.coreserver.environment.Environment;
import com.connectlife.coreserver.environment.cmd.Cmd;
import com.connectlife.coreserver.environment.discover.DiscoveryService;
import com.connectlife.coreserver.environment.find.FindProcessor;
import com.google.gson.Gson;
import com.google.inject.Inject;

/**
* 
* 
* @author ericpinet
* <br> 2015-10-10
*/
public class EnvironmentMock extends Observable implements Environment {
	
	private boolean m_is_init;
	
	@Inject
	public EnvironmentMock(DiscoveryService _Service){
		m_is_init = false;
	}
	
	/**
	 * @return
	 * @see com.connectlife.coreserver.modules.Module#init()
	 */
	@Override
	public boolean init() {
		return m_is_init = true;
	}

	/**
	 * @return
	 * @see com.connectlife.coreserver.modules.Module#isInit()
	 */
	@Override
	public boolean isInit() {
		return m_is_init;
	}

	/**
	 * 
	 * @see com.connectlife.coreserver.modules.Module#unInit()
	 */
	@Override
	public void unInit() {
		m_is_init = false;
	}

	/**
	 * @return
	 * @see com.connectlife.coreserver.environment.Environment#getJsonEnvironment()
	 */
	@Override
	public String getJsonEnvironment() {
			
		Data data = CreateTestData.getData();
		
		Gson gson = new Gson();
		String json = gson.toJson(data);
		
		return new String(json);
	}

	/**
	 * @param _cmd
	 * @throws Exception
	 * @see com.connectlife.coreserver.environment.Environment#executeCommand(com.connectlife.coreserver.environment.cmd.Cmd)
	 */
	@Override
	public void executeCommand(Cmd _cmd) throws Exception {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @return
	 * @see com.connectlife.coreserver.environment.Environment#getJsonFormattedEnvironment()
	 */
	@Override
	public String getJsonFormattedEnvironment() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @return
	 * @throws Exception
	 * @see com.connectlife.coreserver.environment.Environment#getFindProcessor()
	 */
	@Override
	public FindProcessor getFindProcessor() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}