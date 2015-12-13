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
package com.digi.xbee.api.sendbroadcastexplicitdata;

import com.digi.xbee.api.ZigBeeDevice;
import com.digi.xbee.api.exceptions.XBeeException;
import com.digi.xbee.api.utils.HexUtils;

/**
 * XBee Java Library Send Broadcast Explicit Data sample application.
 * 
 * <p>This example sends explicit data to all remote devices on the same 
 * network.</p>
 * 
 * <p>For a complete description on the example, refer to the 'ReadMe.txt' file
 * included in the root directory.</p>
 */
public class MainApp {
	
	/* Constants */
	
	// TODO Replace with the serial port where your sender module is connected to.
	private static final String PORT = "COM1";
	// TODO Replace with the baud rate of your sender module.
	private static final int BAUD_RATE = 9600;
	
	private static final String DATA_TO_SEND = "Hello XBee World!";
	
	// Examples of endpoints, cluster ID and profile ID.
	private static final int SOURCE_ENDPOINT = 0xA0;
	private static final int DESTINATION_ENDPOINT = 0xA1;
	private static final int CLUSTER_ID = 0x1554;
	private static final int PROFILE_ID = 0x1234;
	
	/**
	 * Application main method.
	 * 
	 * @param args Command line arguments.
	 */
	public static void main(String[] args) {
		System.out.println(" +---------------------------------------------------------+");
		System.out.println(" |  XBee Java Library Send Broadcast Explicit Data Sample  |");
		System.out.println(" +---------------------------------------------------------+\n");
		
		ZigBeeDevice myDevice = new ZigBeeDevice(PORT, BAUD_RATE);
		byte[] dataToSend = DATA_TO_SEND.getBytes();
		
		try {
			myDevice.open();
			
			System.out.format("Sending broadcast data [%s %s %s %s] >> '%s' | %s... ",
					HexUtils.integerToHexString(SOURCE_ENDPOINT, 1), 
					HexUtils.integerToHexString(DESTINATION_ENDPOINT, 1), 
					HexUtils.integerToHexString(CLUSTER_ID, 2), 
					HexUtils.integerToHexString(PROFILE_ID, 2), 
					HexUtils.prettyHexString(HexUtils.byteArrayToHexString(dataToSend)), 
					new String(dataToSend));
			
			myDevice.sendBroadcastExplicitData(SOURCE_ENDPOINT, DESTINATION_ENDPOINT, CLUSTER_ID, PROFILE_ID, dataToSend);
			
			System.out.println("Success");
			
		} catch (XBeeException e) {
			System.out.println("Error");
			e.printStackTrace();
			System.exit(1);
		} finally {
			myDevice.close();
		}
	}
}
