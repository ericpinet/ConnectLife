/**
 *  SimulatorGpio.java
 *  coreserver
 *
 *  Created by ericpinet on 2015-11-04.
 *  Copyright (c) 2015 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.connectlife.coreserver.gpio;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xnap.commons.i18n.I18n;

import com.connectlife.coreserver.Application;

/**
 * SimulatorGpio controller. This class simulate the access of the GPIO on Raspberry PI.
 * 
 * @author ericpinet
 * <br> 2015-11-04
 */
public class SimulatorGpio implements Gpio {
	
	/**
	 * Logger use for this class.
	 */
	private final Logger m_logger = LogManager.getLogger(getClass().getName());
	
	/**
	 * Initialization of translation system.
	 */
	private static I18n i18n = Application.i18n;
	
	/**
	 * Is initialized
	 */
	private boolean m_isInit;

	/**
	 * @return True if the initialization is completed.
	 * @see com.connectlife.coreserver.gpio.Gpio#init()
	 */
	@Override
	public boolean init() {
		
		// Init the simulation of GPIO
		m_logger.info(i18n.tr("Simulation of the GPIO."));
		
		return m_isInit = true;
	}

	/**
	 * @return True if is initialized.
	 * @see com.connectlife.coreserver.gpio.Gpio#isInit()
	 */
	@Override
	public boolean isInit() {
		
		return m_isInit;
	}

	/**
	 * 
	 * @see com.connectlife.coreserver.gpio.Gpio#unInit()
	 */
	@Override
	public void unInit() {
		
		m_isInit = false;
	}
}
