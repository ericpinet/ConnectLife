/**
 *  BaseRequest.java
 *  coreserver
 *
 *  Created by ericpinet on 11 oct. 2016.
 *  Copyright (c) 2016 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.connectlife.coreserver.webserver.servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xnap.commons.i18n.I18n;

import com.connectlife.coreserver.Application;

/**
 * Base request for the web server.
 * 
 * @author ericpinet
 * <br> 11 oct. 2016
 */
abstract public class ServletBase extends HttpServlet {
	
	/**
	 * Default serial version uid
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Init logger instance for this class
	 */
	protected Logger m_logger = LogManager.getLogger(getClass().getName());
	
	/**
	 * Initialization of translation system.
	 */
	protected static I18n i18n = Application.i18n;
	
	/**
	 * Get the entry point. 
	 * 
	 * @return Path of the entry point of the servlet.
	 */
	abstract public String getEntryPoint();
	
	/**
	 * Process the request and complete and write response in _response.
	 * 
	 * @param _request Client request.
	 * @param _response Server response.
	 * @throws ServletException If something goes wrong.
	 * @throws IOException On connection lost.
	 * @throws Exception If something goes wrong.
	 */
	abstract public void process(HttpServletRequest _request, HttpServletResponse _response) throws ServletException, IOException;
	
	/**
	 * Process the GET HTTP request.
	 * 
	 * @param _request Client request
     * @param _response Server response
     * @throws ServletException If something goes wrong.
     * @throws IOException If connection lost.
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
    @Override
    protected void doGet(HttpServletRequest _request, HttpServletResponse _response) throws ServletException, IOException {
    	process(_request, _response);
    }
    
    /**
     * Process the POST HTTP request.
     * 
     * @param _request Client request
     * @param _response Server response
     * @throws ServletException If something goes wrong.
     * @throws IOException If connection lost.
     * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    protected void doPost(HttpServletRequest _request, HttpServletResponse _response) throws ServletException, IOException {
    	process(_request, _response);
    }
	
	/**
	 * Build a parameter map key pair from a URL.
	 * 
	 * Key          Value
	 * -----------  -------------
	 * query        list_service
	 * filter       config
	 * 
	 * @param url Url request (after the ?)
	 * @return HashMap with value date.
	 * @throws UnsupportedEncodingException If something goes wrong.
	 */
	public static Map<String, List<String>> getParameters(String url) throws UnsupportedEncodingException {
		final Map<String, List<String>> query_pairs = new LinkedHashMap<String, List<String>>();
		final String[] pairs = url.split("&");
		for (String pair : pairs) {
			final int idx = pair.indexOf("=");
			final String key = idx > 0 ? URLDecoder.decode(pair.substring(0, idx), "UTF-8") : pair;
			if (!query_pairs.containsKey(key)) {
				query_pairs.put(key, new LinkedList<String>());
			}
			final String value = idx > 0 && pair.length() > idx + 1 ? URLDecoder.decode(pair.substring(idx + 1), "UTF-8") : null;
			query_pairs.get(key).add(value);
		}
		return query_pairs;
	}
}
