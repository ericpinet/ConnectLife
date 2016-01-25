/**
 *  OperatingSystemTest.java
 *  coreserver
 *
 *  Created by ericpinet on 2016-01-24.
 *  Copyright (c) 2016 ConnectLife (Eric Pinet). All rights reserved.
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
import com.connectlife.coreserver.tools.os.OperatingSystem.OperatingSystemType;

/**
 * 
 * 
 * @author ericpinet
 * <br> 2016-01-24
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
	public void testGetOS() {
		
		if(System.getProperty("os.name").toLowerCase().indexOf("mac") >= 0){
			assertTrue(OperatingSystem.getOS() == OperatingSystemType.MACOSX);
		}
		else if(System.getProperty("os.name").toLowerCase().indexOf("win") >= 0){
			assertTrue(OperatingSystem.getOS() == OperatingSystemType.WINDOWS);
		}
		else if(System.getProperty("os.name").toLowerCase().indexOf("nux") >= 0){
			assertTrue(OperatingSystem.getOS() == OperatingSystemType.LINUX);
		}
		else{
			assertTrue(OperatingSystem.getOS() == OperatingSystemType.UNKWOWN);
		}
	}
	
	@Test
	public void testIsOS() {
		if(System.getProperty("os.name").toLowerCase().indexOf("mac") >= 0){
			assertTrue(OperatingSystem.isMacOSX());
		}
		else if(System.getProperty("os.name").toLowerCase().indexOf("win") >= 0){
			assertTrue(OperatingSystem.isWindows());
		}
		else if(System.getProperty("os.name").toLowerCase().indexOf("nux") >= 0){
			assertTrue(OperatingSystem.isLinux());
		}
	}

}
