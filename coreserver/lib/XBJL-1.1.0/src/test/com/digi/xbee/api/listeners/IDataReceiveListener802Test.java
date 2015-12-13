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
package com.digi.xbee.api.listeners;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import com.digi.xbee.api.RemoteRaw802Device;
import com.digi.xbee.api.RemoteXBeeDevice;
import com.digi.xbee.api.XBeeDevice;
import com.digi.xbee.api.XBeeNetwork;
import com.digi.xbee.api.connection.DataReader;
import com.digi.xbee.api.connection.IConnectionInterface;
import com.digi.xbee.api.models.OperatingMode;
import com.digi.xbee.api.models.XBee16BitAddress;
import com.digi.xbee.api.models.XBee64BitAddress;
import com.digi.xbee.api.models.XBeeMessage;
import com.digi.xbee.api.models.XBeeProtocol;
import com.digi.xbee.api.packet.APIFrameType;
import com.digi.xbee.api.packet.XBeePacket;
import com.digi.xbee.api.packet.common.ATCommandResponsePacket;
import com.digi.xbee.api.packet.raw.RX16Packet;
import com.digi.xbee.api.packet.raw.RX64Packet;

@RunWith(PowerMockRunner.class)
@PrepareForTest({DataReader.class})
public class IDataReceiveListener802Test {
	
	// Constants.
	private static final XBee16BitAddress XBEE_16BIT_ADDRESS = new XBee16BitAddress("0123");
	private static final XBee64BitAddress XBEE_64BIT_ADDRESS = new XBee64BitAddress("0123456789ABCDEF");
	
	private static final String RECEIVED_DATA = "data";
	private static final byte[] RECEIVED_DATA_BYTES = RECEIVED_DATA.getBytes();
	
	private static final String PACKET_RECEIVED_METHOD = "packetReceived";
	private static final String NOTIFY_DATA_RECEIVED_METHOD = "notifyDataReceived";
	
	// Variables.
	private static XBeeDevice xbeeDevice;
	
	private MyReceiveListener receiveDataListener;
	
	private static RX16Packet rx16Packet;
	private static RX64Packet rx64Packet;
	private static ATCommandResponsePacket invalidPacket;
	
	private static RemoteRaw802Device remote802XBee64Device;
	private static RemoteRaw802Device remote802XBee16Device;
	
	private DataReader dataReader;
	
	@BeforeClass
	public static void setupOnce() throws Exception {
		// Mock Rx16 Packet.
		rx16Packet = Mockito.mock(RX16Packet.class);
		Mockito.when(rx16Packet.getFrameType()).thenReturn(APIFrameType.RX_16);
		Mockito.when(rx16Packet.getRFData()).thenReturn(RECEIVED_DATA_BYTES);
		Mockito.when(rx16Packet.get16bitSourceAddress()).thenReturn(XBEE_16BIT_ADDRESS);
		Mockito.when(rx16Packet.isBroadcast()).thenReturn(false);
		
		// Mock Rx64 Packet.
		rx64Packet = Mockito.mock(RX64Packet.class);
		Mockito.when(rx64Packet.getFrameType()).thenReturn(APIFrameType.RX_64);
		Mockito.when(rx64Packet.getRFData()).thenReturn(RECEIVED_DATA_BYTES);
		Mockito.when(rx64Packet.get64bitSourceAddress()).thenReturn(XBEE_64BIT_ADDRESS);
		Mockito.when(rx64Packet.isBroadcast()).thenReturn(false);
		
		// Mock an invalid packet.
		invalidPacket = Mockito.mock(ATCommandResponsePacket.class);
		
		// Mock a remote 802.15.4 device with only 64-bit address.
		remote802XBee64Device = Mockito.mock(RemoteRaw802Device.class);
		Mockito.when(remote802XBee64Device.get64BitAddress()).thenReturn(XBEE_64BIT_ADDRESS);
		
		// Mock a remote 802.15.4 device with only 16-bit address.
		remote802XBee16Device = Mockito.mock(RemoteRaw802Device.class);
		Mockito.when(remote802XBee16Device.get16BitAddress()).thenReturn(XBEE_16BIT_ADDRESS);
		
		// Mock the XBee network.
		XBeeNetwork network = Mockito.mock(XBeeNetwork.class);
		Mockito.when(network.getDevice(Mockito.any(XBee64BitAddress.class))).thenReturn(remote802XBee64Device);
		Mockito.when(network.getDevice(Mockito.any(XBee16BitAddress.class))).thenReturn(remote802XBee16Device);
		
		// Mock the XBee device.
		xbeeDevice = Mockito.mock(XBeeDevice.class);
		Mockito.when(xbeeDevice.getXBeeProtocol()).thenReturn(XBeeProtocol.RAW_802_15_4);
		Mockito.when(xbeeDevice.getNetwork()).thenReturn(network);
	}
	
