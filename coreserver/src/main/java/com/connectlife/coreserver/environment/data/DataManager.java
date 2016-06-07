/**
 *  DataManager.java
 *  coreserver
 *
 *  Created by ericpinet on 2016-03-28.
 *  Copyright (c) 2016 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.connectlife.coreserver.environment.data;

import org.neo4j.graphdb.GraphDatabaseService;

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
	 * 
	 * @return True if environment file exist.
	 */
	public boolean checkEnvironmentExist();
	
	/**
	 * Return a JSON string representing the environment.
	 * 
	 * @return JSON string of the environment.
	 */
	public String getJsonEnvironment();
	
	/**
	 * Return a JSON formatted string representing the environment.
	 * 
	 * @return JSON string of the environment.
	 */
	public String getJsonFormattedEnvironment();
	
	/**
	 * Generate the base environment on new system.
	 * Must be executed if environment data isn't init.
	 * 
	 * @throws Exception If something goes wrong.
	 */
	public void generateBaseEnvironnment() throws Exception;
	
	/**
	 * Return the environment graph data.
	 * 
	 * @return Return the environment graph data.
	 * @throws Exception Throw exception if something goes wrong.
	 */
	public GraphDatabaseService getGraph() throws Exception;
	
}
