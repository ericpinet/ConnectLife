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
	enum PhoneNumberType{
		HOME, WORK, CELL, OTHERS
	}
	
	/**
	 * Phone number
	 */
	private String m_phone_number;
	
	/**
	 * Type of this phone number.
	 */
	private PhoneNumberType m_type;

	/**
	 * @param m_phone_number
	 * @param m_type
	 */
	public PhoneNumber(String _phone_number, PhoneNumberType _type) {
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
	 * @param m_phone_number the m_phone_number to set
	 */
	public void setPhoneNumber(String m_phone_number) {
		this.m_phone_number = m_phone_number;
	}

	/**
	 * @return the m_type
	 */
	public PhoneNumberType getType() {
		return m_type;
	}

	/**
	 * @param m_type the m_type to set
	 */
	public void setType(PhoneNumberType m_type) {
		this.m_type = m_type;
	}
	
	
	
}
