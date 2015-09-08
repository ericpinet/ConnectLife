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
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import com.sun.net.httpserver.*;

// internal
import com.connectlife.coreserver.modules.Module;
import com.connectlife.coreserver.modules.datamanager.DataManager;
import com.connectlife.coreserver.Consts;
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
	 * Singleton reference for this class
	 */
	private static ApiServer m_ref = null;
	
	/**
	 * Flag to indicate if the module is correctly initialized.
	 */
	private boolean m_isInit;
	
	/**
	 * ModuleUID for the ApiServer
	 */
	private static final ModuleUID m_moduleUID = ModuleUID.API_SERVER;
	
	private HttpServer m_server = null;
	
	private String m_hostname = Consts.API_SERVER_HOSTNAME_CONFIG_DEFAULT_VALUE;
    private int m_port = Integer.parseInt(Consts.API_SERVER_TCPIP_PORT_CONFIG_DEFAULT_VALUE);
    private int m_backlog = Integer.parseInt(Consts.API_SERVER_BACKLOG_CONFIG_DEFAULT_VALUE);

    private String HEADER_ALLOW = "Allow";
    private String HEADER_CONTENT_TYPE = "Content-Type";

    private Charset CHARSET = StandardCharsets.UTF_8;

    private int STATUS_OK = 200;
    private int STATUS_METHOD_NOT_ALLOWED = 405;

    private int NO_RESPONSE_LENGTH = -1;

    private String METHOD_GET = "GET";
    private String METHOD_OPTIONS = "OPTIONS";
    private String ALLOWED_METHODS = METHOD_GET + "," + METHOD_OPTIONS;
	
	
	/**
	 * Default constructor of the api server.
	 */
	private ApiServer(){
		
	}
	
	/**
	 * Return the instance of the ApiServer for this application
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
		
		// TODO - Server HTTP.
		
		/*
		// get config needed for the api server
		m_hostname = DataManager.getInstance().getConfig(Consts.API_SERVER_HOSTNAME_CONFIG_NAME).getValue();
		
		m_server = HttpServer.create(new InetSocketAddress(m_hostname, m_port), m_backlog);
        server.createContext("/func1", he -> {
            try {
                final Headers headers = he.getResponseHeaders();
                final String requestMethod = he.getRequestMethod().toUpperCase();
                switch (requestMethod) {
                    case METHOD_GET:
                        final Map<String, List<String>> requestParameters = getRequestParameters(he.getRequestURI());
                        // do something with the request parameters
                        final String responseBody = "['hello world!']";
                        headers.set(HEADER_CONTENT_TYPE, String.format("application/json; charset=%s", CHARSET));
                        final byte[] rawResponseBody = responseBody.getBytes(CHARSET);
                        he.sendResponseHeaders(STATUS_OK, rawResponseBody.length);
                        he.getResponseBody().write(rawResponseBody);
                        break;
                    case METHOD_OPTIONS:
                        headers.set(HEADER_ALLOW, ALLOWED_METHODS);
                        he.sendResponseHeaders(STATUS_OK, NO_RESPONSE_LENGTH);
                        break;
                    default:
                        headers.set(HEADER_ALLOW, ALLOWED_METHODS);
                        he.sendResponseHeaders(STATUS_METHOD_NOT_ALLOWED, NO_RESPONSE_LENGTH);
                        break;
                }
            } finally {
                he.close();
            }
        });
        server.start();
        */
		
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
		
		// TODO - ApiServer unInit

		m_logger.info("UnInitialization completed.");
	}

	/**
	 * Return the moduleUID for the ApiServer.
	 */
	public ModuleUID getModuleUID() {
		return m_moduleUID;
	}
}
