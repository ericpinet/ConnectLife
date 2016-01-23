/**
 *  SqliteSettingsApp.java
 *  coreserver
 *
 *  Created by ericpinet on 2015-12-12.
 *  Copyright (c) 2015 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.connectlife.coreserver.config;

/**
 * Configuration for the ConfigSqlite for the application.
 * 
 * @author ericpinet
 * <br> 2015-12-12
 */
public class SqliteSettingsApp implements SqliteSettings {
	
	/**
	 * Path of the database from the application base path.
	 */
	private static final String DATABASE_PATH = "data";
	
	/**
	 * Database filename
	 */
	private static final String DATABASE_FILE = "config.sqlite";
	
	/**
	 * Database timeout (sec)
	 */ 
	private static final int DATABASE_TIMEOUT = 30;

	/**
	 * @return The database file path.
	 * @see com.connectlife.coreserver.config.SqliteSettings#getDatabasePath()
	 */
	@Override
	public String getDatabasePath() {
		return DATABASE_PATH;
	}
	
	/**
	 * @return The database file name.
	 * @see com.connectlife.coreserver.config.SqliteSettings#getDatabaseFileName()
	 */
	@Override
	public String getDatabaseFileName() {
		return DATABASE_FILE;
	}

	/**
	 * @return The database timeout.
	 * @see com.connectlife.coreserver.config.SqliteSettings#getDatabaseTimeout()
	 */
	@Override
	public int getDatabaseTimeout() {
		return DATABASE_TIMEOUT;
	}

	
}
