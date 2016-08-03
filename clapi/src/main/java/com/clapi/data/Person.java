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
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

/**
 * Person.
 * 
 * @author ericpinet
 * <br> 2015-11-08
 */
public class Person implements DataObj {
	
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
	 * Image uid of the person.
	 */
	private String imageuid;

	/**
	 * Default constructor. 
	 * 
	 * @param uid Uid of the person.
	 * @param firstname First name of the person.
	 * @param lastname Last name of the person.
	 * @param emails Emails of the person.
	 * @param phones Phones of the person.
	 * @param addresses Addresses of the person.
	 * @param imageuid ImageUID of the person.
	 */
	public Person(String uid, String firstname, String lastname, List<Email> emails, List<Phone> phones,
			List<Address> addresses, String imageuid) {
		super();
		this.uid = uid;
		this.firstname = firstname;
		this.lastname = lastname;
		this.emails = emails;
		this.phones = phones;
		this.addresses = addresses;
		this.imageuid = imageuid;
	}
	
	/**
	 * Default constructor. 
	 * 
	 * @param uid Uid of the person.
	 * @param firstname First name of the person.
	 */
	public Person(String uid, String firstname) {
		super();
		this.uid = uid;
		this.firstname = firstname;
		this.emails = new ArrayList<Email>();
		this.phones = new ArrayList<Phone>();
		this.addresses = new ArrayList<Address>();
		this.imageuid = "";
	}
	
	/**
	 * Default constructor. 
	 * 
	 * @param uid Uid of the person.
	 * @param firstname First name of the person.
	 * @param lastname Last name of the person.
	 * @param imageuid ImageUID of the person.
	 */
	public Person(String uid, String firstname, String lastname, String imageuid) {
		super();
		this.uid = uid;
		this.firstname = firstname;
		this.lastname = lastname;
		this.emails = new ArrayList<Email>();
		this.phones = new ArrayList<Phone>();
		this.addresses = new ArrayList<Address>();
		this.imageuid = imageuid;
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
	 * @return the imageuid
	 */
	public String getImageuid() {
		return imageuid;
	}

	/**
	 * @param imageuid the imageuid to set
	 */
	public void setImageuid(String imageuid) {
		this.imageuid = imageuid;
	}

	/**
	 * @param email Email of the person.
	 */
	public void addEmails(Email email) {
		this.emails.add(email);
	}
	
	/**
	 * Update the mail.
	 * 
	 * @param email Email object at update.
	 */
	public void updateEmail(Email email)
	{
		boolean found = false;
		Iterator<Email> it = emails.iterator();
		
		while(it.hasNext() && found == false){
			
			Email aemail = it.next();
			if(aemail.getUid().equals(email.getUid())){
				found = true;
				aemail.setEmail(email.getEmail());
				aemail.setType(email.getType());
			}
		}
	}

	/**
	 * @param phone Phone of the person.
	 */
	public void addPhones(Phone phone) {
		this.phones.add(phone);
	}

	/**
	 * @param address Address of the person.
	 */
	public void addAddress(Address address) {
		this.addresses.add(address);
	}
	
	/**
	 * Update the person information.
	 * 
	 * @param _person Person information to use in the update.
	 */
	public void updateInformation(Person _person){
		setUid(_person.getUid());
		setFirstname(_person.getFirstname());
		setLastname(_person.getLastname());
		setImageuid(_person.getImageuid());
		setAddresses(_person.getAddresses());
		setEmails(_person.getEmails());
		setPhones(_person.getPhones());
	}
	
	/**
	 * Return children of this object.
	 * 
	 * @return Children of this object.
	 * @see com.clapi.data.DataObj#getChildren()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<DataObj> getChildren() {
		List<DataObj> ret_obj = new Vector<DataObj>();
		ret_obj.addAll(getPhones());
		ret_obj.addAll(getAddresses());
		ret_obj.addAll(getEmails());
		return (List<DataObj>)(Object)ret_obj;
	}
}
