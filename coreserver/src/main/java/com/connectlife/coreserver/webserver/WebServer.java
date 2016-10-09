/**
 *  WebServer.java
 *  coreserver
 *
 *  Created by ericpinet on 2016-10-09.
 *  Copyright (c) 2015 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.connectlife.coreserver.webserver;

/**
 * Web server of the coreserver. This class is main web server interface.
 * 
 * @author ericpinet
 * <br> 2016-10-09
 */
public interface WebServer {
	
	/**
	 * Initialization of the module
	 * 
	 * @return True if initialization completed correctly.
	 */
	public boolean init();
	
	/**
	 * Return if the module is correctly running.
	 * 
	 * @return True if the module is correctly running.
	 */
	public boolean isInit();
	
	/**
	 * Unload the module. After, it's possible to init again. 
	 */
	public void unInit();

}
