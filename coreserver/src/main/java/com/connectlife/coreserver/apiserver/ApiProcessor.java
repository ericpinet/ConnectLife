/**
 *  ApiProcessor.java
 *  coreserver
 *
 *  Created by ericpinet on 2015-11-07.
 *  Copyright (c) 2015 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.connectlife.coreserver.apiserver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.clapi.AddPersonRequest;
import com.clapi.AddPersonResponse;
import com.clapi.CLApiGrpc;
import com.clapi.DeleteAllPersonRequest;
import com.clapi.DeleteAllPersonResponse;
import com.clapi.DeletePersonRequest;
import com.clapi.DeletePersonResponse;
import com.clapi.Email;
import com.clapi.GetVersionRequest;
import com.clapi.GetVersionResponse;
import com.clapi.Notification;
import com.clapi.WaitNotificationRequest;
import com.clapi.WaitNotificationResponse;
import com.clapi.Notification.NotificationType;
import com.clapi.Person;
import com.clapi.UpdatePersonRequest;
import com.clapi.UpdatePersonResponse;
import com.connectlife.coreserver.tools.errormanagement.StdOutErrLog;

import io.grpc.stub.StreamObserver;

/**
 * 
 * 
 * @author ericpinet
 * <br> 2015-11-07
 */
public class ApiProcessor implements CLApiGrpc.CLApi{
	
	/**
	 * Logger use for this class.
	 */
	private final Logger m_logger = LogManager.getLogger(ApiProcessor.class);

	/**
	 * @param request
	 * @param responseObserver
	 * @see com.clapi.CLApiGrpc.CLApi#getVersion(com.clapi.GetVersionRequest, io.grpc.stub.StreamObserver)
	 */
	@Override
	public void getVersion(GetVersionRequest request, StreamObserver<GetVersionResponse> responseObserver) {
		GetVersionResponse reply = GetVersionResponse.newBuilder().setVersion("1.0.0").build();
		responseObserver.onNext(reply);
		responseObserver.onCompleted();
	}

	/**
	 * @param request
	 * @param responseObserver
	 * @see com.clapi.CLApiGrpc.CLApi#waitNotification(com.clapi.WaitNotificationRequest, io.grpc.stub.StreamObserver)
	 */
	@Override
	public void waitNotification(WaitNotificationRequest request,
			StreamObserver<WaitNotificationResponse> responseObserver) {
		
		try {
			Thread.sleep(2000);
			Notification notify = Notification.newBuilder()
											  .setType(NotificationType.ENV_UPDATED)
											  .build();
			
			WaitNotificationResponse reply = WaitNotificationResponse.newBuilder().setNotification(notify).build();
			responseObserver.onNext(reply);
			responseObserver.onCompleted();
			
		} catch (InterruptedException e) {
			m_logger.error(e.getMessage());
			StdOutErrLog.tieSystemOutAndErrToLog();
			e.printStackTrace();
		}
	}

	/**
	 * @param request
	 * @param responseObserver
	 * @see com.clapi.CLApiGrpc.CLApi#addPerson(com.clapi.AddPersonRequest, io.grpc.stub.StreamObserver)
	 */
	@Override
	public void addPerson(AddPersonRequest request, StreamObserver<AddPersonResponse> responseObserver) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @param request
	 * @param responseObserver
	 * @see com.clapi.CLApiGrpc.CLApi#updatePerson(com.clapi.UpdatePersonRequest, io.grpc.stub.StreamObserver)
	 */
	@Override
	public void updatePerson(UpdatePersonRequest request, StreamObserver<UpdatePersonResponse> responseObserver) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @param request
	 * @param responseObserver
	 * @see com.clapi.CLApiGrpc.CLApi#deletePerson(com.clapi.DeletePersonRequest, io.grpc.stub.StreamObserver)
	 */
	@Override
	public void deletePerson(DeletePersonRequest request, StreamObserver<DeletePersonResponse> responseObserver) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @param request
	 * @param responseObserver
	 * @see com.clapi.CLApiGrpc.CLApi#deleteAllPerson(com.clapi.DeleteAllPersonRequest, io.grpc.stub.StreamObserver)
	 */
	@Override
	public void deleteAllPerson(DeleteAllPersonRequest request,
			StreamObserver<DeleteAllPersonResponse> responseObserver) {
		// TODO Auto-generated method stub
		
	}

}
