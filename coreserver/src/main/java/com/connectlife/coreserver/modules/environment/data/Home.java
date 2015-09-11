/**
 *  Home.java
 *  coreserver
 *
 *  Created by ericpinet on 2015-09-10.
 *  Copyright (c) 2015 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.connectlife.coreserver.modules.environment.data;

// external

// internal

/**
 * 
 * 
 * @author ericpinet
 * <br> 2015-09-10
 */
public class Home {

	private String m_uid;
	
	private String m_name;
	
	private String m_image_link;
	
	private Zone[] m_zones;

	/**
	 * 
	 * @param _uid
	 * @param _name
	 * @param _image_link
	 * @param _zones
	 */
	public Home(String _uid, String _name, String _image_link, Zone[] _zones) {
		super();
		this.m_uid = _uid;
		this.m_name = _name;
		this.m_image_link = _image_link;
		this.m_zones = _zones;
	}

	/**
	 * @return the m_uid
	 */
	public String getUid() {
		return m_uid;
	}

	/**
	 * @param m_uid the m_uid to set
	 */
	public void setUid(String _uid) {
		this.m_uid = _uid;
	}

	/**
	 * @return the m_name
	 */
	public String getName() {
		return m_name;
	}

	/**
	 * @param m_name the m_name to set
	 */
	public void setName(String _name) {
		this.m_name = _name;
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
	public void setImageLink(String _image_link) {
		this.m_image_link = _image_link;
	}

	/**
	 * @return the m_zones
	 */
	public Zone[] getZones() {
		return m_zones;
	}

	/**
	 * @param m_zones the m_zones to set
	 */
	public void setZones(Zone[] _zones) {
		this.m_zones = _zones;
	}

	
	
	
}
