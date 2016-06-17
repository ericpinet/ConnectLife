/**
 *  CmdUnregisterAccessoryTest.java
 *  coreserver
 *
 *  Created by ericpinet on 2016-06-16.
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
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Transaction;

import com.clapi.data.Accessory;
import com.connectlife.coreserver.Consts;
import com.connectlife.coreserver.environment.EnvironmentContext;
import com.connectlife.coreserver.environment.cmd.CmdFactory;
import com.connectlife.coreserver.environment.cmd.CmdUnregisterAccessory;
import com.connectlife.coreserver.environment.data.DataManager;

/**
 * 
 * 
 * @author ericpinet
 * <br> 2016-06-16
 */
public class CmdUnregisterAccessoryTest {

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
	public void testNullAccessory() {
		
		CmdUnregisterAccessory cmd = CmdFactory.getCmdUnregisterAccesssory(null);

		try {
			cmd.execute();
			fail();
		} catch (Exception e) {
			assertNotNull(e);
		}
	}
	
	@Test
	public void testDontFindAccessory() {
		
		EnvironmentContext context = Mockito.mock(EnvironmentContext.class);
		DataManager datamanager = Mockito.mock(DataManager.class);
		GraphDatabaseService graph = Mockito.mock(GraphDatabaseService.class);
		Transaction tx = Mockito.mock(Transaction.class);
		
		Accessory accessory = new Accessory(null, null, null, null, "12345", null, null, null, null);
		
		try {
			Mockito.when(context.getDataManager()).thenReturn(datamanager);
			Mockito.when(graph.findNode(Consts.LABEL_ACCESSORY, Consts.ACCESSORY_SERIALNUMBER, accessory.getSerialnumber())).thenReturn(null);
			Mockito.when(graph.beginTx()).thenReturn(tx);
			Mockito.when(datamanager.getGraph()).thenReturn(graph);
			Mockito.doNothing().when(tx).success();
			
		} catch (Exception e1) {
			fail();
		}
		
		CmdUnregisterAccessory cmd = CmdFactory.getCmdUnregisterAccesssory(accessory);
		cmd.setContext(context);
		
		try {
			cmd.execute();
			fail();
		} catch (Exception e) {
			assertNotNull(e);
		}
	}
	
	@Test
	public void testDontFind() {
		
		EnvironmentContext context = Mockito.mock(EnvironmentContext.class);
		DataManager datamanager = Mockito.mock(DataManager.class);
		GraphDatabaseService graph = Mockito.mock(GraphDatabaseService.class);
		Transaction tx = Mockito.mock(Transaction.class);
		
		Accessory accessory = new Accessory(null, null, null, null, "12345", null, null, null, null);
		
		try {
			Mockito.when(context.getDataManager()).thenReturn(datamanager);
			Mockito.when(graph.findNode(Consts.LABEL_ACCESSORY, Consts.ACCESSORY_SERIALNUMBER, accessory.getSerialnumber())).thenReturn(null);
			Mockito.when(graph.beginTx()).thenReturn(tx);
			Mockito.when(datamanager.getGraph()).thenReturn(graph);
			Mockito.doNothing().when(tx).success();
			
		} catch (Exception e1) {
			fail();
		}
		
		CmdUnregisterAccessory cmd = CmdFactory.getCmdUnregisterAccesssory(accessory);
		cmd.setContext(context);
		
		try {
			cmd.execute();
			fail();
		} catch (Exception e) {
			assertNotNull(e);
		}
	}

}
