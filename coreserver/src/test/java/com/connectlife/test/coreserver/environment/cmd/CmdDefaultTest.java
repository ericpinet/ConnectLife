package com.connectlife.test.coreserver.environment.cmd;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import com.connectlife.coreserver.environment.EnvironmentContext;
import com.connectlife.coreserver.environment.cmd.CmdAddAccessory;
import com.connectlife.coreserver.environment.cmd.CmdDefault;
import com.connectlife.coreserver.environment.cmd.CmdFactory;

public class CmdDefaultTest {

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
	public void testInvalidContext() {
		EnvironmentContext context = Mockito.mock(EnvironmentContext.class);
		
		CmdAddAccessory cmd = CmdFactory.getCmdAddAccesssory(null, null);
		cmd.setContext(context);

		try {
			cmd.validContext();
			fail();
		} catch (Exception e) {
			assertNotNull(e);
		}
	}
	
	@Test
	public void testValidContext() {
		EnvironmentContext context = Mockito.mock(EnvironmentContext.class);
		
		CmdAddAccessory cmd = CmdFactory.getCmdAddAccesssory(null, null);
		cmd.setContext(context);
		
		Mockito.when(context.isInit()).thenReturn(true);

		try {
			cmd.validContext();
		
		} catch (Exception e) {
			fail();
		}
	}
	
	@Test
	public void testNullContext() {
		
		CmdAddAccessory cmd = CmdFactory.getCmdAddAccesssory(null, null);
		cmd.setContext(null);

		try {
			cmd.validContext();
			fail();
		} catch (Exception e) {
			assertNotNull(e);
		}
	}
	
	@Test
	public void testExecute() {
		
		CmdDefault cmd = Mockito.mock(CmdDefault.class, Mockito.CALLS_REAL_METHODS);
		
		try {
			cmd.execute();
			fail();
		} catch (Exception e) {
			assertNotNull(e);
		}
	}
	
	@Test
	public void testIsDataChanged() {
		
		CmdAddAccessory cmd = CmdFactory.getCmdAddAccesssory(null, null);
		cmd.setContext(null);

		assertFalse(cmd.isDataChanged());
	}

}
