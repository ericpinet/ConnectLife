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

import com.clapi.data.Email.EmailType;

/**
 * Data
 * 
 * @author ericpinet
 * <br> 2015-11-08
 */
public class Data {
	
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
	public void addPerson(Person person){
		this.persons.add(person);
	}
	
	/**
	 * @param home The home to add
	 */
	public void addHome(Home home){
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
	 * Delete the person.
	 * @param _uid UID of the person.
	 * @return UID of the person to delete.
	 */
	public String deletePerson(String _uid)
	{
		String uid = "";
		Person person = getPerson(_uid);
		if(person != null)
		{
			uid = person.getUid();
			this.persons.remove(person);
		}
		return uid;
	}
	
	/**
	 * Add an email of the person.
	 * @param _uid   UID of the person.
	 * @param _email Email of the person.
	 * @param _type  Type of the email of the person.
	 * @return UID of the person.
	 */
	public String addEmail(String _uid, String _email, int _type)
	{
		Person person = this.getPerson(_uid);
		if(person != null && _email != "")
		{
			Email email = new Email(_uid, _email, EmailType.values()[_type]);
			person.addEmails(email);
		}
		return _uid;
	}
	
	/**
	 * Update the email of the person.
	 * @param _uid   UID of the person.
	 * @param _email Email of the person.
	 * @param _type  Type of the email of the person.
	 * @return UID of the person.
	 */
	public String updateEmail(String _uid, String _email, int _type)
	{
		return "";
	}
	
	/**
	 * Delete an email of the person.
	 * @param _uid   UID of the person.
	 * @return UID of the person.
	 */
	public String deleteEmail(String _uid)
	{
		return "";
	}
}
