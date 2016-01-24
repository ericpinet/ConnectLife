/**
 *  ConfigMock.java
 *  coreserver
 *
 *  Created by ericpinet on 2015-10-10.
 *  Copyright (c) 2015 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.connectlife.test.coreserver;

import java.util.List;

import com.connectlife.coreserver.config.Config;
import com.connectlife.coreserver.config.ConfigItem;

/**
 * 
 * 
 * @author ericpinet
 * <br> 2015-10-10
 */
public class ConfigMock implements Config {
	
	private boolean m_is_init;
	
	public ConfigMock(){
		m_is_init = false;
	}
	
	/**
	 * @return
	 * @see com.connectlife.coreserver.modules.Module#init()
	 */
	@Override
	public boolean init() {
		return m_is_init = true;
	}

	/**
	 * @return
	 * @see com.connectlife.coreserver.modules.Module#isInit()
	 */
	@Override
	public boolean isInit() {
		return m_is_init;
	}

	/**
	 * 
	 * @see com.connectlife.coreserver.modules.Module#unInit()
	 */
	@Override
	public void unInit() {
		m_is_init = false;
	}

	/**
	 * @param _section
	 * @param _item
	 * @return
	 * @see com.connectlife.coreserver.config.Config#getConfig(java.lang.String, java.lang.String)
	 */
	@Override
	public ConfigItem getConfig(String _section, String _item) {
		return null;
	}

	/**
	 * @param _object
	 * @return
	 * @see com.connectlife.coreserver.config.Config#setConfig(com.connectlife.coreserver.config.ConfigItem)
	 */
	@Override
	public boolean setConfig(ConfigItem _object) {
		return false;
	}

	/**
	 * @return
	 * @see com.connectlife.coreserver.config.Config#getConfigs()
	 */
	@Override
	public List<ConfigItem> getConfigs() {
		return null;
	}

	/**
	 * @return
	 * @see com.connectlife.coreserver.config.Config#RestoreFactory()
	 */
	@Override
	public boolean RestoreFactory() {
		return false;
	}
}
