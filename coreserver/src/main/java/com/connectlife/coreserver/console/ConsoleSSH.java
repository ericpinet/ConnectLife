/**
 *  ConsoleSSH.java
 *  coreserver
 *
 *  Created by ericpinet on 2015-09-11.
 *  Copyright (c) 2015 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.connectlife.coreserver.console;

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

import com.connectlife.coreserver.config.Config;
import com.connectlife.coreserver.config.ConfigItem;
import com.connectlife.coreserver.tools.errormanagement.StdOutErrLog;
import com.google.inject.Inject;

/**
 * Manager of the console sshd daemon.
 * 
 * @author ericpinet
 * <br> 2015-09-11
 */
public class ConsoleSSH implements Console {
	
	/**
	 * Logger use for this class.
	 */
	private static Logger m_logger = LogManager.getLogger(ConsoleSSH.class);
	
	/**
	 * Hostkey for the SSH server.
	 */
	private static final String HOSTKEY = "hostkey.ser";
	
	/**
	 * Config manager of the application.
	 */
	private final Config m_config;

	/**
	 * Flag to indicate if the module is correctly initialized.
	 */
	private boolean m_isInit;
	
	/**
	 * Server SSHD.
	 */
	private SshServer m_sshd;
	
	/**
	 * Default constructor.
	 * 
	 * @param _config Config manager for
	 */
	@Inject
	public ConsoleSSH(Config _config){
		m_config = _config;
	}

	/**
	 * Initialization of the console manager of the application.
	 * 
	 * @return True if initialization is completed correctly. 
	 */
	@SuppressWarnings("unchecked")
	public boolean init() {
		
		boolean ret_val = false;
		
		m_logger.info("Initialization in progress ...");
		
		ConfigItem tcpip_port 		= m_config.getConfig("CONSOLE", "TCPIP_PORT");
		
		
		m_logger.info("TCP/IP Port: " + tcpip_port.getIntegerValue());
		
		m_sshd = SshServer.setUpDefaultServer();
		m_sshd.setPort(tcpip_port.getIntegerValue());
		m_sshd.setKeyPairProvider(new SimpleGeneratorHostKeyProvider(HOSTKEY));
		
		m_sshd.setShellFactory(new InAppShellFactory());
	
		List<NamedFactory<UserAuth>> userAuthFactories = new ArrayList<NamedFactory<UserAuth>>();
		userAuthFactories.add(new UserAuthPassword.Factory());
		m_sshd.setUserAuthFactories(userAuthFactories);

		m_sshd.setPasswordAuthenticator(new PasswordAuthenticator() {
		    public boolean authenticate(String username, String password, ServerSession session) {
		    	
		    	boolean ret_val = false;
		    	ConfigItem admin_username 	= m_config.getConfig("CONSOLE", "ADMIN_USERNAME");
				ConfigItem admin_password 	= m_config.getConfig("CONSOLE", "ADMIN_PASSWORD");
				
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
	 * UnInitialize the console.
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
}
