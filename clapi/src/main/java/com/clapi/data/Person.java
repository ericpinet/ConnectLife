/**
 *  Person.java
 *  clapi
 *
 *  Created by ericpinet on 2015-11-08.
 *  Copyright (c) 2015 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.clapi.data;

import java.util.ArrayList;
import java.util.List;

/**
 * Person.
 * 
 * @author ericpinet
 * <br> 2015-11-08
 */
public class Person {
	
	/**
	 * UID of the person. This UID most be generator by the server.
	 */
	private String uid;
	
	/**
	 * First name of the person.
	 */
	private String firstname;
	
	/**
	 * Last name of the person.
	 */
	private String lastname;
	
	/**
	 * Email list of the person.
	 */
	private List<Email> emails;
	
	/**
	 * Phone list of the person.
	 */
	private List<Phone> phones;
	
	/**
	 * Address list of the person.
	 */
	private List <Address> addresses;
	
	/**
	 * Image url of the person.
	 */
	private String imageurl;

	/**
	 * Default constructor. 
	 * 
	 * @param uid
	 * @param firstname
	 * @param lastname
	 * @param emails
	 * @param phones
	 * @param addresses
	 * @param imageurl
	 */
	public Person(String uid, String firstname, String lastname, List<Email> emails, List<Phone> phones,
			List<Address> addresses, String imageurl) {
		super();
		this.uid = uid;
		this.firstname = firstname;
		this.lastname = lastname;
		this.emails = emails;
		this.phones = phones;
		this.addresses = addresses;
		this.imageurl = imageurl;
	}
	
	/**
	 * Default constructor. 
	 * 
	 * @param uid
	 * @param firstname
	 */
	public Person(String uid, String firstname) {
		super();
		this.uid = uid;
		this.firstname = firstname;
		this.emails = new ArrayList<Email>();
		this.phones = new ArrayList<Phone>();
		this.addresses = new ArrayList<Address>();
	}
	
	/**
	 * Default constructor. 
	 * 
	 * @param uid
	 * @param firstname
	 * @param lastname
	 * @param emails
	 * @param phones
	 * @param addresses
	 * @param imageurl
	 */
	public Person(String uid, String firstname, String lastname, String imageurl) {
		super();
		this.uid = uid;
		this.firstname = firstname;
		this.lastname = lastname;
		this.emails = new ArrayList<Email>();
		this.phones = new ArrayList<Phone>();
		this.addresses = new ArrayList<Address>();
		this.imageurl = imageurl;
	}
	
	

	/**
	 * @return the uid
	 */
	public String getUid() {
		return uid;
	}

	/**
	 * @param uid the uid to set
	 */
	public void setUid(String uid) {
		this.uid = uid;
	}

	/**
	 * @return the firstname
	 */
	public String getFirstname() {
		return firstname;
	}

	/**
	 * @param firstname the firstname to set
	 */
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	/**
	 * @return the lastname
	 */
	public String getLastname() {
		return lastname;
	}

	/**
	 * @param lastname the lastname to set
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	/**
	 * @return the emails
	 */
	public List<Email> getEmails() {
		return emails;
	}

	/**
	 * @param emails the emails to set
	 */
	public void setEmails(List<Email> emails) {
		this.emails = emails;
	}

	/**
	 * @return the phones
	 */
	public List<Phone> getPhones() {
		return phones;
	}

	/**
	 * @param phones the phones to set
	 */
	public void setPhones(List<Phone> phones) {
		this.phones = phones;
	}

	/**
	 * @return the addresses
	 */
	public List<Address> getAddresses() {
		return addresses;
	}

	/**
	 * @param addresses the addresses to set
	 */
	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	/**
	 * @return the imageurl
	 */
	public String getImageurl() {
		return imageurl;
	}

	/**
	 * @param imageurl the imageurl to set
	 */
	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}

	/**
	 * @param email
	 */
	public void addToEmails(Email email) {
		this.emails.add(email);
		
	}

	/**
	 * @param phone
	 */
	public void addToPhones(Phone phone) {
		this.phones.add(phone);
	}

	/**
	 * @param address
	 */
	public void addToAddress(Address address) {
		this.addresses.add(address);
	}
}