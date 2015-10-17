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
