/**
 *  Application.java
 *  coreserver
 *
 *  Created by Eric Pinet <pineri01@gmail.com> on 2015-09-07.
 *  Copyright (c) 2015 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.connectlife.coreserver;

// external
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Iterator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.connectlife.clapi.Notification;
import com.connectlife.clapi.Type;
// internal
import com.connectlife.coreserver.Consts;
import com.connectlife.coreserver.Consts.ModuleUID;
import com.connectlife.coreserver.modules.Module;
import com.connectlife.coreserver.modules.ModuleFactory;
import com.connectlife.coreserver.modules.apiserver.ApiServer;
import com.connectlife.coreserver.tools.errormanagement.StdOutErrLog;

/**
 * Main application class. All start here!
 * <p>
 * The main application start all stuff needed in the application. 
 * 
 * @author  Eric Pinet (pineri01@gmail.com)
 * <br> 2015-09-07
 */
public class Application {
	
	/**
	 * Init logger instance for this class
	 */
	private static Logger m_logger = LogManager.getLogger(Application.class);
	
	/**
	 * Reference for singleton
	 */
	private static Application m_ref = null;
	
	/**
	 * Base path of the application.
	 */
	private String m_base_path;
	
	/**
	 * Indicator if the application must be running.
	 */
	private boolean m_is_running;

	/**
	 * Main methode of the application.
	 * @param args Arguments past to the application.
	 */
	public static void main(String[] args) {
		
		m_logger.info(Consts.APP_NAME +" "+ Consts.APP_VERSION +" is starting ...");
		
		Application app = Application.getInstance();
		try{
			
			if( true == app.init() ){
			
				app.setRunning(true);
				app.run();
			}
		}
		catch(Exception e){
			StdOutErrLog.tieSystemOutAndErrToLog();
			e.printStackTrace();
		}
		finally{
			app.unInitModules();
		}
		
		m_logger.info(Consts.APP_NAME +" "+ Consts.APP_VERSION +" closed.");
		
		System.exit(0);
	}
	
	/**
	 * Main constructor of the application
	 */
	public Application(){
		
	}
	
	/**
	 * Return the instance of this application.
	 * 
	 * @return Singleton instance of the ApiServer.
	 */
	public static Application getInstance(){
		if(null == m_ref){
			m_ref = new Application();
		}
		return m_ref;
	}
	
	/**
	 * Init all application stuff. 
	 * @return True is initialization is completed correctly
	 */
	public boolean init(){
		
		boolean ret_val = false;
		
		m_logger.info("Initialization started ...");
		
		// Startup application logging system
		if( true == initBasePath() &&
			true == initModules() ){
			ret_val = true;
			m_logger.info("Initialization completed.");
		}
		
		
		
		return ret_val;
	}
	
	/**
	 * Init the base path of the application
	 * @return True is the base path can be init correctly
	 */
	private boolean initBasePath(){
		
		boolean ret_val = false;
		
		try {
			
			m_base_path = new File(".").getCanonicalPath();
			m_logger.info("Base path: '"+ m_base_path +"'.");
			ret_val = true;
			
		} catch (IOException e) {
			m_logger.error("Load base path failed! "+e.getMessage());
			StdOutErrLog.tieSystemOutAndErrToLog();
			e.printStackTrace();
		}
		
		return ret_val;
	}
	
	/**
	 * Return the base path of the application.
	 * @return The application base path.
	 */
	public String getBasePath(){
		return m_base_path;
	}
	
	/**
	 * @return the m_is_running
	 */
	public boolean isRunning() {
		return m_is_running;
	}

	/**
	 * @param _is_running the m_is_running to set
	 */
	public void setRunning(boolean _is_running) {
		this.m_is_running = _is_running;
	}

	/**
	 * Initialization of all applications modules.
	 * 
	 * @return True if all applications modules are initialized correctly.
	 */
	private boolean initModules(){
		
		boolean ret_val = true;
		Hashtable<ModuleUID,Module> modules = ModuleFactory.getModules();
		Iterator<Module> itr = modules.values().iterator();
		
		// Initialization of ConfigManager first of all
		Module data = modules.get(Consts.ModuleUID.DATA_MANAGER);
		if( null != data &&
		   	false == data.isInit() ){
			
			data.init();
			
			while(itr.hasNext()){
				Module module = itr.next();
				if( false == module.isInit() && 
					false == module.init())
					ret_val = false;
			}
		}
		else{
			m_logger.error("Unable to find ConfigManager.");
		}
		
		return ret_val;
	}
	
	/**
	 * UnInitialization of all applications modules.
	 */
	private void unInitModules(){
		
		Hashtable<ModuleUID,Module> modules = ModuleFactory.getModules();
		Iterator<Module> itr = modules.values().iterator();
			
		while(itr.hasNext()){
			Module module = itr.next();
			if( true == module.isInit() &&
				Consts.ModuleUID.DATA_MANAGER != module.getModuleUID() ){
					module.unInit();
			}
		}
		
		// UnInitialization of ConfigManager at the end
		Module data = modules.get(Consts.ModuleUID.DATA_MANAGER);
		if( null != data &&
		   	false == data.isInit() ){
			data.unInit();
		}
		
	}
	
	/**
	 * Start the application main loop
	 */
	public void run(){
		
		// start the state machine
		ApplicationStateMachine state_machine = new ApplicationStateMachine();
		state_machine.start();
		
		// application run
		while(m_is_running){
			try {
				Thread.sleep(2000);
				
				
				// TODO: TEST NOTIFICATION
				ApiServer apiserver = (ApiServer) ModuleFactory.getModule(Consts.ModuleUID.API_SERVER);
				if( null != apiserver ){
					apiserver.sendNotificationAllClient(new Notification(Type.ENV_UPDATED, "Environnement updated."));
				}
				
				
			} catch (InterruptedException e) {
				// no error on interup.
			}
		}
	}

}
