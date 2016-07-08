/**
 *  DeviceHttpHandler.java
 *  simulator
 *
 *  Created by ericpinet on 2016-01-09.
 *  Copyright (c) 2016 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.connectlife.simulator.device;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.AbstractHandler;

import com.clapi.simulator.device.ServiceDefinition;
import com.google.gson.Gson;

/**
 * Handler HTTP for the device simulation.
 * 
 * @author ericpinet
 * <br> 2016-01-09
 */
public class DeviceHttpHandler extends AbstractHandler {
	
	/**
	 * Service count url section.
	 */
	private static final int TARGET_COUNT = 3;
	
	/**
	 * Service name position in url section.
	 */
	private static final int TARGET_SERVICE_NAME_POSITION = 1;
	
	/**
	 * Characteristic label position in url section.
	 */
	private static final int TARGET_CHARACTERISTIC_LABEL_POSITION = 2;
	
	/**
	 * Init logger instance for this class
	 */
	private static Logger m_logger = LogManager.getLogger(DeviceHttpHandler.class);

	/**
	 * Device parent of this http handle.
	 */
	private Device m_device;
	
	/**
	 * Ip address of the device.
	 */
	private String m_ip_address;
	
	/**
	 * Hostname of the device.
	 */
	private String m_hostname;
	
	/**
	 * Listen port of the device.
	 */
	private int m_listen_port;
	
	/**
	 * Default constructor for the http handler.
	 * @param _device
	 */
	public DeviceHttpHandler( Device _device ){
		m_device = _device;	
	}
	
	/**
	 * Update the connection information.
	 */
	public void updateConnectionInformaiton(){
		
		InetAddress addr;
		try {
			addr = InetAddress.getLocalHost();
			
			// Get IP Address
			m_ip_address = addr.getHostAddress();
			
			// Get hostname
			m_hostname = addr.getHostName();
			
			// listen port
			m_listen_port = ((ServerConnector)m_device.getServer().getConnectors()[0]).getLocalPort();
	        
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return the m_ip_address
	 */
	public String getIpAddress() {
		return m_ip_address;
	}

	/**
	 * @param m_ip_address the m_ip_address to set
	 */
	public void setIpAddress(String _ip_address) {
		this.m_ip_address = _ip_address;
	}

	/**
	 * @return the m_hostname
	 */
	public String getHostname() {
		return m_hostname;
	}

	/**
	 * @param m_hostname the m_hostname to set
	 */
	public void setHostname(String _hostname) {
		this.m_hostname = _hostname;
	}

	/**
	 * @return the m_port
	 */
	public int getPort() {
		return m_listen_port;
	}

	/**
	 * @param m_port the m_port to set
	 */
	public void setPort(int _port) {
		this.m_listen_port = _port;
	}

	/**
	 * Handle the HTTP request for the device.
	 * 
	 * @param _target
	 * @param _baserequest
	 * @param _request
	 * @param _response
	 * 
	 * @throws IOException
	 * @throws ServletException
	 * 
	 * @see org.eclipse.jetty.server.Handler#handle(java.lang.String, org.eclipse.jetty.server.Request, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public void handle(String _target, Request _baserequest, HttpServletRequest _request, HttpServletResponse _response)
			throws IOException, ServletException {
		
        // process the request
		processRequest(_target, _baserequest, _request, _response);
		 
        // create a string with json representation of the result device use to display available services.
        Gson gson = new Gson();
		String jsonresponse = gson.toJson(new ServiceDefinition(m_ip_address, m_hostname, m_listen_port, m_device.getAccessory()));
        
		// create and send the response 
		_response.setContentType("text/json; charset=utf-8");
		_response.setStatus(HttpServletResponse.SC_OK);
		_response.getWriter().println(jsonresponse);
		_baserequest.setHandled(true);
	}
	
	/**
	 * Process the request if the client ask a characteristic change.
	 * <p>
	 * Sample of update request : 
	 * <p>
	 * http://192.168.2.10:52262/light/light?value=true
	 * 
	 * @param target
	 * @param baserequest
	 * @param request
	 * @param response
	 * 
	 * @throws IOException
	 * @throws ServletException
	 */
	private void processRequest(String _target, Request _baserequest, HttpServletRequest _request, HttpServletResponse _response)
			throws IOException, ServletException {
		
		// check if parameters is present for all services of this device
		// if parameters is present, execute the action
        String value = _request.getParameter("value");

        // Check if the string parameter is there and not empty
        if (value != null && !value.trim().equals("")) {
        	
        	// slip the target to get the service name and the characteristic label
        	// exemple of target : /light/dimmable (light = service name, dimmable = characteristic_label)
        	String[] result = _target.split("/");
        	String service_name = "";
        	String characteristic_label = "";
        	
        	if(result.length==TARGET_COUNT){
        		service_name = result[TARGET_SERVICE_NAME_POSITION];
        		characteristic_label = result [TARGET_CHARACTERISTIC_LABEL_POSITION];
        	}
        	else{
        		m_logger.error("Invalid call of service. " + _target );
        	}
        	
        	// change the value of the characteristic
        	m_device.setNewCharacteristicValue(service_name, characteristic_label, value);
        	
        } // ELSE: if no value, do noting.
	}
}
