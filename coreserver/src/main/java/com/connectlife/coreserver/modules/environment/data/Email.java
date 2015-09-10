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
	enum EmailType{
		HOME, WORK, OTHERS
	}
	
	/**
	 * Email
	 */
	private String m_email;
	
	/**
	 * Type of this email.
	 */
	private EmailType m_type;

	/**
	 * @param _email
	 * @param _type
	 */
	public Email(String _email, EmailType _type) {
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
	 * @param m_email the m_email to set
	 */
	public void setEmail(String m_email) {
		this.m_email = m_email;
	}

	/**
	 * @return the m_type
	 */
	public EmailType getType() {
		return m_type;
	}

	/**
	 * @param m_type the m_type to set
	 */
	public void setType(EmailType m_type) {
		this.m_type = m_type;
	}
	
	

}
