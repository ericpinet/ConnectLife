/**
 *  DeviceJson.java
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
 * Service JSON representing a device in the network.
 * 
 * @author ericpinet
 * <br> 2016-01-23
 */
public class DeviceJson implements Device {
	
	/**
	 * Service definition of the service.
	 */
	private ServiceDefinition m_service_definition;
	
	/**
	 * Service information of the Device.
	 */
	private ServiceInfo m_service_info;
	
	/**
	 * Flag to indicate if this service is sync with the application environment.
	 */
	private boolean m_isSync;
	
	/**
	 * Default constructor.
	 * 
	 * @param _definition Service definition of the service.
	 */
	public DeviceJson(ServiceDefinition _definition, ServiceInfo _service_info){
		m_service_definition = _definition;
		m_service_info = _service_info;
		m_isSync = false;
	}

	/**
	 * Return the service definition.
	 * 
	 * @return ServiceDefinition of the service.
	 * @see com.connectlife.coreserver.environment.device.Device#getDefinition()
	 */
	@Override
	public ServiceDefinition getDefinition() {
		return m_service_definition;
	}
	
	/**
	 * Return the service information from the network.
	 * 
	 * @return ServiceInfo for this device.
	 * @see com.connectlife.coreserver.environment.device.Device#getServiceInfo()
	 */
	@Override
	public ServiceInfo getServiceInfo() {
		return m_service_info;
	}

	/**
	 * Indicate if the device is synchronized with the environment of the application.
	 * If not, you can run the sync().
	 * 
	 * @return True if the device is correctly synchronized with the application environment. 
	 * @see com.connectlife.coreserver.environment.device.Device#isSync()
	 */
	@Override
	public boolean isSync() {
		return m_isSync;
	}

	/**
	 * Synchronize the device  with the application environment. 
	 * 
	 * @return True if the device is correctly synchronized with the application environment.
	 * @see com.connectlife.coreserver.environment.device.Device#sync()
	 */
	@Override
	public boolean sync() {
		// TODO Auto-generated method stub
		return false;
	}

	

}
