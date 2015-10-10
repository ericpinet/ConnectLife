/**
 *  NotificationListener.java
 *  clapi
 *
 *  Created by ericpinet on 2015-10-09.
 *  Copyright (c) 2015 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.connectlife.clapi.client;

// internal
import com.connectlife.clapi.Notification;


/**
 * Interface for the reception of notification.
 * 
 * @author ericpinet
 * <br> 2015-10-09
 */
public interface NotificationListener {
	
	/**
	 * When notification was receive from server this method will be call. 
	 * 
	 * @param _notification
	 */
	public void notificationReceive(Notification _notification);

}
