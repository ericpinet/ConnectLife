/**
 *  ApiGrpc.java
 *  coreserver
 *
 *  Created by ericpinet on 2015-11-05.
 *  Copyright (c) 2015 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.connectlife.coreserver.apiserver;

import com.connectlife.coreserver.config.Config;
import com.connectlife.coreserver.config.ConfigItem;
import com.connectlife.coreserver.tools.errormanagement.StdOutErrLog;
import com.google.inject.Inject;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xnap.commons.i18n.I18n;
import org.xnap.commons.i18n.I18nFactory;

import com.clapi.protocol.*;


/**
 * This is the main application API server for the client application (Mobile device).
 * 
 * @author ericpinet
 * <br> 2015-11-05
 */
public class ApiGrpc implements Api {
	
	/**
	 * Logger use for this class.
	 */
	private final Logger m_logger = LogManager.getLogger(ApiGrpc.class);
	
	/**
	 * Initialization of translation system.
	 */
	private static I18n i18n = I18nFactory.getI18n(ApiGrpc.class);
	
	/**
	 * Config use for this class.
	 */
	private final Config m_config;
	
	/**
	 * Flag to indicate if the module is correctly initialized.
	 */
	private boolean m_isInit;
	
	/**
	 * Grpc server
	 */
	private Server m_server;
	
	/**
	 * Processor for the transactions.
	 */
	private final CLApiGrpc.CLApi m_processor;
	
	/**
	 * Default constructor of the ApiGrpc.
	 * @param _config Config manager at use in this ApiGrpc.
	 * @param _processor CLApi at use in this ApiGrpc.
	 */
	@Inject
	public ApiGrpc(Config _config, CLApiGrpc.CLApi _processor){
		m_config = _config;
		m_processor = _processor;
	}

	/**
	 * Initialization of the ApiGrpc
	 * @return True if the ApiGrpc are correctly initialized.
	 * @see com.connectlife.coreserver.apiserver.Api#init()
	 */
	@Override
	public boolean init() {
		boolean ret_val = false;
		
		m_logger.info(i18n.tr("Initialization in progress ..."));
		
		// retrive config
		ConfigItem tcpip_port 			= m_config.getConfig("APISERVER", "TCPIP_PORT");
		ConfigItem tcpip_port_secure 	= m_config.getConfig("APISERVER", "TCPIP_PORT_SECURE");
		
		if( null != tcpip_port &&
			null != tcpip_port_secure){
		
			// Init http server
            try {
            	
            	// Initialize grpc server 
            	m_server = ServerBuilder.forPort(tcpip_port.getIntegerValue())
            	        .addService(CLApiGrpc.bindService(m_processor))
            	        .build()
            	        .start();
            	    m_logger.info(i18n.tr("Server started, listening on ") + tcpip_port.getIntegerValue());
            	    Runtime.getRuntime().addShutdownHook(new Thread() {
            	      @Override
            	      public void run() {
            	        unInit();
            	      }
            	    });
	            
	            ret_val = m_isInit = true;
	            
            } catch (Exception e) {
				m_logger.error(i18n.tr("Unable to start or join http server."));
				StdOutErrLog.tieSystemOutAndErrToLog();
				e.printStackTrace();
			}
		}
		else{
			m_logger.error(i18n.tr("Unable to initialize. Configuration not found!"));
		}
		
		if( true==ret_val )
			m_logger.info(i18n.tr("Initialization completed."));
		else
			m_logger.error(i18n.tr("Initialization failed."));
		
		return ret_val;
	}

	/**
	 * @return True if initialized.
	 * @see com.connectlife.coreserver.apiserver.Api#isInit()
	 */
	@Override
	public boolean isInit() {
		return m_isInit;
	}

	/**
	 * 
	 * @see com.connectlife.coreserver.apiserver.Api#unInit()
	 */
	@Override
	public void unInit() {
		if(m_server!=null){
			m_server.shutdown();
			m_isInit = false;
		}
	}
}
