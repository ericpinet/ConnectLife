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

import com.clapi.data.Data;
import com.clapi.data.Person;
import com.connectlife.coreserver.environment.UIDGenerator;

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
		
		m_logger.info("Execution start ...");
		
		// prepare the data
		Data data = m_context.getDataManager().getData();
		
		// check the person to add in the environment
		if( null == m_person ){
			m_logger.error("Error! It's not possible to add null person in the environment.");
			throw new Exception ("Error! It's not possible to add null person in the environment.");
		}
		
		if( false == m_person.getUid().isEmpty() ){
			m_logger.error("Error! It's not possible to add a person with a UID.");
			throw new Exception ("Error! It's not possible to add a person with a UID.");
		}
				
		// adding uid in the person
		m_person.setUid(UIDGenerator.getUID());
		
		// add person in the environment.
		data.getPersons().add(m_person);
		
		// set the data change
		this.m_data_is_changed = true;
		
		m_logger.info("Execution completed.");
	}
	
}
