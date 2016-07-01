/**
 *  CmdUpdateHome.java
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
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;

import com.clapi.data.Home;
import com.connectlife.coreserver.Consts;
import com.connectlife.coreserver.environment.data.DataManagerNodeFactory;

/**
 * Command to update a home in the environment.
 * 
 * @author ericpinet
 * <br> 2016-03-28
 */
public class CmdUpdateHome extends CmdDefault {
	
	/**
	 * Logger use for this class.
	 */
	private static Logger m_logger = LogManager.getLogger(CmdUpdateHome.class);
	
	/**
	 * Home to update in the environment.
	 */
	private Home m_home;
	
	/**
	 * Default constructor.
	 *  
	 * @param _home Home to update in the environment.
	 */
	public CmdUpdateHome (Home _home){
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
		
		// check the accessory
		if (null == m_home) {
			m_logger.error("Error! It's not possible to update null home in the environment.");
			throw new Exception ("Error! It's not possible to update null home in the environment.");
		}
		
		// get the graph data
		GraphDatabaseService graph = m_context.getDataManager().getGraph();
		
		// find the zone by the uid.
		try ( Transaction tx = graph.beginTx() ) {
			
			Node node_acc = graph.findNode( Consts.LABEL_HOME, 
											Consts.UID, 
											m_home.getUid() );
			
			if (null != node_acc) {
				
				// Update the data in the graph
				DataManagerNodeFactory.updateHomeNode(graph, node_acc, m_home);
				
				// display info in log
				m_logger.info(m_home.toString());
			
				// set the data change
				this.m_data_is_changed = true;
				
			}
			else {
				m_logger.error("Home not found. " + m_home.toString());
				throw new Exception("Home not found. " + m_home.toString());
			}
			
			tx.success();
		}
		
		m_logger.info("Execution completed.");
	}
	
	/**
	 * Return the home.
	 * 
	 * @return Home.
	 */
	public Home getHome(){
		return m_home;
	}
	
}
