/**
 *  ExecutionMode.java
 *  coreserver
 *
 *  Created by ericpinet on 2015-12-13.
 *  Copyright (c) 2015 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.connectlife.coreserver.tools.execution;

/**
 * Class use to check if the application run in debug mode.
 * 
 * @author ericpinet
 * <br> 2015-12-13
 */
public abstract class ExecutionMode {
	
	/**
	 * Check if the application is use in debug mode.
	 * @return True if the application is executed in debug mode.
	 */
	public static boolean isDebug(){	    
		// if local or remote debug return true
		return java.lang.management.ManagementFactory.getRuntimeMXBean().getInputArguments().toString().indexOf("jdwp") >= 0;
	}
}
