/**
 *  CmdDeleteRoom.java
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
import org.xnap.commons.i18n.I18n;

import com.clapi.data.Room;
import com.connectlife.coreserver.Application;
import com.connectlife.coreserver.Consts;
import com.connectlife.coreserver.environment.data.DataManagerNodeFactory;
import com.google.api.client.util.Preconditions;

/**
 * Command to delete a room from the environment.
 * 
 * @author ericpinet
 * <br> 2016-06-25
 */
public class CmdDeleteRoom extends CmdDefault {
	
	/**
	 * Logger use for this class.
	 */
	private static Logger m_logger = LogManager.getLogger(CmdDeleteRoom.class);
	
	/**
	 * Initialization of translation system.
	 */
	private static I18n i18n = Application.i18n;
	
	/**
	 * Room to delete from the environment.
	 */
	private Room m_room;
	
	/**
	 * Default constructor.
	 *  
	 * @param _room Room to delete from the environment.
	 */
	public CmdDeleteRoom (Room _room){
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
		
		m_logger.info(i18n.tr("Execution start ..."));
		
		Preconditions.checkNotNull(m_room, i18n.tr("Error! It's not possible to delete null room in the environment."));
		Preconditions.checkArgument(false == m_room.getUid().isEmpty(), i18n.tr("Error! It's not possible to delete a room with a empty UID."));
		
		// get the graph data
		GraphDatabaseService graph = m_context.getDataManager().getGraph();
		
		// begin transaction
		try ( Transaction tx = graph.beginTx() ) {
			
			// find the room by the uid.
			Node node_acc = graph.findNode( Consts.LABEL_ROOM,
											Consts.UID,
											m_room.getUid() );
			
			// check if room was present in environment
			if (null != node_acc) {
				
				// delete room node
				DataManagerNodeFactory.deleteNodeWithChildren(graph, Consts.LABEL_ROOM, (String)node_acc.getProperty(Consts.UID));
					
				// force a synchronization with device
				m_context.getDeviceManager().forceSynchronizationOfAllDevices();
				
				// display info in log
				m_logger.info(m_room.toString());
				
				// set the data change
				this.m_data_is_changed = true;		
			}
			else{
				m_logger.error(i18n.tr("Room wasn't found: ")+m_room.toString());
				throw new Exception(i18n.tr("Room wasn't found: ")+m_room.toString());
			}
			
			tx.success();
		}
		
		m_logger.info(i18n.tr("Execution completed."));
	}
	
}
