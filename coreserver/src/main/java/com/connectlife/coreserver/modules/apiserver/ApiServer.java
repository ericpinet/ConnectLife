/**
 *  ApiServer.java
 *  coreserver
 *
 *  Created by Eric Pinet <pineri01@gmail.com> on 2015-09-07.
 *  Copyright (c) 2015 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.connectlife.coreserver.modules.apiserver;

// external 
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.thrift.TException;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TSSLTransportFactory;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.transport.TSSLTransportFactory.TSSLTransportParameters;

// internal
import com.connectlife.coreserver.modules.Module;
import com.connectlife.coreserver.modules.configmanager.Config;
import com.connectlife.coreserver.modules.configmanager.ConfigManager;
import com.connectlife.coreserver.modules.environment.EnvironmentManager;
import com.connectlife.coreserver.tools.errormanagement.StdOutErrLog;
import com.connectlife.coreserver.Consts.ModuleUID;
import com.connectlife.clapi.*;
import com.connectlife.clapi.CLApi.Iface;
import com.connectlife.clapi.CLApi.Processor;


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
public class ApiServer implements Module, CLApi.Iface {
	
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
	 * Processor for client connection.
	 */
	private CLApi.Processor<CLApi.Iface> m_processor;
	
	/**
	 * Thread of the simple server.
	 */
	private Runnable m_simple_server;
	
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
	 * This method use the ConfigManager to load configuration of the
	 * Api Server.
	 * 
	 * @return True if initialization completed with success.
	 */
	public boolean init(){
		boolean ret_val = false;
		
		m_logger.info("Initialization in progress ...");
		
		// retrive config
		Config tcpip_port 			= ConfigManager.getConfig("APISERVER", "TCPIP_PORT");
		Config tcpip_port_secure 	= ConfigManager.getConfig("APISERVER", "TCPIP_PORT_SECURE");
		
		if( null != tcpip_port &&
			null != tcpip_port_secure){
		
			// Init http server
            try {
            	
            	// Initialize thrift server (simple and secure)
            	m_processor = new Processor<Iface>(this);
            	
            	// simple
            	Runnable simple = new Runnable() {
            		public void run() {
            			simple(m_processor, tcpip_port.getIntegerValue());
                    }
                };
                new Thread(simple).start();
                
                // secure
                //Runnable secure = new Runnable() {
                //	public void run() {
                //		secure(m_processor, tcpip_port_secure.getIntegerValue());
                //    }
                //};
                //new Thread(secure).start();
	            
	            ret_val = m_isInit = true;
	            
	            m_logger.info("TCP/IP Port: "+tcpip_port.getIntegerValue());
	            
            } catch (Exception e) {
				m_logger.error("Unable to start or join http server.");
				StdOutErrLog.tieSystemOutAndErrToLog();
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
			null != m_simple_server){
			
			try {
				
				// TODO close server correctly
				m_isInit = false;
				
			} catch (Exception e) {
				m_logger.error("Unable to stop correctly the http server. "+e.getMessage());
				StdOutErrLog.tieSystemOutAndErrToLog();
				e.printStackTrace();
			}
		}

		m_logger.info("UnInitialization completed.");
	}

	/**
	 * Return the moduleUID for the ApiServer.
	 */
	public ModuleUID getModuleUID() {
		return m_moduleUID;
	}
	
	/**
	 * Starting simple server (no secure).
	 * 
	 * @param _processor Processor of the server.
	 * @param _port		 TcpIp port to listening.
	 */
	public static void simple(@SuppressWarnings("rawtypes") CLApi.Processor _processor, int _port) {
		try {
			TServerTransport serverTransport = new TServerSocket(_port);
			TServer server = new TThreadPoolServer(new TThreadPoolServer.Args(serverTransport).processor(_processor));
			
			m_logger.info("Starting the simple server...");
			server.serve();
			
		} 
		catch (Exception e) {
			m_logger.error("Unable to start correctly the simple server. "+e.getMessage());
			StdOutErrLog.tieSystemOutAndErrToLog();
			e.printStackTrace();
		}
	}
	
	/**
	 * Starting secure server (SSL).
	 * 
	 * @param _processor Processor of the server.
	 * @param _port		 TcpIp port to listening.
	 */
	public static void secure(@SuppressWarnings("rawtypes") CLApi.Processor _processor, int _port) {
	    try {
			/*
			 * Use TSSLTransportParameters to setup the required SSL parameters. In this example
			 * we are setting the keystore and the keystore password. Other things like algorithms,
			 * cipher suites, client auth etc can be set. 
			 */
			TSSLTransportParameters params = new TSSLTransportParameters();
			// TODO The Keystore contains the private key
			params.setKeyStore("../../lib/java/test/.keystore", "thrift", null, null);
			 
			/*
			 * Use any of the TSSLTransportFactory to get a server transport with the appropriate
			 * SSL configuration. You can use the default settings if properties are set in the command line.
			 * Ex: -Djavax.net.ssl.keyStore=.keystore and -Djavax.net.ssl.keyStorePassword=thrift
			 * 
			 * Note: You need not explicitly call open(). The underlying server socket is bound on return
			 * from the factory class. 
			 */
			TServerTransport serverTransport = TSSLTransportFactory.getServerSocket(_port, 0, null, params);
			TServer server = new TThreadPoolServer(new TThreadPoolServer.Args(serverTransport).processor(_processor));
			
			m_logger.info("Starting the secure server...");
			server.serve();
			
	    } 
	    catch (Exception e) {
	    	m_logger.error("Unable to start correctly the secure server. "+e.getMessage());
			StdOutErrLog.tieSystemOutAndErrToLog();
			e.printStackTrace();
	    }    
	}

	/**
	 * Return the agi version of the server. 
	 * 
	 * @return Version of this agi server.
	 * @throws TException
	 * @see com.connectlife.clapi.clapi.Iface#getVersion()
	 */
	@Override
	public String getVersion() throws TException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Check compatibility with the agi server.
	 * 
	 * @param version Agi version number of the client application.
	 * @return True if this version is supported by this server.
	 * 
	 * @throws TException
	 * @see com.connectlife.clapi.clapi.Iface#checkCompatibility(java.lang.String)
	 */
	@Override
	public boolean checkCompatibility(String version) throws TException {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Return the json string representing the environment data in the application.
	 * 
	 * @return Json representation of the environment data.
	 * @throws TException
	 * @see com.connectlife.clapi.CLApi.Iface#getEnvironmentDataJson()
	 */
	@Override
	public String getEnvironmentDataJson() throws TException {
		return EnvironmentManager.getInstance().getJsonEnvironment();
	}
}
