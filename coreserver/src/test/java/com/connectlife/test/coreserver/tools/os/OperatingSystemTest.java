/**
 *  OperatingSystemTest.java
 *  coreserver
 *
 *  Created by ericpinet on 2015-12-13.
 *  Copyright (c) 2015 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.connectlife.test.coreserver.tools.os;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.connectlife.coreserver.tools.os.OperatingSystem;

/**
 * 
 * 
 * @author ericpinet
 * <br> 2015-12-13
 */
public class OperatingSystemTest {

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

	@Test
	public void testGetOSMacOSX() {
		assertTrue(OperatingSystem.isMacOSX());
		assertFalse(OperatingSystem.isWindows());
		assertFalse(OperatingSystem.isLinux());
	}
}
