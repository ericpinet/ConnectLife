/**
 *  DeviceMngr.java
 *  coreserver
 *
 *  Created by ericpinet on 2016-01-23.
 *  Copyright (c) 2016 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.connectlife.coreserver.environment.device;

import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

import javax.jmdns.ServiceEvent;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.connectlife.coreserver.environment.discover.DiscoveryListner;
import com.connectlife.coreserver.environment.discover.DiscoveryService;
import com.connectlife.coreserver.tools.errormanagement.StdOutErrLog;
import com.google.inject.Inject;

/**
 * The device manager discover, control and monitor all services in the network.
 * 
 * @author ericpinet
 * <br> 2016-01-23
 */
public class DeviceMngr extends TimerTask implements DeviceManager, DiscoveryListner {
	
	/**
	 * Logger use for this class.
	 */
	private static Logger m_logger = LogManager.getLogger(DeviceMngr.class);
	
	/**
	 * Flag to indicate if the module is correctly initialized.
	 */
	private boolean m_isInit;
	
	/**
	 * Discovery manager of the accessories in the environment
	 */
	private DiscoveryService m_discovery_manager;
	
	/**
	 * List of all managed services.
	 */
	private List<Device> m_devices;
	
	/**
	 * List of device that we have to unregister with the application environment.
	 */
	private List<Device> m_devices_to_unregister;
	
	/**
	 * Delay before synchronization with the application environment.
	 */
	private final int _DELAY_INTERVAL_ = 0;
	
	/**
	 * Interval between the synchronization with the application environment.
	 */
	private final int _SYNCHRONIZATION_INTERVAL_ = 1000;
	
	/**
	 * Timer for the synchronization task;
	 */
	private Timer m_timer;
	
	/**
	 * Default constructor.
	 * 
	 * @param _service DiscoveryService at use in this Environment. 
	 */
	@Inject
	public DeviceMngr(DiscoveryService _service){
		m_discovery_manager = _service;
		m_devices = new Vector<Device>();
		m_devices_to_unregister = new Vector<Device>();
		m_timer = new Timer();
	}

	/**
	 * Initialize the service manager.
	 * 
	 * @return True if initialization completed with success.
	 * @see com.connectlife.coreserver.environment.device.DeviceManager#init()
	 */
	@Override
	public boolean init() {
		
		boolean ret_val = false;
		
		m_logger.info("Initialization in progress ...");
		
		if(false == m_isInit){

			// init the service discovery
			if(null != m_discovery_manager){
				m_discovery_manager.addListner(this);
				m_discovery_manager.start();
				
				// Start the timer. 
				m_timer.schedule(this, _DELAY_INTERVAL_, _SYNCHRONIZATION_INTERVAL_); 
				
				ret_val = m_isInit = true;
			}
			else{
				m_logger.warn("No discovery manager set in the environment.");
			}
			
			m_logger.info("Initialization completed.");
		}
		else{
			m_logger.warn("Initialization already completed.");
		}
		
		return ret_val;
	}

	/**
	 * Return true if the service manager is initialized.
	 * 
	 * @return True if initialization completed with success.
	 * @see com.connectlife.coreserver.environment.device.DeviceManager#isInit()
	 */
	@Override
	public boolean isInit() {
		return m_isInit;
	}

	/**
	 * Uninitialized the service manager.
	 * 
	 * @see com.connectlife.coreserver.environment.device.DeviceManager#unInit()
	 */
	@Override
	public void unInit() {
		
		m_logger.info("UnInitialization in progress ...");
		
		if(true == m_isInit){
		
			// Cancel the timer
			m_timer.cancel();
			
			// Stop the service discovery 
			if(null != m_discovery_manager){
				m_discovery_manager.stop();
				m_discovery_manager = null;
			}
			
			// remove all services registered in the manager
			m_devices.clear();
			
			m_isInit = false;
			
			m_logger.info("UnInitialization completed.");
		}
		else{
			m_logger.warn("Already unitialized.");
		}
	}

	/**
	 * Callback when a service is discovered.
	 * 
	 * @param _service Service event for the service.
	 * @see com.connectlife.coreserver.environment.discover.DiscoveryListner#serviceDiscover(javax.jmdns.ServiceEvent)
	 */
	@Override
	public void serviceDiscover(ServiceEvent _service) {

		try {
			Device service = DeviceFactory.buildService(_service);
			if(null != service){
				m_devices.remove(service);
				m_devices.add(service);
			}
			
		} catch (Exception e) {
			m_logger.error(e.getMessage());
			StdOutErrLog.tieSystemOutAndErrToLog();
			e.printStackTrace();
		}
	}

	/**
	 * Callback when a service is removed.
	 * 
	 * @param _service Service event for the service.
	 * @see com.connectlife.coreserver.environment.discover.DiscoveryListner#serviceRemove(javax.jmdns.ServiceEvent)
	 */
	@Override
	public void serviceRemove(ServiceEvent _service) {
		m_logger.info("Service removed : " + _service.getName());
		
		// found the service
		Iterator<Device> it = m_devices.iterator();
		boolean notfound = true;
		while(notfound && it.hasNext()){
			Device device = it.next();
			
			if( device.getServiceInfo().equals(_service.getInfo()) ){
				m_devices.remove(device);
				notfound = false;
			}
			
		}
	}

	/**
	 * Synchronization of all device with the application environment.
	 * If device is already synchronized, do noting.
	 * 
	 * @see java.util.TimerTask#run()
	 */
	@Override
	public void run() {
		
		// pass all device unsynchronized and execute a register
		Iterator<Device> it = m_devices.iterator();
		while(it.hasNext()){
			Device device = it.next();
			if( false == device.isSyncronized() ){
				device.register();
			}
		}
		
		// unregister device
		Iterator<Device> it2 = m_devices_to_unregister.iterator();
		while(it2.hasNext()){
			Device device = it2.next();
			if( false == device.isRegister() ){
				device.unregister();
			}
			m_devices_to_unregister.remove(device);
		}
	}
}
