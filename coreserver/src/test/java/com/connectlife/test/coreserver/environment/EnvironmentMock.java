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
import com.connectlife.coreserver.environment.FindProcessorReadOnly;
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
	 * @see com.connectlife.coreserver.environment.Environment#save()
	 */
	@Override
	public boolean save() {
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
		return null;
	}

	/**
	 * @return
	 * @see com.connectlife.coreserver.environment.Environment#getFindProcessor()
	 */
	@Override
	public FindProcessor getFindProcessorReadOnly() {
		return new FindProcessorReadOnly(CreateTestData.getData());
	}

	/**
	 * @param _accessory
	 * @return
	 * @throws Exception
	 * @see com.connectlife.coreserver.environment.Environment#synchronizeAccessory(com.clapi.data.Accessory)
	 */
	@Override
	public Accessory synchronizeAccessory(Accessory _accessory) throws Exception {
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
		return null;
	}

	/**
	 * @param _person
	 * @return
	 * @throws Exception
	 * @see com.connectlife.coreserver.environment.Environment#addPerson(com.clapi.data.Person)
	 */
	@Override
	public Person addPerson(Person _person) throws Exception {
		return null;
	}

	/**
	 * @param _person
	 * @return
	 * @throws Exception
	 * @see com.connectlife.coreserver.environment.Environment#updatePerson(com.clapi.data.Person)
	 */
	@Override
	public Person updatePerson(Person _person) throws Exception {
		return null;
	}
	
	/**
	 * @param _person
	 * @return
	 * @throws Exception
	 * @see com.connectlife.coreserver.environment.Environment#deletePerson(com.clapi.data.Person)
	 */
	@Override
	public Person deletePerson(Person _person) throws Exception {
		return null;
	}

	/**
	 * @param _accessory
	 * @param _room
	 * @return
	 * @throws Exception
	 * @see com.connectlife.coreserver.environment.Environment#addAccessory(com.clapi.data.Accessory, com.clapi.data.Room)
	 */
	@Override
	public Accessory addAccessory(Accessory _accessory, Room _room) throws Exception {
		return null;
	}

}