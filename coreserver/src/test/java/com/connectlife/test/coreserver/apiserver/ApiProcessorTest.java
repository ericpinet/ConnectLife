/**
 *  ApiProcessorTest.java
 *  coreserver
 *
 *  Created by ericpinet on 2016-02-20.
 *  Copyright (c) 2016 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.connectlife.test.coreserver.apiserver;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.clapi.protocol.CheckCompatibilityRequest;
import com.clapi.protocol.CheckCompatibilityResponse;
import com.clapi.protocol.GetVersionRequest;
import com.clapi.protocol.GetVersionResponse;
import com.connectlife.coreserver.Application;
import com.connectlife.coreserver.Consts;
import com.connectlife.coreserver.apiserver.ApiProcessor;
import com.connectlife.test.coreserver.ApplicationInjectTest;
import com.google.inject.Guice;
import com.google.inject.Injector;

import io.grpc.stub.StreamObserver;

/**
 * 
 * 
 * @author ericpinet
 * <br> 2016-02-20
 */
public class ApiProcessorTest {

	/**
	 * ApiProcessor instance for test
	 */
	ApiProcessor api;
	
	/**
	 * Application instance for test.
	 */
	Application app;
	
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
	public void testGetVersion() {
		// Api
		Injector injector = Guice.createInjector(new ApiProcessorInjectTest());
		api = injector.getInstance(ApiProcessor.class);
		
		// App
		Injector injector_app = Guice.createInjector(new ApplicationInjectTest());
		app = injector_app.getInstance(Application.class);
		
		// Response
		StreamObserver<GetVersionResponse> so = new StreamObserver<GetVersionResponse>() {
            @Override
            public void onNext(GetVersionResponse response) {}
            @Override
            public void onError(Throwable t) {}
            @Override
            public void onCompleted() {}
        };
        
        // Api test
        api.getVersion(GetVersionRequest.newBuilder().build(), so);		
	}
	
	@Test
	public void testCheckCompatibility() {
		// Api
		Injector injector = Guice.createInjector(new ApiProcessorInjectTest());
		api = injector.getInstance(ApiProcessor.class);
		
		// App
		Injector injector_app = Guice.createInjector(new ApplicationInjectTest());
		app = injector_app.getInstance(Application.class);
		
		// Response
		StreamObserver<CheckCompatibilityResponse> so = new StreamObserver<CheckCompatibilityResponse>() {
            @Override
            public void onNext(CheckCompatibilityResponse response) {}
            @Override
            public void onError(Throwable t) {}
            @Override
            public void onCompleted() {}
        };
        
        // Api test compatibility good
        api.checkCompatibility(CheckCompatibilityRequest.newBuilder().setVersion(Consts.APP_VERSION).build(), so);
        
        // Api test compatibility wrong
        api.checkCompatibility(CheckCompatibilityRequest.newBuilder().setVersion("0.0.0.0").build(), so);
	}

}
