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
package com.digi.xbee.api.packet;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;

import java.util.LinkedHashMap;

import static org.junit.Assert.assertThat;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.digi.xbee.api.packet.APIFrameType;
import com.digi.xbee.api.utils.HexUtils;

public class GenericXBeePacketTest {
	
	@Rule
	public ExpectedException exception = ExpectedException.none();

	public GenericXBeePacketTest() {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link com.digi.xbee.api.packet.GenericXBeePacket#createPacket(byte[])}.
	 * 
	 * <p>A {@code NullPointerException} exception must be thrown when parsing a 
	 * {@code null} byte array.</p>
	 */
	@Test
	public final void testCreatePacketNullPayload() {
		// Setup the resources for the test.
		byte[] payload = null;
		exception.expect(NullPointerException.class);
		exception.expectMessage(is(equalTo("Generic packet payload cannot be null.")));
		
		// Call the method under test that should throw a NullPointerException.
		GenericXBeePacket.createPacket(payload);
	}
	
	/**
	 * Test method for {@link com.digi.xbee.api.packet.GenericXBeePacket#createPacket(byte[])}.
	 * 
	 * <p>An {@code IllegalArgumentException} exception must be thrown when 
	 * parsing an empty byte array.</p>
	 */
	@Test
	public final void testCreatePacketEmptyPayload() {
		// Setup the resources for the test.
		byte[] payload = new byte[0];
		exception.expect(IllegalArgumentException.class);
		exception.expectMessage(is(equalTo("Incomplete Generic packet.")));
		
		// Call the method under test that should throw an IllegalArgumentException.
		GenericXBeePacket.createPacket(payload);
	}
	
	/**
	 * Test method for {@link com.digi.xbee.api.packet.GenericXBeePacket#createPacket(byte[])}.
	 * 
	 * <p>An {@code IllegalArgumentException} exception must be thrown when 
	 * the payload does not start with the right frame type 
	 * ({@code APIFrameType.GENERIC}).</p>
	 */
	@Test
	public final void testCreatePacketInvalidFrameType() {
		// Setup the resources for the test.
		int frameType = 0x13;
		
		byte[] payload = new byte[1];
		payload[0] = (byte)frameType;
		
		exception.expect(IllegalArgumentException.class);
		exception.expectMessage(is(equalTo("Payload is not a Generic packet.")));
		
		// Call the method under test that should throw an IllegalArgumentException.
		GenericXBeePacket.createPacket(payload);
	}
	
	/**
	 * Test method for {@link com.digi.xbee.api.packet.GenericXBeePacket#createPacket(byte[])}.
	 * 
	 * <p>A valid API Generic packet with the provided options.</p>
	 */
	@Test
	public final void testCreatePacketValidPayloadWithoutData() {
		// Setup the resources for the test.
		int frameType = APIFrameType.GENERIC.getValue();
		
		byte[] payload = new byte[1];
		payload[0] = (byte)frameType;
		
		// Call the method under test.
		GenericXBeePacket packet = GenericXBeePacket.createPacket(payload);
		
		// Verify the result.
		assertThat("Returned length is not the expected one", packet.getPacketLength(), is(equalTo(payload.length)));
		assertThat("Returned Received Data is not the expected one", packet.getRFData(), is(nullValue()));
		
		assertThat("Returned payload array is not the expected one", packet.getPacketData(), is(equalTo(payload)));
	}
	
	/**
	 * Test method for {@link com.digi.xbee.api.packet.GenericXBeePacket#createPacket(byte[])}.
	 * 
	 * <p>A valid API Generic packet with the provided options.</p>
	 */
	@Test
	public final void testCreatePacketValidPayloadWithData() {
		// Setup the resources for the test.
		int frameType = APIFrameType.GENERIC.getValue();
		byte[] data = new byte[]{0x68, 0x6F, 0x6C, 0x61};
		
		byte[] payload = new byte[1 + data.length];
		payload[0] = (byte)frameType;
		System.arraycopy(data, 0, payload, 1, data.length);
		
		// Call the method under test.
		GenericXBeePacket packet = GenericXBeePacket.createPacket(payload);
		
		// Verify the result.
		assertThat("Returned length is not the expected one", packet.getPacketLength(), is(equalTo(payload.length)));
		assertThat("Returned Received Data is not the expected one", packet.getRFData(), is(equalTo(data)));
		
		assertThat("Returned payload array is not the expected one", packet.getPacketData(), is(equalTo(payload)));
	}
	
	/**
	 * Test method for {@link com.digi.xbee.api.packet.GenericXBeePacket#GenericXBeePacket(byte[])}.
	 * 
	 * <p>Construct a new Generic packet but with {@code null} data.</p>
	 */
	@Test
	public final void testCreateGenericPacketDataNull() {
		// Setup the resources for the test.
		byte[] data = null;
		
		int expectedLength = 1 /* Frame type */;
		
		// Call the method under test.
		GenericXBeePacket packet = new GenericXBeePacket(data);
		
		// Verify the result.
		assertThat("Returned length is not the expected one", packet.getPacketLength(), is(equalTo(expectedLength)));
		assertThat("Returned RF data is not the expected one", packet.getRFData(), is(nullValue(byte[].class)));
		assertThat("Generic packet needs API Frame ID", packet.needsAPIFrameID(), is(equalTo(false)));
	}
	
	/**
	 * Test method for {@link com.digi.xbee.api.packet.GenericXBeePacket#GenericXBeePacket(byte[])}.
	 * 
	 * <p>Construct a new Generic packet but with non-{@code null} data.</p>
	 */
	@Test
	public final void testCreateGenericPacketDataNotNull() {
		// Setup the resources for the test.
		byte[] data = new byte[]{0x68, 0x6F, 0x6C, 0x61};
		
		int expectedLength = 1 /* Frame type */ + data.length /* Data */;
		
		// Call the method under test.
		GenericXBeePacket packet = new GenericXBeePacket(data);
		
		// Verify the result.
		assertThat("Returned length is not the expected one", packet.getPacketLength(), is(equalTo(expectedLength)));
		assertThat("Returned RF data is not the expected one", packet.getRFData(), is(equalTo(data)));
		assertThat("Generic packet needs API Frame ID", packet.needsAPIFrameID(), is(equalTo(false)));
	}
	
	/**
	 * Test method for {@link com.digi.xbee.api.packet.GenericXBeePacket#getAPIData()}.
	 * 
	 * <p>Test the get API parameters, data {@code null}.</p>
	 */
	@Test
	public final void testGetAPIDataNullData() {
		// Setup the resources for the test.
		byte[] data = null;
		GenericXBeePacket packet = new GenericXBeePacket(data);
		
		int expectedLength = 0;
		byte[] expectedData = new byte[expectedLength];
		
		// Call the method under test.
		byte[] apiData = packet.getAPIData();
		
		// Verify the result.
		assertThat("API data is not the expected", apiData, is(equalTo(expectedData)));
	}
	
	/**
	 * Test method for {@link com.digi.xbee.api.packet.GenericXBeePacket#getAPIData()}.
	 * 
	 * <p>Test the get API parameters, data not {@code null}.</p>
	 */
	@Test
	public final void testGetAPIDataNotNullData() {
		// Setup the resources for the test.
		byte[] data = new byte[]{0x68, 0x6F, 0x6C, 0x61};
		GenericXBeePacket packet = new GenericXBeePacket(data);
		
		byte[] expectedData = data;
		
		// Call the method under test.
		byte[] apiData = packet.getAPIData();
		
		// Verify the result.
		assertThat("API data is not the expected", apiData, is(equalTo(expectedData)));
		
	}
	
	/**
	 * Test method for {@link com.digi.xbee.api.packet.GenericXBeePacket#getAPIPacketParameters()}.
	 * 
	 * <p>Test the get API parameters, {@code null} data.</p>
	 */
	@Test
	public final void testGetAPIPacketParametersNullData() {
		// Setup the resources for the test.
		byte[] data = null;
		GenericXBeePacket packet = new GenericXBeePacket(data);
		
		// Call the method under test.
		LinkedHashMap<String, String> packetParams = packet.getAPIPacketParameters();
				
		// Verify the result.
		assertThat("Packet parameters map size is not the expected one", packetParams.size(), is(equalTo(0)));
		assertThat("RF Data is not the expected one", packetParams.get("RF Data"), is(nullValue(String.class)));
	}
	
	/**
	 * Test method for {@link com.digi.xbee.api.packet.GenericXBeePacket#getAPIPacketParameters()}.
	 * 
	 * <p>Test the get API parameters, non-{@code null} data.</p>
	 */
	@Test
	public final void testGetAPIPacketParametersNotNullData() {
		// Setup the resources for the test.
		byte[] data = new byte[]{0x68, 0x6F, 0x6C, 0x61};;
		GenericXBeePacket packet = new GenericXBeePacket(data);
		
		String expectedData = HexUtils.prettyHexString(data);
		
		// Call the method under test.
		LinkedHashMap<String, String> packetParams = packet.getAPIPacketParameters();
				
		// Verify the result.
		assertThat("Packet parameters map size is not the expected one", packetParams.size(), is(equalTo(1)));
		assertThat("RF Data is not the expected one", packetParams.get("RF Data"), is(equalTo(expectedData)));
	}
	
	/**
	 * Test method for {@link com.digi.xbee.api.packet.GenericXBeePacket#isBroadcast()}.
	 * 
	 * <p>Test if a Generic packet is a broadcast packet, never should be.</p>
	 */
	@Test
	public final void testIsBroadcast() {
		// Setup the resources for the test.
		byte[] data = new byte[]{0x68, 0x6F, 0x6C, 0x61};;
		GenericXBeePacket packet = new GenericXBeePacket(data);
		
		// Call the method under test and verify the result.
		assertThat("Packet should not be broadcast", packet.isBroadcast(), is(equalTo(false)));
	}
}
