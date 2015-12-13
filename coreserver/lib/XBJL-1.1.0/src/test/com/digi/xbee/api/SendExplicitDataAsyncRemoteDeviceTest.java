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
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;

import com.digi.xbee.api.connection.serial.SerialPortRxTx;
import com.digi.xbee.api.exceptions.OperationNotSupportedException;
import com.digi.xbee.api.exceptions.TimeoutException;
import com.digi.xbee.api.exceptions.XBeeException;
import com.digi.xbee.api.models.XBee16BitAddress;
import com.digi.xbee.api.models.XBee64BitAddress;
import com.digi.xbee.api.models.XBeeProtocol;

public class SendExplicitDataAsyncRemoteDeviceTest {
	
	// Constants.
	private static final XBee16BitAddress XBEE_16BIT_ADDRESS = new XBee16BitAddress("0123");
	private static final XBee64BitAddress XBEE_64BIT_ADDRESS = new XBee64BitAddress("0123456789ABCDEF");
	
	private static final int SOURCE_ENDPOINT = 0xA0;
	private static final int DESTINATION_ENDPOINT = 0xA1;
	private static final int CLUSTER_ID = 0x1554;
	private static final int PROFILE_ID = 0xC105;
	
	private static final String DATA = "data";
	
	// Variables.
	private XBeeDevice xbeeDevice;
	private RemoteXBeeDevice mockedRemoteDevice;
	
	@Before
	public void setup() throws Exception {
		// Mock an RxTx IConnectionInterface.
		SerialPortRxTx mockedPort = Mockito.mock(SerialPortRxTx.class);
		
		// Instantiate an XBeeDevice object with the mocked interface.
		xbeeDevice = PowerMockito.spy(new XBeeDevice(mockedPort));
		
		// Mock a RemoteXBeeDevice to be used as parameter in the send data command.
		mockedRemoteDevice = Mockito.mock(RemoteXBeeDevice.class);
		Mockito.when(mockedRemoteDevice.get64BitAddress()).thenReturn(XBEE_64BIT_ADDRESS);
		Mockito.when(mockedRemoteDevice.get16BitAddress()).thenReturn(XBEE_16BIT_ADDRESS);
	}
	
	/**
	 * Test method for {@link com.digi.xbee.api.XBeeDevice#sendExplicitDataAsync(RemoteXBeeDevice, int, int, int, int, byte[])}.
	 * 
	 * <p>Verify that async. explicit data cannot be sent if the remote XBee device is {@code null}.</p>
	 * 
	 * @throws XBeeException 
	 * @throws TimeoutException 
	 */
	@Test(expected=NullPointerException.class)
	public void testSendExplicitDataRemoteDeviceNull() throws TimeoutException, XBeeException {
		xbeeDevice.sendExplicitDataAsync((RemoteXBeeDevice)null, SOURCE_ENDPOINT, DESTINATION_ENDPOINT, CLUSTER_ID, PROFILE_ID, DATA.getBytes());
	}
	
	/**
	 * Test method for {@link com.digi.xbee.api.XBeeDevice#sendExplicitDataAsync(RemoteXBeeDevice, int, int, int, int, byte[])}.
	 * 
	 * <p>Verify that async. explicit data can be sent if the protocol of the XBee device is ZigBee and the remote 
	 * device has the 64-bit and 16-bit addresses correctly configured.</p>
	 * 
	 * @throws XBeeException 
	 * @throws TimeoutException 
	 */
	@Test
	public void testSendExplicitDataZigBee6416() throws TimeoutException, XBeeException {
		// Setup the protocol of the XBee device to be ZigBee.
		Mockito.when(xbeeDevice.getXBeeProtocol()).thenReturn(XBeeProtocol.ZIGBEE);
		// Do nothing when the sendExplicitDataAsync(XBee64BitAddress, XBee16BitAddress, int, int, int, int, byte[]) method is called.
		Mockito.doNothing().when(xbeeDevice).sendExplicitDataAsync(Mockito.any(XBee64BitAddress.class), Mockito.any(XBee16BitAddress.class), Mockito.anyInt(), Mockito.anyInt(), 
				Mockito.anyInt(), Mockito.anyInt(), Mockito.any(byte[].class));
		
		xbeeDevice.sendExplicitDataAsync(mockedRemoteDevice, SOURCE_ENDPOINT, DESTINATION_ENDPOINT, CLUSTER_ID, PROFILE_ID, DATA.getBytes());
		
		// Verify the sendExplicitDataAsync(XBee64BitAddress, XBee16BitAddress, int, int, int, int, byte[]) method was called.
		Mockito.verify(xbeeDevice, Mockito.times(1)).sendExplicitDataAsync(Mockito.eq(XBEE_64BIT_ADDRESS), Mockito.eq(XBEE_16BIT_ADDRESS), Mockito.eq(SOURCE_ENDPOINT), 
				Mockito.eq(DESTINATION_ENDPOINT), Mockito.eq(CLUSTER_ID), Mockito.eq(PROFILE_ID), Mockito.eq(DATA.getBytes()));
	}
	
