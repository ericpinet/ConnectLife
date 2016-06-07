/**
 *  ConsoleSSHTest.java
 *  coreserver
 *
 *  Created by ericpinet on 2016-01-24.
 *  Copyright (c) 2016 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.connectlife.test.coreserver.console;

import static org.junit.Assert.*;

import java.io.InterruptedIOException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.connectlife.coreserver.Application;
import com.connectlife.coreserver.console.ConsoleSSH;
import com.connectlife.coreserver.console.ShellCmdExit;
import com.connectlife.coreserver.console.ShellCmdHelp;
import com.connectlife.coreserver.console.ShellCmdOutputAllConfig;
import com.connectlife.coreserver.console.ShellCmdOutputConfig;
import com.connectlife.coreserver.console.ShellCmdOutputEnv;
import com.connectlife.coreserver.console.ShellCmdOutputEnvFormatted;
import com.connectlife.coreserver.console.ShellCmdOutputLog;
import com.connectlife.coreserver.console.ShellCmdQuit;
import com.connectlife.coreserver.console.ShellCmdAddAccessory;
import com.connectlife.coreserver.console.ShellCmdRestoreFactoryConfig;
import com.connectlife.coreserver.console.ShellCmdSetConfig;
import com.connectlife.coreserver.console.ShellCmdShutdown;
import com.connectlife.coreserver.console.ShellCmdRemoveAccessory;
import com.connectlife.coreserver.console.ShellCmdVersion;
import com.connectlife.test.coreserver.ApplicationInjectTest;
import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * 
 * 
 * @author ericpinet
 * <br> 2016-01-24
 */
public class ConsoleSSHTest {

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
		
		Injector injector = Guice.createInjector(new ApplicationInjectTest());
		final Application app = injector.getInstance(Application.class);
		
		app.startupTest();
		
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testInit() {
		Injector injector = Guice.createInjector(new ConsoleInjectTest());
		ConsoleSSH console = injector.getInstance(ConsoleSSH.class);
		
		assertTrue(console.init());
				
		assertTrue(console.isInit());
	}
	
	@Test
	public void testInitTwice() {
		Injector injector = Guice.createInjector(new ConsoleInjectTest());
		ConsoleSSH console = injector.getInstance(ConsoleSSH.class);
		
		assertTrue(console.init());
		
		assertTrue(console.init());
		
		assertTrue(console.isInit());
	}
	
	@Test
	public void testUnInit() {
		Injector injector = Guice.createInjector(new ConsoleInjectTest());
		ConsoleSSH console = injector.getInstance(ConsoleSSH.class);
		
		assertTrue(console.init());
		
		console.unInit();
		
		assertFalse(console.isInit());
	}
	
	@Test
	public void testShellCmdExit(){
		
		ShellCmdExit cmd = new ShellCmdExit();		
		assertTrue(cmd.checkLineForCommandCompatibility("exit"));
		assertFalse(cmd.checkLineForCommandCompatibility("exit2"));
		
		cmd.getCommand();
		cmd.getHelp();
		
		try {
			@SuppressWarnings("unused")
			String response = cmd.execute("exit");
		} catch (InterruptedIOException e) {
			//do nothing.
		}
	}
	
	@Test
	public void testShellCmdHelp(){
		
		ShellCmdHelp cmd = new ShellCmdHelp();		
		assertTrue(cmd.checkLineForCommandCompatibility("help"));
		assertFalse(cmd.checkLineForCommandCompatibility("help2"));
		
		cmd.getCommand();
		cmd.getHelp();
		
		try {
			String response = cmd.execute("help");
			
			assertTrue(response.isEmpty() == false);
			
		} catch (InterruptedIOException e) {
			//do nothing.
		}
	}
	
	@Test
	public void testShellCmdOutputAllConfig(){
		
		ShellCmdOutputAllConfig cmd = new ShellCmdOutputAllConfig();		
		assertTrue(cmd.checkLineForCommandCompatibility("output all config"));
		assertFalse(cmd.checkLineForCommandCompatibility("output all config2"));
		
		cmd.getCommand();
		cmd.getHelp();
		
		try {
			String response = cmd.execute("output all config");
			
			assertTrue(response != null);
			
		} catch (InterruptedIOException e) {
			//do nothing.
		}
	}
	
	@Test
	public void testShellCmdOutputConfig(){
		
		ShellCmdOutputConfig cmd = new ShellCmdOutputConfig();		
		assertTrue(cmd.checkLineForCommandCompatibility("output config [API_SERVER][TCPIP_PORT]"));
		assertFalse(cmd.checkLineForCommandCompatibility("output confi [API_SERVER][TCPIP_PORT]"));
		
		cmd.getCommand();
		cmd.getHelp();
		
		try {
			String response = cmd.execute("output config [API_SERVER][TCPIP_PORT]");
			
			assertTrue(response != null);
			
		} catch (InterruptedIOException e) {
			//do nothing.
		}
	}
	
	@Test
	public void testShellCmdOutputEnv(){
		
		ShellCmdOutputEnv cmd = new ShellCmdOutputEnv();		
		assertTrue(cmd.checkLineForCommandCompatibility("output env"));
		assertFalse(cmd.checkLineForCommandCompatibility("output env 2"));
		
		cmd.getCommand();
		cmd.getHelp();
		
		try {
			String response = cmd.execute("output env");
			
			assertTrue(response != null);
			
		} catch (InterruptedIOException e) {
			//do nothing.
		}
	}
	
