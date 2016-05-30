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
import com.connectlife.coreserver.environment.cmd.Cmd;

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
	 * Return a JSON string representing the environment.
	 * 
	 * @return JSON string of the environment.
	 */
	public String getJsonEnvironment();
	
	/**
	 * Return a JSON formatted string representing the environment.
	 * 
	 * @return JSON string of the environment.
	 */
	public String getJsonFormattedEnvironment();
	
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
	 * Execute the command on the environment data.
	 * 
	 * @param _cmd Command to execute. See the CmdFactory to build command.
	 * @throws Exception Exception if something goes wrong.
	 */
	public void executeCommand(Cmd _cmd) throws Exception;
}
