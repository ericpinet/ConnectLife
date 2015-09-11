/**
 *  EnvironmentManager.java
 *  coreserver
 *
 *  Created by ericpinet on 2015-09-09.
 *  Copyright (c) 2015 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.connectlife.coreserver.modules.environment;

// external
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

// internal
import com.connectlife.coreserver.Application;
import com.connectlife.coreserver.modules.Module;
import com.connectlife.coreserver.modules.environment.data.Address;
import com.connectlife.coreserver.modules.environment.data.Email;
import com.connectlife.coreserver.modules.environment.data.Environment;
import com.connectlife.coreserver.modules.environment.data.Person;
import com.connectlife.coreserver.modules.environment.data.PhoneNumber;
import com.connectlife.coreserver.modules.environment.data.Home;
import com.connectlife.coreserver.tools.errormanagement.StdOutErrLog;
import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.connectlife.coreserver.Consts;
import com.connectlife.coreserver.Consts.ModuleUID;
import com.connectlife.coreserver.modules.environment.UIDGenerator;

/**
 * Manager of the environnement of the automation.
 * 
 * @author ericpinet
 * <br> 2015-09-09
 */
public class EnvironmentManager implements Module {
	
	/**
	 * Logger use for this class.
	 */
	private static Logger m_logger = LogManager.getLogger(EnvironmentManager.class);

	/**
	 * Singleton reference for this class.
	 */
	private static EnvironmentManager m_ref = null;
	
	/**
	 * Flag to indicate if the module is correctly initialized.
	 */
	private boolean m_isInit;
	
	/**
	 * Environment data system. This is the main data 
	 * saved and loaded from environment data file.
	 */
	private Environment m_environment;
	
	/**
	 * Flag to indicate if the envrionment is loaded.
	 */
	private boolean m_is_loaded;
	
	/**
	 * Flag to indicate if the environment is saved.
	 */
	private boolean m_is_saved;
	
	/**
	 * Reference on the main application object.
	 */
	private Application m_app;
	
	/**
	 * Path for the environment file.
	 */
	private String m_path;
	
	/**
	 * Filename for the environment file.
	 */
	private String m_filename;
	
	/**
	 * Backup filename for the environment file.
	 */
	private String m_backup_filename;
	
	/**
	 * ModuleUID for the ApiServer.
	 */
	private static final ModuleUID m_moduleUID = ModuleUID.ENVIRONMENT_MANAGER;
	

	/**
	 * Default constructor of the environment.
	 */
	private EnvironmentManager(){
		
		m_app = Application.getInstance();
		m_path = m_app.getBasePath() + "/" + Consts.ENV_DATA_PATH + "/";
		m_filename = Consts.ENV_DATA_FILENAME;
		m_backup_filename = Consts.ENV_DATA_FILENAME_BACKUP;
		
		m_is_loaded = false;
		m_is_saved = false;
	}
	
	/**
	 * Return the instance of the EnvironmentManager for this application.
	 * 
	 * @return Singleton instance of the ApiServer.
	 */
	public static EnvironmentManager getInstance(){
		if(null == m_ref){
			m_ref = new EnvironmentManager();
		}
		return m_ref;
	}
	
	/**
	 * Initialization of the EnvironmentManager.
	 * 
	 * @return True if initialization completed with success.
	 */
	public boolean init(){
		boolean ret_val = false;
		
		m_logger.info("Initialization in progress ...");
		
		// check if environment directory exist and load if exist
		if( true == checkEnvironmentExist(m_path, m_filename) ){
			
			// file environment exist, try to load it.
			if( true == loadEnvironment(m_path, m_filename) ){
				// loading completed with success.
				ret_val = m_is_loaded = m_is_saved = m_isInit = true;
				m_logger.info("Environment loaded.");
			}
			else{
				// loading failed. 
				m_logger.error("Environment load failed! Try to load backup.");
				
				// try to load last backup
				if( true == loadEnvironment(m_path, m_backup_filename) ){
					
					m_logger.info("Environment backup loaded. Try to save the restored environment.");
					
					if(true == saveEnvironment(m_path, m_filename)){
						m_logger.info("Restore completed.");
						ret_val = m_is_loaded = m_is_saved = m_isInit = true;
					}
					else{
						m_logger.error("Unable to restore backup.");
					}
				}
				else{
					m_logger.error("Environment backup load failed!");
					// TODO contact support service.
				}
			}
		}
		else{
			// create the initial environment
			m_logger.info("No environment file exist. Create the base environment.");
			
			Environment temp_env = this.generateBaseEnvironnment();
			if(null != temp_env){
				
				m_environment = temp_env;
				
				if( true == saveEnvironment(m_path, m_filename) ){
					ret_val = m_is_loaded = m_is_saved = m_isInit = true;
					
					// generate a backup file
					if(false == saveEnvironment(m_path, m_backup_filename)){
						m_logger.warn("Unable to save backup environment file.");
					}
				}
			}
			else{
				m_logger.error("Unable to generate the base environment.");
			}
		}
		
		if( true==ret_val )
			m_logger.info("Initialization completed.");
		else
			m_logger.error("Initialization failed.");
		
		return ret_val;
	}

