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
package com.digi.xbee.api.localdio;

import java.util.Timer;
import java.util.TimerTask;

import com.digi.xbee.api.XBeeDevice;
import com.digi.xbee.api.exceptions.XBeeException;
import com.digi.xbee.api.io.IOLine;
import com.digi.xbee.api.io.IOMode;
import com.digi.xbee.api.io.IOValue;

/**
 * XBee Java Library Get/Set Local DIO sample application.
 * 
 * <p>This example reads the status of the input line periodically and updates 
 * the output to follow the input.</p>
 * 
 * <p>For a complete description on the example, refer to the 'ReadMe.txt' file
 * included in the root directory.</p>
 */
public class MainApp {
	
	/* Constants */
	
	// TODO Replace with the serial port where your module is connected to.
	private static final String PORT = "COM1";
	// TODO Replace with the baud rate of your module.
	private static final int BAUD_RATE = 9600;
	private static final int READ_TIMEOUT = 250;
	
	private static final IOLine IOLINE_IN = IOLine.DIO3_AD3;
	private static final IOLine IOLINE_OUT = IOLine.DIO4_AD4;
	
	/**
	 * Application main method.
	 * 
	 * @param args Command line arguments.
	 */
	public static void main(String[] args) {
		System.out.println(" +--------------------------------------------+");
		System.out.println(" | XBee Java Library Get/Set Local DIO Sample |");
		System.out.println(" +--------------------------------------------+\n");
		
		final XBeeDevice myDevice = new XBeeDevice(PORT, BAUD_RATE);
		
		Timer readADCTimer = new Timer();
		
		try {
			myDevice.open();
			
			myDevice.setIOConfiguration(IOLINE_IN, IOMode.DIGITAL_IN);
			
			myDevice.setIOConfiguration(IOLINE_OUT, IOMode.DIGITAL_OUT_LOW);
			
			readADCTimer.schedule(new UpdateOutputTask(myDevice), 0, READ_TIMEOUT);
			
		} catch (XBeeException e) {
			e.printStackTrace();
			myDevice.close();
			System.exit(1);
		}
	}
	
	/**
	 * Update output task to be performed every {@value #READ_TIMEOUT} ms.
	 *
	 * <p>The task will read the digital input state of {@value #IOLINE_IN} 
	 * and set the {@value #IOLINE_OUT} output with the same value.</p>
	 * 
	 * @see TimerTask
	 */
	private static class UpdateOutputTask extends TimerTask {
		private XBeeDevice xbeeDevice;
		
		public UpdateOutputTask(XBeeDevice xbeeDevice) {
			this.xbeeDevice = xbeeDevice;
		}
		
		@Override
		public void run() {
			try {
				// Read the digital value from the input line.
				IOValue value = xbeeDevice.getDIOValue(IOLINE_IN);
				System.out.println(IOLINE_IN + ": " + value);
				
				// Set the previous value to the output line.
				xbeeDevice.setDIOValue(IOLINE_OUT, value);
				
			} catch (XBeeException e) {
				e.printStackTrace();
			}
		}
	}
}
