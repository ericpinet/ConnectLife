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
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.connectlife.coreserver.tools.os.OperatingSystem;
import com.connectlife.coreserver.tools.os.OperatingSystem.OperatingSystemType;

@RunWith(PowerMockRunner.class)
@PrepareForTest({System.class, OperatingSystem.class})
@PowerMockIgnore("javax.management.*")

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
	public void testGetOSName() {
		
		PowerMockito.mockStatic(System.class);
		PowerMockito.when(System.getProperty("os.name")).thenReturn("windows 7");
		
		assertTrue(OperatingSystem.isWindows());
	}

	@Test
	public void testGetOSWhenMacOS() {
		
		PowerMockito.mockStatic(System.class);
		PowerMockito.when(System.getProperty("os.name")).thenReturn("mac os x", "mac os x");
		
		assertTrue(OperatingSystem.getOS() == OperatingSystemType.MACOSX);
		assertFalse(OperatingSystem.getOS() == OperatingSystemType.WINDOWS);
		assertFalse(OperatingSystem.getOS() == OperatingSystemType.LINUX);
		assertFalse(OperatingSystem.getOS() == OperatingSystemType.UNKWOWN);
	}
	
	@Test
	public void testGetOSWhenWindows() {
		
		PowerMockito.mockStatic(System.class);
		PowerMockito.when(System.getProperty("os.name")).thenReturn("windows 7");
		
		assertFalse(OperatingSystem.getOS() == OperatingSystemType.MACOSX);
		assertTrue(OperatingSystem.getOS() == OperatingSystemType.WINDOWS);
		assertFalse(OperatingSystem.getOS() == OperatingSystemType.LINUX);
		assertFalse(OperatingSystem.getOS() == OperatingSystemType.UNKWOWN);
	}
	
	@Test
	public void testGetOSWhenLinux() {
		
		PowerMockito.mockStatic(System.class);
		PowerMockito.when(System.getProperty("os.name")).thenReturn("nux debian");
		
		assertFalse(OperatingSystem.getOS() == OperatingSystemType.MACOSX);
		assertFalse(OperatingSystem.getOS() == OperatingSystemType.WINDOWS);
		assertTrue(OperatingSystem.getOS() == OperatingSystemType.LINUX);
		assertFalse(OperatingSystem.getOS() == OperatingSystemType.UNKWOWN);
	}
	
	@Test
	public void testIsOSWhenMacOS() {
		
		PowerMockito.mockStatic(System.class);
		PowerMockito.when(System.getProperty("os.name")).thenReturn("mac os x");
		
		assertTrue(OperatingSystem.isMacOSX());
		assertFalse(OperatingSystem.isWindows());
		assertFalse(OperatingSystem.isLinux());
	}
	
	@Test
	public void testIsOSWhenWindows() {
		
		PowerMockito.mockStatic(System.class);
		PowerMockito.when(System.getProperty("os.name")).thenReturn("win 7");
		
		assertFalse(OperatingSystem.isMacOSX());
		assertTrue(OperatingSystem.isWindows());
		assertFalse(OperatingSystem.isLinux());
	}
	
	@Test
	public void testIsOSWhenLinux() {
		
		PowerMockito.mockStatic(System.class);
		PowerMockito.when(System.getProperty("os.name")).thenReturn("nux debian");
		
		assertFalse(OperatingSystem.isMacOSX());
		assertFalse(OperatingSystem.isWindows());
		assertTrue(OperatingSystem.isLinux());
	}

}
