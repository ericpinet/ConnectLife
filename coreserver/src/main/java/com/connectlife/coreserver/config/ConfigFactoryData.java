/**
 *  ConfigFactoryData.java
 *  coreserver
 *
 *  Created by ericpinet on 2015-12-12.
 *  Copyright (c) 2015 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.connectlife.coreserver.config;

/**
 * This class contain the factory configuration data. 
 * This data was useful on factory reset configuration settings.
 * 
 * @author ericpinet
 * <br> 2015-12-12
 */
public class ConfigFactoryData {
	
	/**
	 * Type string 
	 */
	public static final String CONFIG_TYPE_STRING = "string";
	
	/**
	 * Type integer
	 */
	public static final String CONFIG_TYPE_INTEGER = "integer";
	
	/**
	 * Default configuration for the application.
	 * Adding all config and it's default value and type here. This config will be created automatically in configuration manager.
	 */
	public static String [][] ItemConfig = {
            {"APISERVER" , 		"TCPIP_PORT" , 			CONFIG_TYPE_INTEGER,			"9006"},
            {"APISERVER" , 		"TCPIP_PORT_SECURE" , 	CONFIG_TYPE_INTEGER,			"9007"},
            {"APISERVER" , 		"ADMIN_USERNAME", 		CONFIG_TYPE_STRING,				"admin"},
            {"APISERVER" , 		"ADMIN_PASSWORD", 		CONFIG_TYPE_STRING,				"admin"},
            {"CONSOLE" , 		"TCPIP_PORT" , 			CONFIG_TYPE_INTEGER,			"9008"},
            {"CONSOLE" , 		"ADMIN_USERNAME", 		CONFIG_TYPE_STRING,				"admin"},
            {"CONSOLE" , 		"ADMIN_PASSWORD", 		CONFIG_TYPE_STRING,				"admin"}
       };

}
