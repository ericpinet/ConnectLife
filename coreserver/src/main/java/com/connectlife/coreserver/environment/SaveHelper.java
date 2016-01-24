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
	
	
	public static Data prepareSave(Environment _env){
		Data ret_data = _env.getData().clone();
		
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
						
						
						
					}// WHILE: Accessories
				}// WHILE: Rooms
			}// WHILE: Zones
		}// WHILE: Homes
		
		return ret_data;
	}

}
