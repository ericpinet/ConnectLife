/**
 * Copyright (c) 2014-2015 Digi International Inc.,
 * All rights not expressly granted are reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this file,
 * You can obtain one at http://mozilla.org/MPL/2.0/.
 *
 * Digi International Inc. 11001 Bren Road East, Minnetonka, MN 55343
 * =======================================================================
 */
package com.digi.xbee.api.models;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import org.junit.Test;

public class ATCommandTest {

	// Constants.
	private static final String INVALID_COMMAND = "Hello!";
	
	private static final String VALID_COMMAND = "AP";
	private static final String VALID_PARAMETER = "parameter_value";
	
	@Test
	/**
	 * Verify that AT Command object cannot be created using invalid parameters.
	 */
	public void testCreateWithInvalidParameters() {
		// Test with null command.
		try {
			new ATCommand(null);
			fail("Object should not have been created.");
		} catch (Exception e) {
			assertEquals(NullPointerException.class, e.getClass());
		}
		try {
			new ATCommand((String)null, (String)null);
			fail("Object should not have been created.");
		} catch (Exception e) {
			assertEquals(NullPointerException.class, e.getClass());
		}
		// Test with invalid command.
		try {
			new ATCommand(INVALID_COMMAND);
			fail("Object should not have been created.");
		} catch (Exception e) {
			assertEquals(IllegalArgumentException.class, e.getClass());
		}
		try {
			new ATCommand(INVALID_COMMAND, (String)null);
			fail("Object should not have been created.");
		} catch (Exception e) {
			assertEquals(IllegalArgumentException.class, e.getClass());
		}
	}
	
	@Test
	/**
	 * Verify that AT Command object can be created using a valid command name.
	 */
	public void testCreateWithValidCommand() {
		// Test with valid command.
		ATCommand command = null;
		try {
			command = new ATCommand(VALID_COMMAND);
		} catch (Exception e) {
			fail("This exception should have not been thrown.");
		}
		
		assertEquals(VALID_COMMAND, command.getCommand());
		assertNull(command.getParameter());
		assertNull(command.getParameterString());
	}
	
	@Test
	/**
	 * Verify that AT Command object can be created using a valid command and parameter.
	 */
	public void testCreateWithValidCommandAndParameter() {
		// Test with valid command and parameter.
		ATCommand command = null;
		try {
			command = new ATCommand(VALID_COMMAND, VALID_PARAMETER.getBytes());
		} catch (Exception e) {
			fail("This exception should have not been thrown.");
		}
		
		assertEquals(VALID_COMMAND, command.getCommand());
		assertArrayEquals(VALID_PARAMETER.getBytes(), command.getParameter());
		assertEquals(VALID_PARAMETER, command.getParameterString());
	}
	
	@Test
	/**
	 * Verify that AT Command object can be created using a valid command and parameter string.
	 */
	public void testCreateWithValidCommandAndParameterString() {
		// Test with valid command and string parameter.
		ATCommand command = null;
		try {
			command = new ATCommand(VALID_COMMAND, VALID_PARAMETER);
		} catch (Exception e) {
			fail("This exception should have not been thrown.");
		}
		
		assertEquals(VALID_COMMAND, command.getCommand());
		assertArrayEquals(VALID_PARAMETER.getBytes(), command.getParameter());
		assertEquals(VALID_PARAMETER, command.getParameterString());
	}
}
