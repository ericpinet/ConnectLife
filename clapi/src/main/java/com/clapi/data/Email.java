/**
 *  Email.java
 *  clapi
 *
 *  Created by ericpinet on 2015-11-08.
 *  Copyright (c) 2015 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.clapi.data;

/**
 * Email address. 
 * 
 * @author ericpinet
 * <br> 2015-11-08
 */
public class Email {
	
	/**
	 * Enum EmailType
	 * 
	 * @author ericpinet
	 * <br> 2015-11-08
	 */
	public enum EmailType {
		PERSONAL,
		WORK,
		OTHER	
	}
	
	/**
	 * UID of the email generated by the server.
	 */
	private String uid;
	
	/**
	 * Email address
	 */
	private String email;
	
	/**
	 * Email type. 
	 */
	private EmailType type;

	/**
	 * Default Constructor.
	 * 
	 * @param uid
	 * @param email
	 * @param type
	 */
	public Email(String uid, String email, EmailType type) {
		super();
		this.uid = uid;
		this.email = email;
		this.type = type;
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
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the type
	 */
	public EmailType getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(EmailType type) {
		this.type = type;
	}
}
