/**
 *  ConsoleManager.java
 *  coreserver
 *
 *  Created by ericpinet on 2015-09-11.
 *  Copyright (c) 2015 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.connectlife.coreserver.modules.console;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// external
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.sshd.SshServer;
import org.apache.sshd.common.NamedFactory;
import org.apache.sshd.server.PasswordAuthenticator;
import org.apache.sshd.server.UserAuth;
import org.apache.sshd.server.auth.UserAuthPassword;
import org.apache.sshd.server.keyprovider.SimpleGeneratorHostKeyProvider;
import org.apache.sshd.server.session.ServerSession;

// internal
import com.connectlife.coreserver.Consts;
import com.connectlife.coreserver.Consts.ModuleUID;
import com.connectlife.coreserver.modules.Module;
import com.connectlife.coreserver.modules.datamanager.Config;
import com.connectlife.coreserver.modules.datamanager.DataManager;
import com.connectlife.coreserver.tools.errormanagement.StdOutErrLog;

/**
 * Manager of the console sshd daemon.
 * 
 * @author ericpinet
 * <br> 2015-09-11
 */
public class ConsoleManager implements Module {
	
	/**
	 * Logger use for this class.
	 */
	private static Logger m_logger = LogManager.getLogger(ConsoleManager.class);

	/**
	 * Singleton reference for this class.
	 */
	private static ConsoleManager m_ref = null;
	
	/**
	 * Flag to indicate if the module is correctly initialized.
	 */
	private boolean m_isInit;
	
	/**
	 * Server SSHD.
	 */
	private SshServer m_sshd;

	/**
	 * Initialization of the console manager of the application.
	 * 
	 * @return True if initialization is completed correctly. 
	 * @see com.connectlife.coreserver.modules.Module#init()
	 */
	@SuppressWarnings("unchecked")
	public boolean init() {
		
		boolean ret_val = false;
		
		m_logger.info("Initialization in progress ...");
		
		Config tcpip_port 		= DataManager.getConfig("CONSOLE", "TCPIP_PORT");
		
		
		m_logger.info("TCP/IP Port: " + tcpip_port.getIntegerValue());
		
		m_sshd = SshServer.setUpDefaultServer();
		m_sshd.setPort(tcpip_port.getIntegerValue());
		m_sshd.setKeyPairProvider(new SimpleGeneratorHostKeyProvider("hostkey.ser"));
		
		m_sshd.setShellFactory(new InAppShellFactory());
	
		List<NamedFactory<UserAuth>> userAuthFactories = new ArrayList<NamedFactory<UserAuth>>();
		userAuthFactories.add(new UserAuthPassword.Factory());
		m_sshd.setUserAuthFactories(userAuthFactories);

		m_sshd.setPasswordAuthenticator(new PasswordAuthenticator() {
		    public boolean authenticate(String username, String password, ServerSession session) {
		    	
		    	boolean ret_val = false;
		    	Config admin_username 	= DataManager.getConfig("CONSOLE", "ADMIN_USERNAME");
				Config admin_password 	= DataManager.getConfig("CONSOLE", "ADMIN_PASSWORD");
				
		    	if( admin_username.getStringValue().equals(username) && admin_password.getStringValue().equals(password) ){
		    		ret_val = true;
		    		m_logger.info("Authentification succeed for the "+ username +".");
		    	}
		    	else{
		    		m_logger.info("Authentification failed for the "+ username +".");
		    	}
		        return ret_val;
		    }
		});
		
		try {
			m_sshd.start();
			ret_val = m_isInit = true;	
		} 
		catch (IOException e) {
			m_logger.error(e.getMessage());
			StdOutErrLog.tieSystemOutAndErrToLog();
			e.printStackTrace();
		}
	    
	    // return the right message if succeed or failed.
		if( true==ret_val )
			m_logger.info("Initialization completed.");
		else
			m_logger.error("Initialization failed.");
		
		return ret_val;
	}

	/**
	 * Return True if the console is correctly initialized.
	 */
	public boolean isInit() {
		return m_isInit;
	}

	/**
	 * 
	 * @see com.connectlife.coreserver.modules.Module#unInit()
	 */
	public void unInit() {
		
		m_logger.info("Uninitialization started ...");
		
		if(	true == m_isInit &&
			null != m_sshd ){
			
			try {
				m_sshd.stop(true);
				
				m_logger.info("Uninitialization completed.");
				
			} catch (InterruptedException e) {
				// Ignore
				m_logger.warn("Uninitialization interrupted.");
			} finally{
				m_sshd = null;
			}
		}
	}

	/**
	 * Return the module uid for the console manager.
	 * @return ModuleUID for this module.
	 * @see com.connectlife.coreserver.modules.Module#getModuleUID()
	 */
	public ModuleUID getModuleUID() {
		return Consts.ModuleUID.CONSOLE_MANAGER;
	}
	
	/**
	 * Return the instance of the ConsoleManager for this application
	 * 
	 * @return Singleton instance of the ConsoleManager.
	 */
	public static ConsoleManager getInstance(){
		if(null == m_ref){
			m_ref = new ConsoleManager();
		}
		return m_ref;
	}

}
