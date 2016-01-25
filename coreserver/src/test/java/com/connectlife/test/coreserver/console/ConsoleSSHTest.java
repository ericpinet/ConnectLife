/**
 *  ConsoleSSHTest.java
 *  coreserver
 *
 *  Created by ericpinet on 2016-01-24.
 *  Copyright (c) 2016 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.connectlife.test.coreserver.console;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.connectlife.coreserver.Application;
import com.connectlife.coreserver.console.ConsoleSSH;
import com.connectlife.test.coreserver.ApplicationInjectTest;
import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * 
 * 
 * @author ericpinet
 * <br> 2016-01-24
 */
public class ConsoleSSHTest {

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
		
		Injector injector = Guice.createInjector(new ApplicationInjectTest());
		final Application app = injector.getInstance(Application.class);
		
		app.startupTest();
		
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testInit() {
		Injector injector = Guice.createInjector(new ConsoleInjectTest());
		ConsoleSSH console = injector.getInstance(ConsoleSSH.class);
		
		assertTrue(console.init());
				
		assertTrue(console.isInit());
	}
	
	@Test
	public void testInitTwice() {
		Injector injector = Guice.createInjector(new ConsoleInjectTest());
		ConsoleSSH console = injector.getInstance(ConsoleSSH.class);
		
		assertTrue(console.init());
		
		assertTrue(console.init());
		
		assertTrue(console.isInit());
	}
	
	@Test
	public void testUnInit() {
		Injector injector = Guice.createInjector(new ConsoleInjectTest());
		ConsoleSSH console = injector.getInstance(ConsoleSSH.class);
		
		assertTrue(console.init());
		
		console.unInit();
		
		assertFalse(console.isInit());
	}
}
