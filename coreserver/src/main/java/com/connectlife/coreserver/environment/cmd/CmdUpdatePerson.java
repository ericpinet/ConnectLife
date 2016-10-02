/**
 *  CmdUpdatePerson.java
 *  coreserver
 *
 *  Created by ericpinet on 2016-10-01.
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

import com.clapi.data.Person;
import com.connectlife.coreserver.Application;
import com.connectlife.coreserver.Consts;
import com.connectlife.coreserver.environment.data.DataManagerNodeFactory;
import com.google.common.base.Preconditions;

/**
 * Command to update a new person in the environment.
 * 
 * @author ericpinet
 * <br> 2016-10-01
 */
public class CmdUpdatePerson extends CmdDefault {
	
	/**
	 * Logger use for this class.
	 */
	private Logger m_logger = LogManager.getLogger(getClass().getName());
	
	/**
	 * Initialization of translation system.
	 */
	private static I18n i18n = Application.i18n;
	
	/**
	 * Person to update in the environment.
	 */
	private Person m_person;
	
	/**
	 * Default constructor.
	 *  
	 * @param _person Person to update in the environment.
	 */
	public CmdUpdatePerson (Person _person){
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
		
		Preconditions.checkNotNull(m_person, i18n.tr("Error! It's not possible to update null person in the environment."));
		Preconditions.checkArgument(false == m_person.getUid().isEmpty(), i18n.tr("Error! It's not possible to update a person with a null UID."));
		
		// get the graph data
		GraphDatabaseService graph = m_context.getDataManager().getGraph();
		
		// begin transaction
		try ( Transaction tx = graph.beginTx() ) {
			
			Node node = graph.findNode( Consts.LABEL_PERSON, 
										Consts.UID, 
										m_person.getUid() );

			if (null != node) {
			
				// update person node
				DataManagerNodeFactory.updatePersonNode(graph, node, m_person);
				
				// display info in log
				m_logger.info(m_person.toString());
				
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
