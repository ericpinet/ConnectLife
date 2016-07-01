/**
 *  CmdAddHome.java
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

import com.clapi.data.Home;
import com.connectlife.coreserver.environment.UIDGenerator;
import com.connectlife.coreserver.environment.data.DataManagerNodeFactory;

/**
 * Command to add a new home in the environment.
 * 
 * @author ericpinet
 * <br> 2016-03-28
 */
public class CmdAddHome extends CmdDefault {
	
	/**
	 * Logger use for this class.
	 */
	private static Logger m_logger = LogManager.getLogger(CmdAddHome.class);
	
	/**
	 * Home to add in the environment.
	 */
	private Home m_home;
	
	/**
	 * Default constructor.
	 *  
	 * @param _home Home to add in the environment.
	 */
	public CmdAddHome (Home _home){
		m_home = _home;
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
		if( null == m_home ){
			m_logger.error("Error! It's not possible to add null home in the environment.");
			throw new Exception ("Error! It's not possible to add null home in the environment.");
		}
		
		if( false == m_home.getUid().isEmpty() ){
			m_logger.error("Error! It's not possible to add a home with a UID.");
			throw new Exception ("Error! It's not possible to add a home with a UID.");
		}
		
		// get the graph data
		GraphDatabaseService graph = m_context.getDataManager().getGraph();
		
		// begin transaction
		try ( Transaction tx = graph.beginTx() ) {
						
			// create the uid for the home
			m_home.setUid(UIDGenerator.getUID());
			
			// build home node
			DataManagerNodeFactory.buildHomeNode(graph, m_home);
			
			// display info in log
			m_logger.info(m_home.toString());
			
			// set the data change
			this.m_data_is_changed = true;

			tx.success();
		}
		
		m_logger.info("Execution completed.");
	}	
}
