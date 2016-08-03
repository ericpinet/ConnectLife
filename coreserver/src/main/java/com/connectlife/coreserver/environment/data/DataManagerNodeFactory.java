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
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.ResourceIterator;
import org.neo4j.graphdb.Transaction;

import com.clapi.data.Accessory;
import com.clapi.data.Address;
import com.clapi.data.Address.AddressType;
import com.clapi.data.Asset;
import com.clapi.data.Asset.AssetMode;
import com.clapi.data.Asset.AssetType;
import com.clapi.data.Characteristic;
import com.clapi.data.Characteristic.CharacteristicAccessMode;
import com.clapi.data.Characteristic.CharacteristicEventType;
import com.clapi.data.Characteristic.CharacteristicType;
import com.clapi.data.Email;
import com.clapi.data.Email.EmailType;
import com.clapi.data.Home;
import com.clapi.data.Person;
import com.clapi.data.Phone;
import com.clapi.data.Phone.PhoneType;
import com.clapi.data.Room;
import com.clapi.data.Service;
import com.clapi.data.Zone;
import com.clapi.data.Accessory.AccessoryProtocolType;
import com.clapi.data.Accessory.AccessoryType;
import com.connectlife.coreserver.Consts;

/**
 * Data factory to manage the node system of the graph with standard data.
 * 
 * @author ericpinet
 * <br> 2016-06-01
 */
public abstract class DataManagerNodeFactory {
	
	/**
	 * Build home node. 
	 * 
	 * @param _graph Graph database.
	 * @param _home Home to create.
	 * @return Node builded.
	 * @throws Exception If something goes wrong.
	 */
	public static Node buildHomeNode(GraphDatabaseService _graph, Home _home) throws Exception {
		
		Node node = null;
		
		// begin transaction
		try ( Transaction tx = _graph.beginTx() ) {
			
			if (checkUidExist(_graph, Consts.LABEL_HOME, _home.getUid())) {
				throw new Exception ("Uid already exist : " + _home.getUid());
			}
			
			// create node
			node = _graph.createNode(Consts.LABEL_HOME);
			
			if (null != _home.getZones()) {
				// build zone
				Iterator <Zone> itzone = _home.getZones().iterator();
				
				while (itzone.hasNext()) {
					
					Zone zone = itzone.next();
					
					// create zone node
					Node node_zone = buildZoneNode(_graph, zone);
					
					// adding the relationship
					node.createRelationshipTo(node_zone, Consts.RelTypes.CONTAINS);
				}
			}

			// update data
			updateHomeNode(_graph, node, _home);
			
			tx.success();
		}
		
		return node;
	}
	
	/**
	 * Update a home node. 
	 * 
	 * @param _graph Graph database.
	 * @param _node Home node to update.
	 * @param _home Home data.
	 * @throws Exception If something goes wrong.
	 */
	public static void updateHomeNode(GraphDatabaseService _graph, Node _node, Home _home) throws Exception {
		
		// begin transaction
		try ( Transaction tx = _graph.beginTx() ) {
			
			if (_node.hasLabel(Consts.LABEL_HOME)) {
			
				_node.setProperty(Consts.UID, _home.getUid());
				_node.setProperty(Consts.HOME_LABEL, _home.getLabel());
				_node.setProperty(Consts.HOME_IMAGEUID, _home.getImageuid());
				
				if (null != _home.getZones()) {
					// zone
					Iterator<Zone> itzone = _home.getZones().iterator();
					
					while (itzone.hasNext()) {
						
						Zone zone = itzone.next();
						Node node_zone = _graph.findNode(Consts.LABEL_ZONE, Consts.UID, zone.getUid());
						
						if (null == node_zone) {
							
							node_zone = buildZoneNode(_graph, zone);
							_node.createRelationshipTo(node_zone, Consts.RelTypes.CONTAINS);
						}
						else {
							updateZoneNode(_graph, node_zone, zone);
						}
					}
				}
			}
			else {
				throw new Exception ("It's not a home node! ["+_node.getLabels()+"]");
			}
			
			tx.success();
		}
	}
	
