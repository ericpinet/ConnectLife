/**
 *  EnvironmentInjectTest.java
 *  coreserver
 *
 *  Created by ericpinet on 2015-10-31.
 *  Copyright (c) 2015 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.connectlife.test.coreserver.environment;

import com.connectlife.coreserver.environment.discover.DiscoveryService;
import com.google.inject.AbstractModule;

/**
 * 
 * 
 * @author ericpinet
 * <br> 2015-10-31
 */
public class EnvironmentInjectTest extends AbstractModule {

	/**
	 * 
	 * @see com.google.inject.AbstractModule#configure()
	 */
	@Override
	protected void configure() {
		bind(DiscoveryService.class).to(DiscoveryServiceMock.class);
	}

}