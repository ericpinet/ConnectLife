/**
 *  RequestServiceStatus.java
 *  coreserver
 *
 *  Created by ericpinet on 11 oct. 2016.
 *  Copyright (c) 2016 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.connectlife.coreserver.webserver.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

/**
 * Return list of the modules.
 * <br><br>
 * Call sample: <br>
 * http://localhost:8080/api/module
 * 
 * @author ericpinet
 * <br> 11 oct. 2016
 */
public class ServletModule extends ServletBase {
	
	/**
	 * Serial version UID
	 */
	private static final long serialVersionUID = 6580371957875257106L;

	/**
	 * Entry point of the servlet. 
	 */
	private final static String ENTRY_POINT = "module";
	
	/**
	 * Module name 
	 */
	private final static String NAME = "name";
	
	/**
	 * Module short name 
	 */
	private final static String SHORT_NAME = "short_name";
	
	/**
	 * Module description 
	 */
	private final static String DESCRIPTION = "description";
	
	/**
	 * Module status 
	 */
	private final static String STATUS = "status";
	
	/**
	 * Default constructor.
	 */
	public ServletModule() {
		
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
		
		@SuppressWarnings("rawtypes")
		List<Map> modules = new ArrayList<Map>();
		
		modules.add(buildModuleMap("com.connectlife.coreserver.config.ConfigSqlite", i18n.tr("Config"), i18n.tr("Configuration module of the system. Manage the base settings of the system."), "started"));
		modules.add(buildModuleMap("com.connectlife.coreserver.environment.EnvironmentManager", i18n.tr("Environment"), i18n.tr("Environment manager module of the system. Data management of the system."), "started"));
		modules.add(buildModuleMap("com.connectlife.coreserver.environment.device.DeviceMngr", i18n.tr("Device"), i18n.tr("Device management module of the system. Accessories management."), "started"));
		modules.add(buildModuleMap("com.connectlife.coreserver.environment.device.DiscoveryJmdns", i18n.tr("Discovery"), i18n.tr("Device discovery module of the system."), "started"));
		modules.add(buildModuleMap("com.connectlife.coreserver.webserver.WebServerJetty", i18n.tr("WebService"), i18n.tr("Web Server of the system."), "started"));
		modules.add(buildModuleMap("com.connectlife.coreserver.console.ConsoleSSH", i18n.tr("Console"), i18n.tr("Console (command line) to manage the system."), "started"));
		modules.add(buildModuleMap("com.connectlife.coreserver.apiserver.ApiGrpc", i18n.tr("API"), i18n.tr("API server for the client application of the system."), "started"));
		
	    String json = new Gson().toJson(modules);

	    _response.setContentType("application/json");
	    _response.setCharacterEncoding("UTF-8");
	    _response.getWriter().write(json);
	}
	
	/**
	 * Build map for a module. 
	 * 
	 * @param _name Name of the module.
	 * @param _short_name Short name of the module.
	 * @param _description Description of the module.
	 * @param _status Status of the module.
	 * @return Map of the module.
	 */
	private Map<String, String> buildModuleMap(String _name, String _short_name, String _description, String _status) {
		Map<String, String> ret_module = new HashMap<String, String>();
		ret_module.put(NAME, _name);
		ret_module.put(SHORT_NAME, _short_name);
		ret_module.put(DESCRIPTION, _description);
		ret_module.put(STATUS, _status);
		return ret_module;
	}

}
