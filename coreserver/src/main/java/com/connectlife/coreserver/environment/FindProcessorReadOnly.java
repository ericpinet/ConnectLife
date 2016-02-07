/**
 *  FindProcessorReadOnly.java
 *  coreserver
 *
 *  Created by ericpinet on 2016-02-07.
 *  Copyright (c) 2016 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.connectlife.coreserver.environment;

import com.clapi.data.Data;
import com.rits.cloning.Cloner;

/**
 * 
 * 
 * @author ericpinet
 * <br> 2016-02-07
 */
public class FindProcessorReadOnly extends FindProcessor {

	/**
	 * Default constructor.
	 * 
	 * @param _data Data use in the FindProcessor.
	 */
	public FindProcessorReadOnly(Data _data){
		Cloner cloner = new Cloner();
		m_data = cloner.deepClone(_data);
	}
}
