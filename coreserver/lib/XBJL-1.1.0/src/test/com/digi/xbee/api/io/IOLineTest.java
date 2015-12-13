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
package com.digi.xbee.api.io;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class IOLineTest {

	// Constants.
	private static final int INVALID_ID = -1;
	
	// Variables.
	private IOLine[] ioLines;
	
	@Before
	public void setup() {
		// Retrieve the list of enum. values.
		ioLines = IOLine.values();
	}
	
	/**
	 * Verify that the name of each IO Line enum entry is valid.
	 */
	@Test
	public void testIOLineNames() {
		for (IOLine ioLine:ioLines) {
			assertNotNull(ioLine.name());
			assertTrue(ioLine.name().length() > 0);
		}
	}
	
	/**
	 * Verify that each IO line enum. value can be retrieved statically 
	 * using its index.
	 */
	@Test
	public void testIOLineStaticAccess() {
		for (IOLine ioLine:ioLines)
			assertEquals(ioLine, IOLine.getDIO(ioLine.getIndex()));
	}
	
	/**
	 * Verify that when trying to get an IO line not contained in the enumeration, 
	 * a null value is retrieved.
	 */
	@Test
	public void testNullIOLineIsRetrievedWithInvalidIndex() {
		assertNull(IOLine.getDIO(INVALID_ID));
	}
	
	/**
	 * Verify that all the IO lines have a configuration command.
	 */
	@Test
	public void testNotNullConfigurationCommand() {
		for (IOLine ioLine:ioLines)
			assertNotNull(ioLine.getConfigurationATCommand());
	}
	
	/**
	 * Verify that only DIO10 and DIO11 lines are PWM capable.
	 */
	@Test
	public void testPWMCapabilities() {
		for (IOLine ioLine:ioLines) {
			if (ioLine.getIndex() == 10 || ioLine.getIndex() == 11) {
				assertTrue(ioLine.hasPWMCapability());
				assertNotNull(ioLine.getPWMDutyCycleATCommand());
			} else {
				assertFalse(ioLine.hasPWMCapability());
				assertNull(ioLine.getPWMDutyCycleATCommand());
			}
		}
	}
}
