/**
 *  CmdAddPersonTest.java
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
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.clapi.data.Email;
import com.clapi.data.Person;
import com.clapi.data.Email.EmailType;
import com.connectlife.coreserver.environment.EnvironmentContext;
import com.connectlife.coreserver.environment.cmd.CmdAddEmail;
import com.connectlife.coreserver.environment.cmd.CmdFactory;
import com.connectlife.coreserver.environment.data.DataManager;
import com.connectlife.coreserver.environment.data.DataManagerNodeFactory;

@RunWith(PowerMockRunner.class)
@PrepareForTest(DataManagerNodeFactory.class)
@PowerMockIgnore("javax.management.*")

/**
 * 
 * 
 * @author ericpinet
 * <br> 2016-06-16
 */
public class CmdAddEmailTest {

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
	public void testNullPerson() {
		
		CmdAddEmail cmd = CmdFactory.getCmdAddEmail(new Email(null, null, EmailType.PERSONAL), 
													null);

		try {
			cmd.execute();
			fail();
		} catch (Exception e) {
			assertNotNull(e);
		}
	}
	
	@Test
	public void testNullEmail() {
		
		CmdAddEmail cmd = CmdFactory.getCmdAddEmail(null, 
													new Person("12345", ""));

		try {
			cmd.execute();
			fail();
		} catch (Exception e) {
			assertNotNull(e);
		}
	}
	
	@Test
	public void testWithUidEmail() {
		
		CmdAddEmail cmd = CmdFactory.getCmdAddEmail(new Email("123", null, EmailType.PERSONAL),
													new Person("12345", ""));

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
		PowerMockito.mockStatic(DataManagerNodeFactory.class);
		Transaction tx = Mockito.mock(Transaction.class);
		Node node = Mockito.mock(Node.class);
		Node node_person = Mockito.mock(Node.class);
		
		try {			
			Mockito.when(context.getDataManager()).thenReturn(datamanager);
			Mockito.when(datamanager.getGraph()).thenReturn(graph);			
			Mockito.when(graph.beginTx()).thenReturn(tx);
			Mockito.when(graph.findNode(Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(node_person);
			Mockito.when(DataManagerNodeFactory.buildEmailNode(Mockito.any(), Mockito.any())).thenReturn(node);
			
		} catch (Exception e2) {
			fail();
		}
		
		CmdAddEmail cmd = CmdFactory.getCmdAddEmail(new Email("", null, EmailType.PERSONAL), 
													new Person("12345","","",""));
		cmd.setContext(context);
		try {
			cmd.execute();
			
		} catch (Exception e) {
			fail();
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
			Mockito.when(context.getDataManager()).thenReturn(datamanager);
			Mockito.when(datamanager.getGraph()).thenReturn(graph);			
			Mockito.when(graph.beginTx()).thenReturn(tx);
			Mockito.when(graph.findNode(Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(null);
			
		} catch (Exception e2) {
			fail();
		}
		
		CmdAddEmail cmd = CmdFactory.getCmdAddEmail(new Email("", null, EmailType.PERSONAL), 
													new Person("12345","","",""));
		cmd.setContext(context);
		try {
			cmd.execute();
			fail();
			
		} catch (Exception e) {
			assertNotNull(e);
		}
	}
	
	

}
