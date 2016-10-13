/**
 *  BaseRequest.java
 *  coreserver
 *
 *  Created by ericpinet on 11 oct. 2016.
 *  Copyright (c) 2016 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.connectlife.coreserver.webserver.request;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
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
abstract public class RequestBase {
	
	/**
	 * Init logger instance for this class
	 */
	protected Logger m_logger = LogManager.getLogger(getClass().getName());
	
	/**
	 * Initialization of translation system.
	 */
	protected static I18n i18n = Application.i18n;
	
	/**
	 * Check if the http request is compatible with the request. 
	 * 
	 * @param _request
	 * @return True if the http request is compatible with this request.
	 */
	abstract public boolean requestCompatibility(HttpServletRequest _request);
	
	/**
	 * Process the request and complete and write response in _response.
	 * 
	 * @param _request
	 * @param _response
	 * @throws ServletException
	 * @throws IOException
	 * @throws Exception
	 */
	abstract public void process(HttpServletRequest _request, HttpServletResponse _response) throws ServletException, IOException, Exception;
	
	/**
	 * Build a parameter map key pair from a URL.
	 * 
	 * http://localhost/connectlife?query=list_service&filter=config
	 * 
	 * Pass the : query=list_service&filter=config to this function.
	 * 
	 * Key          Value
	 * -----------  -------------
	 * query        list_service
	 * filter       config
	 * 
	 * @param url
	 * @return
	 * @throws UnsupportedEncodingException
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
