/**
 *  AssetHttpHandler.java
 *  coreserver
 *
 *  Created by ericpinet on 2016-07-25.
 *  Copyright (c) 2016 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.connectlife.coreserver.environment.asset;

import java.io.File;
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
import org.xnap.commons.i18n.I18n;

import com.clapi.data.Asset;
import com.connectlife.coreserver.Application;
import com.connectlife.coreserver.environment.find.FindProcessor;
import com.connectlife.coreserver.tools.errormanagement.StdOutErrLog;
import com.google.common.io.Files;

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
	private Logger m_logger = LogManager.getLogger(getClass().getName());
	
	/**
	 * Initialization of translation system.
	 */
	private static I18n i18n = Application.i18n;
	
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
	 * Asset manager.
	 */
	private AssetMngr m_manager;
	
	/**
	 * Default constructor.
	 * 
	 * @param _manager
	 */
	public AssetHttpHandler (AssetMngr _manager) {
		m_manager = _manager;
	}
	
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
			
			// Get host name
			m_hostname = addr.getHostName();
			
			// listen port
			m_listen_port = ((ServerConnector)this.getServer().getConnectors()[0]).getLocalPort();
	        
		} catch (Exception e) {
			m_logger.error(i18n.tr("Unable to get the connection information."));
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
	 * @param _target Target of the request.
	 * @param _baseRequest Base request.
	 * @param _request HTTP request.
	 * @param _response HTTP response.
	 *
	 * @throws IOException If something goes wrong.
	 * @throws ServletException If something goes wrong.
	 * 
	 * @see org.eclipse.jetty.server.Handler#handle(java.lang.String, org.eclipse.jetty.server.Request, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public void handle(	String _target, 
						Request _baseRequest, 
						HttpServletRequest _request, 
						HttpServletResponse _response)
			throws IOException, ServletException {
		
		
		FindProcessor find = null;
		Asset asset = null;
		
		// check if request are completed with a uid request
        String uid = _request.getParameter("uid");

        // Check if the string parameter is there and not empty
        if (uid != null && !uid.trim().equals("")) {
		
			// Get the FindProcessor of the environment
			try {
				
				m_logger.info(i18n.tr("Request asset data for :") + uid);
				
				find = Application.getApp().getEnvironment().getFindProcessor();
	
				// find the asset by the uid
				asset = find.findAssetByUid(uid);
				
				if (null != asset) {
					// load file binary data
					File file = new File(m_manager.getAssetFullFilename(asset));
					byte[] bytes = Files.toByteArray(file);
					
					// create and send the response 
					_response.setContentType("image/png");
					_response.setStatus(HttpServletResponse.SC_OK);
					_response.getOutputStream().write(bytes);
					_baseRequest.setHandled(true);
				}
				else {
					m_logger.warn(i18n.tr("Unable to find asset for uid :") + uid);
				}
				
			} catch (Exception e) {
				m_logger.error(e.getMessage());
				StdOutErrLog.tieSystemOutAndErrToLog();
				e.printStackTrace();
			}
        }
	}
}
