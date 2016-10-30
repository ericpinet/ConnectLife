/**
 *  ServletLogs.java
 *  coreserver
 *
 *  Created by ericpinet on 24 oct. 2016.
 *  Copyright (c) 2016 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.connectlife.coreserver.webserver.servlet;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.Appender;
import org.apache.logging.log4j.core.appender.RollingFileAppender;

import com.google.gson.Gson;

/**
 * Request logs of the system.
 * <br><br>
 * Call sample: <br>
 * http://localhost:8080/api/log?id=70
 * 
 * 
 * @author ericpinet
 * <br> 24 oct. 2016
 */
public class ServletLog extends ServletBase {
	
	/**
	 * Serial version UID 
	 */
	private static final long serialVersionUID = -129490951334031906L;
	
	/**
	 * Entry point of the servlet. 
	 */
	private static String ENTRY_POINT = "log";
	
	/**
	 * id of the first line to retrieve
	 * if id = 10, the request will return only line 10 and above.
	 */
	private static String ID = "id";
	
	/**
	 * Init logger instance for this class
	 */
	private Logger m_logger = LogManager.getLogger(getClass().getName());
	
	/**
	 * Default constructor.
	 */
	public ServletLog() {
		
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
		
		Map<Integer, String> logs = new HashMap<Integer, String>();
		
		// get the logger filename
		org.apache.logging.log4j.core.Logger loggerImpl = (org.apache.logging.log4j.core.Logger) m_logger;
		Appender appender = (loggerImpl).getAppenders().get("File");
    	String fileName = ((RollingFileAppender)appender).getFileName();
    	
    	// Get position
       	int position = 0;
       	if (null != _request.getParameter(ID) && false == _request.getParameter(ID).isEmpty() ) {
       		position = Integer.parseInt(_request.getParameter(ID));
       	}
    	
    	try{
    		
    		// convert log file in map data
    		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
    		    String line = "";
    		    int id = 0;
    		    while ((line = br.readLine()) != null) {
    		    	if (id>=position)
    		    		logs.put( id, line );
    		    	id++;
    		    }
    		}
    	}
    	catch (Exception e){
    		m_logger.warn(e.getMessage());
    	}
		
    	// map data to json for the response
	    String json = new Gson().toJson(logs); 

	    // build response
	    _response.setContentType("application/json");
	    _response.setCharacterEncoding("UTF-8");
	    _response.getWriter().write(json);
	}

}
