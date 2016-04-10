/**
 *  DataObj.java
 *  clapi
 *
 *  Created by ericpinet on 2016-04-09.
 *  Copyright (c) 2016 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.clapi.data;

import java.util.List;

/**
 * Generic interface for simplify the search.
 * 
 * @author ericpinet
 * <br> 2016-04-09
 */
public interface DataObj {
	
	public String getUid();
	
	public List<DataObj> getChildren();
	
}
