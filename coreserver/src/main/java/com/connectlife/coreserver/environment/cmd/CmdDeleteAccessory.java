/**
 *  CmdDeleteAccessory.java
 *  coreserver
 *
 *  Created by ericpinet on 2016-06-25.
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

import com.clapi.data.Accessory;
import com.connectlife.coreserver.Application;
import com.connectlife.coreserver.Consts;
import com.connectlife.coreserver.environment.data.DataManagerNodeFactory;
import com.google.common.base.Preconditions;

/**
 * Command to delete an accessory from the environment.
 * 
 * @author ericpinet
 * <br> 2016-06-25
 */
public class CmdDeleteAccessory extends CmdDefault {
	
	/**
	 * Logger use for this class.
	 */
	private static Logger m_logger = LogManager.getLogger(CmdDeleteAccessory.class);
	
	/**
	 * Initialization of translation system.
	 */
	private static I18n i18n = Application.i18n;
	
	/**
	 * Accessory to delete from the environment.
	 */
	private Accessory m_accessory;
	
	/**
	 * Default constructor.
	 *  
	 * @param _accessory Accessory to delete in the environment.
	 */
	public CmdDeleteAccessory (Accessory _accessory){
		m_accessory = _accessory;
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
		
		Preconditions.checkNotNull(m_accessory, i18n.tr("Error! It's not possible to delete null accessory in the environment."));
		
		// get the graph data
		GraphDatabaseService graph = m_context.getDataManager().getGraph();
		
		// begin transaction
		try ( Transaction tx = graph.beginTx() ) {
			
			// find the accessory by the uid.
			Node node_acc = graph.findNode( Consts.LABEL_ACCESSORY, 
											Consts.UID, 
											m_accessory.getUid() );
			
			// check if accessory was present in environment
			if (null != node_acc) {
				
				// delete accessory node
				DataManagerNodeFactory.deleteNodeWithChildren(graph, Consts.LABEL_ACCESSORY, (String)node_acc.getProperty(Consts.UID));
					
				// force a synchronization with device
				m_context.getDeviceManager().forceSynchronizationOfAllDevices();
				
				// set the data change
				this.m_data_is_changed = true;		
			}
			else{
				m_logger.error(i18n.tr("Accessory wasn't found: ")+m_accessory.toString());
				throw new Exception(i18n.tr("Accessory wasn't found: ")+m_accessory.toString());
			}
			
			tx.success();
		}
		
		m_logger.info(i18n.tr("Execution completed."));
	}
	
}
