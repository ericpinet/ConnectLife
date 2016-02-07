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
import com.connectlife.coreserver.environment.FindProcessor;
import com.connectlife.coreserver.environment.device.DeviceManager;
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
	 * @return
	 * @see com.connectlife.coreserver.environment.Environment#getDeviceManager()
	 */
	@Override
	public DeviceManager getDeviceManager() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @param _accessory
	 * @param _room
	 * @return
	 * @see com.connectlife.coreserver.environment.Environment#registerAccessory(com.clapi.data.Accessory, com.clapi.data.Room)
	 */
	@Override
	public String registerAccessory(Accessory _accessory, Room _room) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @return
	 * @see com.connectlife.coreserver.environment.Environment#getFindProcessor()
	 */
	@Override
	public FindProcessor getFindProcessor() {
		return new FindProcessor(CreateTestData.getData());
	}

	/**
	 * @param _person
	 * @return
	 * @throws Exception
	 * @see com.connectlife.coreserver.environment.Environment#addPerson(com.clapi.data.Person)
	 */
	@Override
	public String addPerson(Person _person) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @param _accessory
	 * @return
	 * @throws Exception
	 * @see com.connectlife.coreserver.environment.Environment#synchronizeAccessory(com.clapi.data.Accessory)
	 */
	@Override
	public Accessory synchronizeAccessory(Accessory _accessory) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @param _accessory
	 * @return
	 * @throws Exception
	 * @see com.connectlife.coreserver.environment.Environment#unsynchronizeAccessory(com.clapi.data.Accessory)
	 */
	@Override
	public Accessory unsynchronizeAccessory(Accessory _accessory) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}