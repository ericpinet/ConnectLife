/**
 *  Application.java
 *  coreserver
 *
 *  Created by Eric Pinet <pineri01@gmail.com> on 2015-09-07.
 *  Copyright (c) 2015-2016 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.connectlife.coreserver;

import java.io.File;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xnap.commons.i18n.I18n;
import org.xnap.commons.i18n.I18nFactory;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Singleton;

import java.util.Observable;
import java.util.Observer;

import com.connectlife.coreserver.Consts;
import com.connectlife.coreserver.apiserver.Api;
import com.connectlife.coreserver.config.Config;
import com.connectlife.coreserver.console.Console;
import com.connectlife.coreserver.environment.Environment;
import com.connectlife.coreserver.gpio.Gpio;
import com.connectlife.coreserver.tools.errormanagement.StdOutErrLog;
import com.connectlife.coreserver.tools.execution.ExecutionMode;
import com.connectlife.coreserver.tools.os.OperatingSystem;
import com.connectlife.coreserver.webserver.WebServer;


/**
 * Main application class. All start here!
 * <p>
 * The main application start all stuff needed in the application. 
 * 
 * @author  Eric Pinet (pineri01@gmail.com)
 * <br> 2015-09-07
 */
@Singleton
public class Application implements Observer {
	
	/**
	 * Init logger instance for this class
	 */
	private static Logger m_logger = LogManager.getLogger(Application.class);
	
	/**
	 * Initialization of translation system.
	 */
	public static I18n i18n = I18nFactory.getI18n(Application.class, "i18n.Messages", java.util.Locale.ENGLISH);
	
	/**
	 * Config manager for the application.
	 */
	private final Config m_config;
	
	/**
	 * Environment data for the application.
	 */
	private final Environment m_environment;
	
	/**
	 * Api manager for the application.
	 */
	private final Api m_api;
	
	/**
	 * Console manager for the application.
	 */
	private final Console m_console;
	
	/**
	 * Web server for the application.
	 */
	private final WebServer m_web_server;
	
	/**
	 * GPIO manager for the application.
	 */
	private final Gpio m_gpio;
	
	/**
	 * Base path of the application.
	 */
	private String m_base_path;
	
	/**
	 * Indicator if the application must be running.
	 */
	private boolean m_is_running;
	
	/**
	 * Singleton reference of this class.
	 */
	private static Application m_ref;
	
	/**
	 * Flag to indicate if application is already initialized.
	 */
	private boolean m_isInit;

	/**
	 * Main methode of the application.
	 * 
	 * @param args Arguments past to the application.
	 */
	public static void main(String[] args) {
		
		Injector injector = Guice.createInjector(new ApplicationInject());
		Application app = injector.getInstance(Application.class);
		
		m_ref = app;
		
		try {
			app.startup();
			
		} catch (Exception e) {
			m_logger.error(e.getMessage());
			StdOutErrLog.tieSystemOutAndErrToLog();
			e.printStackTrace();
		}
		
		System.exit(0);
	}
	
	/**
	 * Default constructor
	 * 
	 * @param _config Config manager for the application.
	 * @param _env Environment manager for the application.
	 * @param _api Api for the application.
	 * @param _console Console for the application.
	 * @param _gpio GPIO manager for the application.
	 * @param _web_server Web server for the application.
	 */
	@Inject
	public Application(Config _config, Environment _env, Api _api, Console _console, Gpio _gpio, WebServer _web_server){
		m_logger.debug(i18n.tr("Application statup."));
		m_config = _config;
		m_environment = _env;
		m_api = _api;
		m_console = _console;
		m_gpio = _gpio;
		m_web_server = _web_server;
		m_ref = this;
	}
	
	/**
	 * Return the application instance.
	 * 
	 * @return Application current running.
	 */
	public static Application getApp() {
		return m_ref;
	}

	
	/**
	 * Init all application stuff. 
	 * 
	 * @return True is initialization is completed correctly
	 */
	private boolean init(){
		
		boolean ret_val = false;
		
		m_logger.info(i18n.tr("Initialization started ..."));
		
		if(false == m_isInit){
		
			// Startup application module
			if( true == initBasePath() &&
				true == initModules() ){
				m_isInit = ret_val = true;
				m_logger.info(i18n.tr("Initialization completed."));
			}
			else{
				m_logger.error(i18n.tr("Initialization failed."));
			}
		}
		else{
			m_logger.warn(i18n.tr("Application is already initialized."));
		}
		
		return ret_val;
	}
	
	/**
	 * Init the base path of the application
	 * 
	 * @return True is the base path can be init correctly
	 */
	private boolean initBasePath(){
		
		boolean ret_val = false;
		
		m_logger.info(i18n.tr("Application retrieving the base path execution context ..."));
		
		try {
			m_base_path = new File(".").getCanonicalPath();
			m_logger.info(i18n.tr("Base path:") + "'" + m_base_path +"'.");
			ret_val = true;
			
		} catch (IOException e) {
			m_logger.error(i18n.tr("Load base path failed! ")+e.getMessage());
			StdOutErrLog.tieSystemOutAndErrToLog();
			e.printStackTrace();
		}
		
		return ret_val;
	}
	
