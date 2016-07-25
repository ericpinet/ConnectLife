/**
 *  Consts.java
 *  coreserver
 *
 *  Created by Eric Pinet <pineri01@gmail.com> on 2015-09-07.
 *  Copyright (c) 2015 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.connectlife.coreserver;

import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.RelationshipType;

/**
 * This class contain all global constances for the application.
 * 
 * @author Eric Pinet (pineri01@gmail.com)
 * <br> 2015-09-07
 */
public final class Consts {
	
	/**
	 * Application name
	 */
	public static final String APP_NAME = "ConnectLife - CoreServer";
	
	/**
	 * Application version
	 */
	public static final String APP_VERSION = "1.0.0.0";
	
	/**
	 * Define all the label, relation and node property for data.
	 */
	
	public static final String UID = "uid";
	
	/**
	 * PERSON DATA TYPE
	 */
	public static final Label  LABEL_PERSON = Label.label("person");
	public static final String PERSON_FIRSTNAME = "firstname";
	public static final String PERSON_LASTNAME = "lastname";
	public static final String PERSON_IMAGEURL = "imageurl";
	
	/**
	 * ADDRESS DATA TYPE
	 */
	public static final Label  LABEL_ADDRESS = Label.label("address");
	public static final String ADDRESS_TYPE = "type";
	public static final String ADDRESS_STREET = "street";
	public static final String ADDRESS_CITY = "city";
	public static final String ADDRESS_REGION = "region";
	public static final String ADDRESS_ZIPCODE = "zipcode";
	public static final String ADDRESS_COUNTRY = "country";
	
	public static final String ADDRESS_TYPE_HOME = "HOME";
	public static final String ADDRESS_TYPE_OTHER = "OTHER";
	public static final String ADDRESS_TYPE_WORK = "WORK";
	
	/**
	 * EMAIL DATA TYPE
	 */
	public static final Label  LABEL_EMAIL = Label.label("email");
	public static final String EMAIL_TYPE = "type";
	public static final String EMAIL_EMAIL = "email";
	
	public static final String EMAIL_TYPE_PERSONAL = "PERSONAL";
	public static final String EMAIL_TYPE_OTHER = "OTHER";
	public static final String EMAIL_TYPE_WORK = "WORK";
	
	/**
	 * PHONE DATA TYPE
	 */
	public static final Label  LABEL_PHONE = Label.label("phone");
	public static final String PHONE_TYPE = "type";
	public static final String PHONE_NUMBER = "phone";
	
	public static final String PHONE_TYPE_HOME = "HOME";
	public static final String PHONE_TYPE_OTHER = "OTHER";
	public static final String PHONE_TYPE_WORK = "WORK";
	public static final String PHONE_TYPE_CELL = "CELL";
	
	/**
	 * CHARACTERISTIC DATA TYPE
	 */
	public static final Label  LABEL_CHARACTERISTIC = Label.label("characteristic");
	public static final String CH_TYPE = "type";
	public static final String CH_LABEL = "label";
	public static final String CH_MODE = "mode";
	public static final String CH_EVENT_TYPE = "event";
	public static final String CH_DATA = "data";
	
	public static final String CH_TYPE_BOOLEAN = "BOOLEAN";
	public static final String CH_TYPE_ENUM = "ENUM";
	public static final String CH_TYPE_FLOAT = "FLOAT";
	public static final String CH_TYPE_INTEGER = "INTEGER";
	public static final String CH_TYPE_STRING = "STRING";
	public static final String CH_TYPE_WRITE_ONLY_BOOLEAN = "WRITE_ONLY_STRING";
	
	public static final String CH_ACCESS_MODE_READ_ONLY = "READ_ONLY";
	public static final String CH_ACCESS_MODE_READ_WRITE = "READ_WRITE";
	public static final String CH_ACCESS_MODE_WRITE_ONLY = "WRITE_ONLY";
	
	public static final String CH_EVENT_TYPE_EVENT = "EVENT";
	public static final String CH_EVENT_TYPE_NO_EVENT = "NO_EVENT";
	
