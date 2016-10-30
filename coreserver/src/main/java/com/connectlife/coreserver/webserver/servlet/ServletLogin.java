/**
 *  RequestLogin.java
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

import com.connectlife.coreserver.Application;
import com.connectlife.coreserver.config.Config;
import com.google.gson.Gson;

/**
 * Request for login
 * 
 * @author ericpinet
 * <br> 15 oct. 2016
 */
public class ServletLogin extends ServletBase {
	
	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 8379533983632510729L;

	/**
	 * Entry point of the servlet. 
	 */
	private static String ENTRY_POINT = "login";
	
	/**
	 * User to login.
	 */
	private static String USER = "user";
	
	/**
	 * Password of the user.
	 */
	private static String PASSWORD = "password";
	
	/**
	 * Return value if login is valid.
	 */
	private static String RETURN_SUCCESS = "200";
	
	/**
	 * Return value if login is invalid.
	 */
	private static String RETURN_FAIL = "401";
	
	/**
	 * Default constructor.
	 */
	public ServletLogin() {
		
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
		
		String ret_status;
		String ret_error_message;
		
		// Get the system user name and password.
		Config config = Application.getApp().getConfig();
		String admin_username 	= config.getConfig("SYSTEM", "ADMIN_USERNAME").getStringValue();
		String admin_password 	= config.getConfig("SYSTEM", "ADMIN_PASSWORD").getStringValue();
		
		// Get the request user name and password.
		String username = _request.getParameter(USER);
		String password = _request.getParameter(PASSWORD);
				
		// Check the login
		if (username.equals(admin_username) &&
			password.equals(admin_password) ) {
			
			// Create the session
			HttpSession session = _request.getSession(true); // true to create new session if not exist.
			session.setAttribute("logged", "true");
			
			ret_status = RETURN_SUCCESS;
			ret_error_message = "";
		}
		else {
			ret_status = RETURN_FAIL;
			ret_error_message = i18n.tr("Invalid username or password!");
		}
		
		// Build response.
		Map<String, String> response = new HashMap<String, String>();
		response.put("status", ret_status);
		response.put("error", ret_error_message);

	    String json = new Gson().toJson(response);
	    _response.setContentType("application/json");
	    _response.setCharacterEncoding("UTF-8");
	    _response.getWriter().write(json);
	}

}
