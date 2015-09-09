/**
 *  ApiServer.java
 *  coreserver
 *
 *  Created by Eric Pinet <pineri01@gmail.com> on 2015-09-07.
 *  Copyright (c) 2015 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.connectlife.coreserver.modules.apiserver;

import java.net.UnknownHostException;

// external 
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.jetty.server.Server;

// internal
import com.connectlife.coreserver.modules.Module;
import com.connectlife.coreserver.modules.datamanager.Config;
import com.connectlife.coreserver.modules.datamanager.DataManager;
import com.connectlife.coreserver.Consts.ModuleUID;

/**
 * Api server of the coreserver. This class is main api server interface
 * for JSON client. This Api is a singleton class that you can instanciated 
 * only once. Use getInstance() methode to get the instance of this api.
 * <p>
 * This api works with HTTP and JSON. 
 * 
 * @author Eric Pinet (pineri01@gmail.com)
 * <br> 2015-09-07
 */
public class ApiServer implements Module {
	
	/**
	 * Logger use for this class.
	 */
	private static Logger m_logger = LogManager.getLogger(ApiServer.class);

	/**
	 * Singleton reference for this class.
	 */
	private static ApiServer m_ref = null;
	
	/**
	 * Flag to indicate if the module is correctly initialized.
	 */
	private boolean m_isInit;
	
	/**
	 * ModuleUID for the ApiServer.
	 */
	private static final ModuleUID m_moduleUID = ModuleUID.API_SERVER;
	
	/**
	 * Http server for the json servlet.
	 */
	private Server m_server;

	/**
	 * Indicate if the server will be close gracefuly
	 */
    private final boolean m_will_stop_gracefuly = false;

	/**
	 * Default constructor of the api server.
	 */
	private ApiServer(){
		
	}
	
	/**
	 * Return the instance of the ApiServer for this application.
	 * 
	 * @return Singleton instance of the ApiServer.
	 */
	public static ApiServer getInstance(){
		if(null == m_ref){
			m_ref = new ApiServer();
		}
		return m_ref;
	}
	
	/**
	 * Initialization of the api server.
	 * This method use the DataManager to load configuration of the
	 * Api Server.
	 * 
	 * @return True if initialization completed with success.
	 */
	public boolean init(){
		boolean ret_val = false;
		
		m_logger.info("Initialization in progress ...");
		
		// retrive config
		Config hostname 	= DataManager.getConfig("APISERVER", "HOSTNAME");
		Config tcpip_port 	= DataManager.getConfig("APISERVER", "TCPIP_PORT");
		Config min_thread 	= DataManager.getConfig("APISERVER", "MIN_THREAD");
		Config max_thread 	= DataManager.getConfig("APISERVER", "MAX_THREAD");
		
		if( null != hostname   || 
			null != tcpip_port ||
			null != min_thread || 
			null != max_thread){
		
			// Init http server
            try {
            	
            	Server server = new Server(8080);
                server.start();
                server.dumpStdErr();
                server.join();
                
            	/*
            	m_server = new Server();
            	 
                SocketListener listener = new SocketListener();
                
				listener.setHost(hostname.getStringValue());
	            listener.setPort(tcpip_port.getIntegerValue());
	            listener.setMinThreads(min_thread.getIntegerValue());
	            listener.setMaxThreads(max_thread.getIntegerValue());
	            m_server.addListener(listener);            

	            m_context = (ServletHttpContext) m_server.getContext("/");
	            m_context.addServlet("/"+ApiServerServlet.class.getSimpleName(), ApiServerServlet.class.getName());
	            
	            m_server.start();
	            m_server.join();
	            */
	            
	            m_isInit = true;
	            
	            m_logger.info("Http server started with this configuration :");
	            m_logger.info("Hostname:    "+hostname.getStringValue());
	            m_logger.info("Tcp/Ip Port: "+tcpip_port.getIntegerValue());
	            m_logger.info("Min thread:  "+min_thread.getIntegerValue());
	            m_logger.info("Max thread:  "+max_thread.getIntegerValue());
	            
            } catch (UnknownHostException e) {
				m_logger.error("Unable to set hostname in http server. ("+hostname.getStringValue()+")");
				e.printStackTrace();
			} catch (Exception e) {
				m_logger.error("Unable to start or join http server.");
				e.printStackTrace();
			}
		}
		else{
			m_logger.error("Unable to initialize. Configuration not found!");
		}
		
		if( true==ret_val )
			m_logger.info("Initialization completed.");
		else
			m_logger.error("Initialization failed.");
		
		return ret_val;
	}

	/**
	 * Return True is the ApiServer is correctly initialized.
	 */
	public boolean isInit() {
		return m_isInit;
	}

	/**
	 * UnInitialize the ApiServer. Return in empty state ready to initialize again.
	 */
	public void unInit() {
		m_logger.info("UnInitialization in progress ...");
		
		if(	m_isInit && 
			null != m_server &&
			true == m_server.isStarted()){
			
			//try {
				// TODO m_server.stop(m_will_stop_gracefuly);
				m_isInit = false;
				
			//} catch (InterruptedException e) {
			//	m_logger.error("Unable to stop correctly the http server.");
			//	e.printStackTrace();
			//}
		}

		m_logger.info("UnInitialization completed.");
	}

	/**
	 * Return the moduleUID for the ApiServer.
	 */
	public ModuleUID getModuleUID() {
		return m_moduleUID;
	}
}
