/**
 *  RequestServiceStatus.java
 *  coreserver
 *
 *  Created by ericpinet on 11 oct. 2016.
 *  Copyright (c) 2016 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.connectlife.coreserver.webserver.request;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.connectlife.coreserver.tools.errormanagement.StdOutErrLog;
import com.connectlife.coreserver.webserver.Service;
import com.google.api.client.util.Preconditions;
import com.google.gson.Gson;

/**
 * 
 * 
 * @author ericpinet
 * <br> 11 oct. 2016
 */
public class RequestListServices extends RequestBase {
	
	/**
	 * Query of the request. 
	 */
	private static String QUERY = "list_services";
	
	/**
	 * Default constructor.
	 */
	public RequestListServices() {
		
	}

	/**
	 * @param _request
	 * @return
	 * @see com.connectlife.coreserver.webserver.request.RequestBase#requestCompatibility(javax.servlet.http.HttpServletRequest)
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
	 * @param _request
	 * @param _response
	 * @throws ServletException
	 * @throws IOException
	 * @throws Exception
	 * @see com.connectlife.coreserver.webserver.request.RequestBase#process(javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public void process(HttpServletRequest _request, HttpServletResponse _response) throws ServletException, IOException, Exception {
		
		Preconditions.checkArgument(requestCompatibility(_request), i18n.tr("Error! This request is invalid: "+_request.getQueryString()));
		
		List<Service> list = new ArrayList<Service>();
		
		list.add(new Service("com.connectlife.coreserver.config.ConfigSqlite", "Config", "Configuration of the system."));
		list.add(new Service("com.connectlife.coreserver.environment.EnvironmentManager", "Environment", "Environment manager of the system."));
		list.add(new Service("com.connectlife.coreserver.webserver.WebServerJetty", "WebService", "Web Server of the system."));
		list.add(new Service("com.connectlife.coreserver.console.ConsoleSSH", "Console", "Console of the system."));
		
	    String json = new Gson().toJson(list);

	    _response.setContentType("application/json");
	    _response.setCharacterEncoding("UTF-8");
	    _response.getWriter().write(json);
	}

}
