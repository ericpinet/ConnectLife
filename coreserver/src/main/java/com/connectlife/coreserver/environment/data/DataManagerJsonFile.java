/**
 *  DataManagerJsonFile.java
 *  coreserver
 *
 *  Created by ericpinet on 2016-03-28.
 *  Copyright (c) 2016 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.connectlife.coreserver.environment.data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.clapi.data.Accessory;
import com.clapi.data.Data;
import com.clapi.data.Home;
import com.clapi.data.Room;
import com.clapi.data.Zone;
import com.connectlife.coreserver.Application;
import com.connectlife.coreserver.tools.errormanagement.StdOutErrLog;
import com.google.gson.Gson;
import com.google.gson.JsonParseException;

/**
 * Data manager for environment using Json file.
 * 
 * @author ericpinet
 * <br> 2016-03-28
 */
public class DataManagerJsonFile implements DataManager {
	
	/**
	 * Logger use for this class.
	 */
	private static Logger m_logger = LogManager.getLogger(DataManagerJsonFile.class);
	
	/**
	 * Environment data path contain the data representing the user, home, 
	 * zones, rooms, accessories
	 */
	private static final String ENV_DATA_PATH = "data";
	
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
	 * Environment data system. This is the main data 
	 * saved and loaded from environment data file.
	 */
	private Data m_data;
	
	/**
	 * Path for the environment file.
	 */
	private String m_path;
	
	/**
	 * Flag to indicate if the environment is loaded.
	 */
	private boolean m_is_loaded;
	
	/**
	 * Flag to indicate if the environment is saved.
	 */
	private boolean m_is_saved;
	
	/**
	 * Flag to indicate if the module is correctly initialized.
	 */
	private boolean m_is_init;
	
	/**
	 * Default constructor.
	 */
	public DataManagerJsonFile(){
		m_path = "";
		m_is_loaded = false;
		m_is_saved = false;
		m_is_init = false;
	}
	
	/**
	 * Initialization the data manager.
	 * 
	 * @return True if initialization completed with success.
	 */
	public boolean init(){
		boolean ret_val = false;
		
		m_logger.info("Initialization in progress ...");
		
		m_path = Application.getApp().getBasePath() + "/" + ENV_DATA_PATH + "/";
		ret_val = true;
		
		return m_is_init = ret_val;
	}

	/**
	 * Return True is the data manager is correctly initialized.
	 * 
	 * @return True if the data manager is correctly initialized.
	 */
	public boolean isInit(){
		return m_is_init;
	}

	/**
	 * UnInitialize the data manager. Return in empty state ready to initialize again.
	 */
	public void unInit(){
		m_is_init = false;
	}
	
	/**
	 * Return True is the EnvironmentJsonFile is correctly Loaded.
	 * 
	 * @return True if this module is loaded.
	 */
	public boolean isLoaded() {
		return m_is_loaded;
	}
	
	/**
	 * Return True is the EnvironmentJsonFile is correctly saved.
	 * 
	 * @return True is the environmment is saved.
	 */
	public boolean isSaved() {
		return m_is_saved;
	}
	
	/**
	 * Indicate that the environment file isn't saved.
	 */
	public void setUnsaved()
	{
		m_is_saved = false;
	}
	
	
	/**
	 * Check if environment file already exist. 
	 * 
	 * @return True if environment file exist.
	 */
	public boolean checkEnvironmentExist(){
		
		boolean ret_val = false;
		
		File f = new File(m_path, ENV_DATA_FILENAME);
		if(f.exists() && !f.isDirectory()){
			ret_val = true;
		}
		
		return ret_val;
			
	}
	
	/**
	 * Load environment data from file.
	 * 
	 * @return True if the load successed.
	 */
	public boolean loadEnvironment(){
		return loadEnvironment(false); // false for load from main file.
	}
	
	/**
	 * Load environment data from the backup.
	 * 
	 * @return True if the load successes.
	 */
	public boolean loadEnvironmentBackup(){
		return loadEnvironment(true); // true for load from backup file.
	}
	
