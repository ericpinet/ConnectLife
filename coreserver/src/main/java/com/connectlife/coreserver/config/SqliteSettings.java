/**
 *  SqliteSettings.java
 *  coreserver
 *
 *  Created by ericpinet on 2015-12-12.
 *  Copyright (c) 2015 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.connectlife.coreserver.config;

/**
 * Contains the configuration of the ConfigSqlite. 
 * 
 * @author ericpinet
 * <br> 2015-12-12
 */
public interface SqliteSettings {
	
	/**
	 * Return the database file name from App base path. 
	 * @return Database file name. 
	 */
	abstract public String getDatabaseFileName();
	
	/**
	 * Return the database timeout (sec).
	 * @return Timeout in sec.
	 */
	abstract public int getDatabaseTimeout();

}
