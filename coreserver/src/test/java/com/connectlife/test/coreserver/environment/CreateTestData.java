/**
 *  CreateTestData.java
 *  coreserver
 *
 *  Created by ericpinet on 2015-10-18.
 *  Copyright (c) 2015 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.connectlife.test.coreserver.environment;

// external
import java.util.ArrayList;
import java.util.List;

// internal
import com.clapi.data.*;
import com.clapi.data.Accessory.AccessoryProtocolType;
import com.clapi.data.Accessory.AccessoryType;
import com.clapi.data.Address.AddressType;
import com.clapi.data.Characteristic.CharacteristicAccessMode;
import com.clapi.data.Characteristic.CharacteristicEventType;
import com.clapi.data.Characteristic.CharacteristicType;
import com.clapi.data.Email.EmailType;
import com.clapi.data.Phone.PhoneType;
import com.connectlife.coreserver.environment.UIDGenerator;


/**
 * 
 * 
 * @author ericpinet
 * <br> 2015-10-18
 */
public class CreateTestData {
	
	public static Data getData(){
		Data ret_env = null;
		
		// Person
		List<Person> persons = new ArrayList<Person>();
		
		Person eric = new Person(UIDGenerator.getUID(), "Eric");
		eric.setLastname("Pinet");
		eric.addEmails(new Email(UIDGenerator.getUID(), "pineri01@gmail.com", EmailType.PERSONAL));
		eric.addEmails(new Email(UIDGenerator.getUID(), "eric.pinet@imagemsoft.com", EmailType.WORK));
		eric.addEmails(new Email(UIDGenerator.getUID(), "eric_pinet@hotmail.com", EmailType.OTHER));
		eric.addPhones(new Phone(UIDGenerator.getUID(), "418 998-2481", PhoneType.CELL));
		eric.addPhones(new Phone(UIDGenerator.getUID(), "418 548-1684", PhoneType.OTHER));
		Address ericadd = new Address(UIDGenerator.getUID(), AddressType.HOME, "2353 rue du cuir");
		ericadd.setCity("Québec");
		ericadd.setRegion("Québec");
		ericadd.setZipcode("G3E0G3");
		ericadd.setCountry("Canada");
		eric.addAddress(ericadd);
		persons.add(eric);
		
		Person qiaomei = new Person(UIDGenerator.getUID(), "Qiaomei");
		qiaomei.setLastname("Wang");
		qiaomei.addEmails(new Email(UIDGenerator.getUID(), "qiaomei.wang.wqm@gmail.com", EmailType.PERSONAL));
		qiaomei.addEmails(new Email(UIDGenerator.getUID(), "qiaomei.wang@frima.com", EmailType.WORK));
		qiaomei.addPhones(new Phone(UIDGenerator.getUID(), "438 348-1699", PhoneType.CELL));
		
		Address qiaomeiadd = new Address(UIDGenerator.getUID(), AddressType.HOME, "2353 rue du cuir");
		qiaomeiadd.setCity("Québec");
		qiaomeiadd.setRegion("Québec");
		qiaomeiadd.setZipcode("G3E0G3");
		qiaomeiadd.setCountry("Canada");
		qiaomei.addAddress(qiaomeiadd);
		persons.add(qiaomei);
		
		Characteristic boolean_light = new Characteristic(UIDGenerator.getUID(), "Light", CharacteristicAccessMode.READ_WRITE, CharacteristicType.BOOLEAN, CharacteristicEventType.EVENT, "false");
		Characteristic dimmable_light = new Characteristic(UIDGenerator.getUID(), "Dimmable", CharacteristicAccessMode.READ_WRITE, CharacteristicType.FLOAT, CharacteristicEventType.EVENT, "1.0");
		List<Characteristic> characteristics = new ArrayList<Characteristic>();
		characteristics.add(boolean_light);
		characteristics.add(dimmable_light);
		
		
		Service dimmable_light_service = new Service(UIDGenerator.getUID(), "Light", characteristics);
		List<Service> services = new ArrayList<Service>();
		services.add(dimmable_light_service);
		
		Accessory light_leving = new Accessory(	UIDGenerator.getUID(),
												"Light",
												"Philips",
												"100w",
												"PL001-100-10009",
												services,
												"",
												AccessoryType.LIGHT_COLORED_DIMMABLE,
												AccessoryProtocolType.JSON_SIMULATION);
		
		List<Accessory> accessories_leving = new ArrayList<Accessory>();
		accessories_leving.add(light_leving);
		
		// Create room
		Room leving = new Room("051ad593-c9f1-4cd4-9645-f3f80d7e7c25", "Leving room");
		leving.setAccessories(accessories_leving);
		
		List<Room> rooms_first_floor = new ArrayList<Room>();
		rooms_first_floor.add(leving);
		
		// Create zone
		Zone first_floor = new Zone(UIDGenerator.getUID(), "First floor");
		first_floor.setRooms(rooms_first_floor);
		
		List<Zone> home1_zones = new ArrayList<Zone>();
		home1_zones.add(first_floor);
		
		// Create home
		Home home1 = new Home(UIDGenerator.getUID(), "Home");
		home1.setZones(home1_zones);
		
		List<Home> homes = new ArrayList<Home>();
		homes.add(home1);
		
		// Create base data
		ret_env = new Data();
		ret_env.setHomes(homes);
		ret_env.setPersons(persons);
		
		return ret_env;
	}
	
	public static Accessory getLightTest(){
		Characteristic boolean_light = new Characteristic(UIDGenerator.getUID(), "Light", CharacteristicAccessMode.READ_WRITE, CharacteristicType.BOOLEAN, CharacteristicEventType.EVENT, "false");
		Characteristic dimmable_light = new Characteristic(UIDGenerator.getUID(), "Dimmable", CharacteristicAccessMode.READ_WRITE, CharacteristicType.FLOAT, CharacteristicEventType.EVENT, "1.0");
		List<Characteristic> characteristics = new ArrayList<Characteristic>();
		characteristics.add(boolean_light);
		characteristics.add(dimmable_light);
		
		
		Service dimmable_light_service = new Service(UIDGenerator.getUID(), "Light", characteristics);
		List<Service> services = new ArrayList<Service>();
		services.add(dimmable_light_service);
		
		Accessory light_leving = new Accessory(	"",
												"Light",
												"Philips",
												"100w",
												"PL001-100-10009",
												services,
												"",
												AccessoryType.LIGHT_COLORED_DIMMABLE,
												AccessoryProtocolType.JSON_SIMULATION);
		
		return light_leving;
	}

}
