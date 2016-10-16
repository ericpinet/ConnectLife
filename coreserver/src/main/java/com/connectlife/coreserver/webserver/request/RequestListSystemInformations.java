/**
 *  RequestListSystemInformation.java
 *  coreserver
 *
 *  Created by ericpinet on 11 oct. 2016.
 *  Copyright (c) 2016 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.connectlife.coreserver.webserver.request;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.connectlife.coreserver.Application;
import com.connectlife.coreserver.Consts;
import com.connectlife.coreserver.tools.errormanagement.StdOutErrLog;
import com.connectlife.coreserver.tools.os.OperatingSystem;
import com.google.api.client.util.Preconditions;
import com.google.gson.Gson;

/**
 * Request list of the system informations.
 * 
 * @author ericpinet
 * <br> 11 oct. 2016
 */
public class RequestListSystemInformations extends RequestBase {
	
	/**
	 * Query of the request. 
	 */
	private static String QUERY = "list_system_info";
	
	/**
	 * Default constructor.
	 */
	public RequestListSystemInformations() {
		
	}

	/**
	 * Check if the client request can be solve by this request processor.
	 * 
	 * @param _request Client request.
	 * @return True if the request,
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
	 * @param _request Client request.
	 * @param _response Server response.
	 * @throws ServletException If something goes wrong.
	 * @throws IOException If connection lost.
	 * @throws Exception If something goes wrong.
	 */
	@Override
	public void process(HttpServletRequest _request, HttpServletResponse _response) throws ServletException, IOException, Exception {
		
		Preconditions.checkArgument(requestCompatibility(_request), i18n.tr("Error! This request is invalid: "+_request.getQueryString()));
		
		Map<String, String> system_infos = new HashMap<String, String>();
		
		system_infos.put(i18n.tr("System Name"), Consts.APP_NAME);
		system_infos.put(i18n.tr("Version"), Consts.APP_VERSION);
		system_infos.put(i18n.tr("Base Directory"), Application.getApp().getBasePath());
		system_infos.put(i18n.tr("Operating System"), OperatingSystem.getOSName());
		
	    String json = new Gson().toJson(system_infos);

	    _response.setContentType("application/json");
	    _response.setCharacterEncoding("UTF-8");
	    _response.getWriter().write(json);
	}

}
