/**
 *  AssetHttpHandler.java
 *  coreserver
 *
 *  Created by ericpinet on 2016-07-25.
 *  Copyright (c) 2016 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.connectlife.coreserver.environment.asset;

import java.io.IOException;
import java.net.InetAddress;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.AbstractHandler;

import com.connectlife.coreserver.tools.errormanagement.StdOutErrLog;

/**
 * Http handler for request in the asset environment data. 
 * 
 * @author ericpinet
 * <br> 2016-07-25
 */
public class AssetHttpHandler extends AbstractHandler {
	
	/**
	 * Init logger instance for this class
	 */
	private static Logger m_logger = LogManager.getLogger(AssetHttpHandler.class);
	
	/**
	 * Ip address of the asset http handler.
	 */
	private String m_ip_address;
	
	/**
	 * Hostname of the asset http handler.
	 */
	private String m_hostname;
	
	/**
	 * Listen port of the asset http handler.
	 */
	private int m_listen_port;
	
	/**
	 * Update the connection information.
	 * Load ip, hostname and tcp/ip port.
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
			m_listen_port = ((ServerConnector)this.getServer().getConnectors()[0]).getLocalPort();
	        
		} catch (Exception e) {
			m_logger.error("Unable to get the connection information.");
			m_logger.error(e.getMessage());
			StdOutErrLog.tieSystemOutAndErrToLog();
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
	 * @return the m_hostname
	 */
	public String getHostname() {
		return m_hostname;
	}

	/**
	 * @return the m_port
	 */
	public int getPort() {
		return m_listen_port;
	}

	/**
	 * Handle a HTTP request.
	 * 
	 * @param target Target of the request.
	 * @param baseRequest Base request.
	 * @param request HTTP request.
	 * @param response HTTP response.
	 *
	 * @throws IOException If something goes wrong.
	 * @throws ServletException If something goes wrong.
	 * 
	 * @see org.eclipse.jetty.server.Handler#handle(java.lang.String, org.eclipse.jetty.server.Request, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public void handle(	String target, 
						Request baseRequest, 
						HttpServletRequest request, 
						HttpServletResponse response)
			throws IOException, ServletException {
		// TODO Auto-generated method stub

	}

}
