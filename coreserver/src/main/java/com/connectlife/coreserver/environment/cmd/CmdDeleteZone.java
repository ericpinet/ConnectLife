/**
 *  CmdDeleteZone.java
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

import com.clapi.data.Zone;
import com.connectlife.coreserver.Consts;
import com.connectlife.coreserver.environment.data.DataManagerNodeFactory;
import com.google.api.client.util.Preconditions;

/**
 * Command to delete a zone from the environment.
 * 
 * @author ericpinet
 * <br> 2016-06-25
 */
public class CmdDeleteZone extends CmdDefault {
	
	/**
	 * Logger use for this class.
	 */
	private static Logger m_logger = LogManager.getLogger(CmdDeleteZone.class);
	
	/**
	 * Zone to delete from the environment.
	 */
	private Zone m_zone;
	
	/**
	 * Default constructor.
	 *  
	 * @param _zone Zone to delete from the environment.
	 */
	public CmdDeleteZone (Zone _zone){
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
		
		Preconditions.checkNotNull(m_zone, "Error! It's not possible to delete null zone in the environment.");
		Preconditions.checkArgument(false == m_zone.getUid().isEmpty(), "Error! It's not possible to delete a zone with a empty UID.");
		
		// get the graph data
		GraphDatabaseService graph = m_context.getDataManager().getGraph();
		
		// begin transaction
		try ( Transaction tx = graph.beginTx() ) {
			
			// find the zone by the uid.
			Node node_acc = graph.findNode( Consts.LABEL_ZONE, 
											Consts.UID, 
											m_zone.getUid() );
			
			// check if zone was present in environment
			if (null != node_acc) {
				
				// delete zone node
				DataManagerNodeFactory.deleteNodeWithChildren(graph, Consts.LABEL_ZONE, (String)node_acc.getProperty(Consts.UID));
					
				// force a synchronization with device
				m_context.getDeviceManager().forceSynchronizationOfAllDevices();
				
				// display info in log
				m_logger.info(m_zone.toString());
				
				// set the data change
				this.m_data_is_changed = true;		
			}
			else{
				m_logger.error("Zone wasn't found. "+m_zone.toString());
				throw new Exception("Zone wasn't found. "+m_zone.toString());
			}
			
			tx.success();
		}
		
		m_logger.info("Execution completed.");
	}
	
}
