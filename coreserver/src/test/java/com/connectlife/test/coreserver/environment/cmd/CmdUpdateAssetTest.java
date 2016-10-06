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

import com.clapi.data.Asset;
import com.clapi.data.Asset.AssetMode;
import com.clapi.data.Asset.AssetType;
import com.connectlife.coreserver.environment.EnvironmentContext;
import com.connectlife.coreserver.environment.asset.AssetManager;
import com.connectlife.coreserver.environment.cmd.CmdFactory;
import com.connectlife.coreserver.environment.cmd.CmdUpdateAsset;
import com.connectlife.coreserver.environment.data.DataManager;
import com.connectlife.coreserver.environment.data.DataManagerNodeFactory;
import com.google.protobuf.ByteString;

@RunWith(PowerMockRunner.class)
@PrepareForTest(DataManagerNodeFactory.class)
@PowerMockIgnore("javax.management.*")

public class CmdUpdateAssetTest {

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
	public void testNullAsset() {
		CmdUpdateAsset cmd = CmdFactory.getCmdUpdateAsset(null, null);
		try {
			cmd.execute();
			fail();
		} catch (Exception e) {
			assertNotNull(e);
		}
	}
	
	@Test
	public void testWithoutUidAsset() {
		CmdUpdateAsset cmd = CmdFactory.getCmdUpdateAsset(new Asset(null, "test", AssetType.IMAGE, AssetMode.USER), ByteString.EMPTY);
		try {
			cmd.execute();
			fail();
		} catch (Exception e) {
			assertNotNull(e);
		}
		
		CmdUpdateAsset cmd2 = CmdFactory.getCmdUpdateAsset(new Asset("", "test", AssetType.IMAGE, AssetMode.USER), ByteString.EMPTY);
		try {
			cmd2.execute();
			fail();
		} catch (Exception e) {
			assertNotNull(e);
		}
	}
	
	@Test
	public void testNotFound() {
		EnvironmentContext context = Mockito.mock(EnvironmentContext.class);
		DataManager datamanager = Mockito.mock(DataManager.class);
		GraphDatabaseService graph = Mockito.mock(GraphDatabaseService.class);
		PowerMockito.mockStatic(DataManagerNodeFactory.class);
		Transaction tx = Mockito.mock(Transaction.class);
		
		try {
			Mockito.when(datamanager.getGraph()).thenReturn(graph);
			Mockito.when(context.getDataManager()).thenReturn(datamanager);
			Mockito.when(graph.beginTx()).thenReturn(tx);
			Mockito.when(graph.findNode(Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(null);
			
		} catch (Exception e2) {
			fail();
		}
		
		CmdUpdateAsset cmd = CmdFactory.getCmdUpdateAsset(new Asset("12345","", AssetType.IMAGE, AssetMode.USER), ByteString.EMPTY);
		cmd.setContext(context);
		try {
			cmd.execute();
			fail();
		} catch (Exception e) {
			assertNotNull(e);
		}
	}
	
	@Test
	public void testComplete() {
		EnvironmentContext context = Mockito.mock(EnvironmentContext.class);
		DataManager datamanager = Mockito.mock(DataManager.class);
		AssetManager asset_manager = Mockito.mock(AssetManager.class);
		GraphDatabaseService graph = Mockito.mock(GraphDatabaseService.class);
		PowerMockito.mockStatic(DataManagerNodeFactory.class);
		Transaction tx = Mockito.mock(Transaction.class);
		Node node = Mockito.mock(Node.class);
		
		try {
			Mockito.when(datamanager.getGraph()).thenReturn(graph);
			Mockito.when(context.getDataManager()).thenReturn(datamanager);
			Mockito.when(context.getAssetManager()).thenReturn(asset_manager);
			Mockito.when(graph.beginTx()).thenReturn(tx);
			Mockito.when(graph.findNode(Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(node);
			
		} catch (Exception e2) {
			fail();
		}
		
		CmdUpdateAsset cmd = CmdFactory.getCmdUpdateAsset(new Asset("12345","test", AssetType.IMAGE, AssetMode.USER), ByteString.EMPTY);
		cmd.setContext(context);
		try {
			cmd.execute();
			
		} catch (Exception e) {
			fail();
		}
	}
}
