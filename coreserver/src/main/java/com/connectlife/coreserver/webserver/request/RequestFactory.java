/**
 *  RequestFactory.java
 *  coreserver
 *
 *  Created by ericpinet on 11 oct. 2016.
 *  Copyright (c) 2016 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.connectlife.coreserver.webserver.request;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xnap.commons.i18n.I18n;

import com.connectlife.coreserver.Application;
import com.google.api.client.repackaged.com.google.common.base.Preconditions;

/**
 * Request factory. Use this factory to build request. 
 * 
 * @author ericpinet
 * <br> 11 oct. 2016
 */
public class RequestFactory {
	
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
	private static RequestFactory m_ref = null;
	
	/**
	 * List of requests available.
	 */
	private List<RequestBase> m_requests;
	
	/**
	 * Return singleton instance.
	 * 
	 * @return Singleton instance.
	 */
	public static RequestFactory getRef() {
		if (null == m_ref)
			m_ref = new RequestFactory();
		return m_ref;
	}
	
	/**
	 * Default constructor.
	 */
	private RequestFactory () {
		m_requests = new ArrayList<RequestBase>();
		m_requests.add(new RequestListModules());
		m_requests.add(new RequestListSystemInformations());
		m_requests.add(new RequestListConfigs());
	}
	
	/**
	 * Return requests available.
	 * 
	 * @return List of all available requests.
	 */
	public List<RequestBase> getRequests() {
		return m_requests;
	}
	
	/**
	 * Build the request to answer the request.
	 * 
	 * @param _request Client request.
	 * @return Request processor to execute this client request.
	 */
	public static RequestBase getRequest(HttpServletRequest _request) {
		
		Preconditions.checkNotNull(_request, i18n.tr("The request cannot be null."));
		
		Iterator<RequestBase> it = (RequestFactory.getRef().getRequests().iterator());
		while (it.hasNext()) {
			RequestBase request = it.next();
			if (request.requestCompatibility(_request))
				return request;
		}
		return null;
		
	}
}
