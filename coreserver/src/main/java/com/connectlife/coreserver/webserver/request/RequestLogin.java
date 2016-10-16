/**
 *  RequestLogin.java
 *  coreserver
 *
 *  Created by ericpinet on 15 oct. 2016.
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
import javax.servlet.http.HttpSession;

import com.connectlife.coreserver.Application;
import com.connectlife.coreserver.config.Config;
import com.connectlife.coreserver.tools.errormanagement.StdOutErrLog;
import com.google.api.client.util.Preconditions;
import com.google.gson.Gson;

/**
 * Request for login
 * 
 * @author ericpinet
 * <br> 15 oct. 2016
 */
public class RequestLogin extends RequestBase {
	
	/**
	 * Query of the request. 
	 */
	private static String QUERY = "login";
	
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
	public RequestLogin() {
		
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
