/**
 *  Light.java
 *  simulator
 *
 *  Created by ericpinet on 2016-01-09.
 *  Copyright (c) 2016 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.connectlife.simulator.device;

import java.util.ArrayList;
import java.util.List;

import com.clapi.data.Characteristic;
import com.clapi.data.Service;
import com.clapi.data.Characteristic.CharacteristicAccessMode;
import com.clapi.data.Characteristic.CharacteristicEventType;
import com.clapi.data.Characteristic.CharacteristicType;
import com.connectlife.coreserver.environment.UIDGenerator;

/**
 * Light for the simulation.
 * 
 * @author ericpinet
 * <br> 2016-01-09
 */
public class Light extends Device {

	
	/**
	 * Default constructor for the light.
	 * 
	 * @param uid
	 * @param label
	 * @param manufacturer
	 * @param model
	 * @param serialnumber
	 * @param imageurl
	 */
	public Light(String uid, String label, String manufacturer, String model, String serialnumber, String imageurl) {
		super(uid, label, manufacturer, model, serialnumber, null, imageurl, AccessoryType.LIGHT);
		
		// Create the characteristic and the services definition for the Dimmable Light
		Characteristic boolean_light = new Characteristic(UIDGenerator.getUID(), "Light", CharacteristicAccessMode.READ_WRITE, CharacteristicType.BOOLEAN, CharacteristicEventType.EVENT, "false");
		List<Characteristic> characteristics = new ArrayList<Characteristic>();
		characteristics.add(boolean_light);
		
		Service dimmable_light_service = new Service(UIDGenerator.getUID(), "Light",characteristics);
		List<Service> services = new ArrayList<Service>();
		services.add(dimmable_light_service);
		
		setServices(services);
	}

}
