/**
 *  PushDistributor.java
 *  coreserver
 *
 *  Created by ericpinet on 2015-10-04.
 *  Copyright (c) 2015 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.connectlife.coreserver.modules.apiserver;

// external
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import org.apache.thrift.TException;
import java.lang.Runnable;

// internal
import com.connectlife.clapi.CLApiPush.Iface;
import com.connectlife.clapi.Notification;



/**
 * PushDistributor manage push notification to clients.
 * 
 * @author ericpinet
 * <br> 2015-10-04
 */
public class PushDistributor implements Iface, Runnable {

	/**
	 * Logger use for this class.
	 */
	private static Logger m_logger = LogManager.getLogger(PushDistributor.class);
	
	/**
	 * Blocking queue for notification to send.
	 */
	private final BlockingQueue<Notification> m_message_queue;
	
	/**
	 * List of client to notify.
	 */
	private final List<NotificationServiceClient> m_clients;
	  
	/**
	 * Default constructor of PushDistributor.
	 */
	public PushDistributor() {
		m_message_queue = new LinkedBlockingQueue<Notification>();
		m_clients = new ArrayList<NotificationServiceClient>();
	}
	  
	/**
	 * Add client to the server for notification.
	 * @param _client Client connected at this server.
	 */
	public void addClient(NotificationServiceClient _client) {
		// There should be some synchronization around this list
		m_clients.add(_client);
		m_logger.info(String.format("Added client at %s", _client.getAddress()));
	}
	
	/**
	 * Run the notification push thread.
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		
		// Start thread of notification push
		while (true) {
			try {
				Notification notification = m_message_queue.take();
				
				Iterator<NotificationServiceClient> clientItr = m_clients.iterator();
				while (clientItr.hasNext()) {
					NotificationServiceClient client = clientItr.next();
					try {
						m_logger.debug(String.format("Send notification to %s started ...", client.getAddress()));
						client.pushNotification(notification);
						m_logger.debug(String.format("Send notification to %s completed.", client.getAddress()));
					} catch (TException te) {
						// Most likely client disconnected, should remove it from the list
						clientItr.remove();
						m_logger.info(String.format("Removing %s from client list.", client.getAddress()));
						m_logger.debug(te);
					}
				}
				
			} catch (InterruptedException ie) {
				m_logger.debug(ie);
			}
		}
	}

	/**
	 * Push new notification at all client. Adding message in queue and it's will be send when
	 * notification thread was ready.
	 * 
	 * @param _notification 	Notification to send at all client.
	 * @throws TException	Exception generated if a problem occur.
	 */
	@Override
	public void push(Notification _notification) throws TException {
		m_message_queue.add(_notification);
	}
}
