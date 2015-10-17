/**
 *  Api.java
 *  coreserver
 *
 *  Created by ericpinet on 2015-10-16.
 *  Copyright (c) 2015 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.connectlife.coreserver.apiserver;

// external
import com.connectlife.clapi.Notification;

// internal

/**
 * Api server of the coreserver. This class is main api server interface
 * for client (Mobile Application).
 * 
 * @author ericpinet
 * <br> 2015-10-16
 */
public interface Api {
	
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
	
	/**
	 * Send notification to all client connected.
	 * 
	 * @param _notification Notification to send at client.
	 */
	public void sendNotificationAllClient(Notification _notification);

}
