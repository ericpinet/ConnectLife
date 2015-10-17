/**
 *  ApiThriftJson.java
 *  coreserver
 *
 *  Created by Eric Pinet <pineri01@gmail.com> on 2015-09-07.
 *  Copyright (c) 2015 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.connectlife.coreserver.apiserver;

// external 
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.thrift.TException;
import org.apache.thrift.TProcessor;
import org.apache.thrift.TProcessorFactory;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TSSLTransportFactory;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TSSLTransportFactory.TSSLTransportParameters;

import com.connectlife.coreserver.configmanager.Config;
import com.connectlife.coreserver.configmanager.ConfigItem;
import com.connectlife.coreserver.tools.errormanagement.StdOutErrLog;
import com.google.inject.Inject;
import com.connectlife.clapi.CLApi;
import com.connectlife.clapi.CLApi.Iface;
import com.connectlife.clapi.CLApi.Processor;
import com.connectlife.clapi.Notification;


/**
 * Api server of the coreserver. This class is main api server interface
 * for JSON client. 
 * 
 * This api works with HTTP and JSON. 
 * 
 * @author Eric Pinet (pineri01@gmail.com)
 * <br> 2015-09-07
 */
public class ApiThriftJson implements Api {
	
	/**
	 * Logger use for this class.
	 */
	private final Logger m_logger = LogManager.getLogger(ApiThriftJson.class);
	
	/**
	 * Config use for this class.
	 */
	private final Config m_config;
	
	/**
	 * Flag to indicate if the module is correctly initialized.
	 */
	private boolean m_isInit;
	
	/**
	 * Processor for client connection.
	 */
	private final CLApi.Processor<CLApi.Iface> m_processor;
	
	/**
	 * Thread of the simple server.
	 */
	private Runnable m_simple_server;
	
	/**
	 * Distributor of push notification to clients simple.
	 */
	private PushDistributor m_push_distributor_simple;
	
	/**
	 * Default constructor of the api server.
	 */
	@Inject
	public ApiThriftJson(CLApi.Iface _processor, Config _config){
		m_push_distributor_simple = new PushDistributor();
		m_processor = new Processor<Iface>(_processor);
		m_config = _config;
	}
	
	/**
	 * Initialization of the api server.
	 * This method use the ConfigSqliteManager to load configuration of the
	 * Api Server.
	 * 
	 * @return True if initialization completed with success.
	 */
	public boolean init(){
		boolean ret_val = false;
		
		m_logger.info("Initialization in progress ...");
		
		// retrive config
		ConfigItem tcpip_port 			= m_config.getConfig("APISERVER", "TCPIP_PORT");
		ConfigItem tcpip_port_secure 	= m_config.getConfig("APISERVER", "TCPIP_PORT_SECURE");
		
		if( null != tcpip_port &&
			null != tcpip_port_secure){
		
			// Init http server
            try {
            	
            	// Initialize thrift server (simple and secure)
            	
            	// simple
            	Runnable simple = new Runnable() {
            		public void run() {
            			simple(m_processor, m_push_distributor_simple, tcpip_port.getIntegerValue());
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
	 * Return True is the ApiThriftJson is correctly initialized.
	 */
	public boolean isInit() {
		return m_isInit;
	}

	/**
	 * UnInitialize the ApiThriftJson. Return in empty state ready to initialize again.
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
	 * Starting simple server (no secure).
	 * 
	 * @param _processor 		Processor of the server.
	 * @param _push_distributor Distributor that keeping list of all client connected for simple connection (no secure).
	 * @param _port		 		TcpIp m_port to listening.
	 */
	private void simple(@SuppressWarnings("rawtypes") CLApi.Processor _processor, PushDistributor _push_distributor, int _port) {
		
		try {
			// Processor factory use to bind second socket on client to
			// push notification.
			TProcessorFactory processor_factory = new TProcessorFactory(null) {
				@Override
			    public TProcessor getProcessor(TTransport trans) {
					_push_distributor.addClient(new NotificationServiceClient(trans));
					return _processor;
				}
			};
			
			TServerTransport serverTransport = new TServerSocket(_port);
			TThreadPoolServer.Args serverArgs = new TThreadPoolServer.Args(serverTransport);
		    serverArgs.processorFactory(processor_factory);
		    TServer server = new TThreadPoolServer(serverArgs);
			
		    // start the push distributor
		    new Thread(_push_distributor).start();
		    
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
	 * @param _port		 TcpIp m_port to listening.
	 */
	private void secure(@SuppressWarnings("rawtypes") CLApi.Processor _processor, int _port) {
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
			 * Use any of the TSSLTransportFactory to get a server m_transport with the appropriate
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
	 * Send notification to all client connected.
	 * 
	 * @param _notification Notification to send at client.
	 */
	public void sendNotificationAllClient(Notification _notification) {
		try {
			if(m_push_distributor_simple != null){
				m_push_distributor_simple.push(_notification);
			}
		} catch (TException e) {
			m_logger.error(e.getMessage());
			StdOutErrLog.tieSystemOutAndErrToLog();
			e.printStackTrace();
		}
	}
}
