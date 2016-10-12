/**
 *  ConnectLifeServlet.java
 *  coreserver
 *
 *  Created by ericpinet on 2016-11-09.
 *  Copyright (c) 2015-2016 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.connectlife.coreserver.webserver;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xnap.commons.i18n.I18n;

import com.connectlife.coreserver.Application;
import com.connectlife.coreserver.tools.errormanagement.StdOutErrLog;
import com.connectlife.coreserver.webserver.request.RequestBase;
import com.connectlife.coreserver.webserver.request.RequestFactory;

@SuppressWarnings("serial")
public class ConnectLifeServlet extends HttpServlet
{
	/**
	 * Init logger instance for this class
	 */
	private Logger m_logger = LogManager.getLogger(getClass().getName());
	
	/**
	 * Initialization of translation system.
	 */
	public static I18n i18n = Application.i18n;

    /**
     * Process the HTTP resquest.
     * 
     * @param _request
     * @param _response
     * @throws ServletException
     * @throws IOException
     */
    private void process(HttpServletRequest _request, HttpServletResponse _response) throws ServletException, IOException {
    	
		RequestBase request = RequestFactory.getRequest(_request);
		if (null != request) {
    		try {
    			request.process(_request, _response);
    			
			} catch (Exception e) {
				ServletException exeption = new ServletException (e.getMessage());
	    		m_logger.error(e.getMessage());
				StdOutErrLog.tieSystemOutAndErrToLog();
				e.printStackTrace();
	    		throw exeption;
			}
		}
		else {
			ServletException exeption = new ServletException (i18n.tr("Error! Invalid request: ") + _request.getPathInfo());
    		m_logger.error(exeption.getMessage());
			StdOutErrLog.tieSystemOutAndErrToLog();
			exeption.printStackTrace();
    		throw exeption;
		}
    }
    
    /**
	 * Process the GET HTTP request.
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	process(request, response);
    }
    
    /**
     * Process the POST HTTP request.
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	process(request, response);
    }
}
