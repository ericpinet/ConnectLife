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

import com.clapi.data.Accessory;

/**
 * Device on the network. 
 * 
 * A device is a Accessory in the network. 
 * 
 * @author ericpinet
 * <br> 2016-01-23
 */
public interface Device {
	
	/**
	 * Return the accessory linked with this device service.
	 * 
	 * @return Accessory linked with this device.
	 */
	public Accessory getAccessory();
	
	/**
	 * Return the service info
	 * 
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
	 * Unsynchronized the device with the environment. The device manage will try to synchronize again.
	 */
	public void unsynchronize();

	/**
	 * Indicate if the device is synchronized with the environment of the application.
	 * If not, you can run the register().
	 * 
	 * @return True if the device is correctly synchronized with the application environment. 
	 */
	public boolean isSyncronized();
	
	/**
	 * Update the device data in the application environment.
	 * 
	 * @return True if it correctly updated.
	 */
	public boolean updateEnvironment();
	
	/**
	 * Indicate if the device characteristic was changed since last load.
	 * 
	 * @return True is the characteristic was updated.
	 */
	public boolean isCharacteristicUpdated();

}
