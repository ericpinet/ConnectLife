/**
 *  AccessoryAction.java
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
public class AccessoryAction {
	
	/**
	 * AccessoryAction's uid. 
	 */
	private String m_uid;
	
	/**
	 * AccessoryAction's accessory. 
	 */
	private Accessory m_accessory;
	
	/**
	 * AccessoryAction's action. 
	 */
	private Action m_action;

	/**
	 * Default constructor. 
	 * 
	 * @param _uid			UID of this accessory action.
	 * @param _accessory	Accessory linked to this action.
	 * @param _action		Action linked to this accessory.
	 */
	public AccessoryAction(String _uid, Accessory _accessory, Action _action) {
		super();
		this.m_uid = _uid;
		this.m_accessory = _accessory;
		this.m_action = _action;
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
	 * @return the m_action
	 */
	public Action getAction() {
		return m_action;
	}

	/**
	 * @param _action the m_action to set
	 */
	public void setAction(Action _action) {
		this.m_action = _action;
	}
}
