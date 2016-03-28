/**
 *  DataManager.java
 *  coreserver
 *
 *  Created by ericpinet on 2016-03-28.
 *  Copyright (c) 2016 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.connectlife.coreserver.environment;

import com.clapi.data.Data;

/**
 * Data manager for the environment. 
 * 
 * @author ericpinet
 * <br> 2016-03-28
 */
public interface DataManager {
	
	/**
	 * Initialization the data manager.
	 * 
	 * @return True if initialization completed with success.
	 */
	public boolean init();

	/**
	 * Return True is the data manager is correctly initialized.
	 * 
	 * @return True if the data manager is correctly initialized.
	 */
	public boolean isInit();

	/**
	 * UnInitialize the data manager. Return in empty state ready to initialize again.
	 */
	public void unInit();
	
	/**
	 * Check if environment data already exist in the system. 
	 * If not, we have to create a new one, else load the existing environment. 
	 * 
	 * @return True if environment file exist.
	 */
	public boolean checkEnvironmentExist();
	
	/**
	 * Load environment data.
	 * 
	 * @return True if the load successes.
	 */
	public boolean loadEnvironment();
	
	/**
	 * Load environment data from the backup.
	 * 
	 * @return True if the load successes.
	 */
	public boolean loadEnvironmentBackup();
	
	/**
	 * Save the environment in data.
	 * 
	 * @return True if save successes.
	 */
	public boolean saveEnvironment();
	
	/**
	 * Save the backup environment file
	 * 
	 * @return True if save succeed.
	 */
	public boolean saveEnvironmentBackup();
	
	/**
	 * Indicate that the environment file isn't saved.
	 */
	public void setUnsaved();
	
	/**
	 * Return True is the environment data is correctly saved.
	 * 
	 * @return True is the environment is saved.
	 */
	public boolean isSaved();
	
	/**
	 * Return True is the environment data is correctly Loaded.
	 * 
	 * @return True if this module is loaded.
	 */
	public boolean isLoaded();
	
	/**
	 * Return the Data environment.
	 * 
	 * @return Data of the environment.
	 */
	public Data getData();
	
	/**
	 * Set the new Data environment.
	 * 
	 * @param _data Set the new data environment.
	 * @return Data of the environment.
	 */
	public void setData(Data _data);
	
}
