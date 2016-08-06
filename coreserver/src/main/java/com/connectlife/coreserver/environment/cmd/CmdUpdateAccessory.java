/**
 *  CmdUpdateAccessory.java
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
import org.xnap.commons.i18n.I18nFactory;

import com.clapi.data.Accessory;
import com.connectlife.coreserver.Consts;
import com.connectlife.coreserver.environment.data.DataManagerNodeFactory;
import com.google.api.client.util.Preconditions;

/**
 * Command to update a accessory in the environment.
 * 
 * @author ericpinet
 * <br> 2016-03-28
 */
public class CmdUpdateAccessory extends CmdDefault {
	
	/**
	 * Logger use for this class.
	 */
	private static Logger m_logger = LogManager.getLogger(CmdUpdateAccessory.class);
	
	/**
	 * Initialization of translation system.
	 */
	private static I18n i18n = I18nFactory.getI18n(CmdUpdateAccessory.class);
	
	/**
	 * Person to add in the environment.
	 */
	private Accessory m_accessory;
	
	/**
	 * Default constructor.
	 *  
	 * @param _accessory Accessory to register in the environment.
	 */
	public CmdUpdateAccessory (Accessory _accessory){
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
		
		Preconditions.checkNotNull(m_accessory, i18n.tr("Error! It's not possible to update null accessory in the environment."));

		// get the graph data
		GraphDatabaseService graph = m_context.getDataManager().getGraph();
		
		// find the accessory by the serial number.
		try ( Transaction tx = graph.beginTx() ) {
			
			Node node_acc = graph.findNode( Consts.LABEL_ACCESSORY, 
											Consts.ACCESSORY_SERIALNUMBER, 
											m_accessory.getSerialnumber() );
			
			if (null != node_acc) {
				
				// the accessory must be already register before update
				if (true == node_acc.getProperty(Consts.ACCESSORY_ISREGISTER).equals("true")) {
				
					// ensure that the data update will not change the register status
					m_accessory.setRegister(true);
					
					// Update the data in the graph
					DataManagerNodeFactory.updateAccessoryNode(graph, node_acc, m_accessory);
				
					// set the data change
					this.m_data_is_changed = true;
				}
			}
			else {
				m_logger.error(i18n.tr("Accessory not found: ") + m_accessory.toString());
				throw new Exception(i18n.tr("Accessory not found: ") + m_accessory.toString());
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
