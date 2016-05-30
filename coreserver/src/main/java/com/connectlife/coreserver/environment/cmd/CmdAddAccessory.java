/**
 *  CmdAddAccessory.java
 *  coreserver
 *
 *  Created by ericpinet on 2016-03-28.
 *  Copyright (c) 2016 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.connectlife.coreserver.environment.cmd;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.clapi.data.Accessory;
import com.clapi.data.Room;
import com.connectlife.coreserver.environment.UIDGenerator;

/**
 * Command to add a new accessory in the environment.
 * 
 * @author ericpinet
 * <br> 2016-03-28
 */
public class CmdAddAccessory extends CmdDefault {
	
	/**
	 * Logger use for this class.
	 */
	private static Logger m_logger = LogManager.getLogger(CmdAddAccessory.class);
	
	/**
	 * Person to add in the environment.
	 */
	private Accessory m_accessory;
	
	/**
	 * Room where to add the accessory.
	 */
	private Room m_room;
	
	/**
	 * Default constructor.
	 *  
	 * @param _accessory Accessory to add in the environment.
	 * @param _room Room where add the accessory.
	 */
	public CmdAddAccessory (Accessory _accessory, Room _room){
		m_accessory = _accessory;
		m_room = _room;
	}
	
	/**
	 * Execute the cmd on the environment.
	 * 
	 * @throws Exception Exception when something goes wrong.
	 * @see com.connectlife.coreserver.environment.cmd.Cmd#execute()
	 */
	@Override
	public void execute() throws Exception {
		
		m_logger.info("Execution start ...");
		
		// TODO: Complete command
		throw new Exception("Not implemented yet!");
		/*
		// Get the find processor
		FindProcessor find = m_context.getFindProcessorReadWrite();
		
		// check the accessory to add in the environment
		if( null == m_accessory ){
			m_logger.error("Error! It's not possible to add null accessory in the environment.");
			throw new Exception ("Error! It's not possible to add null accessory in the environment.");
		}
		
		if( false == m_accessory.getUid().isEmpty() ){
			m_logger.error("Error! It's not possible to add a accessory with a UID.");
			throw new Exception ("Error! It's not possible to add a accessory with a UID.");
		}
				
		// check if the accessory is already added in a room
		// find the accessory by the serial number.
		Accessory accessory = find.findAccessory(m_accessory);
		if(null == accessory){
			// the accessory isn't added
			// we can add it in the room
			Room room = find.findRoom(m_room);
			if(null != room){

				// add the accessory and set a UID.
				m_accessory.setUid(UIDGenerator.getUID());
				
				// Adding the accessory in the room.
				room.getAccessories().add(m_accessory);
				
				// force all device to try again a synchronization
				m_context.getDeviceManager().forceSynchronizationOfAllDevices();
				
				// set the data change
				this.m_data_is_changed = true;
			}
			else{
				throw new Exception("Room not found.");
			}
		}
		else{
			// the acessory was already added in a room
			throw new Exception("Accessory was already added in a room. Remove the accessory before try again.");
		}
		
		m_logger.info("Execution completed.");
		*/
	}
	
}
