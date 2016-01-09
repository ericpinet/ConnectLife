/**
 *  GpioMock.java
 *  coreserver
 *
 *  Created by ericpinet on 2015-11-04.
 *  Copyright (c) 2015 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.connectlife.test.coreserver;

import com.connectlife.coreserver.gpio.Gpio;

/**
 * 
 * 
 * @author ericpinet
 * <br> 2015-11-04
 */
public class GpioMock implements Gpio {

	/**
	 * @return
	 * @see com.connectlife.coreserver.gpio.Gpio#init()
	 */
	@Override
	public boolean init() {
		// TODO Auto-generated method stub
		return true;
	}

	/**
	 * @return
	 * @see com.connectlife.coreserver.gpio.Gpio#isInit()
	 */
	@Override
	public boolean isInit() {
		// TODO Auto-generated method stub
		return true;
	}

	/**
	 * 
	 * @see com.connectlife.coreserver.gpio.Gpio#unInit()
	 */
	@Override
	public void unInit() {
		// TODO Auto-generated method stub

	}

}
