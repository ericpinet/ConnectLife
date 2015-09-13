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
	 * Unique identifier for this Person.
	 */
	private String m_uid;
	
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
	 * @param _uid				Uid for the person.
	 * @param _first_name		First name.
	 * @param _last_name		Last name.
	 * @param _image_link		Image link for this person.
	 * @param _emails			Email.
	 * @param _phone_numbers 	Phones Numbers.
	 * @param _address			Address.
	 */
	public Person(	String _uid,
					String _first_name, 
					String _last_name,
					String _image_link,
					Email[] _emails, 
					PhoneNumber[] _phone_numbers,
					Address[] _address) {
		super();
		this.m_uid = _uid;
		this.m_first_name = _first_name;
		this.m_last_name = _last_name;
		this.m_emails = _emails;
		this.m_phone_numbers = _phone_numbers;
		this.m_address = _address;
		this.m_image_link = _image_link;
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
	 * @return the m_first_name
	 */
	public String getFirstName() {
		return m_first_name;
	}

	/**
	 * @param _first_name the m_first_name to set
	 */
	public void setFirstName(String _first_name) {
		this.m_first_name = _first_name;
	}

	/**
	 * @return the m_last_name
	 */
	public String getLastName() {
		return m_last_name;
	}

	/**
	 * @param _last_name the m_last_name to set
	 */
	public void setLastName(String _last_name) {
		this.m_last_name = _last_name;
	}

	/**
	 * @return the m_emails
	 */
	public Email[] getEmails() {
		return m_emails;
	}

	/**
	 * @param _emails the m_emails to set
	 */
	public void setEmails(Email[] _emails) {
		this.m_emails = _emails;
	}

	/**
	 * @return the m_phone_numbers
	 */
	public PhoneNumber[] getPhoneNumbers() {
		return m_phone_numbers;
	}

	/**
	 * @param _phone_numbers the m_phone_numbers to set
	 */
	public void setPhoneNumbers(PhoneNumber[] _phone_numbers) {
		this.m_phone_numbers = _phone_numbers;
	}

	/**
	 * @return the m_address
	 */
	public Address[] getAddress() {
		return m_address;
	}

	/**
	 * @param _address the m_address to set
	 */
	public void setAddress(Address[] _address) {
		this.m_address = _address;
	}

	/**
	 * @return the m_image_link
	 */
	public String getImageLink() {
		return m_image_link;
	}

	/**
	 * @param _image_link the m_image_link to set
	 */
	public void setImageLink(String _image_link) {
		this.m_image_link = _image_link;
	}
	
}
