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
	 * Enum of all modules include in coreserver application.
	 */
	public enum ModuleUID {
		API_SERVER, DATA_MANAGER
	}
	
	/**
	 * Default configuration for the database
	 */
	public static final String API_SERVER_TCPIP_PORT_CONFIG_NAME 			= "API_SERVER_TCPIP_PORT"; 
	public static final String API_SERVER_TCPIP_PORT_CONFIG_DEFAULT_VALUE 	= "9006";
	
	public static final String API_SERVER_HOSTNAME_CONFIG_NAME 				= "API_SERVER_HOSTNAME"; 
	public static final String API_SERVER_HOSTNAME_CONFIG_DEFAULT_VALUE 	= "localhost";
	
	public static final String API_SERVER_BACKLOG_CONFIG_NAME 				= "API_SERVER_BACKLOG"; 
	public static final String API_SERVER_BACKLOG_CONFIG_DEFAULT_VALUE 		= "1";
	
	public static final String DATABASE_TIMEOUT_CONFIG_NAME 				= "DATABASE_TIMEOUT"; 	// Seconds
	public static final String DATABASE_TIMEOUT_CONFIG_DEFAULT_VALUE 		= "30";
	
	
	
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
