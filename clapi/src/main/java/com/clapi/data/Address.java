/**
 *  Address.java
 *  clapi
 *
 *  Created by ericpinet on 2015-11-08.
 *  Copyright (c) 2015 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.clapi.data;

/**
 * Address.
 * 
 * @author ericpinet
 * <br> 2015-11-08
 */
public class Address {
	
	/**
	 * Enum AddressType
	 * 
	 * @author ericpinet
	 * <br> 2015-11-08
	 */
	public enum AddressType {
		HOME,
		WORK,
		OTHER	
	}
	
	/**
	 * UID of the address generated by the server.
	 */
	private String uid;
	
	/** 
	 * Type of the address.
	 */
	private AddressType type;
	
	/**
	 * Street.
	 */
	private String street;
	
	/**
	 * City.
	 */
	private String city;
	
	/**
	 * Region.
	 */
	private String region;
	
	/**
	 * ZipCode.
	 */
	private String zipcode;
	
	/**
	 * Country.
	 */
	private String country;

	/**
	 * Default Constructor.
	 * 
	 * @param uid
	 * @param type
	 * @param street
	 * @param city
	 * @param region
	 * @param zipcode
	 * @param country
	 */
	public Address(String uid, AddressType type, String street, String city, String region, String zipcode, String country) {
		super();
		this.uid = uid;
		this.type = type;
		this.street = street;
		this.city = city;
		this.region = region;
		this.zipcode = zipcode;
		this.country = country;
	}
	
	/**
	 * Default Constructor.
	 * 
	 * @param uid
	 * @param street
	 */
	public Address(String uid, AddressType type, String street) {
		super();
		this.uid = uid;
		this.type = type;
		this.street = street;
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
	 * @return the type
	 */
	public AddressType getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(AddressType type) {
		this.type = type;
	}

	/**
	 * @return the street
	 */
	public String getStreet() {
		return street;
	}

	/**
	 * @param street the street to set
	 */
	public void setStreet(String street) {
		this.street = street;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the region
	 */
	public String getRegion() {
		return region;
	}

	/**
	 * @param region the region to set
	 */
	public void setRegion(String region) {
		this.region = region;
	}

	/**
	 * @return the zipcode
	 */
	public String getZipcode() {
		return zipcode;
	}

	/**
	 * @param zipcode the zipcode to set
	 */
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}
}
