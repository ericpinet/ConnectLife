/**
 *  AssetHttpHandlerTest.java
 *  coreserver
 *
 *  Created by ericpinet on 2016-09-09.
 *  Copyright (c) 2016 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.connectlife.test.coreserver.environment.asset;

import static org.junit.Assert.*;

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
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.clapi.data.Asset;
import com.connectlife.coreserver.Application;
import com.connectlife.coreserver.environment.Environment;
import com.connectlife.coreserver.environment.asset.AssetHttpHandler;
import com.connectlife.coreserver.environment.asset.AssetMngr;
import com.connectlife.coreserver.environment.find.FindProcessor;


@RunWith(PowerMockRunner.class)
@PrepareForTest(Application.class)
@PowerMockIgnore("javax.management.*")
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
	public void testHandle() {
		
		PowerMockito.spy(Application.class);
		
		AssetMngr manager = Mockito.mock(AssetMngr.class);
		
		Request base_request = Mockito.mock(Request.class);
		HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
		HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
		FindProcessor find = Mockito.mock(FindProcessor.class);
		Asset asset = Mockito.mock(Asset.class);
		Application app = Mockito.mock(Application.class);
		Environment env = Mockito.mock(Environment.class);
		try {
			PowerMockito.doReturn(app).when(Application.class, "getApp");
			Mockito.when(app.getEnvironment()).thenReturn(env);
			Mockito.when(env.getFindProcessor()).thenReturn(find);
			Mockito.when(request.getParameter("uid")).thenReturn("1");
			Mockito.when(find.findAssetByUid("1")).thenReturn(asset);
			Mockito.when(manager.getAssetFullFilename(asset)).thenReturn("filename");
			
		} catch (Exception e1) {
			fail();
		}
		
		AssetHttpHandler http = new AssetHttpHandler(manager);
		try {
			http.handle("", base_request, request, response);
			
		} catch (IOException e) {
			fail();
			
		} catch (ServletException e) {
			fail();
		}
	}
}
