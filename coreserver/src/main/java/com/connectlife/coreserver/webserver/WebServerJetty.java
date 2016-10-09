/**
 *  WebServerJetty.java
 *  coreserver
 *
 *  Created by ericpinet on 2016-11-09.
 *  Copyright (c) 2015-2016 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.connectlife.coreserver.webserver;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tomcat.InstanceManager;
import org.apache.tomcat.SimpleInstanceManager;
import org.xnap.commons.i18n.I18n;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.webapp.WebAppContext;
import org.eclipse.jetty.plus.annotation.ContainerInitializer;
import org.eclipse.jetty.annotations.ServletContainerInitializersStarter;
import org.eclipse.jetty.apache.jsp.JettyJasperInitializer;
import org.eclipse.jetty.jsp.JettyJspServlet;
import org.eclipse.jetty.server.ConnectionFactory;

import com.connectlife.coreserver.Application;
import com.connectlife.coreserver.tools.errormanagement.StdOutErrLog;

/**
 * This is the main application web server for the application.
 * 
 * @author ericpinet
 * <br> 2016-11-09
 */
public class WebServerJetty implements WebServer {
	
	/**
	 * Init logger instance for this class
	 */
	private Logger m_logger = LogManager.getLogger(getClass().getName());
	
	/**
	 * Initialization of translation system.
	 */
	private static I18n i18n = Application.i18n;
	
	/**
	 * Http server.
	 */
	private Server m_server;
	
	/**
	 * Flag to indicate if the HTTP server was initialized.
	 */
	private boolean m_is_init;
	
	/**
	 * Flag to indicate if the initialization is in progress.
	 */
	private boolean m_init_inprogress;
	
	/**
	 * WebRoot resource directory
	 */
	private static String m_webroot = "/webroot/";
	
	/**
	 * Web app context
	 */
	private WebAppContext m_webapp_context;
	
	/**
	 * Server URI
	 */
	private URI m_server_uri;
	
	/**
	 * Default constructor.
	 */
	public WebServerJetty () {
		m_is_init = false;
		m_init_inprogress = false;
	}

	/**
	 * Initialization of the web server. 
	 * 
	 * @return True if the web server is correctly loaded.
	 */
	@Override
	public boolean init() {
		boolean ret_val = false;
		
		m_logger.info(i18n.tr("Initialization starting ..."));
		
		if (null == m_server && false == m_is_init) {
			
			// TODO: Load configuration to set IP Port.
			
			m_server = new Server();
	
	        Thread thread = new Thread(new Runnable()
	        {
	        	public void run()
	        	{
	        		// this will be run in a separate thread
	        		try {
	        			
	        			// build connector
	        			ServerConnector connector = connector();
	        	        m_server.addConnector(connector);
	        			
	        	        // Get base URI
	        			URI baseUri = getWebRootResourceUri();

	        	        // Set JSP to use Standard JavaC always
	        	        System.setProperty("org.apache.jasper.compiler.disablejsr199", "false");

	        	        m_webapp_context = getWebAppContext(baseUri, getScratchDir());
	        	        m_server.setHandler(m_webapp_context);
	        			
	        			// Start the handle for http request 
	        			m_server.start();
	        			
	        			// restore flag 
	        			m_is_init = true;
	        			m_init_inprogress = false;
	        			
	        			m_server_uri = getServerUri(connector);
	        			
	        			// Ready to receive client request
	        			m_server.join();
	        			
	        		} catch (Exception e) {
	        			m_logger.error(i18n.tr("Unable to start the http server."));
	        			m_logger.error(e.getMessage());
	        			StdOutErrLog.tieSystemOutAndErrToLog();
	        			e.printStackTrace();
	        			
	        			// restore flag 
	        			m_init_inprogress = false;
	        		}
	        	}
	        });
	
	        // set the in progress flag
	        m_init_inprogress = true;
	        
	        // start the thread
	        thread.start();
	        
	        // wait flag return
	        while (true == m_init_inprogress) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					m_logger.error(e.getMessage());
        			StdOutErrLog.tieSystemOutAndErrToLog();
        			e.printStackTrace();
				}
	        }
	        
	        ret_val = m_is_init;
	        
