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
	public static final String VERSION = "0.0.0.10";
	
	
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
				"create table CONFIG (section string, item string, type string, value string)",
		};
	
	/**
	 * All query to recreate default data
	 * 
	 * @return Return all query to build config data in database.
	 */
	public static String[] CREATE_DATA(){
		String[] ret_strings = new String[Consts.ItemConfig.length];
		
		// CONFIG TABLE
		final int column_config_section = 0;
		final int column_config_item 	= 1;
		final int column_config_type 	= 2;
		final int column_config_value 	= 3;
		
		for(int i=0 ; i<Consts.ItemConfig.length ; i++){
			
			ret_strings[i] = new String("insert into CONFIG values ('"+ Consts.ItemConfig[i][column_config_section] +"',"
																 + "'"+ Consts.ItemConfig[i][column_config_item] +"',"
																 + "'"+ Consts.ItemConfig[i][column_config_type] +"',"
																 + "'"+ Consts.ItemConfig[i][column_config_value] +"')");
		}
		
		return ret_strings;
	}
	
}
