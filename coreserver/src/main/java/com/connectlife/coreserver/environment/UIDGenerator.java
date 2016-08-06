/**
 *  UIDGenerator.java
 *  coreserver
 *
 *  Created by ericpinet on 2015-09-10.
 *  Copyright (c) 2015 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.connectlife.coreserver.environment;

// external
import java.util.UUID;

// internal

/**
 * Generate UID for object in the system.
 * 
 * @author ericpinet
 * <br> 2015-09-10
 */
public class UIDGenerator {
	
	/**
	 * Generate a new UID for an object in the system.
	 * @return UID representation for an object.
	 */
	public static String getUID(){
		UUID uid = UUID.randomUUID();
		return uid.toString();
	}
	
	/**
	 * Set the constructor private to block instantiation of this class.
	 */
	private UIDGenerator(){
		
	}

}
