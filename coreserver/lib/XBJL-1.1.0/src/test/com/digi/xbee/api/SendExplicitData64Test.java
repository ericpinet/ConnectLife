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
package com.digi.xbee.api;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.digi.xbee.api.connection.serial.SerialPortRxTx;
import com.digi.xbee.api.exceptions.InterfaceNotOpenException;
import com.digi.xbee.api.exceptions.InvalidOperatingModeException;
import com.digi.xbee.api.exceptions.OperationNotSupportedException;
import com.digi.xbee.api.exceptions.TimeoutException;
import com.digi.xbee.api.exceptions.TransmitException;
import com.digi.xbee.api.exceptions.XBeeException;
import com.digi.xbee.api.models.XBee64BitAddress;
import com.digi.xbee.api.packet.common.ExplicitAddressingPacket;

@RunWith(PowerMockRunner.class)
@PrepareForTest({XBeeDevice.class})
public class SendExplicitData64Test {
	
	// Constants.
	private static final XBee64BitAddress XBEE_64BIT_ADDRESS = new XBee64BitAddress("0123456789ABCDEF");
	
	private static final int SOURCE_ENDPOINT = 0xA0;
	private static final int DESTINATION_ENDPOINT = 0xA1;
	private static final int CLUSTER_ID = 0x1554;
	private static final int PROFILE_ID = 0xC105;
	
	private static final String DATA = "data";
	
	// Variables.
	private XBeeDevice xbeeDevice;
	
	private ExplicitAddressingPacket explicitAddressingPacket;
	
	@Before
	public void setup() throws Exception {
		// Instantiate an XBeeDevice object with a mocked interface.
		xbeeDevice = PowerMockito.spy(new XBeeDevice(Mockito.mock(SerialPortRxTx.class)));
		
		// Mock Explicit Addressing packet.
		explicitAddressingPacket = Mockito.mock(ExplicitAddressingPacket.class);
		
		// Whenever an ExplicitAddressingPacket class is instantiated, the mocked transmitPacket packet should be returned.
		PowerMockito.whenNew(ExplicitAddressingPacket.class).withAnyArguments().thenReturn(explicitAddressingPacket);
	}
	
	/**
	 * Test method for {@link com.digi.xbee.api.XBeeDevice#sendExplicitData(XBee64BitAddress, int, int, int, int, byte[])}.
	 * 
	 * <p>Verify that explicit data cannot be sent if the 64-Bit address is {@code null}.</p>
	 * 
	 * @throws XBeeException 
	 * @throws TimeoutException 
	 */
	@Test(expected=NullPointerException.class)
	public void testSendExplicitData64BitAddrNull() throws TimeoutException, XBeeException {
		xbeeDevice.sendExplicitData((XBee64BitAddress)null, SOURCE_ENDPOINT, DESTINATION_ENDPOINT, CLUSTER_ID, PROFILE_ID, DATA.getBytes());
	}
	
