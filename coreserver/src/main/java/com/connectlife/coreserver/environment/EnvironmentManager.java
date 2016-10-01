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
import org.xnap.commons.i18n.I18n;

import com.google.inject.Inject;

import java.util.Observable;

import com.connectlife.coreserver.Application;
import com.connectlife.coreserver.environment.asset.AssetManager;
import com.connectlife.coreserver.environment.cmd.Cmd;
import com.connectlife.coreserver.environment.data.DataManager;
import com.connectlife.coreserver.environment.device.DeviceManager;
import com.connectlife.coreserver.environment.find.FindProcessor;
import com.connectlife.coreserver.environment.find.FindProcessorReadOnly;
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
	private Logger m_logger = LogManager.getLogger(getClass().getName());
	
	/**
	 * Initialization of translation system.
	 */
	private static I18n i18n = Application.i18n;
	
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
	 * Asset manager for manage file in the environment data.
	 */
	private AssetManager m_asset_manager;
	
	/**
	 * Default constructor of the environment.
	 * 
	 * @param _datamngr DataManager at use in this Environment.
	 * @param _devicemngr DeviceManager at use in this Environment.
	 * @param _assetmngr AssetManager at use in this Environment. 
	 */
	@Inject
	public EnvironmentManager(DataManager _datamngr, DeviceManager _devicemngr, AssetManager _assetmngr){
		m_data_manager = _datamngr;
		m_device_manager = _devicemngr;
		m_asset_manager = _assetmngr;
		m_isInit = false;
	}
	
	/**
	 * Initialization of the EnvironmentManager.
	 * 
	 * @return True if initialization completed with success.
	 */
	public boolean init(){
		boolean ret_val = false;
		
		m_logger.info(i18n.tr("Initialization in progress ..."));
	
		// check if environment directory exist and load if exist
		if( true == m_data_manager.checkEnvironmentExist() ) {
			
			// file environment exist, try to load it.
			if( true == m_data_manager.init() ){
				// loading completed with success.
				ret_val = m_isInit = true;
				m_logger.info(i18n.tr("Environment loaded."));
			}
			else{
				// loading failed. 
				m_logger.error(i18n.tr("Environment load failed!"));
			}
		}
		else{
			// create the initial environment
			m_logger.info(i18n.tr("No environment file exist. Create the base environment."));

			try {
				m_data_manager.generateBaseEnvironnment();
				
				if( true == m_data_manager.init() ){
					
					// loading completed with success.
					ret_val = m_isInit = true;
					m_logger.info(i18n.tr("Environment loaded."));
				}
				else{
					// loading failed. 
					m_logger.error(i18n.tr("Environment load failed!"));
				}
				
			} catch (Exception e) {
				m_logger.error(i18n.tr("Unable to create base environment."));
				m_logger.error(e.getMessage());
				StdOutErrLog.tieSystemOutAndErrToLog();
				e.printStackTrace();
			}
		}
		
		// Init the device manager if all is start correctly.
		if (m_device_manager.init() && m_asset_manager.init()) {
			m_logger.info(i18n.tr("Initialization completed."));
		}
		else{
			m_logger.error(i18n.tr("Unable to init the environment."));
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
		
		m_logger.info(i18n.tr("UnInitialization in progress ..."));
		
		if (null != m_data_manager) {
			m_data_manager.unInit();
		}
		
		if (null != m_device_manager) {
			m_device_manager.unInit();
		}
		
		if (null != m_asset_manager) {
			m_asset_manager.unInit();
		}

		m_logger.info(i18n.tr("UnInitialization completed."));
	}
	
	/**
	 * Return the device manager for the environment.
	 * 
	 * @return The device manager of the environment.
	 */
	public DeviceManager getDeviceManager() {
		return m_device_manager;
	}
	
	/**
	 * Return the data manager for the environment.
	 * 
	 * @return The data manager of the environment.
	 */
	public DataManager getDataManager() {
		return m_data_manager;
	}
	
	public AssetManager getAssetManager() {
		return m_asset_manager;
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
	 * Return a Find Processor read only.
	 * 
	 * @return FindProcessor.
	 * @throws Exception If something goes wrong.
	 * @see com.connectlife.coreserver.environment.Environment#getFindProcessor()
	 */
	public FindProcessor getFindProcessor() throws Exception {
		return new FindProcessorReadOnly(m_data_manager.getGraph());
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
