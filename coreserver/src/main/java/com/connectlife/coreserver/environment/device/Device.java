/**
 *  Device.java
 *  coreserver
 *
 *  Created by ericpinet on 2016-01-23.
 *  Copyright (c) 2016 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.connectlife.coreserver.environment.device;

import javax.jmdns.ServiceInfo;

import com.clapi.simulator.device.ServiceDefinition;

/**
 * Device on the network.
 * 
 * @author ericpinet
 * <br> 2016-01-23
 */
public interface Device {
	
	/**
	 * Return the service definition.
	 * 
	 * @return ServiceDefinition of the device.
	 */
	public ServiceDefinition getDefinition(); 
	
	/**
	 * Return the service info
	 * @return ServiceInfo of the device.
	 */
	public ServiceInfo getServiceInfo();
	
	/**
	 * Indicate if the device is synchronized with the environment of the application.
	 * If not, you can run the sync().
	 * 
	 * @return True if the device is correctly synchronized with the application environment. 
	 */
	public boolean isSync();
	
	/**
	 * Synchronize the device  with the application environment. 
	 * @return True if the device is correctly synchronized with the application environment.
	 */
	public boolean sync();

}
