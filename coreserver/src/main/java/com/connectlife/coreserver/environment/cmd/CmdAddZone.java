/**
 *  CmdAddZone.java
 *  coreserver
 *
 *  Created by ericpinet on 2016-06-30.
 *  Copyright (c) 2016 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.connectlife.coreserver.environment.cmd;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Transaction;

import com.clapi.data.Zone;
import com.connectlife.coreserver.environment.UIDGenerator;
import com.connectlife.coreserver.environment.data.DataManagerNodeFactory;

/**
 * Command to add a new zone in the environment.
 * 
 * @author ericpinet
 * <br> 2016-03-28
 */
public class CmdAddZone extends CmdDefault {
	
	/**
	 * Logger use for this class.
	 */
	private static Logger m_logger = LogManager.getLogger(CmdAddZone.class);
	
	/**
	 * Zone to add in the environment.
	 */
	private Zone m_zone;
	
	/**
	 * Default constructor.
	 *  
	 * @param _zone Zone to add in the environment.
	 */
	public CmdAddZone (Zone _zone){
		m_zone = _zone;
	}
	
	/**
	 * Execute the cmd on the environment.
	 * 
	 * @throws Exception Exception when something goes wrong.
	 * @see com.connectlife.coreserver.environment.cmd.Cmd#execute()
	 */
	@Override
	public void execute() throws Exception {
		
		m_logger.info("Execution start ...");
		
		// check the room to add in the environment
		if( null == m_zone ){
			m_logger.error("Error! It's not possible to add null zone in the environment.");
			throw new Exception ("Error! It's not possible to add null zone in the environment.");
		}
		
		if( false == m_zone.getUid().isEmpty() ){
			m_logger.error("Error! It's not possible to add a zone with a UID.");
			throw new Exception ("Error! It's not possible to add a zone with a UID.");
		}
		
		// get the graph data
		GraphDatabaseService graph = m_context.getDataManager().getGraph();
		
		// begin transaction
		try ( Transaction tx = graph.beginTx() ) {
						
			// create the uid for the zone
			m_zone.setUid(UIDGenerator.getUID());
			
			// build zone node
			DataManagerNodeFactory.buildZoneNode(graph, m_zone);
			
			// display info in log
			m_logger.info(m_zone.toString());
			
			// set the data change
			this.m_data_is_changed = true;

			tx.success();
		}
		
		m_logger.info("Execution completed.");
	}	
}
