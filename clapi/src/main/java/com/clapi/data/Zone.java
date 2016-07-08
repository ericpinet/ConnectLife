/**
 *  Zone.java
 *  clapi
 *
 *  Created by ericpinet on 2015-11-08.
 *  Copyright (c) 2015 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.clapi.data;

import java.util.List;
import java.util.Vector;

/**
 * Zone.
 * 
 * @author ericpinet
 * <br> 2015-11-08
 */
public class Zone implements DataObj {
	
	/**
	 * UID of the zone generated by the server.
	 */
	private String uid;
	
	/**
	 * Label of the zone.
	 */
	private String label;
	
	/**
	 * List of rooms is the zone.
	 */
	private List<Room> rooms;
	
	/**
	 * Image of the zone.
	 */
	private String imageurl;

	/**
	 * Default constructor.
	 * 
	 * @param uid Uid of the zone.
	 * @param label Label of the zone.
	 * @param rooms Rooms of the zone.
	 * @param imageurl ImageURL of the zone.
	 */
	public Zone(String uid, String label, List<Room> rooms, String imageurl) {
		super();
		this.uid = uid;
		this.label = label;
		this.rooms = rooms;
		this.imageurl = imageurl;
	}
	
	/**
	 * Default constructor.
	 * 
	 * @param uid Uid of the zone.
	 * @param label Label of the zone.
	 */
	public Zone(String uid, String label) {
		super();
		this.uid = uid;
		this.label = label;
		this.rooms = new Vector<Room>();
		this.imageurl = "";
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
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * @param label the label to set
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 * @return the rooms
	 */
	public List<Room> getRooms() {
		return rooms;
	}

	/**
	 * @param rooms the rooms to set
	 */
	public void setRooms(List<Room> rooms) {
		this.rooms = rooms;
	}
	
	/**
	 * @param room the room to add
	 */
	public void addRoom(Room room) {
		this.rooms.add(room);
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
	 * Return the children of this object.
	 * 
	 * @return Children of this object.
	 * @see com.clapi.data.DataObj#getChildren()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<DataObj> getChildren() {
		return (List<DataObj>)(Object)getRooms();
	}
}
