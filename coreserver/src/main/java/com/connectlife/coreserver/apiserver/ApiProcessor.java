/**
 *  ApiProcessor.java
 *  coreserver
 *
 *  Created by ericpinet on 2015-10-16.
 *  Copyright (c) 2015 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.connectlife.coreserver.apiserver;

// external
import org.apache.thrift.TException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// internal
import com.connectlife.clapi.CLApi;
import com.connectlife.clapi.Xception;
import com.connectlife.coreserver.environment.Environment;
import com.connectlife.coreserver.tools.errormanagement.StdOutErrLog;
import com.google.inject.Inject;

/**
 * Processor of the api. 
 * 
 * @author ericpinet
 * <br> 2015-10-16
 */
public class ApiProcessor implements CLApi.Iface {
	
	/**
	 * Logger use for this class.
	 */
	private final Logger m_logger = LogManager.getLogger(ApiThriftJson.class);
	
	/**
	 * Api server version.
	 * 
	 * To check compatibility the difference with the first and third digit must be the same 
	 * whit the client version.
	 * 
	 * Exemple : Server version 0,1,2 : 0 - 2 = -2
	 *           Client version 1,1,3 : 1 - 3 = -2 
	 *           
	 *           Client and Server are compatible. 
	 */
	private static final int API_SERVER_VERSION[] = { 1, 1, 0 };
	
	/**
	 * Environment manager use to respond at the client.
	 */
	private final Environment m_environment;
	
	/**
	 * Defautl constructor.
	 * @param _env Environment manager use to process client request.
	 */
	@Inject
	public ApiProcessor(Environment _env){
		m_environment = _env;
	}

	/**
	 * @return The server version number.
	 * @throws Xception
	 * @throws TException
	 * @see com.connectlife.clapi.CLApi.Iface#getVersion()
	 */
	@Override
	public String getVersion() throws Xception, TException {
		return new String( API_SERVER_VERSION[0] + "." + API_SERVER_VERSION[1] + "." + API_SERVER_VERSION[2] );
	}

	/**
	 * @param version String representing the client version.
	 * @return
	 * @throws Xception
	 * @throws TException
	 * @see com.connectlife.clapi.CLApi.Iface#checkCompatibility(java.lang.String)
	 */
	@Override
	public boolean checkCompatibility(String version) throws Xception, TException {
		boolean ret_val = false;
		
		try{
			String[] version_id_txt = version.split("\\.");
			int[] version_id = { Integer.parseInt(version_id_txt[0]),
								 Integer.parseInt(version_id_txt[1]),
								 Integer.parseInt(version_id_txt[2])
							   };
			
			
			if( (version_id[0] - version_id[2]) ==  (API_SERVER_VERSION[0] - API_SERVER_VERSION[2]) ){
				
				ret_val = true;
			}
			else{
				m_logger.info("The client version:"+version + " isn't compatible with this server version :"+
						      new String( API_SERVER_VERSION[0] + "." + API_SERVER_VERSION[1] + "." + API_SERVER_VERSION[2] ));
			}
		}
		catch(Exception e){
			m_logger.error("Unable to check compatibility of client api : "+version);
			StdOutErrLog.tieSystemOutAndErrToLog();
			e.printStackTrace();
		}
		
		return ret_val;
	}

	/**
	 * @return Environment data in json format.
	 * @throws Xception
	 * @throws TException
	 * @see com.connectlife.clapi.CLApi.Iface#getEnvironmentDataJson()
	 */
	@Override
	public String getEnvironmentDataJson() throws Xception, TException {
		return m_environment.getJsonEnvironment();
	}
}
