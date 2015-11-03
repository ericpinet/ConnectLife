/**
 *  ConsoleMock.java
 *  coreserver
 *
 *  Created by ericpinet on 2015-10-10.
 *  Copyright (c) 2015 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.connectlife.test.coreserver.environment;

import java.util.List;
import java.util.Observable;

import com.connectlife.clapi.Accessory;
import com.connectlife.clapi.Data;
import com.connectlife.clapi.Home;
import com.connectlife.clapi.Person;
import com.connectlife.clapi.Room;
import com.connectlife.clapi.Zone;
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
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * @return
	 * @see com.connectlife.coreserver.environment.Environment#isSaved()
	 */
	@Override
	public boolean isSaved() {
		// TODO Auto-generated method stub
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
	 * @param person
	 * @return
	 * @see com.connectlife.coreserver.environment.Environment#addPerson(com.connectlife.clapi.Person)
	 */
	@Override
	public boolean addPerson(Person person) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * @param person
	 * @return
	 * @see com.connectlife.coreserver.environment.Environment#deletePerson(com.connectlife.clapi.Person)
	 */
	@Override
	public boolean deletePerson(Person person) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * @param home
	 * @return
	 * @see com.connectlife.coreserver.environment.Environment#addHome(com.connectlife.clapi.Home)
	 */
	@Override
	public boolean addHome(Home home) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * @param home
	 * @return
	 * @see com.connectlife.coreserver.environment.Environment#deleteHome(com.connectlife.clapi.Home)
	 */
	@Override
	public boolean deleteHome(Home home) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * @param home
	 * @param zone
	 * @return
	 * @see com.connectlife.coreserver.environment.Environment#addZone(com.connectlife.clapi.Home, com.connectlife.clapi.Zone)
	 */
	@Override
	public boolean addZone(Home home, Zone zone) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * @param zone
	 * @return
	 * @see com.connectlife.coreserver.environment.Environment#deleteZone(com.connectlife.clapi.Zone)
	 */
	@Override
	public boolean deleteZone(Zone zone) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * @param zone
	 * @param room
	 * @return
	 * @see com.connectlife.coreserver.environment.Environment#addRoom(com.connectlife.clapi.Zone, com.connectlife.clapi.Room)
	 */
	@Override
	public boolean addRoom(Zone zone, Room room) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * @param room
	 * @return
	 * @see com.connectlife.coreserver.environment.Environment#deleteRoom(com.connectlife.clapi.Room)
	 */
	@Override
	public boolean deleteRoom(Room room) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * @return
	 * @see com.connectlife.coreserver.environment.Environment#getNotMatchedAccessories()
	 */
	@Override
	public List<Accessory> getNotMatchedAccessories() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @param room
	 * @param accessory
	 * @return
	 * @see com.connectlife.coreserver.environment.Environment#attachAccessory(com.connectlife.clapi.Room, com.connectlife.clapi.Accessory)
	 */
	@Override
	public boolean attachAccessory(Room room, Accessory accessory) {
		// TODO Auto-generated method stub
		return false;
	}
}