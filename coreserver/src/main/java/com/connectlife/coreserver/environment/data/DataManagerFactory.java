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
import com.clapi.data.Data;
import com.clapi.data.Email;
import com.clapi.data.Home;
import com.clapi.data.Person;
import com.clapi.data.Phone;
import com.clapi.data.Room;
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
				
				while (ipersons.hasNext()) {
					Node person = ipersons.next();
					Vector<Person> persons = new Vector<Person>();
					persons.add(buildPerson(_graph, person));
					ret_data.setPersons(persons);
				}
				
				ResourceIterator<Node> ihomes = _graph.findNodes(Consts.LABEL_HOME);
				while (ihomes.hasNext()) {
					Node home = ihomes.next();
					Vector<Home> homes = new Vector<Home>();
					homes.add(buildHome(_graph, home));
					ret_data.setHomes(homes);
				}
				
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
	 * @param node Person node.
	 * @return Person
	 * @throws Exception Throw an exception is something goes wrong.
	 */
	public static Person buildPerson(GraphDatabaseService _graph, Node _node) throws Exception {
		
		Person ret_person = new Person("","");
		
		ret_person.setUid((String) _node.getProperty(Consts.PERSON_UID));
		ret_person.setFirstname((String) _node.getProperty(Consts.PERSON_FIRSTNAME));
		ret_person.setLastname((String)_node.getProperty(Consts.PERSON_LASTNAME));
		ret_person.setImageurl((String)_node.getProperty(Consts.PERSON_IMAGEURL));
		
		try ( Transaction tx = _graph.beginTx() ) {
			
			Iterator<Relationship> it = _node.getRelationships(Consts.RelTypes.CONTAINS).iterator();
			
			while (it.hasNext()) {
				Relationship relation = it.next();
				Node node = relation.getEndNode();
				
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
			
			tx.success();
		}
		
		return ret_person;
	}
	
	/**
	 * Build a Address data object from a Node of address.
	 * 
	 * @param node Address node.
	 * @return Address
	 * @throws Exception Throw an exception is something goes wrong.
	 */
	public static Address buildAddress(Node _node) throws Exception {
		
		Address ret = null;
		
		if (_node.getProperty(Consts.ADDRESS_TYPE).equals(Consts.ADDRESS_TYPE_HOME)) {
			ret = new Address( 	(String)_node.getProperty(Consts.ADDRESS_UID),
							 	Address.AddressType.HOME,
							 	(String)_node.getProperty(Consts.ADDRESS_STREET),
							 	(String)_node.getProperty(Consts.ADDRESS_CITY),
							 	(String)_node.getProperty(Consts.ADDRESS_REGION),
							 	(String)_node.getProperty(Consts.ADDRESS_ZIPCODE),
							 	(String)_node.getProperty(Consts.ADDRESS_COUNTRY));
		} 
		else if (_node.getProperty(Consts.ADDRESS_TYPE).equals(Consts.ADDRESS_TYPE_WORK)) {
			ret = new Address( 	(String)_node.getProperty(Consts.ADDRESS_UID),
							 	Address.AddressType.WORK,
							 	(String)_node.getProperty(Consts.ADDRESS_STREET),
							 	(String)_node.getProperty(Consts.ADDRESS_CITY),
							 	(String)_node.getProperty(Consts.ADDRESS_REGION),
							 	(String)_node.getProperty(Consts.ADDRESS_ZIPCODE),
							 	(String)_node.getProperty(Consts.ADDRESS_COUNTRY));
}
		else if (_node.getProperty(Consts.ADDRESS_TYPE).equals(Consts.ADDRESS_TYPE_OTHER)) {
			ret = new Address( 	(String)_node.getProperty(Consts.ADDRESS_UID),
							 	Address.AddressType.OTHER,
							 	(String)_node.getProperty(Consts.ADDRESS_STREET),
							 	(String)_node.getProperty(Consts.ADDRESS_CITY),
							 	(String)_node.getProperty(Consts.ADDRESS_REGION),
							 	(String)_node.getProperty(Consts.ADDRESS_ZIPCODE),
							 	(String)_node.getProperty(Consts.ADDRESS_COUNTRY));
		}
		else {
			throw new Exception ("Error! Address type not supported.");
		}
		
		return ret;
	}
	
	/**
	 * Build a Email data object from a Node of email.
	 * 
	 * @param node Email node.
	 * @return Email
	 * @throws Exception Throw an exception is something goes wrong.
	 */
	public static Email buildEmail(Node _node) throws Exception {
		
		Email ret = null;
		
		if (_node.getProperty(Consts.EMAIL_TYPE).equals(Consts.EMAIL_TYPE_PERSONAL)) {
			ret = new Email((String)_node.getProperty(Consts.EMAIL_UID),
							(String)_node.getProperty(Consts.EMAIL_EMAIL),
							Email.EmailType.PERSONAL);
		} 
		else if (_node.getProperty(Consts.EMAIL_TYPE).equals(Consts.EMAIL_TYPE_WORK)) {
			ret = new Email((String)_node.getProperty(Consts.EMAIL_UID),
					(String)_node.getProperty(Consts.EMAIL_EMAIL),
					Email.EmailType.WORK);
		} 
		else if (_node.getProperty(Consts.EMAIL_TYPE).equals(Consts.EMAIL_TYPE_OTHER)) {
			ret = new Email((String)_node.getProperty(Consts.EMAIL_UID),
					(String)_node.getProperty(Consts.EMAIL_EMAIL),
					Email.EmailType.OTHER);
		}
		else {
			throw new Exception ("Error! Email type not supported.");
		}
		
		return ret;
	}
	
	/**
	 * Build a Phone data object from a Node of phone.
	 * 
	 * @param node Phone node.
	 * @return Phone
	 * @throws Exception Throw an exception is something goes wrong.
	 */
	public static Phone buildPhone(Node _node) throws Exception {
		
		Phone ret = null;
		
		if (_node.getProperty(Consts.PHONE_TYPE).equals(Consts.PHONE_TYPE_HOME)) {
			ret = new Phone((String)_node.getProperty(Consts.PHONE_UID),
							(String)_node.getProperty(Consts.PHONE_NUMBER),
							Phone.PhoneType.HOME);
		} 
		else if (_node.getProperty(Consts.PHONE_TYPE).equals(Consts.PHONE_TYPE_WORK)) {
			ret = new Phone((String)_node.getProperty(Consts.PHONE_UID),
					(String)_node.getProperty(Consts.PHONE_NUMBER),
					Phone.PhoneType.WORK);
		} 
		else if (_node.getProperty(Consts.PHONE_TYPE).equals(Consts.PHONE_TYPE_OTHER)) {
			ret = new Phone((String)_node.getProperty(Consts.PHONE_UID),
					(String)_node.getProperty(Consts.PHONE_NUMBER),
					Phone.PhoneType.OTHER);
		}
		else if (_node.getProperty(Consts.PHONE_TYPE).equals(Consts.PHONE_TYPE_CELL)) {
			ret = new Phone((String)_node.getProperty(Consts.PHONE_UID),
					(String)_node.getProperty(Consts.PHONE_NUMBER),
					Phone.PhoneType.CELL);
		}
		else {
			throw new Exception ("Error! Phone type not supported.");
		}
		
		return ret;
	}
	
	/**
	 * Build a Home data object from a Node of home.
	 * 
	 * @param _graph GraphDatabaseService use to build data.
	 * @param node Home node.
	 * @return Home
	 * @throws Exception Throw an exception is something goes wrong.
	 */
	public static Home buildHome(GraphDatabaseService _graph, Node _node) throws Exception {
		
		Home ret_home = new Home("","");
		
		ret_home.setUid((String) _node.getProperty(Consts.HOME_UID));
		ret_home.setLabel((String) _node.getProperty(Consts.HOME_LABEL));
		ret_home.setImageurl((String)_node.getProperty(Consts.HOME_IMAGEURL));
		
		try ( Transaction tx = _graph.beginTx() ) {
			
			Iterator<Relationship> it = _node.getRelationships(Consts.RelTypes.CONTAINS).iterator();
			
			while (it.hasNext()) {
				Relationship relation = it.next();
				Node node = relation.getEndNode();
				
				if (node.hasLabel(Consts.LABEL_ZONE)) {
					Zone zone = buildZone(_graph, node);
					ret_home.addZone(zone);
				}
				else{
					throw new Exception ("Label not supported yet!");
				}
			}
			
			tx.success();
		}
		
		return ret_home;
	}
	
	/**
	 * Build a Zone data object from a Node of zone.
	 * 
	 * @param _graph GraphDatabaseService use to build data.
	 * @param node Zone node.
	 * @return Zone
	 * @throws Exception Throw an exception is something goes wrong.
	 */
	public static Zone buildZone(GraphDatabaseService _graph, Node _node) throws Exception {
		
		Zone ret = new Zone("","");
		
		ret.setUid((String) _node.getProperty(Consts.ZONE_UID));
		ret.setLabel((String) _node.getProperty(Consts.ZONE_LABEL));
		ret.setImageurl((String)_node.getProperty(Consts.ZONE_IMAGEURL));
		
		try ( Transaction tx = _graph.beginTx() ) {
			
			Iterator<Relationship> it = _node.getRelationships(Consts.RelTypes.CONTAINS).iterator();
			
			while (it.hasNext()) {
				Relationship relation = it.next();
				Node node = relation.getEndNode();
				
				if (node.hasLabel(Consts.LABEL_ROOM)) {
					Room room = buildRoom(_graph, node);
					ret.addRoom(room);
				}
				else{
					throw new Exception ("Label not supported yet!");
				}
			}
			
			tx.success();
		}
		
		return ret;
	}
	
	/**
	 * Build a Room data object from a Node of room.
	 * 
	 * @param _graph GraphDatabaseService use to build data.
	 * @param node Room node.
	 * @return Room
	 * @throws Exception Throw an exception is something goes wrong.
	 */
	public static Room buildRoom(GraphDatabaseService _graph, Node _node) throws Exception {
		
		Room ret = new Room("","");
		
		ret.setUid((String) _node.getProperty(Consts.ROOM_UID));
		ret.setLabel((String) _node.getProperty(Consts.ROOM_LABEL));
		ret.setImageurl((String)_node.getProperty(Consts.ROOM_IMAGEURL));
		
		try ( Transaction tx = _graph.beginTx() ) {
			
			Iterator<Relationship> it = _node.getRelationships(Consts.RelTypes.CONTAINS).iterator();
			
			while (it.hasNext()) {
				Relationship relation = it.next();
				Node node = relation.getEndNode();
				
				if (node.hasLabel(Consts.LABEL_ACCESSORY)) {
					Accessory accessory = buildAccessory(_graph, node);
					ret.addAccessory(accessory);
				}
				else{
					throw new Exception ("Label not supported yet!");
				}
			}
			
			tx.success();
		}
		
		return ret;
	}
	
	/**
	 * Build a Accessory data object from a Node of accessory.
	 * 
	 * @param _graph GraphDatabaseService use to build data.
	 * @param node Accessory node.
	 * @return Accessory
	 * @throws Exception Throw an exception is something goes wrong.
	 */
	public static Accessory buildAccessory(GraphDatabaseService _graph, Node _node) throws Exception {
		
		Accessory ret = new Accessory("","");
		
		ret.setUid((String) _node.getProperty(Consts.ACCESSORY_UID));
		ret.setLabel((String) _node.getProperty(Consts.ACCESSORY_LABEL));
		ret.setManufacturer((String)_node.getProperty(Consts.ACCESSORY_MANUFACTURER));
		ret.setImageurl((String)_node.getProperty(Consts.ACCESSORY_IMAGEURL));
		
		try ( Transaction tx = _graph.beginTx() ) {
			
			Iterator<Relationship> it = _node.getRelationships(Consts.RelTypes.CONTAINS).iterator();
			
			while (it.hasNext()) {
				Relationship relation = it.next();
				Node node = relation.getEndNode();
				
				if (node.hasLabel(Consts.LABEL_ACCESSORY)) {
					/* TODO: I'm here
					Accessory accessory = buildAccessory(node);
					ret.addAccessory(accessory);
					*/
				}
				else{
					throw new Exception ("Label not supported yet!");
				}
			}
			
			tx.success();
		}
		
		return ret;
	}

}
