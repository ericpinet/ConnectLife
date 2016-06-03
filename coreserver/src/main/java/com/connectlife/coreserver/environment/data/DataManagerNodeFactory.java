/**
 *  DataManagerNodeFactory.java
 *  coreserver
 *
 *  Created by ericpinet on 2016-06-01.
 *  Copyright (c) 2016 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.connectlife.coreserver.environment.data;

import java.util.Iterator;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.ResourceIterator;
import org.neo4j.graphdb.Transaction;

import com.clapi.data.Accessory;
import com.clapi.data.Characteristic;
import com.clapi.data.Characteristic.CharacteristicAccessMode;
import com.clapi.data.Characteristic.CharacteristicEventType;
import com.clapi.data.Characteristic.CharacteristicType;
import com.clapi.data.Service;
import com.connectlife.coreserver.Consts;

/**
 * Data factory to manage the node system of the graph with standard data.
 * 
 * @author ericpinet
 * <br> 2016-06-01
 */
public abstract class DataManagerNodeFactory {
	
	/**
	 * Create an accessory node with the Accessory.
	 * 
	 * @param _graph Main graph
	 * @param _accessory Accessory data
	 * @return Node build
	 * @throws Exception Throw exception if something goes wrong.
	 */
	public static Node buildAccessoryNode(GraphDatabaseService _graph, Accessory _accessory) throws Exception {
		
		Node node = null;
		
		// begin transaction
		try ( Transaction tx = _graph.beginTx() ) {
			
			if (checkUidExist(_graph, Consts.LABEL_ACCESSORY, _accessory.getUid())) {
				throw new Exception ("Uid already exist : " + _accessory.getUid());
			}
			
			// create node
			node = _graph.createNode(Consts.LABEL_ACCESSORY);
			
			// build services
			Iterator <Service> it = _accessory.getServices().iterator();
			
			while (it.hasNext()) {
				Service service = it.next();
				
				// create service node
				Node node_ser = buildServiceNode(_graph, service);
				
				// adding the relationship
				node.createRelationshipTo(node_ser, Consts.RelTypes.CONTAINS);
			}
			
			// update data
			updateAccessoryNode(_graph, node, _accessory);
			
			tx.success();
		}
		
		return node;
	}
	
	/**
	 * Update a node accessory with the accessory data.
	 * 
	 * @param _graph Main graph
	 * @param _node Node to update
	 * @param _accessory Accessory data
	 * @throws Exception Throw exception if something goes wrong.
	 */
	public static void updateAccessoryNode(GraphDatabaseService _graph, Node _node, Accessory _accessory) throws Exception {
		
		// find the accessory by the serial number.
		try ( Transaction tx = _graph.beginTx() ) {
			
			if (_node.hasLabel(Consts.LABEL_ACCESSORY)) {
			
				_node.setProperty(Consts.ACCESSORY_UID, _accessory.getUid());
				_node.setProperty(Consts.ACCESSORY_LABEL, _accessory.getLabel());
				_node.setProperty(Consts.ACCESSORY_MODEL, _accessory.getModel());
				_node.setProperty(Consts.ACCESSORY_MANUFACTURER, _accessory.getManufacturer());
				_node.setProperty(Consts.ACCESSORY_SERIALNUMBER, _accessory.getSerialnumber());
				_node.setProperty(Consts.ACCESSORY_ISREGISTER, _accessory.isRegister());
				_node.setProperty(Consts.ACCESSORY_IMAGEURL, _accessory.getImageurl());
				
				Iterator<Service> it = _accessory.getServices().iterator();
				while (it.hasNext()) {
					Service service = it.next();
					updateServiceNode(_graph, _graph.findNode(Consts.LABEL_SERVICE, Consts.SERVICE_UID, service.getUid()), service);
				}
			}
			else {
				throw new Exception ("It's not a accessory node! ["+_node.getLabels()+"]");
			}
			
			tx.success();
		}
	}
	
	/**
	 * Create a service node with the Service.
	 * 
	 * @param _graph Main graph
	 * @param _service Service data
	 * @return Node build
	 * @throws Exception Throw exception if something goes wrong.
	 */
	public static Node buildServiceNode(GraphDatabaseService _graph, Service _service) throws Exception {
		
		// create node
		Node node = null;
		
		// begin transaction
		try ( Transaction tx = _graph.beginTx() ) {
			
			if (checkUidExist(_graph, Consts.LABEL_SERVICE, _service.getUid())) {
				throw new Exception ("Uid already exist : " + _service.getUid());
			}
				
			node = _graph.createNode(Consts.LABEL_SERVICE);
			
			// build characteristics
			Iterator <Characteristic> it = _service.getCharacteristics().iterator();
			
			while (it.hasNext()) {
				Characteristic characteristic = it.next();
				
				// create the node
				Node node_ch = buildCharacteristicNode(_graph, characteristic);
				
				// adding relationship
				node.createRelationshipTo(node_ch, Consts.RelTypes.CONTAINS);
			}	
			
			// update data
			updateServiceNode(_graph, node, _service);
			
			tx.success();
		}
		
		// return
		return node;
	}
	
	/**
	 * Update a node accessory service with the service data.
	 * 
	 * @param _graph Main graph
	 * @param _node Node to update
	 * @param _service Service data
	 * @throws Exception Throw exception if something goes wrong.
	 */
	public static void updateServiceNode(GraphDatabaseService _graph, Node _node, Service _service) throws Exception {
		
		// find the accessory by the serial number.
		try ( Transaction tx = _graph.beginTx() ) {
			
			if (_node.hasLabel(Consts.LABEL_SERVICE)) {
				
				_node.setProperty(Consts.SERVICE_UID, _service.getUid());
				_node.setProperty(Consts.SERVICE_NAME, _service.getName());
				
				Iterator<Characteristic> it = _service.getCharacteristics().iterator();
				while (it.hasNext()) {
					Characteristic characteristic = it.next();
					updateCharacteristicNode(_graph, _graph.findNode(Consts.LABEL_CHARACTERISTIC, Consts.CH_UID, characteristic.getUid()), characteristic);
				}
			}
			else {
				throw new Exception ("It's not a service node! ["+_node.getLabels()+"]");
			}
			
			tx.success();
		}
	}
	
