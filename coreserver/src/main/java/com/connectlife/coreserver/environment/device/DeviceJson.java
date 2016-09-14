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
import org.xnap.commons.i18n.I18n;

import com.clapi.data.Accessory;
import com.clapi.simulator.device.ServiceDefinition;
import com.connectlife.coreserver.Application;
import com.connectlife.coreserver.environment.cmd.CmdFactory;
import com.connectlife.coreserver.environment.cmd.CmdRegisterAccessory;
import com.connectlife.coreserver.environment.cmd.CmdUnregisterAccessory;
import com.connectlife.coreserver.environment.cmd.CmdUpdateAccessory;
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
	private Logger m_logger = LogManager.getLogger(getClass().getName());
	
	/**
	 * Initialization of translation system.
	 */
	private static I18n i18n = Application.i18n;
	
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
	 * Return the accessory linked with this device service.
	 * 
	 * @return Accessory linked with this device.
	 */
	public Accessory getAccessory(){
		return m_service_definition.getAccessory();
	}
	
	/**
	 * Return the service information from the network. (Http, AirPlay, etc...)
	 * 
	 * @return ServiceInfo for this device.
	 * @see com.connectlife.coreserver.environment.device.Device#getServiceInfo()
	 */
	
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
	 * Unsynchronized the device with the environment. 
	 * The device manage will try to synchronize again.
	 * 
	 * @see com.connectlife.coreserver.environment.device.Device#unsynchronize()
	 */
	@Override
	public void unsynchronize() {
		m_isSynchronized = false;
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
		if (false == m_isRegister) {
			
			// at the register try, the device is considerate synchronized with the application.
			m_isSynchronized = true;
			
			Accessory accessory = null;
			try {
				CmdRegisterAccessory command = CmdFactory.getCmdRegisterAccesssory(m_service_definition.getAccessory());
				Application.getApp().getEnvironment().executeCommand(command);
				accessory = command.getAccessory();
				
			} catch (Exception e) {
				m_logger.error(e.getMessage());
				StdOutErrLog.tieSystemOutAndErrToLog();
				e.printStackTrace();
			}
			if (null != accessory && accessory.isRegister()) {
				// Update the service definition with the UID.
				ret_val = m_isRegister = true;
				m_service_definition.setAccessory(accessory);
				m_logger.info(i18n.tr("Device ")+ m_service_definition.getHostname() +":"+ m_service_definition.getPort() +i18n.tr(" register."));
			}
			else {
				m_logger.info(i18n.tr("Device ")+ m_service_definition.getHostname() +":"+ m_service_definition.getPort() +i18n.tr(" cannot be register. It's not setup in the application environment."));
			}
		}
		else {
			m_logger.warn(i18n.tr("Device ")+ m_service_definition.getHostname() +":"+ m_service_definition.getPort() +i18n.tr(" is already synchronized with the application environment."));
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
		if (true == m_isRegister) {
			
			Accessory accessory = null;
			try {
				CmdUnregisterAccessory command = CmdFactory.getCmdUnregisterAccesssory(m_service_definition.getAccessory());
				Application.getApp().getEnvironment().executeCommand(command);
				accessory = command.getAccessory();
				
			} catch (Exception e) {
				m_logger.error(e.getMessage());
				StdOutErrLog.tieSystemOutAndErrToLog();
				e.printStackTrace();
			}
			if (null != accessory) {
				// Update the service definition with the register status.
				m_service_definition.setAccessory(accessory);
				
				m_isRegister = false;
				ret_val = true;
				
				m_logger.info(i18n.tr("Device ")+ m_service_definition.getHostname() +":"+ m_service_definition.getPort() +i18n.tr(" unregister."));
				
			}
			else{
				m_logger.info(i18n.tr("Device ")+ m_service_definition.getHostname() +":"+ m_service_definition.getPort() +i18n.tr(" cannot be register. It's not setup in the application environment."));
			}
			
		}
		else{
			m_logger.warn(i18n.tr("Device ")+ m_service_definition.getHostname() +":"+ m_service_definition.getPort() +i18n.tr(" is already synchronized with the application environment."));
		}
		
		return ret_val;
	}
	
	/**
	 * Update the device data in the application environment.
	 * 
	 * @return True if it correctly updated.
	 */
	public boolean updateEnvironment() {
		
		boolean ret_val = false;
		
		// check if already synch
		if(true == m_isRegister){
			
			try {
				CmdUpdateAccessory command = CmdFactory.getCmdUpdateAccesssory(m_service_definition.getAccessory());
				Application.getApp().getEnvironment().executeCommand(command);
				ret_val = true;
				
			} catch (Exception e) {
				m_logger.error(e.getMessage());
				StdOutErrLog.tieSystemOutAndErrToLog();
				e.printStackTrace();
			}
		}
		else{
			m_logger.warn(i18n.tr("Device ")+ m_service_definition.getHostname() +":"+ m_service_definition.getPort() +i18n.tr(" unable to update environment for a not register device."));
		}
		
		return ret_val;
	}
	
	/**
	 * Indicate if the device characteristic was changed since last load.
	 * The device must be register before check for updated characteristics.
	 * 
	 * @return True is the characteristic was updated.
	 */
	@Override
	public boolean isCharacteristicUpdated(){
		
		boolean ret_val = false;
		
		// the device must be register before check for update.
		if (true == m_isRegister) {
			
			// load the device information
			String [] urls = m_service_info.getURLs();
			
			for (int i=0 ; i<urls.length ; i++) {
				
				try{
					// Reload device information
					ServiceDefinition service_definition = DeviceFactory.buildServiceInformation(urls[i]);
					Device device = new DeviceJson(service_definition, m_service_info);
					
					// Compare new device information with existing characteristic
					ret_val = !this.getAccessory().compareCharacteristicsValues(device.getAccessory());
					
					if (ret_val) {
						m_service_definition.setAccessory(device.getAccessory()); // update accessory information
						m_logger.debug(this.getAccessory().getLabel() + i18n.tr(" - Characteristic updated."));
					}
				}
				catch (Exception e){
					m_isRegister = false;
					m_logger.warn(i18n.tr("Unable to manage this http service: ") + urls[i]);
				}
				
			}// END for.
		}// END IF register.
		
		return ret_val;
	}
}
