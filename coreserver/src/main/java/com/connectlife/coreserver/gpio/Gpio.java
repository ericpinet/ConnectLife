/**
 *  Gpio.java
 *  coreserver
 *
 *  Created by ericpinet on 2015-11-04.
 *  Copyright (c) 2015 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.connectlife.coreserver.gpio;

// external

// internal

/**
 * GPIO module is use to control the GPIO. 
 * 
 * @author ericpinet
 * <br> 2015-11-04
 */
public interface Gpio {
	
	/**
	 * Initialization of the module
	 * 
	 * @return True if initialization completed correctly.
	 */
	public boolean init();
	
	/**
	 * Return if the module is correctly running.
	 * 
	 * @return True if the module is correctly running.
	 */
	public boolean isInit();
	
	/**
	 * Unload the module. After, it's possible to init again. 
	 */
	public void unInit();
	

}
