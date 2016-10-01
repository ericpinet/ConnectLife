/**
 *  CmdUnregisterAccessory.java
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
import org.xnap.commons.i18n.I18n;

import com.clapi.data.Accessory;
import com.connectlife.coreserver.Application;
import com.connectlife.coreserver.Consts;
import com.google.api.client.util.Preconditions;

/**
 * Command to unregister a accessory in the environment.
 * 
 * @author ericpinet
 * <br> 2016-03-28
 */
public class CmdUnregisterAccessory extends CmdDefault {
	
	/**
	 * Logger use for this class.
	 */
	private Logger m_logger = LogManager.getLogger(getClass().getName());
	
	/**
	 * Initialization of translation system.
	 */
	private static I18n i18n = Application.i18n;
	
	/**
	 * Person to add in the environment.
	 */
	private Accessory m_accessory;
	
	/**
	 * Default constructor.
	 *  
	 * @param _accessory Accessory to register in the environment.
	 */
	public CmdUnregisterAccessory (Accessory _accessory){
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
		
		Preconditions.checkNotNull(m_accessory, i18n.tr("Error! It's not possible to unregister null accessory in the environment."));
	
		// get the graph data
		GraphDatabaseService graph = m_context.getDataManager().getGraph();
		
		// find the accessory by the serial number.
		try ( Transaction tx = graph.beginTx() ) {
			
			Node node_acc = graph.findNode( Consts.LABEL_ACCESSORY, 
											Consts.ACCESSORY_SERIALNUMBER, 
											m_accessory.getSerialnumber() );
			
			if (null != node_acc) {
				node_acc.setProperty(Consts.ACCESSORY_ISREGISTER, "false");
				
				// set accessory register at true in return accessory
				m_accessory.setRegister(false);
				
				// set the data change
				this.m_data_is_changed = true;
			}
			else {
				m_logger.warn(i18n.tr("Accessory not found: ") + m_accessory.toString());
			}
			
			tx.success();
		}
		
		m_logger.info(i18n.tr("Execution completed."));
	}
	
	/**
	 * Return the accessory.
	 * 
	 * @return Accessory.
	 */
	public Accessory getAccessory(){
		return m_accessory;
	}
	
}
