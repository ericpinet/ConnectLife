/**
 *  Device.java
 *  simulator
 *
 *  Created by ericpinet on 2016-01-05.
 *  Copyright (c) 2016 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.connectlife.simulator.device;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceInfo;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.AbstractHandler;

import com.clapi.data.Accessory;
import com.clapi.data.Service;
import com.clapi.simulator.device.ServiceDefinition;
import com.google.gson.Gson;

/**
 * Base device for the simulation of accessory.
 * 
 * @author ericpinet
 * <br> 2016-01-05
 */
public class Device extends Accessory {
	
	/**
	 * Init logger instance for this class
	 */
	private static Logger m_logger = LogManager.getLogger(Device.class);

	/**
	 * Device http handler for json services.
	 */
	private HttpHandler m_handler;
	
	/**
	 * Http server of services.
	 */
	private Server m_server;
	
	/**
	 * The JmDNS service creation for Bonjour protocol.
	 */
	private JmDNS m_jmdns;
	
	/**
	 * The service information for the Bonjour protocol.
	 */
	private ServiceInfo m_serviceinfo;
	

	/**
	 * Default constructor for Device
	 * 
	 * @param uid
	 * @param label
	 * @param manufacturer
	 * @param model
	 * @param serialnumber
	 * @param services
	 * @param imageurl
	 * @param type
	 */
	public Device(String uid, String label, String manufacturer, String model, String serialnumber,
			List<Service> services, String imageurl, AccessoryType type) {
		super(uid, label, manufacturer, model, serialnumber, services, imageurl, type, AccessoryProtocolType.JSON_SIMULATION); 
	}
	
	/**
	 * Start services of this device.
	 * @return
	 */
	public boolean startServices(){
		boolean ret_val = false;
		
		m_server = new Server(0);
		m_handler = new HttpHandler(this);
		m_server.setHandler(m_handler);

        Thread thread = new Thread(new Runnable()
        {
        	public void run()
        	{
        		// this will be run in a separate thread
        		try {
        			
        			// Start the handle for http request 
        			m_server.start();
        			
        			m_logger.info("Service started : "+getLabel());
        			
        			// Update the connection information (At this point the tcp/port is reserved)
        			m_handler.updateConnectionInformaiton();
        			
        			// Registrer the service for the Bonjour protocol discovery
        			m_jmdns = JmDNS.create();
        			m_serviceinfo = ServiceInfo.create(	"_http._tcp.local.", 
        												getLabel(), 
        												m_handler.getPort(), 
        												getLabel()+" "+getSerialnumber()+" "+getManufacturer());
        			m_jmdns.registerService(m_serviceinfo);
        			
        			m_logger.info("Service register : _http._tcp.local.:"+m_handler.getPort() + " "+getLabel()+" "+getManufacturer());
        			
        			// Ready to receive client request
        			m_server.join();
        			
        		} catch (Exception e) {
        			// TODO Auto-generated catch block
        			m_logger.error(e.getMessage());
        			e.printStackTrace();
        		}
        	}
        });

        // start the thread
        thread.start();
		
		
		return ret_val;
	}

	/**
	 * @return the m_server
	 */
	public Server getServer() {
		return m_server;
	}
	
	/**
	 * Return a accessory object representing this device.
	 * @return
	 */
	public Accessory getAccessory(){
		return new Accessory(getUid(), getLabel(), getManufacturer(), getModel(), getSerialnumber(), getServices(), getImageurl(), getType(), getProtocoltype());
	}
}

/**
 * Http handler for a device.
 * 
 * 
 * @author ericpinet
 * <br> 2016-01-05
 */
class HttpHandler extends AbstractHandler {
	
	/**
	 * Device parent of this http handle.
	 */
	private Device m_device;
	
	/**
	 * Ip address of the device.
	 */
	private String m_ip_address;
	
	/**
	 * Hostname of the device.
	 */
	private String m_hostname;
	
	/**
	 * Listen port of the device.
	 */
	private int m_listen_port;
	
	/**
	 * Default constructor for the http handler.
	 * @param _device
	 */
	public HttpHandler( Device _device ){
		m_device = _device;	
	}
	
	/**
	 * Update the connection information.
	 */
	public void updateConnectionInformaiton(){
		
		InetAddress addr;
		try {
			addr = InetAddress.getLocalHost();
			
			// Get IP Address
			m_ip_address = addr.getHostAddress();
			
			// Get hostname
			m_hostname = addr.getHostName();
			
			// listen port
			m_listen_port = ((ServerConnector)m_device.getServer().getConnectors()[0]).getLocalPort();
	        
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return the m_ip_address
	 */
	public String getIpAddress() {
		return m_ip_address;
	}

	/**
	 * @param m_ip_address the m_ip_address to set
	 */
	public void setIpAddress(String _ip_address) {
		this.m_ip_address = _ip_address;
	}

	/**
	 * @return the m_hostname
	 */
	public String getHostname() {
		return m_hostname;
	}

	/**
	 * @param m_hostname the m_hostname to set
	 */
	public void setHostname(String _hostname) {
		this.m_hostname = _hostname;
	}

	/**
	 * @return the m_port
	 */
	public int getPort() {
		return m_listen_port;
	}

	/**
	 * @param m_port the m_port to set
	 */
	public void setPort(int _port) {
		this.m_listen_port = _port;
	}

	/**
	 * @param target
	 * @param baserequest
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 * @see org.eclipse.jetty.server.Handler#handle(java.lang.String, org.eclipse.jetty.server.Request, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public void handle(String target, Request baserequest, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		response.setContentType("text/json; charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        
        Gson gson = new Gson();
        
		String jsonresponse = gson.toJson(new ServiceDefinition(m_ip_address, m_hostname, m_listen_port, m_device.getAccessory()));
        
        response.getWriter().println(jsonresponse);
        baserequest.setHandled(true);
	}
	
}
