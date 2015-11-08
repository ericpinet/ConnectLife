/**
 *  ApplicationInject.java
 *  coreserver
 *
 *  Created by ericpinet on 2015-10-10.
 *  Copyright (c) 2015 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.connectlife.test.coreserver;

// external
import com.google.inject.AbstractModule;
import com.connectlife.coreserver.apiserver.Api;
import com.connectlife.coreserver.configmanager.Config;
import com.connectlife.coreserver.console.Console;
import com.connectlife.coreserver.environment.Environment;
import com.connectlife.coreserver.environment.discover.DiscoveryService;
import com.connectlife.coreserver.gpio.Gpio;

import com.connectlife.test.coreserver.apiserver.ApiMock;
import com.connectlife.test.coreserver.environment.DiscoveryServiceMock;
import com.connectlife.test.coreserver.environment.EnvironmentMock;


/**
 * 
 * 
 * @author ericpinet
 * <br> 2015-10-10
 */
public class ApplicationInjectTest extends AbstractModule {

	/**
	 * 
	 * @see com.google.inject.AbstractModule#configure()
	 */
	@Override
	protected void configure() {
		bind(Config.class).to(ConfigMock.class);
		bind(Environment.class).to(EnvironmentMock.class);
		bind(Api.class).to(ApiMock.class);
		bind(Console.class).to(ConsoleMock.class);
		bind(Gpio.class).to(GpioMock.class);
		bind(DiscoveryService.class).to(DiscoveryServiceMock.class);
	}
}
