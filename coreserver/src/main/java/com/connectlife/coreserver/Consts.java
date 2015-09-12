/**
 *  Consts.java
 *  coreserver
 *
 *  Created by Eric Pinet <pineri01@gmail.com> on 2015-09-07.
 *  Copyright (c) 2015 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.connectlife.coreserver;

/**
 * This class contain all global constances for the application.
 * 
 * @author Eric Pinet (pineri01@gmail.com)
 * <br> 2015-09-07
 */
public final class Consts {
	
	/**
	 * Application name
	 */
	public static final String APP_NAME = "ConnectLife - CoreServer";
	
	/**
	 * Application version
	 */
	public static final String APP_VERSION = "1.0.0.0";
	
	/**
	 * Database filename (no path include)
	 */
	public static final String DATABASE_FILE = "connectlife-coreserver.db";
	
	/**
	 * Database timeout (sec)
	 */ 
	public static final int DATABASE_TIMEOUT = 30;
	
	/**
	 * Environment data path contain the data representing the user, home, 
	 * zones, rooms, accessories
	 */
	public static final String ENV_DATA_PATH = "data";
	
	/**
	 * Environment file name contain the data representing the user, home, 
	 * zones, rooms, accessories
	 */
	public static final String ENV_DATA_FILENAME = "environnement.data";
	
	/**
	 * Environment file name backup.
	 */
	public static final String ENV_DATA_FILENAME_BACKUP = "environnement.data.bk";
	
	/**
	 * Enum of all modules include in coreserver application.
	 */
	public enum ModuleUID {
		API_SERVER, DATA_MANAGER, ENVIRONMENT_MANAGER, CONSOLE_MANAGER
	}
	
	/**
	 * Type string 
	 */
	public static final String CONFIG_TYPE_STRING = "string";
	
	/**
	 * Type integer
	 */
	public static final String CONFIG_TYPE_INTEGER = "integer";
	
	
	/**
	 * Default configuration for the database
	 * Adding all config and it's default value and type here. This config will be created automatically in database.
	 * If you add new config, change the database version in DatabaseStructure.
	 */
	public static String [][] ItemConfig = {
            {"APISERVER" , 		"TCPIP_PORT" , 			CONFIG_TYPE_INTEGER,			"9006"},
            {"CONSOLE" , 		"TCPIP_PORT" , 			CONFIG_TYPE_INTEGER,			"9007"},
            {"CONSOLE" , 		"ADMIN_USERNAME", 		CONFIG_TYPE_STRING,				"admin"},
            {"CONSOLE" , 		"ADMIN_PASSWORD", 		CONFIG_TYPE_STRING,				"admin"}
       };
	
	
	
	/**
	   The caller references the constants using <tt>Consts.EMPTY_STRING</tt>, 
	   and so on. Thus, the caller should be prevented from constructing objects of 
	   this class, by declaring this private constructor. 
	  */
	  private Consts(){
	    //this prevents even the native class from 
	    //calling this constructor as well :
	    throw new AssertionError();
	  }

}
