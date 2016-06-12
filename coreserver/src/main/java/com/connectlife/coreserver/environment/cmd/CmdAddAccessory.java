/**
 *  CmdAddAccessory.java
 *  coreserver
 *
 *  Created by ericpinet on 2016-03-28.
 *  Copyright (c) 2016 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.connectlife.coreserver.environment.cmd;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;

import com.clapi.data.Accessory;
import com.clapi.data.Room;
import com.connectlife.coreserver.Consts;
import com.connectlife.coreserver.environment.UIDGenerator;
import com.connectlife.coreserver.environment.data.DataManagerNodeFactory;

/**
 * Command to add a new accessory in the environment.
 * 
 * @author ericpinet
 * <br> 2016-03-28
 */
public class CmdAddAccessory extends CmdDefault {
	
	/**
	 * Logger use for this class.
	 */
	private static Logger m_logger = LogManager.getLogger(CmdAddAccessory.class);
	
	/**
	 * Person to add in the environment.
	 */
	private Accessory m_accessory;
	
	/**
	 * Room where to add the accessory.
	 */
	private Room m_room;
	
	/**
	 * Default constructor.
	 *  
	 * @param _accessory Accessory to add in the environment.
	 * @param _room Room where add the accessory.
	 */
	public CmdAddAccessory (Accessory _accessory, Room _room){
		m_accessory = _accessory;
		m_room = _room;
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
		if( null == m_accessory ){
			m_logger.error("Error! It's not possible to add null accessory in the environment.");
			throw new Exception ("Error! It's not possible to add null accessory in the environment.");
		}
		
		if( false == m_accessory.getUid().isEmpty() ){
			m_logger.error("Error! It's not possible to add a accessory with a UID.");
			throw new Exception ("Error! It's not possible to add a accessory with a UID.");
		}
		
		// get the graph data
		GraphDatabaseService graph = m_context.getDataManager().getGraph();
		
		// begin transaction
		try ( Transaction tx = graph.beginTx() ) {
			
			// find the accessory by the serial number.
			Node node_acc = graph.findNode( Consts.LABEL_ACCESSORY, 
											Consts.ACCESSORY_SERIALNUMBER, 
											m_accessory.getSerialnumber() );
			
			Node node_room = graph.findNode( Consts.LABEL_ROOM, 
											 Consts.UID, 
											 m_room.getUid() );
			
			// check if accessory wasn't present in environment
			if (null == node_acc) {
				
				// check if room exist
				if (null != node_room) {
					
					// create the uid for the accessory
					m_accessory.setUid(UIDGenerator.getUID());
					
					// build accessory node
					Node node = DataManagerNodeFactory.buildAccessoryNode(graph, m_accessory);
					
					// create relationship
					node_room.createRelationshipTo(node, Consts.RelTypes.CONTAINS);
					
					// set the data change
					this.m_data_is_changed = true;
					
				}
				else {
					m_logger.error("Room not found ." + m_room.toString());
					throw new Exception("Room not found. " + m_room.toString());
				}
			}
			else{
				m_logger.error("Accessory was already added in a room. Remove the accessory before try again."+m_accessory.toString());
				throw new Exception("Accessory was already added in a room. Remove the accessory before try again.");
			}
			
			tx.success();
		}
		
		m_logger.info("Execution completed.");
	}
	
}
