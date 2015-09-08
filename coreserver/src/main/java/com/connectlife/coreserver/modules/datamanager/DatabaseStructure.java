/**
 *  DatabaseStructure.java
 *  coreserver
 *
 *  Created by Eric Pinet <pineri01@gmail.com> on 2015-09-07.
 *  Copyright (c) 2015 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.connectlife.coreserver.modules.datamanager;

import com.connectlife.coreserver.Consts;

/**
 * This class contain all the database structure.
 * 
 * @author Eric Pinet (pineri01@gmail.com)
 * <br> 2015-09-07
 */
public class DatabaseStructure {

	/**
	 * Version of the latest database. If the version isn't equal with the database file, the database will be recreated.
	 */
	public static final String VERSION = "1.0.0.0";
	
	
	/**
	 * All query to drops all tables in database.
	 */
	public static final String[] DROP_TABLES = 
		{
				"drop table if exists VERSION",		// VERSION
				"drop table if exists CONFIG"		// CONFIG
		};
	
	/**
	 * All query to recreate default database
	 */
	public static final String[] CREATE_TABLES = 
		{
				// VERSION
				"create table VERSION (number string)",
				"insert into VERSION values('"+DatabaseStructure.VERSION+"')",
				
				// CONFIG
				"create table CONFIG (item string, value string)",
				"insert into CONFIG values('"+Consts.API_SERVER_HOSTNAME_CONFIG_NAME+"', '"+Consts.API_SERVER_HOSTNAME_CONFIG_DEFAULT_VALUE+"')", 		// AgiServer - Hostname
				"insert into CONFIG values('"+Consts.API_SERVER_TCPIP_PORT_CONFIG_NAME+"', '"+Consts.API_SERVER_TCPIP_PORT_CONFIG_DEFAULT_VALUE+"')", 	// AgiServer - TCPIP Port
				"insert into CONFIG values('"+Consts.API_SERVER_BACKLOG_CONFIG_NAME+"', '"+Consts.API_SERVER_BACKLOG_CONFIG_DEFAULT_VALUE+"')", 		// AgiServer - Backlog
				"insert into CONFIG values('"+Consts.DATABASE_TIMEOUT_CONFIG_NAME+"', '"+Consts.DATABASE_TIMEOUT_CONFIG_DEFAULT_VALUE+"')" 				// Database - Timeout (sec)
		};
	
}
