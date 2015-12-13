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
import com.digi.xbee.api.exceptions.TimeoutException;
import com.digi.xbee.api.exceptions.XBeeException;
import com.digi.xbee.api.models.XBee64BitAddress;
import com.digi.xbee.api.packet.common.ExplicitAddressingPacket;
import com.digi.xbee.api.packet.common.TransmitPacket;

@RunWith(PowerMockRunner.class)
@PrepareForTest({XBeeDevice.class})
public class SendDataHighLevelDigiMeshTest {
	
	// Constants.
	private static final XBee64BitAddress XBEE_64BIT_ADDRESS = new XBee64BitAddress("0123456789ABCDEF");
	
	private static final int SOURCE_ENDPOINT = 0xA0;
	private static final int DESTINATION_ENDPOINT = 0xA1;
	private static final int CLUSTER_ID = 0x1554;
	private static final int PROFILE_ID = 0xC105;
	
	private static final String DATA = "data";
	
	// Variables.
	private DigiMeshDevice digiMeshDevice;
	private RemoteDigiMeshDevice mockedRemoteDigiMeshDevice;
	
	private TransmitPacket transmitPacket;
	private ExplicitAddressingPacket explicitAddressingPacket;
	
	@Before
	public void setup() throws Exception {
		// Instantiate a DigiMeshDevice object with a mocked interface.
		digiMeshDevice = PowerMockito.spy(new DigiMeshDevice(Mockito.mock(SerialPortRxTx.class)));
		
		// Mock Transmit packet.
		transmitPacket = Mockito.mock(TransmitPacket.class);
		// Mock the explicit addressing packet.
		explicitAddressingPacket = Mockito.mock(ExplicitAddressingPacket.class);
		
		// Mock a RemoteDigiMeshDevice to be used as parameter to send data.
		mockedRemoteDigiMeshDevice = Mockito.mock(RemoteDigiMeshDevice.class);
		Mockito.when(mockedRemoteDigiMeshDevice.get64BitAddress()).thenReturn(XBEE_64BIT_ADDRESS);
		
		// Whenever a TransmitPacket class is instantiated, the mocked transmitPacket packet should be returned.
		PowerMockito.whenNew(TransmitPacket.class).withAnyArguments().thenReturn(transmitPacket);
		// Whenever an ExplicitAddressingPacket class is instantiated, the mocked explicitAddressingPacket packet should be returned.
		PowerMockito.whenNew(ExplicitAddressingPacket.class).withAnyArguments().thenReturn(explicitAddressingPacket);
	}
	
	/**
	 * Test method for {@link com.digi.xbee.api.DigiMeshDevice#sendData(XBee64BitAddress, byte[])}.
	 * 
	 * <p>Verify that the super com.digi.xbee.api.XBeeDevice#sendData(XBee64BitAddress, byte[]) method is 
	 * called when executing the com.digi.xbee.api.DigiMeshDevice#sendData(XBee64BitAddress, byte[]) method.</p>
	 * 
	 * @throws XBeeException 
	 * @throws TimeoutException 
	 */
	@Test
	public void testSendData64Success() throws TimeoutException, XBeeException {
		// Do nothing when sending and checking any TransmitPacket packet.
		Mockito.doNothing().when(digiMeshDevice).sendAndCheckXBeePacket(Mockito.any(TransmitPacket.class), Mockito.eq(false));
		
		digiMeshDevice.sendData(XBEE_64BIT_ADDRESS, DATA.getBytes());
		
		// Verify the sendAndCheckXBeePacket(XBeePacket, boolean) method was called.
		Mockito.verify(digiMeshDevice, Mockito.times(1)).sendAndCheckXBeePacket(Mockito.eq(transmitPacket), Mockito.eq(false));
	}
	
	/**
	 * Test method for {@link com.digi.xbee.api.DigiMeshDevice#sendDataAsync(XBee64BitAddress, byte[])}.
	 * 
	 * <p>Verify that the super com.digi.xbee.api.XBeeDevice#sendDataAsync(XBee64BitAddress, byte[]) method is 
	 * called when executing the com.digi.xbee.api.DigiMeshDevice#sendDataAsync(XBee64BitAddress, byte[]) method.</p>
	 * 
	 * @throws XBeeException 
	 * @throws TimeoutException 
	 */
	@Test
	public void testSendDataAsync64Success() throws TimeoutException, XBeeException {
		// Do nothing when sending and checking any TransmitPacket packet.
		Mockito.doNothing().when(digiMeshDevice).sendAndCheckXBeePacket(Mockito.any(TransmitPacket.class), Mockito.eq(true));
		
		digiMeshDevice.sendDataAsync(XBEE_64BIT_ADDRESS, DATA.getBytes());
		
		// Verify the sendAndCheckXBeePacket(XBeePacket, boolean) method was called.
		Mockito.verify(digiMeshDevice, Mockito.times(1)).sendAndCheckXBeePacket(Mockito.eq(transmitPacket), Mockito.eq(true));
	}
	
