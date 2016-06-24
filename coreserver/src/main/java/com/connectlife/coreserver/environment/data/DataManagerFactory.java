/**
 *  DataManagerFactory.java
 *  coreserver
 *
 *  Created by ericpinet on 2016-05-29.
 *  Copyright (c) 2016 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.connectlife.coreserver.environment.data;

import java.util.Iterator;
import java.util.Vector;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.ResourceIterator;
import org.neo4j.graphdb.Transaction;

import com.clapi.data.Accessory;
import com.clapi.data.Address;
import com.clapi.data.Characteristic;
import com.clapi.data.Characteristic.CharacteristicAccessMode;
import com.clapi.data.Characteristic.CharacteristicEventType;
import com.clapi.data.Characteristic.CharacteristicType;
import com.clapi.data.Data;
import com.clapi.data.Email;
import com.clapi.data.Home;
import com.clapi.data.Person;
import com.clapi.data.Phone;
import com.clapi.data.Room;
import com.clapi.data.Service;
import com.clapi.data.Zone;
import com.connectlife.coreserver.Consts;

/**
 * Data manager factory use to build Data object from Graph database.
 * 
 * @author ericpinet
 * <br> 2016-05-29
 */
public abstract class DataManagerFactory {
	
	/**
	 * Get a Data representation of the environment data.
	 * 
	 * @param _graph GraphDatabaseService use to build data.
	 * @return Data representation of the environment.
	 * @throws Exception Throw an exception is something goes wrong.
	 */
	public static Data prepareData(GraphDatabaseService _graph) throws Exception {
		
		Data ret_data = new Data();
		
		if (null != _graph) {
			
			try ( Transaction tx = _graph.beginTx() ) {
				
				ResourceIterator<Node> ipersons = _graph.findNodes(Consts.LABEL_PERSON);
				Vector<Person> persons = new Vector<Person>();
				
				while (ipersons.hasNext()) {
					Node person = ipersons.next();					
					persons.add(buildPerson(_graph, person));
				}
				ret_data.setPersons(persons);
				
				ResourceIterator<Node> ihomes = _graph.findNodes(Consts.LABEL_HOME);
				Vector<Home> homes = new Vector<Home>();
				
				while (ihomes.hasNext()) {
					Node home = ihomes.next();
					homes.add(buildHome(_graph, home));
				}
				ret_data.setHomes(homes);
				
				tx.success();
			}
		}
		else{
			throw new Exception ("Error! The DataManager must be correctly initialized before trying to get data.");
		}
		
		return ret_data;
	}
	
	/**
	 * Build a Person data object from a Node of person.
	 * 
	 * @param _graph GraphDatabaseService use to build data.
	 * @param _node Person node.
	 * @return Person
	 * @throws Exception Throw an exception is something goes wrong.
	 */
	public static Person buildPerson(GraphDatabaseService _graph, Node _node) throws Exception {
		
		Person ret_person = new Person("","");
		
		if (_node.hasLabel(Consts.LABEL_PERSON)) {
		
			ret_person.setUid((String) _node.getProperty(Consts.UID));
			ret_person.setFirstname((String) _node.getProperty(Consts.PERSON_FIRSTNAME));
			ret_person.setLastname((String)_node.getProperty(Consts.PERSON_LASTNAME));
			ret_person.setImageurl((String)_node.getProperty(Consts.PERSON_IMAGEURL));
			
			try ( Transaction tx = _graph.beginTx() ) {
				
				Iterator<Relationship> it = _node.getRelationships(Consts.RelTypes.CONTAINS).iterator();
				
				while (it.hasNext()) {
					Relationship relation = it.next();
					Node node = relation.getEndNode();
					
					if (node.getId() != _node.getId()) {
						if (node.hasLabel(Consts.LABEL_EMAIL)) {
							Email email = buildEmail(node);
							ret_person.addEmails(email);
						}
						else if (node.hasLabel(Consts.LABEL_PHONE)) {
							Phone phone = buildPhone(node);
							ret_person.addPhones(phone);
						}
						else if (node.hasLabel(Consts.LABEL_ADDRESS)) {
							Address address = buildAddress(node);
							ret_person.addAddress(address);
						}
						else{
							throw new Exception ("Label not supported yet!");
						}
					}
				}
				
				tx.success();
			}
		}
		else {
			throw new Exception ("It's not a person node! ["+_node.getLabels()+"]");
		}
		
		return ret_person;
	}
	
