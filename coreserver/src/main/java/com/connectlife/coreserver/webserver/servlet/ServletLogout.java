/**
 *  RequestLogout.java
 *  coreserver
 *
 *  Created by ericpinet on 15 oct. 2016.
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
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

/**
 * Request for login
 * 
 * @author ericpinet
 * <br> 15 oct. 2016
 */
public class ServletLogout extends ServletBase {
	
	/**
	 * Serial Version UID 
	 */
	private static final long serialVersionUID = -8739750015684249201L;
	
	/**
	 * Entry point of the servlet. 
	 */
	private static String ENTRY_POINT = "logout";
	
	/**
	 * Default constructor.
	 */
	public ServletLogout() {
		
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
				
		// Create the session
		HttpSession session = _request.getSession(false); // true to create new session if not exist.
		session.setAttribute("logged", "false");
		
		// Build response.
		Map<String, String> response = new HashMap<String, String>();
		response.put("status", "completed");

	    String json = new Gson().toJson(response);
	    _response.setContentType("application/json");
	    _response.setCharacterEncoding("UTF-8");
	    _response.getWriter().write(json);
	}

}