	/**
	 * SERVICE DATA TYPE
	 */
	public static final Label  LABEL_SERVICE = Label.label("service");
	public static final String SERVICE_NAME = "name";
	
	/**
	 * ACCESSORY DATA TYPE
	 */
	public static final Label  LABEL_ACCESSORY = Label.label("accessory");
	public static final String ACCESSORY_LABEL = "label";
	public static final String ACCESSORY_SERIALNUMBER = "serialnumber";
	public static final String ACCESSORY_MODEL = "model";
	public static final String ACCESSORY_MANUFACTURER = "manufacturer";
	public static final String ACCESSORY_ISREGISTER = "isregister";
	public static final String ACCESSORY_IMAGEURL = "imageurl";
	public static final String ACCESSORY_TYPE = "type";
	public static final String ACCESSORY_PROTOCOLTYPE = "protocoltype";
	
	public static final String ACC_TYPE_AUTOMATIC_DOOR = "AUTOMATIC_DOOR";
	public static final String ACC_TYPE_CAM = "CAM";
	public static final String ACC_TYPE_CONTROL_BOARD = "CONTROL_BOARD";
	public static final String ACC_TYPE_FAN = "FAN";
	public static final String ACC_TYPE_LIGHT = "LIGHT";
	public static final String ACC_TYPE_LIGHT_COLORED = "LIGHT_COLORED";
	public static final String ACC_TYPE_LIGHT_COLORED_DIMMABLE = "LIGHT_COLORED_DIMMABLE";
	public static final String ACC_TYPE_DIMMABLE = "LIGHT_DIMMABLE";
	public static final String ACC_TYPE_LOCK_MECHANISM = "LOCK_MECHANISM";
	public static final String ACC_TYPE_SWITCH = "SWITCH";
	public static final String ACC_TYPE_THERMOSTAT = "THERMOSTAT";
	
	public static final String ACC_PROTOCOL_TYPE_HAP = "HAP";
	public static final String ACC_PROTOCOL_TYPE_JSON_SIMULATION = "JSON_SIMULATION";
	public static final String ACC_PROTOCOL_TYPE_ZIGBEE = "ZIGBEE";
	public static final String ACC_PROTOCOL_TYPE_ZWAVE = "ZWAVE";
	
	/**
	 * ROOM DATA TYPE
	 */
	public static final Label  LABEL_ROOM = Label.label("room");
	public static final String ROOM_LABEL = "label";
	public static final String ROOM_IMAGEURL = "imageurl";
	
	/**
	 * ZONE DATA TYPE
	 */
	public static final Label  LABEL_ZONE = Label.label("zone");
	public static final String ZONE_LABEL = "label";
	public static final String ZONE_IMAGEURL = "imageurl";
	
	/**
	 * HOME DATA TYPE
	 */
	public static final Label  LABEL_HOME = Label.label("home");
	public static final String HOME_LABEL = "label";
	public static final String HOME_IMAGEURL = "imageurl";
	
	/**
	 * ASSET DATA TYPE
	 */
	public static final Label  LABEL_ASSET = Label.label("asset");
	public static final String ASSET_LABEL = "label";
	public static final String ASSET_TYPE = "type";
	public static final String ASSET_MODE = "mode";
	
	public static final String ASSET_TYPE_IMAGE = "IMAGE";
	public static final String ASSET_TYPE_FILE = "FILE";
	public static final String ASSET_TYPE_OTHER = "OTHER";
	
	public static final String ASSET_MODE_SYSTEM = "SYSTEM";
	public static final String ASSET_MODE_USER = "USER";
	
	/**
	 * RELATION TYPE
	 *
	 * @author ericpinet
	 * <br> 2016-07-10
	 */
	public static enum RelTypes implements RelationshipType
	{
	    CONTAINS
	}
	
	/**
	   The caller references the constants using <tt>Consts.EMPTY_STRING</tt>, 
	   and so on. Thus, the caller should be prevented from constructing objects of 
	   this class, by declaring this private constructor. 
	  */
	  private Consts(){
	    //this prevents even the native class from 
	    //calling this constructor as well :
	    throw new AssertionError();
	  }

}
