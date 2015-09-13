/**
 *  JsonHandler.java
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
import com.connectlife.coreserver.modules.environment.EnvironmentManager;

/**
 * AgiServerHandler manage the JSON api server. 
 * 
 * @author ericpinet
 * <br> 2015-09-09
 */
public class JsonHandler extends AbstractHandler {
	
    /**
     * Default Constructor
     */
	public JsonHandler()
    {
        
    }

	/**
	 * Handle for the http request.
	 * 
	 * @param _target				Target of the request.
	 * @param _base_request			Base request.
	 * @param _request				Request.
	 * @param _response				Response.
	 * @throws IOException			IOException can occur during the request.
	 * @throws ServletException		ServletException can occur during the request.
	 * @see org.eclipse.jetty.server.Handler#handle(java.lang.String, org.eclipse.jetty.server.Request, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public void handle(	String _target, 
					 	Request _base_request, 
					 	HttpServletRequest _request, 
					 	HttpServletResponse _response )
			throws IOException, ServletException {
		
		_response.setContentType("application/json; charset=utf-8");
		_response.setStatus(HttpServletResponse.SC_OK);
 
        PrintWriter out = _response.getWriter();
        out.print(EnvironmentManager.getInstance().getJsonEnvironment());
 
        _base_request.setHandled(true);
	}

}
