/**
 *  DeviceProcessor.java
 *  coreserver
 *
 *  Created by ericpinet on 2016-01-24.
 *  Copyright (c) 2016 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.connectlife.coreserver.environment;

import com.clapi.data.Accessory;

/**
 * Helper the work with environment. Useful function to manage device in the environment.
 * 
 * @author ericpinet
 * <br> 2016-01-24
 */
public abstract class DeviceProcessor {
	
	/**
	 * Default constructor is private to ensure that is never instantiated.
	 */
	public void FindProcessor (){
	}
	
	/**
	 * Synchronize the accessory in the environment.
	 * If this accessory is already in the environment the Accessory was file with UID and return. (The accessory is found by the serial number)
	 * If this accessory wasn't in the environment, this function return null.
	 * 
	 * @param _accessory Accessory to synchronize with the environment.
	 * @return Accessory updated with the UID if it's in the environment, null if the accessory isn't present in the environment.
	 * @see com.connectlife.coreserver.environment.Environment#synchronizeAccessory(com.clapi.data.Accessory)
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
	 * @see com.connectlife.coreserver.environment.Environment#synchronizeAccessory(com.clapi.data.Accessory)
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
