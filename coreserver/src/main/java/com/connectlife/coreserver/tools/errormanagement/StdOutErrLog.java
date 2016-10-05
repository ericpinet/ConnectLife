/**
 *  StdOutErrLog.java
 *  coreserver
 *
 *  Created by Eric Pinet on 2015-09-09.
 *  Copyright (c) 2015 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.connectlife.coreserver.tools.errormanagement;

// external
import java.io.PrintStream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This class permit to simply output stack trace in the log.
 * Exemple:
 * 
 * <pre>
 * {@code
 * try {
 *	    [...]
 *		
 * } catch (Exception e) {
 *    StdOutErrLog.tieSystemOutAndErrToLog();
 * }
 * }
 * </pre>
 * @author ericpinet
 * <br> 2015-09-09
 */
public abstract class StdOutErrLog {
	
	/**
	 * Init logger instance for this class
	 */
	private static Logger m_logger = LogManager.getLogger(StdOutErrLog.class);

	/**
	 * Call this methode before call exception.printStackTrace() to output the stacktrace in log.
	 */
	public static void tieSystemOutAndErrToLog() {
		System.setOut(createLoggingProxy(System.out));
	    System.setErr(createLoggingProxy(System.err));
	}

	/**
	 * Route the print and println in the log.
	 * @param realPrintStream The output stream.
	 * @return Printer stream.
	 */
	public static PrintStream createLoggingProxy(final PrintStream realPrintStream) {
	    return new PrintStream(realPrintStream) {
	        public void print(final String string) {
	        	m_logger.error(string);
	        }
	        public void println(final String string) {
	        	m_logger.error(string);
	        }
	    };
	}

}
