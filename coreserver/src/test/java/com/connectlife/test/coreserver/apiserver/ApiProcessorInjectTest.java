/**
 *  ApiProcessorInjectTest.java
 *  coreserver
 *
 *  Created by ericpinet on 2016-02-20.
 *  Copyright (c) 2016 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.connectlife.test.coreserver.apiserver;

import com.connectlife.coreserver.environment.Environment;
import com.connectlife.coreserver.environment.device.DeviceManager;
import com.connectlife.coreserver.environment.discover.DiscoveryService;
import com.connectlife.test.coreserver.environment.DiscoveryServiceMock;
import com.connectlife.test.coreserver.environment.EnvironmentMock;
import com.connectlife.test.coreserver.environment.ServiceManagerMock;
import com.google.inject.AbstractModule;

/**
 * 
 * 
 * @author ericpinet
 * <br> 2016-02-20
 */
public class ApiProcessorInjectTest extends AbstractModule {

	/**
	 * 
	 * @see com.google.inject.AbstractModule#configure()
	 */
	@Override
	protected void configure() {
		bind(DiscoveryService.class).to(DiscoveryServiceMock.class);
		bind(DeviceManager.class).to(ServiceManagerMock.class);
		bind(Environment.class).to(EnvironmentMock.class);
	}

}
