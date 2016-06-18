/**
 *  CmdFactory.java
 *  coreserver
 *
 *  Created by ericpinet on 2016-03-28.
 *  Copyright (c) 2016 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.connectlife.coreserver.environment.cmd;

import com.clapi.data.Accessory;
import com.clapi.data.Characteristic;
import com.clapi.data.Person;
import com.clapi.data.Room;

/**
 * Environment command factory. Use this class to create command that you can execute on the environment data.
 * 
 * @author ericpinet
 * <br> 2016-03-28
 */
public abstract class CmdFactory {
	
	/**
	 * Return the CmdAddPerson. 
	 * 
	 * @param _person Person to add in the environment.
	 * @return Return the CmdAddPerson. 
	 */
	static public CmdAddPerson getCmdAddPerson(Person _person){
		return new CmdAddPerson(_person);
	}
	
	/**
	 * Return the CmdAddAccessory. 
	 * 
	 * @param _accessory Accessory to add in the environment.
	 * @param _room Room where to add the accessory.
	 * @return Return the CmdAddAccessory. 
	 */
	static public CmdAddAccessory getCmdAddAccesssory(Accessory _accessory, Room _room){
		return new CmdAddAccessory(_accessory, _room);
	}
	
	/**
	 * Return the CmdRegisterAccessory. 
	 * 
	 * @param _accessory Accessory to register in the environment.
	 * @return Return the CmdRegisterAccessory. 
	 */
	static public CmdRegisterAccessory getCmdRegisterAccesssory(Accessory _accessory){
		return new CmdRegisterAccessory(_accessory);
	}
	
	/**
	 * Return the CmdUnregisterAccessory.
	 * 
	 * @param _accessory Accessory to unregister in the environment.
	 * @return Return the CmdUnregisterAccessory. 
	 */
	static public CmdUnregisterAccessory getCmdUnregisterAccesssory(Accessory _accessory){
		return new CmdUnregisterAccessory(_accessory);
	}
	
	/**
	 * Return the CmdUpdateAccessory. 
	 * 
	 * @param _accessory Accessory to update in the environment.
	 * @return Return the CmdUpdateAccessory. 
	 */
	static public CmdUpdateAccessory getCmdUpdateAccesssory(Accessory _accessory){
		return new CmdUpdateAccessory(_accessory);
	}
	
	/**
	 * Return the CmdCharacteristicWrite.
	 * 
	 * @param _characteristic Characteristic
	 * @param _target_value Target value of the characteristic
	 * @return Return the CmdCharacteristicWrite
	 */
	static public CmdCharacteristicWrite getCmdCharacteristicWrite(Characteristic _characteristic, Characteristic _target_value){
		return new CmdCharacteristicWrite(_characteristic, _target_value);
	}
	
}
