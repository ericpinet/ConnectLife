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
package com.digi.xbee.api.packet.raw;

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

import com.digi.xbee.api.models.XBee64BitAddress;
import com.digi.xbee.api.packet.APIFrameType;
import com.digi.xbee.api.utils.HexUtils;

public class TX64PacketTest {

	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	public TX64PacketTest() {
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
	 * Test method for {@link com.digi.xbee.api.packet.raw.TX64Packet#createPacket(byte[])}.
	 * 
	 * <p>A {@code NullPointerException} exception must be thrown when parsing a 
	 * {@code null} byte array.</p>
	 */
	@Test
	public final void testCreatePacketNullPayload() {
		// Setup the resources for the test.
		byte[] payload = null;
		exception.expect(NullPointerException.class);
		exception.expectMessage(is(equalTo("TX64 Request packet payload cannot be null.")));
		
		// Call the method under test that should throw a NullPointerException.
		TX64Packet.createPacket(payload);
	}
	
	/**
	 * Test method for {@link com.digi.xbee.api.packet.raw.TX64Packet#createPacket(byte[])}.
	 * 
	 * <p>An {@code IllegalArgumentException} exception must be thrown when 
	 * parsing an empty byte array.</p>
	 */
	@Test
	public final void testCreatePacketEmptyPayload() {
		// Setup the resources for the test.
		byte[] payload = new byte[0];
		exception.expect(IllegalArgumentException.class);
		exception.expectMessage(is(equalTo("Incomplete TX64 Request packet.")));
		
		// Call the method under test that should throw an IllegalArgumentException.
		TX64Packet.createPacket(payload);
	}
	
	/**
	 * Test method for {@link com.digi.xbee.api.packet.raw.TX64Packet#createPacket(byte[])}.
	 * 
	 * <p>An {@code IllegalArgumentException} exception must be thrown when 
	 * parsing a byte array shorter than the needed one is provided.</p>
	 */
	@Test
	public final void testCreatePacketPayloadShorterThanNeeded() {
		// Setup the resources for the test.
		int frameType = APIFrameType.TX_64.getValue();
		int frameID = 0xE7;
		XBee64BitAddress dest64Addr = new XBee64BitAddress("0013A2004032D9AB");
		
		byte[] payload = new byte[10];
		payload[0] = (byte)frameType;
		payload[1] = (byte)frameID;
		System.arraycopy(dest64Addr.getValue(), 0, payload, 2, dest64Addr.getValue().length);
		
		exception.expect(IllegalArgumentException.class);
		exception.expectMessage(is(equalTo("Incomplete TX64 Request packet.")));
		
		// Call the method under test that should throw an IllegalArgumentException.
		TX64Packet.createPacket(payload);
	}
	
	/**
	 * Test method for {@link com.digi.xbee.api.packet.raw.TX64Packet#createPacket(byte[])}.
	 * 
	 * <p>An {@code IllegalArgumentException} exception must be thrown when 
	 * parsing a byte array not including the Frame type.</p>
	 */
	@Test
	public final void testCreatePacketPayloadNotIncludingFrameType() {
		// Setup the resources for the test.
		int frameID = 0xE7;
		XBee64BitAddress dest64Addr = new XBee64BitAddress("0013A2004032D9AB");
		int options = 0x04;
		byte[] data = new byte[]{0x68, 0x6F, 0x6C, 0x61};
		
		byte[] payload = new byte[10 + data.length];
		payload[0] = (byte)frameID;
		System.arraycopy(dest64Addr.getValue(), 0, payload, 1, dest64Addr.getValue().length);
		payload[9] = (byte)options;
		System.arraycopy(data, 0, payload, 10, data.length);
		
		exception.expect(IllegalArgumentException.class);
		exception.expectMessage(is(equalTo("Payload is not a TX64 Request packet.")));
		
		// Call the method under test that should throw an IllegalArgumentException.
		TX64Packet.createPacket(payload);
	}
	
	/**
	 * Test method for {@link com.digi.xbee.api.packet.raw.TX64Packet#createPacket(byte[])}.
	 * 
	 * <p>A valid API TX64 packet with the provided options without RF data is 
	 * created.</p>
	 */
	@Test
	public final void testCreatePacketValidPayloadWithoutData() {
		// Setup the resources for the test.
		int frameType = APIFrameType.TX_64.getValue();
		int frameID = 0xE7;
		XBee64BitAddress dest64Addr = new XBee64BitAddress("0013A2004032D9AB");
		int options = 0x04;
		
		byte[] payload = new byte[11];
		payload[0] = (byte)frameType;
		payload[1] = (byte)frameID;
		System.arraycopy(dest64Addr.getValue(), 0, payload, 2, dest64Addr.getValue().length);
		payload[10] = (byte)options;
		
		// Call the method under test.
		TX64Packet packet = TX64Packet.createPacket(payload);
		
		// Verify the result.
		assertThat("Returned length is not the expected one", packet.getPacketLength(), is(equalTo(payload.length)));
		assertThat("Frame ID is not the expected one", packet.getFrameID(), is(equalTo(frameID)));
		assertThat("Returned destination 64-bit address is not the expected one", packet.get64bitDestinationAddress(), is(equalTo(dest64Addr)));
		assertThat("Returned transmit options is not the expected one", packet.getTransmitOptions(), is(equalTo(options)));
		assertThat("Returned RF Data is not the expected one", packet.getRFData(), is(nullValue()));
		
		assertThat("Returned payload array is not the expected one", packet.getPacketData(), is(equalTo(payload)));
	}
	
	/**
	 * Test method for {@link com.digi.xbee.api.packet.raw.TX64Packet#createPacket(byte[])}.
	 * 
	 * <p>A valid API TX64 packet with the provided options and RF data is 
	 * created.</p>
	 */
	@Test
	public final void testCreatePacketValidPayloadWithData() {
		// Setup the resources for the test.
		int frameType = APIFrameType.TX_64.getValue();
		int frameID = 0xE7;
		XBee64BitAddress dest64Addr = new XBee64BitAddress("0013A2004032D9AB");
		int options = 0x04;
		byte[] data = new byte[]{0x68, 0x6F, 0x6C, 0x61};
		
		byte[] payload = new byte[11 + data.length];
		payload[0] = (byte)frameType;
		payload[1] = (byte)frameID;
		System.arraycopy(dest64Addr.getValue(), 0, payload, 2, dest64Addr.getValue().length);
		payload[10] = (byte)options;
		System.arraycopy(data, 0, payload, 11, data.length);
		
		// Call the method under test.
		TX64Packet packet = TX64Packet.createPacket(payload);
		
		// Verify the result.
		assertThat("Returned length is not the expected one", packet.getPacketLength(), is(equalTo(payload.length)));
		assertThat("Frame ID is not the expected one", packet.getFrameID(), is(equalTo(frameID)));
		assertThat("Returned destination 64-bit address is not the expected one", packet.get64bitDestinationAddress(), is(equalTo(dest64Addr)));
		assertThat("Returned transmit options is not the expected one", packet.getTransmitOptions(), is(equalTo(options)));
		assertThat("Returned RF Data is not the expected one", packet.getRFData(), is(equalTo(data)));
		
		assertThat("Returned payload array is not the expected one", packet.getPacketData(), is(equalTo(payload)));
	}
	
	/**
	 * Test method for {@link com.digi.xbee.api.packet.raw.TX64Packet#TX64Packet(int, XBee64BitAddress, int, byte[])}.
	 * 
	 * <p>Construct a new TX64 packet but with a {@code null} 64-bit address. 
	 * This must throw a {@code NullPointerException}.</p>
	 */
	@Test
	public final void testCreateTX64Packet16BitAddressNull() {
		// Setup the resources for the test.
		int frameID = 5;
		XBee64BitAddress dest64Addr = null;
		int options = 40;
		byte[] data = new byte[]{0x68, 0x6F, 0x6C, 0x61};
		
		exception.expect(NullPointerException.class);
		exception.expectMessage(is(equalTo("64-bit destination address cannot be null.")));
		
		// Call the method under test that should throw a NullPointerException.
		new TX64Packet(frameID, dest64Addr, options, data);
	}
	
	/**
	 * Test method for {@link com.digi.xbee.api.packet.raw.TX64Packet#TX64Packet(int, XBee64BitAddress, int, byte[])}.
	 * 
	 * <p>Construct a new TX64 packet but with a frame ID bigger than 255. 
	 * This must throw an {@code IllegalArgumentException}.</p>
	 */
	@Test
	public final void testCreateTX64PacketFrameIDBiggerThan255() {
		// Setup the resources for the test.
		int frameID = 524;
		XBee64BitAddress dest64Addr = new XBee64BitAddress("0013A2004032D9AB");
		int options = 40;
		byte[] data = new byte[]{0x68, 0x6F, 0x6C, 0x61};
		
		exception.expect(IllegalArgumentException.class);
		exception.expectMessage(is(equalTo("Frame ID must be between 0 and 255.")));
		
		// Call the method under test that should throw an IllegalArgumentException.
		new TX64Packet(frameID, dest64Addr, options, data);
	}
	
	/**
	 * Test method for {@link com.digi.xbee.api.packet.raw.TX64Packet#TX16Packet(int, XBee64BitAddress, int, byte[])}.
	 * 
	 * <p>Construct a new TX64 packet but with a negative frame ID. This 
	 * must throw an {@code IllegalArgumentException}.</p>
	 */
	@Test
	public final void testCreateTX64PacketFrameIDNegative() {
		// Setup the resources for the test.
		int frameID = -6;
		XBee64BitAddress dest64Addr = new XBee64BitAddress("0013A2004032D9AB");
		int options = 40;
		byte[] data = new byte[]{0x68, 0x6F, 0x6C, 0x61};
		
		exception.expect(IllegalArgumentException.class);
		exception.expectMessage(is(equalTo("Frame ID must be between 0 and 255.")));
		
		// Call the method under test that should throw an IllegalArgumentException.
		new TX64Packet(frameID, dest64Addr, options, data);
	}
	
	/**
	 * Test method for {@link com.digi.xbee.api.packet.raw.TX64Packet#TX64Packet(int, XBee64BitAddress, int, byte[])}.
	 * 
	 * <p>Construct a new TX64 packet but with transmit options bigger 
	 * than 255. This must throw an {@code IllegalArgumentException}.</p>
	 */
	@Test
	public final void testCreateTX64PacketTransmitOptinsBiggerThan255() {
		// Setup the resources for the test.
		int frameID = 5;
		XBee64BitAddress dest64Addr = new XBee64BitAddress("0013A2004032D9AB");
		int options = 2360;
		byte[] data = new byte[]{0x68, 0x6F, 0x6C, 0x61};
		
		exception.expect(IllegalArgumentException.class);
		exception.expectMessage(is(equalTo("Transmit options must be between 0 and 255.")));
		
		// Call the method under test that should throw an IllegalArgumentException.
		new TX64Packet(frameID, dest64Addr, options, data);
	}
	
	/**
	 * Test method for {@link com.digi.xbee.api.packet.raw.TX64Packet#TX64Packet(int, XBee64BitAddress, int, byte[])}.
	 * 
	 * <p>Construct a new TX64 packet but with a negative transmit options.
	 * This must throw an {@code IllegalArgumentException}.</p>
	 */
	@Test
	public final void testCreateTX64PacketTransmitOptionsNegative() {
		// Setup the resources for the test.
		int frameID = 5;
		XBee64BitAddress dest64Addr = new XBee64BitAddress("0013A2004032D9AB");
		int options = -40;
		byte[] data = new byte[]{0x68, 0x6F, 0x6C, 0x61};
		
		exception.expect(IllegalArgumentException.class);
		exception.expectMessage(is(equalTo("Transmit options must be between 0 and 255.")));
		
		// Call the method under test that should throw an IllegalArgumentException.
		new TX64Packet(frameID, dest64Addr, options, data);
	}
	
	/**
	 * Test method for {@link com.digi.xbee.api.packet.raw.TX64Packet#TX64Packet(int, XBee64BitAddress, int, byte[])}.
	 * 
	 * <p>Construct a new TX64 packet but with valid parameters but without 
	 * data ({@code null}).</p>
	 */
	@Test
	public final void testCreateTX64PacketValidDataNull() {
		// Setup the resources for the test.
		int frameID = 0x65;
		XBee64BitAddress dest64Addr = new XBee64BitAddress("0013A2004032D9AB");
		int options = 40;
		byte[] data = null;
		
		int expectedLength = 1 /* Frame type */ + 1 /* Frame ID */ + 8 /* 64-bit address */ + 1 /* options */;
		
		// Call the method under test.
		TX64Packet packet = new TX64Packet(frameID, dest64Addr, options, data);
		
		// Verify the result.
		assertThat("Returned length is not the expected one", packet.getPacketLength(), is(equalTo(expectedLength)));
		assertThat("Frame ID is not the expected one", packet.getFrameID(), is(equalTo(frameID)));
		assertThat("Returned destination 64-bit address is not the expected one", packet.get64bitDestinationAddress(), is(equalTo(dest64Addr)));
		assertThat("Returned transmit options are not the expected one", packet.getTransmitOptions(), is(equalTo(options)));
		assertThat("Returned Command Data is not the expected one", packet.getRFData(), is(nullValue(byte[].class)));
		assertThat("TX64 packet needs API Frame ID", packet.needsAPIFrameID(), is(equalTo(true)));
	}
	
	/**
	 * Test method for {@link com.digi.xbee.api.packet.raw.TX64Packet#TX64Packet(int, XBee64BitAddress, int, byte[])}.
	 * 
	 * <p>Construct a new TX64 packet but with valid parameters with data.</p>
	 */
	@Test
	public final void testCreateTX64PacketValidDataNonNull() {
		// Setup the resources for the test.
		int frameID = 0x65;
		XBee64BitAddress dest64Addr = new XBee64BitAddress("0013A2004032D9AB");
		int options = 40;
		byte[] data = new byte[]{0x68, 0x6F, 0x6C, 0x61};
		
		int expectedLength = 1 /* Frame type */ + 1 /* Frame ID */ + 8 /* 64-bit address */ + 1 /* options */ + data.length /* Data */;
		
		// Call the method under test.
		TX64Packet packet = new TX64Packet(frameID, dest64Addr, options, data);
		
		// Verify the result.
		assertThat("Returned length is not the expected one", packet.getPacketLength(), is(equalTo(expectedLength)));
		assertThat("Frame ID is not the expected one", packet.getFrameID(), is(equalTo(frameID)));
		assertThat("Returned destination 64-bit address is not the expected one", packet.get64bitDestinationAddress(), is(equalTo(dest64Addr)));
		assertThat("Returned transmit options are not the expected one", packet.getTransmitOptions(), is(equalTo(options)));
		assertThat("Returned Command Data is not the expected one", packet.getRFData(), is(equalTo(data)));
		assertThat("TX64 packet needs API Frame ID", packet.needsAPIFrameID(), is(equalTo(true)));
	}
	
	/**
	 * Test method for {@link com.digi.xbee.api.packet.raw.TX64Packet#getAPIData()}.
	 * 
	 * <p>Test the get API parameters but with a {@code null} received data.</p>
	 */
	@Test
	public final void testGetAPIDataReceivedDataNull() {
		// Setup the resources for the test.
		int frameID = 0x65;
		XBee64BitAddress dest64Addr = new XBee64BitAddress("0013A2004032D9AB");
		int options = 40;
		byte[] data = null;
		TX64Packet packet = new TX64Packet(frameID, dest64Addr, options, data);
		
		int expectedLength = 1 /* Frame ID */ + 8 /* 64-bit address */ + 1 /* options */;
		byte[] expectedData = new byte[expectedLength];
		expectedData[0] = (byte)frameID;
		System.arraycopy(dest64Addr.getValue(), 0, expectedData, 1, dest64Addr.getValue().length);
		expectedData[9] = (byte)options;
		
		// Call the method under test.
		byte[] apiData = packet.getAPIData();
		
		// Verify the result.
		assertThat("API data is not the expected", apiData, is(equalTo(expectedData)));
	}
	
	/**
	 * Test method for {@link com.digi.xbee.api.packet.raw.TX64Packet#getAPIData()}.
	 * 
	 * <p>Test the get API parameters but with a not-{@code null} received data.</p>
	 */
	@Test
	public final void testGetAPIDataReceivedDataNotNull() {
		// Setup the resources for the test.
		int frameID = 0x65;
		XBee64BitAddress dest64Addr = new XBee64BitAddress("0013A2004032D9AB");
		int options = 40;
		byte[] data = new byte[]{0x68, 0x6F, 0x6C, 0x61};
		TX64Packet packet = new TX64Packet(frameID, dest64Addr, options, data);
		
		int expectedLength = 1 /* Frame ID */ + 8 /* 64-bit address */ + 1 /* options */ + data.length /* Data */;
		byte[] expectedData = new byte[expectedLength];
		expectedData[0] = (byte)frameID;
		System.arraycopy(dest64Addr.getValue(), 0, expectedData, 1, dest64Addr.getValue().length);
		expectedData[9] = (byte)options;
		System.arraycopy(data, 0, expectedData, 10, data.length);
		
		// Call the method under test.
		byte[] apiData = packet.getAPIData();
		
		// Verify the result.
		assertThat("API data is not the expected", apiData, is(equalTo(expectedData)));
	}
	
	/**
	 * Test method for {@link com.digi.xbee.api.packet.raw.TX64Packet#getAPIPacketParameters()}.
	 * 
	 * <p>Test the get API parameters but with a {@code null} received data.</p>
	 */
	@Test
	public final void testGetAPIPacketParametersReceivedDataNull() {
		// Setup the resources for the test.
		int frameID = 0x65;
		XBee64BitAddress dest64Addr = new XBee64BitAddress("0013A2004032D9AB");
		int options = 40;
		byte[] data = null;
		TX64Packet packet = new TX64Packet(frameID, dest64Addr, options, data);
		
		String expectedDest64Addr = HexUtils.prettyHexString(dest64Addr.getValue());
		String expectedOptions = HexUtils.prettyHexString(Integer.toHexString(options));
		
		// Call the method under test.
		LinkedHashMap<String, String> packetParams = packet.getAPIPacketParameters();
		
		// Verify the result.
		assertThat("Packet parameters map size is not the expected one", packetParams.size(), is(equalTo(2)));
		assertThat("Destination 64-bit Address is not the expected one", packetParams.get("64-bit dest. address"), is(equalTo(expectedDest64Addr)));
		assertThat("Transmit options are not the expected", packetParams.get("Options"), is(equalTo(expectedOptions)));
		assertThat("Data is not the expected", packetParams.get("RF data"), is(nullValue(String.class)));
	}
	
	/**
	 * Test method for {@link com.digi.xbee.api.packet.raw.TX64Packet#getAPIPacketParameters()}.
	 * 
	 * <p>Test the get API parameters but with a non-{@code null} received data.</p>
	 */
	@Test
	public final void testGetAPIPacketParametersReceivedDataNotNull() {
		// Setup the resources for the test.
		int frameID = 0x65;
		XBee64BitAddress dest64Addr = new XBee64BitAddress("0013A2004032D9AB");
		int options = 40;
		byte[] data = new byte[]{0x68, 0x6F, 0x6C, 0x61};
		TX64Packet packet = new TX64Packet(frameID, dest64Addr, options, data);
		
		String expectedDest64Addr = HexUtils.prettyHexString(dest64Addr.getValue());
		String expectedOptions = HexUtils.prettyHexString(Integer.toHexString(options));
		String expectedData = HexUtils.prettyHexString(HexUtils.byteArrayToHexString(data));
		
		// Call the method under test.
		LinkedHashMap<String, String> packetParams = packet.getAPIPacketParameters();
		
		// Verify the result.
		assertThat("Packet parameters map size is not the expected one", packetParams.size(), is(equalTo(3)));
		assertThat("Destination 64-bit Address is not the expected one", packetParams.get("64-bit dest. address"), is(equalTo(expectedDest64Addr)));
		assertThat("Receive options are not the expected", packetParams.get("Options"), is(equalTo(expectedOptions)));
		assertThat("Data is not the expected", packetParams.get("RF data"), is(equalTo(expectedData)));
	}
	
	/**
	 * Test method for {@link com.digi.xbee.api.packet.raw.TX64Packet#isBroadcast()}.
	 * 
	 * <p>Test if a TX 64 packet is a broadcast packet address when the 
	 * destination address is not broadcast.</p>
	 */
	@Test
	public final void testIsBroadcastWithNonBroadcastDestinationAddress() {
		// Setup the resources for the test.
		int frameID = 0x65;
		XBee64BitAddress dest64Addr = new XBee64BitAddress("0013A2004032D9AB");
		int options = 40;
		byte[] data = new byte[]{0x68, 0x6F, 0x6C, 0x61};
		TX64Packet packet = new TX64Packet(frameID, dest64Addr, options, data);
		
		// Call the method under test and verify the result.
		assertThat("Packet should not be broadcast", packet.isBroadcast(), is(equalTo(false)));
	}
	
	
	/**
	 * Test method for {@link com.digi.xbee.api.packet.raw.TX64Packet#isBroadcast()}.
	 * 
	 * <p>Test if a TX 64 packet is a broadcast packet address when the 
	 * destination address is broadcast.</p>
	 */
	@Test
	public final void testIsBroadcastWithBroadcastDestinationAddress() {
		// Setup the resources for the test.
		int frameID = 0x65;
		XBee64BitAddress dest64Addr = new XBee64BitAddress("FFFF");
		int options = 40;
		byte[] data = new byte[]{0x68, 0x6F, 0x6C, 0x61};
		TX64Packet packet = new TX64Packet(frameID, dest64Addr, options, data);
		
		// Call the method under test and verify the result.
		assertThat("Packet should be broadcast", packet.isBroadcast(), is(equalTo(true)));
	}
}