	/**
	 * Build a zone. 
	 * 
	 * @param _graph Graph database.
	 * @param _zone Zone data.
	 * @return Node builded.
	 * @throws Exception If something goes wrong.
	 */
	public static Node buildZoneNode(GraphDatabaseService _graph, Zone _zone) throws Exception {
		
		Node node = null;
		
		// begin transaction
		try ( Transaction tx = _graph.beginTx() ) {
			
			if (checkUidExist(_graph, Consts.LABEL_ZONE, _zone.getUid())) {
				throw new Exception ("Uid already exist : " + _zone.getUid());
			}
			
			// create node
			node = _graph.createNode(Consts.LABEL_ZONE);
			
			if (null != _zone.getRooms()) {
				// build room
				Iterator <Room> itroom = _zone.getRooms().iterator();
				
				while (itroom.hasNext()) {
					
					Room room = itroom.next();
					
					// create room node
					Node node_room = buildRoomNode(_graph, room);
					
					// adding the relationship
					node.createRelationshipTo(node_room, Consts.RelTypes.CONTAINS);
				}
			}

			// update data
			updateZoneNode(_graph, node, _zone);
			
			tx.success();
		}
		
		return node;
	}
	
	/**
	 * Update zone node.
	 * 
	 * @param _graph Graph database.
	 * @param _node Zone node to update.
	 * @param _zone Zone data.
	 * @throws Exception If something goes wrong.
	 */
	public static void updateZoneNode(GraphDatabaseService _graph, Node _node, Zone _zone) throws Exception {
		
		// begin transaction
		try ( Transaction tx = _graph.beginTx() ) {
			
			if (_node.hasLabel(Consts.LABEL_ZONE)) {
			
				_node.setProperty(Consts.UID, _zone.getUid());
				_node.setProperty(Consts.ZONE_LABEL, _zone.getLabel());
				_node.setProperty(Consts.ZONE_IMAGEUID, _zone.getImageuid());
				
				if (null != _zone.getRooms()) {
					// room
					Iterator<Room> itroom = _zone.getRooms().iterator();
					
					while (itroom.hasNext()) {
						
						Room room = itroom.next();
						Node node_room = _graph.findNode(Consts.LABEL_ROOM, Consts.UID, room.getUid());
						
						if (null == node_room) {
							
							node_room = buildRoomNode(_graph, room);
							_node.createRelationshipTo(node_room, Consts.RelTypes.CONTAINS);
						}
						else {
							updateRoomNode(_graph, node_room, room);
						}
					}
				}
			}
			else {
				throw new Exception ("It's not a zone node! ["+_node.getLabels()+"]");
			}
			
			tx.success();
		}
	}
	
	/**
	 * Build room node.
	 * 
	 * @param _graph Graph database.
	 * @param _room Room data.
	 * @return Room node.
	 * @throws Exception If something goes wrong.
	 */
	public static Node buildRoomNode(GraphDatabaseService _graph, Room _room) throws Exception {
		
		Node node = null;
		
		// begin transaction
		try ( Transaction tx = _graph.beginTx() ) {
			
			if (checkUidExist(_graph, Consts.LABEL_ROOM, _room.getUid())) {
				throw new Exception ("Uid already exist : " + _room.getUid());
			}
			
			// create node
			node = _graph.createNode(Consts.LABEL_ROOM);
			
			if (null != _room.getAccessories()) {
				// accessory room
				Iterator <Accessory> itaccessory = _room.getAccessories().iterator();
				
				while (itaccessory.hasNext()) {
					
					Accessory accessory = itaccessory.next();
					
					// create accessory node
					Node node_room = buildAccessoryNode(_graph, accessory);
					
					// adding the relationship
					node.createRelationshipTo(node_room, Consts.RelTypes.CONTAINS);
				}
			}

			// update data
			updateRoomNode(_graph, node, _room);
			
			tx.success();
		}
		
		return node;
	}
	
