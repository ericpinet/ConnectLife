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
}
