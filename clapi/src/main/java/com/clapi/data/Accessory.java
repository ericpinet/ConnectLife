/**
 *  Accessory.java
 *  clapi
 *
 *  Created by ericpinet on 2015-11-08.
 *  Copyright (c) 2015 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.clapi.data;

import java.util.List;
import java.util.Vector;

import com.rits.cloning.Cloner;

/**
 * Accessory.
 * 
 * @author ericpinet
 * <br> 2015-11-08
 */
public class Accessory implements DataObj {

	/**
	 * Enum AccessoryType
	 * 
	 * @author ericpinet
	 * <br> 2015-11-08
	 */
	public enum AccessoryType {
		LIGHT,
		LIGHT_DIMMABLE,
		LIGHT_COLORED,
		LIGHT_COLORED_DIMMABLE,
		FAN,
		AUTOMATIC_DOOR,
		LOCK_MECHANISM,
		THERMOSTAT,
		SWITCH,
		CONTROL_BOARD,
		CAM,
	}
	
	/**
	 * Enum AccessoryProtocolType
	 * 
	 * @author ericpinet
	 * <br> 2016-01-05
	 */
	public enum AccessoryProtocolType {
		JSON_SIMULATION,
		HAP,
		ZIGBEE,
		ZWAVE
	}
	
	/**
	 * UID of the accessory generated by the server.
	 */
	private String uid;
	
	/**
	 * Label for the accessory.
	 */
	private String label;
	
	/**
	 * Manufacturer for the accessory.
	 */
	private String manufacturer;
	
	/**
	 * Model of the accessory.
	 */
	private String model;
	
	/**
	 * Serial number of the accessory.
	 */
	private String serialnumber;
	
	/**
	 * List of services of the accessory.
	 */
	private List<Service> services;
	
	/**
	 * Image url of the accessory.
	 */
	private String imageurl;
	
	/**
	 * Type of this accessory.
	 */
	private AccessoryType type;
	
	/**
	 * Protocol type for this accessory.
	 */
	private AccessoryProtocolType protocoltype;
	
	/**
	 * Register indicate that the accessory service is correctly connected with the server.
	 */
	private boolean isregister;

	/**
	 * Default constructor.
	 * 
	 * @param uid 			Uid of the accessory. See UIDGenerator.getUID().
	 * @param label			Display name at the user.
	 * @param manufacturer	Manufacturer of the accessory.
	 * @param model			Model of the accessory.
	 * @param serialnumber	Serial number of the accessory.
	 * @param services		List of service of the accessory. See com.clapi.data.Service.
	 * @param imageurl		Image url for this accessory display at the user.
	 * @param type			Type of this accessory.
	 * @param protocoltype  Protocol type of this accessory.
	 * 
	 */
	public Accessory(String uid, String label, String manufacturer, String model, String serialnumber, List<Service> services,
			String imageurl, AccessoryType type, AccessoryProtocolType protocoltype) {
		super();
		this.uid = uid;
		this.label = label;
		this.manufacturer = manufacturer;
		this.model = model;
		this.serialnumber = serialnumber;
		this.services = services;
		this.imageurl = imageurl;
		this.type = type;
		this.protocoltype = protocoltype;
		this.isregister = false;
	}
	
	/**
	 * Default constructor.
	 * 
	 * @param uid 	Uid of the accessory. See UIDGenerator.getUID().
	 * @param label Display name at the user.
	 */
	public Accessory(String uid, String label) {
		super();
		this.uid = uid;
		this.label = label;
		this.manufacturer = "";
		this.model = "";
		this.serialnumber = "";
		this.services = new Vector<Service>();
		this.imageurl = "";
		this.type = null;
		this.protocoltype = null;
		this.isregister = false;
	}

	
	/**
	 * Return the uid of the accessory. See UIDGenerator.getUID().
	 * @return the uid
	 */
	public String getUid() {
		return uid;
	}


	/**
	 * Set the uid of the accessory. See UIDGenerator.getUID().
	 * @param uid the uid to set
	 */
	public void setUid(String uid) {
		this.uid = uid;
	}


	/**
	 * Get the label name of the accessory.
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * Set the label name of the accessory.
	 * @param label the label to set
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 * Get the manufacturer of the accessory.
	 * @return the manufacturer
	 */
	public String getManufacturer() {
		return manufacturer;
	}

	/**
	 * Set the manufacturer of the accessory.
	 * @param manufacturer the manufacturer to set
	 */
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	/**
	 * Get the accessory model.
	 * @return the model
	 */
	public String getModel() {
		return model;
	}

	/**
	 * Set the accessory model.
	 * @param model the model to set
	 */
	public void setModel(String model) {
		this.model = model;
	}

	/**
	 * Get the accessory serial number.
	 * @return the serialnumber
	 */
	public String getSerialnumber() {
		return serialnumber;
	}

	/**
	 * Set the accessory serial number.
	 * @param serialnumber the serialnumber to set
	 */
	public void setSerialnumber(String serialnumber) {
		this.serialnumber = serialnumber;
	}

	/**
	 * Return the list of service of the accessory. See com.clapi.data.Service.
	 * @return the services
	 * @see com.clapi.data.Service
	 */
	public List<Service> getServices() {
		return services;
	}

	/**
	 * Set the list of service of the accessory. See com.clapi.data.Service.
	 * @param services the services to set
	 * @see com.clapi.data.Service
	 */
	public void setServices(List<Service> services) {
		this.services = services;
	}
	
	/**
	 * @param service the service to add.
	 */
	public void addService(Service service) {
		this.services.add(service);
	}

	/**
	 * Get the url of the accessory image.
	 * @return the imageurl
	 */
	public String getImageurl() {
		return imageurl;
	}

	/**
	 * Set the url of the accessory image.
	 * @param imageurl the imageurl to set
	 */
	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}

	/**
	 * @return the type
	 */
	public AccessoryType getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(AccessoryType type) {
		this.type = type;
	}

	/**
	 * @return the protocoltype
	 */
	public AccessoryProtocolType getProtocoltype() {
		return protocoltype;
	}

	/**
	 * @param protocoltype the protocoltype to set
	 */
	public void setProtocoltype(AccessoryProtocolType protocoltype) {
		this.protocoltype = protocoltype;
	}

	/**
	 * @return the isregister
	 */
	public boolean isRegister() {
		return isregister;
	}

	/**
	 * @param isregister the isregister to set
	 */
	public void setRegister(boolean isRegister) {
		this.isregister = isRegister;
	}
	
	/**
	 * Update the accessory.
	 * @param accessory
	 */
	public void update(Accessory accessory) {
		Cloner cloner = new Cloner();
		Accessory temp = cloner.deepClone(accessory);
		this.imageurl = temp.imageurl;
		this.isregister = temp.isregister;
		this.label = temp.label;
		this.manufacturer = temp.manufacturer;
		this.model = temp.model;
		this.protocoltype = temp.protocoltype;
		this.serialnumber = temp.serialnumber;
		this.services = temp.services;
		this.type = temp.type;
	}

	/**
	 * Return children of this class.
	 * 
	 * @return Children of this class.
	 * @see com.clapi.data.DataObj#getChildren()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<DataObj> getChildren() {
		return (List<DataObj>)(Object)getServices();
	}
}
