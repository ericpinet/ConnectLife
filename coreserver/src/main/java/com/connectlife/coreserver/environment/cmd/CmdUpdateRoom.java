/**
 *  CmdUpdateRoom.java
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
import com.connectlife.coreserver.Consts;
import com.connectlife.coreserver.environment.data.DataManagerNodeFactory;

/**
 * Command to update a room in the environment.
 * 
 * @author ericpinet
 * <br> 2016-03-28
 */
public class CmdUpdateRoom extends CmdDefault {
	
	/**
	 * Logger use for this class.
	 */
	private static Logger m_logger = LogManager.getLogger(CmdUpdateRoom.class);
	
	/**
	 * Room to update in the environment.
	 */
	private Room m_room;
	
	/**
	 * Default constructor.
	 *  
	 * @param _room Room to update in the environment.
	 */
	public CmdUpdateRoom (Room _room){
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
		
		// check the accessory
		if (null == m_room) {
			m_logger.error("Error! It's not possible to update null room in the environment.");
			throw new Exception ("Error! It's not possible to update null room in the environment.");
		}
		
		// get the graph data
		GraphDatabaseService graph = m_context.getDataManager().getGraph();
		
		// find the room by the uid.
		try ( Transaction tx = graph.beginTx() ) {
			
			Node node_acc = graph.findNode( Consts.LABEL_ROOM, 
											Consts.UID, 
											m_room.getUid() );
			
			if (null != node_acc) {
				
				// Update the data in the graph
				DataManagerNodeFactory.updateRoomNode(graph, node_acc, m_room);
			
				// display info in log
				m_logger.info(m_room.toString());
				
				// set the data change
				this.m_data_is_changed = true;
				
			}
			else {
				m_logger.error("Room not found. " + m_room.toString());
				throw new Exception("Room not found. " + m_room.toString());
			}
			
			tx.success();
		}
		
		m_logger.info("Execution completed.");
	}
	
	/**
	 * Return the room.
	 * 
	 * @return Room.
	 */
	public Room getRoom(){
		return m_room;
	}
	
}