/**
 *  RequestListConfig.java
 *  coreserver
 *
 *  Created by ericpinet on 13 oct. 2016.
 *  Copyright (c) 2016 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.connectlife.coreserver.webserver.request;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.connectlife.coreserver.Application;
import com.connectlife.coreserver.config.ConfigItem;
import com.connectlife.coreserver.tools.errormanagement.StdOutErrLog;
import com.google.api.client.util.Preconditions;
import com.google.gson.Gson;

/**
 * Return list of the configurations.
 * 
 * @author ericpinet
 * <br> 13 oct. 2016
 */
public class RequestListConfigs extends RequestBase {
	
	/**
	 * Query of the request. 
	 */
	private final static String QUERY = "list_configs";
	
	/**
	 * Default constructor.
	 */
	public RequestListConfigs() {
		
	}
	
	/**
	 * Check the compatibility of the request processor vs the client request.
	 * 
	 * @param _request Client request
	 * @return True if the client request can be respond by this request.
	 */
	@Override
	public boolean requestCompatibility(HttpServletRequest _request) {
		
		Preconditions.checkNotNull(_request, i18n.tr("Error! It's not possible to check compatibility of null http request."));
		
		boolean ret_val = false;
		try {
			Map<String, List<String>> params = getParameters(_request.getQueryString());
			List<String> queries = params.get("query");
			
			if ( queries.contains(QUERY) ) {
				ret_val = true;
			}
			
		} catch (UnsupportedEncodingException e) {
			m_logger.error(e.getMessage());
			StdOutErrLog.tieSystemOutAndErrToLog();
			e.printStackTrace();
		}
		
		return ret_val;
	}

	/**
	 * Process the request. 
	 * 
	 * @param _request Client request.
	 * @param _response Server response.
	 * @throws ServletException If something goes wrong.
	 * @throws IOException If connection lost.
	 * @throws Exception If something goes wrong.
	 */
	@Override
	public void process(HttpServletRequest _request, HttpServletResponse _response) throws ServletException, IOException, Exception {
		
		Preconditions.checkArgument(requestCompatibility(_request), i18n.tr("Error! This request is invalid: "+_request.getQueryString()));
		
		List<ConfigItem> configs = Application.getApp().getConfig().getConfigs();
	    String json = new Gson().toJson(configs);

	    _response.setContentType("application/json");
	    _response.setCharacterEncoding("UTF-8");
	    _response.getWriter().write(json);
	}
}
