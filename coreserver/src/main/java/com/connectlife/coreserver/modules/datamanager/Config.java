/**
 *  Config.java
 *  coreserver
 *
 *  Created by Eric Pinet <pineri01@gmail.com> on 2015-09-07.
 *  Copyright (c) 2015 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.connectlife.coreserver.modules.datamanager;

/**
 * Configuration use in the application
 * 
 * @author Eric Pinet (pineri01@gmail.com)
 * <br> 2015-09-07
 */
public class Config {
	
	/**
	 * Configuration name
	 */
	private String m_name;
	
	/**
	 * Config type
	 */
	private ConfigType m_type;
	
	/**
	 * Value of config if it's string
	 */
	private String m_string_value;
	
	/**
	 * Value of config if it's integer
	 */
	private int m_integer_value;

	/**
	 * Enum of config type.
	 */
	public enum ConfigType{
		STRING, INTEGER
	}
	
	/**
	 * Constructor for config with string value.
	 * @param _name		Config name.
	 * @param _value	Config value.
	 */
	public Config(String _name, String _value){
		m_name = _name;
		m_string_value = _value;
		m_type = ConfigType.STRING;
	}
	
	/**
	 * Constructor for config with integer value.
	 * @param _name		Config name.
	 * @param _value	Config value.
	 */
	public Config(String _name, Integer _value){
		m_name = _name;
		m_integer_value = _value;
		m_type = ConfigType.INTEGER;
	}
	
	/**
	 * Return the value of the config.
	 * 
	 * @return Value for this configuration.
	 */
	public Object getValue(){
		switch (m_type){
			case STRING:
				return m_string_value;
			
			case INTEGER:
				return m_integer_value;		
		}
		return null;
	}
	
	/**
	 * Return the name of the config.
	 * @return
	 */
	public String getName(){
		return m_name;
	}

}
