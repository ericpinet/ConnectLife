/**
 *  ApiProcessor.java
 *  coreserver
 *
 *  Created by ericpinet on 2015-11-07.
 *  Copyright (c) 2015 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.connectlife.coreserver.apiserver;

import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.clapi.*;
import com.clapi.Notification.NotificationType;
import com.connectlife.coreserver.environment.Environment;
import com.connectlife.coreserver.tools.errormanagement.StdOutErrLog;
import com.google.inject.Inject;

import io.grpc.stub.StreamObserver;

/**
 * Procesor for the client connection at the API GRPC.
 * 
 * @author ericpinet
 * <br> 2015-11-07
 */
public class ApiProcessor implements CLApiGrpc.CLApi, Observer {
	
	/**
	 * Logger use for this class.
	 */
	private final Logger m_logger = LogManager.getLogger(ApiProcessor.class);
	
	/**
	 * Api server version.
	 * 
	 * To check compatibility the difference with the first and third digit must be the same 
	 * whit the client version.
	 * 
	 * Exemple : Server version 0,1,2 : 0 - 2 = -2
	 *           Client version 1,1,3 : 1 - 3 = -2 
	 *           
	 *           Client and Server are compatible. 
	 */
	public static final int API_SERVER_VERSION[] = { 1, 0, 0 };

	
	/**
	 * Environment linked to this processor.
	 */
	private final Environment m_environment;
	
	 
	/**
	 * Vector of client connection waiting for notification.
	 */
	private Vector<StreamObserver<WaitNotificationResponse>> m_wait_client_notification;
	
	/**
	 * Default constructor.
	 * @param _environment
	 */
	@Inject
	public ApiProcessor(Environment _environment){
		m_environment = _environment;
		m_environment.addObserver(this);
		m_wait_client_notification = new Vector<StreamObserver<WaitNotificationResponse>>();
	}

	/**
	 * 
	 * @param request
	 * @param responseObserver
	 * @see com.clapi.CLApiGrpc.CLApi#getVersion(com.clapi.GetVersionRequest, io.grpc.stub.StreamObserver)
	 */
	@Override
	public void getVersion(GetVersionRequest request, StreamObserver<GetVersionResponse> responseObserver) {
		GetVersionResponse reply = GetVersionResponse.newBuilder().setVersion(	API_SERVER_VERSION[0] + "." +
																				API_SERVER_VERSION[1] + "." +
																				API_SERVER_VERSION[2]).build();
		responseObserver.onNext(reply);
		responseObserver.onCompleted();
	}
	
	/**
	 * @param request
	 * @param responseObserver
	 * @see com.clapi.CLApiGrpc.CLApi#checkCompatibility(com.clapi.CheckCompatibilityRequest, io.grpc.stub.StreamObserver)
	 */
	@Override
	public void checkCompatibility(CheckCompatibilityRequest request,
			StreamObserver<CheckCompatibilityResponse> responseObserver) {
		
		boolean ret_val = false;
		
		try{
			String[] version_id_txt = request.getVersion().split("\\.");
			int[] version_id = { Integer.parseInt(version_id_txt[0]),
								 Integer.parseInt(version_id_txt[1]),
								 Integer.parseInt(version_id_txt[2])
							   };
			
			
			if( (version_id[0] - version_id[2]) ==  (API_SERVER_VERSION[0] - API_SERVER_VERSION[2]) ){
				
				ret_val = true;
			}
			else{
				m_logger.info("The client version:" + request.getVersion() + " isn't compatible with this server version :"+
						      new String( API_SERVER_VERSION[0] + "." + API_SERVER_VERSION[1] + "." + API_SERVER_VERSION[2] ));
			}
		}
		catch(Exception e){
			m_logger.error("Unable to check compatibility of client api : " + request.getVersion());
			StdOutErrLog.tieSystemOutAndErrToLog();
			e.printStackTrace();
		}
		
		CheckCompatibilityResponse reply = CheckCompatibilityResponse.newBuilder().setCompatible(ret_val).build();
		responseObserver.onNext(reply);
		responseObserver.onCompleted();
	}
	
	


	/**
	 * @param request
	 * @param responseObserver
	 * @see com.clapi.CLApiGrpc.CLApi#waitNotification(com.clapi.WaitNotificationRequest, io.grpc.stub.StreamObserver)
	 */
	@Override
	public synchronized void waitNotification(WaitNotificationRequest request,
			StreamObserver<WaitNotificationResponse> responseObserver) {

		m_wait_client_notification.remove(responseObserver);
		m_wait_client_notification.add(responseObserver);
		
		m_logger.info("New client waiting for notification.");
	}
	
	/**
	 * Send this notification to all client in waiting.
	 * @param notification
	 */
	public synchronized void sendNotificationToAllClient(Notification notification){
		
		Iterator<StreamObserver<WaitNotificationResponse>> itr = m_wait_client_notification.iterator();
		if(null != itr){
			while(itr.hasNext()){
				WaitNotificationResponse reply = WaitNotificationResponse.newBuilder().setNotification(notification).build();
				StreamObserver<WaitNotificationResponse> response = itr.next();
				response.onNext(reply);
				response.onCompleted();
				
				m_logger.info("Notify client.");
			}
		}
		m_wait_client_notification.removeAllElements();
	}

