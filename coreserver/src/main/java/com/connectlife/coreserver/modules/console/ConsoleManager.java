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
import org.apache.sshd.server.command.ScpCommandFactory;
import org.apache.sshd.server.keyprovider.SimpleGeneratorHostKeyProvider;
import org.apache.sshd.server.session.ServerSession;
import org.apache.sshd.server.shell.ProcessShellFactory;

// internal
import com.connectlife.coreserver.Consts;
import com.connectlife.coreserver.Consts.ModuleUID;
import com.connectlife.coreserver.modules.Module;
import com.connectlife.coreserver.modules.datamanager.Config;
import com.connectlife.coreserver.modules.datamanager.DataManager;
import com.connectlife.coreserver.tools.errormanagement.StdOutErrLog;

/**
 * 
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
	 * @return
	 * @see com.connectlife.coreserver.modules.Module#init()
	 */
	public boolean init() {
		
		boolean ret_val = false;
		
		m_logger.info("Initialization in progress ...");
		
		Config tcpip_port 	= DataManager.getConfig("CONSOLE", "TCPIP_PORT");
		
		m_sshd = SshServer.setUpDefaultServer();
		m_sshd.setPort(tcpip_port.getIntegerValue());
		m_sshd.setKeyPairProvider(new SimpleGeneratorHostKeyProvider("hostkey.ser"));
		
		
		// TODO : implement shutdown/exit/log show/module show/module init/module uninit/module reload/env show/env save/env load
		
		m_sshd.setShellFactory(new ProcessShellFactory(new String[] { "/bin/sh", "-i", "-l" }));
		//m_sshd.setCommandFactory(new ScpCommandFactory());
		
		List<NamedFactory<UserAuth>> userAuthFactories = new ArrayList<NamedFactory<UserAuth>>();
		userAuthFactories.add(new UserAuthPassword.Factory());
		m_sshd.setUserAuthFactories(userAuthFactories);

		m_sshd.setPasswordAuthenticator(new PasswordAuthenticator() {
		    public boolean authenticate(String username, String password, ServerSession session) {
		        return "admin".equals(username) && "1234".equals(password);
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
		// TODO Auto-generated method stub

	}

	/**
	 * @return
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
