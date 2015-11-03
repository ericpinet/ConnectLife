/**
 *  Environment.java
 *  coreserver
 *
 *  Created by ericpinet on 2015-10-17.
 *  Copyright (c) 2015 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.connectlife.coreserver.environment;

import java.util.List;
import java.util.Observer;
import com.connectlife.clapi.*;


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
	 * Return a JSON string representing the environment.
	 * @return JSON string of the environment.
	 */
	public String getJsonEnvironment();
	
	/**
	 * Adds an observer to the set of observers for this object, provided that it 
	 * is not the same as some observer already in the set.
	 * @param _o Observer that want to be updated when the environment data was updated.
	 */
	public void addObserver(Observer _o);
	
	/**
	 * Deletes an observer from the set of observers of this object.
	 * @param _o Remove this observer from the environment data.
	 */
	public void deleteObserver(Observer _o);
	
	/**
	 * Clears the observer list so that this object no longer has any observers.
	 */
	public void deleteObservers();
	
	/**
	 * Returns the number of observers of this Observable object.
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
	 * @param _obj Any object.
	 */
	public void notifyObservers(Object _obj);
	
	/**
	 * Tests if this object has changed.
	 * @return True if this object are changed.
	 */
	public boolean hasChanged();
	
	/**
	 * Return Data of the environment.
	 * @return Data of the environment.
	 */
	public Data getData();
	
	/**
	 * Add or update person data in the environment.
	 * 
	 * @param person A person.
	 * @return True if data updated.
	 */
	public boolean addPerson(Person person);

	/**
	 * Delete person data in the environment.
	 * 
	 * @param person A person.
	 * @return True if data updated.
	 */
	public boolean deletePerson(Person person);

	/**
	 * Add or update home data in the environment.
	 * 
	 * @param home A home.
	 * @return True if data updated.
	 */
	public boolean addHome(Home home);

	/**
	 * Delete home data in the environment.
	 * 
	 * @param home A home.
	 * @return True if data was deleted.
	 */
	public boolean deleteHome(Home home);

	/**
	 * Add zone data in the environment.
	 * 
	 * @param home A home.
	 * @param zone A zone.
	 * @return True if data updated.
	 */
	public boolean addZone(Home home, Zone zone);

	/**
	 * Delete zone in the environment.
	 * 
	 * @param zone A zone.
	 * @return True if data was deleted.
	 */
	public boolean deleteZone(Zone zone);

	/**
	 * Add or update room data in the environment.
	 * 
	 * @param zone A zone.
	 * @param room A room.
	 * @return True if data was updated.
	 */
	public boolean addRoom(Zone zone, Room room);

	/**
	 * Delete room data in the environment.
	 * 
	 * @param room A room.
	 * @return True if data was deleted.
	 */
	public boolean deleteRoom(Room room);

	/**
	 * Return list of Accessories not matched in a room.
	 * 
	 * @return List of Accessory.
	 */
	public List<Accessory> getNotMatchedAccessories();

	/**
	 * Attach a Accessory at one room.
	 * 
	 * @param room A room.
	 * @param accessory A accessory.
	 * @return True if data was correctly updated.
	 */
	public boolean attachAccessory(Room room, Accessory accessory);
	
	
}
