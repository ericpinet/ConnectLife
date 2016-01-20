/**
 *  Characteristic.java
 *  clapi
 *
 *  Created by ericpinet on 2015-11-08.
 *  Copyright (c) 2015 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.clapi.data;

/**
 * Characteristic for an accessory service. 
 * 
 * @author ericpinet
 * <br> 2015-11-08
 */
public class Characteristic {
	
	/**
	 * Value of boolean true in string
	 */
	static private final String BOOLEAN_TRUE = "true";
	
	/**
	 * Value of boolean false in string
	 */
	static private final String BOOLEAN_FALSE = "false";
	
	/**
	 * Enum CharacteristicAccessMode for a service characteristic.
	 * 
	 * @author ericpinet
	 * <br> 2015-11-08
	 */
	public enum CharacteristicAccessMode {
		READ_ONLY,
		WRITE_ONLY,
		READ_WRITE	
	}
	
	/**
	 * Enum CharacteristicType for a service characteristic.
	 * 
	 * @author ericpinet
	 * <br> 2015-11-08
	 */
	public enum CharacteristicType {
		BOOLEAN,
		ENUM,
		FLOAT,
		INTEGER,
		STATIC_STRING,
		WRITE_ONLY_BOOLEAN
	}
	
	/**
	 * Enum CharacteristicEventType for a service characteristic.
	 * 
	 * @author ericpinet
	 * <br> 2015-11-08
	 */
	public enum CharacteristicEventType {
		NO_EVENT,
		EVENT
	}
	
	/**
	 * UID of the characteristic generated by the server.
	 */
	private String uid;
	
	/**
	 * Label to describe the characteristic.
	 */
	private String label;
	
	/**
	 * Access Mode for the characteristic.
	 */
	private CharacteristicAccessMode mode;
	
	/**
	 * Type of the characteristic.
	 */
	private CharacteristicType type;
	
	/**
	 * Indicated if the characteristic is a event.
	 */
	private CharacteristicEventType event;
	
	/**
	 * Data value.
	 */
	private String data;

	/**
	 * Defautl constructor. 
	 * 
	 * @param uid Uid of the characteristic.
	 * @param label Label of the characteristic.
	 * @param mode mode of the characteristic.
	 * @param type type of the characteristic.
	 * @param event event of the characteristic.
	 * @param data of the characteristic.
	 */
	public Characteristic(String uid, String label, CharacteristicAccessMode mode, CharacteristicType type, CharacteristicEventType event, String data) {
		super();
		this.uid = uid;
		this.label = label;
		this.mode = mode;
		this.type = type;
		this.event = event;
		this.data = data;
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
	 * @return the mode
	 */
	public CharacteristicAccessMode getMode() {
		return mode;
	}

	/**
	 * @param mode the mode to set
	 */
	public void setMode(CharacteristicAccessMode mode) {
		this.mode = mode;
	}

	/**
	 * @return the type
	 */
	public CharacteristicType getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(CharacteristicType type) {
		this.type = type;
	}

	/**
	 * @return the event
	 */
	public CharacteristicEventType getEvent() {
		return event;
	}

	/**
	 * @param event the event to set
	 */
	public void setEvent(CharacteristicEventType event) {
		this.event = event;
	}

	/**
	 * @return the data
	 * @throws Exception When data type is invalid.
	 */
	public String getData() throws Exception {
		if(type == CharacteristicType.STATIC_STRING){
			return data;
		}
		else{
			throw new Exception("This characteristic isn't string format.");
		}
	}

	/**
	 * @param data the data to set
	 * @throws Exception When data type is invalid.
	 */
	public void setData(String data) throws Exception {
		if(type == CharacteristicType.STATIC_STRING){
			this.data = data;
		}
		else{
			throw new Exception("This characteristic isn't string format.");
		}
	}
	
	/**
	 * Return the value of characteristic in boolean.
	 * @return The data.
	 * @throws Exception When data type is invalid.
	 */
	public boolean getDataBoolean() throws Exception {
		if(type == CharacteristicType.BOOLEAN){
			if(data.equalsIgnoreCase("true")){
				return true;
			}
			else{
				return false;
			}
		}
		else{
			throw new Exception("This characteristic isn't boolean format.");
		}
	}
	
	/**
	 * Set the value of characteristic in boolean.
	 * @param data Data to set.
	 * @throws Exception When data type is invalid.
	 */
	public void setBooleanData(boolean data) throws Exception{
		if(	type == CharacteristicType.BOOLEAN || 
			type == CharacteristicType.WRITE_ONLY_BOOLEAN){
			
			if(data){
				this.data = BOOLEAN_TRUE;
			}
			else{
				this.data = BOOLEAN_FALSE;
			}
		}
		else{
			throw new Exception("This characteristic isn't boolean format.");
		}
	}
	
	/**
	 * Return the value of characteristic in integer.
	 * @return The data.
	 * @throws Exception When data type is invalid.
	 */
	public int getDataInteger() throws Exception{
		int ret_val = 0;
		if(type == CharacteristicType.INTEGER){
			try{
				ret_val = Integer.valueOf(data);
			}
			catch(Exception e){
				ret_val = 0;
			}
		}
		else{
			throw new Exception("Characteristic isn't integer format.");
		}
		return ret_val;
	}
	
	/**
	 * Set the value of characteristic in integer.
	 * @param data Data to set.
	 * @throws Exception When data type is invalid.
	 */
	public void setIntegerData(int data) throws Exception{
		if(type == CharacteristicType.INTEGER){
			try{
				this.data = String.valueOf(data);
			}
			catch(Exception e){
				this.data = "";
			}
		}
		else{
			throw new Exception("Characteristic isn't integer format.");
		}
	}
	
	/**
	 * Return the value of characteristic in float.
	 * @return The data.
	 * @throws Exception When data type is invalid.
	 */
	public float getDataFloat() throws Exception{
		float ret_val = 0;
		if(type == CharacteristicType.FLOAT){
			try{
				ret_val = Float.valueOf(data);
			}
			catch(Exception e){
				ret_val = 0;
			}
		}
		else{
			throw new Exception("Characteristic isn't float format.");
		}
		return ret_val;
	}
	
	/**
	 * Set the value of characteristic in integer.
	 * @param data Data to set.
	 * @throws Exception When data type is invalid.
	 */
	public void setFloatData(float data) throws Exception{
		if(type == CharacteristicType.FLOAT){
			try{
				this.data = String.valueOf(data);
			}
			catch(Exception e){
				this.data = "";
			}
		}
		else{
			throw new Exception("Characteristic isn't float format.");
		}
	}
	
}
