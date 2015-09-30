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
import java.util.Collection;

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
	 * Room's accessories.
	 */
	private Collection<Accessory> m_accessories;
	
	/**
	 * Default constructor.
	 * 
	 * @param _uid			Room's uid.
	 * @param _name			Room's name.
	 * @param _image_link  	Room's image link.
	 * @param _accessories	Accessories in the room.
	 */
	public Room(String _uid, String _name, String _image_link, Collection<Accessory> _accessories) {
		super();
		this.m_uid = _uid;
		this.m_name = _name;
		this.m_image_link = _image_link;
		this.m_accessories = _accessories;
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
	 * @return the m_accessories
	 */
	public Collection<Accessory> getAccessories() {
		return m_accessories;
	}

	/**
	 * @param _accessories the m_accessories to set
	 */
	public void setAccessories(Collection<Accessory> _accessories) {
		this.m_accessories = _accessories;
	}
	
}
