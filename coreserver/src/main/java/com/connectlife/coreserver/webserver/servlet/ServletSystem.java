/**
 *  RequestListSystemInformation.java
 *  coreserver
 *
 *  Created by ericpinet on 11 oct. 2016.
 *  Copyright (c) 2016 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.connectlife.coreserver.webserver.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.connectlife.coreserver.Application;
import com.connectlife.coreserver.Consts;
import com.connectlife.coreserver.tools.os.OperatingSystem;
import com.google.gson.Gson;

/**
 * Request list of the system informations.
 * 
 * @author ericpinet
 * <br> 11 oct. 2016
 */
public class ServletSystem extends ServletBase {
	
	/**
	 * Serial version UID 
	 */
	private static final long serialVersionUID = 5755799887200689999L;
	
	/**
	 * Entry point of the servlet. 
	 */
	private static String ENTRY_POINT = "system";
	
	/**
	 * Default constructor.
	 */
	public ServletSystem() {
		
	}

	/**
	 * Get the entry point of the Servlet.
	 * 
	 * @return Path of the entry point
	 * @see com.connectlife.coreserver.webserver.servlet.ServletBase#getEntryPoint()
	 */
	@Override
	public String getEntryPoint() {
		return ENTRY_POINT;
	}

	/**
	 * Process the request. 
	 * 
	 * @param _request Client request.
	 * @param _response Server response.
	 * @throws ServletException If something goes wrong.
	 * @throws IOException If connection lost.
	 */
	@Override
	public void process(HttpServletRequest _request, HttpServletResponse _response) throws ServletException, IOException {
		
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
