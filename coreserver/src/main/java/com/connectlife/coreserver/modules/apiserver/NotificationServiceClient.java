/**
 *  NotificationServiceClient.java
 *  coreserver
 *
 *  Created by ericpinet on 2015-10-04.
 *  Copyright (c) 2015 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.connectlife.coreserver.modules.apiserver;

// external
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

// internal
import com.connectlife.clapi.CLApiPush;
import com.connectlife.clapi.Notification;


/**
 * This class is a stub that the server can use to send messages back
 * to the client.
 * 
 * @author ericpinet
 * <br> 2015-10-04
 */
public class NotificationServiceClient {
	
	/**
	 * Transport protocol of the client connection.
	 */
	protected final TTransport m_transport;
	
	/**
	 * Network address of the client.
	 */
	protected final String m_address;
	
	/**
	 * Port TCP/IP of the client.
	 */
	protected final int m_port;
	
	/**
	 * Client object
	 */
	protected final CLApiPush.Client m_client;
  
	/**
	 * Default construction of NotificationServiceClient
	 * 
	 * @param _transport Transport protocol of the client.
	 */
	public NotificationServiceClient(TTransport _transport) {
		TSocket tsocket = (TSocket)_transport;
		this.m_transport = _transport;
    
		this.m_client = new CLApiPush.Client(new TBinaryProtocol(_transport));
		this.m_address = tsocket.getSocket().getInetAddress().getHostAddress();
		this.m_port = tsocket.getSocket().getPort();
	}
  
	/**
	 * Return the address of the client.
	 * @return The address of the client.
	 */
	public String getAddress() {
		return m_address;
	}
  
	/**
	 * Send notification to client.
	 * @param _notification Notification to send at the client.
	 * @throws TException Exception throw if an error occur.
	 */
	public void sendNotification(Notification _notification) throws TException {
		this.m_client.Push(_notification);
	}
}
