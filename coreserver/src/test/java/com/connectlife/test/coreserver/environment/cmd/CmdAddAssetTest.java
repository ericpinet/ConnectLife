/**
 *  CmdAddAssetTest.java
 *  coreserver
 *
 *  Created by ericpinet on 2016-10-04.
 *  Copyright (c) 2016 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.connectlife.test.coreserver.environment.cmd;

import static org.junit.Assert.*;

import java.io.IOException;

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
import com.connectlife.coreserver.environment.cmd.CmdAddAsset;
import com.connectlife.coreserver.environment.cmd.CmdFactory;
import com.connectlife.coreserver.environment.data.DataManager;
import com.connectlife.coreserver.environment.data.DataManagerNodeFactory;
import com.google.protobuf.ByteString;

@RunWith(PowerMockRunner.class)
@PrepareForTest(DataManagerNodeFactory.class)
@PowerMockIgnore("javax.management.*")

/**
 * 
 * 
 * @author ericpinet
 * <br> 2016-06-16
 */
public class CmdAddAssetTest {

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
	public void testNullAsset() {
		
		CmdAddAsset cmd = CmdFactory.getCmdAddAsset(null, null);

		try {
			cmd.execute();
			fail();
		} catch (Exception e) {
			assertNotNull(e);
		}
	}
	
	@Test
	public void testNullData() {
		
		CmdAddAsset cmd = CmdFactory.getCmdAddAsset(new Asset("", "", AssetType.IMAGE, AssetMode.SYSTEM), 
													null);

		try {
			cmd.execute();
			fail();
		} catch (Exception e) {
			assertNotNull(e);
		}
	}
	
	@Test
	public void testWithUidAsset() {
		
		CmdAddAsset cmd = CmdFactory.getCmdAddAsset(new Asset("12345", "", AssetType.IMAGE, AssetMode.SYSTEM), 
													ByteString.EMPTY);

		try {
			cmd.execute();
			fail();
		} catch (Exception e) {
			assertNotNull(e);
		}
	}
	
	@Test
	public void testWithoutLabelAsset() {
		
		CmdAddAsset cmd = CmdFactory.getCmdAddAsset(new Asset("", "", AssetType.IMAGE, AssetMode.SYSTEM), 
													ByteString.EMPTY);

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
		GraphDatabaseService graph = Mockito.mock(GraphDatabaseService.class);
		AssetManager asset_manager = Mockito.mock(AssetManager.class);
		PowerMockito.mockStatic(DataManagerNodeFactory.class);
		Transaction tx = Mockito.mock(Transaction.class);
		Node node = Mockito.mock(Node.class);
		
		try {			
			Mockito.when(context.getDataManager()).thenReturn(datamanager);
			Mockito.when(datamanager.getGraph()).thenReturn(graph);
			Mockito.when(context.getAssetManager()).thenReturn(asset_manager);
			Mockito.when(graph.beginTx()).thenReturn(tx);
			Mockito.when(DataManagerNodeFactory.buildAssetNode(Mockito.any(), Mockito.any())).thenReturn(node);
			Mockito.doNothing().when(asset_manager).addAsset(Mockito.any(), Mockito.any());
			
		} catch (Exception e2) {
			fail();
		}
		
		CmdAddAsset cmd = CmdFactory.getCmdAddAsset(new Asset("", "test", AssetType.IMAGE, AssetMode.SYSTEM), 
													ByteString.EMPTY);
		cmd.setContext(context);
		try {
			cmd.execute();
			
		} catch (Exception e) {
			fail();
		}
	}
	
	@Test
	public void testSaveFailed() {
		
		EnvironmentContext context = Mockito.mock(EnvironmentContext.class);
		DataManager datamanager = Mockito.mock(DataManager.class);
		GraphDatabaseService graph = Mockito.mock(GraphDatabaseService.class);
		AssetManager asset_manager = Mockito.mock(AssetManager.class);
		PowerMockito.mockStatic(DataManagerNodeFactory.class);
		Transaction tx = Mockito.mock(Transaction.class);
		Node node = Mockito.mock(Node.class);
		
		try {			
			Mockito.when(context.getDataManager()).thenReturn(datamanager);
			Mockito.when(datamanager.getGraph()).thenReturn(graph);
			Mockito.when(context.getAssetManager()).thenReturn(asset_manager);
			Mockito.when(graph.beginTx()).thenReturn(tx);
			Mockito.when(DataManagerNodeFactory.buildAssetNode(Mockito.any(), Mockito.any())).thenReturn(node);
			Mockito.doThrow(new IOException()).when(asset_manager).addAsset(Mockito.any(), Mockito.any());
			
		} catch (Exception e2) {
			fail();
		}
		
		CmdAddAsset cmd = CmdFactory.getCmdAddAsset(new Asset("", "test", AssetType.IMAGE, AssetMode.SYSTEM), 
													ByteString.EMPTY);
		cmd.setContext(context);
		try {
			cmd.execute();
			fail();
		} catch (Exception e) {
			assertNotNull(e);
		}
	}
}
