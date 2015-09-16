/**
 *  PhoneNumber.java
 *  coreserver
 *
 *  Created by ericpinet on 2015-09-09.
 *  Copyright (c) 2015 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.connectlife.coreserver.modules.environment.data;

// external

// internal

/**
 * PhoneNumber represent phone number and is type. 
 * 
 * @author ericpinet
 * <br> 2015-09-09
 */
public class PhoneNumber {

	/**
	 * List of type of PhoneNumber.
	 */
	public enum Type{
		HOME, WORK, CELL, OTHER
	}
	
	/**
	 * Phone number
	 */
	private String m_phone_number;
	
	/**
	 * Type of this phone number.
	 */
	private Type m_type;

	/**
	 * @param _phone_number	Phone number.
	 * @param _type			Type of phone number.
	 */
	public PhoneNumber(String _phone_number, Type _type) {
		super();
		m_phone_number = _phone_number;
		m_type = _type;
	}

	/**
	 * @return the m_phone_number
	 */
	public String getPhoneNumber() {
		return m_phone_number;
	}

	/**
	 * @param _phone_number the m_phone_number to set
	 */
	public void setPhoneNumber(String _phone_number) {
		this.m_phone_number = _phone_number;
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
	
	
	
}