	/**
	 * Return True is the EnvironmentManager is correctly initialized.
	 */
	public boolean isInit() {
		return m_isInit;
	}
	
	/**
	 * Return True is the EnvironmentManager is correctly Loaded.
	 */
	public boolean isLoaded() {
		return m_is_loaded;
	}
	
	/**
	 * Return True is the EnvironmentManager is correctly saved.
	 */
	public boolean isSaved() {
		return m_is_saved;
	}

	/**
	 * UnInitialize the EnvironmentManager. Return in empty state ready to initialize again.
	 */
	public void unInit() {
		m_logger.info("UnInitialization in progress ...");
		
		// TODO UnInit Environment Manager

		m_logger.info("UnInitialization completed.");
	}
	
	/**
	 * Return a JSON string representing the environment.
	 * @return
	 */
	public String getJsonEnvironment(){
		String ret_val = "";
		if(true == m_isInit &&
		   null != m_environment){
			
			Gson gson = new Gson();
			ret_val = gson.toJson(m_environment);
		}
		return ret_val;
	}

	/**
	 * Return the moduleUID for the ApiServer.
	 */
	public ModuleUID getModuleUID() {
		return m_moduleUID;
	}
	
	/**
	 * Check if environment file already exist. 
	 * @param _path
	 * @param _filename
	 * @return True if environment file exist.
	 */
	private boolean checkEnvironmentExist(String _path, String _filename){
		
		boolean ret_val = false;
		
		File f = new File(m_path, m_filename);
		if(f.exists() && !f.isDirectory()){
			ret_val = true;
		}
		
		return ret_val;
			
	}
	
	/**
	 * Load environment data from file.
	 * 
	 * @param _path
	 * @param _filename
	 * @return True if the load successed.
	 */
	private boolean loadEnvironment(String _path, String _filename){
		boolean ret_value = false;

		m_logger.info("Loading environment file: " + _path + _filename + "...");
		
		BufferedReader br;
		try {
			
			br = new BufferedReader( new FileReader( _path + _filename) );
			
			// load json file and convert to object
			Gson gson = new Gson();
			Environment env_obj = gson.fromJson(br, Environment.class);
			
			if(null != env_obj){
				m_environment = env_obj;
				ret_value = true;
			}
			else{
				m_logger.warn("Nothing to load in environment file : " + _path + _filename);
			}
			
		} 
		catch (FileNotFoundException e) {
			m_logger.error(e.getMessage());
			StdOutErrLog.tieSystemOutAndErrToLog();
			e.printStackTrace();
		}
		catch (JsonParseException e){
			m_logger.error(e.getMessage());
			StdOutErrLog.tieSystemOutAndErrToLog();
			e.printStackTrace();
		}
		catch (Exception e){
			m_logger.error(e.getMessage());
			StdOutErrLog.tieSystemOutAndErrToLog();
			e.printStackTrace();
		}
			     
		return ret_value;
	}
	
	/**
	 * Save the environment in file.
	 * 
	 * @param _path
	 * @param _filename
	 * @return True if save succeded.
	 */
	private boolean saveEnvironment(String _path, String _filename){
		
		boolean ret_value = false;
		
		if( null != m_environment){
		
			Gson gson = new Gson();
			String json = gson.toJson(m_environment);  
			
			try {  
				
				m_logger.info("Saving file environment: " + _path + _filename);
				
				// ensure the path exist before saving file
				File directory = new File(_path);
				directory.mkdirs();
				
				FileWriter writer = new FileWriter( _path + _filename);  
				writer.write(json);  
				writer.close();
			   
				ret_value = true;
			} 
			catch (IOException e) {  
				m_logger.error(e.getMessage());
				StdOutErrLog.tieSystemOutAndErrToLog();
				e.printStackTrace(); 
			}
		}
		else{
			m_logger.info("Notthing to save in environment");
		}
		  
		return ret_value;
	}
	
	/**
	 * Generate the base environment on new system. 
	 * @param _environment Out variable. The generated environment in set in this parameter.
	 * @return True if the environment are correctly generated.
	 */
	private Environment generateBaseEnvironnment(){
		Environment ret_env = null;
		
		Person[] persons = {new Person(	UIDGenerator.getUID(), 
											"Eric", 
											"Pinet", 
											new Email[]{new Email("pineri01@gmail.com", Email.Type.PERSONAL)}, 
											new PhoneNumber[]{new PhoneNumber("418 998-2481",PhoneNumber.Type.CELL)}, 
											new Address[]{ new Address("2353 rue du cuir", "Québec", "Québec", "G3E 0G3", "Canada") }, 
											""),
							new Person(	UIDGenerator.getUID(), 
									"Qiaomei", 
									"Wang", 
									new Email[]{new Email("qiaomei.wang.wqm@gmail.com", Email.Type.PERSONAL)}, 
									new PhoneNumber[]{new PhoneNumber("438 348-1699",PhoneNumber.Type.CELL)}, 
									new Address[]{ new Address("2353 rue du cuir", "Québec", "Québec", "G3E 0G3", "Canada") }, 
									"")};
		
		Home[] homes = {new Home(UIDGenerator.getUID(), "Home", "", null)};
		
		ret_env = new Environment( persons, homes );
		
		return ret_env;
	}
}
