/**
 *  CmdDelAccessoryTest.java
 *  coreserver
 *
 *  Created by ericpinet on 2016-06-25.
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
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.clapi.data.Accessory;
import com.connectlife.coreserver.Consts;
import com.connectlife.coreserver.environment.EnvironmentContext;
import com.connectlife.coreserver.environment.cmd.CmdDeleteAccessory;
import com.connectlife.coreserver.environment.cmd.CmdFactory;
import com.connectlife.coreserver.environment.data.DataManager;
import com.connectlife.coreserver.environment.data.DataManagerNodeFactory;
import com.connectlife.coreserver.environment.device.DeviceManager;

@RunWith(PowerMockRunner.class)
@PrepareForTest(DataManagerNodeFactory.class)
@PowerMockIgnore("javax.management.*")
/**
 * 
 * 
 * @author ericpinet
 * <br> 2016-06-25
 */
public class CmdDelAccessoryTest {

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
		
		CmdDeleteAccessory cmd = CmdFactory.getCmdDeleteAccesssory(null);
		cmd.setContext(context);

		try {
			cmd.execute();
			fail();
		} catch (Exception e) {
			assertNotNull(e);
		}
	}
	
	/**
	 * Test without uid set in accessory already added
	 */
	@Test
	public void testComplete() {
		
		EnvironmentContext context = Mockito.mock(EnvironmentContext.class);
		DataManager datamanager = Mockito.mock(DataManager.class);
		DeviceManager devicemanager = Mockito.mock(DeviceManager.class);
		GraphDatabaseService graph = Mockito.mock(GraphDatabaseService.class);
		Node acc = Mockito.mock(Node.class);
		Transaction tx = Mockito.mock(Transaction.class);
		
		Accessory accessory = new Accessory("12345", "Label");
		
		try {
			PowerMockito.mockStatic(DataManagerNodeFactory.class);
			
			
			Mockito.when(context.getDataManager()).thenReturn(datamanager);
			Mockito.when(datamanager.getGraph()).thenReturn(graph);
			Mockito.when(graph.beginTx()).thenReturn(tx);
			Mockito.when(graph.findNode(Consts.LABEL_ACCESSORY, Consts.UID, "12345")).thenReturn(acc);
			Mockito.when(DataManagerNodeFactory.deleteNodeWithChildren(Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(true);
			Mockito.when(acc.getProperty(Consts.UID)).thenReturn("12345");
			Mockito.when(context.getDeviceManager()).thenReturn(devicemanager);
			Mockito.doNothing().when(devicemanager).forceSynchronizationOfAllDevices();
			
			Mockito.doNothing().when(tx).success();
			
		} catch (Exception e) {
			fail();
		}

		CmdDeleteAccessory cmd = CmdFactory.getCmdDeleteAccesssory(accessory);
		cmd.setContext(context);

		try {
			cmd.execute();
			
		} catch (Exception e) {
			fail();
		}
	}
}
