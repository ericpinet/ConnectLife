/**
 *  EnvironmentManager.java
 *  coreserver
 *
 *  Created by ericpinet on 2015-09-09.
 *  Copyright (c) 2015 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.connectlife.coreserver.environment;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.inject.Inject;

import java.util.Observable;

import com.connectlife.coreserver.environment.cmd.Cmd;
import com.connectlife.coreserver.environment.data.DataManager;
import com.connectlife.coreserver.environment.device.DeviceManager;
import com.connectlife.coreserver.tools.errormanagement.StdOutErrLog;

/**
 * Manager of the environment of the automation.
 * 
 * @author ericpinet
 * <br> 2015-09-09
 */
public class EnvironmentManager extends Observable implements Environment, EnvironmentContext {
	
	/**
	 * Logger use for this class.
	 */
	private static Logger m_logger = LogManager.getLogger(EnvironmentManager.class);
	
	/**
	 * Flag to indicate if the module is correctly initialized.
	 */
	private boolean m_isInit;
	
	/**
	 * Device manager of the accessories in the environment
	 */
	private DeviceManager m_device_manager;
	
	/**
	 * Data manager for the environment data.
	 */
	private DataManager m_data_manager;
	
	/**
	 * Default constructor of the environment.
	 * 
	 * @param _datamngr DataManager at use in this Environment.
	 * @param _devicemngr DeviceManager at use in this Environment. 
	 */
	@Inject
	public EnvironmentManager(DataManager _datamngr, DeviceManager _devicemngr){
		m_data_manager = _datamngr;
		m_device_manager = _devicemngr;
		m_isInit = false;
	}
	
	/**
	 * Initialization of the EnvironmentManager.
	 * 
	 * @return True if initialization completed with success.
	 */
	public boolean init(){
		boolean ret_val = false;
		
		m_logger.info("Initialization in progress ...");
	
		// check if environment directory exist and load if exist
		if( true == m_data_manager.checkEnvironmentExist() ) {
			
			// file environment exist, try to load it.
			if( true == m_data_manager.init() ){
				// loading completed with success.
				ret_val = m_isInit = true;
				m_logger.info("Environment loaded.");
			}
			else{
				// loading failed. 
				m_logger.error("Environment load failed!");
			}
		}
		else{
			// create the initial environment
			m_logger.info("No environment file exist. Create the base environment.");

			try {
				m_data_manager.generateBaseEnvironnment();
				
				if( true == m_data_manager.init() ){
					
					// loading completed with success.
					ret_val = m_isInit = true;
					m_logger.info("Environment loaded.");
				}
				else{
					// loading failed. 
					m_logger.error("Environment load failed!");
				}
				
			} catch (Exception e) {
				m_logger.error("Unable to create base environment.");
				m_logger.error(e.getMessage());
				StdOutErrLog.tieSystemOutAndErrToLog();
				e.printStackTrace();
			}
		}
		
		// Init the device manager if all is start correctly.
		if(true == ret_val){
			ret_val = m_device_manager.init();
			
			if(true == ret_val){
				m_logger.info("Initialization completed.");
			}
			else{
				m_logger.error("Unable to init the service manager of this environment. Environment initialization failed.");
			}
		}
		else{
			m_logger.error("Unable to init the environment.");
		}
	

		return ret_val;
	}

	/**
	 * Return True is the EnvironmentManager is correctly initialized.
	 */
	public boolean isInit() {
		return m_isInit;
	}

	/**
	 * UnInitialize the EnvironmentManager. Return in empty state ready to initialize again.
	 */
	public void unInit() {
		
		m_logger.info("UnInitialization in progress ...");
		
		if (null != m_data_manager) {
			m_data_manager.unInit();
		}
		
		if (null != m_device_manager) {
			m_device_manager.unInit();
		}

		m_logger.info("UnInitialization completed.");
	}
	
	/**
	 * Return the device manager for the environment.
	 * 
	 * @return The device manager of the environment.
	 */
	public DeviceManager getDeviceManager(){
		return m_device_manager;
	}
	
	/**
	 * Return the data manager for the environment.
	 * 
	 * @return The data manager of the environment.
	 */
	public DataManager getDataManager(){
		return m_data_manager;
	}
	
	/**
	 * Return a JSON string representing the environment.
	 * 
	 * @return JSON string of the environment.
	 */
	public String getJsonEnvironment(){
		String ret_val = "";
		ret_val = m_data_manager.getJsonEnvironment();
		return ret_val;
	}
	
	/**
	 * Return a JSON formatted string representing the environment.
	 * 
	 * @return JSON string of the environment.
	 */
	public String getJsonFormattedEnvironment(){
		String ret_val = "";
		ret_val = m_data_manager.getJsonFormattedEnvironment();
		return ret_val;
	}
	
	/**
	 * Indicate that the environment was changes. All observers will be notified.
	 */
	private void environmentChange(){
		setChanged();
		notifyObservers();
	}
	
	/**
	 * Execute the command on the environment data.
	 * 
	 * @param _cmd Command to execute. See the CmdFactory to build command.
	 * @throws Exception Exception if something goes wrong.
	 */
	public synchronized void executeCommand(Cmd _cmd) throws Exception{

		_cmd.setContext(this);	// set the context of the execution
		
		try {
			_cmd.validContext(); 	// validate the context
			_cmd.execute();			// execute command on the context
			
			// check if the data was changed by the execution of the context
			if( true == _cmd.isDataChanged() )
				environmentChange();
			
		}catch (Exception exception){
			throw exception;
		}
	}
}
