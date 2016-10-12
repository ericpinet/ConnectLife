/**
 *  RequestFactory.java
 *  coreserver
 *
 *  Created by ericpinet on 11 oct. 2016.
 *  Copyright (c) 2016 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.connectlife.coreserver.webserver.request;

import javax.servlet.http.HttpServletRequest;

/**
 * Request factory. Use this factory to build request. 
 * 
 * @author ericpinet
 * <br> 11 oct. 2016
 */
public class RequestFactory {
	
	/**
	 * Build the request to answer the request.
	 * 
	 * @param _request
	 * @return
	 */
	public static RequestBase getRequest(HttpServletRequest _request) {
		
		return new RequestListServices();
	}
}
