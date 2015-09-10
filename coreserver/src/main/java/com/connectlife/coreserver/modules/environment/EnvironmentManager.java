/**
 *  EnvironmentManager.java
 *  coreserver
 *
 *  Created by ericpinet on 2015-09-09.
 *  Copyright (c) 2015 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.connectlife.coreserver.modules.environment;

// external
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// internal
import com.connectlife.coreserver.modules.Module;
import com.connectlife.coreserver.Consts.ModuleUID;

/**
 * Manager of the environnement of the automation.
 * 
 * @author ericpinet
 * <br> 2015-09-09
 */
public class EnvironmentManager implements Module {
	
	/**
	 * Logger use for this class.
	 */
	private static Logger m_logger = LogManager.getLogger(EnvironmentManager.class);

	/**
	 * Singleton reference for this class.
	 */
	private static EnvironmentManager m_ref = null;
	
	/**
	 * Flag to indicate if the module is correctly initialized.
	 */
	private boolean m_isInit;
	
	/**
	 * ModuleUID for the ApiServer.
	 */
	private static final ModuleUID m_moduleUID = ModuleUID.ENVIRONMENT_MANAGER;
	

	/**
	 * Default constructor of the environment.
	 */
	private EnvironmentManager(){
		
	}
	
	/**
	 * Return the instance of the ApiServer for this application.
	 * 
	 * @return Singleton instance of the ApiServer.
	 */
	public static EnvironmentManager getInstance(){
		if(null == m_ref){
			m_ref = new EnvironmentManager();
		}
		return m_ref;
	}
	
	/**
	 * Initialization of the EnvironmentManager.
	 * 
	 * @return True if initialization completed with success.
	 */
	public boolean init(){
		boolean ret_val = false;
		
		m_logger.info("Initialization in progress ...");
		
		// TODO - Init environment manager
		
		if( true==ret_val )
			m_logger.info("Initialization completed.");
		else
			m_logger.error("Initialization failed.");
		
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
		
		// TODO UnInit Environment Manager

		m_logger.info("UnInitialization completed.");
	}

	/**
	 * Return the moduleUID for the ApiServer.
	 */
	public ModuleUID getModuleUID() {
		return m_moduleUID;
	}

}
