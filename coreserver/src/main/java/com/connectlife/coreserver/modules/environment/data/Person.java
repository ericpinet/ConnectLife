/**
 *  Person.java
 *  coreserver
 *
 *  Created by Eric Pinet on 2015-09-09.
 *  Copyright (c) 2015 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.connectlife.coreserver.modules.environment.data;

// external

// internal

/**
 * Representation for a person in the system.
 * 
 * @author ericpinet
 * <br> 2015-09-09
 */
public class Person {
	
	/**
	 * First name of the person.
	 */
	private String m_first_name;
	
	/**
	 * Last name of the person.
	 */
	private String m_last_name;
	
	/**
	 * Emails of the person.
	 */
	private Email[] m_emails;
	
	/**
	 * PhoneNumbers of the person.
	 */
	private PhoneNumber[] m_phone_numbers;
	
	/**
	 * Address of the person.
	 */
	private Address[] m_address;
	
	/**
	 * Image link of the person.
	 */
	private String m_image_link;

	/**
	 * @param _first_name
	 * @param _last_name
	 * @param _emails
	 * @param _phone_numbers
	 * @param _address
	 */
	public Person(	String _first_name, 
					String _last_name, 
					Email[] _emails, 
					PhoneNumber[] _phone_numbers,
					Address[] _address,
					String _image_link) {
		super();
		this.m_first_name = _first_name;
		this.m_last_name = _last_name;
		this.m_emails = _emails;
		this.m_phone_numbers = _phone_numbers;
		this.m_address = _address;
		this.m_image_link = _image_link;
	}

	/**
	 * @return the m_first_name
	 */
	public String getFirstName() {
		return m_first_name;
	}

	/**
	 * @param m_first_name the m_first_name to set
	 */
	public void setFirstName(String m_first_name) {
		this.m_first_name = m_first_name;
	}

	/**
	 * @return the m_last_name
	 */
	public String getLastName() {
		return m_last_name;
	}

	/**
	 * @param m_last_name the m_last_name to set
	 */
	public void setLastName(String m_last_name) {
		this.m_last_name = m_last_name;
	}

	/**
	 * @return the m_emails
	 */
	public Email[] getEmails() {
		return m_emails;
	}

	/**
	 * @param m_emails the m_emails to set
	 */
	public void setEmails(Email[] m_emails) {
		this.m_emails = m_emails;
	}

	/**
	 * @return the m_phone_numbers
	 */
	public PhoneNumber[] getPhoneNumbers() {
		return m_phone_numbers;
	}

	/**
	 * @param m_phone_numbers the m_phone_numbers to set
	 */
	public void setPhoneNumbers(PhoneNumber[] m_phone_numbers) {
		this.m_phone_numbers = m_phone_numbers;
	}

	/**
	 * @return the m_address
	 */
	public Address[] getAddress() {
		return m_address;
	}

	/**
	 * @param m_address the m_address to set
	 */
	public void setAddress(Address[] m_address) {
		this.m_address = m_address;
	}

	/**
	 * @return the m_image_link
	 */
	public String getImageLink() {
		return m_image_link;
	}

	/**
	 * @param m_image_link the m_image_link to set
	 */
	public void setImageLink(String m_image_link) {
		this.m_image_link = m_image_link;
	}
	
}
