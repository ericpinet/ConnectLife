/**
 *  CmdAddAccessoryTest.java
 *  coreserver
 *
 *  Created by ericpinet on 2016-06-14.
 *  Copyright (c) 2016 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.connectlife.test.coreserver.environment.cmd;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import com.clapi.data.Accessory;
import com.connectlife.coreserver.environment.EnvironmentContext;
import com.connectlife.coreserver.environment.cmd.CmdAddAccessory;
import com.connectlife.coreserver.environment.cmd.CmdFactory;

/**
 * 
 * 
 * @author ericpinet
 * <br> 2016-06-14
 */
public class CmdAddAccessoryTest {

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

	/**
	 * Test with null accessory
	 */
	@Test
	public void testNullAccessory() {
		
		EnvironmentContext context = Mockito.mock(EnvironmentContext.class);
		
		CmdAddAccessory cmd = CmdFactory.getCmdAddAccesssory(null, null);
		cmd.setContext(context);

		try {
			cmd.execute();
			fail();
		} catch (Exception e) {
			assertNotNull(e);
		}
	}
	
	/**
	 * Test with uid set in accessory
	 */
	@Test
	public void testWithUIDAccessory() {
		
		EnvironmentContext context = Mockito.mock(EnvironmentContext.class);
		
		Accessory accessory = new Accessory("12345", "Label");
		
		CmdAddAccessory cmd = CmdFactory.getCmdAddAccesssory(accessory, null);
		cmd.setContext(context);

		try {
			cmd.execute();
			fail();
		} catch (Exception e) {
			assertNotNull(e);
		}
	}
	
	/**
	 * Test without uid set in accessory but room null
	 */
	@Test
	public void testWithoutUIDAccessory() {
		
		EnvironmentContext context = Mockito.mock(EnvironmentContext.class);
		
		Accessory accessory = new Accessory("", "Label");
		
		CmdAddAccessory cmd = CmdFactory.getCmdAddAccesssory(accessory, null);
		cmd.setContext(context);

		try {
			cmd.execute();
			fail();
		} catch (Exception e) {
			assertNotNull(e);
		}
	}
}