	/**
	 * Update a room node.
	 * 
	 * @param _graph Graph database.
	 * @param _node Room node to update.
	 * @param _room Room data.
	 * @throws Exception If something goes wrong.
	 */
	public static void updateRoomNode(GraphDatabaseService _graph, Node _node, Room _room) throws Exception {
		
		// begin transaction
		try ( Transaction tx = _graph.beginTx() ) {
			
			if (_node.hasLabel(Consts.LABEL_ROOM)) {
			
				_node.setProperty(Consts.UID, _room.getUid());
				_node.setProperty(Consts.ROOM_LABEL, _room.getLabel());
				_node.setProperty(Consts.ROOM_IMAGEUID, _room.getImageuid());
				
				if (null != _room.getAccessories()) {
					// accessory
					Iterator<Accessory> itaccessory = _room.getAccessories().iterator();
					
					while (itaccessory.hasNext()) {
						
						Accessory accessory = itaccessory.next();
						Node node_accessory = _graph.findNode(Consts.LABEL_ACCESSORY, Consts.UID, accessory.getUid());
						
						if (null == node_accessory) {
							
							node_accessory = buildAccessoryNode(_graph, accessory);
							_node.createRelationshipTo(node_accessory, Consts.RelTypes.CONTAINS);
						}
						else {
							updateAccessoryNode(_graph, node_accessory, accessory);
						}
					}
				}
			}
			else {
				throw new Exception ("It's not a zone node! ["+_node.getLabels()+"]");
			}
			
			tx.success();
		}
	}
	
	/**
	 * Build a person node in the graph with person information.
	 * 
	 * @param _graph Main graph
	 * @param _person Person to add
	 * @return Node builded
	 * @throws Exception when something goes wrong.
	 */
	public static Node buildPersonNode(GraphDatabaseService _graph, Person _person) throws Exception {
		
		Node node = null;
		
		// begin transaction
		try ( Transaction tx = _graph.beginTx() ) {
			
			if (checkUidExist(_graph, Consts.LABEL_PERSON, _person.getUid())) {
				throw new Exception ("Uid already exist : " + _person.getUid());
			}
			
			// create node
			node = _graph.createNode(Consts.LABEL_PERSON);
			
			if (null != _person.getEmails()) {
				// build email
				Iterator <Email> itemail = _person.getEmails().iterator();
				
				while (itemail.hasNext()) {
					
					Email email = itemail.next();
					
					// create email node
					Node node_email = buildEmailNode(_graph, email);
					
					// adding the relationship
					node.createRelationshipTo(node_email, Consts.RelTypes.CONTAINS);
				}
			}
			
			if (null != _person.getPhones()) {
				// build phone
				Iterator <Phone> itphone = _person.getPhones().iterator();
				
				while (itphone.hasNext()) {
					
					Phone phone = itphone.next();
					
					// create phone node
					Node node_phone = buildPhoneNode(_graph, phone);
					
					// adding the relationship
					node.createRelationshipTo(node_phone, Consts.RelTypes.CONTAINS);
				}
			}
			
			if (null != _person.getAddresses()) {
				// build address
				Iterator <Address> itaddress = _person.getAddresses().iterator();
				
				while (itaddress.hasNext()) {
					
					Address address = itaddress.next();
					
					// create address node
					Node node_address = buildAddressNode(_graph, address);
					
					// adding the relationship
					node.createRelationshipTo(node_address, Consts.RelTypes.CONTAINS);
				}
			}
			
			// update data
			updatePersonNode(_graph, node, _person);
			
			tx.success();
		}
		
		return node;
	}
	
