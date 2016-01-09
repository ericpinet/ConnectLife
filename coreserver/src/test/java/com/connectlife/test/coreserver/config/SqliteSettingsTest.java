/**
 *  SqliteSettingsTest.java
 *  coreserver
 *
 *  Created by ericpinet on 2015-12-13.
 *  Copyright (c) 2015 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.connectlife.test.coreserver.config;

import com.connectlife.coreserver.config.SqliteSettings;

/**
 * 
 * 
 * @author ericpinet
 * <br> 2015-12-13
 */
public class SqliteSettingsTest implements SqliteSettings {
	
	/**
	 * Path of the database from the application base path.
	 */
	private static final String DATABASE_PATH = "data";
	
	/**
	 * Database filename (from app base path)
	 */
	private static final String DATABASE_FILE = "config-test.sqlite";
	
	/**
	 * Database timeout (sec)
	 */ 
	private static final int DATABASE_TIMEOUT = 30;
	
	/**
	 * @return
	 * @see com.connectlife.coreserver.config.SqliteSettings#getDatabasePath()
	 */
	@Override
	public String getDatabasePath() {
		return DATABASE_PATH;
	}

	/**
	 * @return
	 * @see com.connectlife.coreserver.config.SqliteSettings#getDatabaseFileName()
	 */
	@Override
	public String getDatabaseFileName() {
		return DATABASE_FILE;
	}

	/**
	 * @return
	 * @see com.connectlife.coreserver.config.SqliteSettings#getDatabaseTimeout()
	 */
	@Override
	public int getDatabaseTimeout() {
		return DATABASE_TIMEOUT;
	}
}
