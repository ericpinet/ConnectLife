/**
 *  Environment.java
 *  coreserver
 *
 *  Created by ericpinet on 2015-10-17.
 *  Copyright (c) 2015 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.connectlife.coreserver.environment;

import java.util.Observer;
import com.clapi.data.*;
import com.connectlife.coreserver.environment.device.DeviceManager;


/**
 * Environment is the representation of all objects in the environment. 
 * 
 * @author ericpinet
 * <br> 2015-10-17
 */
public interface Environment {

	/**
	 * Initialization the environment data.
	 * 
	 * @return True if initialization completed with success.
	 */
	public boolean init();

	/**
	 * Return True is the environment data is correctly initialized.
	 * 
	 * @return True if the environment data is correctly initialized.
	 */
	public boolean isInit();
	
	/**
	 * Return True is the environment data is correctly Loaded.
	 * 
	 * @return True if this module is loaded.
	 */
	public boolean isLoaded();
	
	/**
	 * Return True is the environment data is correctly saved.
	 * 
	 * @return True is the environment is saved.
	 */
	public boolean isSaved();

	/**
	 * UnInitialize the environment data. Return in empty state ready to initialize again.
	 */
	public void unInit();
	
	/**
	 * Return the device manager for the environment.
	 * 
	 * @return The device manager of the environment.
	 */
	public DeviceManager getDeviceManager();
	
	/**
	 * Return a JSON string representing the environment.
	 * 
	 * @return JSON string of the environment.
	 */
	public String getJsonEnvironment();
	
	/**
	 * Adds an observer to the set of observers for this object, provided that it 
	 * is not the same as some observer already in the set.
	 * 
	 * @param _o Observer that want to be updated when the environment data was updated.
	 */
	public void addObserver(Observer _o);
	
	/**
	 * Deletes an observer from the set of observers of this object.
	 * 
	 * @param _o Remove this observer from the environment data.
	 */
	public void deleteObserver(Observer _o);
	
	/**
	 * Clears the observer list so that this object no longer has any observers.
	 */
	public void deleteObservers();
	
	/**
	 * Returns the number of observers of this Observable object.
	 * 
	 * @return Number of observers.
	 */
	public int countObservers();
	
	/**
	 * If this object has changed, as indicated by the hasChanged method, then notify all of 
	 * its observers and then call the clearChanged method to indicate that this object 
	 * has no longer changed.
	 */
	public void notifyObservers();
	
	/**
	 * If this object has changed, as indicated by the hasChanged method, then notify all 
	 * of its observers and then call the clearChanged method to indicate that this object 
	 * has no longer changed.
	 * 
	 * @param _obj Any object.
	 */
	public void notifyObservers(Object _obj);
	
	/**
	 * Tests if this object has changed.
	 * 
	 * @return True if this object are changed.
	 */
	public boolean hasChanged();
	
	/**
	 * Return clone of the Data environment.
	 * 
	 * @return Data of the environment.
	 */
	public Data getData();	
	
	/**
	 * Return the find processor of this environment.
	 * 
	 * @return FindProcessor Return the find processor of this environment.
	 */
	public FindProcessor getFindProcessor();
	
	/**
	 * Add a person in the data. 
	 * 
	 * @param _person Person to add in the environment.
	 * @return UID of the person.
	 * @throws Exception If something goes wrong.
	 */
	public String addPerson(Person _person) throws Exception;
	
	/**
	 * Update a person in the data.
	 * 
	 * @param _person Person to update in the environment.
	 * @throws Exception If something goes wrong.
	 */
	public void updatePerson(Person _person) throws Exception;
	
	/**
	 * Synchronize the accessory in the environment.
	 * If this accessory is already in the environment the Accessory was file with UID and return. (The accessory is found by the serial number)
	 * If this accessory wasn't in the environment, this function return null.
	 * 
	 * @param _accessory Accessory to synchronize with the environment.
	 * @return Accessory updated with the UID if it's in the environment
	 * @throws Exception If something goes wrong.
	 */
	public Accessory synchronizeAccessory(Accessory _accessory) throws Exception;
	
	/**
	 * Unsynchronized the accessory in the environment.
	 * The accessory register will be removed.
	 * 
	 * @param _accessory Accessory to unsynchronized with the environment.
	 * @return Accessory updated with the register if it's in the environment
	 * @throws Exception If something goes wrong.
	 */
	public Accessory unsynchronizeAccessory(Accessory _accessory) throws Exception;
	
	/**
	 * Register the accessory in the room.
	 * 
	 * @param _accessory Accessory to register.
	 * @param _room Room where register the accessory.
	 * @return UID of the accessory after the registration.
	 * @throws Exception If something goes wrong.
	 */
	public String registerAccessory(Accessory _accessory, Room _room) throws Exception;
	
}