	/**
	 * Build a Address data object from a Node of address.
	 * 
	 * @param _node Address node.
	 * @return Address
	 * @throws Exception Throw an exception is something goes wrong.
	 */
	public static Address buildAddress(Node _node) throws Exception {
		
		Address ret = null;
		
		if (_node.hasLabel(Consts.LABEL_ADDRESS)) {
		
			if (_node.getProperty(Consts.ADDRESS_TYPE).equals(Consts.ADDRESS_TYPE_HOME)) {
				ret = new Address( 	(String)_node.getProperty(Consts.UID),
								 	Address.AddressType.HOME,
								 	(String)_node.getProperty(Consts.ADDRESS_STREET),
								 	(String)_node.getProperty(Consts.ADDRESS_CITY),
								 	(String)_node.getProperty(Consts.ADDRESS_REGION),
								 	(String)_node.getProperty(Consts.ADDRESS_ZIPCODE),
								 	(String)_node.getProperty(Consts.ADDRESS_COUNTRY));
			} 
			else if (_node.getProperty(Consts.ADDRESS_TYPE).equals(Consts.ADDRESS_TYPE_WORK)) {
				ret = new Address( 	(String)_node.getProperty(Consts.UID),
								 	Address.AddressType.WORK,
								 	(String)_node.getProperty(Consts.ADDRESS_STREET),
								 	(String)_node.getProperty(Consts.ADDRESS_CITY),
								 	(String)_node.getProperty(Consts.ADDRESS_REGION),
								 	(String)_node.getProperty(Consts.ADDRESS_ZIPCODE),
								 	(String)_node.getProperty(Consts.ADDRESS_COUNTRY));
			}
			else if (_node.getProperty(Consts.ADDRESS_TYPE).equals(Consts.ADDRESS_TYPE_OTHER)) {
				ret = new Address( 	(String)_node.getProperty(Consts.UID),
								 	Address.AddressType.OTHER,
								 	(String)_node.getProperty(Consts.ADDRESS_STREET),
								 	(String)_node.getProperty(Consts.ADDRESS_CITY),
								 	(String)_node.getProperty(Consts.ADDRESS_REGION),
								 	(String)_node.getProperty(Consts.ADDRESS_ZIPCODE),
								 	(String)_node.getProperty(Consts.ADDRESS_COUNTRY));
			}
			else {
				throw new Exception ("Error! Address type not supported. ["+_node.getProperty(Consts.ADDRESS_TYPE)+"]");
			}
		}
		else {
			throw new Exception ("It's not a address node! ["+_node.getLabels()+"]");
		}
		
		return ret;
	}
	
	/**
	 * Build a Email data object from a Node of email.
	 * 
	 * @param _node Email node.
	 * @return Email
	 * @throws Exception Throw an exception is something goes wrong.
	 */
	public static Email buildEmail(Node _node) throws Exception {
		
		Email ret = null;
		
		if (_node.hasLabel(Consts.LABEL_EMAIL)) {
		
			if (_node.getProperty(Consts.EMAIL_TYPE).equals(Consts.EMAIL_TYPE_PERSONAL)) {
				ret = new Email((String)_node.getProperty(Consts.UID),
								(String)_node.getProperty(Consts.EMAIL_EMAIL),
								Email.EmailType.PERSONAL);
			} 
			else if (_node.getProperty(Consts.EMAIL_TYPE).equals(Consts.EMAIL_TYPE_WORK)) {
				ret = new Email((String)_node.getProperty(Consts.UID),
						(String)_node.getProperty(Consts.EMAIL_EMAIL),
						Email.EmailType.WORK);
			} 
			else if (_node.getProperty(Consts.EMAIL_TYPE).equals(Consts.EMAIL_TYPE_OTHER)) {
				ret = new Email((String)_node.getProperty(Consts.UID),
						(String)_node.getProperty(Consts.EMAIL_EMAIL),
						Email.EmailType.OTHER);
			}
			else {
				throw new Exception ("Email type not supported yet! ["+_node.getProperty(Consts.EMAIL_TYPE)+"]");
			}
		}
		else {
			throw new Exception ("It's not a email node! ["+_node.getLabels()+"]");
		}
		
		return ret;
	}
	
