/**
 *  ApiProcessorInjectTest.java
 *  coreserver
 *
 *  Created by ericpinet on 2015-10-18.
 *  Copyright (c) 2015 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.connectlife.test.coreserver.apiserver;

// external
import com.google.inject.AbstractModule;

// internal
import com.connectlife.coreserver.environment.Environment;
import com.connectlife.test.coreserver.environment.EnvironmentMock;

/**
 * 
 * 
 * @author ericpinet
 * <br> 2015-10-18
 */
public class ApiProcessorInjectTest  extends AbstractModule {

	/**
	 * 
	 * @see com.google.inject.AbstractModule#configure()
	 */
	@Override
	protected void configure() {
		bind(Environment.class).to(EnvironmentMock.class);
	}

}
