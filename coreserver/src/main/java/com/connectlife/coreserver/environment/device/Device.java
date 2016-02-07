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
	 * Indicate if the device is register in the environment of the application.
	 * If not, you can run the register().
	 * 
	 * @return True if the device is correctly register with the application environment. 
	 */
	public boolean isRegister();
	
	/**
	 * Register the device  with the application environment. 
	 * The device can be register if it's already setup in the environment.
	 * 
	 * @return True if the device is correctly register with the application environment.
	 */
	public boolean register();
	
	/**
	 * Unregister the device  with the application environment. 
	 * If device is unreachable on the network the unregister() will be call. 
	 * 
	 * @return True if the device is correctly unregister with the application environment.
	 */
	public boolean unregister();

	/**
	 *  Indicate if the device is synchronized with the environment of the application.
	 * If not, you can run the register().
	 * 
	 * @return True if the device is correctly synchronized with the application environment. 
	 */
	boolean isSyncronized();

}
