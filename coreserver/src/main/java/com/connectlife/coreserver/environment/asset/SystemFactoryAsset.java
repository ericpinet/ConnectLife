/**
 *  SystemFactoryAsset.java
 *  coreserver
 *
 *  Created by ericpinet on 2016-08-03.
 *  Copyright (c) 2016 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.connectlife.coreserver.environment.asset;

import com.clapi.data.Accessory;
import com.clapi.data.Accessory.AccessoryType;

/**
 * System default configuration for asset.
 * 
 * @author ericpinet
 * <br> 2016-08-03
 */
public class SystemFactoryAsset {
	
	/**
	 * Position of the class name in the array AssetItems 
	 */
	public static final int CLASSNAME = 0;
	
	/**
	 * Position of the label in the array AssetItems 
	 */
	public static final int LABEL = 1;
	
	/**
	 * Position of the uid in the array AssetItems 
	 */
	public static final int UID = 2;
	
	/**
	 * Position of the accessory type in the array AssetItems 
	 */
	public static final int ACCESSORY_TYPE = 3;
	
	/**
	 * Default configuration for the application.
	 * 
	 * Class name, label and uid.
	 */
	public static String [][] AssetItems = {
			{"com.clapi.data.Address", 		"address.png", 			"b1f4d4c9-5e69-48db-9a78-c20475a6b9ec", ""},
			{"com.clapi.data.Accessory", 	"automatic-door.png", 	"4490c19a-0a7f-4816-ba67-78f6eff65212", "AUTOMATIC_DOOR"},
			{"com.clapi.data.Accessory", 	"control-board.png", 	"0ad400fe-6a4c-4908-8480-767ccade713a", "CONTROL_BOARD"},
			{"com.clapi.data.Email", 		"email.png", 			"0a81f5fe-5275-460e-8d71-6338dae542ef", ""},
			{"com.clapi.data.Accessory", 	"fan.png", 				"2ffb35dd-4613-41bb-96dd-8f76bdc38ee6", "FAN"},
			{"com.clapi.data.Home", 		"home.png", 			"c7aa8c15-69f6-41aa-91be-b0b49774f0ad", ""},
			{"com.clapi.data.Accessory", 	"light.png", 			"77f73780-7afa-4883-a284-e08055b15f31", "LIGHT"},
			{"com.clapi.data.Accessory", 	"lock.png", 			"e611bbdc-2c7f-4626-80a6-1d561f2646aa", "LOCK"},
			{"com.clapi.data.Person", 		"person.png", 			"6c2cc85b-3901-4b7d-a46c-3a0404595ea1", ""},
			{"com.clapi.data.Phone", 		"phone.png", 			"4d6e4a77-3647-4ddd-8b45-6e706455fb71", ""},
			{"com.clapi.data.Room", 		"room.png", 			"592a9314-a8a7-47cc-99bd-8755cb640071", ""},
			{"com.clapi.data.Accessory", 	"switch.png", 			"e5bf7ed4-cc65-443e-957d-58987d4eb92f", "SWITCH"},
			{"com.clapi.data.Accessory", 	"thermostat.png", 		"aeea8b2b-ebfa-41df-b5ed-7cac1a7d308f", "THERMOSTAT"},
			{"com.clapi.data.Accessory", 	"camera.png", 			"f41d60f1-5d75-4f33-b997-c49b4c74f6d4", "CAM"},
			{"com.clapi.data.Zone", 		"zone.png", 			"5b7d6933-3d83-4529-8f8c-6a7d61d38d7d", ""}
    };
	
	/**
	 * Return the default uid of the asset image for this class type.
	 * Return empty string if no default image asset for the specific class name.
	 * 
	 * @param obj Object to be evaluated.
	 * @return Uid of the asset.
	 */
	public static String getAssetUidByClassType (Object obj) {
		String ret_uid = "";
		String classname = "";
		boolean found = false;
		int pos = 0;
		
		// Get the class name
		Class<?> enclosingClass = obj.getClass().getEnclosingClass();
		if (enclosingClass != null) {
			classname = enclosingClass.getName();
		} else {
			classname = obj.getClass().getName();
		}
		
		
		// found the uid for the class
		// special check for accessory
		if (classname.equals("com.clapi.data.Accessory")) {
			
			Accessory accessory = (Accessory)obj;
			String type = "";
			
			if (accessory.getType() == AccessoryType.AUTOMATIC_DOOR) {
				type = "AUTOMATIC_DOOR";
			}
			else if (accessory.getType() == AccessoryType.CAM) {
				type = "CAM";
			}
			else if (accessory.getType() == AccessoryType.CONTROL_BOARD) {
				type = "CONTROL_BOARD";
			}
			else if (accessory.getType() == AccessoryType.FAN) {
				type = "FAN";
			}
			else if (accessory.getType() == AccessoryType.LIGHT ||
					 accessory.getType() == AccessoryType.LIGHT_COLORED ||
					 accessory.getType() == AccessoryType.LIGHT_COLORED_DIMMABLE ||
					 accessory.getType() == AccessoryType.LIGHT_DIMMABLE ) {
				type = "LIGHT";
			}
			else if (accessory.getType() == AccessoryType.LOCK_MECHANISM) {
				type = "LOCK";
			}
			else if (accessory.getType() == AccessoryType.SWITCH) {
				type = "SWITCH";
			}
			else if (accessory.getType() == AccessoryType.THERMOSTAT) {
				type = "THERMOSTAT";
			}
			
			while (false == type.isEmpty() && false == found && pos < AssetItems.length) {
				
				// Check the accessory type to pick the right image
				if (AssetItems[pos][ACCESSORY_TYPE].equals(type)) {
					// the type was found
					// return the uid
					ret_uid = AssetItems[pos][UID];
					
					// change the found flag
					found = true;
				}
				pos++;
			}
		}
		else {
			while (false == found && pos < AssetItems.length) {
				
				if (AssetItems[pos][CLASSNAME].equals(classname)) {
					// the class name was found
					// The class name is not accessory
					// return the uid
					ret_uid = AssetItems[pos][UID];
					
					// change the found flag
					found = true;
				}
				pos++;
			}
		}
		
		return ret_uid;
	}

}
