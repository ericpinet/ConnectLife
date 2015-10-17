/**
 *  Environment.java
 *  coreserver
 *
 *  Created by ericpinet on 2015-10-17.
 *  Copyright (c) 2015 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.connectlife.coreserver.environment;

// external
import javax.jmdns.ServiceEvent;

// internal

/**
 * 
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
	 * Callback called when service is discover.
	 * 
	 * @param _service Service informations.
	 * @see com.connectlife.coreserver.environment.discover.DiscoveryListner#serviceDiscover(javax.jmdns.ServiceEvent)
	 */
	public void serviceDiscover(ServiceEvent _service);

	/**
	 * Callback called when service is removed.
	 * 
	 * @param _service Service information.
	 * @see com.connectlife.coreserver.environment.discover.DiscoveryListner#serviceRemove(javax.jmdns.ServiceEvent)
	 */
	public void serviceRemove(ServiceEvent _service);
}
