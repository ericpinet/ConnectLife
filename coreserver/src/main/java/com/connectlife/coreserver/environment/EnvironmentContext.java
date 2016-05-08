/**
 *  EnvironmentContext.java
 *  coreserver
 *
 *  Created by ericpinet on 2016-03-28.
 *  Copyright (c) 2016 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.connectlife.coreserver.environment;

import com.connectlife.coreserver.environment.data.DataManager;
import com.connectlife.coreserver.environment.device.DeviceManager;
import com.connectlife.coreserver.environment.find.FindProcessor;

/**
 * Context for the execution of commands in the environment.
 * 
 * @author ericpinet
 * <br> 2016-03-28
 */
public interface EnvironmentContext {

	/**
	 * Return True is the environment data is correctly initialized.
	 * 
	 * @return True if the environment data is correctly initialized.
	 */
	public boolean isInit();
	
	/**
	 * Return data manager
	 * 
	 * @return DataManager of the environment.
	 */
	public DataManager getDataManager();
	
	/**
	 * Return the device manager of the environment.
	 * 
	 * @return DeviceManager of the environment.
	 */
	public DeviceManager getDeviceManager();
	
	/**
	 * Return the find processor of the context.
	 * 
	 * @return Find processor for the context.
	 */
	public FindProcessor getFindProcessorReadWrite();
}
