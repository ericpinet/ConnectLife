/**
 *  RaspberryPiGpio.java
 *  coreserver
 *
 *  Created by ericpinet on 2015-11-04.
 *  Copyright (c) 2015 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.connectlife.coreserver.gpio;

import java.util.concurrent.Callable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xnap.commons.i18n.I18n;

import com.connectlife.coreserver.Application;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.gpio.trigger.GpioCallbackTrigger;

/**
 * RaspberryPiGpio controller. This class manage the access of the GPIO on Raspberry PI.
 * 
 * @author ericpinet
 * <br> 2015-11-04
 */
public class RaspberryPiGpio implements Gpio {
	
	/**
	 * Logger use for this class.
	 */
	private final Logger m_logger = LogManager.getLogger(getClass().getName());
	
	/**
	 * Initialization of translation system.
	 */
	private static I18n i18n = Application.i18n;

	/**
	 * @return True if the initialization is completed.
	 * @see com.connectlife.coreserver.gpio.Gpio#init()
	 */
	@Override
	public boolean init() {
		
		m_logger.info(i18n.tr("<--Pi4J--> GPIO Control Example ... started."));
        
        // create gpio controller
        final GpioController gpio = GpioFactory.getInstance();
        
        // provision gpio pin #01 as an output pin and turn on
        final GpioPinDigitalOutput pin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_00, "MyLED", PinState.LOW);
        
        // provision gpio pin #02 as an output pin and turn on
        final GpioPinDigitalOutput pin2 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_02, "MyLED2", PinState.LOW);
        
        // provision gpio pin #02 as an input pin with its internal pull down resistor enabled
        final GpioPinDigitalInput myButton = gpio.provisionDigitalInputPin(RaspiPin.GPIO_03, PinPullResistance.PULL_DOWN);

        /*
        // set shutdown state for this pin
        pin.setShutdownOptions(true, PinState.LOW);
        pin2.setShutdownOptions(true, PinState.LOW);

        m_logger.info("--> GPIO state should be: ON");

        try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        
        // turn off gpio pin #01
        pin.low();
        pin2.low();
        m_logger.info("--> GPIO state should be: OFF");

        try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

        // toggle the current state of gpio pin #01 (should turn on)
        pin.toggle();
        pin2.toggle();
        m_logger.info("--> GPIO state should be: ON");

        try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

        // toggle the current state of gpio pin #01  (should turn off)
        pin.toggle();
        pin2.toggle();
        m_logger.info("--> GPIO state should be: OFF");
        
        try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

        // turn on gpio pin #01 for 1 second and then off
        m_logger.info("--> GPIO state should be: ON for only 1 second");
        pin.pulse(250, true); // set second argument to 'true' use a blocking call
        pin2.pulse(500, true);// set second argument to 'true' use a blocking call
        */
        System.out.println(" --> GPIO TRIGGER READY ");
        pin2.toggle();
        
        // create a gpio callback trigger on gpio pin#4; when #4 changes state, perform a callback
        // invocation on the user defined 'Callable' class instance
        myButton.addTrigger(new GpioCallbackTrigger(new Callable<Void>() {
            public Void call() throws Exception {
                System.out.println(" --> GPIO TRIGGER CALLBACK RECEIVED ");

                if( myButton.isHigh() ){
                	pin.toggle();
                	pin2.toggle();
                }
                else{
                	pin.toggle();
                	pin2.toggle();
                }
                return null;
            }
        }));
        
        try {
			Thread.sleep(60000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        
        System.out.println(" --> GPIO TRIGGER ENDED ");
        
        // stop all GPIO activity/threads by shutting down the GPIO controller
        // (this method will forcefully shutdown all GPIO monitoring threads and scheduled tasks)
        gpio.shutdown();

		return true;
	}

	/**
	 * @return True if initialized.
	 * @see com.connectlife.coreserver.gpio.Gpio#isInit()
	 */
	@Override
	public boolean isInit() {
		return false;
	}

	/**
	 * 
	 * @see com.connectlife.coreserver.gpio.Gpio#unInit()
	 */
	@Override
	public void unInit() {
	}
}
