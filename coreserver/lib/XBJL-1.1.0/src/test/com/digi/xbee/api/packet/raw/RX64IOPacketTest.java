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

import com.digi.xbee.api.io.IOLine;
import com.digi.xbee.api.io.IOSample;
import com.digi.xbee.api.models.XBee64BitAddress;
import com.digi.xbee.api.packet.APIFrameType;
import com.digi.xbee.api.utils.HexUtils;

public class RX64IOPacketTest {

	@Rule
	public ExpectedException exception = ExpectedException.none();

	public RX64IOPacketTest() {
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
	 * Test method for {@link com.digi.xbee.api.packet.raw.RX64IOPacket#createPacket(byte[])}.
	 * 
	 * <p>A {@code NullPointerException} exception must be thrown when parsing a 
	 * {@code null} byte array.</p>
	 */
	@Test
	public final void testCreatePacketNullPayload() {
		// Setup the resources for the test.
		byte[] payload = null;
		exception.expect(NullPointerException.class);
		exception.expectMessage(is(equalTo("RX64 Address IO packet payload cannot be null.")));
		
		// Call the method under test that should throw a NullPointerException.
		RX64IOPacket.createPacket(payload);
	}
	
	/**
	 * Test method for {@link com.digi.xbee.api.packet.raw.RX64IOPacket#createPacket(byte[])}.
	 * 
	 * <p>An {@code IllegalArgumentException} exception must be thrown when 
	 * parsing an empty byte array.</p>
	 */
	@Test
	public final void testCreatePacketEmptyPayload() {
		// Setup the resources for the test.
		byte[] payload = new byte[0];
		exception.expect(IllegalArgumentException.class);
		exception.expectMessage(is(equalTo("Incomplete RX64 Address IO packet.")));
		
		// Call the method under test that should throw an IllegalArgumentException.
		RX64IOPacket.createPacket(payload);
	}
	
	/**
	 * Test method for {@link com.digi.xbee.api.packet.raw.RX64IOPacket#createPacket(byte[])}.
	 * 
	 * <p>An {@code IllegalArgumentException} exception must be thrown when 
	 * parsing a byte array shorter than the needed one is provided.</p>
	 */
	@Test
	public final void testCreatePacketPayloadShorterThanNeeded() {
		// Setup the resources for the test.
		int frameType = APIFrameType.RX_IO_64.getValue();
		XBee64BitAddress source64Addr = new XBee64BitAddress("0013A2004032D9AB");
		int rssi = 40;
		
		byte[] payload = new byte[10];
		payload[0] = (byte)frameType;
		System.arraycopy(source64Addr.getValue(), 0, payload, 1, source64Addr.getValue().length);
		payload[9] = (byte)rssi;
		
		exception.expect(IllegalArgumentException.class);
		exception.expectMessage(is(equalTo("Incomplete RX64 Address IO packet.")));
		
		// Call the method under test that should throw an IllegalArgumentException.
		RX64IOPacket.createPacket(payload);
	}
	
	/**
	 * Test method for {@link com.digi.xbee.api.packet.common.RX64IOPacket#createPacket(byte[])}.
	 * 
	 * <p>An {@code IllegalArgumentException} exception must be thrown when 
	 * parsing a byte array shorter than the needed one is provided.</p>
	 */
	@Test
	public final void testCreatePacketReceivedDataShorterThanNeeded() {
		// Setup the resources for the test.
		int frameType = APIFrameType.RX_IO_64.getValue();
		XBee64BitAddress source64Addr = new XBee64BitAddress("0013A2004032D9AB");
		int rssi = 40;
		byte[] data = new byte[]{(byte)0xFF, (byte)0xFF};
		
		byte[] payload = new byte[10 + data.length];
		payload[0] = (byte)frameType;
		System.arraycopy(source64Addr.getValue(), 0, payload, 1, source64Addr.getValue().length);
		payload[9] = (byte)rssi;
		System.arraycopy(data, 0, payload, 10, data.length);
		
		exception.expect(IllegalArgumentException.class);
		exception.expectMessage(is(equalTo("IO sample payload must be longer than 4.")));
		
		// Call the method under test that should throw an IllegalArgumentException.
		RX64IOPacket.createPacket(payload);
	}
	
	/**
	 * Test method for {@link com.digi.xbee.api.packet.raw.RX64IOPacket#createPacket(byte[])}.
	 * 
	 * <p>An {@code IllegalArgumentException} exception must be thrown when 
	 * parsing a byte array not including the Frame type.</p>
	 */
	@Test
	public final void testCreatePacketPayloadNotIncludingFrameType() {
		// Setup the resources for the test.
		XBee64BitAddress source64Addr = new XBee64BitAddress("0013A2004032D9AB");
		int rssi = 40;
		int options = 0x01;
		byte[] data = new byte[]{(byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF};
		
		byte[] payload = new byte[10 + data.length];
		System.arraycopy(source64Addr.getValue(), 0, payload, 0, source64Addr.getValue().length);
		payload[8] = (byte)rssi;
		payload[9] = (byte)options;
		System.arraycopy(data, 0, payload, 10, data.length);
		
		exception.expect(IllegalArgumentException.class);
		exception.expectMessage(is(equalTo("Payload is not a RX64 Address IO packet.")));
		
		// Call the method under test that should throw an IllegalArgumentException.
		RX64IOPacket.createPacket(payload);
	}
	
	/**
	 * Test method for {@link com.digi.xbee.api.packet.raw.RX64IOPacket#createPacket(byte[])}.
	 * 
	 * <p>A valid RX64 Address IO packet with the provided options without RF 
	 * data is created.</p>
	 */
	@Test
	public final void testCreatePacketValidPayloadWithoutData() {
		// Setup the resources for the test.
		int frameType = APIFrameType.RX_IO_64.getValue();
		XBee64BitAddress source64Addr = new XBee64BitAddress("0013A2004032D9AB");
		int rssi = 40;
		int options = 0x01;
		
		byte[] payload = new byte[11];
		payload[0] = (byte)frameType;
		System.arraycopy(source64Addr.getValue(), 0, payload, 1, source64Addr.getValue().length);
		payload[9] = (byte)rssi;
		payload[10] = (byte)options;
		
		// Call the method under test.
		RX64IOPacket packet = RX64IOPacket.createPacket(payload);
		
		// Verify the result.
		assertThat("Returned length is not the expected one", packet.getPacketLength(), is(equalTo(payload.length)));
		assertThat("Returned source 64-bit address is not the expected one", packet.get64bitSourceAddress(), is(equalTo(source64Addr)));
		assertThat("Returned RSSI is not the expected one", packet.getRSSI(), is(equalTo(rssi)));
		assertThat("Returned received options is not the expected one", packet.getReceiveOptions(), is(equalTo(options)));
		assertThat("Returned Received Data is not the expected one", packet.getRFData(), is(nullValue()));
		
		assertThat("Returned payload array is not the expected one", packet.getPacketData(), is(equalTo(payload)));
	}
	
	/**
	 * Test method for {@link com.digi.xbee.api.packet.raw.RX64IOPacket#createPacket(byte[])}.
	 * 
	 * <p>A valid RX64 Address IO packet with the provided options and RF data 
	 * is created.</p>
	 */
	@Test
	public final void testCreatePacketValidPayloadWithData() {
		// Setup the resources for the test.
		int frameType = APIFrameType.RX_IO_64.getValue();
		XBee64BitAddress source64Addr = new XBee64BitAddress("0013A2004032D9AB");
		int rssi = 40;
		int options = 0x01;
		byte[] data = new byte[]{(byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF};
		
		byte[] payload = new byte[11 + data.length];
		payload[0] = (byte)frameType;
		System.arraycopy(source64Addr.getValue(), 0, payload, 1, source64Addr.getValue().length);
		payload[9] = (byte)rssi;
		payload[10] = (byte)options;
		System.arraycopy(data, 0, payload, 11, data.length);
		
		// Call the method under test.
		RX64IOPacket packet = RX64IOPacket.createPacket(payload);
		
		// Verify the result.
		assertThat("Returned length is not the expected one", packet.getPacketLength(), is(equalTo(payload.length)));
		assertThat("Returned source 64-bit address is not the expected one", packet.get64bitSourceAddress(), is(equalTo(source64Addr)));
		assertThat("Returned RSSI is not the expected one", packet.getRSSI(), is(equalTo(rssi)));
		assertThat("Returned received options is not the expected one", packet.getReceiveOptions(), is(equalTo(options)));
		assertThat("Returned Received Data is not the expected one", packet.getRFData(), is(equalTo(data)));
		
		assertThat("Returned payload array is not the expected one", packet.getPacketData(), is(equalTo(payload)));
	}
	
	/**
	 * Test method for {@link com.digi.xbee.api.packet.raw.RX64IOPacket#RX64IOPacket(XBee64BitAddress, int, int, byte[])}.
	 * 
	 * <p>Construct a new RX64 IO packet but with a {@code null} 64-bit address. 
	 * This must throw a {@code NullPointerException}.</p>
	 */
	@Test
	public final void testCreateRX64IOPacket16BitAddressNull() {
		// Setup the resources for the test.
		XBee64BitAddress source64Addr = null;
		int rssi = 25;
		int options = 40;
		byte[] data = new byte[]{(byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF};
		
		exception.expect(NullPointerException.class);
		exception.expectMessage(is(equalTo("64-bit source address cannot be null.")));
		
		// Call the method under test that should throw a NullPointerException.
		new RX64IOPacket(source64Addr, rssi, options, data);
	}
	
	/**
	 * Test method for {@link com.digi.xbee.api.packet.raw.RX64IOPacket#RX64IOPacket(XBee64BitAddress, int, int, byte[])}.
	 * 
	 * <p>Construct a new RX64 IO packet but with a RSSI bigger than 100. 
	 * This must throw an {@code IllegalArgumentException}.</p>
	 */
	@Test
	public final void testCreateRX64IOPacketRssiBiggerThan100() {
		// Setup the resources for the test.
		XBee64BitAddress source64Addr = new XBee64BitAddress("0013A2004032D9AB");
		int rssi = 725;
		int options = 40;
		byte[] data = new byte[]{(byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF};
		
		exception.expect(IllegalArgumentException.class);
		exception.expectMessage(is(equalTo("RSSI value must be between 0 and 100.")));
		
		// Call the method under test that should throw a NullPointerException.
		new RX64IOPacket(source64Addr, rssi, options, data);
	}
	
	/**
	 * Test method for {@link com.digi.xbee.api.packet.raw.RX64IOPacket#RX64IOPacket(XBee64BitAddress, int, int, byte[])}.
	 * 
	 * <p>Construct a new RX64 IO packet but with a negative RSSI. 
	 * This must throw an {@code IllegalArgumentException}.</p>
	 */
	@Test
	public final void testCreateRX64IOPacketRssiNegative() {
		// Setup the resources for the test.
		XBee64BitAddress source64Addr = new XBee64BitAddress("0013A2004032D9AB");
		int rssi = -5;
		int options = 40;
		byte[] data = new byte[]{(byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF};
		
		exception.expect(IllegalArgumentException.class);
		exception.expectMessage(is(equalTo("RSSI value must be between 0 and 100.")));
		
		// Call the method under test that should throw a NullPointerException.
		new RX64IOPacket(source64Addr, rssi, options, data);
	}
	
	/**
	 * Test method for {@link com.digi.xbee.api.packet.raw.RX64IOPacket#RX64IOPacket(XBee64BitAddress, int, int, byte[])}.
	 * 
	 * <p>Construct a new RX64 IO packet but with a RSSI bigger than 255. 
	 * This must throw an {@code IllegalArgumentException}.</p>
	 */
	@Test
	public final void testCreateRX64IOPacketOptionsBiggerThan255() {
		// Setup the resources for the test.
		XBee64BitAddress source64Addr = new XBee64BitAddress("0013A2004032D9AB");
		int rssi = 75;
		int options = 863;
		byte[] data = new byte[]{(byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF};
		
		exception.expect(IllegalArgumentException.class);
		exception.expectMessage(is(equalTo("Receive options value must be between 0 and 255.")));
		
		// Call the method under test that should throw a NullPointerException.
		new RX64IOPacket(source64Addr, rssi, options, data);
	}
	
	/**
	 * Test method for {@link com.digi.xbee.api.packet.raw.RX64IOPacket#RX64IOPacket(XBee64BitAddress, int, int, byte[])}.
	 * 
	 * <p>Construct a new RX64 IO packet but with a negative options. 
	 * This must throw an {@code IllegalArgumentException}.</p>
	 */
	@Test
	public final void testCreateRX64IOPacketOptionsNegative() {
		// Setup the resources for the test.
		XBee64BitAddress source64Addr = new XBee64BitAddress("0013A2004032D9AB");
		int rssi = 75;
		int options = -12;
		byte[] data = new byte[]{(byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF};
		
		exception.expect(IllegalArgumentException.class);
		exception.expectMessage(is(equalTo("Receive options value must be between 0 and 255.")));
		
		// Call the method under test that should throw a NullPointerException.
		new RX64IOPacket(source64Addr, rssi, options, data);
	}
	
	/**
	 * Test method for {@link com.digi.xbee.api.packet.raw.RX64IOPacket#RX64IOPacket(XBee64BitAddress, int, int, byte[])}.
	 * 
	 * <p>Construct a new RX64 IO packet but with {@code null} data.</p>
	 */
	@Test
	public final void testCreateRX64IOPacketReceiveDataNull() {
		// Setup the resources for the test.
		XBee64BitAddress source64Addr = new XBee64BitAddress("0013A2004032D9AB");
		int rssi = 75;
		int options = 40;
		byte[] data = null;
		
		int expectedLength = 1 /* Frame type */ + 8 /* 64-bit address */ + 1 /* RSSI */ + 1 /* options */;
		
		// Call the method under test.
		RX64IOPacket packet = new RX64IOPacket(source64Addr, rssi, options, data);
		
		// Verify the result.
		assertThat("Returned length is not the expected one", packet.getPacketLength(), is(equalTo(expectedLength)));
		assertThat("Returned source 64-bit address is not the expected one", packet.get64bitSourceAddress(), is(equalTo(source64Addr)));
		assertThat("Returned RSSI is not the expected one", packet.getRSSI(), is(equalTo(rssi)));
		assertThat("Returned received options is not the expected one", packet.getReceiveOptions(), is(equalTo(options)));
		assertThat("Returned Data is not the expected one", packet.getRFData(), is(equalTo(data)));
		assertThat("RX64 IO packet does NOT need API Frame ID", packet.needsAPIFrameID(), is(equalTo(false)));
	}
	
	/**
	 * Test method for {@link com.digi.xbee.api.packet.raw.RX64IOPacket#RX64IOPacket(XBee64BitAddress, int, int, byte[])}.
	 * 
	 * <p>Construct a new RX64 IO packet but with data length less than 5. 
	 * This must throw an {@code IllegalArgumentException}.</p>
	 */
	@Test
	public final void testCreateRX64IOPacketReceiveDataLengthLessThan5() {
		// Setup the resources for the test.
		XBee64BitAddress source64Addr = new XBee64BitAddress("0013A2004032D9AB");
		int rssi = 75;
		int options = 40;
		byte[] data = new byte[]{0x68, 0x6F, 0x6C, 0x61};
		
		exception.expect(IllegalArgumentException.class);
		exception.expectMessage(is(equalTo("IO sample payload must be longer than 4.")));
		
		// Call the method under test that should throw an IllegalArgumentException.
		new RX64IOPacket(source64Addr, rssi, options, data);
	}
	
	/**
	 * Test method for {@link com.digi.xbee.api.packet.raw.RX64IOPacket#RX64IOPacket(XBee64BitAddress, int, int, byte[])}.
	 * 
	 * <p>Construct a new RX64 IO packet but with data length less than 5. 
	 * This must throw an {@code IllegalArgumentException}.</p>
	 */
	@Test
	public final void testCreateRX64IOPacket() {
		// Setup the resources for the test.
		XBee64BitAddress source64Addr = new XBee64BitAddress("0013A2004032D9AB");
		int rssi = 75;
		int options = 40;
		byte[] data = new byte[]{(byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF};
		
		int expectedLength = 1 /* Frame type */ + 8 /* 64-bit address */ + 1 /* RSSI */ + 1 /* options */ + data.length /* Data */;
		
		// Call the method under test.
		RX64IOPacket packet = new RX64IOPacket(source64Addr, rssi, options, data);
		
		// Verify the result.
		assertThat("Returned length is not the expected one", packet.getPacketLength(), is(equalTo(expectedLength)));
		assertThat("Returned source 64-bit address is not the expected one", packet.get64bitSourceAddress(), is(equalTo(source64Addr)));
		assertThat("Returned RSSI is not the expected one", packet.getRSSI(), is(equalTo(rssi)));
		assertThat("Returned received options is not the expected one", packet.getReceiveOptions(), is(equalTo(options)));
		assertThat("Returned Data is not the expected one", packet.getRFData(), is(equalTo(data)));
		assertThat("RX64 IO packet does NOT need API Frame ID", packet.needsAPIFrameID(), is(equalTo(false)));
	}
	
	/**
	 * Test method for {@link com.digi.xbee.api.packet.raw.RX64IOPacket#getAPIData()}.
	 * 
	 * <p>Test the get API parameters but with a {@code null} received data.</p>
	 */
	@Test
	public final void testGetAPIDataReceivedDataNull() {
		// Setup the resources for the test.
		XBee64BitAddress source64Addr = new XBee64BitAddress("0013A2004032D9AB");
		int rssi = 75;
		int options = 40;
		byte[] receivedData = null;
		RX64IOPacket packet = new RX64IOPacket(source64Addr, rssi, options, receivedData);
		
		int expectedLength = 8 /* 64-bit address */ + 1 /* RSSI */ + 1 /* options */;
		byte[] expectedData = new byte[expectedLength];
		System.arraycopy(source64Addr.getValue(), 0, expectedData, 0, source64Addr.getValue().length);
		expectedData[8] = (byte)rssi;
		expectedData[9] = (byte)options;
		
		// Call the method under test.
		byte[] data = packet.getAPIData();
		
		// Verify the result.
		assertThat("API data is not the expected", data, is(equalTo(expectedData)));
	}
	
	/**
	 * Test method for {@link com.digi.xbee.api.packet.raw.RX64IOPacket#getAPIData()}.
	 * 
	 * <p>Test the get API parameters but with a non-{@code null} received data.</p>
	 */
	@Test
	public final void testGetAPIDataReceivedDataNotNull() {
		// Setup the resources for the test.
		XBee64BitAddress source64Addr = new XBee64BitAddress("0013A2004032D9AB");
		int rssi = 75;
		int options = 40;
		byte[] receivedData = new byte[]{(byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF};
		RX64IOPacket packet = new RX64IOPacket(source64Addr, rssi, options, receivedData);
		
		int expectedLength = 8 /* 64-bit address */ + 1 /* RSSI */ + 1 /* options */ + receivedData.length /* Data */;
		byte[] expectedData = new byte[expectedLength];
		System.arraycopy(source64Addr.getValue(), 0, expectedData, 0, source64Addr.getValue().length);
		expectedData[8] = (byte)rssi;
		expectedData[9] = (byte)options;
		System.arraycopy(receivedData, 0, expectedData, 10, receivedData.length);
		
		// Call the method under test.
		byte[] data = packet.getAPIData();
		
		// Verify the result.
		assertThat("API data is not the expected", data, is(equalTo(expectedData)));
	}
	
	/**
	 * Test method for {@link com.digi.xbee.api.packet.raw.RX64IOPacket#getAPIPacketParameters()}.
	 * 
	 * <p>Test the get API parameters but with {@code null} received data.</p>
	 */
	@Test
	public final void testGetAPIPacketParametersReceivedDataNull() {
		// Setup the resources for the test.
		XBee64BitAddress source64Addr = new XBee64BitAddress("0013A2004032D9AB");
		int rssi = 75;
		int options = 40;
		byte[] receivedData = null;
		RX64IOPacket packet = new RX64IOPacket(source64Addr, rssi, options, receivedData);
		
		String expectedSource64Addr = HexUtils.prettyHexString(source64Addr.getValue());
		String expectedRSSI = HexUtils.prettyHexString(HexUtils.integerToHexString(rssi, 1));
		String expectedOptions = HexUtils.prettyHexString(Integer.toHexString(options));
		
		// Call the method under test.
		LinkedHashMap<String, String> packetParams = packet.getAPIPacketParameters();
		
		// Verify the result.
		assertThat("Packet parameters map size is not the expected one", packetParams.size(), is(equalTo(3)));
		assertThat("Source 64-bit Address is not the expected one", packetParams.get("64-bit source address"), is(equalTo(expectedSource64Addr)));
		assertThat("RSSI is not the expected", packetParams.get("RSSI"), is(equalTo(expectedRSSI)));
		assertThat("Options are not the expected", packetParams.get("Options"), is(equalTo(expectedOptions)));
		assertThat("Number of samples is not the expected", packetParams.get("Number of samples"), is(nullValue(String.class)));
		assertThat("Digital channel mask is not the expected", packetParams.get("Digital channel mask"), is(nullValue(String.class)));
		assertThat("Analog channel mask is not the expected", packetParams.get("Analog channel mask"), is(nullValue(String.class)));
	}
	
	/**
	 * Test method for {@link com.digi.xbee.api.packet.raw.RX64IOPacket#getAPIPacketParameters()}.
	 * 
	 * <p>Test the get API parameters but with non-{@code null} received data.</p>
	 */
	@Test
	public final void testGetAPIPacketParametersReceivedDataNotNull() {
		// Setup the resources for the test.
		XBee64BitAddress source64Addr = new XBee64BitAddress("0013A2004032D9AB");
		int rssi = 75;
		int options = 40;
		byte[] receivedData = new byte[]{(byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF};
		RX64IOPacket packet = new RX64IOPacket(source64Addr, rssi, options, receivedData);
		
		String expectedSource64Addr = HexUtils.prettyHexString(source64Addr.getValue());
		String expectedRSSI = HexUtils.prettyHexString(HexUtils.integerToHexString(rssi, 1));
		String expectedOptions = HexUtils.prettyHexString(Integer.toHexString(options));
		IOSample expectedIoSample = new IOSample(receivedData);
		String expectedDigitalMask = HexUtils.prettyHexString(HexUtils.integerToHexString(expectedIoSample.getDigitalMask(), 2));
		String expectedAnalogMask = HexUtils.prettyHexString(HexUtils.integerToHexString(expectedIoSample.getAnalogMask(), 1));
		
		// Call the method under test.
		LinkedHashMap<String, String> packetParams = packet.getAPIPacketParameters();
		
		// Verify the result.
		assertThat("Packet parameters map size is not the expected one", packetParams.size(), is(equalTo(15)));
		assertThat("Source 64-bit Address is not the expected one", packetParams.get("64-bit source address"), is(equalTo(expectedSource64Addr)));
		assertThat("RSSI is not the expected", packetParams.get("RSSI"), is(equalTo(expectedRSSI)));
		assertThat("Options are not the expected", packetParams.get("Options"), is(equalTo(expectedOptions)));
		assertThat("Number of samples is not the expected", packetParams.get("Number of samples"), is(equalTo("01"))); // Always 1.
		assertThat("Digital channel mask is not the expected", packetParams.get("Digital channel mask"), is(equalTo(expectedDigitalMask)));
		assertThat("Analog channel mask is not the expected", packetParams.get("Analog channel mask"), is(equalTo(expectedAnalogMask)));
		for (int i = 0; i < 16; i++) {
			if (expectedIoSample.hasDigitalValue(IOLine.getDIO(i)))
				assertThat(packetParams.get(IOLine.getDIO(i).getName() + " digital value"), 
						is(equalTo(expectedIoSample.getDigitalValue(IOLine.getDIO(i)).getName())));
		}
		for (int i = 0; i < 6; i++)
			if (expectedIoSample.hasAnalogValue(IOLine.getDIO(i)))
				assertThat(packetParams.get(IOLine.getDIO(i).getName() + " analog value"), 
						is(equalTo(HexUtils.prettyHexString(HexUtils.integerToHexString(expectedIoSample.getAnalogValue(IOLine.getDIO(i)), 2)))));
	}
	
	/**
	 * Test method for {@link com.digi.xbee.api.packet.raw.RX64IOPacket#isBroadcast()}.
	 * 
	 * <p>Test if a RX64 IO packet is a broadcast packet when broadcast options 
	 * are not enabled in the options.</p>
	 */
	@Test
	public final void testIsBroadcastWithNonBroadcastOption() {
		// Setup the resources for the test.
		XBee64BitAddress source64Addr = new XBee64BitAddress("0013A2004032D9AB");
		int rssi = 75;
		int options = 0x20;
		byte[] receivedData = new byte[]{(byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF};
		RX64IOPacket packet = new RX64IOPacket(source64Addr, rssi, options, receivedData);
		
		// Call the method under test and verify the result.
		assertThat("Packet should not be broadcast", packet.isBroadcast(), is(equalTo(false)));
	}
	
	/**
	 * Test method for {@link com.digi.xbee.api.packet.raw.RX64IOPacket#isBroadcast()}.
	 * 
	 * <p>Test if a RX64 IO packet is a broadcast packet when broadcast (bit 1 
	 * - Address broadcast) is enabled in the options.</p>
	 */
	@Test
	public final void testIsBroadcastWithBroadcastOptionBit1Enabled() {
		// Setup the resources for the test.
		XBee64BitAddress source64Addr = new XBee64BitAddress("0013A2004032D9AB");
		int rssi = 75;
		int options = 0x22; /* bit 1 */
		byte[] receivedData = new byte[]{(byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF};
		RX64IOPacket packet = new RX64IOPacket(source64Addr, rssi, options, receivedData);
		
		// Call the method under test and verify the result.
		assertThat("Packet should be broadcast", packet.isBroadcast(), is(equalTo(true)));
	}
	
	/**
	 * Test method for {@link com.digi.xbee.api.packet.raw.RX64IOPacket#isBroadcast()}.
	 * 
	 * <p>Test if a RX64 IO packet is a broadcast packet when broadcast (bit 2 
	 * - PAN broadcast) is enabled in the options.</p>
	 */
	@Test
	public final void testIsBroadcastWithBroadcastOptionBit2Enabled() {
		// Setup the resources for the test.
		XBee64BitAddress source64Addr = new XBee64BitAddress("0013A2004032D9AB");
		int rssi = 75;
		int options = 0x34; /* bit 2 */
		byte[] receivedData = new byte[]{(byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF};
		RX64IOPacket packet = new RX64IOPacket(source64Addr, rssi, options, receivedData);
		
		// Call the method under test and verify the result.
		assertThat("Packet should be broadcast", packet.isBroadcast(), is(equalTo(true)));
	}
}
