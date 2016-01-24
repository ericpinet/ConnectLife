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
	private String m_ip_address;
	
	/**
	 * Service hostname.
	 */
	private String m_hostname;
	
	/**
	 * Service TCP/IP port.
	 */
	private int m_port;
	
	/**
	 * Accessory definition
	 */
	private Accessory m_accessory;

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

	/**
	 * @return the m_ip_address
	 */
	public String getIpAddress() {
		return m_ip_address;
	}

	/**
	 * @param _ip_address the _ip_address to set
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
	 * @param _hostname the _hostname to set
	 */
	public void setHostname(String _hostname) {
		this.m_hostname = _hostname;
	}

	/**
	 * @return the m_port
	 */
	public int getPort() {
		return m_port;
	}

	/**
	 * @param _port the _port to set
	 */
	public void setPort(int _port) {
		this.m_port = _port;
	}

	/**
	 * @return the m_accessory
	 */
	public Accessory getAccessory() {
		return m_accessory;
	}

	/**
	 * @param _accessory the _accessory to set
	 */
	public void setAccessory(Accessory _accessory) {
		this.m_accessory = _accessory;
	}
	
	
}

