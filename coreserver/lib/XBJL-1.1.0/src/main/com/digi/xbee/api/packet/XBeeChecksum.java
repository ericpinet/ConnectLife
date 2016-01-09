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
package com.digi.xbee.api.packet;

/**
 * This class stores, computes and verifies the checksum of the API packets.
 * 
 * <p>To test data integrity, a checksum is calculated and verified on 
 * non-escaped API data.</p>
 * 
 * <p><b>To calculate</b></p>
 * 
 * <p>Not including frame delimiters and length, add all bytes keeping only the 
 * lowest 8 bits of the result and subtract the result from {@code 0xFF}.</p>
 * 
 * <p><b>To verify</b></p>
 * 
 * <p>Add all bytes (include checksum, but not the delimiter and length). If the 
 * checksum is correct, the sum will equal {@code 0xFF}.</p>
 */
public class XBeeChecksum {

	// Variables.
	private int value = 0;
	
	/**
	 * Adds the given byte to the checksum.
	 * 
	 * @param value Byte to add.
	 */
	public void add(int value) {
		this.value += value;
	}
	
	/**
	 * Adds the given data to the checksum.
	 * 
	 * @param data Byte array to add.
	 */
	public void add(byte[] data) {
		if (data == null)
			return;
		for (int i = 0; i < data.length; i++)
			add(data[i]);
	}
	
	/**
	 * Resets the checksum.
	 */
	public void reset() {
		value = 0;
	}
	
	/**
	 * Generates the checksum byte for the API packet.
	 * 
	 * @return Checksum byte.
	 */
	public int generate() {
		value = value & 0xFF;
		return 0xFF - value;
	}
	
	/**
	 * Validates the checksum.
	 * 
	 * @return {@code true} if checksum is valid, {@code false} otherwise.
	 */
	public boolean validate() {
		value = value & 0xFF;
		return value == 0xFF;
	}
}
