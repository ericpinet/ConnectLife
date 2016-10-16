/**
 *  DiscoveryJmdnsTest.java
 *  coreserver
 *
 *  Created by ericpinet on 2016-04-06.
 *  Copyright (c) 2016 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.connectlife.test.coreserver.environment;

import java.io.IOException;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceEvent;

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

import com.connectlife.coreserver.environment.discover.DiscoveryJmdns;
import com.connectlife.coreserver.environment.discover.DiscoveryListner;

@RunWith(PowerMockRunner.class)
@PrepareForTest(JmDNS.class)
@PowerMockIgnore("javax.management.*")

/**
 * 
 * 
 * @author ericpinet
 * <br> 2016-04-06
 */
public class DiscoveryJmdnsTest implements DiscoveryListner {

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
	 * Test a normal start
	 */
	@Test
	public void testNormalStart() {
		
		PowerMockito.mockStatic(JmDNS.class);
		JmDNS mock = Mockito.mock(JmDNS.class);
		try {
			Mockito.when(JmDNS.create()).thenReturn(mock);
		} catch (IOException e) {
		}
		
		Mockito.doNothing().when(mock).addServiceListener(Mockito.anyString(), Mockito.any());
		
		DiscoveryJmdns dis = new DiscoveryJmdns();
		dis.start();
		
		Mockito.verify(mock, Mockito.atLeastOnce());
	}
	
	/**
	 * Test a start fail with exception
	 */
	@Test
	public void testExceptionStart() {
		
		PowerMockito.mockStatic(JmDNS.class);
		
		try {
			Mockito.when(JmDNS.create()).thenThrow(new IOException("IO Error"));
		} catch (IOException e) {
		}
				
		DiscoveryJmdns dis = new DiscoveryJmdns();
		dis.start();

	}
	
	/**
	 * Test 2 start
	 */
	@Test
	public void test2Start() {
		
		PowerMockito.mockStatic(JmDNS.class);
		JmDNS mock = Mockito.mock(JmDNS.class);
		try {
			Mockito.when(JmDNS.create()).thenReturn(mock);
		} catch (IOException e) {
		}
		
		Mockito.doNothing().when(mock).addServiceListener(Mockito.anyString(), Mockito.any());
		
		DiscoveryJmdns dis = new DiscoveryJmdns();
		dis.start();
		
		dis.start();
	}
	
	/**
	 * Test stop without start
	 */
	@Test
	public void testStop() {
		DiscoveryJmdns dis = new DiscoveryJmdns();
		dis.stop();
	}
	
	/**
	 * Test stop with excepotion on stop
	 */
	@Test
	public void testStopException() {
		
		PowerMockito.mockStatic(JmDNS.class);
		JmDNS mock = Mockito.mock(JmDNS.class);
		try {
			Mockito.when(JmDNS.create()).thenReturn(mock);
			Mockito.doThrow(new IOException("IO Error")).when(mock).close();
		} catch (IOException e) {
		}
		
		Mockito.doNothing().when(mock).addServiceListener(Mockito.anyString(), Mockito.any());		
		DiscoveryJmdns dis = new DiscoveryJmdns();
		dis.start();
		
		dis.stop();
	}
	
	/**
	 * Test stop after good start
	 */
	@Test
	public void testStartStop() {
		
		PowerMockito.mockStatic(JmDNS.class);
		JmDNS mock = Mockito.mock(JmDNS.class);
		try {
			Mockito.when(JmDNS.create()).thenReturn(mock);
			Mockito.doNothing().when(mock).close();
		} catch (IOException e) {
		}
		
		Mockito.doNothing().when(mock).addServiceListener(Mockito.anyString(), Mockito.any());		
		DiscoveryJmdns dis = new DiscoveryJmdns();
		dis.start();
		
		dis.stop();
	}
	
	/**
	 * Add valid listener
	 */
	@Test
	public void testaddListner() {
		DiscoveryJmdns dis = new DiscoveryJmdns();
		dis.addListner(this);
	}
	
	/**
	 * Add invalid listener
	 */
	@Test
	public void testaddListnerNull() {
		DiscoveryJmdns dis = new DiscoveryJmdns();
		dis.addListner(null);
	}
	

	/**
	 * @param _service
	 * @see com.connectlife.coreserver.environment.discover.DiscoveryListner#serviceDiscover(javax.jmdns.ServiceEvent)
	 */
	@Override
	public void serviceDiscover(ServiceEvent _service) {
	}

	/**
	 * @param _service
	 * @see com.connectlife.coreserver.environment.discover.DiscoveryListner#serviceRemove(javax.jmdns.ServiceEvent)
	 */
	@Override
	public void serviceRemove(ServiceEvent _service) {
	}

}
