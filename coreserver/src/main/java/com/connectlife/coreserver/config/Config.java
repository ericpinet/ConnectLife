/**
 *  Config.java
 *  coreserver
 *
 *  Created by ericpinet on 2015-10-17.
 *  Copyright (c) 2015 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.connectlife.coreserver.config;

import java.util.List;

/**
 * Interface of the manager of configuration in the application. 
 * This manager can store and reload configuration.
 * 
 * @author ericpinet
 * <br> 2015-10-17
 */
public interface Config {
	
	/**
	 * Initialization of the module
	 * 
	 * @return True if initialization completed correctly.
	 */
	public boolean init();
	
	/**
	 * Return if the module is correctly running.
	 * 
	 * @return True if the module is correctly running.
	 */
	public boolean isInit();
	
	/**
	 * Unload the module. After, it's possible to init again. 
	 */
	public void unInit();
	
	/**
	 * Return a ConfigItem object that correspond to this section and item.
	 * 
	 * @param _section 	Section of the configuration.
	 * @param _item		Item of the configuraiton.
	 * @return ConfigItem object or null.
	 */
	public ConfigItem getConfig(String _section, String _item);
	
	/**
	 * Return all ConfigItem object in the application.
	 * 
	 * @return List of ConfigItem or null.
	 */
	public List<ConfigItem> getConfigs();
	
	/**
	 * Set new value for a config and save.
	 * 
	 * @param _object ConfigItem objecto to save in configuration.
	 * @return True if configuration was correctly updated.
	 */
	public boolean setConfig(ConfigItem _object);
	
	/**
	 * Restore the factory configurations of system.
	 * 
	 * @return True if the restoration is completed successfully.
	 */
	public boolean RestoreFactory();

}
