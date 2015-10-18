/**
 *  ConfigManager.java
 *  coreserver
 *
 *  Created by EricPinet <pineri01@gmail.com> on 2015-09-07.
 *  Copyright (c) 2015 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.connectlife.coreserver.modules.configmanager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

// external
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.connectlife.coreserver.Consts;
import com.connectlife.coreserver.Consts.ModuleUID;
// internal
import com.connectlife.coreserver.modules.Module;
import com.connectlife.coreserver.tools.errormanagement.StdOutErrLog;

/**
 * This is the ConfigManager for this application. 
 * This class permit to read and write object in
 * SQLite database of the application.
 * 
 * @author Eric Pinet (pineri01@gmail.com)
 * <br> 2015-09-07
 */
public class ConfigManager implements Module {
	
	/**
	 * Logger use for this class.
	 */
	private static Logger m_logger = LogManager.getLogger(ConfigManager.class);
    
	/**
	 * Singleton reference for this class
	 */
	private static ConfigManager m_ref = null;
	
	/**
	 * Flag to indicate if the module is correctly initialized.
	 */
	private boolean m_isInit;
	
	/**
	 * ModuleUID for the ApiServer
	 */
	private static final ModuleUID m_moduleUID = ModuleUID.API_SERVER;
	
	/**
	 * Connection of database (sqlite file)
	 */
	Connection m_connection;
	
	/**
	 * Default ConfigManager constructor.
	 */
	private ConfigManager(){
		
	}
	
	/**
	 * Return the instance of the ConfigManager for this application
	 * 
	 * @return Singleton instance of the ConfigManager.
	 */
	public static ConfigManager getInstance(){
		if(null == m_ref){
			m_ref = new ConfigManager();
		}
		return m_ref;
	}
	
	/**
	 * Initialization of the ConfigManager. 
	 * Database file will be created if doesn't exist. 
	 * If exist, ensure that is valid.
	 * 
	 * @return True if the ConfigManager initialized correctly.
	 */
	public boolean init(){
		boolean ret_val = false;
		
		m_logger.info("Initialization in progress ...");
		
		m_isInit = ret_val = prepareDatabase();
	    
	    // return the right message if succeed or failed.
		if( true==ret_val )
			m_logger.info("Initialization completed.");
		else
			m_logger.error("Initialization failed.");
		
		return ret_val;
	}

	/**
	 * Return True is the ConfigManager is correctly initialized.
	 */
	public boolean isInit() {
		return m_isInit;
	}

	/**
	 * UnInitialize the ConfigManager. Return in empty state ready to init again.
	 */
	public void unInit() {
		
		m_logger.info("UnInitialization in progress ...");
		
		if(null != m_connection){
			try {
				m_connection.close();
			} catch (SQLException e) {
				m_logger.error("Unable to close connection. "+e.getMessage());
				StdOutErrLog.tieSystemOutAndErrToLog();
				e.printStackTrace();
			}
		}
		
		m_logger.info("UnInitialization completed.");
	}
	
	/**
	 * Return the moduleUID for the ApiServer.
	 */
	public ModuleUID getModuleUID() {
		return m_moduleUID;
	}
	
	/**
	 * Return a Config object that correspond to this section and item.
	 * 
	 * @param _section 	Section of the configuration.
	 * @param _item		Item of the configuraiton.
	 * @return Config object or null.
	 */
	public static Config getConfig(String _section, String _item){
		return getInstance().loadConfig(_section, _item);
	}
	
