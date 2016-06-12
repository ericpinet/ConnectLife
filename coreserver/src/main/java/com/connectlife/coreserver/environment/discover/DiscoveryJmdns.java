/**
 *  DiscoveryJmdns.java
 *  coreserver
 *
 *  Created by ericpinet on 2015-09-17.
 *  Copyright (c) 2015 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.connectlife.coreserver.environment.discover;


// external
import javax.jmdns.JmDNS;
import javax.jmdns.ServiceEvent;
import javax.jmdns.ServiceListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.IOException;
import java.util.Vector;

// internal
import com.connectlife.coreserver.tools.errormanagement.StdOutErrLog;


/**
 * Discovery JMDNS services in the local network.
 * 
 * @author ericpinet
 * <br> 2015-09-17
 */
public class DiscoveryJmdns implements DiscoveryService {
	
	/**
	 * Logger use for this class.
	 */
	private static Logger m_logger = LogManager.getLogger(DiscoveryJmdns.class);
	
	/**
	 * Discover service manager.
	 */
	private JmDNS m_discover;
	
	/**
	 * List of service that we trying to find.
	 */
	private static final String[] m_service_list = {  "_http._tcp.local." 		// HTTP
													//, "_ssh._tcp.local."		// SSH
													//, "_scanner._tcp.local."	// Scanner
													//, "_webdave._tcp.local."	// WebDave
													//, "_tftp._udp.local."		// TFTP
													, "_airplay._tcp.local."	// AirPlay
													, "_ipp._tcp.local."		// AirPrint
													//, "_afpovertcp._tcp.local."	// Apple file sharing
													//, "_ichat._tcp.local."		// iChat 1.0
													//, "_presence._tcp.local."	// iChat AV
													//, "_dacp._tcp.local."		// iTunes Digital Audio Control Protocol
													//, "_daap._tcp.local."		// iTunes Music Sharing
													
													
	};
	
	/**
	 * List of registered listner on the discovery manager.
	 */
	private Vector<DiscoveryListner> m_listners;
	
	/**
	 * ServiceListener to receive the service update status.
	 */
	private ServiceListener m_mdnsServiceListener = new ServiceListener() {
		  
		/**
		 * Callback when new service is added.
		 * 
		 * @param _service Service information.
		 * @see javax.jmdns.ServiceListener#serviceAdded(javax.jmdns.ServiceEvent)
		 */
		public void serviceAdded(ServiceEvent _service) {
			  
			m_logger.debug("Service discovered: "+_service.getName() + " - " + _service.getType());
			m_discover.requestServiceInfo(_service.getType(), _service.getName());
		}
		
		/**
		 * Callback called when a service is removed.
		 * 
		 * @param _service Service information.
		 * @see javax.jmdns.ServiceListener#serviceRemoved(javax.jmdns.ServiceEvent)
		 */
		public void serviceRemoved(ServiceEvent _service) {

			m_logger.debug("Service removed: "+_service.getName());
			informListerServiceRemove(_service);
		}
		
		/**
		 * Callback called when service informations was resolved.
		 * 
		 * @param _service
		 * @see javax.jmdns.ServiceListener#serviceResolved(javax.jmdns.ServiceEvent)
		 */
		public void serviceResolved(ServiceEvent _service) {
			
			m_logger.debug("Service resolved: "+_service.getName() + " - " + _service.getType());
			informListerServiceDiscover(_service);
		}
	};
	
	/**
	 * Default constructor
	 */
	public DiscoveryJmdns(){
		m_discover = null;
		m_listners = new Vector<DiscoveryListner>();
	}
	

	/**
	 * Start the discovery manager.
	 */
	public void start(){
		
		m_logger.info("Starting...");
		
		// start discovering
		if(null == m_discover){
			try {
				m_discover = JmDNS.create();
				
				for(int i=0 ; i<m_service_list.length ; i++){
					m_logger.info("Add service listner: "+m_service_list[i]);
					m_discover.addServiceListener(m_service_list[i], this.m_mdnsServiceListener);
				}
				
			} catch (IOException e) {
				m_logger.error("Unable to create JmDNS service :"+e.getMessage());
				StdOutErrLog.tieSystemOutAndErrToLog();
				e.printStackTrace();
			}
		}
		else{
			m_logger.warn("Discovering is already started.");
		}
		
		m_logger.info("Start completed.");
	}
	
	/**
	 * Stop the discovering process.
	 */
	public void stop(){
		
		if(null != m_discover){
			try {
				
				for(int i=0 ; i<m_service_list.length ; i++){
					m_logger.info("Remove service listner: "+m_service_list[i]);
					m_discover.removeServiceListener(m_service_list[i], this.m_mdnsServiceListener);
				}
				
				m_discover.close();
				
			} catch (IOException e) {
				m_logger.error("Unable to close correctly the discovering service: "+e.getMessage());
				StdOutErrLog.tieSystemOutAndErrToLog();
				e.printStackTrace();
			}
		}
		else{
			m_logger.warn("Discovering is already stoped.");
		}
	}
	
	/**
	 * Add a new listner on the discovery manager.
	 * 
	 * @param _listner Listener that will be receive the notification.
	 */
	public void addListner(DiscoveryListner _listner){
		if (null != _listner) {
			m_listners.add(_listner);
		}
		else{
			m_logger.warn("Can not add a null listner.");
		}
	}
	
	/**
	 * Inform all listners of new ServiceDiscover
	 * 
	 * @param _service ServiceEvent described the service discovered.
	 */
	private void informListerServiceDiscover(ServiceEvent _service){
		for(int i=0 ; i<m_listners.size() ; i++){
			m_listners.elementAt(i).serviceDiscover(_service);
		}
	}
	
	/**
	 * Inform all listners of new ServiceRemove
	 * @param _service ServiceEvent described the service removed.
	 */
	private void informListerServiceRemove(ServiceEvent _service){
		for(int i=0 ; i<m_listners.size() ; i++){
			m_listners.elementAt(i).serviceRemove(_service);
		}
	}
	
}
