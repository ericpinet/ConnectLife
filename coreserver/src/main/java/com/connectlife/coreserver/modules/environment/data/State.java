/**
 *  State.java
 *  coreserver
 *
 *  Created by ericpinet on 2015-09-11.
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
 * <br> 2015-09-11
 */
public class State {
	
	/**
	 * Type of state.
	 */
	public enum Type {
		BOOLEAN, INTEGER, FLOAT, STRING, LIST
	}
	
	/**
	 * State's name
	 */
	private String m_name;
	
	/**
	 * State's boolean value.
	 */
	private boolean m_boolean_value;
	
	/**
	 * State's integer value.
	 */
	private int m_integer_value;
	
	/**
	 * State's float value.
	 */
	private float m_float_value;
	
	/**
	 * State's string value.
	 */
	private String m_string_value;
	
	/**
	 * State's list value.
	 */
	private int m_list_value;
	
	/**
	 * State's list description.
	 */
	private String[] m_list_desc;
	
	/**
	 * State's type.
	 */
	private Type m_type;

	/**
	 * Constructor for boolean state.
	 * 
	 * @param m_name			Name of state
	 * @param m_boolean_value	boolean value.
	 */
	public State(String m_name, boolean m_boolean_value) {
		super();
		this.m_name = m_name;
		this.m_boolean_value = m_boolean_value;
		this.m_type = Type.BOOLEAN;
	}

	/**
	 * Constructor for integer value.
	 * 
	 * @param m_name			Name of state.
	 * @param m_integer_value	Integer value.
	 */
	public State(String m_name, int m_integer_value) {
		super();
		this.m_name = m_name;
		this.m_integer_value = m_integer_value;
		this.m_type = Type.INTEGER;
	}

	/**
	 * Constructor for float.
	 * 
	 * @param m_name			Name of state.
	 * @param m_float_value		Float value.
	 */
	public State(String m_name, float m_float_value) {
		super();
		this.m_name = m_name;
		this.m_float_value = m_float_value;
		this.m_type = Type.FLOAT;
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
	 * @return the m_boolean_value
	 */
	public boolean getBooleanValue() {
		return m_boolean_value;
	}

	/**
	 * @param _boolean_value the m_boolean_value to set
	 */
	public void setBooleanValue(boolean _boolean_value) {
		this.m_boolean_value = _boolean_value;
	}

	/**
	 * @return the m_integer_value
	 */
	public int getIntegerValue() {
		return m_integer_value;
	}

	/**
	 * @param _integer_value the m_integer_value to set
	 */
	public void setIntegerValue(int _integer_value) {
		this.m_integer_value = _integer_value;
	}

	/**
	 * @return the m_float_value
	 */
	public float getFloatValue() {
		return m_float_value;
	}

	/**
	 * @param _float_value the m_float_value to set
	 */
	public void setFloatValue(float _float_value) {
		this.m_float_value = _float_value;
	}

	/**
	 * @return the m_string_value
	 */
	public String getStringValue() {
		return m_string_value;
	}

	/**
	 * @param _string_value the m_string_value to set
	 */
	public void setStringValue(String _string_value) {
		this.m_string_value = _string_value;
	}

	/**
	 * @return the m_list_value
	 */
	public int getListValue() {
		return m_list_value;
	}

	/**
	 * @param _list_value the m_list_value to set
	 */
	public void setListValue(int _list_value) {
		this.m_list_value = _list_value;
	}

	/**
	 * @return the m_list_desc
	 */
	public String[] getListDesc() {
		return m_list_desc;
	}

	/**
	 * @param _list_desc the m_list_desc to set
	 */
	public void setListDesc(String[] _list_desc) {
		this.m_list_desc = _list_desc;
	}

	/**
	 * @return the m_type
	 */
	public Type getType() {
		return m_type;
	}

	/**
	 * @param _type the m_type to set
	 */
	public void setType(Type _type) {
		this.m_type = _type;
	}
	
	

	
	
	

}
