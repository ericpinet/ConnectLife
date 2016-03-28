/**
 *  CLApiClient.java
 *  clapi
 *
 *  Created by ericpinet on 2015-11-05.
 *  Copyright (c) 2015 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.clapi.client;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.clapi.protocol.*;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.MoreExecutors;

/**
 * Client to connect with connectlife server. 
 * 
 * @author ericpinet
 * <br> 2015-11-05
 */
public class CLApiClient {
	
	/**
	 * Init logger instance for this class
	 */
	private static Logger m_logger = LogManager.getLogger(CLApiClient.class);
	
	/**
	 * Channel manager.
	 */
	private final ManagedChannel m_channel;
	
	/**
	 * Blocking request (Synchronize)
	 */
	private final CLApiGrpc.CLApiBlockingStub m_blockingStub;
	
	/**
	 * Not blocking request. (Asynchronize)
	 */
	private final CLApiGrpc.CLApiFutureStub m_futureStub;
	
	/**
	 * Listner for the notification reception.
	 */
	private final NotificationListener m_listener;
	
	/**
	 * Api client version.
	 * 
	 * To check compatibility the difference with the first and third digit must be the same 
	 * whit the client version.
	 * 
	 * Exemple : Server version 0,1,2 : 0 - 2 = -2
	 *           Client version 1,1,3 : 1 - 3 = -2 
	 *           
	 *           Client and Server are compatible. 
	 */
	public static final int API_CLIENT_VERSION[] = { 1, 0, 0 };

	/**
	 * Default constructor.
	 * 
	 * @param host Host name of the server ConnectLife.
	 * @param port Port of the server ConnectLife.
	 * @param _listener Listner of the notification.
	 */
	public CLApiClient(String host, int port, NotificationListener _listener) {
	  m_channel = ManagedChannelBuilder.forAddress(host, port)
	      .usePlaintext(true)
	      .build();
	  m_blockingStub = CLApiGrpc.newBlockingStub(m_channel);
	  m_futureStub = CLApiGrpc.newFutureStub(m_channel);
	  m_listener = _listener;
	}
	
	/**
	 * Get the version of the server.
	 * @return Version number of the server.
	 */
	public String getVersion(){
		GetVersionRequest request = GetVersionRequest.newBuilder().build();
	    GetVersionResponse response = m_blockingStub.getVersion(request);
	    return response.getVersion();
	}
	
	/**
	 * Check if the client is compatible with the server.
	 * @return True if the server and client are compatible.
	 */
	public boolean checkCompatibility(){
		CheckCompatibilityRequest request = CheckCompatibilityRequest.newBuilder().setVersion(	API_CLIENT_VERSION[0] + "." +
																								API_CLIENT_VERSION[1] + "." + 
																								API_CLIENT_VERSION[2]).build();
		CheckCompatibilityResponse response = m_blockingStub.checkCompatibility(request);
	    return response.getCompatible();
	}
	
	/**
	 * Get the json data.
	 * @return Json representation of the data.
	 */
	public String getJsonData(){
		GetJsonDataRequest request = GetJsonDataRequest.newBuilder().build();
		GetJsonDataResponse response = m_blockingStub.getJsonData(request);
		return response.getData();
	}
	
	/**
	 * Add person in environment.
	 * 
	 * @param firstname First name of the person.
	 * @param lastname Last name of the person.
	 * @param imageurl Image url of the person.
	 * @return UID of the new person.
	 */
	public String addPerson(String firstname, String lastname, String imageurl){
		AddPersonRequest request = AddPersonRequest.newBuilder()
												   .setFirstname(firstname)
												   .setLastname(lastname)
												   .setImageurl(imageurl)
												   .build();
		AddPersonResponse response = m_blockingStub.addPerson(request);
		return response.getUid();
	}
	
	/**
	 * Update person in environment.
	 * @param uid UID of the person.
	 * @param firstname First name of the person. 
	 * @param lastname Last name of the person.
	 * @param imageurl Image url of the person.
	 * @return UID of the person.
	 */
	public String updatePerson(String uid, String firstname, String lastname, String imageurl){
		UpdatePersonRequest request = UpdatePersonRequest.newBuilder()
												   .setUid(uid)
												   .setFirstname(firstname)
												   .setLastname(lastname)
												   .setImageurl(imageurl)
												   .build();
		UpdatePersonResponse response = m_blockingStub.updatePerson(request);
		return response.getUid();
	}
	
	/**
	 * Delete the person.
	 * @param uid UID of the person.
	 * @return UID of the person.
	 */
	public String deletePerson(String uid){
		DeletePersonRequest request = DeletePersonRequest.newBuilder()
												   .setUid(uid)
												   .build();
		DeletePersonResponse response = m_blockingStub.deletePerson(request);
		return response.getUid();
	}
	
	/**
	 * Add email of the person.
	 * @param _uid UID of the person.
	 * @param _email Email of the person.
	 * @param _type  Type d'email of the person.
	 * @return UID of the person.
	 */
	public String AddEmail(String _uid, String _email, int _type){
		AddEmailRequest request = AddEmailRequest.newBuilder()
												 .setUidPerson(_uid)
												 .setEmail(_email)
												 .setType(_type)
												 .build();
		AddEmailResponse response = m_blockingStub.addEmail(request);
		return response.getUid();
	}
	
	/**
	 * Wait notification. When notification will arrived from server, 
	 * the listener will be notify.
	 */
	public void waitNotification(){
		
		WaitNotificationRequest request = WaitNotificationRequest.newBuilder().build();
		final ListenableFuture<WaitNotificationResponse> future = m_futureStub.waitNotification(request);
		future.addListener(new Runnable() {
		     public void run() {
		    	 try {
					WaitNotificationResponse response = future.get();
					m_listener.notificationReceive(response.getNotification());
					
				} catch (InterruptedException e) {
					m_logger.error(e.getMessage());
					e.printStackTrace();
				} catch (ExecutionException e) {
					m_logger.error(e.getMessage());
					e.printStackTrace();
				} catch (StatusRuntimeException e){
					m_logger.warn(e.getMessage());
				}
		     }
		   }, MoreExecutors.newDirectExecutorService());
		
	}
	
	/**
	 * Shutdown the client connection with the server.
	 * 
	 * @throws InterruptedException If timeout of shutdown is reach.
	 */
	public void shutdown() throws InterruptedException {
	    m_channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
	}
}
