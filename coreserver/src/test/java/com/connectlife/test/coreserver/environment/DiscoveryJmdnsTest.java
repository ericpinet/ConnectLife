/**
 *  DiscoveryJmdnsTest.java
 *  coreserver
 *
 *  Created by ericpinet on 2016-04-06.
 *  Copyright (c) 2016 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.connectlife.test.coreserver.environment;

import javax.jmdns.ServiceEvent;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.connectlife.coreserver.environment.discover.DiscoveryJmdns;
import com.connectlife.coreserver.environment.discover.DiscoveryListner;

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

	@Test
	public void testStart() {
		DiscoveryJmdns dis = new DiscoveryJmdns();
		dis.start();
		dis.start();
	}
	
	@Test
	public void testStop() {
		DiscoveryJmdns dis = new DiscoveryJmdns();
		dis.stop();
	}
	
	@Test
	public void testStartStop() {
		DiscoveryJmdns dis = new DiscoveryJmdns();
		dis.start();
		dis.stop();
	}
	
	@Test
	public void testaddListner() {
		DiscoveryJmdns dis = new DiscoveryJmdns();
		dis.addListner(this);
	}

	/**
	 * @param _service
	 * @see com.connectlife.coreserver.environment.discover.DiscoveryListner#serviceDiscover(javax.jmdns.ServiceEvent)
	 */
	@Override
	public void serviceDiscover(ServiceEvent _service) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @param _service
	 * @see com.connectlife.coreserver.environment.discover.DiscoveryListner#serviceRemove(javax.jmdns.ServiceEvent)
	 */
	@Override
	public void serviceRemove(ServiceEvent _service) {
		// TODO Auto-generated method stub
		
	}

}