	/**
	 * Test method for {@link com.digi.xbee.api.DigiMeshDevice#sendExplicitData(RemoteXBeeDevice, int, int, int, int, byte[])}.
	 * 
	 * <p>Verify that the super com.digi.xbee.api.XBeeDevice#sendExplicitData(RemoteXBeeDevice, int, int, int, int, byte[]) method is 
	 * called when executing the com.digi.xbee.api.DigiMeshDevice#sendExplicitData(RemoteXBeeDevice, int, int, int, int, byte[]) method.</p>
	 * 
	 * @throws XBeeException 
	 * @throws TimeoutException 
	 */
	@Test
	public void testSendExplicitDataRemoteDeviceSuccess() throws TimeoutException, XBeeException {
		// Do nothing when the sendExplicitData(XBee64BitAddress, int, int, int, int, byte[]) method is called.
		Mockito.doNothing().when(digiMeshDevice).sendExplicitData(Mockito.any(XBee64BitAddress.class), Mockito.anyInt(), Mockito.anyInt(), 
				Mockito.anyInt(), Mockito.anyInt(), Mockito.any(byte[].class));
		
		digiMeshDevice.sendExplicitData(mockedRemoteDigiMeshDevice, SOURCE_ENDPOINT, DESTINATION_ENDPOINT, CLUSTER_ID, PROFILE_ID, DATA.getBytes());
		
		// Verify the sendExplicitData(XBee64BitAddress, int, int, int, int, byte[]) method was called.
		Mockito.verify(digiMeshDevice, Mockito.times(1)).sendExplicitData(Mockito.eq(XBEE_64BIT_ADDRESS), Mockito.eq(SOURCE_ENDPOINT), 
				Mockito.eq(DESTINATION_ENDPOINT), Mockito.eq(CLUSTER_ID), Mockito.eq(PROFILE_ID), Mockito.eq(DATA.getBytes()));
	}
	
	/**
	 * Test method for {@link com.digi.xbee.api.DigiMeshDevice#sendExplicitData(XBee64BitAddress, int, int, int, int, byte[])}.
	 * 
	 * <p>Verify that the super com.digi.xbee.api.XBeeDevice#sendExplicitData(XBee64BitAddress, int, int, int, int, byte[]) method is 
	 * called when executing the com.digi.xbee.api.DigiMeshDevice#sendExplicitData(XBee64BitAddress, int, int, int, int, byte[]) method.</p>
	 * 
	 * @throws XBeeException 
	 * @throws TimeoutException 
	 */
	@Test
	public void testSendExplicitData64Success() throws TimeoutException, XBeeException {
		// Do nothing when sending and checking any ExplicitAddressingPacket packet.
		Mockito.doNothing().when(digiMeshDevice).sendAndCheckXBeePacket(Mockito.any(ExplicitAddressingPacket.class), Mockito.eq(false));
		
		digiMeshDevice.sendExplicitData(XBEE_64BIT_ADDRESS, SOURCE_ENDPOINT, DESTINATION_ENDPOINT, CLUSTER_ID, PROFILE_ID, DATA.getBytes());
		
		// Verify the sendAndCheckXBeePacket(XBeePacket, boolean) method was called.
		Mockito.verify(digiMeshDevice, Mockito.times(1)).sendAndCheckXBeePacket(Mockito.eq(explicitAddressingPacket), Mockito.eq(false));
	}
	
