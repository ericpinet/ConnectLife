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
import com.clapi.data.Home;
import com.clapi.data.Person;
import com.clapi.data.Room;
import com.clapi.data.Zone;

/**
 * Environment command factory. Use this class to create command that you can execute on the environment data.
 * 
 * @author ericpinet
 * <br> 2016-03-28
 */
public abstract class CmdFactory {
	
	/**
	 * Return the CmdAddHome. 
	 * 
	 * @param _home Home to add in the environment.
	 * @return Return the CmdAddHome. 
	 */
	static public CmdAddHome getCmdAddHome(Home _home){
		return new CmdAddHome(_home);
	}
	
	/**
	 * Return the CmdUpdateHome. 
	 * 
	 * @param _home Home to update in the environment.
	 * @return Return the CmdUpdateHome. 
	 */
	static public CmdUpdateHome getCmdUpdateHome(Home _home){
		return new CmdUpdateHome(_home);
	}
	
	/**
	 * Return the CmdDeleteHome. 
	 * 
	 * @param _home Home to delete in the environment.
	 * @return Return the CmdDeleteHome. 
	 */
	static public CmdDeleteHome getCmdDeleteHome(Home _home){
		return new CmdDeleteHome(_home);
	}
	
	/**
	 * Return the CmdAddZone. 
	 * 
	 * @param _zone Zone to add in the environment.
	 * @return Return the CmdAddZone. 
	 */
	static public CmdAddZone getCmdAddZone(Zone _zone){
		return new CmdAddZone(_zone);
	}
	
	/**
	 * Return the CmdUpdateZone. 
	 * 
	 * @param _zone Zone to update in the environment.
	 * @return Return the CmdUpdateZone. 
	 */
	static public CmdUpdateZone getCmdUpdateZone(Zone _zone){
		return new CmdUpdateZone(_zone);
	}
	
	/**
	 * Return the CmdDeleteZone. 
	 * 
	 * @param _zone Zone to delete in the environment.
	 * @return Return the CmdDeleteZone. 
	 */
	static public CmdDeleteZone getCmdDeleteZone(Zone _zone){
		return new CmdDeleteZone(_zone);
	}
	
	/**
	 * Return the CmdAddRoom. 
	 * 
	 * @param _room Room to add in the environment.
	 * @return Return the CmdAddRoom. 
	 */
	static public CmdAddRoom getCmdAddRoom(Room _room){
		return new CmdAddRoom(_room);
	}
	
	/**
	 * Return the CmdUpdateRoom. 
	 * 
	 * @param _room Room to update in the environment.
	 * @return Return the CmdUpdateRoom. 
	 */
	static public CmdUpdateRoom getCmdUpdateRoom(Room _room){
		return new CmdUpdateRoom(_room);
	}
	
	/**
	 * Return the CmdDeleteRoom. 
	 * 
	 * @param _room Room to delete in the environment.
	 * @return Return the CmdDeleteRoom. 
	 */
	static public CmdDeleteRoom getCmdDeleteRoom(Room _room){
		return new CmdDeleteRoom(_room);
	}
	
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
	 * Return the CmddeleteAccessory. 
	 * 
	 * @param _accessory Accessory to delete in the environment.
	 * @return Return the CmdDeleteAccessory. 
	 */
	static public CmdDeleteAccessory getCmdDeleteAccesssory(Accessory _accessory){
		return new CmdDeleteAccessory(_accessory);
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
