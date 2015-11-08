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

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.clapi.CLApiGrpc;
import com.clapi.GetVersionRequest;
import com.clapi.GetVersionResponse;
import com.clapi.WaitNotificationRequest;
import com.clapi.WaitNotificationResponse;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

/**
 * 
 * 
 * @author ericpinet
 * <br> 2015-11-05
 */
public class CLApiClient {
	
	/**
	 * Init logger instance for this class
	 */
	private static Logger m_logger = LogManager.getLogger(CLApiClient.class);
	
	private final ManagedChannel channel;
	private final CLApiGrpc.CLApiBlockingStub blockingStub;
	private final CLApiGrpc.CLApiFutureStub futureStub;
	
	private final NotificationListener listener;

	public CLApiClient(String host, int port, NotificationListener _listener) {
	  channel = ManagedChannelBuilder.forAddress(host, port)
	      .usePlaintext(true)
	      .build();
	  blockingStub = CLApiGrpc.newBlockingStub(channel);
	  futureStub = CLApiGrpc.newFutureStub(channel);
	  listener = _listener;
	}
	
	public String getVersion(){
		GetVersionRequest request = GetVersionRequest.newBuilder().build();
	    GetVersionResponse response = blockingStub.getVersion(request);
	    return response.getVersion();
	}
	
	public void waitNotification(){
		ListeningExecutorService pool = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(10));
		
		WaitNotificationRequest request = WaitNotificationRequest.newBuilder().build();
		ListenableFuture<WaitNotificationResponse> future = futureStub.waitNotification(request);
		future.addListener(new Runnable() {
		     public void run() {
		    	 try {
					WaitNotificationResponse response = future.get();
					
					listener.notificationReceive(response.getNotification());
					
				} catch (InterruptedException e) {
					m_logger.error(e.getMessage());
					e.printStackTrace();
				} catch (ExecutionException e) {
					m_logger.error(e.getMessage());
					e.printStackTrace();
				}
		     }
		   }, MoreExecutors.sameThreadExecutor());
		
	}
	
	public void shutdown() throws InterruptedException {
	    channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
	}
}
