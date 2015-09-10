/**
 *  Address.java
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
 * Representation of one address and is type.
 * 
 * @author ericpinet
 * <br> 2015-09-09
 */
public class Address {
	
	/**
	 * List of type of address.
	 */
	enum AddressType{
		HOME, WORK, OTHERS
	}
	
	/**
	 * Street of the address.
	 */
	private String m_street;
	
	/**
	 * City of the address.
	 */
	private String m_city;
	
	/**
	 * Region of the address.
	 */
	private String m_region;
	
	/**
	 * ZipCode of the address
	 */
	private String m_zip_code;
	
	/**
	 * Country of the address
	 */
	private String m_country;
	
	/**
	 * 
	 * @param _street
	 * @param _city
	 * @param _region
	 * @param _zip_code
	 * @param _country
	 */
	public Address(String _street, String _city, String _region, String _zip_code, String _country) {
		super();
		this.m_street = _street;
		this.m_city = _city;
		this.m_region = _region;
		this.m_zip_code = _zip_code;
		this.m_country = _country;
	}

	/**
	 * @return the m_street
	 */
	public String getStreet() {
		return m_street;
	}

	/**
	 * @param m_street the m_street to set
	 */
	public void setStreet(String m_street) {
		this.m_street = m_street;
	}

	/**
	 * @return the m_city
	 */
	public String getCity() {
		return m_city;
	}

	/**
	 * @param m_city the m_city to set
	 */
	public void setCity(String m_city) {
		this.m_city = m_city;
	}

	/**
	 * @return the m_region
	 */
	public String getRegion() {
		return m_region;
	}

	/**
	 * @param m_region the m_region to set
	 */
	public void setRegion(String m_region) {
		this.m_region = m_region;
	}

	/**
	 * @return the m_zip_code
	 */
	public String getZipCode() {
		return m_zip_code;
	}

	/**
	 * @param m_zip_code the m_zip_code to set
	 */
	public void setZipCode(String m_zip_code) {
		this.m_zip_code = m_zip_code;
	}

	/**
	 * @return the m_country
	 */
	public String getCountry() {
		return m_country;
	}

	/**
	 * @param m_country the m_country to set
	 */
	public void setCountry(String m_country) {
		this.m_country = m_country;
	}

	
	
	

}
