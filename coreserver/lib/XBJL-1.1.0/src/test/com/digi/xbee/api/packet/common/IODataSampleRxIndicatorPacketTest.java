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
package com.digi.xbee.api.packet.common;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.hamcrest.core.IsNot.not;

import static org.junit.Assert.assertThat;

import java.util.LinkedHashMap;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.digi.xbee.api.exceptions.OperationNotSupportedException;
import com.digi.xbee.api.io.IOLine;
import com.digi.xbee.api.io.IOSample;
import com.digi.xbee.api.models.XBee16BitAddress;
import com.digi.xbee.api.models.XBee64BitAddress;
import com.digi.xbee.api.packet.APIFrameType;
import com.digi.xbee.api.utils.HexUtils;

public class IODataSampleRxIndicatorPacketTest {

	@Rule
	public ExpectedException exception = ExpectedException.none();

	public IODataSampleRxIndicatorPacketTest() {
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
	 * Test method for {@link com.digi.xbee.api.packet.common.IODataSampleRxIndicatorPacket#createPacket(byte[])}.
	 * 
	 * <p>A {@code NullPointerException} exception must be thrown when parsing a 
	 * {@code null} byte array.</p>
	 */
	@Test
	public final void testCreatePacketNullPayload() {
		// Setup the resources for the test.
		byte[] payload = null;
		exception.expect(NullPointerException.class);
		exception.expectMessage(is(equalTo("IO Data Sample RX Indicator packet payload cannot be null.")));
		
		// Call the method under test that should throw a NullPointerException.
		IODataSampleRxIndicatorPacket.createPacket(payload);
	}
	
	/**
	 * Test method for {@link com.digi.xbee.api.packet.common.IODataSampleRxIndicatorPacket#createPacket(byte[])}.
	 * 
	 * <p>An {@code IllegalArgumentException} exception must be thrown when 
	 * parsing an empty byte array.</p>
	 */
	@Test
	public final void testCreatePacketEmptyPayload() {
		// Setup the resources for the test.
		byte[] payload = new byte[0];
		exception.expect(IllegalArgumentException.class);
		exception.expectMessage(is(equalTo("Incomplete IO Data Sample RX Indicator packet.")));
		
		// Call the method under test that should throw an IllegalArgumentException.
		IODataSampleRxIndicatorPacket.createPacket(payload);
	}
	
	/**
	 * Test method for {@link com.digi.xbee.api.packet.common.IODataSampleRxIndicatorPacket#createPacket(byte[])}.
	 * 
	 * <p>An {@code IllegalArgumentException} exception must be thrown when 
	 * parsing a byte array shorter than the needed one is provided.</p>
	 */
	@Test
	public final void testCreatePacketPayloadShorterThanNeeded() {
		// Setup the resources for the test.
		int frameType = APIFrameType.IO_DATA_SAMPLE_RX_INDICATOR.getValue();
		XBee64BitAddress source64Addr = new XBee64BitAddress("0013A2004032D9AB");
		XBee16BitAddress source16Addr = new XBee16BitAddress("D817");
		
		byte[] payload = new byte[11];
		payload[0] = (byte)frameType;
		System.arraycopy(source64Addr.getValue(), 0, payload, 1, source64Addr.getValue().length);
		System.arraycopy(source64Addr.getValue(), 0, payload, 9, source16Addr.getValue().length);
		
		exception.expect(IllegalArgumentException.class);
		exception.expectMessage(is(equalTo("Incomplete IO Data Sample RX Indicator packet.")));
		
		// Call the method under test that should throw an IllegalArgumentException.
		IODataSampleRxIndicatorPacket.createPacket(payload);
	}
	
	/**
	 * Test method for {@link com.digi.xbee.api.packet.common.IODataSampleRxIndicatorPacket#createPacket(byte[])}.
	 * 
	 * <p>An {@code IllegalArgumentException} exception must be thrown when 
	 * parsing a byte array shorter than the needed one is provided.</p>
	 */
	@Test
	public final void testCreatePacketReceivedDataShorterThanNeeded() {
		// Setup the resources for the test.
		int frameType = APIFrameType.IO_DATA_SAMPLE_RX_INDICATOR.getValue();
		XBee64BitAddress source64Addr = new XBee64BitAddress("0013A2004032D9AB");
		XBee16BitAddress source16Addr = new XBee16BitAddress("D817");
		int options = 40;
		byte[] data = new byte[]{(byte)0xFF, (byte)0xFF};
		
		byte[] payload = new byte[12 + data.length];
		payload[0] = (byte)frameType;
		System.arraycopy(source64Addr.getValue(), 0, payload, 1, source64Addr.getValue().length);
		System.arraycopy(source64Addr.getValue(), 0, payload, 9, source16Addr.getValue().length);
		payload[10] = (byte)options;
		System.arraycopy(data, 0, payload, 11, data.length);
		
		exception.expect(IllegalArgumentException.class);
		exception.expectMessage(is(equalTo("IO sample payload must be longer than 4.")));
		
		// Call the method under test that should throw an IllegalArgumentException.
		IODataSampleRxIndicatorPacket.createPacket(payload);
	}
	
	/**
	 * Test method for {@link com.digi.xbee.api.packet.common.IODataSampleRxIndicatorPacket#createPacket(byte[])}.
	 * 
	 * <p>An {@code IllegalArgumentException} exception must be thrown when 
	 * parsing a byte array not including the Frame type.</p>
	 */
	@Test
	public final void testCreatePacketPayloadNotIncludingFrameType() {
		// Setup the resources for the test.
		XBee64BitAddress source64Addr = new XBee64BitAddress("0013A2004032D9AB");
		XBee16BitAddress source16Addr = new XBee16BitAddress("D817");
		int options = 40;
		byte[] data = new byte[]{(byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF};
		
		byte[] payload = new byte[11 + data.length];
		System.arraycopy(source64Addr.getValue(), 0, payload, 0, source64Addr.getValue().length);
		System.arraycopy(source16Addr.getValue(), 0, payload, 8, source16Addr.getValue().length);
		payload[10] = (byte)options;
		System.arraycopy(data, 0, payload, 11, data.length);
		
		exception.expect(IllegalArgumentException.class);
		exception.expectMessage(is(equalTo("Payload is not a IO Data Sample RX Indicator packet.")));
		
		// Call the method under test that should throw an IllegalArgumentException.
		IODataSampleRxIndicatorPacket.createPacket(payload);
	}
	
	/**
	 * Test method for {@link com.digi.xbee.api.packet.common.IODataSampleRxIndicatorPacket#createPacket(byte[])}.
	 * 
	 * <p>A valid API IO Data Sample RX Indicator packet with the provided 
	 * options without RF data is created.</p>
	 */
	@Test
	public final void testCreatePacketValidPayloadWithoutData() {
		// Setup the resources for the test.
		int frameType = APIFrameType.IO_DATA_SAMPLE_RX_INDICATOR.getValue();
		XBee64BitAddress source64Addr = new XBee64BitAddress("0013A2004032D9AB");
		XBee16BitAddress source16Addr = new XBee16BitAddress("D817");
		int options = 40;
		
		byte[] payload = new byte[12];
		payload[0] = (byte)frameType;
		System.arraycopy(source64Addr.getValue(), 0, payload, 1, source64Addr.getValue().length);
		System.arraycopy(source16Addr.getValue(), 0, payload, 9, source16Addr.getValue().length);
		payload[11] = (byte)options;
		
		// Call the method under test.
		IODataSampleRxIndicatorPacket packet = IODataSampleRxIndicatorPacket.createPacket(payload);
		
		// Verify the result.
		assertThat("Returned length is not the expected one", packet.getPacketLength(), is(equalTo(payload.length)));
		assertThat("Returned source 64-bit address is not the expected one", packet.get64bitSourceAddress(), is(equalTo(source64Addr)));
		assertThat("Returned source 16-bit address is not the expected one", packet.get16bitSourceAddress(), is(equalTo(source16Addr)));
		assertThat("Returned received options is not the expected one", packet.getReceiveOptions(), is(equalTo(options)));
		assertThat("Returned Received Data is not the expected one", packet.getRFData(), is(nullValue()));
		
		assertThat("Returned payload array is not the expected one", packet.getPacketData(), is(equalTo(payload)));
	}
	
	/**
	 * Test method for {@link com.digi.xbee.api.packet.common.IODataSampleRxIndicatorPacket#createPacket(byte[])}.
	 * 
	 * <p>A valid API IO Data Sample RX Indicator packet with the provided 
	 * options and RF data is created.</p>
	 */
	@Test
	public final void testCreatePacketValidPayloadWithData() {
		// Setup the resources for the test.
		int frameType = APIFrameType.IO_DATA_SAMPLE_RX_INDICATOR.getValue();
		XBee64BitAddress source64Addr = new XBee64BitAddress("0013A2004032D9AB");
		XBee16BitAddress source16Addr = new XBee16BitAddress("D817");
		int options = 40;
		byte[] data = new byte[]{(byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF};
		
		byte[] payload = new byte[12 + data.length];
		payload[0] = (byte)frameType;
		System.arraycopy(source64Addr.getValue(), 0, payload, 1, source64Addr.getValue().length);
		System.arraycopy(source16Addr.getValue(), 0, payload, 9, source16Addr.getValue().length);
		payload[11] = (byte)options;
		System.arraycopy(data, 0, payload, 12, data.length);
		
		// Call the method under test.
		IODataSampleRxIndicatorPacket packet = IODataSampleRxIndicatorPacket.createPacket(payload);
		
		// Verify the result.
		assertThat("Returned length is not the expected one", packet.getPacketLength(), is(equalTo(payload.length)));
		assertThat("Returned source 64-bit address is not the expected one", packet.get64bitSourceAddress(), is(equalTo(source64Addr)));
		assertThat("Returned source 16-bit address is not the expected one", packet.get16bitSourceAddress(), is(equalTo(source16Addr)));
		assertThat("Returned received options is not the expected one", packet.getReceiveOptions(), is(equalTo(options)));
		assertThat("Returned Received Data is not the expected one", packet.getRFData(), is(equalTo(data)));
		
		assertThat("Returned payload array is not the expected one", packet.getPacketData(), is(equalTo(payload)));
	}
	
	/**
	 * Test method for {@link com.digi.xbee.api.packet.common.IODataSampleRxIndicatorPacket#IODataSampleRxIndicatorPacket(XBee64BitAddress, XBee16BitAddress, int, byte[])}.
	 * 
	 * <p>Construct a new IO Data Sample RX Indicator packet but with a 
	 * {@code null} 64-bit address. This must throw a 
	 * {@code NullPointerException}.</p>
	 */
	@Test
	public final void testCreateIODataSampleRxIndicatorPacket64BitAddressNull() {
		// Setup the resources for the test.
		XBee64BitAddress source64Addr = null;
		XBee16BitAddress source16Addr = new XBee16BitAddress("D817");
		int options = 40;
		byte[] data = new byte[]{(byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF};
		
		exception.expect(NullPointerException.class);
		exception.expectMessage(is(equalTo("64-bit source address cannot be null.")));
		
		// Call the method under test that should throw a NullPointerException.
		new IODataSampleRxIndicatorPacket(source64Addr, source16Addr, options, data);
	}
	
	/**
	 * Test method for {@link com.digi.xbee.api.packet.common.IODataSampleRxIndicatorPacket#IODataSampleRxIndicatorPacket(XBee64BitAddress, XBee16BitAddress, int, byte[])}.
	 * 
	 * <p>Construct a new IO Data Sample RX Indicator packet but with a 
	 * {@code null} 16-bit address. This must throw a 
	 * {@code NullPointerException}.</p>
	 */
	@Test
	public final void testCreateIODataSampleRxIndicatorPacket16BitAddressNull() {
		// Setup the resources for the test.
		XBee64BitAddress source64Addr = new XBee64BitAddress("0013A2004032D9AB");
		XBee16BitAddress source16Addr = null;
		int options = 40;
		byte[] data = new byte[]{(byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF};
		
		exception.expect(NullPointerException.class);
		exception.expectMessage(is(equalTo("16-bit source address cannot be null.")));
		
		// Call the method under test that should throw a NullPointerException.
		new IODataSampleRxIndicatorPacket(source64Addr, source16Addr, options, data);
	}
	
	/**
	 * Test method for {@link com.digi.xbee.api.packet.common.IODataSampleRxIndicatorPacket#IODataSampleRxIndicatorPacket(XBee64BitAddress, XBee16BitAddress, int, byte[])}.
	 * 
	 * <p>Construct a new IO Data Sample RX Indicator packet but with a 
	 * receive options bigger than 255. This must throw an 
	 * {@code IllegalArgumentException}.</p>
	 */
	@Test
	public final void testCreateIODataSampleRxIndicatorPacketReceiveOptionsBiggerThan255() {
		// Setup the resources for the test.
		XBee64BitAddress source64Addr = new XBee64BitAddress("0013A2004032D9AB");
		XBee16BitAddress source16Addr = new XBee16BitAddress("D817");
		int options = 500;
		byte[] data = new byte[]{(byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF};
		
		exception.expect(IllegalArgumentException.class);
		exception.expectMessage(is(equalTo("Receive options value must be between 0 and 255.")));
		
		// Call the method under test that should throw an IllegalArgumentException.
		new IODataSampleRxIndicatorPacket(source64Addr, source16Addr, options, data);
	}
	
	/**
	 * Test method for {@link com.digi.xbee.api.packet.common.IODataSampleRxIndicatorPacket#IODataSampleRxIndicatorPacket(XBee64BitAddress, XBee16BitAddress, int, byte[])}.
	 * 
	 * <p>Construct a new IO Data Sample RX Indicator packet but with a 
	 * negative receive options. This must throw an 
	 * {@code IllegalArgumentException}.</p>
	 */
	@Test
	public final void testCreateIODataSampleRxIndicatorPacketReceiveOptionsNegative() {
		// Setup the resources for the test.
		XBee64BitAddress source64Addr = new XBee64BitAddress("0013A2004032D9AB");
		XBee16BitAddress source16Addr = new XBee16BitAddress("D817");
		int options = -52;
		byte[] data = new byte[]{(byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF};
		
		exception.expect(IllegalArgumentException.class);
		exception.expectMessage(is(equalTo("Receive options value must be between 0 and 255.")));
		
		// Call the method under test that should throw an IllegalArgumentException.
		new IODataSampleRxIndicatorPacket(source64Addr, source16Addr, options, data);
	}
	
	/**
	 * Test method for {@link com.digi.xbee.api.packet.common.IODataSampleRxIndicatorPacket#IODataSampleRxIndicatorPacket(XBee64BitAddress, XBee16BitAddress, int, byte[])}.
	 * 
	 * <p>Construct a new IO Data Sample RX Indicator packet but with 
	 * {@code null} data.</p>
	 */
	@Test
	public final void testCreateIODataSampleRxIndicatorPacketDataNull() {
		// Setup the resources for the test.
		XBee64BitAddress source64Addr = new XBee64BitAddress("0013A2004032D9AB");
		XBee16BitAddress source16Addr = new XBee16BitAddress("D817");
		int options = 40;
		byte[] data = null;
		
		int expectedLength = 1 /* Frame type */ + 8 /* 64-bit address */ + 2 /* 16-bit address */ + 1 /* options */;
		
		// Call the method under test.
		IODataSampleRxIndicatorPacket packet = new IODataSampleRxIndicatorPacket(source64Addr, source16Addr, options, data);
		
		// Verify the result.
		assertThat("Returned length is not the expected one", packet.getPacketLength(), is(equalTo(expectedLength)));
		assertThat("Returned source 64-bit address is not the expected one", packet.get64bitSourceAddress(), is(equalTo(source64Addr)));
		assertThat("Returned source 16-bit address is not the expected one", packet.get16bitSourceAddress(), is(equalTo(source16Addr)));
		assertThat("Returned received options is not the expected one", packet.getReceiveOptions(), is(equalTo(options)));
		assertThat("Returned Received Data is not the expected one", packet.getRFData(), is(nullValue(byte[].class)));
		assertThat("Frame ID is not the expected one", packet.needsAPIFrameID(), is(equalTo(false)));
		assertThat("IO Sample must be null", packet.getIOSample(), is(nullValue(IOSample.class)));
	}
	
	/**
	 * Test method for {@link com.digi.xbee.api.packet.common.IODataSampleRxIndicatorPacket#IODataSampleRxIndicatorPacket(XBee64BitAddress, XBee16BitAddress, int, byte[])}.
	 * 
	 * <p>Construct a new IO Data Sample RX Indicator packet but with data 
	 * length less than 5. This must throw an 
	 * {@code IllegalArgumentException}.</p>
	 */
	@Test
	public final void testCreateIODataSampleRxIndicatorPacketDataLengthLessThan5() {
		// Setup the resources for the test.
		XBee64BitAddress source64Addr = new XBee64BitAddress("0013A2004032D9AB");
		XBee16BitAddress source16Addr = new XBee16BitAddress("D817");
		int options = 40;
		byte[] data = new byte[]{(byte)0xFF, (byte)0xFF, (byte)0xFF};
		
		exception.expect(IllegalArgumentException.class);
		exception.expectMessage(is(equalTo("IO sample payload must be longer than 4.")));
		
		// Call the method under test that should throw an IllegalArgumentException.
		new IODataSampleRxIndicatorPacket(source64Addr, source16Addr, options, data);
	}
	
	/**
	 * Test method for {@link com.digi.xbee.api.packet.common.IODataSampleRxIndicatorPacket#IODataSampleRxIndicatorPacket(XBee64BitAddress, XBee16BitAddress, int, byte[])}.
	 * 
	 * <p>Construct a new IO Data Sample RX Indicator packet.</p>
	 */
	@Test
	public final void testCreateIODataSampleRxIndicatorPacket() {
		// Setup the resources for the test.
		XBee64BitAddress source64Addr = new XBee64BitAddress("0013A2004032D9AB");
		XBee16BitAddress source16Addr = new XBee16BitAddress("D817");
		int options = 40;
		byte[] data = new byte[]{(byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF};
		
		int expectedLength = 1 /* Frame type */ + 8 /* 64-bit address */ + 2 /* 16-bit address */ + 1 /* options */ + data.length /* Data */;
		
		// Call the method under test.
		IODataSampleRxIndicatorPacket packet = new IODataSampleRxIndicatorPacket(source64Addr, source16Addr, options, data);
		
		// Verify the result.
		assertThat("Returned length is not the expected one", packet.getPacketLength(), is(equalTo(expectedLength)));
		assertThat("Returned source 64-bit address is not the expected one", packet.get64bitSourceAddress(), is(equalTo(source64Addr)));
		assertThat("Returned source 16-bit address is not the expected one", packet.get16bitSourceAddress(), is(equalTo(source16Addr)));
		assertThat("Returned received options is not the expected one", packet.getReceiveOptions(), is(equalTo(options)));
		assertThat("Returned Data is not the expected one", packet.getRFData(), is(equalTo(data)));
		assertThat("IO Data Sample RX Indicator packet does NOT need API Frame ID", packet.needsAPIFrameID(), is(equalTo(false)));
		assertThat("IO Sample must NOT be null", packet.getIOSample(), is(not(nullValue(IOSample.class))));
	}
	
	/**
	 * Test method for {@link com.digi.xbee.api.packet.common.IODataSampleRxIndicatorPacket#getAPIData()}.
	 * 
	 * <p>Test the get API parameters but with a {@code null} received data.</p>
	 */
	@Test
	public final void testGetAPIDataReceivedDataNull() {
		// Setup the resources for the test.
		XBee64BitAddress source64Addr = new XBee64BitAddress("0013A2004032D9AB");
		XBee16BitAddress source16Addr = new XBee16BitAddress("D817");
		int options = 40;
		byte[] receivedData = null;
		IODataSampleRxIndicatorPacket packet = new IODataSampleRxIndicatorPacket(source64Addr, source16Addr, options, receivedData);
		
		int expectedLength = 8 /* 64-bit address */ + 2 /* 16-bit address */ + 1 /* options */;
		byte[] expectedData = new byte[expectedLength];
		System.arraycopy(source64Addr.getValue(), 0, expectedData, 0, source64Addr.getValue().length);
		System.arraycopy(source16Addr.getValue(), 0, expectedData, 8, source16Addr.getValue().length);
		expectedData[10] = (byte)options;
		
		// Call the method under test.
		byte[] data = packet.getAPIData();
		
		// Verify the result.
		assertThat("API data is not the expected", data, is(equalTo(expectedData)));
	}
	
	/**
	 * Test method for {@link com.digi.xbee.api.packet.common.IODataSampleRxIndicatorPacket#getAPIData()}.
	 * 
	 * <p>Test the get API parameters but with a non-{@code null} received data.</p>
	 */
	@Test
	public final void testGetAPIDataReceivedDataNonNull() {
		// Setup the resources for the test.
		XBee64BitAddress source64Addr = new XBee64BitAddress("0013A2004032D9AB");
		XBee16BitAddress source16Addr = new XBee16BitAddress("D817");
		int options = 40;
		byte[] receivedData = new byte[]{(byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF};
		IODataSampleRxIndicatorPacket packet = new IODataSampleRxIndicatorPacket(source64Addr, source16Addr, options, receivedData);
		
		int expectedLength = 8 /* 64-bit address */ + 2 /* 16-bit address */ + 1 /* options */ + receivedData.length /* Data */;
		byte[] expectedData = new byte[expectedLength];
		System.arraycopy(source64Addr.getValue(), 0, expectedData, 0, source64Addr.getValue().length);
		System.arraycopy(source16Addr.getValue(), 0, expectedData, 8, source16Addr.getValue().length);
		expectedData[10] = (byte)options;
		System.arraycopy(receivedData, 0, expectedData, 11, receivedData.length);
		
		// Call the method under test.
		byte[] data = packet.getAPIData();
		
		// Verify the result.
		assertThat("API data is not the expected", data, is(equalTo(expectedData)));
	}
	
	/**
	 * Test method for {@link com.digi.xbee.api.packet.common.IODataSampleRxIndicatorPacket#getAPIPacketParameters()}.
	 * 
	 * <p>Test the get API parameters but with a {@code null} received data.</p>
	 */
	@Test
	public final void testGetAPIPacketParametersReceivedDataNull() {
		// Setup the resources for the test.
		XBee64BitAddress source64Addr = new XBee64BitAddress("0013A2004032D9AB");
		XBee16BitAddress source16Addr = new XBee16BitAddress("D817");
		int options = 40;
		byte[] receivedData = null;
		IODataSampleRxIndicatorPacket packet = new IODataSampleRxIndicatorPacket(source64Addr, source16Addr, options, receivedData);
		
		String expectedSource64Addr = HexUtils.prettyHexString(source64Addr.getValue());
		String expectedSource16Addr = HexUtils.prettyHexString(source16Addr.getValue());
		String expectedOptions = HexUtils.prettyHexString(Integer.toHexString(options));
		
		// Call the method under test.
		LinkedHashMap<String, String> packetParams = packet.getAPIPacketParameters();
		
		// Verify the result.
		assertThat("Packet parameters map size is not the expected one", packetParams.size(), is(equalTo(3)));
		assertThat("Source 64-bit Address is not the expected one", packetParams.get("64-bit source address"), is(equalTo(expectedSource64Addr)));
		assertThat("Source 16-bit Address is not the expected one", packetParams.get("16-bit source address"), is(equalTo(expectedSource16Addr)));
		assertThat("Receive options are not the expected", packetParams.get("Receive options"), is(equalTo(expectedOptions)));
		assertThat("Number of samples is not the expected", packetParams.get("Number of samples"), is(nullValue(String.class)));
		assertThat("Digital channel mask is not the expected", packetParams.get("Digital channel mask"), is(nullValue(String.class)));
		assertThat("Analog channel mask is not the expected", packetParams.get("Analog channel mask"), is(nullValue(String.class)));
	}
	
	/**
	 * Test method for {@link com.digi.xbee.api.packet.common.IODataSampleRxIndicatorPacket#getAPIPacketParameters()}.
	 * 
	 * <p>Test the get API parameters but with a non-{@code null} received data.</p>
	 * 
	 * @throws OperationNotSupportedException 
	 */
	@Test
	public final void testGetAPIPacketParametersReceivedDataNotNull() throws OperationNotSupportedException {
		// Setup the resources for the test.
		XBee64BitAddress source64Addr = new XBee64BitAddress("0013A2004032D9AB");
		XBee16BitAddress source16Addr = new XBee16BitAddress("D817");
		int options = 40;
		byte[] receivedData = new byte[]{(byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF};
		IODataSampleRxIndicatorPacket packet = new IODataSampleRxIndicatorPacket(source64Addr, source16Addr, options, receivedData);
		
		String expectedSource64Addr = HexUtils.prettyHexString(source64Addr.getValue());
		String expectedSource16Addr = HexUtils.prettyHexString(source16Addr.getValue());
		String expectedOptions = HexUtils.prettyHexString(Integer.toHexString(options));
		IOSample expectedIoSample = new IOSample(receivedData);
		String expectedDigitalMask = HexUtils.prettyHexString(HexUtils.integerToHexString(expectedIoSample.getDigitalMask(), 2));
		String expectedAnalogMask = HexUtils.prettyHexString(HexUtils.integerToHexString(expectedIoSample.getAnalogMask(), 1));
		
		// Call the method under test.
		LinkedHashMap<String, String> packetParams = packet.getAPIPacketParameters();
		
		// Verify the result.
		assertThat("Packet parameters map size is not the expected one", packetParams.size(), is(equalTo(22)));
		assertThat("Source 64-bit Address is not the expected one", packetParams.get("64-bit source address"), is(equalTo(expectedSource64Addr)));
		assertThat("Source 16-bit Address is not the expected one", packetParams.get("16-bit source address"), is(equalTo(expectedSource16Addr)));
		assertThat("Receive options are not the expected", packetParams.get("Receive options"), is(equalTo(expectedOptions)));
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
		
		assertThat(packetParams.get("Power supply value"), 
				is(equalTo(HexUtils.prettyHexString(HexUtils.integerToHexString(expectedIoSample.getPowerSupplyValue(), 2)))));
	}
	
	/**
	 * Test method for {@link com.digi.xbee.api.packet.common.IODataSampleRxIndicatorPacket#isBroadcast()}.
	 * 
	 * <p>Test if an API IO Data Sample RX Indicator packet is a broadcast 
	 * packet when broadcast options is not enabled.</p>
	 */
	@Test
	public final void testIsBroadcastWithNonBroadcastOption() {
		// Setup the resources for the test.
		XBee64BitAddress source64Addr = new XBee64BitAddress("0013A2004032D9AB");
		XBee16BitAddress source16Addr = new XBee16BitAddress("D817");
		int options = 0x0D; /* bit 1 */
		byte[] receivedData = new byte[]{(byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF};
		IODataSampleRxIndicatorPacket packet = new IODataSampleRxIndicatorPacket(source64Addr, source16Addr, options, receivedData);
		
		// Call the method under test and verify the result.
		assertThat("Packet should not be broadcast", packet.isBroadcast(), is(equalTo(false)));
	}
	
	/**
	 * Test method for {@link com.digi.xbee.api.packet.common.IODataSampleRxIndicatorPacket#isBroadcast()}.
	 * 
	 * <p>Test if an API IO Data Sample RX Indicator packet is a broadcast 
	 * packet when broadcast is enabled in the options.</p>
	 */
	@Test
	public final void testIsBroadcastWithBroadcastOptionEnabled() {
		// Setup the resources for the test.
		XBee64BitAddress source64Addr = new XBee64BitAddress("0013A2004032D9AB");
		XBee16BitAddress source16Addr = new XBee16BitAddress("D817");
		int options = 0x46; /* bit 1 */
		byte[] receivedData = new byte[]{(byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF};
		IODataSampleRxIndicatorPacket packet = new IODataSampleRxIndicatorPacket(source64Addr, source16Addr, options, receivedData);
		
		// Call the method under test and verify the result.
		assertThat("Packet should be broadcast", packet.isBroadcast(), is(equalTo(true)));
	}
}
