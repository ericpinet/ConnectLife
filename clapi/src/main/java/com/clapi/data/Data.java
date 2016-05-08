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
public class Data {
	
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
	 * @param persons Persons of the data.
	 * @param homes Homes of the data.
	 */
	public Data(List<Person> persons, List<Home> homes) {
		super();
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
}
