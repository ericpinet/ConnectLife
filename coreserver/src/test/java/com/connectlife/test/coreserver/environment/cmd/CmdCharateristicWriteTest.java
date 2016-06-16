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

import com.clapi.data.Characteristic;
import com.clapi.data.Characteristic.CharacteristicType;
import com.connectlife.coreserver.Consts;
import com.connectlife.coreserver.environment.EnvironmentContext;
import com.connectlife.coreserver.environment.cmd.CmdCharacteristicWrite;
import com.connectlife.coreserver.environment.cmd.CmdFactory;
import com.connectlife.coreserver.environment.data.DataManager;
import com.connectlife.coreserver.environment.data.DataManagerFactory;

@RunWith(PowerMockRunner.class)
@PrepareForTest(DataManagerFactory.class)
@PowerMockIgnore("javax.management.*")

public class CmdCharateristicWriteTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testNullCharacteristic() {
		
		EnvironmentContext context = Mockito.mock(EnvironmentContext.class);
		
		CmdCharacteristicWrite cmd = CmdFactory.getCmdCharacteristicWrite(null, null);
		cmd.setContext(context);

		try {
			cmd.execute();
			fail();
		} catch (Exception e) {
			assertNotNull(e);
		}
	}
	
	@Test
	public void testNullTargetValue() {
		
		EnvironmentContext context = Mockito.mock(EnvironmentContext.class);
		
		Characteristic characteristic = new Characteristic(null, null, null, null, null, null);
		CmdCharacteristicWrite cmd = CmdFactory.getCmdCharacteristicWrite(characteristic, null);
		cmd.setContext(context);

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
		
		Mockito.when(context.getDataManager()).thenReturn(datamanager);
		Mockito.when(graph.findNode(Consts.LABEL_CHARACTERISTIC, Consts.UID, "12345")).thenReturn(null);
		Mockito.when(graph.beginTx()).thenReturn(tx);
		try {
			Mockito.when(datamanager.getGraph()).thenReturn(graph);
		} catch (Exception e1) {
			fail();
		}
		
		Mockito.doNothing().when(tx).success();
		
		Characteristic characteristic = new Characteristic(null, null, null, null, null, null);
		CmdCharacteristicWrite cmd = CmdFactory.getCmdCharacteristicWrite(characteristic, null);
		cmd.setContext(context);
		
		try {
			cmd.execute();
			fail();
		} catch (Exception e) {
			assertNotNull(e);
		}
	}
	
	@Test
	public void testFindAccessoryReadOnly() {
		
		EnvironmentContext context = Mockito.mock(EnvironmentContext.class);
		
		DataManager datamanager = Mockito.mock(DataManager.class);
		GraphDatabaseService graph = Mockito.mock(GraphDatabaseService.class);
		Node charac = Mockito.mock(Node.class);
		Transaction tx = Mockito.mock(Transaction.class);
		
		Mockito.when(context.getDataManager()).thenReturn(datamanager);
		Mockito.when(graph.findNode(Consts.LABEL_CHARACTERISTIC, Consts.UID, null)).thenReturn(charac);
		Mockito.when(graph.beginTx()).thenReturn(tx);
		try {
			Mockito.when(datamanager.getGraph()).thenReturn(graph);
		} catch (Exception e1) {
			fail();
		}
		
		Mockito.when(charac.getProperty(Consts.CH_MODE)).thenReturn(Consts.CH_ACCESS_MODE_READ_ONLY);
		
		Mockito.doNothing().when(tx).success();
		
		Characteristic characteristic = new Characteristic(null, null, null, null, null, null);
		CmdCharacteristicWrite cmd = CmdFactory.getCmdCharacteristicWrite(characteristic, null);
		cmd.setContext(context);
		
		try {
			cmd.execute();
			fail();
		} catch (Exception e) {
			assertNotNull(e);
		}
	}
	
	@Test
	public void testFindAccessoryBoolean() {
		
		EnvironmentContext context = Mockito.mock(EnvironmentContext.class);
		
		DataManager datamanager = Mockito.mock(DataManager.class);
		GraphDatabaseService graph = Mockito.mock(GraphDatabaseService.class);
		Node charac = Mockito.mock(Node.class);
		Transaction tx = Mockito.mock(Transaction.class);
		
		PowerMockito.mockStatic(DataManagerFactory.class);
		
		Mockito.when(context.getDataManager()).thenReturn(datamanager);
		Mockito.when(graph.findNode(Consts.LABEL_CHARACTERISTIC, Consts.UID, null)).thenReturn(charac);
		Mockito.when(graph.beginTx()).thenReturn(tx);
		try {
			Mockito.when(datamanager.getGraph()).thenReturn(graph);
		} catch (Exception e1) {
			fail();
		}
		
		Mockito.when(charac.getProperty(Consts.CH_MODE)).thenReturn(Consts.CH_ACCESS_MODE_READ_WRITE);
		
		
		Mockito.doNothing().when(tx).success();
		
		Characteristic characteristic = new Characteristic(null, null, null, CharacteristicType.BOOLEAN, null, "false");
		Characteristic target_value = new Characteristic(null, null, null, CharacteristicType.BOOLEAN, null, "true");
		
		try {
			Mockito.when(DataManagerFactory.buildCharacteristic(Mockito.any())).thenReturn(characteristic);
		} catch (Exception e2) {
			fail();
		}
		
		CmdCharacteristicWrite cmd = CmdFactory.getCmdCharacteristicWrite(characteristic, target_value);
		cmd.setContext(context);
		
		try {
			cmd.execute();
			
		} catch (Exception e) {
			fail();
		}
	}

}
