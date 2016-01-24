/**
 *  SaveHelper.java
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
 * Helper the work with environment. Useful function to save element in the environment.
 * 
 * @author ericpinet
 * <br> 2016-01-23
 */
public abstract class SaveHelper {
	
	/**
	 * Default constructor is private to ensure that is never instantiated.
	 */
	private SaveHelper (){
	}
	
	/**
	 * Return the data prepared to save. Remove all field that we don't want o save.
	 * 
	 * @param _env Environment that we want to save.
	 * @return Data prepared to save.
	 */
	public static Data prepareSave(Environment _env){
		Data ret_data = _env.getData().clone();
		
		// Step to prepare the data to save:
		//   1) Put all accessory register at false
		//
		
		// iterate in home
		Iterator<Home> ihome = _env.getData().getHomes().iterator();
		while(ihome.hasNext()){
			Home home = ihome.next();
			
			// iterate in zone
			Iterator<Zone> izone = home.getZones().iterator();
			while(izone.hasNext()){
				Zone zone = izone.next();
				
				// iterate in room
				Iterator<Room> iroom = zone.getRooms().iterator();
				while(iroom.hasNext()){
					Room room = iroom.next();
					
					// iterate in accessory
					Iterator<Accessory> iaccessory = room.getAccessories().iterator();
					while(iaccessory.hasNext()){
						Accessory accessory = iaccessory.next();
						
						accessory.setRegister(false);
						
					}// WHILE: Accessories
				}// WHILE: Rooms
			}// WHILE: Zones
		}// WHILE: Homes
		
		return ret_data;
	}

}
