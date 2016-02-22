/**
 *  ConfigSqliteTest.java
 *  coreserver
 *
 *  Created by ericpinet on 2015-12-13.
 *  Copyright (c) 2015 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.connectlife.test.coreserver.config;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.connectlife.coreserver.Application;
import com.connectlife.coreserver.config.Config;
import com.connectlife.coreserver.config.ConfigFactoryData;
import com.connectlife.coreserver.config.ConfigItem;
import com.connectlife.coreserver.config.SqliteSettings;
import com.connectlife.test.coreserver.ApplicationInjectTest;
import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * 
 * 
 * @author ericpinet
 * <br> 2015-12-13
 */
public class ConfigSqliteTest {
	
	private String m_database_file;
	
	private final int SECTION 	= 0;
	private final int ITEM 		= 1;
	private final int TYPE		= 2;
	private final int VALUE		= 3;
	

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
		
		// prepare config object
		Injector injector = Guice.createInjector(new ConfigSqliteInjectTest());
		SqliteSettings settings = injector.getInstance(SqliteSettings.class);
		
		// prepare app object
		Injector injector2 = Guice.createInjector(new ApplicationInjectTest());
		final Application app = injector2.getInstance(Application.class);
		
		app.startupTest();
		
		m_database_file = app.getBasePath() + "/" + settings.getDatabasePath() + "/" + settings.getDatabaseFileName();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		
		// delete test database after all tests cases.
		deleteDatabase();
	}

	@Test
	public void testConfigInitWhenDatabaseWasNotAlreadyPrepared() {
		
		// prepare config object
		Injector injector = Guice.createInjector(new ConfigSqliteInjectTest());
		Config config = injector.getInstance(Config.class);
		
		// prepare to test, delete database file
		deleteDatabase();
		
		// init the config
		assertTrue(config.init());
		
	}
	
	@Test
	public void testConfigInitWhenDatabaseWasAlreadyPrepared() {
		
		// prepare config object
		Injector injector = Guice.createInjector(new ConfigSqliteInjectTest());
		Config config = injector.getInstance(Config.class);
		
		// init the config
		assertTrue(config.init());
		
		// init the second config on existant database
		Config config2 = injector.getInstance(Config.class);
		
		// init the config2
		assertTrue(config2.init());
	}
	
	@Test
	public void testConfigUnInitWhenInit() {
		
		// prepare config object
		Injector injector = Guice.createInjector(new ConfigSqliteInjectTest());
		Config config = injector.getInstance(Config.class);
		
		// init the config
		assertTrue(config.init());
		
		// init the config
		config.unInit();
	}
	
	@Test
	public void testConfigUnInitWhenNoInit() {
		
		// prepare config object
		Injector injector = Guice.createInjector(new ConfigSqliteInjectTest());
		Config config = injector.getInstance(Config.class);
		
		// init the config
		config.unInit();
	}
	
	@Test
	public void testGetConfig() {
		
		// prepare config object
		Injector injector = Guice.createInjector(new ConfigSqliteInjectTest());
		Config config = injector.getInstance(Config.class);
		
		// delete old test config database
		deleteDatabase();
		
		// init the config
		assertTrue(config.init());
		
		// compare the config with the factory data (test getConfig with all config)
		assertTrue(compareConfigWith(config, ConfigFactoryData.ItemConfig));
	}
	
	@Test
	public void testGetConfigs() {
		
		// prepare config object
		Injector injector = Guice.createInjector(new ConfigSqliteInjectTest());
		Config config = injector.getInstance(Config.class);
		
		// delete old test config database
		deleteDatabase();
		
		// init the config
		assertTrue(config.init());
		
		// compare the config with the factory data size
		assertTrue(config.getConfigs().size() == ConfigFactoryData.ItemConfig.length );
	}
	
	@Test
	public void testSetConfig() {
		
		// prepare config object
		Injector injector = Guice.createInjector(new ConfigSqliteInjectTest());
		Config config = injector.getInstance(Config.class);
		
		// delete old test config database
		deleteDatabase();
		
		// init the config
		assertTrue(config.init());
		
		// take the factory config and check if value return is good
		for(int i=0 ; i<ConfigFactoryData.ItemConfig.length ; i++){
			
			String section = ConfigFactoryData.ItemConfig[i][SECTION];
			String item = ConfigFactoryData.ItemConfig[i][ITEM];
			String value =  "1";
			
			ConfigItem configitem = new ConfigItem(section, item, value);
			
			assertTrue(config.setConfig(configitem));
		}
		
		// check if all config value is "1"
		assertTrue( compareConfigWithOne(config, ConfigFactoryData.ItemConfig) );
	}
	
	@Test
	public void testRestoreFactory() {
		
		// prepare config object
		Injector injector = Guice.createInjector(new ConfigSqliteInjectTest());
		Config config = injector.getInstance(Config.class);
		
		// delete old test config database
		deleteDatabase();
		
		// init the config
		assertTrue(config.init());
		
		// set 1 in all value
		for(int i=0 ; i<ConfigFactoryData.ItemConfig.length ; i++){
			
			String section = ConfigFactoryData.ItemConfig[i][SECTION];
			String item = ConfigFactoryData.ItemConfig[i][ITEM];
			String value =  "1";
			
			ConfigItem configitem = new ConfigItem(section, item, value);
			
			assertTrue(config.setConfig(configitem));
		}
		
		// check if all config value is "1"
		assertTrue( compareConfigWithOne(config, ConfigFactoryData.ItemConfig) );
		
		// restore factory
		assertTrue( config.RestoreFactory() );
		
		// check if all config value is factory
		assertTrue( compareConfigWith(config, ConfigFactoryData.ItemConfig) );
	}
	
	/**
	 * Delete the database file.
	 */
	private void deleteDatabase(){
		File file = new File(m_database_file);
		if(file.exists()){
			file.delete();
		}
	}
	
	/**
	 * Compare the configuration with the data.
	 * 
	 * @param _config
	 * @param _itemConfig
	 * @return Return true if is equals.
	 */
	private boolean compareConfigWith(Config _config, String[][] _data){
		boolean ret_val = true;
		
		// take the factory config and check if value return is good
		for(int i=0 ; i<_data.length ; i++){
			
			String section = _data[i][SECTION];
			String item = _data[i][ITEM];
			String type = _data[i][TYPE];
			String value =  _data[i][VALUE];
			String str_value = "";
			int integer_value = -1;
			
			// check type and value.
			if( type.equalsIgnoreCase(ConfigFactoryData.CONFIG_TYPE_STRING) ){ // String 
				str_value = value;
				if(_config.getConfig(section, item).getStringValue().compareTo(str_value) != 0 ){
					ret_val = false;
				}
			}
			else if( type.equalsIgnoreCase(ConfigFactoryData.CONFIG_TYPE_INTEGER) ) { // Integer
				integer_value = Integer.valueOf(value);
				if( _config.getConfig(section, item).getIntegerValue().intValue() != integer_value ){
					ret_val = false;
				}
			}
			else{
				ret_val = false;
				fail("Invalid config type.");
			}
		}
		
		return ret_val;
	}
	
	/**
	 * Compare the configuration with the data 1 in each value.
	 * 
	 * @param _config
	 * @param _itemConfig
	 * @return Return true if is equals.
	 */
	private boolean compareConfigWithOne(Config _config, String[][] _data){
		boolean ret_val = true;
		
		// take the factory config and check if value return is good
		for(int i=0 ; i<_data.length ; i++){
			
			String section = _data[i][SECTION];
			String item = _data[i][ITEM];
			String type = _data[i][TYPE];
			String str_value = "1";
			int integer_value = 1;
			
			// check type and value.
			if( type.equalsIgnoreCase(ConfigFactoryData.CONFIG_TYPE_STRING) ){ // String 
				if(_config.getConfig(section, item).getStringValue().compareTo(str_value) != 0 ){
					ret_val = false;
				}
			}
			else if( type.equalsIgnoreCase(ConfigFactoryData.CONFIG_TYPE_INTEGER) ) { // Integer
				if( _config.getConfig(section, item).getIntegerValue().intValue() != integer_value ){
					ret_val = false;
				}
			}
			else{
				ret_val = false;
				fail("Invalid config type.");
			}
		}
		
		return ret_val;
	}

}
