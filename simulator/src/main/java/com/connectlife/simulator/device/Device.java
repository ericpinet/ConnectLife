/**
 *  Device.java
 *  simulator
 *
 *  Created by ericpinet on 2016-01-05.
 *  Copyright (c) 2016 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.connectlife.simulator.device;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceInfo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.jetty.server.Server;

import com.clapi.data.Accessory;
import com.clapi.data.Characteristic;
import com.clapi.data.Characteristic.CharacteristicAccessMode;
import com.clapi.data.Characteristic.CharacteristicType;
import com.clapi.data.Service;

/**
 * Base device for the simulation of result.
 * Call the url of the device like this to change value of characteristic :
 * 
 * http://[hostname]:[port]/[service_name]/[characteristic_label]?value=[value]
 * 
 * Exemple with dimmable light : 
 * http://127.0.0.1:51709/light/dimmable?value=0.6
 * 
 * @author ericpinet
 * <br> 2016-01-05
 */
public abstract class Device extends Accessory {
	
	/**
	 * Init logger instance for this class
	 */
	private static Logger m_logger = LogManager.getLogger(Device.class);

	/**
	 * Device http handler for json services.
	 */
	private DeviceHttpHandler m_handler;
	
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
	protected Device(String uid, String label, String manufacturer, String model, String serialnumber,
			List<Service> services, String imageurl, AccessoryType type) {
		super(uid, label, manufacturer, model, serialnumber, services, imageurl, type, AccessoryProtocolType.JSON_SIMULATION); 
	}
	
	/**
	 * Constructor for Device without services.
	 * 
	 * @param label
	 * @param manufacturer
	 * @param model
	 * @param serialnumber
	 * @param imageurl
	 * @param type
	 */
	public Device(String label, String manufacturer, String model, String serialnumber,
			String imageurl, AccessoryType type) {
		super("", label, manufacturer, model, serialnumber, new ArrayList<Service>(), imageurl, type, AccessoryProtocolType.JSON_SIMULATION); 
	}
	
	/**
	 * Start services of this device.
	 * 
	 * @return
	 */
	public boolean startServices(){
		boolean ret_val = false;
		
		if (null == m_server) {
		
			m_server = new Server(0);
			m_handler = new DeviceHttpHandler(this);
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
	        			m_logger.error(e.getMessage());
	        			e.printStackTrace();
	        		}
	        	}
	        });
	
	        // start the thread
	        thread.start();
		}
		else {
			m_logger.warn("Service already started.");
		}
		
		return ret_val;
	}
	
	/**
	 * Stop services.
	 */
	public void stopServices(){
		try {
			m_server.stop();
			m_handler.stop();
			m_jmdns.unregisterAllServices();
			m_jmdns.close();
		} catch (Exception e) {
			m_logger.error(e.getMessage());
			e.printStackTrace();
		}
		finally {
			m_server = null;
			m_handler = null;
			m_jmdns = null;
		}
	}

	/**
	 * @return the m_server
	 */
	public Server getServer() {
		return m_server;
	}
	
	/**
	 * Return a result object representing this device.
	 * @return
	 */
	public Accessory getAccessory(){
		
		return new Accessory(	getUid(), 
								getLabel(), 
								getManufacturer(), 
								getModel(), 
								getSerialnumber(), 
								getServices(), 
								getImageuid(), 
								getType(), 
								getProtocoltype());
	}
	
	/**
	 * Return the URL of the service.
	 * 
	 * @return Url of the service.
	 */
	public String getUrl() {
		String ret_val = "";
		if (null != m_handler) {
			ret_val = "http://" + m_handler.getHostname() + ":" + String.valueOf(m_handler.getPort());
		}
		return ret_val;
	}
	
	/**
	 * Set new characteristic value.
	 * 
	 * @param _service_name
	 * @param _characteristic_label
	 * @param _data
	 * @return
	 */
	public boolean setNewCharacteristicValue(String _service_name, String _characteristic_label, String _data){
		boolean ret_val = false;
		
		// find the right services
		Iterator<Service> it = getServices().iterator();
		while(it.hasNext()){
			Service service = it.next();
			if(service.getName().equalsIgnoreCase(_service_name)){
				
				// find the right characteristic
				Iterator<Characteristic> it2 = service.getCharacteristics().iterator();
				while(it2.hasNext()){
					Characteristic charac = it2.next();
					if(charac.getLabel().equalsIgnoreCase(_characteristic_label)){
						
						// check if is write characteristics
						if (charac.getMode() == CharacteristicAccessMode.READ_WRITE ||
									charac.getMode() == CharacteristicAccessMode.WRITE_ONLY) {
							
							// update characteristic value
							try {
								if(charac.getType() == CharacteristicType.STATIC_STRING){
									charac.setData(_data);
									m_logger.info("Set new value for " + _service_name + "." + _characteristic_label + " = " +_data);
								}
								else if (charac.getType() == CharacteristicType.BOOLEAN ||
										charac.getType() == CharacteristicType.WRITE_ONLY_BOOLEAN){
									boolean data = Boolean.valueOf(_data);
									charac.setBooleanData(data);
									m_logger.info("Set new value for " + _service_name + "." + _characteristic_label + " = " +_data);
								}
								else if (charac.getType() == CharacteristicType.FLOAT){
									float data = Float.valueOf(_data);
									charac.setFloatData(data);
									m_logger.info("Set new value for " + _service_name + "." + _characteristic_label + " = " +_data);
								}
								else if (charac.getType() == CharacteristicType.INTEGER){
									int data = Integer.valueOf(_data);
									charac.setIntegerData(data);
									m_logger.info("Set new value for " + _service_name + "." + _characteristic_label + " = " +_data);
								}
								else if (charac.getType() == CharacteristicType.ENUM){
									charac.setDataEnum(_data);
									m_logger.info("Set new value for " + _service_name + "." + _characteristic_label + " = " +_data);
								}
								
								ret_val = true;

							} catch (Exception e) {
								m_logger.error(e.getMessage());
								e.printStackTrace();
							}
						
						}// ELSE: characteristic is read only. Do noting.
						
					}// ELSE: didn't find the right characteristic. Do noting. 
				}
			}// ELSE: didn't find the right service. Do noting.
		}
		
		return ret_val;
	}
}