	/**
	 * Build a Phone data object from a Node of phone.
	 * 
	 * @param _node Phone node.
	 * @return Phone
	 * @throws Exception Throw an exception is something goes wrong.
	 */
	public static Phone buildPhone(Node _node) throws Exception {
		
		Phone ret = null;
		
		if (_node.hasLabel(Consts.LABEL_PHONE)) {
		
			if (_node.getProperty(Consts.PHONE_TYPE).equals(Consts.PHONE_TYPE_HOME)) {
				ret = new Phone((String)_node.getProperty(Consts.UID),
								(String)_node.getProperty(Consts.PHONE_NUMBER),
								Phone.PhoneType.HOME);
			} 
			else if (_node.getProperty(Consts.PHONE_TYPE).equals(Consts.PHONE_TYPE_WORK)) {
				ret = new Phone((String)_node.getProperty(Consts.UID),
						(String)_node.getProperty(Consts.PHONE_NUMBER),
						Phone.PhoneType.WORK);
			} 
			else if (_node.getProperty(Consts.PHONE_TYPE).equals(Consts.PHONE_TYPE_OTHER)) {
				ret = new Phone((String)_node.getProperty(Consts.UID),
						(String)_node.getProperty(Consts.PHONE_NUMBER),
						Phone.PhoneType.OTHER);
			}
			else if (_node.getProperty(Consts.PHONE_TYPE).equals(Consts.PHONE_TYPE_CELL)) {
				ret = new Phone((String)_node.getProperty(Consts.UID),
						(String)_node.getProperty(Consts.PHONE_NUMBER),
						Phone.PhoneType.CELL);
			}
			else {
				throw new Exception ("Phone type not supported yet! ["+_node.getProperty(Consts.PHONE_TYPE)+"]");
			}
		}
		else {
			throw new Exception ("It's not a phone node! ["+_node.getLabels()+"]");
		}
		
		return ret;
	}
	
	/**
	 * Build a Home data object from a Node of home.
	 * 
	 * @param _graph GraphDatabaseService use to build data.
	 * @param _node Home node.
	 * @return Home
	 * @throws Exception Throw an exception is something goes wrong.
	 */
	public static Home buildHome(GraphDatabaseService _graph, Node _node) throws Exception {
		
		Home ret_home = new Home("","");
		
		if(_node.hasLabel(Consts.LABEL_HOME)) {
			
			ret_home.setUid((String) _node.getProperty(Consts.UID));
			ret_home.setLabel((String) _node.getProperty(Consts.HOME_LABEL));
			ret_home.setImageurl((String)_node.getProperty(Consts.HOME_IMAGEURL));
			
			try ( Transaction tx = _graph.beginTx() ) {
				
				Iterator<Relationship> it = _node.getRelationships(Consts.RelTypes.CONTAINS).iterator();
				
				while (it.hasNext()) {
					Relationship relation = it.next();
					Node node = relation.getEndNode();
					
					if (node.getId() != _node.getId()) {
						if (node.hasLabel(Consts.LABEL_ZONE)) {
							Zone zone = buildZone(_graph, node);
							ret_home.addZone(zone);
						}
						else{
							throw new Exception ("Label not supported yet! ["+node.getLabels()+"]");
						}
					}
				}
				
				tx.success();
			}
		}
		else {
			throw new Exception ("It's not a home node! ["+_node.getLabels()+"]");
		}
		return ret_home;
	}
	
	/**
	 * Build a Zone data object from a Node of zone.
	 * 
	 * @param _graph GraphDatabaseService use to build data.
	 * @param _node Zone node.
	 * @return Zone
	 * @throws Exception Throw an exception is something goes wrong.
	 */
	public static Zone buildZone(GraphDatabaseService _graph, Node _node) throws Exception {
		
		Zone ret = new Zone("","");
		
		if (_node.hasLabel(Consts.LABEL_ZONE)) {
		
			ret.setUid((String) _node.getProperty(Consts.UID));
			ret.setLabel((String) _node.getProperty(Consts.ZONE_LABEL));
			ret.setImageurl((String)_node.getProperty(Consts.ZONE_IMAGEURL));
			
			try ( Transaction tx = _graph.beginTx() ) {
				
				Iterator<Relationship> it = _node.getRelationships(Consts.RelTypes.CONTAINS).iterator();
				
				while (it.hasNext()) {
					Relationship relation = it.next();
					Node node = relation.getEndNode();
					
					if (node.getId() != _node.getId()) {
						if (node.hasLabel(Consts.LABEL_ROOM)) {
							Room room = buildRoom(_graph, node);
							ret.addRoom(room);
						}
						else{
							throw new Exception ("Label not supported yet! ["+node.getLabels()+"]");
						}
					}
				}
				
				tx.success();
			}
		}
		else {
			throw new Exception ("It's not a zone node! ["+_node.getLabels()+"]");
		}
		
		return ret;
	}
	
