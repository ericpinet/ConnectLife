/**
 *  DiscoveryService.java
 *  coreserver
 *
 *  Created by ericpinet on 2015-10-31.
 *  Copyright (c) 2015 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.connectlife.coreserver.environment.discover;


/**
 * Interface for the discovery service system. 
 * 
 * @author ericpinet
 * <br> 2015-10-31
 */
public interface DiscoveryService {

	/**
	 * Start the discovery manager.
	 */
	public void start();
	
	/**
	 * Stop the discovering process.
	 */
	public void stop();
	
	/**
	 * Add a new listner on the discovery manager.
	 * @param _listner Listener that will be receive the notification.
	 */
	public void addListner(DiscoveryListner _listner);
}