	/**
	 * Update a person node with new information in graph.
	 * 
	 * @param _graph Main graph
	 * @param _node Node person to update
	 * @param _person Person information.
	 * @throws Exception If something goes wrong.
	 */
	public static void updatePersonNode(GraphDatabaseService _graph, Node _node, Person _person) throws Exception {
		
		// begin transaction
		try ( Transaction tx = _graph.beginTx() ) {
			
			if (_node.hasLabel(Consts.LABEL_PERSON)) {
			
				_node.setProperty(Consts.UID, _person.getUid());
				_node.setProperty(Consts.PERSON_FIRSTNAME, _person.getFirstname());
				_node.setProperty(Consts.PERSON_LASTNAME, _person.getLastname());
				_node.setProperty(Consts.PERSON_IMAGEUID, _person.getImageuid());
				
				if (null != _person.getEmails()) {
					// email
					Iterator<Email> itemail = _person.getEmails().iterator();
					
					while (itemail.hasNext()) {
						
						Email email = itemail.next();
						Node node_email = _graph.findNode(Consts.LABEL_EMAIL, Consts.UID, email.getUid());
						
						if (null == node_email) {
							
							node_email = buildEmailNode(_graph, email);
							_node.createRelationshipTo(node_email, Consts.RelTypes.CONTAINS);
						}
						else {
							updateEmailNode(_graph, node_email, email);
						}
					}
				}
				
				if (null != _person.getPhones()) {
					// phone
					Iterator<Phone> itphone = _person.getPhones().iterator();
					
					while (itphone.hasNext()) {
						
						Phone phone = itphone.next();
						Node node_phone = _graph.findNode(Consts.LABEL_PHONE, Consts.UID, phone.getUid());
						
						if (null == node_phone) {
							
							node_phone = buildPhoneNode(_graph, phone);
							_node.createRelationshipTo(node_phone, Consts.RelTypes.CONTAINS);
						}
						else {
							updatePhoneNode(_graph, node_phone, phone);
						}
					}
				}
				
				if (null != _person.getAddresses()) {
					// address
					Iterator<Address> itaddress = _person.getAddresses().iterator();
					
					while (itaddress.hasNext()) {
						
						Address address = itaddress.next();
						Node node_address = _graph.findNode(Consts.LABEL_ADDRESS, Consts.UID, address.getUid());
						
						if (null == node_address) {
							
							node_address = buildAddressNode(_graph, address);
							_node.createRelationshipTo(node_address, Consts.RelTypes.CONTAINS);
						}
						else {
							updateAddressNode(_graph, node_address, address);
						}
					}
				}
			}
			else {
				throw new Exception ("It's not a accessory node! ["+_node.getLabels()+"]");
			}
			
			tx.success();
		}
	}
	
	/**
	 * Build an email node in the graph with the email information.
	 *  
	 * @param _graph Main graph.
	 * @param _email Email information.
	 * @return Email node builded in graph.
	 * @throws Exception If something goes wrong.
	 */
	public static Node buildEmailNode(GraphDatabaseService _graph, Email _email) throws Exception {
		
		Node node = null;
		
		// begin transaction
		try ( Transaction tx = _graph.beginTx() ) {
			
			if (checkUidExist(_graph, Consts.LABEL_EMAIL, _email.getUid())) {
				throw new Exception ("Uid already exist : " + _email.getUid());
			}
			
			// create node
			node = _graph.createNode(Consts.LABEL_EMAIL);
			
			// update data
			updateEmailNode(_graph, node, _email);
			
			tx.success();
		}
		
		return node;
	}
	
	/**
	 * Update an email node in graph with new information.
	 * 
	 * @param _graph Main graph
	 * @param _node Email node to update
	 * @param _email New email information.
	 * @throws Exception If something goes wrong.
	 */
	public static void updateEmailNode(GraphDatabaseService _graph, Node _node, Email _email) throws Exception {
		
		// find the accessory by the serial number.
		try ( Transaction tx = _graph.beginTx() ) {
			
			if (_node.hasLabel(Consts.LABEL_EMAIL)) {
				
				_node.setProperty(Consts.UID, _email.getUid());
				_node.setProperty(Consts.EMAIL_EMAIL, _email.getEmail());
				
				if (EmailType.PERSONAL == _email.getType()) {
					_node.setProperty(Consts.EMAIL_TYPE, Consts.EMAIL_TYPE_PERSONAL);
				}
				else if (EmailType.WORK == _email.getType()) {
					_node.setProperty(Consts.EMAIL_TYPE, Consts.EMAIL_TYPE_WORK);
				}
				else if (EmailType.OTHER == _email.getType()) {
					_node.setProperty(Consts.EMAIL_TYPE, Consts.EMAIL_TYPE_OTHER);
				}
			}
			else {
				throw new Exception ("It's not an email node! ["+_node.getLabels()+"]");
			}
			
			tx.success();
		}
	}
	
	/**
	 * Build a phone node in graph with phone information.
	 * 
	 * @param _graph Main graph 
	 * @param _phone Phone information.
	 * @return Node builded in graph.
	 * @throws Exception If something goes wrong.
	 */
	public static Node buildPhoneNode(GraphDatabaseService _graph, Phone _phone) throws Exception {
		
		Node node = null;
		
		// begin transaction
		try ( Transaction tx = _graph.beginTx() ) {
			
			if (checkUidExist(_graph, Consts.LABEL_PHONE, _phone.getUid())) {
				throw new Exception ("Uid already exist : " + _phone.getUid());
			}
			
			// create node
			node = _graph.createNode(Consts.LABEL_PHONE);
			
			// update data
			updatePhoneNode(_graph, node, _phone);
			
			tx.success();
		}
		
		return node;
	}
	
