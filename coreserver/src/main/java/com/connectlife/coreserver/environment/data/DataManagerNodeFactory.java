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
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;

import com.clapi.data.Accessory;
import com.clapi.data.Characteristic;
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
			
			tx.success();
		}
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
			
			_node.setProperty(Consts.SERVICE_UID, _service.getUid());
			_node.setProperty(Consts.SERVICE_NAME, _service.getName());
			
			Iterator<Characteristic> it = _service.getCharacteristics().iterator();
			while (it.hasNext()) {
				Characteristic characteristic = it.next();
				updateCharacteristicNode(_graph, _graph.findNode(Consts.LABEL_CHARACTERISTIC, Consts.CH_UID, characteristic.getUid()), characteristic);
			}
			
			tx.success();
		}
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
			
			_node.setProperty(Consts.CH_UID, _characteristic.getUid());
			_node.setProperty(Consts.CH_LABEL, _characteristic.getLabel());
			
			// TODO
			// MODE, TYPE, EVENT, DATA			
			
			tx.success();
		}
	}
	

}
