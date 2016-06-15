/**
 *  ApplicationTest.java
 *  coreserver
 *
 *  Created by ericpinet on 2015-10-10.
 *  Copyright (c) 2015 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.connectlife.test.coreserver;


// external
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

// internal
import com.connectlife.coreserver.Application;
import com.google.inject.Guice;
import com.google.inject.Injector;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Application.class)
@PowerMockIgnore("javax.management.*")

/**
 * 
 * 
 * @author ericpinet
 * <br> 2015-10-10
 */
public class ApplicationTest {

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
	public void accessorTest() {
		Injector injector = Guice.createInjector(new ApplicationInjectTest());
		final Application app = injector.getInstance(Application.class);
		
		assertNotNull(app.getApi());
		assertNull(app.getBasePath());
		assertNotNull(app.getConfig());
		assertNotNull(app.getConsole());
		assertNotNull(app.getEnvironment());
	}
	
	@Test
	public void startupTest() {
		Injector injector = Guice.createInjector(new ApplicationInjectTest());
		final Application app = injector.getInstance(Application.class);
		
		Thread test_thread = new Thread(new Runnable() {
	         public void run()
	         {
	        	 app.startup();
	         }
		});
		test_thread.start();
		
		int maxtry = 100;
		int trying = 0;
		while(app.isRunning() == false && trying < maxtry){
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {}
			trying++;
		}
		
		assertTrue(app.isRunning());
	}
	
	@Test
	public void startupFailTest() {
		Injector injector = Guice.createInjector(new ApplicationInjectTest());
		final Application app = injector.getInstance(Application.class);
		
		Thread test_thread = new Thread(new Runnable() {
	         public void run()
	         {
	        	 app.startup();
	         }
		});
		test_thread.start();
		
		int maxtry = 100;
		int trying = 0;
		while(app.isRunning() == false && trying < maxtry){
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {}
			trying++;
		}
		
		assertTrue(app.isRunning());
	}
	
	@Test
	public void shutdownTest() {
		Injector injector = Guice.createInjector(new ApplicationInjectTest());
		final Application app = injector.getInstance(Application.class);
		
		Thread test_thread = new Thread(new Runnable() {
	         public void run()
	         {
	        	 app.startup();
	         }
		});
		test_thread.start();
		
		int maxtry = 100;
		int trying = 0;
		while(app.isRunning() == false && trying < maxtry){
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {}
			trying++;
		}
		
		Thread test2_thread = new Thread(new Runnable() {
	         public void run()
	         {
	        	 app.shutdown();
	         }
		});
		test2_thread.start();
		
		maxtry = 100;
		trying = 0;
		while(app.isRunning() == true && trying < maxtry){
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {}
			trying++;
		}
		
		assertFalse(app.isRunning());
	}

	@Test
	public void basePathTest() {
		Injector injector = Guice.createInjector(new ApplicationInjectTest());
		final Application app = injector.getInstance(Application.class);
		
		Thread test_thread = new Thread(new Runnable() {
	         public void run()
	         {
	        	 app.startup();
	         }
		});
		test_thread.start();
		
		int maxtry = 100;
		int trying = 0;
		while(app.isRunning() == false && trying < maxtry){
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {}
			trying++;
		}
		
		assertTrue(app.isRunning());
		
		assertNotNull(app.getBasePath());
	}
	
	@Test
	public void startupTwiceTest() {
		Injector injector = Guice.createInjector(new ApplicationInjectTest());
		final Application app = injector.getInstance(Application.class);
		
		Thread test_thread = new Thread(new Runnable() {
	         public void run()
	         {
	        	 app.startup();
	         }
		});
		test_thread.start();
		
		int maxtry = 100;
		int trying = 0;
		while(app.isRunning() == false && trying < maxtry){
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {}
			trying++;
		}
		
		Thread test2_thread = new Thread(new Runnable() {
	         public void run()
	         {
	        	 app.startup();
	         }
		});
		test2_thread.start();
		
		int maxtry2 = 100;
		int trying2 = 0;
		while(app.isRunning() == true && trying2 < maxtry2){
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {}
			trying2++;
		}
		
		assertTrue(app.isRunning());
	}
}
