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
				// TODO Auto-generated catch block
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
						m_logger.warn("Database version "+ DatabaseStructure.VERSION +" is good.");
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
			}
			
			
			ret_val = true;
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	    finally{
	    	try{
		    	if(null != statement)
		    		statement.close();
	    	}
	    	catch(SQLException e){
	    		// statement close failed.
	    		System.err.println(e);
	    	}
		}
		
		return ret_val;
	}
	
	/**
	 * Return a configuration object.
	 * @param _name
	 * @return
	 */
	public Config getConfig(String _name){
		Config ret_config = null;
		Statement statement = null;
		
		// check if connection is ready to get config.
		if(true == m_isInit &&
		   null != m_connection	){
			
			
			try {
				statement = m_connection.createStatement();
				statement.setQueryTimeout(Consts.DATABASE_TIMEOUT);
				
				ResultSet rs = statement.executeQuery( "select value from config where name = '"+_name+"';" );
				while ( rs.next() ) {
					String value = rs.getString("value");
					ret_config = new Config(_name, value);
					
					// TODO - return value for integer
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else{
			m_logger.error("Unable to retrive config from database, the connection is not ready.");
		}
		
		return ret_config;
	}
	
}