	/**
	 * Set new value for a config and save.
	 * 
	 * @param _object Config objecto to save in configuration.
	 * @return True if configuration was correctly updated.
	 */
	public static boolean setConfig(Config _object){
		//TODO Complete this methode.
		boolean ret_val = false;
		return ret_val;
	}
	
	
	/**
	 * Prepare database for the application
	 * 
	 * @return True if the preparation of the database is completed correctly.
	 */
	private boolean prepareDatabase(){
		boolean ret_val = false;
		boolean need_to_recreate_database = true;
		Statement statement = null;
		
		try {
	    	// load sqlite jdbc driver
			Class.forName("org.sqlite.JDBC");
			
			// create a database connection
			m_logger.info("Load database file '"+Consts.DATABASE_FILE+"'");
			m_connection = DriverManager.getConnection("jdbc:sqlite:"+Consts.DATABASE_FILE);
			
			// setup timeout for query
			m_logger.info("Set database default timeout '"+Consts.DATABASE_TIMEOUT+"'");
			statement = m_connection.createStatement();
			statement.setQueryTimeout(Consts.DATABASE_TIMEOUT);
			
			// prepare database
			// check version of the database
			// if version is not the right
			// recreate the database
			try{
				ResultSet rs = statement.executeQuery( "select number from version;" );
				while ( rs.next() ) {
					String version_number = rs.getString("number");
					if( true == version_number.equalsIgnoreCase(DatabaseStructure.VERSION) ){
						need_to_recreate_database = false;
						ret_val = true;
						m_logger.info("Database version "+ DatabaseStructure.VERSION +" is good.");
					}
					else{
						m_logger.warn("Database version isn't the right version. Database must be rebuild.");
					}
				}
			}catch (SQLException e) {
				m_logger.warn("Database version isn't the right version. Database must be rebuild.");
			}
			
			// if version not the same
			// recreate the database
			if(true == need_to_recreate_database){
				
				// drop all tables
				for( int i=0 ; i<DatabaseStructure.DROP_TABLES.length ; i++){
					m_logger.warn("Execute statement: "+DatabaseStructure.DROP_TABLES[i]);
					statement.executeUpdate(DatabaseStructure.DROP_TABLES[i]);
				}
				
				// create all tables
				for( int i=0 ; i<DatabaseStructure.CREATE_TABLES.length ; i++){
					m_logger.warn("Execute statement: "+DatabaseStructure.CREATE_TABLES[i]);
					statement.executeUpdate(DatabaseStructure.CREATE_TABLES[i]);
				}
				
				// create default data in all tables
				String [] datas = DatabaseStructure.CREATE_DATA();
				for( int i=0 ; i<datas.length ; i++){
					m_logger.warn("Execute statement: "+datas[i]);
					statement.executeUpdate(datas[i]);
				}
			}
			
			ret_val = true;
			
		} catch (ClassNotFoundException e) {
			m_logger.error("Unable to prepare database. "+e.getMessage());
			StdOutErrLog.tieSystemOutAndErrToLog();
			e.printStackTrace();
		} catch (SQLException e) {
			m_logger.error("Unable to prepare database. "+e.getMessage());
			StdOutErrLog.tieSystemOutAndErrToLog();
			e.printStackTrace();
		}
	    finally{
	    	try{
		    	if(null != statement)
		    		statement.close();
	    	}
	    	catch(SQLException e){
	    		m_logger.error("Unable to close database statement. "+e.getMessage());
	    		StdOutErrLog.tieSystemOutAndErrToLog();
	    		e.printStackTrace();
	    	}
		}
		
		return ret_val;
	}
	
	/**
	 * Restore the factory configurations of system.
	 * 
	 * @return True if the restoration is completed successfully.
	 */
	public boolean RestoreFactory(){
		Statement statement = null;
		boolean ret = false;
		
		// check if connection is ready to get config.
		if(true == m_isInit &&
		   null != m_connection	){

			try {
				statement = m_connection.createStatement();
				statement.setQueryTimeout(Consts.DATABASE_TIMEOUT);
				
				// drop all tables
				for( int i=0 ; i<DatabaseStructure.DROP_TABLES.length ; i++){
					m_logger.warn("Execute statement: "+DatabaseStructure.DROP_TABLES[i]);
					statement.executeUpdate(DatabaseStructure.DROP_TABLES[i]);
				}
				
				// create all tables
				for( int i=0 ; i<DatabaseStructure.CREATE_TABLES.length ; i++){
					m_logger.warn("Execute statement: "+DatabaseStructure.CREATE_TABLES[i]);
					statement.executeUpdate(DatabaseStructure.CREATE_TABLES[i]);
				}
				
				// create default data in all tables
				String [] datas = DatabaseStructure.CREATE_DATA();
				for( int i=0 ; i<datas.length ; i++){
					m_logger.warn("Execute statement: "+datas[i]);
					statement.executeUpdate(datas[i]);
				}
				ret = true;
				
			} catch (SQLException e) {
				m_logger.error("Unable to restore factory configurations. "+e.getMessage());
				StdOutErrLog.tieSystemOutAndErrToLog();
				e.printStackTrace();
			}
		}
		else{
			m_logger.error("Unable to  restore factory, the connection is not ready.");
		}
		
		return ret;
	}
	