	/**
	 * Test method for {@link com.digi.xbee.api.XBeeDevice#sendExplicitData(XBee64BitAddress, int, int, int, int, byte[])}.
	 * 
	 * <p>Verify that explicit data cannot be sent if the source endpoint is negative.</p>
	 * 
	 * @throws XBeeException 
	 * @throws TimeoutException 
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testSendExplicitDataSourceEndpointNegative() throws TimeoutException, XBeeException {
		xbeeDevice.sendExplicitData(XBEE_64BIT_ADDRESS, -44, DESTINATION_ENDPOINT, CLUSTER_ID, PROFILE_ID, DATA.getBytes());
	}
	
	/**
	 * Test method for {@link com.digi.xbee.api.XBeeDevice#sendExplicitData(XBee64BitAddress, int, int, int, int, byte[])}.
	 * 
	 * <p>Verify that explicit data cannot be sent if the source endpoint is greater than 255.</p>
	 * 
	 * @throws XBeeException 
	 * @throws TimeoutException 
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testSendExplicitDataSourceEndpointGreater() throws TimeoutException, XBeeException {
		xbeeDevice.sendExplicitData(XBEE_64BIT_ADDRESS, 256, DESTINATION_ENDPOINT, CLUSTER_ID, PROFILE_ID, DATA.getBytes());
	}
	
	/**
	 * Test method for {@link com.digi.xbee.api.XBeeDevice#sendExplicitData(XBee64BitAddress, int, int, int, int, byte[])}.
	 * 
	 * <p>Verify that explicit data cannot be sent if the source endpoint is negative.</p>
	 * 
	 * @throws XBeeException 
	 * @throws TimeoutException 
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testSendExplicitDataDestinationEndpointNegative() throws TimeoutException, XBeeException {
		xbeeDevice.sendExplicitData(XBEE_64BIT_ADDRESS, SOURCE_ENDPOINT, -59, CLUSTER_ID, PROFILE_ID, DATA.getBytes());
	}
	
	/**
	 * Test method for {@link com.digi.xbee.api.XBeeDevice#sendExplicitData(XBee64BitAddress, int, int, int, int, byte[])}.
	 * 
	 * <p>Verify that explicit data cannot be sent if the source endpoint is greater than 255.</p>
	 * 
	 * @throws XBeeException 
	 * @throws TimeoutException 
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testSendExplicitDataDestinationEndpointGreater() throws TimeoutException, XBeeException {
		xbeeDevice.sendExplicitData(XBEE_64BIT_ADDRESS, SOURCE_ENDPOINT, 256, CLUSTER_ID, PROFILE_ID, DATA.getBytes());
	}
	
	/**
	 * Test method for {@link com.digi.xbee.api.XBeeDevice#sendExplicitData(XBee64BitAddress, int, int, int, int, byte[])}.
	 * 
	 * <p>Verify that explicit data cannot be sent if the cluster ID is negative.</p>
	 * 
	 * @throws XBeeException 
	 * @throws TimeoutException 
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testSendExplicitDataClusterIDNegative() throws TimeoutException, XBeeException {
		xbeeDevice.sendExplicitData(XBEE_64BIT_ADDRESS, SOURCE_ENDPOINT, DESTINATION_ENDPOINT, -20, PROFILE_ID, DATA.getBytes());
	}
	
	/**
	 * Test method for {@link com.digi.xbee.api.XBeeDevice#sendExplicitData(XBee64BitAddress, int, int, int, int, byte[])}.
	 * 
	 * <p>Verify that explicit data cannot be sent if the cluster ID is greater than 65535.</p>
	 * 
	 * @throws XBeeException 
	 * @throws TimeoutException 
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testSendExplicitDataClusterIDGreater() throws TimeoutException, XBeeException {
		xbeeDevice.sendExplicitData(XBEE_64BIT_ADDRESS, SOURCE_ENDPOINT, DESTINATION_ENDPOINT, 65536, PROFILE_ID, DATA.getBytes());
	}
	
	/**
	 * Test method for {@link com.digi.xbee.api.XBeeDevice#sendExplicitData(XBee64BitAddress, int, int, int, int, byte[])}.
	 * 
	 * <p>Verify that explicit data cannot be sent if the profile ID is negative.</p>
	 * 
	 * @throws XBeeException 
	 * @throws TimeoutException 
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testSendExplicitDataProfileIDNegative() throws TimeoutException, XBeeException {
		xbeeDevice.sendExplicitData(XBEE_64BIT_ADDRESS, SOURCE_ENDPOINT, DESTINATION_ENDPOINT, CLUSTER_ID, -15, DATA.getBytes());
	}
	
	/**
	 * Test method for {@link com.digi.xbee.api.XBeeDevice#sendExplicitData(XBee64BitAddress, int, int, int, int, byte[])}.
	 * 
	 * <p>Verify that explicit data cannot be sent if the cluster ID is greater than 65535.</p>
	 * 
	 * @throws XBeeException 
	 * @throws TimeoutException 
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testSendExplicitDataProfileIDGreater() throws TimeoutException, XBeeException {
		xbeeDevice.sendExplicitData(XBEE_64BIT_ADDRESS, SOURCE_ENDPOINT, DESTINATION_ENDPOINT, CLUSTER_ID, 65536, DATA.getBytes());
	}
	
	/**
	 * Test method for {@link com.digi.xbee.api.XBeeDevice#sendExplicitData(XBee64BitAddress, int, int, int, int, byte[])}.
	 * 
	 * <p>Verify that explicit data cannot be sent if the data is {@code null}.</p>
	 * 
	 * @throws XBeeException 
	 * @throws TimeoutException 
	 */
	@Test(expected=NullPointerException.class)
	public void testSendExplicitDataDataNull() throws TimeoutException, XBeeException {
		xbeeDevice.sendExplicitData(XBEE_64BIT_ADDRESS, SOURCE_ENDPOINT, DESTINATION_ENDPOINT, CLUSTER_ID, PROFILE_ID, null);
	}
	
