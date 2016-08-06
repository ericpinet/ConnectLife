/**
 *  ApplicationStateMachine.java
 *  coreserver
 *
 *  Created by ericpinet on 2015-09-11.
 *  Copyright (c) 2015 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.connectlife.coreserver;


// external
import au.com.ds.ef.*;
import au.com.ds.ef.call.*;

import static au.com.ds.ef.FlowBuilder.*;
import static com.connectlife.coreserver.ApplicationStateMachine.Events.*;
import static com.connectlife.coreserver.ApplicationStateMachine.States.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xnap.commons.i18n.I18n;
import org.xnap.commons.i18n.I18nFactory;

// internal

/**
 * 
 * 
 * @author ericpinet
 * <br> 2015-09-11
 */
public class ApplicationStateMachine {
	
	/**
	 * Init logger instance for this class
	 */
	private static Logger m_logger = LogManager.getLogger(ApplicationStateMachine.class);
	
	/**
	 * Initialization of translation system.
	 */
	private static I18n i18n = I18nFactory.getI18n(ApplicationStateMachine.class);
	
	/**
	 * Flow Context for the state machine.
	 */
	private static class FlowContext extends StatefulContext {

		/**
		 * Serial version UID
		 */
		private static final long serialVersionUID = 1L;
        // adding member variables
    }
	
	/**
	 * List of state for this state machine.
	 */
	public enum States implements StateEnum {
        WAITING,
        EXECUTE_TRIGGER,
        EXECUTE_USER_ACTION,
        TERMINATED
    }
	
	/**
	 * List of event possible in the state machine.
	 */
	public enum Events implements EventEnum {
        onTrigger, 
        onUserAction,
        onEndExecuteTrigger,
        onEndExecuteUserAction,
        onCloseApp
    }
	
	/**
	 * EasyFlow context to drive state machine.
	 */
	private EasyFlow<FlowContext> m_flow;
	
	/**
	 * Start the state machine.
	 */
	public void start(){
		this.initFlow();
        this.bindFlow();
        this.startFlow();
	}
	
	/**
	 * Start state machine. This methode return immediatly. 
	 * State machine started in different thread.
	 */
	private void startFlow() {
		m_logger.info(i18n.tr("State machine starting ..."));
        m_flow.start(new FlowContext());
    }
	
	/**
	 * Init the state machine.
	 */
	private void initFlow() {
        m_flow =
            from(WAITING).transit(
                on(onTrigger).to(EXECUTE_TRIGGER).transit(
                    on(onEndExecuteTrigger).to(WAITING)
                ),
                on(onCloseApp).finish(TERMINATED)
            );
    }
	
	/**
	 * Bind state of the state machine.
	 */
	private void bindFlow() {
        m_flow 

            .whenEnter(WAITING, new ContextHandler<FlowContext>() {
	            public void call(final FlowContext context) throws Exception {
	            	
	            	Thread.sleep(5000);
	            	
	            	// check if trigger must be raise
	            	
	            	// check if user do an action to perform
	            	
	            	// terminated
	            	
	            	context.trigger(onTrigger);
	            }
	        })// END WAITING
        
	        .whenEnter(EXECUTE_TRIGGER, new ContextHandler<FlowContext>() {
	            public void call(final FlowContext context) throws Exception {

	            	Thread.sleep(5000);
	            	context.trigger(onEndExecuteTrigger);
	            	
	            }
	        });// END EXECUTE TRIGGER
	}
}
