/**
 *  AccessoryState.java
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
public class AccessoryState {
	
	/**
	 * AccessoryAction's uid. 
	 */
	private String m_uid;
	
	/**
	 * AccessoryAction's accessory. 
	 */
	private Accessory m_accessory;
	
	/**
	 * AccessoryAction's state. 
	 */
	private State m_state;

	/**
	 * Default constructor. 
	 * 
	 * @param _uid			UID of this accessory action.
	 * @param _accessory	Accessory linked to this state.
	 * @param _state		State linked to this accessory.
	 */
	public AccessoryState(String _uid, Accessory _accessory, State _state) {
		super();
		this.m_uid = _uid;
		this.m_accessory = _accessory;
		this.m_state = _state;
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
	 * @return the m_accessory
	 */
	public Accessory getAccessory() {
		return m_accessory;
	}

	/**
	 * @param _accessory the m_accessory to set
	 */
	public void setAccessory(Accessory _accessory) {
		this.m_accessory = _accessory;
	}

	/**
	 * @return the m_state
	 */
	public State getState() {
		return m_state;
	}

	/**
	 * @param _state the m_state to set
	 */
	public void setState(State _state) {
		this.m_state = _state;
	}
}
