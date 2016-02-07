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
import com.clapi.data.*;

/**
 * Find processor for the environment.
 * 
 * @author ericpinet
 * <br> 2016-02-07
 */
public class FindProcessor {
	
	/**
	 * Data environment use by this find processor;
	 */
	Data m_data;
	
	/**
	 * Default constructor.
	 * 
	 * @param _environment
	 */
	public FindProcessor(Data _data){
		m_data = _data;
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
	 * Find the accessory with this uid, label or serial number in the environment.
	 * 
	 * @param _serial_number Serial number to be found.
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
