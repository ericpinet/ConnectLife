/**
 *  CmdAddRoom.java
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

import com.clapi.data.Room;
import com.clapi.data.Zone;
import com.connectlife.coreserver.Consts;
import com.connectlife.coreserver.environment.UIDGenerator;
import com.connectlife.coreserver.environment.data.DataManagerNodeFactory;

/**
 * Command to add a new room in the environment.
 * 
 * @author ericpinet
 * <br> 2016-03-28
 */
public class CmdAddRoom extends CmdDefault {
	
	/**
	 * Logger use for this class.
	 */
	private static Logger m_logger = LogManager.getLogger(CmdAddRoom.class);
	
	/**
	 * Room to add in the environment.
	 */
	private Room m_room;
	
	/**
	 * Zone where add in the environment.
	 */
	private Zone m_zone;
	
	/**
	 * Default constructor.
	 *  
	 * @param _room Room to add in the environment.
	 * @param _zone Zone where add the room.
	 */
	public CmdAddRoom (Room _room, Zone _zone){
		m_room = _room;
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
		if( null == m_room ){
			m_logger.error("Error! It's not possible to add null room in the environment.");
			throw new Exception ("Error! It's not possible to add null room in the environment.");
		}
		
		if( false == m_room.getUid().isEmpty() ){
			m_logger.error("Error! It's not possible to add a room with a UID.");
			throw new Exception ("Error! It's not possible to add a room with a UID.");
		}
		
		if (null == m_zone) {
			m_logger.error("Error! It's not possible to add room in a null zone in the environment.");
			throw new Exception ("Error! It's not possible to add room in a null zone in the environment.");
		}
		
		// get the graph data
		GraphDatabaseService graph = m_context.getDataManager().getGraph();
		
		// begin transaction
		try ( Transaction tx = graph.beginTx() ) {
			
			Node node_zone = graph.findNode( Consts.LABEL_ZONE, 
											 Consts.UID, 
											 m_zone.getUid() );

			if (null != node_zone) {
						
				// create the uid for the room
				m_room.setUid(UIDGenerator.getUID());
				
				// build room node
				Node node = DataManagerNodeFactory.buildRoomNode(graph, m_room);
				
				// create relationship
				node_zone.createRelationshipTo(node, Consts.RelTypes.CONTAINS);
				
				// display info in log
				m_logger.info(m_room.toString());
				
				// set the data change
				this.m_data_is_changed = true;
			}
			else {
				m_logger.error("Zone not found. " + m_zone.toString());
				throw new Exception("Zone not found. " + m_zone.toString());
			}

			tx.success();
		}
		
		m_logger.info("Execution completed.");
	}	
}