	/**
	 * Build a Room data object from a Node of room.
	 * 
	 * @param _graph GraphDatabaseService use to build data.
	 * @param _node Room node.
	 * @return Room
	 * @throws Exception Throw an exception is something goes wrong.
	 */
	public static Room buildRoom(GraphDatabaseService _graph, Node _node) throws Exception {
		
		Room ret = new Room("","");
		
		if (_node.hasLabel(Consts.LABEL_ROOM)) {
		
			ret.setUid((String) _node.getProperty(Consts.UID));
			ret.setLabel((String) _node.getProperty(Consts.ROOM_LABEL));
			ret.setImageurl((String)_node.getProperty(Consts.ROOM_IMAGEURL));
			
			try ( Transaction tx = _graph.beginTx() ) {
				
				Iterator<Relationship> it = _node.getRelationships(Consts.RelTypes.CONTAINS).iterator();
				
				while (it.hasNext()) {
					Relationship relation = it.next();
					Node node = relation.getEndNode();
					
					if (node.getId() != _node.getId()) {
						if (node.hasLabel(Consts.LABEL_ACCESSORY)) {
							Accessory accessory = buildAccessory(_graph, node);
							ret.addAccessory(accessory);
						}
						else{
							throw new Exception ("Label not supported yet! ["+node.getLabels()+"]");
						}
					}
				}
				
				tx.success();
			}
		}
		
		return ret;
	}
	
