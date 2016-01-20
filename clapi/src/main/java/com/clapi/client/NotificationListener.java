/**
 *  NotificationListener.java
 *  clapi
 *
 *  Created by ericpinet on 2015-11-07.
 *  Copyright (c) 2015 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.clapi.client;

import com.clapi.protocol.Notification;

/**
 * NotificationListener for the server notification.
 * 
 * @author ericpinet
 * <br> 2015-11-07
 */
public interface NotificationListener {
	
	/**
	 * When notification was receive from server this method will be call. 
	 * 
	 * @param _notification Notification receive from the server.
	 */
	public void notificationReceive(Notification _notification);

}
