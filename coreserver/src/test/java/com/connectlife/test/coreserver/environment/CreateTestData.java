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
import com.connectlife.clapi.Accessory;
import com.connectlife.clapi.Address;
import com.connectlife.clapi.AddressType;
import com.connectlife.clapi.Characteristic;
import com.connectlife.clapi.CharacteristicAccessMode;
import com.connectlife.clapi.CharacteristicEventType;
import com.connectlife.clapi.CharacteristicType;
import com.connectlife.clapi.Data;
import com.connectlife.clapi.Email;
import com.connectlife.clapi.EmailType;
import com.connectlife.clapi.Home;
import com.connectlife.clapi.Person;
import com.connectlife.clapi.Phone;
import com.connectlife.clapi.PhoneType;
import com.connectlife.clapi.Room;
import com.connectlife.clapi.Service;
import com.connectlife.clapi.Zone;


/**
 * 
 * 
 * @author ericpinet
 * <br> 2015-10-18
 */
public class CreateTestData {
	
	public static Data getData(){
		Data data = null;
		
		// Person
		List<Person> persons = new ArrayList<Person>();
		Person eric = new Person("1", "Eric");
		eric.setLastname("Pinet");
		eric.addToEmails(new Email(EmailType.PERSONAL, "pineri01@gmail.com"));
		eric.addToEmails(new Email(EmailType.WORK, "eric.pinet@imagemsoft.com"));
		eric.addToEmails(new Email(EmailType.OTHER, "eric_pinet@hotmail.com"));
		eric.addToPhones(new Phone(PhoneType.CELL, "418 998-2481"));
		eric.addToPhones(new Phone(PhoneType.OTHER, "418 548-1684"));
		Address ericadd = new Address(AddressType.HOME, "2353 rue du cuir");
		ericadd.setCity("Québec");
		ericadd.setRegion("Québec");
		ericadd.setZipcode("G3E0G3");
		ericadd.setCountry("Canada");
		eric.addToAddress(ericadd);
		persons.add(eric);
		
		Person qiaomei = new Person("2", "Qiaomei");
		qiaomei.setLastname("Wang");
		qiaomei.addToEmails(new Email(EmailType.PERSONAL, "qiaomei.wang.wqm@gmail.com"));
		qiaomei.addToEmails(new Email(EmailType.WORK, "qiaomei.wang@frima.com"));
		qiaomei.addToPhones(new Phone(PhoneType.CELL, "438 348-1699"));
		Address qiaomeiadd = new Address(AddressType.HOME, "2353 rue du cuir");
		qiaomeiadd.setCity("Québec");
		qiaomeiadd.setRegion("Québec");
		qiaomeiadd.setZipcode("G3E0G3");
		qiaomeiadd.setCountry("Canada");
		qiaomei.addToAddress(ericadd);
		persons.add(qiaomei);
		
		Characteristic boolean_light = new Characteristic("3", CharacteristicAccessMode.READ_WRITE, CharacteristicType.BOOLEAN, CharacteristicEventType.EVENT, "false");
		Characteristic dimmable_light = new Characteristic("4", CharacteristicAccessMode.READ_WRITE, CharacteristicType.FLOAT, CharacteristicEventType.EVENT, "1.0");
		List<Characteristic> characteristics = new ArrayList<Characteristic>();
		characteristics.add(boolean_light);
		characteristics.add(dimmable_light);
		
		
		Service dimmable_light_service = new Service("5", characteristics);
		List<Service> services = new ArrayList<Service>();
		services.add(dimmable_light_service);
		
		Accessory light_leving = new Accessory(	"6",
												"Light",
												"Philips",
												"100w",
												"PL001-100-10009",
												services);
		
		List<Accessory> accessories_leving = new ArrayList<Accessory>();
		accessories_leving.add(light_leving);
		
		// Create room
		Room leving = new Room("7", "Leving room");
		leving.setAccessories(accessories_leving);
		
		List<Room> rooms_first_floor = new ArrayList<Room>();
		rooms_first_floor.add(leving);
		
		// Create zone
		Zone first_floor = new Zone("8", "First floor");
		first_floor.setRooms(rooms_first_floor);
		
		List<Zone> home1_zones = new ArrayList<Zone>();
		home1_zones.add(first_floor);
		
		// Create home
		Home home1 = new Home("9", "Home");
		home1.setZones(home1_zones);
		
		List<Home> homes = new ArrayList<Home>();
		homes.add(home1);
		
		// Create base data
		data = new Data();
		data.addToHome(home1);
		data.setPersons(persons);
		
		return data;
	}

}
