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
import java.util.Optional;

import com.clapi.data.Accessory;
import com.clapi.data.Data;
import com.clapi.data.Home;
import com.clapi.data.Person;
import com.clapi.data.Room;
import com.clapi.data.Zone;

/**
 * Find processor for the environment.
 * 
 * @author ericpinet
 * <br> 2016-02-07
 */
public abstract class FindProcessor {
	
	/**
	 * Data environment use by this find processor;
	 */
	protected Data m_data;
	
	/**
	 * Find person by the uid, last name or first name.
	 * 
	 * @param _person Person to find.
	 * @return Person found, or null if not found.
	 */
	public Person findPerson(Person _person){
		Optional<Person> ret_person = null;
			
		if(false == _person.getUid().isEmpty()){
			ret_person =  m_data.getPersons()
								.stream()
								.filter( p -> p.getUid().equalsIgnoreCase(_person.getUid()) )
								.findFirst();
		}
		else{
			// if the last name is there we try to find the last name
			if(false == _person.getLastname().isEmpty()){
				ret_person =  m_data.getPersons()
									.stream()
									.filter( p -> p.getLastname().equalsIgnoreCase(_person.getLastname()) )
									.findFirst();
			}
			else{
				// if the first name is there we try to find the first name
				if(false == _person.getFirstname().isEmpty()){
					ret_person =  m_data.getPersons()
										.stream()
										.filter( p -> p.getFirstname().equalsIgnoreCase(_person.getFirstname()) )
										.findFirst();
				}
			} // ELSE: Nothing else to found.
		}// ENDIF
			
		if(null != ret_person && ret_person.isPresent())
			return ret_person.get();
		else
			return null;
	}
	
	/**
	 * Find room by Uid or Label.
	 * 
	 * @param _room Room to find. 
	 * @return Room found, null if not found.
	 */
	public Room findRoom(Room _room){
		Optional<Room> ret_room = null;
		boolean found = false;
		
		// iterate in home
		Iterator<Home> ihome = m_data.getHomes().iterator();
		while(ihome.hasNext() && false == found){
			Home home = ihome.next();
			
			// iterate in zone
			Iterator<Zone> izone = home.getZones().iterator();
			while(izone.hasNext() && false == found){
				Zone zone = izone.next();
				
				
				// if the uid
				if(false == _room.getUid().isEmpty()){
					ret_room =  zone.getRooms()
									.stream()
									.filter( r -> r.getUid().equalsIgnoreCase(_room.getUid()) )
									.findFirst();
					if(ret_room.isPresent())
						found = true;
				}
				else{
					// if the first name is there we try to find the first name
					if(false == _room.getLabel().isEmpty()){
						ret_room =  zone.getRooms()
										.stream()
										.filter( r -> r.getLabel().equalsIgnoreCase(_room.getLabel()) )
										.findFirst();
						if(ret_room.isPresent())
							found = true;
					}
				} // ELSE: Nothing else to found.
			}// WHILE: Zones
		}// WHILE: Homes
		
		if(null != ret_room && ret_room.isPresent())
			return ret_room.get();
		else
			return null;
	}
	
	/**
	 * Find the room where the accessory is added.
	 * 
	 * @param _accessory Accessory added in a room.
	 * @return Room where the accessory is located. Null if not found.
	 */
	public Room findRoom(Accessory _accessory){
		Room ret_room = null;
		Optional<Accessory> accessory = null;
		boolean found = false;
		
		// iterate in home
		Iterator<Home> ihome = m_data.getHomes().iterator();
		while(ihome.hasNext() && false == found){
			Home home = ihome.next();
			
			// iterate in zone
			Iterator<Zone> izone = home.getZones().iterator();
			while(izone.hasNext() && false == found){
				Zone zone = izone.next();
				
				// iterate in room
				Iterator<Room> iroom = zone.getRooms().iterator();
				while(iroom.hasNext() && false == found){
					Room room = iroom.next();
					
					// uid
					if(false == _accessory.getUid().isEmpty()){
						accessory =   room.getAccessories()
										.stream()
										.filter( a -> a.getUid().equalsIgnoreCase(_accessory.getUid()) )
										.findFirst();
						if(accessory.isPresent()){
							found = true;
							ret_room = room;
						}
					}
					else{
						// serial number
						if(false == _accessory.getSerialnumber().isEmpty()){
							accessory = room.getAccessories()
											.stream()
											.filter( a -> a.getSerialnumber().equalsIgnoreCase(_accessory.getSerialnumber()) )
											.findFirst();
							if(accessory.isPresent()){
								found = true;
								ret_room = room;
							}
						}
						else{
							// label
							if(false == _accessory.getLabel().isEmpty()){
								accessory = room.getAccessories()
												.stream()
												.filter( a -> a.getLabel().equalsIgnoreCase(_accessory.getLabel()) )
												.findFirst();
								if(accessory.isPresent()){
									found = true;
									ret_room = room;
								}
							}
						} // ELSE: Nothing else to found.
					}// ENDIF
				}// WHILE: Rooms
			}// WHILE: Zones
		}// WHILE: Homes
		
		return ret_room;
	}
	
	
	/**
	 * Find the accessory with this uid, label or serial number in the environment.
	 * 
	 * @param _accessory Accessory to be found.
	 * @return Accessory if found. Null if not found.
	 */
	public Accessory findAccessory(Accessory _accessory){
		Optional<Accessory> ret_acc = null;
		
		boolean found = false;
		
		// iterate in home
		Iterator<Home> ihome = m_data.getHomes().iterator();
		while(ihome.hasNext() && false == found){
			Home home = ihome.next();
			
			// iterate in zone
			Iterator<Zone> izone = home.getZones().iterator();
			while(izone.hasNext() && false == found){
				Zone zone = izone.next();
				
				// iterate in room
				Iterator<Room> iroom = zone.getRooms().iterator();
				while(iroom.hasNext() && false == found){
					Room room = iroom.next();
					
					// uid
					if(false == _accessory.getUid().isEmpty()){
						ret_acc =   room.getAccessories()
										.stream()
										.filter( a -> a.getUid().equalsIgnoreCase(_accessory.getUid()) )
										.findFirst();
						if(ret_acc.isPresent())
							found = true;
					}
					else{
						// serial number
						if(false == _accessory.getSerialnumber().isEmpty()){
							ret_acc =   room.getAccessories()
											.stream()
											.filter( a -> a.getSerialnumber().equalsIgnoreCase(_accessory.getSerialnumber()) )
											.findFirst();
							if(ret_acc.isPresent())
								found = true;
						}
						else{
							// label
							if(false == _accessory.getLabel().isEmpty()){
								ret_acc =   room.getAccessories()
												.stream()
												.filter( a -> a.getLabel().equalsIgnoreCase(_accessory.getLabel()) )
												.findFirst();
								if(ret_acc.isPresent())
									found = true;
							}
						} // ELSE: Nothing else to found.
					}// ENDIF
				}// WHILE: Rooms
			}// WHILE: Zones
		}// WHILE: Homes
		
		if(null != ret_acc && ret_acc.isPresent())
			return ret_acc.get();
		else
			return null;
	}
}
