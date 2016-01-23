/**
 *  ApiGrpcInjectTest.java
 *  coreserver
 *
 *  Created by ericpinet on 2016-01-22.
 *  Copyright (c) 2016 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.connectlife.test.coreserver.apiserver;

import com.clapi.protocol.CLApiGrpc;
import com.connectlife.coreserver.config.Config;
import com.google.inject.AbstractModule;

/**
 * 
 * 
 * @author ericpinet
 * <br> 2016-01-22
 */
public class ApiGrpcInjectTest extends AbstractModule {

	/**
	 * 
	 * @see com.google.inject.AbstractModule#configure()
	 */
	@Override
	protected void configure() {
		bind(Config.class).to(ConfigMock.class);
		bind(CLApiGrpc.CLApi.class).to(CLApiGrpcMock.class);
	}

}
