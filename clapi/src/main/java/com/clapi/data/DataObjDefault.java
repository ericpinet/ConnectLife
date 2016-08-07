/**
 *  DataObjDefault.java
 *  clapi
 *
 *  Created by ericpinet on 2016-08-06.
 *  Copyright (c) 2016 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.clapi.data;

import com.google.gson.Gson;

/**
 * Default object data
 * 
 * @author ericpinet
 * <br> 2016-08-06
 */
public class DataObjDefault {
	
	/**
	 * Return a JSON representation of the object.
	 * 
	 * @return JSON string.
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		Gson gson = new Gson();
		String json = gson.toJson(this);
		return json;
	}

}
