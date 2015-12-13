/**
 *  ConfigSqliteInjectTest.java
 *  coreserver
 *
 *  Created by ericpinet on 2015-12-13.
 *  Copyright (c) 2015 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.connectlife.test.coreserver.config;

import com.connectlife.coreserver.config.Config;
import com.connectlife.coreserver.config.ConfigSqlite;
import com.connectlife.coreserver.config.SqliteSettings;
import com.google.inject.AbstractModule;

/**
 * 
 * 
 * @author ericpinet
 * <br> 2015-12-13
 */
public class ConfigSqliteInjectTest extends AbstractModule{

	/**
	 * 
	 * @see com.google.inject.AbstractModule#configure()
	 */
	@Override
	protected void configure() {
		bind(SqliteSettings.class).to(SqliteSettingsTest.class);
		bind(Config.class).to(ConfigSqlite.class);
	}

}
