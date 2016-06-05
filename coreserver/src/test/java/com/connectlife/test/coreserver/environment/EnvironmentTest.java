/**
 *  EnvironmentTest.java
 *  coreserver
 *
 *  Created by ericpinet on 2015-10-31.
 *  Copyright (c) 2015 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.connectlife.test.coreserver.environment;

import static org.junit.Assert.*;

import java.util.Observable;
import java.util.Observer;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.clapi.data.Room;
import com.clapi.data.Accessory;
import com.connectlife.coreserver.Application;
import com.connectlife.coreserver.environment.Environment;
import com.connectlife.coreserver.environment.EnvironmentManager;
import com.connectlife.coreserver.environment.cmd.CmdAddAccessory;
import com.connectlife.coreserver.environment.cmd.CmdFactory;
import com.connectlife.coreserver.environment.cmd.CmdRegisterAccessory;
import com.connectlife.coreserver.environment.cmd.CmdUnregisterAccessory;
import com.connectlife.coreserver.environment.cmd.CmdUpdateAccessory;
import com.connectlife.test.coreserver.ApplicationInjectTest;
import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * 
 * 
 * @author ericpinet
 * <br> 2015-10-31
 */
public class EnvironmentTest implements Observer {
	
	/**
	 * Environment data path contain the data representing the user, home, 
	 * zones, rooms, accessories
	 */
	private static final String ENV_DATA_PATH = "data";
	
	/**
	 * Path for keep temporary the true environment during the test
	 */
	private static final String ENV_DATA_BACKUP_PATH = "data-backup";
	
	/**
	 * Environment file name contain the data representing the user, home, 
	 * zones, rooms, accessories
	 */
	private static final String ENV_DATA_FILENAME = "env.data";
	
	/**
	 * Environment file name backup.
	 */
	private static final String ENV_DATA_FILENAME_BACKUP = "env.data.bk";
	
	/**
	 * Environment to test
	 */
	Environment env;
	
	/**
	 * Path
	 */
	String m_path;
	
	/**
	 * Temporary path for test.
	 */
	String m_path_backup;
	
	/**
	 * full Filename
	 */
	String m_filename;
	
	/**
	 * Full filename backup
	 */
	String m_filename_backup; 
	
	/**
	 * Full filename temporary for test.
	 */
	String m_filename_bk;
	
	/**
	 * Full filename backup temporary for test.
	 */
	String m_filename_bk_backup;

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

		m_path = app.getBasePath() + "/" + ENV_DATA_PATH + "/";
		m_path_backup = app.getBasePath() + "/" + ENV_DATA_BACKUP_PATH + "/";
		
		m_filename = m_path + ENV_DATA_FILENAME;
		m_filename_backup = m_path + ENV_DATA_FILENAME_BACKUP; 
		
		m_filename_bk = m_path_backup + ENV_DATA_FILENAME;
		m_filename_bk_backup = m_path_backup + ENV_DATA_FILENAME_BACKUP;
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testInitWithoutEnvFile() {
		
		// prepare file to test
		assertTrue(moveEnvFileInBackupTest());
		
		// delete env directory and file
		deleteEnvDirectory();
		
		// init the environment
		Injector injector = Guice.createInjector(new EnvironmentInjectTest());
		env = injector.getInstance(EnvironmentManager.class);
		assertTrue(env.init());
		
		// delete env directory and file
		deleteEnvDirectory();
		
		// restore file after test.
		assertTrue(restoreEnvFileFromBackupTest());
	}
	
	@Test
	public void testInitUnitWithEnvFile() {
		// prepare file to test
		assertTrue(moveEnvFileInBackupTest());
		
		// create env directory and file valid
		createValidDataEnv();
		
		// init the environment
		Injector injector = Guice.createInjector(new EnvironmentInjectTest());
		env = injector.getInstance(EnvironmentManager.class);
		assertTrue(env.init());
		
		// uninit environment
		env.unInit();
		
		// delete env directory and file
		deleteEnvDirectory();
		
		// restore file after test.
		assertTrue(restoreEnvFileFromBackupTest());
	}
	
