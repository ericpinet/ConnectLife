/**
 *  CmdCharacteristicWrite.java
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
import com.clapi.data.Characteristic;
import com.clapi.data.Room;
import com.connectlife.coreserver.environment.UIDGenerator;
import com.connectlife.coreserver.environment.find.FindProcessor;

/**
 * Command to change value of a characteristic for an Accessory.
 * 
 * @author ericpinet
 * <br> 2016-03-28
 */
public class CmdCharacteristicWrite extends CmdDefault {
	
	/**
	 * Logger use for this class.
	 */
	private static Logger m_logger = LogManager.getLogger(CmdCharacteristicWrite.class);
	
	/**
	 * Characteristic.
	 */
	private Characteristic m_characteristic;
	
	/**
	 * Target value of the characteristic.
	 */
	private Characteristic m_target_value;
	
	
	/**
	 * Default constructor.
	 * 
	 * @param _characteristic Characteristic.
	 * @param _target_value Target value of the characteristic.
	 */
	public CmdCharacteristicWrite (Characteristic _characteristic, Characteristic _target_value){
		m_characteristic = _characteristic;
		m_target_value = _target_value;
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
		
		throw new Exception("Not yet implemented.");
		
		/* TODO - Compelte this function
		
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
