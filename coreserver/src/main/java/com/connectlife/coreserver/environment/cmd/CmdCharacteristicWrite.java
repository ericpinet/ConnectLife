/**
 *  CmdCharacteristicWrite.java
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

import com.clapi.data.Characteristic;
import com.clapi.data.Characteristic.CharacteristicType;
import com.connectlife.coreserver.Consts;
import com.connectlife.coreserver.environment.data.DataManagerFactory;
import com.google.common.base.Preconditions;

/**
 * Command to change value of a characteristic for an Accessory.
 * 
 * @author ericpinet
 * <br> 2016-03-28
 */
public class CmdCharacteristicWrite extends CmdDefault {
	
	/**
	 * Logger use for this class.
	 */
	private static Logger m_logger = LogManager.getLogger(CmdCharacteristicWrite.class);
	
	/**
	 * Initialization of translation system.
	 */
	private static I18n i18n = I18nFactory.getI18n(CmdCharacteristicWrite.class);
	
	/**
	 * Characteristic.
	 */
	private Characteristic m_characteristic;
	
	/**
	 * Target value of the characteristic.
	 */
	private Characteristic m_target_value;
	
	
	/**
	 * Default constructor.
	 * 
	 * @param _characteristic Characteristic.
	 * @param _target_value Target value of the characteristic.
	 */
	public CmdCharacteristicWrite (Characteristic _characteristic, Characteristic _target_value){
		m_characteristic = _characteristic;
		m_target_value = _target_value;
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
		
		Preconditions.checkNotNull(m_characteristic, i18n.tr("Error! It's not possible to update null characteristic in the environment."));
		Preconditions.checkNotNull(m_target_value, i18n.tr("Error! It's not possible to update characteristic with null target value in the environment."));
		
		// get the graph data
		GraphDatabaseService graph = m_context.getDataManager().getGraph();
		
		// begin transaction
		try ( Transaction tx = graph.beginTx() ) {
			
			// find the accessory by the uid.
			Node node = graph.findNode( Consts.LABEL_CHARACTERISTIC, 
										Consts.UID, 
										m_characteristic.getUid() );
			
			// check if characteristic wasn't present in environment
			if (null != node) {
				
				// check if the data isn't read only
				if (false == node.getProperty(Consts.CH_MODE).equals(Consts.CH_ACCESS_MODE_READ_ONLY)) {
				
					// check if characteristic and target value have same type
					if ( DataManagerFactory.buildCharacteristic(node).getType() == m_target_value.getType() || 
						 ( DataManagerFactory.buildCharacteristic(node).getType() == CharacteristicType.WRITE_ONLY_BOOLEAN && 
						   m_target_value.getType() == CharacteristicType.BOOLEAN) ) {
					
						// BOOLEAN OR WRITE ONLY BOOLEAN
						if (CharacteristicType.BOOLEAN == m_target_value.getType()){
							node.setProperty(Consts.CH_DATA, m_target_value.getDataBoolean()); 
						}
						// STRING
						else if (CharacteristicType.STATIC_STRING == m_target_value.getType()) {
							node.setProperty(Consts.CH_DATA, m_target_value.getData());
						}
						// ENUM
						else if (CharacteristicType.ENUM == m_target_value.getType()) {
							node.setProperty(Consts.CH_DATA, m_target_value.getDataEnum());
						}
						// INTEGER
						else if (CharacteristicType.INTEGER == m_target_value.getType()) {
							node.setProperty(Consts.CH_DATA, m_target_value.getDataInteger());
						}
						// FLOAT
						else if (CharacteristicType.FLOAT == m_target_value.getType()) {
							node.setProperty(Consts.CH_DATA, m_target_value.getDataFloat());
						}
						
						// display info in log
						m_logger.info(m_target_value.toString());
						
						// set the data change
						this.m_data_is_changed = true;
						
					}
					else {
						m_logger.error(i18n.tr("Characteristic target type was invalid: ") + m_target_value.toString());
						throw new Exception(i18n.tr("Characteristic target type was invalid: ") + m_target_value.toString());
					}
				}
				else {
					m_logger.error(i18n.tr("Characteristic was read only."));
					throw new Exception(i18n.tr("Characteristic was read only."));
				}
			}
			else {
				m_logger.error(i18n.tr("Characteristic not found: ") + m_characteristic.toString());
				throw new Exception(i18n.tr("Characteristic not found: ") + m_characteristic.toString());
			}
			
			tx.success();
		}
		m_logger.info(i18n.tr("Execution completed."));
	}
	
}
