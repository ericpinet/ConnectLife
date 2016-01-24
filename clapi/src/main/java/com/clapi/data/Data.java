/**
 *  Data.java
 *  clapi
 *
 *  Created by ericpinet on 2015-11-08.
 *  Copyright (c) 2015 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.clapi.data;

import java.util.ArrayList;
import java.util.List;

/**
 * Data
 * 
 * @author ericpinet
 * <br> 2015-11-08
 */
public class Data implements Cloneable {
	
	/**
	 * Version of the data. Increment by one for each change.
	 */
	private int version;
	
	/**
	 * List of the persons in data.
	 */
	private List<Person> persons;
	
	/**
	 * List of homes in data.
	 */
	private List<Home> homes;

	/**
	 * Default constructor.
	 * @param version Version of the data.
	 * @param persons Persons of the data.
	 * @param homes Homes of the data.
	 */
	public Data(int version, List<Person> persons, List<Home> homes) {
		super();
		this.version = version;
		this.persons = persons;
		this.homes = homes;
	}
	
	/**
	 * Default constructor.
	 */
	public Data() {
		super();
		this.persons = new ArrayList<Person>();
		this.homes = new ArrayList<Home>();
	}

	/**
	 * @return the version
	 */
	public int getVersion() {
		return version;
	}

	/**
	 * @param version the version to set
	 */
	public void setVersion(int version) {
		this.version = version;
	}

	/**
	 * @return the persons
	 */
	public List<Person> getPersons() {
		return persons;
	}

	/**
	 * @param persons the persons to set
	 */
	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}

	/**
	 * @return the homes
	 */
	public List<Home> getHomes() {
		return homes;
	}

	/**
	 * @param homes the homes to set
	 */
	public void setHomes(List<Home> homes) {
		this.homes = homes;
	}
	
	/**
	 * @param person the persons to set
	 */
	public void addToPersons(Person person){
		this.persons.add(person);
	}
	
	/**
	 * @param home The home to add
	 */
	public void addToHomes(Home home){
		this.homes.add(home);
	}
	
	/**
	 * Get the person by uid.
	 * 
	 * @param uid UID of the person.
	 * @return The person.
	 */
	public Person getPerson(String uid){
		Person person = null;
		for(int i=0; i<this.persons.size(); i++){
			if(this.persons.get(i).getUid().equals(uid))
			{
				person = this.persons.get(i);
				break;
			}
		}
		return person;
	}
	
	/**
	 * Update the person information.
	 * 
	 * @param uid UID of the person.
	 * @param firstname First name of the person.
	 * @param lastname  Last name of the person.
	 * @param imageurl Image url of the person.
	 */
	public void updatePerson(String uid, String firstname, String lastname, String imageurl){
		Person person = getPerson(uid);
		if(person!=null)
		{
			person.setFirstname(firstname);
			person.setLastname(lastname);
			person.setImageurl(imageurl);
		}
	}
	
	/**
	 * Clone the data environment.
	 * 
	 * @return Clone of the data.
	 * @see java.lang.Object#clone()
	 */
	public Data clone(){
		Data data = null;
	    try {
	      	data = (Data) super.clone();
	    } catch(CloneNotSupportedException exception) {
	    	exception.printStackTrace(System.err);
	    }
	    
	    // Clone field of data.
	    // TODO : Clone the data all fields
	    
	    // return data
	    return data;
	}
}
