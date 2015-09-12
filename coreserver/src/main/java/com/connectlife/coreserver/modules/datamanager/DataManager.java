/**
 *  DataManager.java
 *  coreserver
 *
 *  Created by EricPinet <pineri01@gmail.com> on 2015-09-07.
 *  Copyright (c) 2015 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.connectlife.coreserver.modules.datamanager;

// external
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// internal
import com.connectlife.coreserver.modules.Module;
import com.connectlife.coreserver.Consts;
import com.connectlife.coreserver.Consts.ModuleUID;
import com.connectlife.coreserver.modules.datamanager.DatabaseStructure;
import com.connectlife.coreserver.tools.errormanagement.StdOutErrLog;
import com.connectlife.coreserver.modules.datamanager.Config;

/**
 * This is the data manager for this application. 
 * This class permit to read and write object in
 * SQLite database of the application.
 * 
 * @author Eric Pinet (pineri01@gmail.com)
 * <br> 2015-09-07
 */
public class DataManager implements Module {
	
	/**
	 * Logger use for this class.
	 */
	private static Logger m_logger = LogManager.getLogger(DataManager.class);
    
	/**
	 * Singleton reference for this class
	 */
	private static DataManager m_ref = null;
	
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
	 * Default DataManager constructor.
	 */
	private DataManager(){
		
	}
	
	/**
	 * Return the instance of the DataManager for this application
	 * 
	 * @return Singleton instance of the DataManager.
	 */
	public static DataManager getInstance(){
		if(null == m_ref){
			m_ref = new DataManager();
		}
		return m_ref;
	}
	
	/**
	 * Initialization of the DataManager. 
	 * Database file will be created if doesn't exist. 
	 * If exist, ensure that is valid.
	 * 
	 * @return True if the DataManager initialized correctly.
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
	 * Return True is the DataManager is correctly initialized.
	 */
	public boolean isInit() {
		return m_isInit;
	}

	/**
	 * UnInitialize the DataManager. Return in empty state ready to init again.
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
	 * @param _section 	Section of the configuration.
	 * @param _item		Item of the configuraiton.
	 * @return Config object or null.
	 */
	public static Config getConfig(String _section, String _item){
		return getInstance().loadConfig(_section, _item);
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
	
}