	/**
	 * Return a configuration object.
	 * 
	 * @param _section Section of the item configuration.
	 * @param _item Item of configuration.
	 * 
	 * @return A Config object representing the configuration.
	 */
	private Config loadConfig(String _section, String _item){
		Config ret_config = null;
		Statement statement = null;
		
		// check if connection is ready to get config.
		if(true == m_isInit &&
		   null != m_connection	){

			try {
				statement = m_connection.createStatement();
				statement.setQueryTimeout(Consts.DATABASE_TIMEOUT);
				
				ResultSet rs = statement.executeQuery( "select section, item, type, value "
													 + "from config "
													 + "where section = '"+_section+"' "
													 + "and item = '"+_item+"';" );
				while ( rs.next() ) {
					String section = rs.getString("section");
					String item = rs.getString("item");
					String type = rs.getString("type");
					String value = rs.getString("value");
					
					if( type.equals(Consts.CONFIG_TYPE_STRING) ){
						ret_config = new Config(section, item , value);
					}
					else if( type.equals(Consts.CONFIG_TYPE_INTEGER) ){
						ret_config = new Config(section, item, Integer.parseInt(value));
					}
					else {
						m_logger.error("Unable to get config from database. Invalid type ("+ type +") or section ("+ section +") and item ("+ item +") invalid.");
					}
				}
				
			} catch (SQLException e) {
				m_logger.error("Unable to retrive configuration. "+e.getMessage());
				StdOutErrLog.tieSystemOutAndErrToLog();
				e.printStackTrace();
			}
		}
		else{
			m_logger.error("Unable to retrive config from database, the connection is not ready.");
		}
		
		return ret_config;
	}
	
	/**
	 * Return all the configurations.
	 * 
	 * @return A Configurations list.
	 */
	public ArrayList<Config> getConfigs(){
		Config config = null;
		Statement statement = null;
		ArrayList<Config> ret_configs = new ArrayList<Config>();
		
		// check if connection is ready to get config.
		if(true == m_isInit &&
		   null != m_connection	){

			try {
				statement = m_connection.createStatement();
				statement.setQueryTimeout(Consts.DATABASE_TIMEOUT);
				
				ResultSet rs = statement.executeQuery( "select section, item, type, value from config;");
				while ( rs.next() ) {
					String section = rs.getString("section");
					String item = rs.getString("item");
					String type = rs.getString("type");
					String value = rs.getString("value");
					
					if( type.equals(Consts.CONFIG_TYPE_STRING) ){
						config = new Config(section, item , value);
					}
					else if( type.equals(Consts.CONFIG_TYPE_INTEGER) ){
						config = new Config(section, item, Integer.parseInt(value));
					}
					else {
						m_logger.error("Unable to get config from database. Invalid type ("+ type +") or section ("+ section +") and item ("+ item +") invalid.");
					}
					ret_configs.add(config);
				}
				
			} catch (SQLException e) {
				m_logger.error("Unable to retrive configurations. "+e.getMessage());
				StdOutErrLog.tieSystemOutAndErrToLog();
				e.printStackTrace();
			}
		}
		else{
			m_logger.error("Unable to retrive configs from database, the connection is not ready.");
		}
		
		return ret_configs;
	}
	
	/**
	 * Return all the configurations.
	 * 
	 * @return A Config object representing the configuration.
	 */
	public boolean setConfigs(String _section, String _item, String _value){
		Statement statement = null;
		boolean ret = false;
		
		// check if connection is ready to get config.
		if(true == m_isInit &&
		   null != m_connection	){

			try {
				statement = m_connection.createStatement();
				statement.setQueryTimeout(Consts.DATABASE_TIMEOUT);
				
				String sql = "UPDATE config SET value = '" + _value + "' WHERE section = '"+ _section + "' AND item = '"+_item+"';";
				statement.executeUpdate(sql);
				ret = true;
				
			} catch (SQLException e) {
				m_logger.error("Unable to retrive configurations. "+e.getMessage());
				StdOutErrLog.tieSystemOutAndErrToLog();
				e.printStackTrace();
			}
		}
		else{
			m_logger.error("Unable to retrive configs from database, the connection is not ready.");
		}
		
		return ret;
	}
}