	/**
	 * Build a Accessory data object from a Node of accessory.
	 * 
	 * @param _graph GraphDatabaseService use to build data.
	 * @param _node Accessory node.
	 * @return Accessory
	 * @throws Exception Throw an exception is something goes wrong.
	 */
	public static Accessory buildAccessory(GraphDatabaseService _graph, Node _node) throws Exception {
		
		Accessory ret = new Accessory("","");
		
		if (_node.hasLabel(Consts.LABEL_ACCESSORY)) {
			
			ret.setUid((String) _node.getProperty(Consts.UID));
			ret.setLabel((String) _node.getProperty(Consts.ACCESSORY_LABEL));
			ret.setModel((String)_node.getProperty(Consts.ACCESSORY_MODEL));
			ret.setManufacturer((String)_node.getProperty(Consts.ACCESSORY_MANUFACTURER));
			ret.setSerialnumber((String)_node.getProperty(Consts.ACCESSORY_SERIALNUMBER));
			ret.setRegister((boolean) _node.getProperty(Consts.ACCESSORY_ISREGISTER));
			ret.setImageurl((String)_node.getProperty(Consts.ACCESSORY_IMAGEURL));
			
			if (_node.getProperty(Consts.ACCESSORY_TYPE).equals(Consts.ACC_TYPE_AUTOMATIC_DOOR)) {
				ret.setType(Accessory.AccessoryType.AUTOMATIC_DOOR);
			}
			else if (_node.getProperty(Consts.ACCESSORY_TYPE).equals(Consts.ACC_TYPE_CAM)) {
				ret.setType(Accessory.AccessoryType.CAM);
			}
			else if (_node.getProperty(Consts.ACCESSORY_TYPE).equals(Consts.ACC_TYPE_CONTROL_BOARD)) {
				ret.setType(Accessory.AccessoryType.CONTROL_BOARD);
			}
			else if (_node.getProperty(Consts.ACCESSORY_TYPE).equals(Consts.ACC_TYPE_DIMMABLE)) {
				ret.setType(Accessory.AccessoryType.LIGHT_DIMMABLE);
			}
			else if (_node.getProperty(Consts.ACCESSORY_TYPE).equals(Consts.ACC_TYPE_FAN)) {
				ret.setType(Accessory.AccessoryType.FAN);
			}
			else if (_node.getProperty(Consts.ACCESSORY_TYPE).equals(Consts.ACC_TYPE_LIGHT)) {
				ret.setType(Accessory.AccessoryType.LIGHT);
			}
			else if (_node.getProperty(Consts.ACCESSORY_TYPE).equals(Consts.ACC_TYPE_LIGHT_COLORED)) {
				ret.setType(Accessory.AccessoryType.LIGHT_COLORED);
			}
			else if (_node.getProperty(Consts.ACCESSORY_TYPE).equals(Consts.ACC_TYPE_LIGHT_COLORED_DIMMABLE)) {
				ret.setType(Accessory.AccessoryType.LIGHT_COLORED_DIMMABLE);
			}
			else if (_node.getProperty(Consts.ACCESSORY_TYPE).equals(Consts.ACC_TYPE_LOCK_MECHANISM)) {
				ret.setType(Accessory.AccessoryType.LOCK_MECHANISM);
			}
			else if (_node.getProperty(Consts.ACCESSORY_TYPE).equals(Consts.ACC_TYPE_SWITCH)) {
				ret.setType(Accessory.AccessoryType.SWITCH);
			}
			else if (_node.getProperty(Consts.ACCESSORY_TYPE).equals(Consts.ACC_TYPE_THERMOSTAT)) {
				ret.setType(Accessory.AccessoryType.THERMOSTAT);
			}
			else {
				throw new Exception ("Accessory type not supported yet! ["+_node.getProperty(Consts.ACCESSORY_TYPE)+"]");
			}
			
			if (_node.getProperty(Consts.ACCESSORY_PROTOCOLTYPE).equals(Consts.ACC_PROTOCOL_TYPE_HAP)) {
				ret.setProtocoltype(Accessory.AccessoryProtocolType.HAP);
			}
			else if (_node.getProperty(Consts.ACCESSORY_PROTOCOLTYPE).equals(Consts.ACC_PROTOCOL_TYPE_JSON_SIMULATION)) {
				ret.setProtocoltype(Accessory.AccessoryProtocolType.JSON_SIMULATION);
			}
			else if (_node.getProperty(Consts.ACCESSORY_PROTOCOLTYPE).equals(Consts.ACC_PROTOCOL_TYPE_ZIGBEE)) {
				ret.setProtocoltype(Accessory.AccessoryProtocolType.ZIGBEE);
			}
			else if (_node.getProperty(Consts.ACCESSORY_PROTOCOLTYPE).equals(Consts.ACC_PROTOCOL_TYPE_ZWAVE)) {
				ret.setProtocoltype(Accessory.AccessoryProtocolType.ZWAVE);
			}
			else {
				throw new Exception ("Accessory protocol type not supported yet! ["+_node.getProperty(Consts.ACCESSORY_PROTOCOLTYPE)+"]");
			}
			
			try ( Transaction tx = _graph.beginTx() ) {
				
				Iterator<Relationship> it = _node.getRelationships(Consts.RelTypes.CONTAINS).iterator();
				
				while (it.hasNext()) {
					Relationship relation = it.next();
					Node node = relation.getEndNode();
					
					if (node.getId() != _node.getId()) {
						if (node.hasLabel(Consts.LABEL_SERVICE)) {
							Service service = buildService(_graph, node);
							ret.addService(service);
						}
						else{
							throw new Exception ("Label not supported yet! ["+node.getLabels()+"]");
						}
					}
				}
				
				tx.success();
			}
		}
		else {
			throw new Exception ("It's not a accessory node! ["+_node.getLabels()+"]");
		}
		
		return ret;
	}
	
	/**
	 * Build a Service data object from a Node of service.
	 * 
	 * @param _graph GraphDatabaseService use to build data.
	 * @param _node Service node.
	 * @return Service
	 * @throws Exception Throw an exception is something goes wrong.
	 */
	public static Service buildService(GraphDatabaseService _graph, Node _node) throws Exception {
		
		Service ret = new Service("","");
		
		if (_node.hasLabel(Consts.LABEL_SERVICE)) {
		
			ret.setUid((String) _node.getProperty(Consts.UID));
			ret.setName((String) _node.getProperty(Consts.SERVICE_NAME));
			
			try ( Transaction tx = _graph.beginTx() ) {
				
				Iterator<Relationship> it = _node.getRelationships(Consts.RelTypes.CONTAINS).iterator();
				
				while (it.hasNext()) {
					Relationship relation = it.next();
					Node node = relation.getEndNode();
					
					if (node.getId() != _node.getId()) {
						if (node.hasLabel(Consts.LABEL_CHARACTERISTIC)) {
							Characteristic characteristic = buildCharacteristic(node);
							ret.addCharacteristic(characteristic);
						}
						else{
							throw new Exception ("Label not supported yet! ["+node.getLabels()+"]");
						}
					}
				}
				
				tx.success();
			}
		}
		
		return ret;
	}
	
