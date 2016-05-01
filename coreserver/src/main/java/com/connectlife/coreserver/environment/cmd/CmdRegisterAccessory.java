/**
 *  CmdRegisterAccessory.java
 *  coreserver
 *
 *  Created by ericpinet on 2016-03-28.
 *  Copyright (c) 2016 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.connectlife.coreserver.environment.cmd;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.clapi.data.Accessory;
import com.connectlife.coreserver.environment.find.FindProcessor;

/**
 * Command to register a accessory in the environment.
 * 
 * @author ericpinet
 * <br> 2016-03-28
 */
public class CmdRegisterAccessory extends CmdDefault {
	
	/**
	 * Logger use for this class.
	 */
	private static Logger m_logger = LogManager.getLogger(CmdRegisterAccessory.class);
	
	/**
	 * Person to add in the environment.
	 */
	private Accessory m_accessory;
	
	/**
	 * Default constructor.
	 *  
	 * @param _accessory Accessory to register in the environment.
	 */
	public CmdRegisterAccessory (Accessory _accessory){
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
		
		m_logger.info("Execution start ...");
		
		// Get the find processor
		FindProcessor find = m_context.getFindProcessorReadWrite();
		
		// check the person to add in the environment
		if( null == m_accessory ){
			m_logger.error("Error! It's not possible to register null accessory in the environment.");
			throw new Exception ("Error! It's not possible to register null accessory in the environment.");
		}
		
		// find the accessory by the serial number.
		Accessory accessory = find.findAccessory(m_accessory);
		
		// if accessory is find register.
		if(null != accessory){
			accessory.update(m_accessory);
			accessory.setRegister(true);
			
			m_accessory = accessory;
			
			// set the data change
			this.m_data_is_changed = true;
		}
		else{
			m_accessory = null;
		}
		
		m_logger.info("Execution completed.");
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