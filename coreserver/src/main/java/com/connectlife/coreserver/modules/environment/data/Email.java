/**
 *  Email.java
 *  coreserver
 *
 *  Created by ericpinet on 2015-09-09.
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
 * <br> 2015-09-09
 */
public class Email {
	
	/**
	 * List of type of Email.
	 */
	public enum Type{
		PERSONAL, WORK, OTHER
	}
	
	/**
	 * Email
	 */
	private String m_email;
	
	/**
	 * Type of this email.
	 */
	private Type m_type;

	/**
	 * Default constructor.
	 * 
	 * @param _email 	Email.
	 * @param _type		Type.
	 */
	public Email(String _email, Type _type) {
		super();
		this.m_email = _email;
		this.m_type = _type;
	}

	/**
	 * @return the m_email
	 */
	public String getEmail() {
		return m_email;
	}

	/**
	 * @param _email the m_email to set
	 */
	public void setEmail(String _email) {
		this.m_email = _email;
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
