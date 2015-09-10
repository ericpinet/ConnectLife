/**
 *  AccessoriesHandler.java
 *  coreserver
 *
 *  Created by Eric Pinet  on 2015-09-09.
 *  Copyright (c) 2015 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.connectlife.coreserver.modules.apiserver;

// external
import java.io.PrintWriter;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

// internal

/**
 * AgiServerHandler manage the JSON api server. 
 * 
 * @author ericpinet
 * <br> 2015-09-09
 */
public class AccessoriesHandler extends AbstractHandler {
	
    /**
     * Default Constructor
     */
	public AccessoriesHandler()
    {
        
    }

	/**
	 * 
	 * @param _target
	 * @param _base_request
	 * @param _request
	 * @param _response
	 * @throws IOException
	 * @throws ServletException
	 * @see org.eclipse.jetty.server.Handler#handle(java.lang.String, org.eclipse.jetty.server.Request, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public void handle(	String _target, 
					 	Request _base_request, 
					 	HttpServletRequest _request, 
					 	HttpServletResponse _response )
			throws IOException, ServletException {
		
		//TODO - Create JSON response
		_response.setContentType("text/html; charset=utf-8");
		_response.setStatus(HttpServletResponse.SC_OK);
 
        PrintWriter out = _response.getWriter();
 
        out.println("<h1>" + "It's work" + "</h1>");
        out.println("I'm amazing!");
 
        _base_request.setHandled(true);
	}

}