	@Before
	public void setup() throws Exception {
		// Data receive listener.
		receiveDataListener = PowerMockito.spy(new MyReceiveListener());
		
		// Data reader.
		dataReader = PowerMockito.spy(new DataReader(Mockito.mock(IConnectionInterface.class), OperatingMode.UNKNOWN, xbeeDevice));
		// Stub the 'notifyDataReceived' method of the dataReader instance so it directly notifies the 
		// listeners instead of opening a new thread per listener (which is what the real method does). This avoids us 
		// having to wait for the executor to run the threads.
		PowerMockito.doAnswer(new Answer<Object>() {
			public Object answer(InvocationOnMock invocation) {
				Object[] args = invocation.getArguments();
				XBeeMessage xbeeMessage = (XBeeMessage)args[0];
				notifyDataReceiveListeners(xbeeMessage);
				return null;
			}
		}).when(dataReader, NOTIFY_DATA_RECEIVED_METHOD, Mockito.any(XBeeMessage.class));
	}
	
	/**
	 * Test method for {@link com.digi.xbee.api.listeners.IDataReceiveListener#dataReceived(XBeeMessage)}.
	 * 
	 * <p>Verify that the data received callback of the IDataReceive interface is executed 
	 * correctly when a unicast XBeeMessage (originated by an Rx16 frame) is received.</p>
	 */
	@Test
	public void testUnicastDataReceiveEventRx16() {
		// This is the message that should have been created if a unicast Rx16 frame would have been received.
		XBeeMessage xbeeMessage = new XBeeMessage(remote802XBee16Device, RECEIVED_DATA_BYTES, false);
		
		receiveDataListener.dataReceived(xbeeMessage);
		
		assertEquals(XBEE_16BIT_ADDRESS, receiveDataListener.get16BitAddress());
		assertNull(receiveDataListener.get64BitAddress());
		assertArrayEquals(RECEIVED_DATA_BYTES, receiveDataListener.getData());
		assertFalse(receiveDataListener.isBroadcast());
	}
	
	/**
	 * Test method for {@link com.digi.xbee.api.listeners.IDataReceiveListener#dataReceived(XBeeMessage)}.
	 * 
	 * <p>Verify that the data received callback of the IDataReceive interface is executed 
	 * correctly when a broadcast XBeeMessage (originated by an Rx16 frame) is received.</p>
	 */
	@Test
	public void testBroadcastDataReceiveEventRx16() {
		// This is the message that should have been created if a broadcast Rx16 frame would have been received.
		XBeeMessage xbeeMessage = new XBeeMessage(remote802XBee16Device, RECEIVED_DATA_BYTES, true);
		
		receiveDataListener.dataReceived(xbeeMessage);
		
		assertEquals(XBEE_16BIT_ADDRESS, receiveDataListener.get16BitAddress());
		assertNull(receiveDataListener.get64BitAddress());
		assertArrayEquals(RECEIVED_DATA_BYTES, receiveDataListener.getData());
		assertTrue(receiveDataListener.isBroadcast());
	}
	
	/**
	 * Test method for {@link com.digi.xbee.api.listeners.IDataReceiveListener#dataReceived(XBeeMessage)}.
	 * 
	 * <p>Verify that the data received callback of the IDataReceive interface is executed 
	 * correctly when a unicast XBeeMessage (originated by an Rx64 frame) is received.</p>
	 */
	@Test
	public void testUnicastDataReceiveEventRx64() {
		// This is the message that should have been created if a unicast Rx64 frame would have been received.
		XBeeMessage xbeeMessage = new XBeeMessage(remote802XBee64Device, RECEIVED_DATA_BYTES, false);
		
		receiveDataListener.dataReceived(xbeeMessage);
		
		assertNull(receiveDataListener.get16BitAddress());
		assertEquals(XBEE_64BIT_ADDRESS, receiveDataListener.get64BitAddress());
		assertArrayEquals(RECEIVED_DATA_BYTES, receiveDataListener.getData());
		assertFalse(receiveDataListener.isBroadcast());
	}
	
	/**
	 * Test method for {@link com.digi.xbee.api.listeners.IDataReceiveListener#dataReceived(XBeeMessage)}.
	 * 
	 * <p>Verify that the data received callback of the IDataReceive interface is executed 
	 * correctly when a broadcast XBeeMessage (originated by an Rx64 frame) is received.</p>
	 */
	@Test
	public void testBroadcastDataReceiveEventRx64() {
		// This is the message that should have been created if a broadcast Rx64 frame would have been received.
		XBeeMessage xbeeMessage = new XBeeMessage(remote802XBee64Device, RECEIVED_DATA_BYTES, true);
		
		receiveDataListener.dataReceived(xbeeMessage);
		
		assertNull(receiveDataListener.get16BitAddress());
		assertEquals(XBEE_64BIT_ADDRESS, receiveDataListener.get64BitAddress());
		assertArrayEquals(RECEIVED_DATA_BYTES, receiveDataListener.getData());
		assertTrue(receiveDataListener.isBroadcast());
	}
	
