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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.clapi.data.Accessory;
import com.clapi.simulator.device.ServiceDefinition;
import com.connectlife.coreserver.Application;
import com.connectlife.coreserver.tools.errormanagement.StdOutErrLog;

/**
 * Service JSON representing a device in the network.
 * 
 * @author ericpinet
 * <br> 2016-01-23
 */
public class DeviceJson implements Device {
	
	/**
	 * Logger use for this class.
	 */
	private static Logger m_logger = LogManager.getLogger(DeviceJson.class);
	
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
	private boolean m_isRegister;
	
	/**
	 * Flag to indicate if the device is synchronized with the application environment. 
	 * If the device was try to register, it's synchronized.
	 */
	private boolean m_isSynchronized;
	
	/**
	 * Default constructor.
	 * 
	 * @param _definition Service definition of the service.
	 * @param _service_info Service information from JmDns
	 */
	public DeviceJson(ServiceDefinition _definition, ServiceInfo _service_info){
		m_service_definition = _definition;
		m_service_info = _service_info;
		m_isRegister = false;
		m_isSynchronized = false;
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
	 * If not, you can run the register().
	 * 
	 * @return True if the device is correctly synchronized with the application environment. 
	 * @see com.connectlife.coreserver.environment.device.Device#isRegister()
	 */
	@Override
	public boolean isRegister() {
		return m_isRegister;
	}
	
	/**
	 * Indicate if the device is synchronized with the environment of the application.
	 * If not, you can run the register().
	 * 
	 * @return True if the device is correctly synchronized with the application environment. 
	 * @see com.connectlife.coreserver.environment.device.Device#isRegister()
	 */
	@Override
	public boolean isSyncronized(){
		return m_isSynchronized;
	}

	/**
	 * Register the device  with the application environment. If the device is already 
	 * setup in environment, the accessory will be updated with UID. 
	 * If not, the accessory stay no register.
	 * 
	 * @return True if the device is correctly synchronized with the application environment.
	 * @see com.connectlife.coreserver.environment.device.Device#register()
	 */
	@Override
	public boolean register() {
		
		boolean ret_val = false;
		
		// check if already synch
		if(false == m_isRegister){
			
			// at the register try, the device is considerate synchronized with the application.
			m_isSynchronized = true;
			
			Accessory accessory = null;
			try {
				accessory = Application.getApp().getEnvironment().synchronizeAccessory(m_service_definition.getAccessory());
			} catch (Exception e) {
				m_logger.error(e.getMessage());
				StdOutErrLog.tieSystemOutAndErrToLog();
				e.printStackTrace();
			}
			if(null != accessory){
				// Update the service definition with the UID.
				ret_val = m_isRegister = true;
				m_service_definition.setAccessory(accessory);
				m_logger.info("Device "+ m_service_definition.getHostname() +":"+ m_service_definition.getPort() +" register.");
			}
			else{
				m_logger.info("Device "+ m_service_definition.getHostname() +":"+ m_service_definition.getPort() +" cannot be register. It's not setup in the application environment.");
			}
		}
		else{
			m_logger.warn("Device "+ m_service_definition.getHostname() +":"+ m_service_definition.getPort() +" is already synchronized with the application environment.");
		}
		
		return ret_val;
	}

	/**
	 * Unregister the device  with the application environment. 
	 * If device is unreachable on the network the unregister() will be call. 
	 * 
	 * @return True if the device is correctly unregister with the application environment.
	 * @see com.connectlife.coreserver.environment.device.Device#unregister()
	 */
	@Override
	public boolean unregister() {
		boolean ret_val = false;
		
		// the device will be unsynchronized after the unregister.
		m_isSynchronized = false;
		
		// check if already synch
		if(true == m_isRegister){
			
			Accessory accessory = null;
			try {
				accessory = Application.getApp().getEnvironment().unsynchronizeAccessory(m_service_definition.getAccessory());
			} catch (Exception e) {
				m_logger.error(e.getMessage());
				StdOutErrLog.tieSystemOutAndErrToLog();
				e.printStackTrace();
			}
			if(null != accessory){
				// Update the service definition with the register status.
				m_service_definition.setAccessory(accessory);
				
				m_isRegister = false;
				ret_val = true;
				
				m_logger.info("Device "+ m_service_definition.getHostname() +":"+ m_service_definition.getPort() +" register.");
				
			}
			else{
				m_logger.info("Device "+ m_service_definition.getHostname() +":"+ m_service_definition.getPort() +" cannot be register. It's not setup in the application environment.");
				m_isRegister = false;
			}
			
		}
		else{
			m_logger.warn("Device "+ m_service_definition.getHostname() +":"+ m_service_definition.getPort() +" is already synchronized with the application environment.");
		}
		
		return ret_val;
	}

	

}
