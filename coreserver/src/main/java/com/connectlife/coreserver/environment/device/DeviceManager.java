/**
 *  DeviceManager.java
 *  coreserver
 *
 *  Created by ericpinet on 2016-01-23.
 *  Copyright (c) 2016 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.connectlife.coreserver.environment.device;

import java.util.List;

/**
 * The service manager discover, control and monitor all services in the network.
 * 
 * @author ericpinet
 * <br> 2016-01-23
 */
public interface DeviceManager {
	
	/**
	 * Initialization the service manager.
	 * 
	 * @return True if initialization completed with success.
	 */
	public boolean init();

	/**
	 * Return True is the service manager is correctly initialized.
	 * @return True if the service manager is correctly initialized.
	 */
	public boolean isInit();

	/**
	 * UnInitialize the service manager. Return in empty state ready to initialize again.
	 */
	public void unInit();
	
	/**
	 * Force all devices to synchronization with the environment.
	 */
	public void forceSynchronizationOfAllDevices();
	
	/**
	 * Return the list of the devices in the environment.
	 * 
	 * @return Device in the environment.
	 */
	public List<Device> getDevices();

}
