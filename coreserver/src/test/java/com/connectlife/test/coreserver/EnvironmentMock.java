/**
 *  ConsoleMock.java
 *  coreserver
 *
 *  Created by ericpinet on 2015-10-10.
 *  Copyright (c) 2015 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.connectlife.test.coreserver;

import javax.jmdns.ServiceEvent;

import com.connectlife.coreserver.environment.Environment;

/**
* 
* 
* @author ericpinet
* <br> 2015-10-10
*/
public class EnvironmentMock implements Environment {
	
	private boolean m_is_init;
	
	public EnvironmentMock(){
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
	 * @return
	 * @see com.connectlife.coreserver.environment.Environment#isLoaded()
	 */
	@Override
	public boolean isLoaded() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * @return
	 * @see com.connectlife.coreserver.environment.Environment#isSaved()
	 */
	@Override
	public boolean isSaved() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * @return
	 * @see com.connectlife.coreserver.environment.Environment#getJsonEnvironment()
	 */
	@Override
	public String getJsonEnvironment() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @param _service
	 * @see com.connectlife.coreserver.environment.Environment#serviceDiscover(javax.jmdns.ServiceEvent)
	 */
	@Override
	public void serviceDiscover(ServiceEvent _service) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @param _service
	 * @see com.connectlife.coreserver.environment.Environment#serviceRemove(javax.jmdns.ServiceEvent)
	 */
	@Override
	public void serviceRemove(ServiceEvent _service) {
		// TODO Auto-generated method stub
		
	}

}