	@Test
	public void testInitWithInvalidEnvFile() {
		// prepare file to test
		assertTrue(moveEnvFileInBackupTest());
		
		// create env directory and file valid
		createInValidDataEnv();
		
		// init the environment
		Injector injector = Guice.createInjector(new EnvironmentInjectTest());
		env = injector.getInstance(EnvironmentManager.class);
		assertTrue(env.init());
		
		// uninit environment
		env.unInit();
		
		// delete env directory and file
		deleteEnvDirectory();
		
		// restore file after test.
		assertTrue(restoreEnvFileFromBackupTest());
	}
	
	@Test
	public void testInitWithInvalidEnvFileAndInvalidBackup() {
		// prepare file to test
		assertTrue(moveEnvFileInBackupTest());
		
		// create env directory and file valid
		createInValidDataEnvInvalidBackup();
		
		// init the environment
		Injector injector = Guice.createInjector(new EnvironmentInjectTest());
		env = injector.getInstance(EnvironmentManager.class);
		assertFalse(env.init());
		
		// uninit environment
		env.unInit();
		
		// delete env directory and file
		deleteEnvDirectory();
		
		// restore file after test.
		assertTrue(restoreEnvFileFromBackupTest());
	}
	
	@Test
	public void testAddDeleteObserver() {
		
		// prepare file to test
		assertTrue(moveEnvFileInBackupTest());
				
		// init the environment
		Injector injector = Guice.createInjector(new EnvironmentInjectTest());
		env = injector.getInstance(EnvironmentManager.class);
		
		assertTrue(env.init());
		
		assertEquals(env.countObservers(), 0);
		
		env.addObserver(this);
		
		assertEquals(env.countObservers(), 1);
		
		env.deleteObserver(this);
		
		assertEquals(env.countObservers(), 0);
		
		env.addObserver(this);
		
		assertEquals(env.countObservers(), 1);
		
		env.deleteObservers();
		
		assertEquals(env.countObservers(), 0);
		
		// delete env directory and file
		deleteEnvDirectory();
		
		// restore file after test.
		assertTrue(restoreEnvFileFromBackupTest());
	}
	
	@Test
	public void testGetJsonData() {
		
		fail("Not yet implemented");
		/*
		// prepare file to test
		assertTrue(moveEnvFileInBackupTest());
		
		// create env directory and file valid
		createValidDataEnv();
		
		// init the environment
		Injector injector = Guice.createInjector(new EnvironmentInjectTest());
		env = injector.getInstance(EnvironmentManager.class);
		assertTrue(env.init());
		
		// test json data
		Data data = env.getData();
		Gson gson = new Gson();
		String json = gson.toJson(data);
		
		assertTrue(env.getJsonEnvironment().equals(json));
		
		// restore file after test.
		assertTrue(restoreEnvFileFromBackupTest());
		*/
	}
	
	@Test
	public void testGetData() {
		
		fail("Not yet implemented");
		/*
		// prepare file to test
		assertTrue(moveEnvFileInBackupTest());
				
		// create env directory and file valid
		createValidDataEnv();
		
		// init the environment
		Injector injector = Guice.createInjector(new EnvironmentInjectTest());
		env = injector.getInstance(EnvironmentManager.class);
		assertTrue(env.init());
		
		// test get data return not null
		assertNotNull(env.getData());
		
		// restore file after test.
		assertTrue(restoreEnvFileFromBackupTest());
		*/
	}
	
	@Test
	public void testAddAccessory() throws Exception {
		
		// prepare file to test
		assertTrue(moveEnvFileInBackupTest());
				
		// create env directory and file valid
		createValidDataEnv();
		
		// init the environment
		Injector injector = Guice.createInjector(new EnvironmentInjectTest());
		env = injector.getInstance(EnvironmentManager.class);
		assertTrue(env.init());
		
		// test add accessory
		CmdAddAccessory cmd = CmdFactory.getCmdAddAccesssory(new Accessory("", "test"), new Room("5",""));
		env.executeCommand(cmd);
		
		// test add same accessory in same room
		cmd = CmdFactory.getCmdAddAccesssory(new Accessory("", "test"), new Room("5",""));
		try{
			env.executeCommand(cmd);
			fail("A exception must be raise!");
		}catch(Exception e){
		}
		
		// test add accessory
		cmd = CmdFactory.getCmdAddAccesssory(new Accessory("1", "test"), new Room("5",""));
		try{
			env.executeCommand(cmd);
			fail("A exception must be raise!");
		}catch(Exception e){
		}
		
		// test add accessory
		cmd = CmdFactory.getCmdAddAccesssory(new Accessory("", "test"), new Room("6",""));
		try{
			env.executeCommand(cmd);
			fail("A exception must be raise!");
		}catch(Exception e){
		}
		
		// restore file after test.
		assertTrue(restoreEnvFileFromBackupTest());
	}
	