	/**
	 * Test method for {@link com.digi.xbee.api.listeners.IDataReceiveListener#dataReceived(XBeeMessage)} and
	 * {@link com.digi.xbee.api.connection.DataReader#packetReceived(XBeePacket)}.
	 * 
	 * <p>Verify that if the listener is not subscribed to receive data, the callback is not 
	 * executed although an 802.15.4 data packet is received.</p>
	 * 
	 * @throws Exception
	 */
	@Test
	public void testDataReceiveNotSubscribed() throws Exception {
		// Whenever a new Remote 802.15.4 device needs to be instantiated, return the mocked one.
		PowerMockito.whenNew(RemoteXBeeDevice.class).withAnyArguments().thenReturn(remote802XBee64Device);
		
		// Fire the private packetReceived method of the dataReader with anRx64  packet.
		Whitebox.invokeMethod(dataReader, PACKET_RECEIVED_METHOD, rx64Packet);
		
		// Verify that the notifyDataReceived private method was called.
		PowerMockito.verifyPrivate(dataReader, Mockito.times(1)).invoke(NOTIFY_DATA_RECEIVED_METHOD, 
				Mockito.any(XBeeMessage.class));
		
		// As the receiveDataListener was not subscribed in the dataReceiveListeners of the dataReader object, the 
		// addresses and data of the receiveDataListener should be null.
		assertNull(receiveDataListener.get64BitAddress());
		assertNull(receiveDataListener.get16BitAddress());
		assertNull(receiveDataListener.getData());
		assertFalse(receiveDataListener.isBroadcast());
	}
	
	/**
	 * Test method for {@link com.digi.xbee.api.listeners.IDataReceiveListener#dataReceived(XBeeMessage)} and
	 * {@link com.digi.xbee.api.connection.DataReader#packetReceived(XBeePacket)}.
	 * 
	 * <p>Verify that, when subscribed to receive data and a Rx16 packet is received, the data received 
	 * callback of the listener is executed.</p>
	 * 
	 * @throws Exception
	 */
	@Test
	public void testDataReceiveSubscribedRx16() throws Exception {
		// Whenever a new Remote 802.15.4 device needs to be instantiated, return the mocked one with 16-bit address.
		PowerMockito.whenNew(RemoteRaw802Device.class).withAnyArguments().thenReturn(remote802XBee16Device);
		
		// Subscribe to listen for data.
		dataReader.addDataReceiveListener(receiveDataListener);
		
		// Fire the private packetReceived method of the dataReader with a RX16Packet.
		Whitebox.invokeMethod(dataReader, PACKET_RECEIVED_METHOD, rx16Packet);
		
		// Verify that the notifyDataReceived private method was called.
		PowerMockito.verifyPrivate(dataReader, Mockito.times(1)).invoke(NOTIFY_DATA_RECEIVED_METHOD, 
				Mockito.any(XBeeMessage.class));
		
		// Verify that the dataReceived method of the listener was executed one time.
		Mockito.verify(receiveDataListener, Mockito.times(1)).dataReceived(Mockito.any(XBeeMessage.class));
		
		// All the parameters of our listener but the 64-bit address should be correct.
		assertEquals(XBEE_16BIT_ADDRESS, receiveDataListener.get16BitAddress());
		assertNull(receiveDataListener.get64BitAddress());
		assertArrayEquals(RECEIVED_DATA_BYTES, receiveDataListener.getData());
		assertFalse(receiveDataListener.isBroadcast());
	}
	
	/**
	 * Test method for {@link com.digi.xbee.api.listeners.IDataReceiveListener#dataReceived(XBeeMessage)} and
	 * {@link com.digi.xbee.api.connection.DataReader#packetReceived(XBeePacket)}.
	 * 
	 * <p>Verify that, when subscribed to receive data and a Rx64 packet is received, the data received 
	 * callback of the listener is executed.</p>
	 * 
	 * @throws Exception
	 */
	@Test
	public void testDataReceiveSubscribedRx64() throws Exception {
		// Whenever a new Remote 802.15.4 device needs to be instantiated, return the mocked one with 64-bit address.
		PowerMockito.whenNew(RemoteXBeeDevice.class).withAnyArguments().thenReturn(remote802XBee64Device);
		
		// Subscribe to listen for data.
		dataReader.addDataReceiveListener(receiveDataListener);
		
		// Fire the private packetReceived method of the dataReader with a RX16Packet.
		Whitebox.invokeMethod(dataReader, PACKET_RECEIVED_METHOD, rx64Packet);
		
		// Verify that the notifyDataReceived private method was called.
		PowerMockito.verifyPrivate(dataReader, Mockito.times(1)).invoke(NOTIFY_DATA_RECEIVED_METHOD, 
				Mockito.any(XBeeMessage.class));
		
		// Verify that the dataReceived method of the listener was executed one time.
		Mockito.verify(receiveDataListener, Mockito.times(1)).dataReceived(Mockito.any(XBeeMessage.class));
		
		// All the parameters of our listener but the 64-bit address should be correct.
		assertNull(receiveDataListener.get16BitAddress());
		assertEquals(XBEE_64BIT_ADDRESS, receiveDataListener.get64BitAddress());
		assertArrayEquals(RECEIVED_DATA_BYTES, receiveDataListener.getData());
		assertFalse(receiveDataListener.isBroadcast());
	}
	
