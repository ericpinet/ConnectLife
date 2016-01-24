/**
 *  ServiceDefinition.java
 *  clapi
 *
 *  Created by ericpinet on 2016-01-05.
 *  Copyright (c) 2016 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.clapi.simulator.device;

import com.clapi.data.Accessory;

/**
 * Definition for a service.
 * Use to return the information about services of this device.
 * 
 * @author ericpinet
 * <br> 2016-01-05
 */
public class ServiceDefinition {
	
	/**
	 * Service IP address.
	 */
	public String m_ip_address;
	
	/**
	 * Service hostname.
	 */
	public String m_hostname;
	
	/**
	 * Service TCP/IP port.
	 */
	public int m_port;
	
	/**
	 * Accessory definition
	 */
	public Accessory m_accessory;

	/**
	 * Default constructor.
	 * 
	 * @param _ip_address Ip address of the service.
	 * @param _hostname Hostname of the service.
	 * @param _port Port of the service.
	 * @param _accessory Accessory of the service.
	 */
	public ServiceDefinition(String _ip_address, String _hostname, int _port, Accessory _accessory) {
		super();
		this.m_ip_address = _ip_address;
		this.m_hostname = _hostname;
		this.m_port = _port;
		this.m_accessory = _accessory;
	}
}

