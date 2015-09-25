/**
 *  DiscoveryListner.java
 *  coreserver
 *
 *  Created by ericpinet on 2015-09-18.
 *  Copyright (c) 2015 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.connectlife.coreserver.modules.environment.discover;

import javax.jmdns.ServiceEvent;

/**
 * Interface for DiscoveryManager.
 * 
 * @author ericpinet
 * <br> 2015-09-18
 */
public interface DiscoveryListner {
	
	/**
	 * Callback called when new service is discover.
	 * @param _service Service Information.
	 */
	public void serviceDiscover(ServiceEvent _service);
	
	/**
	 * Callback called when a service is removed.
	 * @param _service Service Information.
	 */
	public void serviceRemove(ServiceEvent _service);

}