	/**
	 * Test method for {@link com.digi.xbee.api.XBeeDevice#sendExplicitDataAsync(RemoteXBeeDevice, int, int, int, int, byte[])}.
	 * 
	 * <p>Verify that async. explicit data can be sent if the protocol of the XBee device is ZigBee and the remote 
	 * device only has the 64-bit address correctly configured.</p>
	 * 
	 * @throws XBeeException 
	 * @throws TimeoutException 
	 */
	@Test
	public void testSendExplicitDataZigBee64() throws TimeoutException, XBeeException {
		// Setup the protocol of the XBee device to be ZigBee.
		Mockito.when(xbeeDevice.getXBeeProtocol()).thenReturn(XBeeProtocol.ZIGBEE);
		// Do nothing when the sendExplicitDataAsync(XBee64BitAddress, int, int, int, int, byte[]) method is called.
		Mockito.doNothing().when(xbeeDevice).sendExplicitDataAsync(Mockito.any(XBee64BitAddress.class), Mockito.anyInt(), Mockito.anyInt(), 
				Mockito.anyInt(), Mockito.anyInt(), Mockito.any(byte[].class));
		// Return a null 16-bit address in the remote XBee device when asked.
		Mockito.doReturn(null).when(mockedRemoteDevice).get16BitAddress();
		
		xbeeDevice.sendExplicitDataAsync(mockedRemoteDevice, SOURCE_ENDPOINT, DESTINATION_ENDPOINT, CLUSTER_ID, PROFILE_ID, DATA.getBytes());
		
		// Verify the sendExplicitDataAsync(XBee64BitAddress, int, int, int, int, byte[]) method was called.
		Mockito.verify(xbeeDevice, Mockito.times(1)).sendExplicitDataAsync(Mockito.eq(XBEE_64BIT_ADDRESS), Mockito.eq(SOURCE_ENDPOINT), Mockito.eq(DESTINATION_ENDPOINT), 
				Mockito.eq(CLUSTER_ID), Mockito.eq(PROFILE_ID), Mockito.eq(DATA.getBytes()));
	}
	
	/**
	 * Test method for {@link com.digi.xbee.api.XBeeDevice#sendExplicitDataAsync(RemoteXBeeDevice, int, int, int, int, byte[])}.
	 * 
	 * <p>Verify that async. explicit data can be sent if the protocol of the XBee device is Point-to-Multipoint and 
	 * the remote device has the 64-bit and 16-bit addresses correctly configured.</p>
	 * 
	 * @throws XBeeException 
	 * @throws TimeoutException 
	 */
	@Test
	public void testSendExplicitDataDigiPoint6416() throws TimeoutException, XBeeException {
		// Setup the protocol of the XBee device to be Point-to-Multipoint.
		Mockito.when(xbeeDevice.getXBeeProtocol()).thenReturn(XBeeProtocol.DIGI_POINT);
		// Do nothing when the sendExplicitDataAsync(XBee64BitAddress, XBee16BitAddress, int, int, int, int, byte[]) method is called.
		Mockito.doNothing().when(xbeeDevice).sendExplicitDataAsync(Mockito.any(XBee64BitAddress.class), Mockito.any(XBee16BitAddress.class), Mockito.anyInt(), Mockito.anyInt(), 
				Mockito.anyInt(), Mockito.anyInt(), Mockito.any(byte[].class));
		
		xbeeDevice.sendExplicitDataAsync(mockedRemoteDevice, SOURCE_ENDPOINT, DESTINATION_ENDPOINT, CLUSTER_ID, PROFILE_ID, DATA.getBytes());
		
		// Verify the sendExplicitDataAsync(XBee64BitAddress, XBee16BitAddress, int, int, int, int, byte[]) method was called.
		Mockito.verify(xbeeDevice, Mockito.times(1)).sendExplicitDataAsync(Mockito.eq(XBEE_64BIT_ADDRESS), Mockito.eq(XBEE_16BIT_ADDRESS), Mockito.eq(SOURCE_ENDPOINT), 
				Mockito.eq(DESTINATION_ENDPOINT), Mockito.eq(CLUSTER_ID), Mockito.eq(PROFILE_ID), Mockito.eq(DATA.getBytes()));
	}
	