	        m_logger.info(i18n.tr("Initialization completed."));
	        
		}
		else {
			m_logger.warn(i18n.tr("Already initialized."));
		}
		
		return ret_val;
	}

	/**
	 * Return true if the web server was started.
	 * 
	 * @return True if the web server was started.
	 */
	@Override
	public boolean isInit() {
		return m_is_init;
	}

	/**
	 * UnInit the web server. Ready to init again.
	 * 
	 */
	@Override
	public void unInit() {
		
		m_logger.info(i18n.tr("UnInit ..."));
		
		try {
			
			if (null != m_server)
				m_server.stop();
			
		} catch (Exception e) {
			m_logger.error(i18n.tr("Unable to stop the asset http server."));
			m_logger.error(e.getMessage());
			StdOutErrLog.tieSystemOutAndErrToLog();
			e.printStackTrace();
		}
		finally {
			m_server = null;
			m_is_init = false;
		}
		
		m_logger.info(i18n.tr("UnInit completed."));
	}
	
	/**
	 * Return the server connector (Port)
	 * @return
	 */
	private ServerConnector connector()
    {
        ServerConnector connector = new ServerConnector(m_server);
        connector.setPort(8080);
        return connector;
    }
	
	/**
	 * Return the web root resource directory.
	 * 
	 * @return URI of the web root resource directory.
	 * @throws FileNotFoundException
	 * @throws URISyntaxException
	 */
	private URI getWebRootResourceUri() throws FileNotFoundException, URISyntaxException
    {
        URL indexUri = this.getClass().getResource(m_webroot);
        if (indexUri == null) {
            throw new FileNotFoundException("Unable to find resource " + m_webroot);
        }
        // Points to wherever /webroot/ (the resource) is
        return indexUri.toURI();
    }
	
	/**
	 * Establish Scratch directory for the servlet context (used by JSP compilation)
	 * 
	 * @return
	 * @throws IOException
	 */
    private File getScratchDir() throws IOException
    {
        File tempDir = new File(System.getProperty("java.io.tmpdir"));
        File scratchDir = new File(tempDir.toString(), "connectlife-embedded-jetty-jsp");

        if (!scratchDir.exists()) {
            if (!scratchDir.mkdirs()) {
                throw new IOException(i18n.tr("Unable to create scratch directory: ") + scratchDir);
            }
        }
        return scratchDir;
    }
    
    /**
     * Setup the basic application "context" for this application at "/"
     * This is also known as the handler tree (in jetty speak)
     */
    private WebAppContext getWebAppContext(URI baseUri, File scratchDir)
    {
        WebAppContext context = new WebAppContext();
        context.setContextPath("/");
        context.setAttribute("javax.servlet.context.tempdir", scratchDir);
        context.setAttribute("org.eclipse.jetty.server.webapp.ContainerIncludeJarPattern",
          ".*/[^/]*servlet-api-[^/]*\\.jar$|.*/javax.servlet.jsp.jstl-.*\\.jar$|.*/.*taglibs.*\\.jar$");
        context.setResourceBase(baseUri.toASCIIString());
        context.setAttribute("org.eclipse.jetty.containerInitializers", jspInitializers());
        context.setAttribute(InstanceManager.class.getName(), new SimpleInstanceManager());
        context.addBean(new ServletContainerInitializersStarter(context), true);
        context.setClassLoader(getUrlClassLoader());

        context.addServlet(jspServletHolder(), "*.jsp");
        
        // Add Application Servlets
        context.addServlet(ConnectLifeServlet.class, "/connectlife/");

        context.addServlet(exampleJspFileMappedServletHolder(), "/test/foo/");
        
        context.addServlet(defaultServletHolder(baseUri), "/");
        return context;
    }
    
    /**
     * Ensure the jsp engine is initialized correctly
     * 
     * @return
     */
    private List<ContainerInitializer> jspInitializers()
    {
        JettyJasperInitializer sci = new JettyJasperInitializer();
        ContainerInitializer initializer = new ContainerInitializer(sci, null);
        List<ContainerInitializer> initializers = new ArrayList<ContainerInitializer>();
        initializers.add(initializer);
        return initializers;
    }
    
    /**
     * Set Classloader of Context to be sane (needed for JSTL)
     * JSP requires a non-System classloader, this simply wraps the
     * embedded System classloader in a way that makes it suitable
     * for JSP to use
     * 
     * @return
     */
    private ClassLoader getUrlClassLoader()
    {
        ClassLoader jspClassLoader = new URLClassLoader(new URL[0], this.getClass().getClassLoader());
        return jspClassLoader;
    }
    
    /**
     * Create JSP Servlet (must be named "jsp")
     */
    private ServletHolder jspServletHolder()
    {
        ServletHolder holderJsp = new ServletHolder("jsp", JettyJspServlet.class);
        holderJsp.setInitOrder(0);
        holderJsp.setInitParameter("logVerbosityLevel", "DEBUG");
        holderJsp.setInitParameter("fork", "false");
        holderJsp.setInitParameter("xpoweredBy", "false");
        holderJsp.setInitParameter("compilerTargetVM", "1.7");
        holderJsp.setInitParameter("compilerSourceVM", "1.7");
        holderJsp.setInitParameter("keepgenerated", "true");
        return holderJsp;
    }

    /**
     * Create Example of mapping jsp to path spec
     */
    private ServletHolder exampleJspFileMappedServletHolder()
    {
        ServletHolder holderAltMapping = new ServletHolder();
        holderAltMapping.setName("foo.jsp");
        holderAltMapping.setForcedPath("/test/foo/foo.jsp");
        return holderAltMapping;
    }

    /**
     * Create Default Servlet (must be named "default")
     */
    private ServletHolder defaultServletHolder(URI baseUri)
    {
        ServletHolder holderDefault = new ServletHolder("default", DefaultServlet.class);
        m_logger.info(i18n.tr("Base URI: ") + baseUri);
        holderDefault.setInitParameter("resourceBase", baseUri.toASCIIString());
        holderDefault.setInitParameter("dirAllowed", "true");
        return holderDefault;
    }

    /**
     * Establish the Server URI
     */
    private URI getServerUri(ServerConnector connector) throws URISyntaxException 
    {
        String scheme = "http";
        for (ConnectionFactory connectFactory : connector.getConnectionFactories())
        {
            if (connectFactory.getProtocol().equals("SSL-http"))
            {
                scheme = "https";
            }
        }
        String host = connector.getHost();
        if (host == null)
        {
            host = "localhost";
        }
        int port = connector.getLocalPort();
        m_server_uri = new URI(String.format("%s://%s:%d/", scheme, host, port));
        m_logger.info(i18n.tr("Server URI: ") + m_server_uri);
        return m_server_uri;
    }

}