	/**
	 * Test method for {@link com.digi.xbee.api.listeners.IDataReceiveListener#dataReceived(XBeeMessage)} and
	 * {@link com.digi.xbee.api.connection.DataReader#packetReceived(XBeePacket)}.
	 * 
	 * <p>Verify that, when subscribed to receive data and a packet that does not correspond to 
	 * data, the callback of the listener is not executed.</p>
	 * 
	 * @throws Exception
	 */
	@Test
	public void testDataReceiveSubscribedInvalid() throws Exception {
		// Subscribe to listen for data.
		dataReader.addDataReceiveListener(receiveDataListener);
		
		// Fire the private packetReceived method of the dataReader with an invalid packet.
		Whitebox.invokeMethod(dataReader, PACKET_RECEIVED_METHOD, invalidPacket);
		
		// Verify that the notifyDataReceived private method was not called.
		PowerMockito.verifyPrivate(dataReader, Mockito.never()).invoke(NOTIFY_DATA_RECEIVED_METHOD, 
				Mockito.any(XBeeMessage.class));
		
		// Verify that the callback of the listener was not executed
		Mockito.verify(receiveDataListener, Mockito.never()).dataReceived(Mockito.any(XBeeMessage.class));
		
		// All the parameters of our listener should be empty.
		assertNull(receiveDataListener.get16BitAddress());
		assertNull(receiveDataListener.get64BitAddress());
		assertNull(receiveDataListener.getData());
		assertFalse(receiveDataListener.isBroadcast());
	}
	
	
	/**
	 * This method directly notifies the IDataReceiveListeners of the dataReader instance that new 
	 * data has been received. This method intends to replace the original 'notifyDataReceived' 
	 * located within the dataReader object because it generates a thread for each notify process.
	 * 
	 * @param xbeeMessage The XBeeMessage containing the address of the node that sent the data, the data 
	 *                    and a flag indicating if the data was sent via broadcast.
	 */
	private void notifyDataReceiveListeners(XBeeMessage xbeeMessage) {
		@SuppressWarnings("unchecked")
		ArrayList<IDataReceiveListener> dataReceiveListeners = (ArrayList<IDataReceiveListener>)Whitebox.getInternalState(dataReader, "dataReceiveListeners");
		for (IDataReceiveListener listener:dataReceiveListeners)
			listener.dataReceived(xbeeMessage);
	}
	
	/**
	 * Helper class to test the IDataReceiveListener.
	 */
	private class MyReceiveListener implements IDataReceiveListener {
		
		// Variables.
		private byte[] data = null;
		private XBee64BitAddress address64 = null;
		private XBee16BitAddress address16 = null;
		private boolean isBroadcast = false;
		
		/*
		 * (non-Javadoc)
		 * @see com.digi.xbee.api.listeners.IDataReceiveListener#dataReceived(com.digi.xbee.api.models.XBeeMessage)
		 */
		public void dataReceived(XBeeMessage xbeeMessage) {
			this.address64 = xbeeMessage.getDevice().get64BitAddress();
			this.address16 = xbeeMessage.getDevice().get16BitAddress();
			this.data = xbeeMessage.getData();
			this.isBroadcast = xbeeMessage.isBroadcast();
		}
		
		/**
		 * Retrieves the data received.
		 * 
		 * @return The data.
		 */
		public byte[] getData() {
			return data;
		}
		
		/**
		 * Retrieves the 64-bit source address of the node that sent the data.
		 * 
		 * @return The remote 64-bit address.
		 */
		public XBee64BitAddress get64BitAddress() {
			return address64;
		}
		
		/**
		 * Retrieves the 16-bit source address of the node that sent the data.
		 * 
		 * @return The remote 16-bit address.
		 */
		public XBee16BitAddress get16BitAddress() {
			return address16;
		}
		
		/**
		 * Retrieves whether or not the data was sent via broadcast.
		 * 
		 * @return True if the data was sent via broadcast, false otherwise.
		 */
		public boolean isBroadcast() {
			return isBroadcast;
		}
	}
}
