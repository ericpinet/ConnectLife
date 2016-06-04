/**
 *  FindProcessorReadOnly.java
 *  coreserver
 *
 *  Created by ericpinet on 2016-02-07.
 *  Copyright (c) 2016 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.connectlife.coreserver.environment.find;

import org.neo4j.graphdb.GraphDatabaseService;

/**
 * Find processor read only.
 * 
 * @author ericpinet
 * <br> 2016-02-07
 */
public class FindProcessorReadOnly extends FindProcessor {

	/**
	 * Default constructor.
	 * 
	 * @param _graph Graph data use in the FindProcessor.
	 */
	public FindProcessorReadOnly(GraphDatabaseService _graph){
		m_graph = _graph;
	}
}
