/**
 *  AssetHttpHandlerTest.java
 *  coreserver
 *
 *  Created by ericpinet on 2016-09-09.
 *  Copyright (c) 2016 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.connectlife.test.coreserver.environment.asset;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import com.connectlife.coreserver.environment.asset.AssetHttpHandler;
import com.connectlife.coreserver.environment.asset.AssetMngr;


/**
 * 
 * 
 * @author ericpinet
 * <br> 2016-09-09
 */
public class AssetHttpHandlerTest {

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
	public void testUpdate() {
		
		AssetMngr manager = Mockito.mock(AssetMngr.class);
		
		Request base_request = Mockito.mock(Request.class);
		HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
		HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
		
		AssetHttpHandler http = new AssetHttpHandler(manager);
		
		try {
			http.handle("", base_request, request, response);
			
			
		} catch (IOException e) {
			
		} catch (ServletException e) {

		}
	}
}
