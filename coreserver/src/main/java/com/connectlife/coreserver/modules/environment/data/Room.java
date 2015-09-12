/**
 *  Room.java
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
 * Room representation.
 * 
 * @author ericpinet
 * <br> 2015-09-10
 */
public class Room {
	
	/**
	 * Room's UID
	 */
	private String m_uid;
	
	/**
	 * Room's name.
	 */
	private String m_name;
	
	/**
	 * Room's image link.
	 */
	private String m_image_link;
	
	/**
	 * Default constructor.
	 * 
	 * @param m_uid			Room's uid.
	 * @param m_name		Room's name.
	 * @param m_image_link  Room's image link.
	 */
	public Room(String _uid, String _name, String _image_link) {
		super();
		this.m_uid = _uid;
		this.m_name = _name;
		this.m_image_link = _image_link;
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

}