	/**
	 * Build a Characteristic data object from a Node of characteristic.
	 * 
	 * @param _node Characteristic node.
	 * @return Characteristic
	 * @throws Exception Throw an exception is something goes wrong.
	 */
	public static Characteristic buildCharacteristic(Node _node) throws Exception {
		
		Characteristic ret = null;
		CharacteristicType type = CharacteristicType.STATIC_STRING;
		CharacteristicAccessMode mode = CharacteristicAccessMode.READ_WRITE;
		CharacteristicEventType event = CharacteristicEventType.EVENT;
		
		if (_node.hasLabel(Consts.LABEL_CHARACTERISTIC)) {
			
			if (_node.getProperty(Consts.CH_TYPE).equals(Consts.CH_TYPE_BOOLEAN)) {
				type = CharacteristicType.BOOLEAN;
			} 
			else if (_node.getProperty(Consts.CH_TYPE).equals(Consts.CH_TYPE_ENUM)) {
				type = CharacteristicType.ENUM;
			}
			else if (_node.getProperty(Consts.CH_TYPE).equals(Consts.CH_TYPE_FLOAT)) {
				type = CharacteristicType.FLOAT;
			}
			else if (_node.getProperty(Consts.CH_TYPE).equals(Consts.CH_TYPE_INTEGER)) {
				type = CharacteristicType.INTEGER;
			}
			else if (_node.getProperty(Consts.CH_TYPE).equals(Consts.CH_TYPE_STRING)) {
				type = CharacteristicType.STATIC_STRING;
			}
			else if (_node.getProperty(Consts.CH_TYPE).equals(Consts.CH_TYPE_WRITE_ONLY_BOOLEAN)) {
				type = CharacteristicType.WRITE_ONLY_BOOLEAN;
			}
			else {
				throw new Exception ("Characteristic type not supported yet! ["+_node.getProperty(Consts.CH_TYPE)+"]");
			}
			
			if (_node.getProperty(Consts.CH_MODE).equals(Consts.CH_ACCESS_MODE_READ_ONLY)) {
				mode = CharacteristicAccessMode.READ_ONLY;
			}
			else if (_node.getProperty(Consts.CH_MODE).equals(Consts.CH_ACCESS_MODE_READ_WRITE)) {
				mode = CharacteristicAccessMode.READ_WRITE;
			}
			else if (_node.getProperty(Consts.CH_MODE).equals(Consts.CH_ACCESS_MODE_WRITE_ONLY)) {
				mode = CharacteristicAccessMode.WRITE_ONLY;
			}
			else {
				throw new Exception ("Characteristic access mode not supported yet! ["+_node.getProperty(Consts.CH_MODE)+"]");
			}
			
			if (_node.getProperty(Consts.CH_EVENT_TYPE).equals(Consts.CH_EVENT_TYPE_EVENT)) {
				event = CharacteristicEventType.EVENT;
			}
			else if (_node.getProperty(Consts.CH_EVENT_TYPE).equals(Consts.CH_EVENT_TYPE_NO_EVENT)) {
				event = CharacteristicEventType.NO_EVENT;
			}
			else {
				throw new Exception ("Characteristic event type not supported yet! ["+_node.getProperty(Consts.CH_EVENT_TYPE)+"]");
			}
			
			ret = new Characteristic(	(String)_node.getProperty(Consts.UID),
										(String)_node.getProperty(Consts.CH_LABEL), 
										mode, 
										type, 
										event, 
										(String)_node.getProperty(Consts.CH_DATA));
			
			
		}
		else {
			throw new Exception ("It's not a phone node! ["+_node.getLabels()+"]");
		}
		
		return ret;
	}
}
