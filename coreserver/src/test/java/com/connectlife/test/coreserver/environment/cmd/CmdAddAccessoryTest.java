/**
 *  CmdAddAccessoryTest.java
 *  coreserver
 *
 *  Created by ericpinet on 2016-06-14.
 *  Copyright (c) 2016 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.connectlife.test.coreserver.environment.cmd;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.List;

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
import com.clapi.data.Room;
import com.connectlife.coreserver.Consts;
import com.connectlife.coreserver.environment.EnvironmentContext;
import com.connectlife.coreserver.environment.cmd.CmdAddAccessory;
import com.connectlife.coreserver.environment.cmd.CmdFactory;
import com.connectlife.coreserver.environment.data.DataManager;
import com.connectlife.coreserver.environment.data.DataManagerNodeFactory;
import com.connectlife.coreserver.environment.device.Device;
import com.connectlife.coreserver.environment.device.DeviceManager;

@RunWith(PowerMockRunner.class)
@PrepareForTest(DataManagerNodeFactory.class)
@PowerMockIgnore("javax.management.*")

/**
 * 
 * 
 * @author ericpinet
 * <br> 2016-06-14
 */
public class CmdAddAccessoryTest {

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
		
		CmdAddAccessory cmd = CmdFactory.getCmdAddAccesssory(null, null);
		cmd.setContext(context);