	/**
	 * Create a characteristic node with the Characteristic.
	 * 
	 * @param _graph Main graph
	 * @param _characteristic Characteristic data
	 * @return Node build
	 * @throws Exception Throw exception if something goes wrong.
	 */
	public static Node buildCharacteristicNode(GraphDatabaseService _graph, Characteristic _characteristic) throws Exception {
		
		Node node = null;
		
		try ( Transaction tx = _graph.beginTx() ) {
			
			if (checkUidExist(_graph, Consts.LABEL_CHARACTERISTIC, _characteristic.getUid())) {
				throw new Exception ("Uid already exist : " + _characteristic.getUid());
			}
			
			// create node
			node = _graph.createNode(Consts.LABEL_CHARACTERISTIC);
			
			// update data
			updateCharacteristicNode(_graph, node, _characteristic);
			
			tx.success();
		}
		
		// return
		return node;
	}
	
	/**
	 * Update a node characteristic with the characteristic data.
	 * 
	 * @param _graph Main graph
	 * @param _node Node to update
	 * @param _characteristic Characteristic data
	 * @throws Exception Throw exception if something goes wrong.
	 */
	public static void updateCharacteristicNode(GraphDatabaseService _graph, Node _node, Characteristic _characteristic) throws Exception {
		
		// find the accessory by the serial number.
		try ( Transaction tx = _graph.beginTx() ) {
			
			if (_node.hasLabel(Consts.LABEL_CHARACTERISTIC)) {
				
				_node.setProperty(Consts.CH_UID, _characteristic.getUid());
				_node.setProperty(Consts.CH_LABEL, _characteristic.getLabel());
				
				// TYPE
				if (CharacteristicType.BOOLEAN == _characteristic.getType()) {
					_node.setProperty(Consts.CH_TYPE, Consts.CH_TYPE_BOOLEAN);
					_node.setProperty(Consts.CH_DATA, _characteristic.getDataBoolean());
				}
				else if (CharacteristicType.ENUM == _characteristic.getType()) {
					_node.setProperty(Consts.CH_TYPE, Consts.CH_TYPE_ENUM);
					_node.setProperty(Consts.CH_DATA, _characteristic.getDataEnum());
				}
				else if (CharacteristicType.FLOAT == _characteristic.getType()) {
					_node.setProperty(Consts.CH_TYPE, Consts.CH_TYPE_FLOAT);
					_node.setProperty(Consts.CH_DATA, _characteristic.getDataFloat());
				}
				else if (CharacteristicType.INTEGER == _characteristic.getType()) {
					_node.setProperty(Consts.CH_TYPE, Consts.CH_TYPE_INTEGER);
					_node.setProperty(Consts.CH_DATA, _characteristic.getDataInteger());
				}
				else if (CharacteristicType.STATIC_STRING == _characteristic.getType()) {
					_node.setProperty(Consts.CH_TYPE, Consts.CH_TYPE_STRING);
					_node.setProperty(Consts.CH_DATA, _characteristic.getData());
				}
				else if (CharacteristicType.WRITE_ONLY_BOOLEAN == _characteristic.getType()) {
					_node.setProperty(Consts.CH_TYPE, Consts.CH_TYPE_WRITE_ONLY_BOOLEAN);
					_node.setProperty(Consts.CH_DATA, _characteristic.getDataBoolean());
				}
				
				// EVENT
				if (CharacteristicEventType.EVENT == _characteristic.getEvent()) {
					_node.setProperty(Consts.CH_EVENT_TYPE, Consts.CH_EVENT_TYPE_EVENT);
				}
				else if (CharacteristicEventType.NO_EVENT == _characteristic.getEvent()) {
					_node.setProperty(Consts.CH_EVENT_TYPE, Consts.CH_EVENT_TYPE_NO_EVENT);
				}
	
				// ACCESS MODE
				if (CharacteristicAccessMode.READ_ONLY == _characteristic.getMode()) {
					_node.setProperty(Consts.CH_MODE, Consts.CH_ACCESS_MODE_READ_ONLY);
				}
				else if (CharacteristicAccessMode.READ_WRITE == _characteristic.getMode()) {
					_node.setProperty(Consts.CH_MODE, Consts.CH_ACCESS_MODE_READ_WRITE);
				}
				else if (CharacteristicAccessMode.WRITE_ONLY == _characteristic.getMode()) {
					_node.setProperty(Consts.CH_MODE, Consts.CH_ACCESS_MODE_WRITE_ONLY);
				}
			}
			else {
				throw new Exception ("It's not a characteristic node! ["+_node.getLabels()+"]");
			}
			
			tx.success();
		}
	}
	
	/**
	 * Return true if UID exist in environment.
	 * 
	 * @param _graph Main graph
	 * @param _label Label of node
	 * @param _uid Uid to check
	 * @return True if uid exist.
	 */
	public static boolean checkUidExist(GraphDatabaseService _graph, Label _label, String _uid) {
		
		boolean ret_val = false;
		
		try ( Transaction tx = _graph.beginTx() ) {
			
			ResourceIterator<Node> nodes = _graph.findNodes(_label, Consts.UID, _uid);
			ret_val = nodes.hasNext();
			
			tx.success();
		}
		
		return ret_val;
	}
}
