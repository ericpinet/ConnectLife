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

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.digi.xbee.api.utils.HexUtils;

public class AssociationIndicationStatusTest {

	// Variables.
	private AssociationIndicationStatus[] associationIndicationStatusValues;
	
	
	@Before
	public void setup() {
		// Retrieve the list of enum. values.
		associationIndicationStatusValues = AssociationIndicationStatus.values();
	}
	
	/**
	 * Test method for {@link com.digi.xbee.api.models.AssociationIndicationStatus#getValue()}.
	 * 
	 * <p>Verify that the ID of each {@code AssociationIndicationStatus} entry is valid.</p>
	 */
	@Test
	public void testAssociationIndicationStatusValues() {
		for (AssociationIndicationStatus associationIndicationStatus:associationIndicationStatusValues)
			assertTrue(associationIndicationStatus.getValue() >= 0);
	}
	
	/**
	 * Test method for {@link com.digi.xbee.api.models.AssociationIndicationStatus#name()}.
	 * 
	 * <p>Verify that the name of each {@code AssociationIndicationStatus} entry is valid.</p>
	 */
	@Test
	public void testAssociationIndicationStatusNames() {
		for (AssociationIndicationStatus associationIndicationStatus:associationIndicationStatusValues) {
			assertNotNull(associationIndicationStatus.name());
			assertTrue(associationIndicationStatus.name().length() > 0);
		}
	}
	
	/**
	 * Test method for {@link com.digi.xbee.api.models.AssociationIndicationStatus#getDescription()}.
	 * 
	 * <p>Verify that the description of each {@code AssociationIndicationStatus} entry is valid.</p>
	 */
	@Test
	public void testAssociationIndicationStatusDescriptions() {
		for (AssociationIndicationStatus associationIndicationStatus:associationIndicationStatusValues) {
			assertNotNull(associationIndicationStatus.getDescription());
			assertTrue(associationIndicationStatus.getDescription().length() > 0);
		}
	}
	
	/**
	 * Test method for {@link com.digi.xbee.api.models.AssociationIndicationStatus#get(int)}.
	 * 
	 * <p>Verify that each {@code AssociationIndicationStatus} entry can be retrieved statically 
	 * using its value.</p>
	 */
	@Test
	public void testAssociationIndicationStatusStaticAccess() {
		for (AssociationIndicationStatus associationIndicationStatus:associationIndicationStatusValues)
			assertEquals(associationIndicationStatus, AssociationIndicationStatus.get(associationIndicationStatus.getValue()));
	}
	
	/**
	 * Test method for {@link com.digi.xbee.api.models.AssociationIndicationStatus#get(int)}.
	 * 
	 * <p>Verify that when trying to get an invalid {@code AssociationIndicationStatus} entry, a 
	 * {@code null} entry is retrieved.</p>
	 */
	@Test
	public void testAssociationIndicationStatusStaticInvalidAccess() {
		assertNull(AssociationIndicationStatus.get(0xFE));
	}
	
	/**
	 * Test method for {@link com.digi.xbee.api.models.AssociationIndicationStatus#toString()}.
	 * 
	 * <p>Verify that the {@code toString()} method of an {@code AssociationIndicationStatus} 
	 * entry returns its description correctly.</p>
	 */
	@Test
	public void testAssociationIndicationStatusToString() {
		for (AssociationIndicationStatus associationIndicationStatus:associationIndicationStatusValues)
			assertEquals(HexUtils.byteToHexString((byte)associationIndicationStatus.getValue()) + ": " + associationIndicationStatus.getDescription(), associationIndicationStatus.toString());
	}
}