	@Test
	public void testShellCmdOutputEnvFormatted(){
		
		ShellCmdOutputEnvFormatted cmd = new ShellCmdOutputEnvFormatted();		
		assertTrue(cmd.checkLineForCommandCompatibility("output env formatted"));
		assertFalse(cmd.checkLineForCommandCompatibility("output env for2"));
		
		cmd.getCommand();
		cmd.getHelp();
		
		try {
			String response = cmd.execute("output env formatted");
			
			//TODO
			//assertTrue(response != null);
			
		} catch (InterruptedIOException e) {
			//do nothing.
		}
	}
	
	@Test
	public void testShellCmdOutputLog(){
		
		ShellCmdOutputLog cmd = new ShellCmdOutputLog();		
		assertTrue(cmd.checkLineForCommandCompatibility("output log"));
		assertFalse(cmd.checkLineForCommandCompatibility("output logg"));
		
		cmd.getCommand();
		cmd.getHelp();
		
		// doesn't work in travis
		// so, don't execute this 
	}
	
	@Test
	public void testShellCmdQuit(){
		
		ShellCmdQuit cmd = new ShellCmdQuit();		
		assertTrue(cmd.checkLineForCommandCompatibility("quit"));
		assertFalse(cmd.checkLineForCommandCompatibility("quitt"));
		
		cmd.getCommand();
		cmd.getHelp();
		
		try {
			@SuppressWarnings("unused")
			String response = cmd.execute("quit");
		} catch (InterruptedIOException e) {
			//do nothing.
		}
	}
	
	@Test
	public void testShellCmdAddAccessory(){
		
		ShellCmdAddAccessory cmd = new ShellCmdAddAccessory();		
		assertTrue(cmd.checkLineForCommandCompatibility("add accessory [1][2]"));
		assertFalse(cmd.checkLineForCommandCompatibility("add accessorry [1][2]"));
		
		cmd.getCommand();
		cmd.getHelp();
		
		try {
			String response = cmd.execute("register accessory [1][2]");
			
			assertFalse(response.isEmpty());
			
		} catch (InterruptedIOException e) {
			//do nothing.
		}
	}
	
	@Test
	public void testShellCmdRemoveAccessory(){
		
		ShellCmdRemoveAccessory cmd = new ShellCmdRemoveAccessory();		
		assertTrue(cmd.checkLineForCommandCompatibility("remove accessory [1][2]"));
		assertFalse(cmd.checkLineForCommandCompatibility("remove accessorry [1][2]"));
		
		cmd.getCommand();
		cmd.getHelp();
		
		try {
			String response = cmd.execute("nregister accessory [1][2]");
			
			assertFalse(response.isEmpty());
			
		} catch (InterruptedIOException e) {
			//do nothing.
		}
	}
	
	@Test
	public void testShellCmdRestoreFactory(){
		
		ShellCmdRestoreFactoryConfig cmd = new ShellCmdRestoreFactoryConfig();		
		assertTrue(cmd.checkLineForCommandCompatibility("restore factory config"));
		assertFalse(cmd.checkLineForCommandCompatibility("restores factory config"));
		
		cmd.getCommand();
		cmd.getHelp();
		
		try {
			String response = cmd.execute("restore factory config");
			
			assertFalse(response.isEmpty());
			
		} catch (InterruptedIOException e) {
			//do nothing.
		}
	}
	
	@Test
	public void testShellCmdSetConfig(){
		
		ShellCmdSetConfig cmd = new ShellCmdSetConfig();		
		assertTrue(cmd.checkLineForCommandCompatibility("set config [AGISERVER][TCPIP_PORT] 9000"));
		assertFalse(cmd.checkLineForCommandCompatibility("vset config [AGISERVER][TCPIP_PORT] 0999"));
		
		cmd.getCommand();
		cmd.getHelp();
		
		try {
			String response = cmd.execute("set config [AGISERVER][TCPIP_PORT]");
			
			assertFalse(response.isEmpty());
			
		} catch (InterruptedIOException e) {
			//do nothing.
		}
	}
	
	@Test
	public void testShellCmdShutdown(){
		
		ShellCmdShutdown cmd = new ShellCmdShutdown();		
		assertTrue(cmd.checkLineForCommandCompatibility("shutdown"));
		assertFalse(cmd.checkLineForCommandCompatibility("shutdowns"));
		
		cmd.getCommand();
		cmd.getHelp();
		
		try {
			String response = cmd.execute("shutdown");
			
			assertFalse(response.isEmpty());
			
		} catch (InterruptedIOException e) {
			//do nothing.
		}
	}
	
	@Test
	public void testShellCmdVersion(){
		
		ShellCmdVersion cmd = new ShellCmdVersion();		
		assertTrue(cmd.checkLineForCommandCompatibility("version"));
		assertFalse(cmd.checkLineForCommandCompatibility("versions"));
		
		cmd.getCommand();
		cmd.getHelp();
		
		try {
			String response = cmd.execute("version");
			
			assertFalse(response.isEmpty());
			
		} catch (InterruptedIOException e) {
			//do nothing.
		}
	}
}
