/**
 *  RequestListConfig.java
 *  coreserver
 *
 *  Created by ericpinet on 13 oct. 2016.
 *  Copyright (c) 2016 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.connectlife.coreserver.webserver.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.connectlife.coreserver.Application;
import com.connectlife.coreserver.config.ConfigItem;
import com.google.gson.Gson;

/**
 * Return list of the configurations.
 * 
 * @author ericpinet
 * <br> 13 oct. 2016
 */
public class ServletConfig extends ServletBase {
	
	/**
	 * Serial version uid
	 */
	private static final long serialVersionUID = -3488603144413187590L;
	
	/**
	 * Entry point of the servlet. 
	 */
	private final static String ENTRY_POINT = "config";
	
	/**
	 * Default constructor.
	 */
	public ServletConfig() {
		
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
	 * @throws Exception If something goes wrong.
	 */
	@Override
	public void process(HttpServletRequest _request, HttpServletResponse _response) throws ServletException, IOException {
		
		List<ConfigItem> configs = Application.getApp().getConfig().getConfigs();
	    String json = new Gson().toJson(configs);

	    _response.setContentType("application/json");
	    _response.setCharacterEncoding("UTF-8");
	    _response.getWriter().write(json);
	}


	
}
