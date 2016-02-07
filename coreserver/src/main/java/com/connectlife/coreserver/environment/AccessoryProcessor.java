/**
 *  AccessoryProcessor.java
 *  coreserver
 *
 *  Created by ericpinet on 2016-01-24.
 *  Copyright (c) 2016 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.connectlife.coreserver.environment;

import com.clapi.data.Accessory;
import com.clapi.data.Room;

/**
 * Helper the work with environment. Useful function to manage accessory in the environment.
 * 
 * @author ericpinet
 * <br> 2016-01-24
 */
public abstract class AccessoryProcessor {
	
	/**
	 * Default constructor is private to ensure that is never instantiated.
	 */
	private AccessoryProcessor (){
	}
	
	/**
	 * Register the accessory and link with the room.
	 * 
	 * @param _accessory Accessory to register in the application environment.
	 * @param _room Room to link the accessory.
	 * @throws Exception Exception when the register cannot be completed.
	 */
	public static void registerAccessory(Accessory _accessory, Room _room) throws Exception{

		// check if the accessory is already register in a room
		// find the accessory by the serial number.
		Accessory accessory = FindProcessor.findAccessoryBySerialNumber(_accessory.getSerialnumber());
		if(null == accessory){
			// the accessory isn't register
			// we can add it in the room
			
			Room room = FindProcessor.findRoomByUid(_room.getUid());
			if(null != room){
				// Register the accessory and set a UID.
				_accessory.setUid(UIDGenerator.getUID());
				_accessory.setRegister(true);
				
				// Adding the accessory in the room.
				room.getAccessories().add(_accessory);
			}
			else{
				throw new Exception("The accessory cannot be register. The room is unreacheble.");
			}
		}
		else{
			throw new Exception("The accessory is already register. Unregister the accessory before retry.");
		}
	}
	
	/**
	 * Synchronize the accessory in the environment.
	 * If this accessory is already in the environment the Accessory was file with UID and return. (The accessory is found by the serial number)
	 * If this accessory wasn't in the environment, this function return null.
	 * 
	 * @param _accessory Accessory to synchronize with the environment.
	 * @return Accessory updated with the UID if it's in the environment, null if the accessory isn't present in the environment.
	 */
	public static Accessory synchronizeAccessory(Accessory _accessory) {

		// find the accessory by the serial number.
		Accessory accessory = FindProcessor.findAccessoryBySerialNumber(_accessory.getSerialnumber());
		
		// if accessory is find register.
		if(null != accessory){
			accessory.setRegister(true);
		}

		return accessory;
	}
	
	/**
	 * Unsynchronized the accessory in the environment.
	 * The accessory register will be removed.
	 * 
	 * @param _accessory Accessory to unsynchronized with the environment.
	 * @return Accessory updated with the register if it's in the environment, null if the accessory isn't present in the environment.
	 */
	public static Accessory unsynchronizeAccessory(Accessory _accessory) {

		// find the accessory by the serial number.
		Accessory accessory = FindProcessor.findAccessoryBySerialNumber(_accessory.getSerialnumber());
		
		// if accessory is find register.
		if(null != accessory){
			accessory.setRegister(false);
		}

		return accessory;
	}

}