	@Test
	public void testRegisterDevice() {
		
		// prepare file to test
		assertTrue(moveEnvFileInBackupTest());
				
		// create env directory and file valid
		createValidDataEnv();
		
		// init the environment
		Injector injector = Guice.createInjector(new EnvironmentInjectTest());
		env = injector.getInstance(EnvironmentManager.class);
		assertTrue(env.init());
		
		// test register accessory
		Accessory accessory = null;
		try {
			CmdRegisterAccessory command = CmdFactory.getCmdRegisterAccesssory(CreateTestData.getLightTest());
			env.executeCommand(command);
			accessory = command.getAccessory();
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertTrue(accessory.isRegister());
		
		// test register a invalid accessory
		Accessory invalid = CreateTestData.getLightTest();
		invalid.setSerialnumber("XXXXXXXX");
		Accessory accessory2 = null;
		try {
			CmdRegisterAccessory command = CmdFactory.getCmdRegisterAccesssory(invalid);
			env.executeCommand(command);
			accessory2 = command.getAccessory();
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertTrue(null == accessory2);
		
		// restore file after test.
		assertTrue(restoreEnvFileFromBackupTest());
	}
	
	@Test
	public void testUpdateDevice() {
		
		// prepare file to test
		assertTrue(moveEnvFileInBackupTest());
				
		// create env directory and file valid
		createValidDataEnv();
		
		// init the environment
		Injector injector = Guice.createInjector(new EnvironmentInjectTest());
		env = injector.getInstance(EnvironmentManager.class);
		assertTrue(env.init());
		
		// test update accessory when not register
		Accessory accessory = null;
		try {
			CmdUpdateAccessory command = CmdFactory.getCmdUpdateAccesssory(CreateTestData.getLightTest());
			env.executeCommand(command);
			accessory = command.getAccessory();
		} catch (Exception e) {
		}
		assertNull(accessory);
		
		// test update accessory when register
		accessory = null;
		try {
			// register device
			CmdRegisterAccessory command = CmdFactory.getCmdRegisterAccesssory(CreateTestData.getLightTest());
			env.executeCommand(command);
			
			CmdUpdateAccessory command2 = CmdFactory.getCmdUpdateAccesssory(CreateTestData.getLightTest());
			env.executeCommand(command2);
			accessory = command2.getAccessory();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertNotNull(accessory);
		
		// restore file after test.
		assertTrue(restoreEnvFileFromBackupTest());
	}
	
	@Test
	public void testUnregisterDevice() {
		
		// prepare file to test
		assertTrue(moveEnvFileInBackupTest());
				
		// create env directory and file valid
		createValidDataEnv();
		
		// init the environment
		Injector injector = Guice.createInjector(new EnvironmentInjectTest());
		env = injector.getInstance(EnvironmentManager.class);
		assertTrue(env.init());
		
		// test register accessory
		Accessory accessory = null;
		try {
			CmdRegisterAccessory command = CmdFactory.getCmdRegisterAccesssory(CreateTestData.getLightTest());
			env.executeCommand(command);
			accessory = command.getAccessory();
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertTrue(accessory.isRegister());
		
		// test unregister
		try {
			CmdUnregisterAccessory command = CmdFactory.getCmdUnregisterAccesssory(CreateTestData.getLightTest());
			env.executeCommand(command);
			accessory = command.getAccessory();
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertFalse(accessory.isRegister());
		
		// test unregister a unregistered accessory
		try {
			CmdUnregisterAccessory command = CmdFactory.getCmdUnregisterAccesssory(CreateTestData.getLightTest());
			env.executeCommand(command);
			accessory = command.getAccessory();
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertFalse(accessory.isRegister());
		
		// restore file after test.
		assertTrue(restoreEnvFileFromBackupTest());
	}
	
	
	private void deleteEnvDirectory(){
		/*
		File directory = new File(m_path);
		if(directory.exists()){
			File files[] = directory.listFiles();
			
			for(int i=files.length-1 ; i>=0 ; i--){
				files[i].delete();
			}
			
			directory.delete();
		}
		*/
	}
	
	private boolean moveEnvFileInBackupTest(){
		boolean ret_val = true;
		/*
		try{
			
			File directory = new File(m_path);
			if(directory.exists()){
			
				File bk_directory = new File(m_path_backup);
				bk_directory.mkdirs();
				
				File file = new File(m_filename);
				File filebk = new File(m_filename_backup);
				
				file.renameTo(new File(m_filename_bk));
				filebk.renameTo(new File(m_filename_bk_backup));
			}
			
			ret_val = true;

		}catch(Exception e){
			e.printStackTrace();
		}*/
		
		return ret_val;
	}

	private boolean restoreEnvFileFromBackupTest(){
		boolean ret_val = true;
		/*
		try{
			
			File bk_directory = new File(m_path_backup);
			if(bk_directory.exists()){
			
				File directory = new File(m_path);
				directory.mkdirs();
				
				File file = new File(m_filename_bk);
				File filebk = new File(m_filename_bk_backup);
				
				file.renameTo(new File(m_filename));
				filebk.renameTo(new File(m_filename_backup)); 
				
				bk_directory.delete();
			}
			
			ret_val = true;

		}catch(Exception e){
			e.printStackTrace();
		}*/
		return ret_val;
	}
	
	private boolean createValidDataEnv(){
		boolean ret_val = true;
		/*
		try {
			File directory = new File(m_path);
			directory.mkdirs();
			
			File file = new File(m_filename);
			if (!file.exists()) {
				file.createNewFile();
			}
			
			File file_bk = new File(m_filename_backup);
			if (!file_bk.exists()) {
				file_bk.createNewFile();
			}
			
			Data data = CreateTestData.getData();
			Gson gson = new Gson();
			String json = gson.toJson(data);
			
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(json);
			bw.close();
			
			FileWriter fw_bk = new FileWriter(file_bk.getAbsoluteFile());
			BufferedWriter bw_bk = new BufferedWriter(fw_bk);
			bw_bk.write(json);
			bw_bk.close();
			
			ret_val = true;
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		*/
		return ret_val;
	}
	
	private boolean createInValidDataEnv(){
		boolean ret_val = true;
		/*
		try {
			File directory = new File(m_path);
			directory.mkdirs();
			
			File file = new File(m_filename);
			if (!file.exists()) {
				file.createNewFile();
			}
			
			File file_bk = new File(m_filename_backup);
			if (!file_bk.exists()) {
				file_bk.createNewFile();
			}
			
			Data data = CreateTestData.getData();
			Gson gson = new Gson();
			String json = gson.toJson(data);
			String invalid_json = "jkdhjsdf";
			
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(invalid_json);
			bw.close();
			
			FileWriter fw_bk = new FileWriter(file_bk.getAbsoluteFile());
			BufferedWriter bw_bk = new BufferedWriter(fw_bk);
			bw_bk.write(json);
			bw_bk.close();
			
			ret_val = true;
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		*/
		return ret_val;
	}
	
	private boolean createInValidDataEnvInvalidBackup(){
		boolean ret_val = true;
		/*
		try {
			File directory = new File(m_path);
			directory.mkdirs();
			
			File file = new File(m_filename);
			if (!file.exists()) {
				file.createNewFile();
			}
			
			File file_bk = new File(m_filename_backup);
			if (!file_bk.exists()) {
				file_bk.createNewFile();
			}
			
			String invalid_json = "jkdhjsdf";
			
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(invalid_json);
			bw.close();
			
			FileWriter fw_bk = new FileWriter(file_bk.getAbsoluteFile());
			BufferedWriter bw_bk = new BufferedWriter(fw_bk);
			bw_bk.write(invalid_json);
			bw_bk.close();
			
			ret_val = true;
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		*/
		return ret_val;
	}

	/**
	 * @param o
	 * @param arg
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable o, Object arg) {
		
	}
}
