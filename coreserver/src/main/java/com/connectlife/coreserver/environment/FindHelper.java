/**
 *  FindHelper.java
 *  coreserver
 *
 *  Created by ericpinet on 2016-01-23.
 *  Copyright (c) 2016 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.connectlife.coreserver.environment;

import java.util.Iterator;

import com.clapi.data.*;

/**
 * Helper the work with environment. Useful function to find element in the environment.
 * 
 * @author ericpinet
 * <br> 2016-01-23
 */
public abstract class FindHelper {
	
	/**
	 * Default constructor is private to ensure that is never instantiated.
	 */
	private FindHelper (){
	}
	
	/**
	 * Find the accessory with this serial number in the environment.
	 * 
	 * @param _env Environment where to found.
	 * @param _serial_number Serial number to be found.
	 * @return Accessory if found. Null if not found.
	 */
	public static Accessory findAccessoryBySerialNumber(Environment _env, String _serial_number){
		Accessory ret_acc = null;
		
		boolean notfound = true;
		
		// iterate in home
		Iterator<Home> ihome = _env.getData().getHomes().iterator();
		while(ihome.hasNext() && notfound){
			Home home = ihome.next();
			
			// iterate in zone
			Iterator<Zone> izone = home.getZones().iterator();
			while(izone.hasNext() && notfound){
				Zone zone = izone.next();
				
				// iterate in room
				Iterator<Room> iroom = zone.getRooms().iterator();
				while(iroom.hasNext() && notfound){
					Room room = iroom.next();
					
					// iterate in accessory
					Iterator<Accessory> iaccessory = room.getAccessories().iterator();
					while(iaccessory.hasNext() && notfound){
						Accessory accessory = iaccessory.next();
						
						if(accessory.getSerialnumber().equals(_serial_number)){
							notfound = false;
							ret_acc = accessory;
							
						} // ELSE: Accessory not found. Do noting.
						
					}// WHILE: Accessories
				}// WHILE: Rooms
			}// WHILE: Zones
		}// WHILE: Homes
		
		return ret_acc;
	}

}