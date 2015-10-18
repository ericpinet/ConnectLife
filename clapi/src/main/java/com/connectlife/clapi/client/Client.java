/**
 *  Client.java
 *  clapi
 *
 *  Created by ericpinet on 2015-10-09.
 *  Copyright (c) 2015 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.connectlife.clapi.client;


// external
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// internal
import com.connectlife.clapi.*;
import com.connectlife.clapi.CLApiPush.Processor;

/**
 * Client class for the clapi protocol. Use this class to embedded all clapi protocol support. 
 * 
 * <pre>
 * public class Sample implements NotificationListner {
 * 
 *     public void ConnectClient() {
 * 	       try {
 *	           Client client = new Client("127.0.0.1", 9008, this);
 *	           String responseBody = client.getEnvironmentDataJson();
 * 	       }
 *         catch( Exception e){
 *             System.out.println(e.getMessage());
 *         }
 *     }
 * 
 *     public void notificationReceive(Notification _notification) {
 *         System.out.println(_notification.getData());	
 *     }
 * }
 * </pre>
 * 
 * @author ericpinet
 * <br> 2015-10-09
 */
public class Client implements CLApi.Iface, Runnable {
	
	/**
	 * Init logger instance for this class
	 */
	private static Logger m_logger = LogManager.getLogger(Client.class);
	
	/**
	 * Transport system for the client.
	 */
	private final TTransport m_transport;
	
	/**
	 * Protocol system for the client.
	 */
	private final TProtocol m_protocol;
	
	/**
	 * Listner to notify when new notification receive.
	 */
	private final NotificationListener m_listner;
	
	/**
	 * Notification processor.
	 */
	private final Processor<CLApiPush.Iface> m_processor;
	
	/**
	 * Thrift Client
	 */
	private final CLApi.Client m_client;
	
	/**
	 * Time in milisecond to wait before start notification listener.
	 */
	private final int SLEEP_BEFORE_START_NOTIFICATION = 1000;
	
	/**
	 * Indicate if the listener of notification must be run.
	 */
	private boolean m_is_running;
	
	/**
	 * Default constructor.
	 * 
	 * @param _host	Hostname of the server.
	 * @param _port Port Tcp/Ip of the server.
	 * @param _listener	Object listener for the notification. Can be null.
	 * @throws TTransportException	Exception when something goes wrong.
	 */
	public Client(String _host, int _port, NotificationListener _listener) throws TTransportException{
		
		m_logger.info("Initialization ...");
		
		m_transport = new TSocket(_host, _port);
        m_transport.open();
        
        m_protocol = new  TBinaryProtocol(m_transport);
        m_client = new CLApi.Client(m_protocol);
        m_listner = _listener;
        
        CLApiPush.Iface handler = new CLApiPush.Iface() {
			
			public void push(Notification _notification) throws TException {
				if(m_listner != null)
					m_listner.notificationReceive(_notification);
			}
		};
        
		m_processor = new CLApiPush.Processor<CLApiPush.Iface>(handler);
		m_is_running = true;
		
		if(m_listner != null)
			new Thread(this).start();
		
		m_logger.info("Initialization completed.");
	}
	
	/**
	 * Close connection whit the server.
	 */
	public void close(){
		
		m_logger.info("Closing ...");
		
		m_is_running = false;
		
		if(m_transport != null)
			m_transport.close();
		
		m_logger.info("Close completed.");
	}

	/**
	 * @return
	 * @throws TException
	 * @see com.connectlife.clapi.CLApi.Iface#getVersion()
	 */
	@Override
	public String getVersion() throws TException {
		return m_client.getVersion();
	}

	/**
	 * @param _version
	 * @return
	 * @throws TException
	 * @see com.connectlife.clapi.CLApi.Iface#checkCompatibility(java.lang.String)
	 */
	@Override
	public boolean checkCompatibility(String _version) throws TException {
		return m_client.checkCompatibility(_version);
	}

	/**
	 * @return
	 * @throws TException
	 * @see com.connectlife.clapi.CLApi.Iface#getEnvironmentDataJson()
	 */
	@Override
	public String getEnvironmentDataJson() throws TException {
		return m_client.getEnvironmentDataJson();
	}

	
	
	/**
	 * Run the notification listner.
	 * 
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		
		try {
			Thread.sleep(SLEEP_BEFORE_START_NOTIFICATION);
		} catch (InterruptedException e1) {
		}
		
		m_logger.info("Notification listner started.");
		
		while(m_is_running)
        {
	        try {
				while (m_processor.process(m_protocol, m_protocol) == true) {  }
			} catch (TException e) {
				m_is_running = false;
			}
        }
		
		m_logger.info("Notification listner stopted.");
	}

	
	

}