		try {
			cmd.execute();
			fail();
		} catch (Exception e) {
			assertNotNull(e);
		}
	}
	
	/**
	 * Test with uid set in accessory
	 */
	@Test
	public void testWithUIDAccessory() {
		
		EnvironmentContext context = Mockito.mock(EnvironmentContext.class);
		
		Accessory accessory = new Accessory("12345", "Label");
		
		CmdAddAccessory cmd = CmdFactory.getCmdAddAccesssory(accessory, null);
		cmd.setContext(context);

		try {
			cmd.execute();
			fail();
		} catch (Exception e) {
			assertNotNull(e);
		}
	}
	
	/**
	 * Test without uid set in accessory but room null
	 */
	@Test
	public void testWithoutUIDRoomNullAccessory() {
		
		EnvironmentContext context = Mockito.mock(EnvironmentContext.class);
		DataManager datamanager = Mockito.mock(DataManager.class);
		GraphDatabaseService service = Mockito.mock(GraphDatabaseService.class);

		Mockito.when(context.getDataManager()).thenReturn(datamanager);
		try {
			Mockito.when(datamanager.getGraph()).thenReturn(service);
		} catch (Exception e1) {
			fail();
		}
		
		Accessory accessory = new Accessory("", "Label");
		
		CmdAddAccessory cmd = CmdFactory.getCmdAddAccesssory(accessory, null);
		cmd.setContext(context);

		try {
			cmd.execute();
			fail();
		} catch (Exception e) {
			assertNotNull(e);
		}
	}
	
	/**
	 * Test without uid set in accessory and valid room (don't find room)
	 */
	@Test
	public void testWithoutUIDAccessoryDontFindRoom() {
		
		EnvironmentContext context = Mockito.mock(EnvironmentContext.class);
		DataManager datamanager = Mockito.mock(DataManager.class);
		GraphDatabaseService service = Mockito.mock(GraphDatabaseService.class);

		Mockito.when(context.getDataManager()).thenReturn(datamanager);
		try {
			Mockito.when(datamanager.getGraph()).thenReturn(service);
		} catch (Exception e1) {
			fail();
		}
		
		Accessory accessory = new Accessory("", "Label");
		Room room = new Room("12345", "Label");
		
		CmdAddAccessory cmd = CmdFactory.getCmdAddAccesssory(accessory, room);
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
	public void testWithoutUIDAccessoryAccessoryAlreadyAdded() {
		
		EnvironmentContext context = Mockito.mock(EnvironmentContext.class);
		DataManager datamanager = Mockito.mock(DataManager.class);
		GraphDatabaseService graph = Mockito.mock(GraphDatabaseService.class);
		Node acc = Mockito.mock(Node.class);

		Mockito.when(context.getDataManager()).thenReturn(datamanager);
		Mockito.when(graph.findNode(Consts.LABEL_ACCESSORY, Consts.ACCESSORY_SERIALNUMBER, "")).thenReturn(acc);
		try {
			Mockito.when(datamanager.getGraph()).thenReturn(graph);
		} catch (Exception e1) {
			fail();
		}
		
		Accessory accessory = new Accessory("", "Label");
		Room room = new Room("12345", "Label");
		
		CmdAddAccessory cmd = CmdFactory.getCmdAddAccesssory(accessory, room);
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
	public void testNotFound() {
		
		EnvironmentContext context = Mockito.mock(EnvironmentContext.class);
		DataManager datamanager = Mockito.mock(DataManager.class);
		DeviceManager devicemanager = Mockito.mock(DeviceManager.class);
		GraphDatabaseService graph = Mockito.mock(GraphDatabaseService.class);
		Node node = Mockito.mock(Node.class);
		Transaction tx = Mockito.mock(Transaction.class);
		@SuppressWarnings("unchecked")
		List<Device> devices = (List<Device>) Mockito.mock(List.class);
		@SuppressWarnings("unchecked")
		Iterator<Device> it = (Iterator<Device>) Mockito.mock(Iterator.class);
		
		try {
			PowerMockito.mockStatic(DataManagerNodeFactory.class);
			Mockito.when(context.getDataManager()).thenReturn(datamanager);
			Mockito.when(graph.findNode(Consts.LABEL_ROOM, Consts.UID, "12345")).thenReturn(node);
			Mockito.when(graph.beginTx()).thenReturn(tx);
			Mockito.when(datamanager.getGraph()).thenReturn(graph);
			Mockito.when(context.getDeviceManager()).thenReturn(devicemanager);
			Mockito.when(devicemanager.getDevices()).thenReturn(devices);
			Mockito.when(devices.iterator()).thenReturn(it);
			Mockito.when(it.hasNext()).thenReturn(false);
			Mockito.doNothing().when(tx).success();
			
		} catch (Exception e) {
			fail();
		}

		Accessory accessory = new Accessory("", "Label");
		Room room = new Room("12345", "Label");
		
		CmdAddAccessory cmd = CmdFactory.getCmdAddAccesssory(accessory, room);
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
		Node node = Mockito.mock(Node.class);
		Node acc = Mockito.mock(Node.class);
		Transaction tx = Mockito.mock(Transaction.class);
		@SuppressWarnings("unchecked")
		List<Device> devices = (List<Device>) Mockito.mock(List.class);
		@SuppressWarnings("unchecked")
		Iterator<Device> it = (Iterator<Device>) Mockito.mock(Iterator.class);
		Device device = Mockito.mock(Device.class);
		
		try {
			PowerMockito.mockStatic(DataManagerNodeFactory.class);
			Mockito.when(DataManagerNodeFactory.buildAccessoryNode(Mockito.any(), Mockito.any())).thenReturn(acc);
			Mockito.when(context.getDataManager()).thenReturn(datamanager);
			Mockito.when(graph.findNode(Consts.LABEL_ROOM, Consts.UID, "12345")).thenReturn(node);
			Mockito.when(graph.beginTx()).thenReturn(tx);
			Mockito.when(datamanager.getGraph()).thenReturn(graph);
			
			Mockito.when(context.getDeviceManager()).thenReturn(devicemanager);
			Mockito.when(devicemanager.getDevices()).thenReturn(devices);
			Mockito.when(devices.iterator()).thenReturn(it);
			
			Mockito.when(it.hasNext()).thenReturn(true);
			Mockito.when(it.next()).thenReturn(device);
			Mockito.when(device.getAccessory()).thenReturn(new Accessory(null, null));
			
			Mockito.doNothing().when(tx).success();
			
		} catch (Exception e) {
			fail();
		}

		Accessory accessory = new Accessory("", "Label");
		Room room = new Room("12345", "Label");
		
		CmdAddAccessory cmd = CmdFactory.getCmdAddAccesssory(accessory, room);
		cmd.setContext(context);

		try {
			cmd.execute();
			
		} catch (Exception e) {
			fail();
		}
	}
}
