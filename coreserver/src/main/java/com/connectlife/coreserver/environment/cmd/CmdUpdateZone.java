/**
 *  CmdUpdateZone.java
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
import org.xnap.commons.i18n.I18nFactory;

import com.clapi.data.Zone;
import com.connectlife.coreserver.Consts;
import com.connectlife.coreserver.environment.data.DataManagerNodeFactory;
import com.google.api.client.util.Preconditions;

/**
 * Command to update a zone in the environment.
 * 
 * @author ericpinet
 * <br> 2016-03-28
 */
public class CmdUpdateZone extends CmdDefault {
	
	/**
	 * Logger use for this class.
	 */
	private static Logger m_logger = LogManager.getLogger(CmdUpdateZone.class);
	
	/**
	 * Initialization of translation system.
	 */
	private static I18n i18n = I18nFactory.getI18n(CmdUpdateZone.class);
	
	/**
	 * Zone to update in the environment.
	 */
	private Zone m_zone;
	
	/**
	 * Default constructor.
	 *  
	 * @param _zone Zone to update in the environment.
	 */
	public CmdUpdateZone (Zone _zone){
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
		
		m_logger.info(i18n.tr("Execution start ..."));
		
		Preconditions.checkNotNull(m_zone, i18n.tr("Error! It's not possible to update null zone in the environment."));
		
		// get the graph data
		GraphDatabaseService graph = m_context.getDataManager().getGraph();
		
		// find the zone by the uid.
		try ( Transaction tx = graph.beginTx() ) {
			
			Node node_acc = graph.findNode( Consts.LABEL_ZONE, 
											Consts.UID, 
											m_zone.getUid() );
			
			if (null != node_acc) {
				
				// Update the data in the graph
				DataManagerNodeFactory.updateZoneNode(graph, node_acc, m_zone);
				
				// display info in log
				m_logger.info(m_zone.toString());
			
				// set the data change
				this.m_data_is_changed = true;
				
			}
			else {
				m_logger.error(i18n.tr("Zone not found: ") + m_zone.toString());
				throw new Exception(i18n.tr("Zone not found: ") + m_zone.toString());
			}
			
			tx.success();
		}
		
		m_logger.info(i18n.tr("Execution completed."));
	}
	
	/**
	 * Return the zone.
	 * 
	 * @return Zone.
	 */
	public Zone getZone(){
		return m_zone;
	}
	
}
