/**
 *  Trigger.java
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
public class Trigger {
	
	public enum Type {
		ACCESSORY_STATE, TIME, LOCATION
	}
	
	/**
	 * Type of the trigger.
	 */
	private Type m_type;
	
	/**
	 * Accessory State linked to the trigger.
	 */
	private AccessoryState m_accessory_state;

	/**
	 * Constructor for Accessory state trigger.
	 * 
	 * @param _accessory_state State for an accessories.
	 */
	public Trigger(AccessoryState _accessory_state) {
		super();
		this.m_type = Type.ACCESSORY_STATE;
		this.m_accessory_state = _accessory_state;
	}

	/**
	 * @return the m_type
	 */
	public Type getType() {
		return m_type;
	}

	/**
	 * @param _type the m_type to set
	 */
	public void setType(Type _type) {
		this.m_type = _type;
	}

	/**
	 * @return the m_accessory_state
	 */
	public AccessoryState getAccessoryState() {
		return m_accessory_state;
	}

	/**
	 * @param _accessory_state the m_accessory_state to set
	 */
	public void setAccessoryState(AccessoryState _accessory_state) {
		this.m_accessory_state = _accessory_state;
	}
	
	
	
	
}
