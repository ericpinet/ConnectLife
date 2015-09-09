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
import java.util.Collection;
import java.util.Iterator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// internal
import com.connectlife.coreserver.Consts;
import com.connectlife.coreserver.modules.Module;
import com.connectlife.coreserver.modules.ModuleFactory;

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
	 * @param args
	 */
	public static void main(String[] args) {
		
		m_logger.info(Consts.APP_NAME +" "+ Consts.APP_VERSION +" is starting ...");
		
		Application app = new Application();
		try{
			
			if( true == app.init() ){
			
				app.run();
			}
		}
		catch(Exception e){
			e.printStackTrace();
			m_logger.error(e.toString());
		}
		finally{
			app.unInitModules();
		}
		
		m_logger.info(Consts.APP_NAME +" "+ Consts.APP_VERSION +" closed.");
	}
	
	/**
	 * Main constructor of the application
	 */
	public Application(){
		
	}
	
	/**
	 * Init all application stuff. 
	 * @return True is initialization is completed correctly
	 */
	public boolean init(){
		
		boolean ret_val = false;
		
		// Startup application logging system
		if( true == initBasePath() &&
			true == initModules() ){
			ret_val = true;
		}
		
		return ret_val;
	}
	
	/**
	 * Init the base path of the application
	 * @return True is the base path can be init correctly
	 */
	private boolean initBasePath(){
		boolean ret_val = false;
		String path = "";
		try {
			
			path = new File(".").getCanonicalPath();
			m_logger.info("Base path: '"+ path +"'.");
			ret_val = true;
			
		} catch (IOException e) {
			m_logger.error("Load base path failed! "+e.getMessage());
			e.printStackTrace();
		}
		
		return ret_val;
	}
	
	/**
	 * Initialization of all applications modules.
	 * 
	 * @return True if all applications modules are initialized correctly.
	 */
	private boolean initModules(){
		boolean ret_val = true;
		
		Collection<Module> modules = ModuleFactory.getModules();
		Iterator<Module> itr = modules.iterator();
		
		while(itr.hasNext()){
			Module module = itr.next();
			if(false == module.init())
				ret_val = false;
		}
		
		return ret_val;
	}
	
	/**
	 * UnInitialization of all applications modules.
	 */
	private void unInitModules(){
		
		Collection<Module> modules = ModuleFactory.getModules();
		Iterator<Module> itr = modules.iterator();
		
		while(itr.hasNext()){
			Module module = itr.next();
			if(true == module.isInit())
				module.unInit();
		}

	}
	
	/**
	 * Start the application main loop
	 */
	public void run(){
		// application run
	}

}
