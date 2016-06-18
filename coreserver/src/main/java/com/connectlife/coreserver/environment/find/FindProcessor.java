/**
 *  FindProcessor.java
 *  coreserver
 *
 *  Created by ericpinet on 2016-02-07.
 *  Copyright (c) 2016 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.connectlife.coreserver.environment.find;

import java.util.Iterator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.Transaction;

import com.clapi.data.Accessory;
import com.clapi.data.Person;
import com.clapi.data.Room;
import com.connectlife.coreserver.Consts;
import com.connectlife.coreserver.environment.data.DataManagerFactory;
import com.connectlife.coreserver.tools.errormanagement.StdOutErrLog;

/**
 * Find processor for the environment.
 * 
 * @author ericpinet
 * <br> 2016-02-07
 */
public abstract class FindProcessor {
	
	/**
	 * Logger use for this class.
	 */
	private static Logger m_logger = LogManager.getLogger(FindProcessor.class);
	
	/**
	 * Data environment use by this find processor;
	 */
	protected GraphDatabaseService m_graph;
	
	/**
	 * Find a person in environment by uid.
	 * 
	 * @param _uid Uid to find.
	 * @return Person or null if not found.
	 */
	public Person findPersonByUid(String _uid) {
		
		Person ret = null;
		
		// begin transaction
		try ( Transaction tx = m_graph.beginTx() ) {
			
			Node node = m_graph.findNode( 	Consts.LABEL_PERSON, 
											Consts.UID, 
											_uid );
			
			// if the node was found
			if (null != node) {
				
				try {
					ret = DataManagerFactory.buildPerson(m_graph, node);
				}
				catch (Exception e) {
					m_logger.error(e.getMessage());
					StdOutErrLog.tieSystemOutAndErrToLog();
					e.printStackTrace();
				}
			}
			
			tx.success();
		}
		
		return ret;
	}
	
	/**
	 * Find room by Uid.
	 * 
	 * @param _uid Uid to find 
	 * @return Room or null if not found.
	 */
	public Room findRoomByUid(String _uid){
		
		Room ret = null;
		
		// begin transaction
		try ( Transaction tx = m_graph.beginTx() ) {
			
			Node node = m_graph.findNode( 	Consts.LABEL_ROOM, 
											Consts.UID, 
											_uid );
			
			// if the node was found
			if (null != node) {
				
				try {
					ret = DataManagerFactory.buildRoom(m_graph, node);
				}
				catch (Exception e) {
					m_logger.error(e.getMessage());
					StdOutErrLog.tieSystemOutAndErrToLog();
					e.printStackTrace();
				}
			}
			
			tx.success();
		}
		
		return ret;
	}
	
	/**
	 * Find the room where the accessory is added.
	 * 
	 * @param _uid Accessory uid added in a room.
	 * @return Room where the accessory is located. Null if not found.
	 */
	public Room findRoomByAccessoryUid(String _uid){
		
		Room ret = null;
		
		// begin transaction
		try ( Transaction tx = m_graph.beginTx() ) {
			
			Node node = m_graph.findNode( 	Consts.LABEL_ACCESSORY, 
											Consts.UID, 
											_uid );
			
			// if the node was found
			if (null != node) {
				
				// find the room where this accessory is added.
				Iterator<Relationship> relations = node.getRelationships(Consts.RelTypes.CONTAINS, Direction.INCOMING).iterator();
				boolean found = false;
				
				while (relations.hasNext() && found == false) {
					Relationship relation = relations.next();
					if (relation.getStartNode().hasLabel(Consts.LABEL_ROOM)) {
						try {
							found = true;
							ret = DataManagerFactory.buildRoom(m_graph, node);
						}
						catch (Exception e) {
							m_logger.error(e.getMessage());
							StdOutErrLog.tieSystemOutAndErrToLog();
							e.printStackTrace();
						}
					}
				}
			}
			
			tx.success();
		}
		
		return ret;
	}
	
	
	/**
	 * Find the accessory by uid.
	 * 
	 * @param _uid Uid to be found.
	 * @return Accessory or null if not found.
	 */
	public Accessory findAccessoryByUid(String _uid){
		Accessory ret = null;
		
		// begin transaction
		try ( Transaction tx = m_graph.beginTx() ) {
			
			Node node = m_graph.findNode( 	Consts.LABEL_ACCESSORY, 
											Consts.UID, 
											_uid );
			
			// if the node was found
			if (null != node) {
				
				try {
					ret = DataManagerFactory.buildAccessory(m_graph, node);
				}
				catch (Exception e) {
					m_logger.error(e.getMessage());
					StdOutErrLog.tieSystemOutAndErrToLog();
					e.printStackTrace();
				}
			}
			
			tx.success();
		}
		
		return ret;
	}
	
	/**
	 * Find the accessory by serial number.
	 * 
	 * @param _serial_number Serial number to be found.
	 * @return Accessory or null if not found.
	 */
	public Accessory findAccessoryBySerialNumber(String _serial_number){
		Accessory ret = null;
		
		// begin transaction
		try ( Transaction tx = m_graph.beginTx() ) {
			
			Node node = m_graph.findNode( 	Consts.LABEL_ACCESSORY, 
											Consts.ACCESSORY_SERIALNUMBER, 
											_serial_number );
			
			// if the node was found
			if (null != node) {
				
				try {
					ret = DataManagerFactory.buildAccessory(m_graph, node);
				}
				catch (Exception e) {
					m_logger.error(e.getMessage());
					StdOutErrLog.tieSystemOutAndErrToLog();
					e.printStackTrace();
				}
			}
			
			tx.success();
		}
		
		return ret;
	}
}