	/**
	 * Test method for {@link com.digi.xbee.api.XBeeDevice#sendExplicitDataAsync(RemoteXBeeDevice, int, int, int, int, byte[])}.
	 * 
	 * <p>Verify that async. explicit data can be sent if the protocol of the XBee device is Point-to-Multipoint and 
	 * the remote device only has the 64-bit address correctly configured.</p>
	 * 
	 * @throws XBeeException 
	 * @throws TimeoutException 
	 */
	@Test
	public void testSendExplicitDataDigiPoint64() throws TimeoutException, XBeeException {
		// Setup the protocol of the XBee device to be Point-to-Multipoint.
		Mockito.when(xbeeDevice.getXBeeProtocol()).thenReturn(XBeeProtocol.DIGI_POINT);
		// Do nothing when the sendExplicitDataAsync(XBee64BitAddress, int, int, int, int, byte[]) method is called.
		Mockito.doNothing().when(xbeeDevice).sendExplicitDataAsync(Mockito.any(XBee64BitAddress.class), Mockito.anyInt(), Mockito.anyInt(), 
				Mockito.anyInt(), Mockito.anyInt(), Mockito.any(byte[].class));
		// Return a null 16-bit address in the remote XBee device when asked.
		Mockito.doReturn(null).when(mockedRemoteDevice).get16BitAddress();
		
		xbeeDevice.sendExplicitDataAsync(mockedRemoteDevice, SOURCE_ENDPOINT, DESTINATION_ENDPOINT, CLUSTER_ID, PROFILE_ID, DATA.getBytes());
		
		// Verify the sendExplicitDataAsync(XBee64BitAddress, int, int, int, int, byte[]) method was called.
		Mockito.verify(xbeeDevice, Mockito.times(1)).sendExplicitDataAsync(Mockito.eq(XBEE_64BIT_ADDRESS), Mockito.eq(SOURCE_ENDPOINT), Mockito.eq(DESTINATION_ENDPOINT), 
				Mockito.eq(CLUSTER_ID), Mockito.eq(PROFILE_ID), Mockito.eq(DATA.getBytes()));
	}
	
	/**
	 * Test method for {@link com.digi.xbee.api.XBeeDevice#sendExplicitDataAsync(RemoteXBeeDevice, int, int, int, int, byte[])}.
	 * 
	 * <p>Verify that async. explicit data cannot be sent if the protocol of the XBee device is 802.15.4.</p>
	 * 
	 * @throws XBeeException 
	 * @throws TimeoutException 
	 */
	@Test(expected=OperationNotSupportedException.class)
	public void testSendExplicitDataRaw802() throws TimeoutException, XBeeException {
		// Setup the protocol of the XBee device to be 802.15.4.
		Mockito.when(xbeeDevice.getXBeeProtocol()).thenReturn(XBeeProtocol.RAW_802_15_4);
		
		xbeeDevice.sendExplicitDataAsync(mockedRemoteDevice, SOURCE_ENDPOINT, DESTINATION_ENDPOINT, CLUSTER_ID, PROFILE_ID, DATA.getBytes());
	}
	
	/**
	 * Test method for {@link com.digi.xbee.api.XBeeDevice#sendExplicitDataAsync(RemoteXBeeDevice, int, int, int, int, byte[])}.
	 * 
	 * <p>Verify that async. explicit data can be sent if the protocol of the XBee device is DigiMesh.</p>
	 * 
	 * @throws XBeeException 
	 * @throws TimeoutException 
	 */
	@Test
	public void testSendExplicitDataDigiMesh() throws TimeoutException, XBeeException {
		// Setup the protocol of the XBee device to be DigiMesh.
		Mockito.when(xbeeDevice.getXBeeProtocol()).thenReturn(XBeeProtocol.DIGI_MESH);
		// Do nothing when the sendExplicitDataAsync(XBee64BitAddress, int, int, int, int, byte[]) method is called.
		Mockito.doNothing().when(xbeeDevice).sendExplicitDataAsync(Mockito.any(XBee64BitAddress.class), Mockito.anyInt(), Mockito.anyInt(), 
				Mockito.anyInt(), Mockito.anyInt(), Mockito.any(byte[].class));
		
		xbeeDevice.sendExplicitDataAsync(mockedRemoteDevice, SOURCE_ENDPOINT, DESTINATION_ENDPOINT, CLUSTER_ID, PROFILE_ID, DATA.getBytes());
		
		// Verify the sendExplicitDataAsync(XBee64BitAddress, int, int, int, int, byte[]) method was called.
		Mockito.verify(xbeeDevice, Mockito.times(1)).sendExplicitDataAsync(Mockito.eq(XBEE_64BIT_ADDRESS), Mockito.eq(SOURCE_ENDPOINT), Mockito.eq(DESTINATION_ENDPOINT), 
				Mockito.eq(CLUSTER_ID), Mockito.eq(PROFILE_ID), Mockito.eq(DATA.getBytes()));
	}
}
