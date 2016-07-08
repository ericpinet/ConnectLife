/**
 *  CmdDeleteHome.java
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
 * Command to delete a home from the environment.
 * 
 * @author ericpinet
 * <br> 2016-06-25
 */
public class CmdDeleteHome extends CmdDefault {
	
	/**
	 * Logger use for this class.
	 */
	private static Logger m_logger = LogManager.getLogger(CmdDeleteHome.class);
	
	/**
	 * Home to delete from the environment.
	 */
	private Home m_home;
	
	/**
	 * Default constructor.
	 *  
	 * @param _home Home to delete from the environment.
	 */
	public CmdDeleteHome (Home _home){
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
		
		// check the accessory to add in the environment
		if( null == m_home ){
			m_logger.error("Error! It's not possible to delete null home in the environment.");
			throw new Exception ("Error! It's not possible to delete null home in the environment.");
		}
		
		// get the graph data
		GraphDatabaseService graph = m_context.getDataManager().getGraph();
		
		// begin transaction
		try ( Transaction tx = graph.beginTx() ) {
			
			// find the home by the uid.
			Node node_acc = graph.findNode( Consts.LABEL_HOME, 
											Consts.UID, 
											m_home.getUid() );
			
			// check if home was present in environment
			if (null != node_acc) {
				
				// delete home node
				DataManagerNodeFactory.deleteNodeWithChildren(graph, Consts.LABEL_HOME, (String)node_acc.getProperty(Consts.UID));
					
				// force a synchronization with device
				m_context.getDeviceManager().forceSynchronizationOfAllDevices();
				
				// display info in log
				m_logger.info(m_home.toString());
				
				// set the data change
				this.m_data_is_changed = true;		
			}
			else{
				m_logger.error("Home wasn't found. "+m_home.toString());
				throw new Exception("Home wasn't found. "+m_home.toString());
			}
			
			tx.success();
		}
		
		m_logger.info("Execution completed.");
	}
	
}