	/**
	 * Update a phone node in graph with new information.
	 * 
	 * @param _graph Main graph
	 * @param _node Phone node to update.
	 * @param _phone New phone information.
	 * @throws Exception If something goes wrong.
	 */
	public static void updatePhoneNode(GraphDatabaseService _graph, Node _node, Phone _phone) throws Exception {
		
		// begin transaction
		try ( Transaction tx = _graph.beginTx() ) {
			
			if (_node.hasLabel(Consts.LABEL_PHONE)) {
				
				_node.setProperty(Consts.UID, _phone.getUid());
				_node.setProperty(Consts.PHONE_NUMBER, _phone.getPhone());
				
				if (PhoneType.HOME == _phone.getType()) {
					_node.setProperty(Consts.PHONE_TYPE, Consts.PHONE_TYPE_HOME);
				}
				else if (PhoneType.WORK == _phone.getType()) {
					_node.setProperty(Consts.PHONE_TYPE, Consts.PHONE_TYPE_WORK);
				}
				else if (PhoneType.CELL == _phone.getType()) {
					_node.setProperty(Consts.PHONE_TYPE, Consts.PHONE_TYPE_CELL);
				}
				else if (PhoneType.OTHER == _phone.getType()) {
					_node.setProperty(Consts.PHONE_TYPE, Consts.PHONE_TYPE_OTHER);
				}
			}
			else {
				throw new Exception ("It's not an phone node! ["+_node.getLabels()+"]");
			}
			
			tx.success();
		}
	}
	
	/**
	 * Build an address node in graph with address information.
	 * 
	 * @param _graph Main graph.
	 * @param _address Address information.
	 * @return Address node builded in graph.
	 * @throws Exception If something goes wrong.
	 */
	public static Node buildAddressNode(GraphDatabaseService _graph, Address _address) throws Exception {
		
		Node node = null;
		
		// begin transaction
		try ( Transaction tx = _graph.beginTx() ) {
			
			if (checkUidExist(_graph, Consts.LABEL_ADDRESS, _address.getUid())) {
				throw new Exception ("Uid already exist : " + _address.getUid());
			}
			
			// create node
			node = _graph.createNode(Consts.LABEL_ADDRESS);
			
			// update data
			updateAddressNode(_graph, node, _address);
			
			tx.success();
		}
		
		return node;
	}
	
	/**
	 * Update address node in graph with address information.
	 * 
	 * @param _graph Main graph.
	 * @param _node Node to update.
	 * @param _address Addess information.
	 * @throws Exception If something goes wrong.
	 */
	public static void updateAddressNode(GraphDatabaseService _graph, Node _node, Address _address) throws Exception {
		
		// begin transaction
		try ( Transaction tx = _graph.beginTx() ) {
			
			if (_node.hasLabel(Consts.LABEL_ADDRESS)) {
				
				_node.setProperty(Consts.UID, _address.getUid());
				_node.setProperty(Consts.ADDRESS_STREET, _address.getStreet());
				_node.setProperty(Consts.ADDRESS_CITY, _address.getCity());
				_node.setProperty(Consts.ADDRESS_REGION, _address.getRegion());
				_node.setProperty(Consts.ADDRESS_ZIPCODE, _address.getZipcode());
				_node.setProperty(Consts.ADDRESS_COUNTRY, _address.getCountry());
				
				if (AddressType.HOME == _address.getType()) {
					_node.setProperty(Consts.ADDRESS_TYPE, Consts.ADDRESS_TYPE_HOME);
				}
				else if (AddressType.WORK == _address.getType()) {
					_node.setProperty(Consts.ADDRESS_TYPE, Consts.ADDRESS_TYPE_WORK);
				}
				else if (AddressType.OTHER == _address.getType()) {
					_node.setProperty(Consts.ADDRESS_TYPE, Consts.ADDRESS_TYPE_OTHER);
				}
			}
			else {
				throw new Exception ("It's not an address node! ["+_node.getLabels()+"]");
			}
			
			tx.success();
		}
	}
	
	
	
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
			
