/**
 *  ModuleFactory.java
 *  coreserver
 *
 *  Created by Eric Pinet <pineri01@gmail.com> on 2015-09-07.
 *  Copyright (c) 2015 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.connectlife.coreserver.modules;


// external
import java.util.Hashtable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// internal
import com.connectlife.coreserver.Consts.ModuleUID;
import com.connectlife.coreserver.modules.Module;
import com.connectlife.coreserver.modules.apiserver.ApiServer;
import com.connectlife.coreserver.modules.datamanager.DataManager;
import com.connectlife.coreserver.modules.environment.EnvironmentManager;

/**
 * Factory of all modules include in coreserver application.
 * 
 * @author Eric Pinet (pineri01@gmail.com)
 * <br> 2015-09-07
 */
public class ModuleFactory {
	
	/**
	 * Logger use for this class.
	 */
	private static Logger m_logger = LogManager.getLogger(ModuleFactory.class);

	/**
	 * HashMap of modules load in factory.
	 */
	private static Hashtable <ModuleUID,Module> m_modules = null;
	
	/**
	 * Flag to indicate if modules are prepared and in hashmap of the factory.
	 */
	private static boolean m_prepareModuleCompleted = false;
	
	/**
	 * Default constructor is private.
	 */
	private ModuleFactory(){
	}
	
	/**
	 * Return the module corresponding at the ModuleUID.
	 * 
	 * @param _module
	 */
	public static Module getModule(ModuleUID _module){
		Module ret_module = null;
		
		if( false  == m_prepareModuleCompleted ){
			prepareModules();
		}
		
		ret_module = m_modules.get(_module);
		return ret_module;
	}
	
	/**
	 * Return all modules of the application.
	 */
	public static Hashtable <ModuleUID,Module> getModules(){
		
		if( false  == m_prepareModuleCompleted ){
			prepareModules();
		}
		
		return m_modules;
	}
	
	/**
	 * Prepare modules
	 */
	private static boolean prepareModules(){
		boolean ret_val = false;
		
		if(null == m_modules){
			
			m_logger.info("Prepare module in progress ...");
			
			m_modules = new Hashtable<ModuleUID, Module>();
			m_modules.put(ModuleUID.DATA_MANAGER, DataManager.getInstance());
			m_modules.put(ModuleUID.API_SERVER, ApiServer.getInstance());
			m_modules.put(ModuleUID.ENVIRONMENT_MANAGER, EnvironmentManager.getInstance());
			
			ret_val = true;
			m_prepareModuleCompleted = true;
			
			m_logger.info("Prepare module completed.");
		}
		
		return ret_val;
	}
}
