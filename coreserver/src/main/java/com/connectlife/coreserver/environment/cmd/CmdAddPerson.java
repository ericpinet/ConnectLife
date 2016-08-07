/**
 *  CmdAddPerson.java
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
import org.neo4j.graphdb.Transaction;
import org.xnap.commons.i18n.I18n;

import com.clapi.data.Person;
import com.connectlife.coreserver.Application;
import com.connectlife.coreserver.environment.UIDGenerator;
import com.connectlife.coreserver.environment.data.DataManagerNodeFactory;
import com.google.common.base.Preconditions;

/**
 * Command to add a new person in the environment.
 * 
 * @author ericpinet
 * <br> 2016-03-28
 */
public class CmdAddPerson extends CmdDefault {
	
	/**
	 * Logger use for this class.
	 */
	private static Logger m_logger = LogManager.getLogger(CmdAddPerson.class);
	
	/**
	 * Initialization of translation system.
	 */
	private static I18n i18n = Application.i18n;
	
	/**
	 * Person to add in the environment.
	 */
	private Person m_person;
	
	/**
	 * Default constructor.
	 *  
	 * @param _person Person to add in the environment.
	 */
	public CmdAddPerson (Person _person){
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
		
		Preconditions.checkNotNull(m_person, i18n.tr("Error! It's not possible to add null person in the environment."));
		Preconditions.checkArgument(m_person.getUid().isEmpty(), i18n.tr("Error! It's not possible to add a person with a UID."));
		
		// get the graph data
		GraphDatabaseService graph = m_context.getDataManager().getGraph();
		
		// begin transaction
		try ( Transaction tx = graph.beginTx() ) {
						
			// create the uid for the person
			m_person.setUid(UIDGenerator.getUID());
			
			// build person node
			DataManagerNodeFactory.buildPersonNode(graph, m_person);
			
			// display info in log
			m_logger.info(m_person.toString());
			
			// set the data change
			this.m_data_is_changed = true;

			tx.success();
		}
		
		m_logger.info(i18n.tr("Execution completed."));
	}	
}
