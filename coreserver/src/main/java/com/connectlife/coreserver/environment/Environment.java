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
import com.connectlife.coreserver.environment.cmd.Cmd;
import com.connectlife.coreserver.environment.find.FindProcessor;


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
	 * UnInitialize the environment data. Return in empty state ready to initialize again.
	 */
	public void unInit();
	
	/**
	 * Save the environment.
	 * 
	 * @return True if the environment is saved.
	 */
	public boolean save();
	
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
	 * Execute the command on the environment data.
	 * 
	 * @param _cmd Command to execute. See the CmdFactory to build command.
	 * @throws Exception Exception if something goes wrong.
	 */
	public void executeCommand(Cmd _cmd) throws Exception;
	
	/**
	 * Return the find processor of this environment. All data return was in read only state. 
	 * Change on this data will not affect the current environment data.
	 * 
	 * @return FindProcessor Return the find processor of this environment.
	 */
	public FindProcessor getFindProcessorReadOnly();
	
	/**
	 * Add a person in the data. The uid of the person will be generated
	 * during the adding process.
	 * 
	 * @param _person Person to add in the environment.
	 * @return Person added to the environment whit his generated uid.
	 * @throws Exception If something goes wrong.
	 * @deprecated Use executeCommand(Cmd _cmd) throws Exception;
	 */
	public Person addPerson(Person _person) throws Exception;
	
	/**
	 * Update a person in the data.
	 * 
	 * @param _person Person to update in the environment.
	 * @return Person updated in the environment.
	 * @throws Exception If something goes wrong.
	 * @deprecated Use executeCommand(Cmd _cmd) throws Exception;
	 */
	public Person updatePerson(Person _person) throws Exception;
	
	/**
	 * Delete a person in the data.
	 * 
	 * @param _person Person to delete in the environment.
	 * @return Person deleted in the environment.
	 * @throws Exception If something goes wrong.
	 * @deprecated Use executeCommand(Cmd _cmd) throws Exception;
	 */
	public Person deletePerson(Person _person) throws Exception;
	
}
