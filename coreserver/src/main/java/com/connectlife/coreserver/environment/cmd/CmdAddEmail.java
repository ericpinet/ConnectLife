/**
 *  CmdAddEmail.java
 *  coreserver
 *
 *  Created by ericpinet on 2016-10-03.
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

import com.clapi.data.Email;
import com.clapi.data.Person;
import com.connectlife.coreserver.Application;
import com.connectlife.coreserver.Consts;
import com.connectlife.coreserver.environment.UIDGenerator;
import com.connectlife.coreserver.environment.data.DataManagerNodeFactory;
import com.google.common.base.Preconditions;

/**
 * Command to add an email in the environment.
 * 
 * @author ericpinet
 * <br> 2016-10-03
 */
public class CmdAddEmail extends CmdDefault {
	
	/**
	 * Logger use for this class.
	 */
	private Logger m_logger = LogManager.getLogger(getClass().getName());
	
	/**
	 * Initialization of translation system.
	 */
	private static I18n i18n = Application.i18n;
	
	/**
	 * Email to add in the environment.
	 */
	private Email m_email;
	
	/**
	 * Person where to add the email.
	 */
	private Person m_person;
	
	/**
	 * Default constructor.
	 *  
	 * @param _email Email to add in the environment.
	 * @param _person Home target where add zone.
	 */
	public CmdAddEmail (Email _email, Person _person){
		m_email = _email;
		m_person = _person;
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
		
		Preconditions.checkNotNull(m_email, i18n.tr("Error! It's not possible to add null email in the environment."));
		Preconditions.checkArgument(m_email.getUid().isEmpty(), i18n.tr("Error! It's not possible to add a email with a UID."));
		Preconditions.checkNotNull(m_person, i18n.tr("Error! It's not possible to add email in a null person in the environment."));
		
		// get the graph data
		GraphDatabaseService graph = m_context.getDataManager().getGraph();
		
		// begin transaction
		try ( Transaction tx = graph.beginTx() ) {
			
			Node node_person = graph.findNode( 	Consts.LABEL_PERSON, 
											 	Consts.UID, 
											 	m_person.getUid() );
			
			if (null != node_person) {
						
				// create the uid for the email
				m_email.setUid(UIDGenerator.getUID());
				
				// build email node
				Node node = DataManagerNodeFactory.buildEmailNode(graph, m_email);
				
				// create relationship
				node_person.createRelationshipTo(node, Consts.RelTypes.CONTAINS);
				
				// display info in log
				m_logger.info(m_email.toString());
				
				// set the data change
				this.m_data_is_changed = true;
			}
			else {
				m_logger.error(i18n.tr("Person not found: ") + m_person.toString());
				throw new Exception(i18n.tr("Person not found: ") + m_person.toString());
			}

			tx.success();
		}
		
		m_logger.info(i18n.tr("Execution completed."));
	}	
}