	/**
	 * Return the base path of the application.
	 * 
	 * @return The application base path.
	 */
	public String getBasePath(){
		return m_base_path;
	}
	
	/**
	 * Return true if the application must is running. 
	 * 
	 * @return the m_is_running
	 */
	public boolean isRunning() {
		return m_is_running;
	}

	/**
	 * Set if the application must running.
	 * 
	 * @param _is_running the m_is_running to set
	 */
	private void setRunning(boolean _is_running) {
		this.m_is_running = _is_running;
	}

	/**
	 * Initialization of all applications modules.
	 * 
	 * @return True if all applications modules are initialized correctly.
	 */
	private boolean initModules(){
		
		boolean ret_val = false;
		
		// Check if all module are not null
		if (m_config != null &&
		   m_environment != null &&
		   m_api != null &&
		   m_console != null &&
		   m_web_server != null
		   ) {
			
			// init config first
			if (m_config.init() == true) {
				
				// init environment second
				if (m_environment.init() == true) {
					
					m_environment.addObserver(this);
					
					// init others modules
					if (m_api.init() == true &&
						m_console.init() == true && 
						m_gpio.init() == true &&
						m_web_server.init() == true) {
						
						// in debug mode (give more rights on all files to permit remote debugging)
						if (ExecutionMode.isDebug() && OperatingSystem.isLinux()) {
							Process p;
					        try {
					            p = Runtime.getRuntime().exec("chmod 777 -R "+getBasePath());
					            p.waitFor();

					        } catch (Exception e) {
					            e.printStackTrace();
					        }
						}
						
						ret_val = true;
					}
				}
			}
		}
		else{
			m_logger.error(i18n.tr("Unable to initialize modules. At less one module is null."));
		}
		
		return ret_val;
	}
	
	/**
	 * UnInitialization of all applications modules.
	 */
	private void unInitModules(){
		
		if(m_config != null &&
		   m_environment != null &&
		   m_api != null &&
		   m_console != null &&
		   m_gpio != null &&
		   m_web_server != null
		   ){
			
			m_console.unInit();
			m_api.unInit();
			m_environment.unInit();
			m_config.unInit();
			m_gpio.unInit();
			m_web_server.unInit();
			
		}
		else{
			m_logger.error(i18n.tr("Unable to uninitialize modules. At less one module is null."));
		}
		
	}
	
	/**
	 * Start the application main loop
	 */
	public void run(){
		
		setRunning(true);
		
		// start the state machine
		ApplicationStateMachine state_machine = new ApplicationStateMachine();
		state_machine.start();
		
		// application run
		while(m_is_running){
			try {
				Thread.sleep(2000);
				
			} catch (InterruptedException e) {
				m_logger.info(i18n.tr("Main application thread was stopted."));
			}
		}
	}

	/**
	 * Startup the application process.
	 * This function block until a shutdown as called.
	 * 
	 * @throws Exception If something goes wrong.
	 */
	public void startup() throws Exception {
		
		m_logger.info(Consts.APP_NAME +" "+ Consts.APP_VERSION + i18n.tr(" is starting ..."));
		
		try{
			if( true == init() ){
				run();
			}
		}
		catch(Exception e){
			StdOutErrLog.tieSystemOutAndErrToLog();
			e.printStackTrace();
		}
		finally{
			unInitModules();
		}
		
		m_logger.info(Consts.APP_NAME +" "+ Consts.APP_VERSION + i18n.tr(" closed."));
	}
	
	/**
	 * Startup application process for test.
	 * Don't run, just init the application.
	 */
	public void startupTest() {
		
		m_logger.info(Consts.APP_NAME +" "+ Consts.APP_VERSION + i18n.tr(" is starting for test ..."));
		
		try{
			if( true == init() ){
				// do nothing for test process.
			}
		}
		catch(Exception e){
			StdOutErrLog.tieSystemOutAndErrToLog();
			e.printStackTrace();
		}
		finally{
			unInitModules();
		}
		
		m_logger.info(Consts.APP_NAME +" "+ Consts.APP_VERSION + i18n.tr(" closed."));
	}

	/**
	 * Shutdown the application.
	 */
	public void shutdown() {
		setRunning(false);
	}
	
	/**
	 * Return the configuration manager for this application.
	 * 
	 * @return Return configuration manager.
	 */
	public Config getConfig() {
		return m_config;
	}
	
	/**
	 * Return the environment manager for this application.
	 * 
	 * @return Return environement manager.
	 */
	public Environment getEnvironment() {
		return m_environment;
	}
	
	/**
	 * Return the api manager for this application.
	 * 
	 * @return Return api manager.
	 */
	public Api getApi() {
		return m_api;
	}
	
	/**
	 * Return the console manager for this application.
	 * 
	 * @return Return console manager.
	 */
	public Console getConsole() {
		return m_console;
	}
	
	/**
	 * Return the web server for this application.
	 * 
	 * @return Return web server.
	 */
	public WebServer getWebServer() {
		return m_web_server;
	}

	/**
	 * Callback when environment was updated.
	 * 
	 * @param o Reference of the object source
	 * @param arg Argument of the environment.
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable o, Object arg) {
		if(m_environment == o){
			m_logger.info(i18n.tr("Environment was updated."));
		}
	}
}