	/**
	 * Test method for {@link com.digi.xbee.api.DigiMeshDevice#sendBroadcastExplicitData(int, int, int, int, byte[])}.
	 * 
	 * <p>Verify that the super com.digi.xbee.api.XBeeDevice#sendBroadcastExplicitData(int, int, int, int, byte[]) method is 
	 * called when executing the com.digi.xbee.api.DigiMeshDevice#sendBroadcastExplicitData(int, int, int, int, byte[]) method.</p>
	 * 
	 * @throws XBeeException 
	 * @throws TimeoutException 
	 */
	@Test
	public void testSendBroadcastExplicitDataSuccess() throws TimeoutException, XBeeException {
		// Do nothing when the sendExplicitData(XBee64BitAddress, int, int, int, int, byte[]) method is called.
		Mockito.doNothing().when(digiMeshDevice).sendExplicitData(Mockito.any(XBee64BitAddress.class), Mockito.anyInt(), Mockito.anyInt(), 
				Mockito.anyInt(), Mockito.anyInt(), Mockito.any(byte[].class));
		
		digiMeshDevice.sendBroadcastExplicitData(SOURCE_ENDPOINT, DESTINATION_ENDPOINT, CLUSTER_ID, PROFILE_ID, DATA.getBytes());
		
		// Verify the sendExplicitData(XBee64BitAddress, int, int, int, int, byte[]) method was called (64BitAddress must be the broadcast one).
		Mockito.verify(digiMeshDevice, Mockito.times(1)).sendExplicitData(Mockito.eq(XBee64BitAddress.BROADCAST_ADDRESS), Mockito.eq(SOURCE_ENDPOINT), 
				Mockito.eq(DESTINATION_ENDPOINT), Mockito.eq(CLUSTER_ID), Mockito.eq(PROFILE_ID), Mockito.eq(DATA.getBytes()));
	}
	
	/**
	 * Test method for {@link com.digi.xbee.api.DigiMeshDevice#sendExplicitDataAsync(RemoteXBeeDevice, int, int, int, int, byte[])}.
	 * 
	 * <p>Verify that the super com.digi.xbee.api.XBeeDevice#sendExplicitDataAsync(RemoteXBeeDevice, int, int, int, int, byte[]) method is 
	 * called when executing the com.digi.xbee.api.DigiMeshDevice#sendExplicitDataAsync(RemoteXBeeDevice, int, int, int, int, byte[]) method.</p>
	 * 
	 * @throws XBeeException 
	 * @throws TimeoutException 
	 */
	@Test
	public void testSendExplicitDataAsyncRemoteDeviceSuccess() throws TimeoutException, XBeeException {
		// Do nothing when the sendExplicitDataAsync(XBee64BitAddress, int, int, int, int, byte[]) method is called.
		Mockito.doNothing().when(digiMeshDevice).sendExplicitDataAsync(Mockito.any(XBee64BitAddress.class), Mockito.anyInt(), Mockito.anyInt(), 
				Mockito.anyInt(), Mockito.anyInt(), Mockito.any(byte[].class));
		
		digiMeshDevice.sendExplicitDataAsync(mockedRemoteDigiMeshDevice, SOURCE_ENDPOINT, DESTINATION_ENDPOINT, CLUSTER_ID, PROFILE_ID, DATA.getBytes());
		
		// Verify the sendExplicitDataAsync(XBee64BitAddress, int, int, int, int, byte[]) method was called.
		Mockito.verify(digiMeshDevice, Mockito.times(1)).sendExplicitDataAsync(Mockito.eq(XBEE_64BIT_ADDRESS), Mockito.eq(SOURCE_ENDPOINT), 
				Mockito.eq(DESTINATION_ENDPOINT), Mockito.eq(CLUSTER_ID), Mockito.eq(PROFILE_ID), Mockito.eq(DATA.getBytes()));
	}
	
	/**
	 * Test method for {@link com.digi.xbee.api.DigiMeshDevice#sendExplicitDataAsync(XBee64BitAddress, int, int, int, int, byte[])}.
	 * 
	 * <p>Verify that the super com.digi.xbee.api.XBeeDevice#sendExplicitDataAsync(XBee64BitAddress, int, int, int, int, byte[]) method is 
	 * called when executing the com.digi.xbee.api.DigiMeshDevice#sendExplicitDataAsync(XBee64BitAddress, int, int, int, int, byte[]) method.</p>
	 * 
	 * @throws XBeeException 
	 * @throws TimeoutException 
	 */
	@Test
	public void testSendExplicitDataAsync64Success() throws TimeoutException, XBeeException {
		// Do nothing when sending and checking any ExplicitAddressingPacket packet.
		Mockito.doNothing().when(digiMeshDevice).sendAndCheckXBeePacket(Mockito.any(ExplicitAddressingPacket.class), Mockito.eq(true));
		
		digiMeshDevice.sendExplicitDataAsync(XBEE_64BIT_ADDRESS, SOURCE_ENDPOINT, DESTINATION_ENDPOINT, CLUSTER_ID, PROFILE_ID, DATA.getBytes());
		
		// Verify the sendAndCheckXBeePacket(XBeePacket, boolean) method was called.
		Mockito.verify(digiMeshDevice, Mockito.times(1)).sendAndCheckXBeePacket(Mockito.eq(explicitAddressingPacket), Mockito.eq(true));
	}
}