	/**
	 * Load environment data from file.
	 * 
	 * @param _backup True to load from backup, False to load from main file.
	 * @return True if the load successed.
	 */
	private boolean loadEnvironment(boolean _backup){
		boolean ret_value = false;
		
		String filename = "";
		
		if( false == _backup )
			filename = ENV_DATA_FILENAME;
		else
			filename = ENV_DATA_FILENAME_BACKUP;

		m_logger.info("Loading environment file: " + m_path + filename + "...");
		
		BufferedReader br;
		try {
			
			br = new BufferedReader( new FileReader( m_path + filename) );
			
			// load json file and convert to object
			Gson gson = new Gson();
			Data env_obj = gson.fromJson(br, Data.class);
			
			if(null != env_obj){
				m_data = env_obj;
				ret_value = true;
			}
			else{
				m_logger.warn("Nothing to load in environment file : " + m_path + filename);
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
			     
		return m_is_loaded = ret_value;
	}
	
	/**
	 * Save the environment file
	 * 
	 * @return True if save succeed.
	 * @see com.connectlife.coreserver.environment.data.DataManager#saveEnvironment()
	 */
	public boolean saveEnvironment(){
		return saveEnvironment(false); // false for the backup
	}
	
	/**
	 * Save the backup environment file
	 * 
	 * @return True if save succeed.
	 * @see com.connectlife.coreserver.environment.data.DataManager#saveEnvironment()
	 */
	public boolean saveEnvironmentBackup(){
		return saveEnvironment(true); // true for the backup
	}
	
	/**
	 * Save the environment in file.
	 * 
	 * @param _backup True if you want save the backup file. False for save the main file.
	 * @return True if save succeed.
	 */
	private boolean saveEnvironment(boolean _backup){
		
		boolean ret_value = false;
		String filename = "";
		
		if( false == _backup )
			filename = ENV_DATA_FILENAME;
		else
			filename = ENV_DATA_FILENAME_BACKUP;
		
		if( null != m_data){
			
			// prepare the data to save.
			// remove not valuable field to save.
			Data data_to_save = prepareSave();
		
			// convert to json.
			Gson gson = new Gson();
			String json = gson.toJson(data_to_save);  
			
			try {  
				
				m_logger.info("Saving file environment: " + m_path + filename);
				
				// ensure the path exist before saving file
				File directory = new File(m_path);
				directory.mkdirs();
				
				FileWriter writer = new FileWriter( m_path + filename);  
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
		  
		return m_is_saved = ret_value;
	}
	
	/**
	 * Return the data prepared to save. Remove all field that we don't want o save.
	 * 
	 * @return Data prepared to save.
	 */
	private Data prepareSave(){
		Data ret_data = null;
		
		// Get clone of data environment.
		ret_data = m_data;
		
		// Step to prepare the data to save:
		//   1) Put all accessory register at false, Register indicate that accessory is correctly connected on the server.
		//
		
		// iterate in home
		Iterator<Home> ihome = m_data.getHomes().iterator();
		while(ihome.hasNext()){
			Home home = ihome.next();
			
			// iterate in zone
			Iterator<Zone> izone = home.getZones().iterator();
			while(izone.hasNext()){
				Zone zone = izone.next();
				
				// iterate in room
				Iterator<Room> iroom = zone.getRooms().iterator();
				while(iroom.hasNext()){
					Room room = iroom.next();
					
					// iterate in accessory
					Iterator<Accessory> iaccessory = room.getAccessories().iterator();
					while(iaccessory.hasNext()){
						Accessory accessory = iaccessory.next();
						
						accessory.setRegister(false);
						
					}// WHILE: Accessories
				}// WHILE: Rooms
			}// WHILE: Zones
		}// WHILE: Homes
		
		return ret_data;
	}

	/**
	 * Return the data environment.
	 * 
	 * @return Environment data.
	 * @see com.connectlife.coreserver.environment.data.DataManager#getData()
	 */
	@Override
	public Data getData() {
		return m_data;
	}

	/**
	 * Set new data environment.
	 * 
	 * @param _data Set the new data environment.
	 * @see com.connectlife.coreserver.environment.data.DataManager#setData(com.clapi.data.Data)
	 */
	@Override
	public void setData(Data _data) {
		m_data = _data;
	}

}
