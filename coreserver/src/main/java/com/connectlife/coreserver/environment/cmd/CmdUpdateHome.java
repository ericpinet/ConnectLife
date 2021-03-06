/**
 *  CmdUpdateHome.java
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

import com.clapi.data.Home;
import com.connectlife.coreserver.Application;
import com.connectlife.coreserver.Consts;
import com.connectlife.coreserver.environment.data.DataManagerNodeFactory;
import com.google.api.client.util.Preconditions;

/**
 * Command to update a home in the environment.
 * 
 * @author ericpinet
 * <br> 2016-03-28
 */
public class CmdUpdateHome extends CmdDefault {
	
	/**
	 * Logger use for this class.
	 */
	private Logger m_logger = LogManager.getLogger(getClass().getName());
	
	/**
	 * Initialization of translation system.
	 */
	private static I18n i18n = Application.i18n;
	
	/**
	 * Home to update in the environment.
	 */
	private Home m_home;
	
	/**
	 * Default constructor.
	 *  
	 * @param _home Home to update in the environment.
	 */
	public CmdUpdateHome (Home _home){
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
		
		m_logger.info(i18n.tr("Execution start ..."));
		
		Preconditions.checkNotNull(m_home, i18n.tr("Error! It's not possible to update null home in the environment."));
		
		// get the graph data
		GraphDatabaseService graph = m_context.getDataManager().getGraph();
		
		// find the zone by the uid.
		try ( Transaction tx = graph.beginTx() ) {
			
			Node node = graph.findNode( Consts.LABEL_HOME, 
										Consts.UID, 
										m_home.getUid() );
			
			if (null != node) {
				
				// Update the data in the graph
				DataManagerNodeFactory.updateHomeNode(graph, node, m_home);
				
				// display info in log
				m_logger.info(m_home.toString());
			
				// set the data change
				this.m_data_is_changed = true;
				
			}
			else {
				m_logger.error(i18n.tr("Home not found: ") + m_home.toString());
				throw new Exception(i18n.tr("Home not found: ") + m_home.toString());
			}
			
			tx.success();
		}
		
		m_logger.info(i18n.tr("Execution completed."));
	}
	
	/**
	 * Return the home.
	 * 
	 * @return Home.
	 */
	public Home getHome(){
		return m_home;
	}
	
}
