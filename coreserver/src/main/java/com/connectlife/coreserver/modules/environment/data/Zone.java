/**
 *  Zone.java
 *  coreserver
 *
 *  Created by ericpinet on 2015-09-10.
 *  Copyright (c) 2015 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.connectlife.coreserver.modules.environment.data;

// external
import java.util.Collection;

// internal

/**
 * 
 * 
 * @author ericpinet
 * <br> 2015-09-10
 */
public class Zone {
	
	/**
	 * Zone's UID.
	 */
	private String m_uid;
	
	/**
	 * Zone's name.
	 */
	private String m_name;
	
	/**
	 * Zone's image link.
	 */
	private String m_image_link;
	
	/**
	 * Zone's rooms. 
	 */
	private Collection<Room> m_rooms;
	
	/**
	 * Default constructor. 
	 * 
	 * @param _uid			Zone's uid.
	 * @param _name			Zone's name.
	 * @param _image_link	Zone's image link.
	 * @param _rooms		Zone's rooms.
	 */
	public Zone(String _uid, String _name, String _image_link, Collection<Room> _rooms ) {
		super();
		this.m_uid = _uid;
		this.m_name = _name;
		this.m_image_link = _image_link;
		this.m_rooms = _rooms;
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
	 * @return the m_name
	 */
	public String getName() {
		return m_name;
	}

	/**
	 * @param _name the m_name to set
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
	 * @param _image_link the m_image_link to set
	 */
	public void setImageLink(String _image_link) {
		this.m_image_link = _image_link;
	}

	/**
	 * @return the m_rooms
	 */
	public Collection<Room> getRooms() {
		return m_rooms;
	}

	/**
	 * @param _rooms the m_rooms to set
	 */
	public void setRooms(Collection<Room> _rooms) {
		this.m_rooms = _rooms;
	}

}