	/**
	 * Test method for {@link com.digi.xbee.api.XBeeDevice#sendExplicitData(XBee64BitAddress, int, int, int, int, byte[])}.
	 * 
	 * <p>Verify that explicit data cannot be sent if the device is not open.</p>
	 * 
	 * @throws XBeeException 
	 * @throws TimeoutException 
	 */
	@Test(expected=InterfaceNotOpenException.class)
	public void testSendExplicitDataConnectionClosed() throws TimeoutException, XBeeException {
		// Throw an Interface not open exception when sending and checking any Explicit Addressing packet.
		Mockito.doThrow(new InterfaceNotOpenException()).when(xbeeDevice).sendAndCheckXBeePacket(Mockito.any(ExplicitAddressingPacket.class), Mockito.eq(false));
		
		xbeeDevice.sendExplicitData(XBEE_64BIT_ADDRESS, SOURCE_ENDPOINT, DESTINATION_ENDPOINT, CLUSTER_ID, PROFILE_ID, DATA.getBytes());
	}
	
	/**
	 * Test method for {@link com.digi.xbee.api.XBeeDevice#sendExplicitData(XBee64BitAddress, int, int, int, int, byte[])}.
	 * 
	 * <p>Verify that explicit data cannot be sent if the device has an invalid operating mode.</p>
	 * 
	 * @throws XBeeException 
	 * @throws TimeoutException 
	 */
	@Test(expected=InvalidOperatingModeException.class)
	public void testSendExplicitDataInvalidOperatingMode() throws TimeoutException, XBeeException {
		// Throw an invalid operating mode exception when sending and checking any Explicit Addressing packet.
		Mockito.doThrow(new InvalidOperatingModeException()).when(xbeeDevice).sendAndCheckXBeePacket(Mockito.any(ExplicitAddressingPacket.class), Mockito.eq(false));
		
		xbeeDevice.sendExplicitData(XBEE_64BIT_ADDRESS, SOURCE_ENDPOINT, DESTINATION_ENDPOINT, CLUSTER_ID, PROFILE_ID, DATA.getBytes());
	}
	
	/**
	 * Test method for {@link com.digi.xbee.api.XBeeDevice#sendExplicitData(XBee64BitAddress, int, int, int, int, byte[])}.
	 * 
	 * <p>Verify that explicit data cannot be sent if there is a timeout sending and checking the Explicit Addressing packet.</p>
	 * 
	 * @throws XBeeException 
	 * @throws TimeoutException 
	 */
	@Test(expected=TimeoutException.class)
	public void testSendExplicitDataTimeout() throws TimeoutException, XBeeException {
		// Throw a timeout exception when sending and checking any Explicit Addressing packet.
		Mockito.doThrow(new TimeoutException()).when(xbeeDevice).sendAndCheckXBeePacket(Mockito.any(ExplicitAddressingPacket.class), Mockito.eq(false));
		
		xbeeDevice.sendExplicitData(XBEE_64BIT_ADDRESS, SOURCE_ENDPOINT, DESTINATION_ENDPOINT, CLUSTER_ID, PROFILE_ID, DATA.getBytes());
	}
	