	/**
	 * @param request
	 * @param responseObserver
	 * @see com.clapi.CLApiGrpc.CLApi#getJsonData(com.clapi.GetJsonDataRequest, io.grpc.stub.StreamObserver)
	 */
	@Override
	public void getJsonData(GetJsonDataRequest request, StreamObserver<GetJsonDataResponse> responseObserver) {
		GetJsonDataResponse reply = GetJsonDataResponse.newBuilder().setData(m_environment.getJsonEnvironment()).build();
		responseObserver.onNext(reply);
		responseObserver.onCompleted();
	}

	/**
	 * @param request
	 * @param responseObserver
	 * @see com.clapi.CLApiGrpc.CLApi#addPerson(com.clapi.AddPersonRequest, io.grpc.stub.StreamObserver)
	 */
	@Override
	public void addPerson(AddPersonRequest request, StreamObserver<AddPersonResponse> responseObserver) {
		String uid = m_environment.addPerson(request.getFirstname(), request.getLastname(), request.getImageurl());
		AddPersonResponse reply = AddPersonResponse.newBuilder().setUid(uid).build();
		responseObserver.onNext(reply);
		responseObserver.onCompleted();
	}

	/**
	 * @param request
	 * @param responseObserver
	 * @see com.clapi.CLApiGrpc.CLApi#updatePerson(com.clapi.UpdatePersonRequest, io.grpc.stub.StreamObserver)
	 */
	@Override
	public void updatePerson(UpdatePersonRequest request, StreamObserver<UpdatePersonResponse> responseObserver) {
		String uid = m_environment.updatePerson(request.getUid(), request.getFirstname(), request.getLastname(), request.getImageurl());
		UpdatePersonResponse reply = UpdatePersonResponse.newBuilder().setUid(uid).build();
		responseObserver.onNext(reply);
		responseObserver.onCompleted();
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
	 * @see com.clapi.CLApiGrpc.CLApi#addEmail(com.clapi.AddEmailRequest, io.grpc.stub.StreamObserver)
	 */
	@Override
	public void addEmail(AddEmailRequest request, StreamObserver<AddEmailResponse> responseObserver) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @param request
	 * @param responseObserver
	 * @see com.clapi.CLApiGrpc.CLApi#updateEmail(com.clapi.UpdateEmailRequest, io.grpc.stub.StreamObserver)
	 */
	@Override
	public void updateEmail(UpdateEmailRequest request, StreamObserver<UpdateEmailResponse> responseObserver) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @param request
	 * @param responseObserver
	 * @see com.clapi.CLApiGrpc.CLApi#deleteEmail(com.clapi.DeleteEmailRequest, io.grpc.stub.StreamObserver)
	 */
	@Override
	public void deleteEmail(DeleteEmailRequest request, StreamObserver<DeleteEmailResponse> responseObserver) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @param o
	 * @param arg
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable o, Object arg) {
		if(m_environment == o){
			m_logger.info("Environment was updated, send new environment at all client.");
			
			sendNotificationToAllClient( Notification.newBuilder()
													 .setType(NotificationType.ENV_UPDATED)
													 .setData(m_environment.getJsonEnvironment())
													 .build() );
		}
	}

	/**
	 * @param request
	 * @param responseObserver
	 * @see com.clapi.CLApiGrpc.CLApi#addPhone(com.clapi.AddPhoneRequest, io.grpc.stub.StreamObserver)
	 */
	@Override
	public void addPhone(AddPhoneRequest request, StreamObserver<AddPhoneResponse> responseObserver) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @param request
	 * @param responseObserver
	 * @see com.clapi.CLApiGrpc.CLApi#updatePhone(com.clapi.UpdatePhoneRequest, io.grpc.stub.StreamObserver)
	 */
	@Override
	public void updatePhone(UpdatePhoneRequest request, StreamObserver<UpdatePhoneResponse> responseObserver) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @param request
	 * @param responseObserver
	 * @see com.clapi.CLApiGrpc.CLApi#deletePhone(com.clapi.DeletePhoneRequest, io.grpc.stub.StreamObserver)
	 */
	@Override
	public void deletePhone(DeletePhoneRequest request, StreamObserver<DeletePhoneResponse> responseObserver) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @param request
	 * @param responseObserver
	 * @see com.clapi.CLApiGrpc.CLApi#addAddress(com.clapi.AddAddressRequest, io.grpc.stub.StreamObserver)
	 */
	@Override
	public void addAddress(AddAddressRequest request, StreamObserver<AddAddressResponse> responseObserver) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @param request
	 * @param responseObserver
	 * @see com.clapi.CLApiGrpc.CLApi#updateAddress(com.clapi.UpdateAddressRequest, io.grpc.stub.StreamObserver)
	 */
	@Override
	public void updateAddress(UpdateAddressRequest request, StreamObserver<UpdateAddressResponse> responseObserver) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @param request
	 * @param responseObserver
	 * @see com.clapi.CLApiGrpc.CLApi#deleteAddress(com.clapi.DeleteAddressRequest, io.grpc.stub.StreamObserver)
	 */
	@Override
	public void deleteAddress(DeleteAddressRequest request, StreamObserver<DeleteAddressResponse> responseObserver) {
		// TODO Auto-generated method stub
		
	}
}
