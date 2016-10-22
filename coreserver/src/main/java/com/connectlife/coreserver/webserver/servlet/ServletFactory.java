/**
 *  ServletFactory.java
 *  coreserver
 *
 *  Created by ericpinet on 11 oct. 2016.
 *  Copyright (c) 2016 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.connectlife.coreserver.webserver.servlet;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xnap.commons.i18n.I18n;

import com.connectlife.coreserver.Application;

/**
 * Request factory. Use this factory to build request. 
 * 
 * @author ericpinet
 * <br> 11 oct. 2016
 */
public class ServletFactory {
	
	/**
	 * Init logger instance for this class
	 */
	protected Logger m_logger = LogManager.getLogger(getClass().getName());
	
	/**
	 * Initialization of translation system.
	 */
	protected static I18n i18n = Application.i18n;
	
	/**
	 * Singleton instance.
	 */
	private static ServletFactory m_ref = null;
	
	/**
	 * List of requests available.
	 */
	private List<ServletBase> m_requests;
	
	/**
	 * Return singleton instance.
	 * 
	 * @return Singleton instance.
	 */
	public static ServletFactory getRef() {
		if (null == m_ref)
			m_ref = new ServletFactory();
		return m_ref;
	}
	
	/**
	 * Default constructor.
	 */
	private ServletFactory () {
		m_requests = new ArrayList<ServletBase>();
		m_requests.add(new ServletModule());
		m_requests.add(new ServletSystem());
		m_requests.add(new ServletConfig());
		m_requests.add(new ServletLogin());
		m_requests.add(new ServletLogout());
	}
	
	/**
	 * Return servlets available.
	 * 
	 * @return List of all available servlet.
	 */
	public List<ServletBase> getServlets() {
		return m_requests;
	}
}
