/**
 *  ApiProcessor.java
 *  coreserver
 *
 *  Created by ericpinet on 2015-10-16.
 *  Copyright (c) 2015 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.connectlife.coreserver.apiserver;

// external
import org.apache.thrift.TException;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.connectlife.clapi.Accessory;
// internal
import com.connectlife.clapi.CLApi;
import com.connectlife.clapi.Data;
import com.connectlife.clapi.Home;
import com.connectlife.clapi.Person;
import com.connectlife.clapi.Room;
import com.connectlife.clapi.Zone;
import com.connectlife.coreserver.environment.Environment;
import com.connectlife.coreserver.environment.UIDGenerator;
import com.connectlife.coreserver.tools.errormanagement.StdOutErrLog;
import com.google.inject.Inject;

/**
 * Processor of the api. 
 * 
 * @author ericpinet
 * <br> 2015-10-16
 */
public class ApiProcessor implements CLApi.Iface {
	
	/**
	 * Logger use for this class.
	 */
	private final Logger m_logger = LogManager.getLogger(ApiThriftJson.class);
	
	/**
	 * Api server version.
	 * 
	 * To check compatibility the difference with the first and third digit must be the same 
	 * whit the client version.
	 * 
	 * Exemple : Server version 0,1,2 : 0 - 2 = -2
	 *           Client version 1,1,3 : 1 - 3 = -2 
	 *           
	 *           Client and Server are compatible. 
	 */
	public static final int API_SERVER_VERSION[] = { 1, 0, 0 };
	
	/**
	 * Environment manager use to respond at the client.
	 */
	private final Environment m_environment;
	
	/**
	 * Defautl constructor.
	 * @param _env Environment manager use to process client request.
	 */
	@Inject
	public ApiProcessor(Environment _env){
		m_environment = _env;
	}

	/**
	 * @return The server version number.
	 * @throws TException Thrift exception if something goes wrong.
	 * @see com.connectlife.clapi.CLApi.Iface#getVersion()
	 */
	@Override
	public String getVersion() throws TException {
		return new String( API_SERVER_VERSION[0] + "." + API_SERVER_VERSION[1] + "." + API_SERVER_VERSION[2] );
	}

	/**
	 * @param version String representing the client version.
	 * @return True if server is compatible with the client.
	 * @throws TException Thrift exception if something goes wrong.
	 * @see com.connectlife.clapi.CLApi.Iface#checkCompatibility(java.lang.String)
	 */
	@Override
	public boolean checkCompatibility(String version) throws TException {
		boolean ret_val = false;
		
		try{
			String[] version_id_txt = version.split("\\.");
			int[] version_id = { Integer.parseInt(version_id_txt[0]),
								 Integer.parseInt(version_id_txt[1]),
								 Integer.parseInt(version_id_txt[2])
							   };
			
			
			if( (version_id[0] - version_id[2]) ==  (API_SERVER_VERSION[0] - API_SERVER_VERSION[2]) ){
				
				ret_val = true;
			}
			else{
				m_logger.info("The client version:"+version + " isn't compatible with this server version :"+
						      new String( API_SERVER_VERSION[0] + "." + API_SERVER_VERSION[1] + "." + API_SERVER_VERSION[2] ));
			}
		}
		catch(Exception e){
			m_logger.error("Unable to check compatibility of client api : "+version);
			StdOutErrLog.tieSystemOutAndErrToLog();
			e.printStackTrace();
		}
		
		return ret_val;
	}

	/**
	 * @return Environment data in json format.
	 * @throws TException Thrift exception if something goes wrong.
	 * @see com.connectlife.clapi.CLApi.Iface#getEnvironmentDataJson()
	 */
	@Override
	public String getEnvironmentDataJson() throws TException {
		return m_environment.getJsonEnvironment();
	}

	/**
	 * @return
	 * @throws TException
	 * @see com.connectlife.clapi.CLApi.Iface#getData()
	 */
	@Override
	public Data getData() throws TException {
		return m_environment.getData();
	}

	/**
	 * Add or update person data in the environment.
	 * 
	 * @param person A person.
	 * @return True if data updated.
	 * @throws TException
	 * @see com.connectlife.clapi.CLApi.Iface#addPerson(com.connectlife.clapi.Person)
	 */
	@Override
	public boolean addPerson(Person person) throws TException {
		return m_environment.addPerson(person);
	}

	/**
	 * Delete person data in the environment.
	 * 
	 * @param person A person.
	 * @return True if data updated.
	 * @throws TException
	 * @see com.connectlife.clapi.CLApi.Iface#deletePerson(com.connectlife.clapi.Person)
	 */
	@Override
	public boolean deletePerson(Person person) throws TException {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Add or update home data in the environment.
	 * 
	 * @param home A home.
	 * @return True if data updated.
	 * @throws TException
	 * @see com.connectlife.clapi.CLApi.Iface#addHome(com.connectlife.clapi.Home)
	 */
	@Override
	public boolean addHome(Home home) throws TException {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Delete home data in the environment.
	 * 
	 * @param home A home.
	 * @return True if data was deleted.
	 * @throws TException
	 * @see com.connectlife.clapi.CLApi.Iface#deleteHome(com.connectlife.clapi.Home)
	 */
	@Override
	public boolean deleteHome(Home home) throws TException {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Add zone data in the environment.
	 * 
	 * @param home A home.
	 * @param zone A zone.
	 * @return True if data updated.
	 * @throws TException
	 * @see com.connectlife.clapi.CLApi.Iface#addZone(com.connectlife.clapi.Home, com.connectlife.clapi.Zone)
	 */
	@Override
	public boolean addZone(Home home, Zone zone) throws TException {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Delete zone in the environment.
	 * 
	 * @param zone A zone.
	 * @return True if data was deleted.
	 * @throws TException
	 * @see com.connectlife.clapi.CLApi.Iface#deleteZone(com.connectlife.clapi.Zone)
	 */
	@Override
	public boolean deleteZone(Zone zone) throws TException {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Add or update room data in the environment.
	 * 
	 * @param zone A zone.
	 * @param room A room.
	 * @return True if data was updated.
	 * @throws TException
	 * @see com.connectlife.clapi.CLApi.Iface#addRoom(com.connectlife.clapi.Zone, com.connectlife.clapi.Room)
	 */
	@Override
	public boolean addRoom(Zone zone, Room room) throws TException {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Delete room data in the environment.
	 * 
	 * @param room A room.
	 * @return True if data was deleted.
	 * @throws TException
	 * @see com.connectlife.clapi.CLApi.Iface#deleteRoom(com.connectlife.clapi.Room)
	 */
	@Override
	public boolean deleteRoom(Room room) throws TException {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Return list of Accessories not matched in a room.
	 * 
	 * @return List of Accessory.
	 * @throws TException
	 * @see com.connectlife.clapi.CLApi.Iface#getNotMatchedAccessories()
	 */
	@Override
	public List<Accessory> getNotMatchedAccessories() throws TException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Attach a Accessory at one room.
	 * 
	 * @param room A room.
	 * @param accessory A accessory.
	 * @return True if data was correctly updated.
	 * @throws TException
	 * @see com.connectlife.clapi.CLApi.Iface#attachAccessory(com.connectlife.clapi.Room, com.connectlife.clapi.Accessory)
	 */
	@Override
	public boolean attachAccessory(Room room, Accessory accessory) throws TException {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * @return
	 * @throws TException
	 * @see com.connectlife.clapi.CLApi.Iface#generateUID()
	 */
	@Override
	public String generateUID() throws TException {
		return UIDGenerator.getUID();
	}
}