			if (null != _accessory.getServices()) {
				// build services
				Iterator <Service> it = _accessory.getServices().iterator();
				
				while (it.hasNext()) {
					Service service = it.next();
					
					// create service node
					Node node_ser = buildServiceNode(_graph, service);
					
					// adding the relationship
					node.createRelationshipTo(node_ser, Consts.RelTypes.CONTAINS);
				}
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
		
		// begin transaction
		try ( Transaction tx = _graph.beginTx() ) {
			
			if (_node.hasLabel(Consts.LABEL_ACCESSORY)) {
			
				_node.setProperty(Consts.UID, _accessory.getUid());
				_node.setProperty(Consts.ACCESSORY_LABEL, _accessory.getLabel());
				_node.setProperty(Consts.ACCESSORY_MODEL, _accessory.getModel());
				_node.setProperty(Consts.ACCESSORY_MANUFACTURER, _accessory.getManufacturer());
				_node.setProperty(Consts.ACCESSORY_SERIALNUMBER, _accessory.getSerialnumber());
				_node.setProperty(Consts.ACCESSORY_ISREGISTER, (_accessory.isRegister() ? "true" : "false"));
				_node.setProperty(Consts.ACCESSORY_IMAGEURL, _accessory.getImageurl());
				
				if (AccessoryType.AUTOMATIC_DOOR == _accessory.getType()) {
					_node.setProperty(Consts.ACCESSORY_TYPE, Consts.ACC_TYPE_AUTOMATIC_DOOR);
				}
				else if (AccessoryType.CAM == _accessory.getType()) {
					_node.setProperty(Consts.ACCESSORY_TYPE, Consts.ACC_TYPE_CAM);
				}
				else if (AccessoryType.CONTROL_BOARD == _accessory.getType()) {
					_node.setProperty(Consts.ACCESSORY_TYPE, Consts.ACC_TYPE_CONTROL_BOARD);
				}
				else if (AccessoryType.FAN == _accessory.getType()) {
					_node.setProperty(Consts.ACCESSORY_TYPE, Consts.ACC_TYPE_FAN);
				}
				else if (AccessoryType.LIGHT == _accessory.getType()) {
					_node.setProperty(Consts.ACCESSORY_TYPE, Consts.ACC_TYPE_LIGHT);
				}
				else if (AccessoryType.LIGHT_COLORED == _accessory.getType()) {
					_node.setProperty(Consts.ACCESSORY_TYPE, Consts.ACC_TYPE_LIGHT_COLORED);
				}
				else if (AccessoryType.LIGHT_DIMMABLE == _accessory.getType()) {
					_node.setProperty(Consts.ACCESSORY_TYPE, Consts.ACC_TYPE_DIMMABLE);
				}
				else if (AccessoryType.LIGHT_COLORED_DIMMABLE == _accessory.getType()) {
					_node.setProperty(Consts.ACCESSORY_TYPE, Consts.ACC_TYPE_LIGHT_COLORED_DIMMABLE);
				}
				else if (AccessoryType.LOCK_MECHANISM == _accessory.getType()) {
					_node.setProperty(Consts.ACCESSORY_TYPE, Consts.ACC_TYPE_LOCK_MECHANISM);
				}
				else if (AccessoryType.SWITCH == _accessory.getType()) {
					_node.setProperty(Consts.ACCESSORY_TYPE, Consts.ACC_TYPE_SWITCH);
				}
				else if (AccessoryType.THERMOSTAT == _accessory.getType()) {
					_node.setProperty(Consts.ACCESSORY_TYPE, Consts.ACC_TYPE_THERMOSTAT);
				}
				else {
					throw new Exception ("It's not a valid accessory type! ["+_accessory.getType()+"]");
				}
				
				if (AccessoryProtocolType.HAP == _accessory.getProtocoltype()) {
					_node.setProperty(Consts.ACCESSORY_PROTOCOLTYPE, Consts.ACC_PROTOCOL_TYPE_HAP);
				}
				else if (AccessoryProtocolType.JSON_SIMULATION == _accessory.getProtocoltype()) {
					_node.setProperty(Consts.ACCESSORY_PROTOCOLTYPE, Consts.ACC_PROTOCOL_TYPE_JSON_SIMULATION);
				}
				else if (AccessoryProtocolType.ZIGBEE == _accessory.getProtocoltype()) {
					_node.setProperty(Consts.ACCESSORY_PROTOCOLTYPE, Consts.ACC_PROTOCOL_TYPE_ZIGBEE);
				}
				else if (AccessoryProtocolType.ZWAVE == _accessory.getProtocoltype()) {
					_node.setProperty(Consts.ACCESSORY_PROTOCOLTYPE, Consts.ACC_PROTOCOL_TYPE_ZWAVE);
				}
				else {
					throw new Exception ("It's not a valid accessory protocol type! ["+_accessory.getType()+"]");
				}
				
				if (null != _accessory.getServices()) {
					Iterator<Service> it = _accessory.getServices().iterator();
					while (it.hasNext()) {
						Service service = it.next();
						updateServiceNode(_graph, _graph.findNode(Consts.LABEL_SERVICE, Consts.UID, service.getUid()), service);
					}
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
			
			if (null != _service.getCharacteristics()) {
				// build characteristics
				Iterator <Characteristic> it = _service.getCharacteristics().iterator();
				
				while (it.hasNext()) {
					Characteristic characteristic = it.next();
					
					// create the node
					Node node_ch = buildCharacteristicNode(_graph, characteristic);
					
					// adding relationship
					node.createRelationshipTo(node_ch, Consts.RelTypes.CONTAINS);
				}
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
		
		// begin transaction
		try ( Transaction tx = _graph.beginTx() ) {
			
			if (_node.hasLabel(Consts.LABEL_SERVICE)) {
				
				_node.setProperty(Consts.UID, _service.getUid());
				_node.setProperty(Consts.SERVICE_NAME, _service.getName());
				
				if (null != _service.getCharacteristics()) {
					Iterator<Characteristic> it = _service.getCharacteristics().iterator();
					while (it.hasNext()) {
						Characteristic characteristic = it.next();
						updateCharacteristicNode(_graph, _graph.findNode(Consts.LABEL_CHARACTERISTIC, Consts.UID, characteristic.getUid()), characteristic);
					}
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
		
		// begin transaction
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
		
		// begin transaction
		try ( Transaction tx = _graph.beginTx() ) {
			
			if (_node.hasLabel(Consts.LABEL_CHARACTERISTIC)) {
				
				_node.setProperty(Consts.UID, _characteristic.getUid());
				_node.setProperty(Consts.CH_LABEL, _characteristic.getLabel());
				
				// TYPE
				if (CharacteristicType.BOOLEAN == _characteristic.getType()) {
					_node.setProperty(Consts.CH_TYPE, Consts.CH_TYPE_BOOLEAN);
					_node.setProperty(Consts.CH_DATA, (_characteristic.getDataBoolean() ? "true" : "false"));
				}
				else if (CharacteristicType.ENUM == _characteristic.getType()) {
					_node.setProperty(Consts.CH_TYPE, Consts.CH_TYPE_ENUM);
					_node.setProperty(Consts.CH_DATA, _characteristic.getDataEnum());
				}
				else if (CharacteristicType.FLOAT == _characteristic.getType()) {
					_node.setProperty(Consts.CH_TYPE, Consts.CH_TYPE_FLOAT);
					_node.setProperty(Consts.CH_DATA, String.valueOf(_characteristic.getDataFloat()));
				}
				else if (CharacteristicType.INTEGER == _characteristic.getType()) {
					_node.setProperty(Consts.CH_TYPE, Consts.CH_TYPE_INTEGER);
					_node.setProperty(Consts.CH_DATA, String.valueOf(_characteristic.getDataInteger()));
				}
				else if (CharacteristicType.STATIC_STRING == _characteristic.getType()) {
					_node.setProperty(Consts.CH_TYPE, Consts.CH_TYPE_STRING);
					_node.setProperty(Consts.CH_DATA, _characteristic.getData());
				}
				else if (CharacteristicType.WRITE_ONLY_BOOLEAN == _characteristic.getType()) {
					_node.setProperty(Consts.CH_TYPE, Consts.CH_TYPE_WRITE_ONLY_BOOLEAN);
					_node.setProperty(Consts.CH_DATA, (_characteristic.getDataBoolean() ? "true" : "false"));
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
	 * Delete node and his children in graph.
	 * 
	 * @param _graph Graph database.
	 * @param _label Label of the node to delete.
	 * @param _uid Uid of the node to delete.
	 * @return true if the node was deleted.
	 * @throws Exception Throw exception if something goes wrong.
	 */
	public static boolean deleteNodeWithChildren(GraphDatabaseService _graph, Label _label, String _uid) throws Exception {
		boolean ret_val = false;
		
		// begin transaction
		try ( Transaction tx = _graph.beginTx() ) {
			
			// find the node to delete
			ResourceIterator<Node> nodes = _graph.findNodes(_label, Consts.UID, _uid);
			while (nodes.hasNext()) {
			
				Node node = nodes.next();
				Iterator<Relationship> it = node.getRelationships(Consts.RelTypes.CONTAINS).iterator();
				
				// pass all relationship to delete children
				while (it.hasNext()) {
					Relationship relation = it.next();
					Node child_node = relation.getEndNode();
					
					// delete all children node
					if (child_node.getId() != node.getId()) {
						
						Iterator<Label> labels = child_node.getLabels().iterator();						
						if (labels.hasNext()) {
							
							Label label = labels.next();
							relation.delete(); // delete child relation relation
							deleteNodeWithChildren(_graph, label, (String)child_node.getProperty(Consts.UID)); // delete node and children
						}						
					}
					else{
						relation.delete(); // delete the relation with parent
					}
				}
				node.delete(); // delete the node
			}
			
			tx.success();
		}
		
		return ret_val;
	}
	
	/**
	 * Build an asset node in graph with asset information.
	 * 
	 * @param _graph Main graph 
	 * @param _asset Asset information.
	 * @return Node builded in graph.
	 * @throws Exception If something goes wrong.
	 */
	public static Node buildAssetNode(GraphDatabaseService _graph, Asset _asset) throws Exception {
		
		Node node = null;
		
		// begin transaction
		try ( Transaction tx = _graph.beginTx() ) {
			
			if (checkUidExist(_graph, Consts.LABEL_ASSET, _asset.getUid())) {
				throw new Exception ("Uid already exist : " + _asset.getUid());
			}
			
			// create node
			node = _graph.createNode(Consts.LABEL_ASSET);
			
			// update data
			updateAssetNode(_graph, node, _asset);
			
			tx.success();
		}
		
		return node;
	}
	
	/**
	 * Update an asset node in graph with new information.
	 * 
	 * @param _graph Main graph
	 * @param _node Asset node to update.
	 * @param _asset New asset information.
	 * @throws Exception If something goes wrong.
	 */
	public static void updateAssetNode(GraphDatabaseService _graph, Node _node, Asset _asset) throws Exception {
		
		// begin transaction
		try ( Transaction tx = _graph.beginTx() ) {
			
			if (_node.hasLabel(Consts.LABEL_ASSET)) {
				
				_node.setProperty(Consts.UID, _asset.getUid());
				_node.setProperty(Consts.ASSET_LABEL, _asset.getLabel());
				
				// type
				if (AssetType.IMAGE == _asset.getType()) {
					_node.setProperty(Consts.ASSET_TYPE, Consts.ASSET_TYPE_IMAGE);
				}
				else if (AssetType.FILE == _asset.getType()) {
					_node.setProperty(Consts.ASSET_TYPE, Consts.ASSET_TYPE_FILE);
				}
				else if (AssetType.OTHER == _asset.getType()) {
					_node.setProperty(Consts.ASSET_TYPE, Consts.ASSET_TYPE_OTHER);
				}
				
				// mode
				if (AssetMode.SYSTEM == _asset.getMode()) {
					_node.setProperty(Consts.ASSET_MODE, Consts.ASSET_MODE_SYSTEM);
				}
				else if (AssetMode.USER == _asset.getMode()) {
					_node.setProperty(Consts.ASSET_MODE, Consts.ASSET_MODE_USER);
				}
			}
			else {
				throw new Exception ("It's not an asset node! ["+_node.getLabels()+"]");
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
		
		// begin transaction
		try ( Transaction tx = _graph.beginTx() ) {
			
			ResourceIterator<Node> nodes = _graph.findNodes(_label, Consts.UID, _uid);
			ret_val = nodes.hasNext();
			
			tx.success();
		}
		
		return ret_val;
	}
}
