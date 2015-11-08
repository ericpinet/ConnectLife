/**
 *  RaspberryPiGpio.java
 *  coreserver
 *
 *  Created by ericpinet on 2015-11-04.
 *  Copyright (c) 2015 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.connectlife.coreserver.gpio;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

/**
 * 
 * 
 * @author ericpinet
 * <br> 2015-11-04
 */
public class RaspberryPiGpio implements Gpio {
	
	/**
	 * Logger use for this class.
	 */
	private final Logger m_logger = LogManager.getLogger(RaspberryPiGpio.class);

	/**
	 * @return
	 * @see com.connectlife.coreserver.gpio.Gpio#init()
	 */
	@Override
	public boolean init() {
		
		// TODO: GPIO 

		/*
		m_logger.info("<--Pi4J--> GPIO Control Example ... started.");
        
        // create gpio controller
        final GpioController gpio = GpioFactory.getInstance();
        
        // provision gpio pin #01 as an output pin and turn on
        final GpioPinDigitalOutput pin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_00, "MyLED", PinState.HIGH);

        // set shutdown state for this pin
        pin.setShutdownOptions(true, PinState.LOW);

        m_logger.info("--> GPIO state should be: ON");

        try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        
        // turn off gpio pin #01
        pin.low();
        m_logger.info("--> GPIO state should be: OFF");

        try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

        // toggle the current state of gpio pin #01 (should turn on)
        pin.toggle();
        m_logger.info("--> GPIO state should be: ON");

        try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

        // toggle the current state of gpio pin #01  (should turn off)
        pin.toggle();
        m_logger.info("--> GPIO state should be: OFF");
        
        try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

        // turn on gpio pin #01 for 1 second and then off
        m_logger.info("--> GPIO state should be: ON for only 1 second");
        pin.pulse(1000, true); // set second argument to 'true' use a blocking call
        
        // stop all GPIO activity/threads by shutting down the GPIO controller
        // (this method will forcefully shutdown all GPIO monitoring threads and scheduled tasks)
        gpio.shutdown();
        */

		return false;
	}

	/**
	 * @return
	 * @see com.connectlife.coreserver.gpio.Gpio#isInit()
	 */
	@Override
	public boolean isInit() {
		// TODO Auto-generated method stub
		return false;
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
