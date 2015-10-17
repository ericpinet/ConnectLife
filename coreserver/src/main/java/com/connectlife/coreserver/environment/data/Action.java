/**
 *  Action.java
 *  coreserver
 *
 *  Created by ericpinet on 2015-09-11.
 *  Copyright (c) 2015 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.connectlife.coreserver.environment.data;

// external

// internal

/**
 * 
 * 
 * @author ericpinet
 * <br> 2015-09-11
 */
public class Action {
	
	/**
	 * Action's name.
	 */
	private String m_name;
	
	/**
	 * Default constructor.
	 * 
	 * @param _name Action's name.
	 */
	public Action(String _name){
		super();
		this.m_name = _name;
	}

	/**
	 * @return the m_name
	 */
	public String getName() {
		return m_name;
	}

	/**
	 * @param _name the m_name to set
	 */
	public void setName(String _name) {
		this.m_name = _name;
	}
	
	
}
