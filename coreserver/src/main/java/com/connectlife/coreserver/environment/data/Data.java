/**
 *  Data.java
 *  coreserver
 *
 *  Created by ericpinet on 2015-09-10.
 *  Copyright (c) 2015 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.connectlife.coreserver.environment.data;

// external
import java.util.Collection;

// internal
import com.connectlife.clapi.*;

/**
 * 
 * 
 * @author ericpinet
 * <br> 2015-09-10
 */
public class Data {
	
	/**
	 * Persons in the environment.
	 */
	Collection<Person> m_persons;
	
	/**
	 * Home in the environment.
	 */
	Collection<Home> m_homes;
	
	/**
	 * Default constructor.
	 */
	public Data() {
		super();
	}
	
	/**
	 * @param _persons Persons present in the environment
	 * @param _homes Homes in this environment.
	 */
	public Data(Collection<Person> _persons, Collection<Home> _homes) {
		super();
		this.m_persons = _persons;
		this.m_homes = _homes;
	}

	/**
	 * @return the m_persons
	 */
	public Collection<Person> getPersons() {
		return m_persons;
	}

	/**
	 * @param _persons the m_persons to set
	 */
	public void setPersons(Collection<Person> _persons) {
		this.m_persons = _persons;
	}

	/**
	 * @return the m_homes
	 */
	public Collection<Home> getHomes() {
		return m_homes;
	}

	/**
	 * @param _homes the m_homes to set
	 */
	public void setHomes(Collection<Home> _homes) {
		this.m_homes = _homes;
	}
}
