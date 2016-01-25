/**
 *  ConsoleInjectTest.java
 *  coreserver
 *
 *  Created by ericpinet on 2015-10-31.
 *  Copyright (c) 2015 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.connectlife.test.coreserver.console;

import com.connectlife.coreserver.config.Config;
import com.google.inject.AbstractModule;

/**
 * 
 * 
 * @author ericpinet
 * <br> 2015-10-31
 */
public class ConsoleInjectTest extends AbstractModule {

	/**
	 * 
	 * @see com.google.inject.AbstractModule#configure()
	 */
	@Override
	protected void configure() {
		bind(Config.class).to(ConfigMock.class);
	}

}
