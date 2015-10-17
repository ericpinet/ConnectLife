/**
 *  ApplicationInject.java
 *  coreserver
 *
 *  Created by ericpinet on 2015-10-10.
 *  Copyright (c) 2015 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.connectlife.coreserver;

// external
import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.connectlife.clapi.CLApi;
import com.connectlife.coreserver.apiserver.Api;
import com.connectlife.coreserver.apiserver.ApiProcessor;
import com.connectlife.coreserver.apiserver.ApiThriftJson;
import com.connectlife.coreserver.configmanager.Config;
import com.connectlife.coreserver.configmanager.ConfigSqliteManager;
import com.connectlife.coreserver.console.Console;
import com.connectlife.coreserver.console.ConsoleSSH;
import com.connectlife.coreserver.environment.Environment;
import com.connectlife.coreserver.environment.EnvironmentJsonFile;

/**
 * Google Guice injection module to create an Application Class.
 * 
 * @author ericpinet
 * <br> 2015-10-10
 */
public class ApplicationInject extends AbstractModule {

	/**
	 * 
	 * @see com.google.inject.AbstractModule#configure()
	 */
	@Override
	protected void configure() {
		bind(Config.class).to(ConfigSqliteManager.class).in(Singleton.class);
		bind(Environment.class).to(EnvironmentJsonFile.class).in(Singleton.class);
		bind(Api.class).to(ApiThriftJson.class);
		bind(Console.class).to(ConsoleSSH.class);
		
		bind(CLApi.Iface.class).to(ApiProcessor.class);
	}

}
