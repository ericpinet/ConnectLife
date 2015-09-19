/**
 *  Environment.java
 *  coreserver
 *
 *  Created by ericpinet on 2015-09-10.
 *  Copyright (c) 2015 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.connectlife.coreserver.modules.environment.data;

// external

// internal

/**
 * 
 * 
 * @author ericpinet
 * <br> 2015-09-10
 */
public class Environment {
	
	/**
	 * Persons in the environment.
	 */
	Person[] m_persons;
	
	/**
	 * Home in the environment.
	 */
	Home[] m_homes;
	
	/**
	 * Default constructor.
	 */
	public Environment() {
		super();
	}
	
	/**
	 * @param _persons Persons present in the environment
	 * @param _homes Homes in this environment.
	 */
	public Environment(Person[] _persons, Home[] _homes) {
		super();
		this.m_persons = _persons;
		this.m_homes = _homes;
	}

	/**
	 * @return the m_persons
	 */
	public Person[] getPersons() {
		return m_persons;
	}

	/**
	 * @param _persons the m_persons to set
	 */
	public void setPersons(Person[] _persons) {
		this.m_persons = _persons;
	}

	/**
	 * @return the m_homes
	 */
	public Home[] getHomes() {
		return m_homes;
	}

	/**
	 * @param _homes the m_homes to set
	 */
	public void setHomes(Home[] _homes) {
		this.m_homes = _homes;
	}
}
