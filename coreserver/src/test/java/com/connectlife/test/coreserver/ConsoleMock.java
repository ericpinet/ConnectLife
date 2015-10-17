/**
 *  ConsoleMock.java
 *  coreserver
 *
 *  Created by ericpinet on 2015-10-10.
 *  Copyright (c) 2015 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.connectlife.test.coreserver;

import com.connectlife.coreserver.console.Console;


/**
* 
* 
* @author ericpinet
* <br> 2015-10-10
*/
public class ConsoleMock implements Console {
	
	private boolean m_is_init;
	
	public ConsoleMock(){
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
}