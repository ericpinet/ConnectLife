/**
 *  ApiGrpcTest.java
 *  coreserver
 *
 *  Created by ericpinet on 2016-01-22.
 *  Copyright (c) 2016 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.connectlife.test.coreserver.apiserver;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.connectlife.coreserver.Application;
import com.connectlife.coreserver.apiserver.ApiGrpc;
import com.connectlife.test.coreserver.ApplicationInjectTest;
import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * 
 * 
 * @author ericpinet
 * <br> 2016-01-22
 */
public class ApiGrpcTest {

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
	public void testInit() {
		Injector injector = Guice.createInjector(new ApiGrpcInjectTest());
		final ApiGrpc api = injector.getInstance(ApiGrpc.class);
		
		assertFalse(api.isInit());
		
		api.init();
		
		assertTrue(api.isInit());
		
		api.init();
		
		assertTrue(api.isInit());
		
		api.unInit();
		
		assertFalse(api.isInit());
		
		
	}
}
