/**
 *  Accessory.java
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
public class Accessory {
	
	/**
	 * Category of accessories.
	 */
	public enum Category {
		SENSOR, DOOR, WINDOW, LIGHT, HMI
	}
	
	/**
	 * Accessory's uid.
	 */
	private String m_uid;
	
	/**
	 * Accessory's name.
	 */
	private String m_name;
	
	/**
	 * States of this accessory.
	 */
	private State[] m_states;
	
	/**
	 * List of actions possible to execute on this accessory.
	 */
	private Action[] m_possible_actions;
	
	/**
	 * Default constructor.
	 * 
	 * @param _uid				Accessory's uid.
	 * @param _name				Accessory's name.
	 * @param _possible_actions Actions possible to do on this accessory.
	 * @param _states			States of this accessory.
	 */
	public Accessory(String _uid, String _name, State[] _states, Action[] _possible_actions){
		super();
		this.m_uid = _uid;
		this.m_name = _name;
		this.m_states = _states;
		this.m_possible_actions = _possible_actions;
	}

	/**
	 * @return the m_uid
	 */
	public String getUid() {
		return m_uid;
	}

	/**
	 * @param _uid the m_uid to set
	 */
	public void setUid(String _uid) {
		this.m_uid = _uid;
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

	/**
	 * @return the m_possible_actions
	 */
	public Action[] getPossibleActions() {
		return m_possible_actions;
	}

	/**
	 * @param _possible_actions the m_possible_actions to set
	 */
	public void setPossibleActions(Action[] _possible_actions) {
		this.m_possible_actions = _possible_actions;
	}

	/**
	 * @return the m_states
	 */
	public State[] getStates() {
		return m_states;
	}

	/**
	 * @param _states the m_states to set
	 */
	public void setStates(State[] _states) {
		this.m_states = _states;
	}
	
	

}
