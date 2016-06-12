/**
 *  DeviceFactory.java
 *  coreserver
 *
 *  Created by ericpinet on 2016-01-23.
 *  Copyright (c) 2016 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.connectlife.coreserver.environment.device;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.jmdns.ServiceEvent;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.clapi.simulator.device.ServiceDefinition;
import com.google.gson.Gson;

/**
 * Factory for the service manager. 
 * This factory buid the right service 
 * 
 * @author ericpinet
 * <br> 2016-01-23
 */
public abstract class DeviceFactory {
	
	/**
	 * Logger use for this class.
	 */
	private static Logger m_logger = LogManager.getLogger(DeviceFactory.class);
	
	/**
	 * Service application HTTP application.
	 */
	public static final String _SERVICE_APPLICATION_HTTP_ = "http";
	
	/**
	 * Service application Air Play application.
	 */
	public static final String _SERVICE_APPLICATION_AIRPLAY_ = "airplay";
	
	/**
	 * Service application IPP (Print) application.
	 */
	public static final String _SERVICE_APPLICATION_IPP_ = "ipp";
	
	/**
	 * Constructor private for the factory.
	 */
	private DeviceFactory(){
		
	}
	
	/**
	 * Build a service object with the Bonjour service discovered.
	 * 
	 * @param _service The service event from JmDns.
	 * @return Device ready to use. With monitor and controller.
	 * @throws Exception If the building process failed , a exception will be generated with the details.
	 */
	public static Device buildService(ServiceEvent _service) throws Exception {
		
		Device ret_service = null;
		
		// check if the service is valid
		if(null != _service){
			
			if( _service.getInfo().getApplication().equals(_SERVICE_APPLICATION_HTTP_) ){ // build the service object for HTTP
				
				String [] urls = _service.getInfo().getURLs();
				for(int i=0 ; i<urls.length ; i++){
					m_logger.info(urls[i]);
					
					try{
						ServiceDefinition service_definition = DeviceFactory.buildServiceInformation(urls[i]);
						ret_service = new DeviceJson(service_definition, _service.getInfo());
					}
					catch(Exception e){
						m_logger.warn("Unable to manage this http service: " + urls[i]);
					}
					
				}// END for.
			}
			else if( _service.getInfo().getApplication().equals(_SERVICE_APPLICATION_AIRPLAY_) ){ // build the service object for AIR PLAY
				m_logger.warn("Service application " +_service.getInfo().getApplication() + " not supported yet!");
			}
			else if( _service.getInfo().getApplication().equals(_SERVICE_APPLICATION_IPP_) ){ // build the service object for IPP (Print)
				m_logger.warn("Service application " +_service.getInfo().getApplication() + " not supported yet!");
			}
			else{
				throw new Exception("Service application " +_service.getInfo().getApplication() + " not supported yet!");
			}
			
		}
		else{
			throw new Exception("Service is null.");
		}
		
		return ret_service;
	}
	
	/**
	 * Build a service definition from url. The url have to return json format service definition.
	 * 
	 * @param _url Url of the service.
	 * @return Return the ServiceDefinition.
	 * @throws Exception If the building process failed , a exception will be generated with the details.
	 */
	public static ServiceDefinition buildServiceInformation(String _url) throws Exception {
		ServiceDefinition ret_def = null;
		
		try {

			// Read the service definition on the json web service.
			URL url = new URL(_url);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			if (conn.getResponseCode() != 200) {
				throw new Exception("Failed : HTTP error code : " + conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			String line = "", json = "";
			while ((line = br.readLine()) != null) {
				json += line;
			}
			conn.disconnect();

			// Create the ServiceDefinition
			Gson gson = new Gson();        
			ret_def = gson.fromJson(json, ServiceDefinition.class);

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return ret_def;
	}

}
