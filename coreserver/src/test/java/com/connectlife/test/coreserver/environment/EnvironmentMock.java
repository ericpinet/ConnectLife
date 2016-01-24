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
import com.connectlife.coreserver.environment.discover.DiscoveryService;
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
	 * @see com.connectlife.coreserver.environment.Environment#isLoaded()
	 */
	@Override
	public boolean isLoaded() {
		return false;
	}

	/**
	 * @return
	 * @see com.connectlife.coreserver.environment.Environment#isSaved()
	 */
	@Override
	public boolean isSaved() {
		return false;
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
	 * @return
	 * @see com.connectlife.coreserver.environment.Environment#getData()
	 */
	@Override
	public Data getData() {
		return CreateTestData.getData();
	}

	/**
	 * @param firstname
	 * @param lastname
	 * @param imageurl
	 * @return
	 * @see com.connectlife.coreserver.environment.Environment#addPerson(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public String addPerson(String firstname, String lastname, String imageurl) {
		return null;
	}
}