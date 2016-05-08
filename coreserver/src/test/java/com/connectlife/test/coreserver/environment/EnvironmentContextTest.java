/**
 *  EnvironmentContextTest.java
 *  coreserver
 *
 *  Created by ericpinet on 2016-04-03.
 *  Copyright (c) 2016 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.connectlife.test.coreserver.environment;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.connectlife.coreserver.Application;
import com.connectlife.coreserver.environment.EnvironmentContext;
import com.connectlife.coreserver.environment.EnvironmentManager;
import com.connectlife.test.coreserver.ApplicationInjectTest;
import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * 
 * 
 * @author ericpinet
 * <br> 2016-04-03
 */
public class EnvironmentContextTest {
	
	/**
	 * Environment to test
	 */
	EnvironmentContext env;

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
	public void testGetDataManager() {
		Injector injector = Guice.createInjector(new EnvironmentInjectTest());
		env = injector.getInstance(EnvironmentManager.class);
		assertNotNull(env.getDataManager());
	}
	
	@Test
	public void testGetDeviceManager() {
		Injector injector = Guice.createInjector(new EnvironmentInjectTest());
		env = injector.getInstance(EnvironmentManager.class);
		assertNotNull(env.getDeviceManager());
	}
	
	@Test
	public void testGetFindProcessor() {
		Injector injector = Guice.createInjector(new EnvironmentInjectTest());
		EnvironmentManager mgr = injector.getInstance(EnvironmentManager.class);
		env = mgr;
		
		assertTrue(mgr.init());
		
		assertNotNull(env.getFindProcessorReadWrite());
	}

}