	/**
	 * Test method for {@link com.digi.xbee.api.XBeeDevice#sendExplicitData(XBee64BitAddress, int, int, int, int, byte[])}.
	 * 
	 * <p>Verify that explicit data cannot be sent if there is a transmit exception when sending and checking the Explicit 
	 * Addressing packet.</p>
	 * 
	 * @throws XBeeException 
	 * @throws TimeoutException 
	 */
	@Test(expected=TransmitException.class)
	public void testSendExplicitDataTransmitException() throws TimeoutException, XBeeException {
		// Throw a transmit exception when sending and checking any Explicit Addressing packet.
		Mockito.doThrow(new TransmitException(null)).when(xbeeDevice).sendAndCheckXBeePacket(Mockito.any(ExplicitAddressingPacket.class), Mockito.eq(false));
		
		xbeeDevice.sendExplicitData(XBEE_64BIT_ADDRESS, SOURCE_ENDPOINT, DESTINATION_ENDPOINT, CLUSTER_ID, PROFILE_ID, DATA.getBytes());
	}
	
	/**
	 * Test method for {@link com.digi.xbee.api.XBeeDevice#sendExplicitData(XBee64BitAddress, int, int, int, int, byte[])}.
	 * 
	 * <p>Verify that explicit data cannot be sent if there is an IO error when sending and checking the Explicit 
	 * Addressing packet.</p>
	 * 
	 * @throws XBeeException 
	 * @throws TimeoutException 
	 */
	@Test(expected=XBeeException.class)
	public void testSendExplicitDataIOException() throws TimeoutException, XBeeException {
		// Throw an XBee exception when sending and checking any Explicit Addressing packet.
		Mockito.doThrow(new XBeeException()).when(xbeeDevice).sendAndCheckXBeePacket(Mockito.any(ExplicitAddressingPacket.class), Mockito.eq(false));
		
		xbeeDevice.sendExplicitData(XBEE_64BIT_ADDRESS, SOURCE_ENDPOINT, DESTINATION_ENDPOINT, CLUSTER_ID, PROFILE_ID, DATA.getBytes());
	}
	
	/**
	 * Test method for {@link com.digi.xbee.api.XBeeDevice#sendExplicitData(XBee64BitAddress, int, int, int, int, byte[])}.
	 * 
	 * <p>Verify that explicit data cannot be sent if the sender is a remote XBee device.</p>
	 * 
	 * @throws XBeeException 
	 * @throws TimeoutException 
	 */
	@Test(expected=OperationNotSupportedException.class)
	public void testSendExplicitDataFromRemoteDevices() throws TimeoutException, XBeeException {
		// Return that the XBee device is remote when asked.
		Mockito.when(xbeeDevice.isRemote()).thenReturn(true);
		
		xbeeDevice.sendExplicitData(XBEE_64BIT_ADDRESS, SOURCE_ENDPOINT, DESTINATION_ENDPOINT, CLUSTER_ID, PROFILE_ID, DATA.getBytes());
	}
	
	/**
	 * Test method for {@link com.digi.xbee.api.XBeeDevice#sendExplicitData(XBee64BitAddress, int, int, int, int, byte[])}.
	 * 
	 * <p>Verify that explicit data is sent successfully if there is not any error when sending and checking the Explicit 
	 * Addressing packet.</p>
	 * 
	 * @throws XBeeException 
	 * @throws TimeoutException 
	 */
	@Test
	public void testSendExplicitDataSuccess() throws TimeoutException, XBeeException {
		// Do nothing when sending and checking any Explicit Addressing packet.
		Mockito.doNothing().when(xbeeDevice).sendAndCheckXBeePacket(Mockito.any(ExplicitAddressingPacket.class), Mockito.eq(false));
		
		xbeeDevice.sendExplicitData(XBEE_64BIT_ADDRESS, SOURCE_ENDPOINT, DESTINATION_ENDPOINT, CLUSTER_ID, PROFILE_ID, DATA.getBytes());
		
		// Verify the sendAndCheckXBeePacket(XBeePacket, boolean) method was called.
		Mockito.verify(xbeeDevice, Mockito.times(1)).sendAndCheckXBeePacket(Mockito.eq(explicitAddressingPacket), Mockito.eq(false));
	}
}
