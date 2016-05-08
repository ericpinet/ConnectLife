/**
 *  FindProcessorReadWrite.java
 *  coreserver
 *
 *  Created by ericpinet on 2016-02-07.
 *  Copyright (c) 2016 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.connectlife.coreserver.environment.find;

import com.clapi.data.Data;

/**
 * Find processor in read/write mode on the data environment.
 * 
 * @author ericpinet
 * <br> 2016-02-07
 */
public class FindProcessorReadWrite extends FindProcessor {
	
	/**
	 * Default constructor.
	 * 
	 * @param _data Data use in the FindProcessor.
	 */
	public FindProcessorReadWrite(Data _data){
		m_data = _data;
	}
}
