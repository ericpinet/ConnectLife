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

import java.io.IOException;

import com.digi.xbee.api.exceptions.InterfaceNotOpenException;
import com.digi.xbee.api.exceptions.TimeoutException;
import com.digi.xbee.api.exceptions.XBeeException;
import com.digi.xbee.api.models.ATCommand;
import com.digi.xbee.api.models.ATCommandResponse;
import com.digi.xbee.api.models.XBee16BitAddress;
import com.digi.xbee.api.models.XBee64BitAddress;
import com.digi.xbee.api.models.XBeeProtocol;

/**
 * This class represents a remote XBee device.
 * 
 * @see RemoteDigiMeshDevice
 * @see RemoteDigiPointDevice
 * @see RemoteRaw802Device
 * @see RemoteZigBeeDevice
 */
public class RemoteXBeeDevice extends AbstractXBeeDevice {

	/**
	 * Class constructor. Instantiates a new {@code RemoteXBeeDevice} object 
	 * with the given local {@code XBeeDevice} which contains the connection 
	 * interface to be used.
	 * 
	 * @param localXBeeDevice The local XBee device that will behave as 
	 *                        connection interface to communicate with this 
	 *                        remote XBee device.
	 * @param addr64 The 64-bit address to identify this remote XBee device.
	 * 
	 * @throws IllegalArgumentException if {@code localXBeeDevice.isRemote() == true}.
	 * @throws NullPointerException if {@code localXBeeDevice == null} or
	 *                              if {@code addr64 == null}.
	 * 
	 * @see com.digi.xbee.api.models.XBee64BitAddress
	 */
	public RemoteXBeeDevice(XBeeDevice localXBeeDevice, XBee64BitAddress addr64) {
		super(localXBeeDevice, addr64);
	}
	
	/**
	 * Class constructor. Instantiates a new {@code RemoteXBeeDevice} object 
	 * with the given local {@code XBeeDevice} which contains the connection 
	 * interface to be used.
	 * 
	 * @param localXBeeDevice The local XBee device that will behave as 
	 *                        connection interface to communicate with this 
	 *                        remote XBee device.
	 * @param addr64 The 64-bit address to identify this remote XBee device.
	 * @param addr16 The 16-bit address to identify this remote XBee device. It 
	 *               might be {@code null}.
	 * @param ni The node identifier of this remote XBee device. It might be 
	 *           {@code null}.
	 * 
	 * @throws IllegalArgumentException if {@code localXBeeDevice.isRemote() == true}.
	 * @throws NullPointerException if {@code localXBeeDevice == null} or
	 *                              if {@code addr64 == null}.
	 * 
	 * @see com.digi.xbee.api.models.XBee16BitAddress
	 * @see com.digi.xbee.api.models.XBee64BitAddress
	 */
	public RemoteXBeeDevice(XBeeDevice localXBeeDevice, XBee64BitAddress addr64, 
			XBee16BitAddress addr16, String ni) {
		super(localXBeeDevice, addr64, addr16, ni);
	}
	
	/**
	 * Always returns {@code true}, since it is a remote device.
	 * 
	 * @return {@code true} always.
	 */
	@Override
	public boolean isRemote() {
		return true;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.digi.xbee.api.AbstractXBeeDevice#reset()
	 */
	@Override
	public void reset() throws TimeoutException, XBeeException {
		// Check connection.
		if (!connectionInterface.isOpen())
			throw new InterfaceNotOpenException();
		
		logger.info(toString() + "Resetting the remote module ({})...", get64BitAddress());
		
		ATCommandResponse response = null;
		try {
			response = sendATCommand(new ATCommand("FR"));
		} catch (IOException e) {
			throw new XBeeException("Error writing in the communication interface.", e);
		} catch (TimeoutException e) {
			// Remote 802.15.4 devices do not respond to the AT command.
			if (localXBeeDevice.getXBeeProtocol() == XBeeProtocol.RAW_802_15_4)
				return;
			else
				throw e;
		}
		
		// Check if AT Command response is valid.
		checkATCommandResponseIsValid(response);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.digi.xbee.api.AbstractXBeeDevice#toString()
	 */
	@Override
	public String toString() {
		String id = getNodeID();
		if (id == null)
			id = "";
		return String.format("%s - %s", get64BitAddress(), getNodeID());
	}
}
