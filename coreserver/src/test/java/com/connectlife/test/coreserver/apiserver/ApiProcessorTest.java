/**
 *  ApiProcessorTest.java
 *  coreserver
 *
 *  Created by ericpinet on 2015-10-18.
 *  Copyright (c) 2015 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.connectlife.test.coreserver.apiserver;

// external
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.inject.Guice;
import com.google.inject.Injector;
import static org.junit.Assert.*;

import org.apache.thrift.TException;

import com.connectlife.clapi.Data;
// internal
import com.connectlife.coreserver.apiserver.ApiProcessor;
import com.connectlife.test.coreserver.environment.CreateTestData;

/**
 * 
 * 
 * @author ericpinet
 * <br> 2015-10-18
 */
public class ApiProcessorTest {
	
	ApiProcessor apiprocessor;

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
		
		Injector injector = Guice.createInjector(new ApiProcessorInjectTest());
		apiprocessor = injector.getInstance(ApiProcessor.class);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void checkCompatibilitySameTest() {

		int version_id_1 = ApiProcessor.API_SERVER_VERSION[0];
		int version_id_2 = ApiProcessor.API_SERVER_VERSION[1];
		int version_id_3 = ApiProcessor.API_SERVER_VERSION[2];
		
		try {
			assertTrue(apiprocessor.checkCompatibility(new String( 	version_id_1 + 
																	"." + 
																	version_id_2 + 
																	"." + 
																	version_id_3 )));
		} catch (TException e) {
			fail("TExeption :" + e.getMessage());
		}
	}
	
	@Test
	public void checkCompatibilityUpValidTest() {
		
		int version_id_1 = ApiProcessor.API_SERVER_VERSION[0] + 1000;
		int version_id_2 = ApiProcessor.API_SERVER_VERSION[1] + 10;
		int version_id_3 = ApiProcessor.API_SERVER_VERSION[2] + 1000;
		
		try {
			assertTrue(apiprocessor.checkCompatibility(new String( 	version_id_1 + 
																	"." + 
																	version_id_2 + 
																	"." + 
																	version_id_3 )));
		} catch (TException e) {
			fail("TExeption :" + e.getMessage());
		}
	}
	
	@Test
	public void checkCompatibilityDownValidTest() {
		
		int version_id_1 = ApiProcessor.API_SERVER_VERSION[0] - 1000;
		int version_id_2 = ApiProcessor.API_SERVER_VERSION[1] + 500;
		int version_id_3 = ApiProcessor.API_SERVER_VERSION[2] - 1000;
		
		try {
			assertTrue(apiprocessor.checkCompatibility(new String( 	version_id_1 + 
																	"." + 
																	version_id_2 + 
																	"." + 
																	version_id_3 )));
		} catch (TException e) {
			fail("TExeption :" + e.getMessage());
		}
	}
	
	@Test
	public void checkCompatibilityUpInvalidTest() {
		
		int version_id_1 = ApiProcessor.API_SERVER_VERSION[0] + 1000;
		int version_id_2 = ApiProcessor.API_SERVER_VERSION[1] + 500;
		int version_id_3 = ApiProcessor.API_SERVER_VERSION[2] + 2000;
		
		try {
			assertFalse(apiprocessor.checkCompatibility(new String( version_id_1 + 
																	"." + 
																	version_id_2 + 
																	"." + 
																	version_id_3 )));
		} catch (TException e) {
			fail("TExeption :" + e.getMessage());
		}
	}
	
	@Test
	public void checkCompatibilityDownInvalidTest() {
		
		int version_id_1 = ApiProcessor.API_SERVER_VERSION[0] - 1000;
		int version_id_2 = ApiProcessor.API_SERVER_VERSION[1] - 500;
		int version_id_3 = ApiProcessor.API_SERVER_VERSION[2] - 2000;
		
		try {
			assertFalse(apiprocessor.checkCompatibility(new String( version_id_1 + 
																	"." + 
																	version_id_2 + 
																	"." + 
																	version_id_3 )));
		} catch (TException e) {
			fail("TExeption :" + e.getMessage());
		}
	}
	
	@Test
	public void checkCompatibilityInvalidFormatTest() {
		
		try {
			assertFalse(apiprocessor.checkCompatibility(new String( "abc.abc" )));
		} catch (TException e) {
			fail("TExeption :" + e.getMessage());
		}
	}
	
	@Test
	public void getJsonTest() {
		
		try {
			
			Data data = CreateTestData.getData();
			Gson gson = new Gson();
			String json = gson.toJson(data);
			
			assertEquals(json, apiprocessor.getEnvironmentDataJson());
			
		} catch (TException e) {
			fail("TExeption :" + e.getMessage());
		}
	}
	
}
