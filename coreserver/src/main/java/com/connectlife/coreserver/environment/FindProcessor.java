/**
 *  FindProcessor.java
 *  coreserver
 *
 *  Created by ericpinet on 2016-02-07.
 *  Copyright (c) 2016 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.connectlife.coreserver.environment;

import java.util.Iterator;
import java.util.List;

import com.clapi.data.Accessory;
import com.clapi.data.Data;
import com.clapi.data.Email;
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
		Person ret_person = null;
		boolean found = false;
		
		Iterator<Person> iperson = m_data.getPersons().iterator();
		while(iperson.hasNext() && false == found){
			Person person = iperson.next();
			
			if(false == _person.getUid().isEmpty()){
				if(person.getUid().equals(_person.getUid())){
					found = true;
					ret_person = person;
				} // ELSE: Person not found. Do noting.
			}
			else{
				// if the last name is there we try to find the last name
				if(false == _person.getLastname().isEmpty()){
					if(person.getLastname().equals(_person.getLastname())){
						found = true;
						ret_person = person;
					} // ELSE: Person not found. Do noting.
				}
				else{
					// if the first name is there we try to find the first name
					if(false == _person.getFirstname().isEmpty()){
						if(person.getFirstname().equals(_person.getFirstname())){
							found = true;
							ret_person = person;
						} // ELSE: Person not found. Do noting.
					}
				} // ELSE: Nothing else to found.
			}
			
		}// end while person
		
		return ret_person;
	}
	
	/**
	 * Find person by the uid of the mail or the mail address.
	 * 
	 * @param _email Email to find.
	 * @return Person found, or null if not found.
	 */
	public Person findPerson(Email _email){
		Person ret_person = null;
		boolean found = false;
		
		Iterator<Person> iperson = m_data.getPersons().iterator();
		while(iperson.hasNext() && false == found){
			Person person = iperson.next();
			
			if(false == _email.getUid().isEmpty()){
				List<Email> emails = person.getEmails();
				for(int i=0; i<emails.size(); i++)
				{
					if(emails.get(i).getUid().equals(_email.getUid()))
					{
						ret_person = person;
						found = true;
						break;
					}
				}
			}
			else{
				// if the last name is there we try to find the last name
				if(false == _email.getEmail().isEmpty()){
					
					List<Email> emails = person.getEmails();
					for(int i=0; i<emails.size(); i++)
					{
						if(emails.get(i).getEmail().equals(_email.getEmail()))
						{
							ret_person = person;
							found = true;
							break;
						}
					}
				}
			}
			
		}// end while person
		
		return ret_person;
	}
	/**
	 * Find room by Uid or Label.
	 * 
	 * @param _room Room to find. 
	 * @return Room found, null if not found.
	 */
	public Room findRoom(Room _room){
		Room ret_room = null;
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
					
					// if the uid is there we try to find the uid
					if(false == _room.getUid().isEmpty()){
						if(room.getUid().equals(_room.getUid())){
							found = true;
							ret_room = room;
						} // ELSE: Room not found. Do noting.
					}
					else{
						// if the label is there we try to find the label
						if(false == _room.getLabel().isEmpty()){
							if(room.getLabel().equals(_room.getLabel())){
								found = true;
								ret_room = room;
							} // ELSE: Room not found. Do noting.
						}// ELSE: Nothing else to found.
					}
				}// WHILE: Rooms
			}// WHILE: Zones
		}// WHILE: Homes
		
		return ret_room;
	}
	
	/**
	 * Find the room where the accessory is added.
	 * 
	 * @param _accessory Accessory added in a room.
	 * @return Room where the accessory is located. Null if not found.
	 */
	public Room findRoom(Accessory _accessory){
		Room ret_room = null;
		
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
					
					// iterate in accessory
					Iterator<Accessory> iaccessory = room.getAccessories().iterator();
					while(iaccessory.hasNext() && false == found){
						Accessory accessory = iaccessory.next();
						
						// if the uid is there we try to find the uid
						if(false == _accessory.getUid().isEmpty()){
							if(accessory.getUid().equals(_accessory.getUid())){
								found = true;
								ret_room = room;
							} // ELSE: Accessory not found. Do noting.
						}
						else{
							// if the serial number is there we try to find the serial number
							if(false == _accessory.getSerialnumber().isEmpty()){
								if(accessory.getSerialnumber().equals(_accessory.getSerialnumber())){
									found = true;
									ret_room = room;
								} // ELSE: Accessory not found. Do noting.
							}
							else{
								// if the label is there we try to find the label
								if(false == _accessory.getLabel().isEmpty()){
									if(accessory.getLabel().equals(_accessory.getLabel())){
										found = true;
										ret_room = room;
									} // ELSE: Accessory not found. Do noting.
								}// ELSE: Nothing else to find.
							}
						}
						
					}// WHILE: Accessories
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
		Accessory ret_acc = null;
		
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
					
					// iterate in accessory
					Iterator<Accessory> iaccessory = room.getAccessories().iterator();
					while(iaccessory.hasNext() && false == found){
						Accessory accessory = iaccessory.next();
						
						// if the uid is there we try to find the uid
						if(false == _accessory.getUid().isEmpty()){
							if(accessory.getUid().equals(_accessory.getUid())){
								found = true;
								ret_acc = accessory;
							} // ELSE: Accessory not found. Do noting.
						}
						else{
							// if the serial number is there we try to find the serial number
							if(false == _accessory.getSerialnumber().isEmpty()){
								if(accessory.getSerialnumber().equals(_accessory.getSerialnumber())){
									found = true;
									ret_acc = accessory;
								} // ELSE: Accessory not found. Do noting.
							}
							else{
								// if the label is there we try to find the label
								if(false == _accessory.getLabel().isEmpty()){
									if(accessory.getLabel().equals(_accessory.getLabel())){
										found = true;
										ret_acc = accessory;
									} // ELSE: Accessory not found. Do noting.
								}// ELSE: Nothing else to find.
							}
						}
						
					}// WHILE: Accessories
				}// WHILE: Rooms
			}// WHILE: Zones
		}// WHILE: Homes
		
		return ret_acc;
	}
}
