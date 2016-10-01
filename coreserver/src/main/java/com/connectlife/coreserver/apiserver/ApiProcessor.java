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
import org.xnap.commons.i18n.I18n;

import com.clapi.data.*;
import com.clapi.data.Asset.AssetMode;
import com.clapi.data.Asset.AssetType;
import com.clapi.protocol.*;
import com.connectlife.coreserver.environment.cmd.*;
import com.connectlife.coreserver.tools.errormanagement.StdOutErrLog;
import com.clapi.protocol.Notification.NotificationType;
import com.connectlife.coreserver.Application;
import com.connectlife.coreserver.environment.Environment;
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
	private Logger m_logger = LogManager.getLogger(getClass().getName());
	
	/**
	 * Initialization of translation system.
	 */
	private static I18n i18n = Application.i18n;
	
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
	 * @param _environment Environment at use in the ApiProcessor.
	 */
	@Inject
	public ApiProcessor(Environment _environment){
		m_environment = _environment;
		m_environment.addObserver(this);
		m_wait_client_notification = new Vector<StreamObserver<WaitNotificationResponse>>();
	}

	/**
	 * Return the server api version.
	 * @param request Client request.
	 * @param responseObserver Response.
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
	 * @param request Client request.
	 * @param responseObserver Response.
	 */
	@Override
	public void checkCompatibility(CheckCompatibilityRequest request,
			StreamObserver<CheckCompatibilityResponse> responseObserver) {
		
		boolean ret_val = false;
		CheckCompatibilityResponse reply = null;
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
				m_logger.info(i18n.tr("The client version: ") 
						      + request.getVersion()
						      + i18n.tr(" isn't compatible with this server version : ") 
						      + new String(API_SERVER_VERSION[0] + "." + API_SERVER_VERSION[1] + "." + API_SERVER_VERSION[2]));
			}
			reply = CheckCompatibilityResponse.newBuilder().setCompatible(ret_val).build();
		}
		catch(Exception e){
			reply = CheckCompatibilityResponse.newBuilder()
											  .setCompatible(ret_val)
											  .setError(e.toString())
											  .build();
			
			m_logger.error(i18n.tr("Unable to check compatibility of client api : ") + request.getVersion());
			StdOutErrLog.tieSystemOutAndErrToLog();
			e.printStackTrace();
		}

		responseObserver.onNext(reply);
		responseObserver.onCompleted();
	}
	
	/**
	 * @param request Client request.
	 * @param responseObserver Response.
	 */
	@Override
	public synchronized void waitNotification(WaitNotificationRequest request,
			StreamObserver<WaitNotificationResponse> responseObserver) {

		m_wait_client_notification.remove(responseObserver);
		m_wait_client_notification.add(responseObserver);
		
		m_logger.info(i18n.tr("New client waiting for notification."));
	}
	
	/**
	 * Send this notification to all client in waiting.
	 * @param notification Notification to send at all client.
	 */
	public synchronized void sendNotificationToAllClient(Notification notification){
		
		Iterator<StreamObserver<WaitNotificationResponse>> itr = m_wait_client_notification.iterator();
		if(null != itr){
			while(itr.hasNext()){
				WaitNotificationResponse reply = WaitNotificationResponse.newBuilder().setNotification(notification).build();
				StreamObserver<WaitNotificationResponse> response = itr.next();
				response.onNext(reply);
				response.onCompleted();
				
				m_logger.info(i18n.tr("Notify client."));
			}
		}
		m_wait_client_notification.removeAllElements();
	}

	/**
	 * @param request Client request.
	 * @param responseObserver Response.
	 */
	@Override
	public void getJsonData(GetJsonDataRequest request, StreamObserver<GetJsonDataResponse> responseObserver) {
		GetJsonDataResponse reply = null;
		try {
			reply = GetJsonDataResponse.newBuilder().setData(m_environment.getJsonEnvironment()).build();
		}
		catch (Exception e) {
			
			reply = GetJsonDataResponse.newBuilder()
									   .setData("")
									   .setError(e.toString())
									   .build(); // no data in response if failed.
			
			m_logger.error(e.toString());
			StdOutErrLog.tieSystemOutAndErrToLog();
			e.printStackTrace();
		}
		responseObserver.onNext(reply);
		responseObserver.onCompleted();
		
	}
	
	/**
	 * @param request
	 * @param responseObserver
	 * @see com.clapi.protocol.CLApiGrpc.CLApi#addHome(com.clapi.protocol.AddHomeRequest, io.grpc.stub.StreamObserver)
	 */
	@Override
	public void addHome(AddHomeRequest request, StreamObserver<AddHomeResponse> responseObserver) {
		Home home = new Home("", request.getLabel(), null, request.getImageuid());
		AddHomeResponse reply = null;
		try {
			CmdAddHome cmd = CmdFactory.getCmdAddHome(home);
			m_environment.executeCommand(cmd);
			reply = AddHomeResponse.newBuilder().setUid(home.getUid()).build(); // uid is return to client.
			
		} 
		catch (Exception e) {
			
			reply = AddHomeResponse.newBuilder()
								   .setUid("")
								   .setError(e.toString())
								   .build(); // no uid in response if failed.
			
			m_logger.error(e.toString());
			StdOutErrLog.tieSystemOutAndErrToLog();
			e.printStackTrace();
		}
		
		responseObserver.onNext(reply);
		responseObserver.onCompleted();
	}

	/**
	 * @param request
	 * @param responseObserver
	 * @see com.clapi.protocol.CLApiGrpc.CLApi#updateHome(com.clapi.protocol.UpdateHomeRequest, io.grpc.stub.StreamObserver)
	 */
	@Override
	public void updateHome(UpdateHomeRequest request, StreamObserver<UpdateHomeResponse> responseObserver) {
		Home home = new Home(request.getUid(), request.getLabel(), null, request.getImageuid());
		UpdateHomeResponse reply = null;
		try {
			CmdUpdateHome cmd = CmdFactory.getCmdUpdateHome(home);
			m_environment.executeCommand(cmd);
			reply = UpdateHomeResponse.newBuilder().setUid(home.getUid()).build(); // uid is return to client.
			
		} catch (Exception e) {
			
			reply = UpdateHomeResponse.newBuilder()
									  .setUid("")
									  .setError(e.toString())
									  .build(); // no uid in response if failed.
			
			m_logger.error(e.toString());
			StdOutErrLog.tieSystemOutAndErrToLog();
			e.printStackTrace();
		}
		
		responseObserver.onNext(reply);
		responseObserver.onCompleted();
	}

	/**
	 * @param request
	 * @param responseObserver
	 * @see com.clapi.protocol.CLApiGrpc.CLApi#deleteHome(com.clapi.protocol.DeleteHomeRequest, io.grpc.stub.StreamObserver)
	 */
	@Override
	public void deleteHome(DeleteHomeRequest request, StreamObserver<DeleteHomeResponse> responseObserver) {
		Home home = new Home(request.getUid(), "", null, "");
		DeleteHomeResponse reply = null;
		try {
			CmdDeleteHome cmd = CmdFactory.getCmdDeleteHome(home);
			m_environment.executeCommand(cmd);
			reply = DeleteHomeResponse.newBuilder().setUid(home.getUid()).build(); // uid is return to client.
			
		} catch (Exception e) {
			
			reply = DeleteHomeResponse.newBuilder()
									  .setUid("")
									  .setError(e.toString())
									  .build(); // no uid in response if failed.
			
			m_logger.error(e.toString());
			StdOutErrLog.tieSystemOutAndErrToLog();
			e.printStackTrace();
		}
		
		responseObserver.onNext(reply);
		responseObserver.onCompleted();
	}

	/**
	 * @param request
	 * @param responseObserver
	 * @see com.clapi.protocol.CLApiGrpc.CLApi#addZone(com.clapi.protocol.AddZoneRequest, io.grpc.stub.StreamObserver)
	 */
	@Override
	public void addZone(AddZoneRequest request, StreamObserver<AddZoneResponse> responseObserver) {
		Home home = new Home(request.getUidHome(), null);
		Zone zone = new Zone("", request.getLabel(), null, request.getImageuid());
		AddZoneResponse reply = null;
		try {
			CmdAddZone cmd = CmdFactory.getCmdAddZone(zone, home);
			m_environment.executeCommand(cmd);
			reply = AddZoneResponse.newBuilder().setUid(zone.getUid()).build(); // uid is return to client.
			
		} catch (Exception e) {
			
			reply = AddZoneResponse.newBuilder()
								   .setUid("")
								   .setError(e.toString())
								   .build(); // no uid in response if failed.
			
			m_logger.error(e.toString());
			StdOutErrLog.tieSystemOutAndErrToLog();
			e.printStackTrace();
		}
		
		responseObserver.onNext(reply);
		responseObserver.onCompleted();
	}

	/**
	 * @param request
	 * @param responseObserver
	 * @see com.clapi.protocol.CLApiGrpc.CLApi#updateZone(com.clapi.protocol.UpdateZoneRequest, io.grpc.stub.StreamObserver)
	 */
	@Override
	public void updateZone(UpdateZoneRequest request, StreamObserver<UpdateZoneResponse> responseObserver) {
		Zone zone = new Zone(request.getUid(), request.getLabel(), null, request.getImageuid());
		UpdateZoneResponse reply = null;
		try {
			CmdUpdateZone cmd = CmdFactory.getCmdUpdateZone(zone);
			m_environment.executeCommand(cmd);
			reply = UpdateZoneResponse.newBuilder().setUid(zone.getUid()).build(); // uid is return to client.
			
		} catch (Exception e) {
			
			reply = UpdateZoneResponse.newBuilder()
									  .setUid("")
									  .setError(e.toString())
									  .build(); // no uid in response if failed.
			
			m_logger.error(e.toString());
			StdOutErrLog.tieSystemOutAndErrToLog();
			e.printStackTrace();
		}
		
		responseObserver.onNext(reply);
		responseObserver.onCompleted();
	}

	/**
	 * @param request
	 * @param responseObserver
	 * @see com.clapi.protocol.CLApiGrpc.CLApi#deleteZone(com.clapi.protocol.DeleteZoneRequest, io.grpc.stub.StreamObserver)
	 */
	@Override
	public void deleteZone(DeleteZoneRequest request, StreamObserver<DeleteZoneResponse> responseObserver) {
		Zone zone = new Zone(request.getUid(), "", null, "");
		DeleteZoneResponse reply = null;
		try {
			CmdDeleteZone cmd = CmdFactory.getCmdDeleteZone(zone);
			m_environment.executeCommand(cmd);
			reply = DeleteZoneResponse.newBuilder().setUid(zone.getUid()).build(); // uid is return to client.
			
		} catch (Exception e) {
			
			reply = DeleteZoneResponse.newBuilder()
									  .setUid("")
									  .setError(e.toString())
									  .build(); // no uid in response if failed.
			
			m_logger.error(e.toString());
			StdOutErrLog.tieSystemOutAndErrToLog();
			e.printStackTrace();
		}
		
		responseObserver.onNext(reply);
		responseObserver.onCompleted();
	}

	/**
	 * @param request
	 * @param responseObserver
	 * @see com.clapi.protocol.CLApiGrpc.CLApi#addRoom(com.clapi.protocol.AddRoomRequest, io.grpc.stub.StreamObserver)
	 */
	@Override
	public void addRoom(AddRoomRequest request, StreamObserver<AddRoomResponse> responseObserver) {
		Zone zone = new Zone(request.getUidZone(), "");
		Room room = new Room("", request.getLabel(), null, request.getImageuid());
		AddRoomResponse reply = null;
		try {
			CmdAddRoom cmd = CmdFactory.getCmdAddRoom(room, zone);
			m_environment.executeCommand(cmd);
			reply = AddRoomResponse.newBuilder().setUid(room.getUid()).build(); // uid is return to client.
			
		} catch (Exception e) {
			
			reply = AddRoomResponse.newBuilder()
								   .setUid("")
								   .setError(e.toString())
								   .build(); // no uid in response if failed.
			
			m_logger.error(e.toString());
			StdOutErrLog.tieSystemOutAndErrToLog();
			e.printStackTrace();
		}
		
		responseObserver.onNext(reply);
		responseObserver.onCompleted();
	}

	/**
	 * @param request
	 * @param responseObserver
	 * @see com.clapi.protocol.CLApiGrpc.CLApi#updateRoom(com.clapi.protocol.UpdateRoomRequest, io.grpc.stub.StreamObserver)
	 */
	@Override
	public void updateRoom(UpdateRoomRequest request, StreamObserver<UpdateRoomResponse> responseObserver) {
		Room room = new Room(request.getUid(), request.getLabel(), null, request.getImageuid());
		UpdateRoomResponse reply = null;
		try {
			CmdUpdateRoom cmd = CmdFactory.getCmdUpdateRoom(room);
			m_environment.executeCommand(cmd);
			reply = UpdateRoomResponse.newBuilder().setUid(room.getUid()).build(); // uid is return to client.
			
		} catch (Exception e) {
			
			reply = UpdateRoomResponse.newBuilder()
									  .setUid("")
									  .setError(e.toString())
									  .build(); // no uid in response if failed.
			
			m_logger.error(e.toString());
			StdOutErrLog.tieSystemOutAndErrToLog();
			e.printStackTrace();
		}
		
		responseObserver.onNext(reply);
		responseObserver.onCompleted();
	}

	/**
	 * @param request
	 * @param responseObserver
	 * @see com.clapi.protocol.CLApiGrpc.CLApi#deleteRoom(com.clapi.protocol.DeleteRoomRequest, io.grpc.stub.StreamObserver)
	 */
	@Override
	public void deleteRoom(DeleteRoomRequest request, StreamObserver<DeleteRoomResponse> responseObserver) {
		Room room = new Room(request.getUid(), "", null, "");
		DeleteRoomResponse reply = null;
		try {
			CmdDeleteRoom cmd = CmdFactory.getCmdDeleteRoom(room);
			m_environment.executeCommand(cmd);
			reply = DeleteRoomResponse.newBuilder().setUid(room.getUid()).build(); // uid is return to client.
			
		} catch (Exception e) {
			
			reply = DeleteRoomResponse.newBuilder()
									  .setUid("")
									  .setError(e.toString())
									  .build(); // no uid in response if failed.
			
			m_logger.error(e.toString());
			StdOutErrLog.tieSystemOutAndErrToLog();
			e.printStackTrace();
		}
		
		responseObserver.onNext(reply);
		responseObserver.onCompleted();
	}

	/**
	 * @param request Client request.
	 * @param responseObserver Response.
	 */
	@Override
	public void addPerson(AddPersonRequest request, StreamObserver<AddPersonResponse> responseObserver) {
		Person person = new Person("", request.getFirstname(), request.getLastname(), request.getImageuid());
		AddPersonResponse reply = null;
		try {
			CmdAddPerson cmd = CmdFactory.getCmdAddPerson(person);
			m_environment.executeCommand(cmd);
			reply = AddPersonResponse.newBuilder().setUid(person.getUid()).build(); // uid is return to client.
			
		} catch (Exception e) {
			
			reply = AddPersonResponse.newBuilder()
								     .setUid("")
								     .setError(e.toString())
								     .build(); // no uid in response if failed.
			
			m_logger.error(e.toString());
			StdOutErrLog.tieSystemOutAndErrToLog();
			e.printStackTrace();
		}
		
		responseObserver.onNext(reply);
		responseObserver.onCompleted();
	}

	/**
	 * @param request Client request.
	 * @param responseObserver Response.
	 */
	@Override
	public void updatePerson(UpdatePersonRequest request, StreamObserver<UpdatePersonResponse> responseObserver) {
		
		UpdatePersonResponse reply = null;
		
		// TODO - Complete the transaction
		reply = UpdatePersonResponse.newBuilder()
		    	.setUid("")
		   		.setError(i18n.tr("Not implemented yet!"))
		   		.build();
		
		/*
		try {
			Person person = m_environment.getFindProcessor().findPersonByUid(request.getUid());
			person.setFirstname(request.getFirstname());
			person.setLastname(request.getLastname());
			person.setImageurl(request.getImageurl());
			
			CmdAddPerson cmd = CmdFactory.getCmdAddPerson(person);
			m_environment.executeCommand(cmd);
			reply = AddPersonResponse.newBuilder().setUid(person.getUid()).build(); // uid is return to client.
			
		} catch (Exception e) {
			
			reply = AddPersonResponse.newBuilder()
								     .setUid("")
								     .setError(e.toString())
								     .build(); // no uid in response if failed.
			
			m_logger.error(e.toString());
			StdOutErrLog.tieSystemOutAndErrToLog();
			e.printStackTrace();
		}
		*/
		responseObserver.onNext(reply);
		responseObserver.onCompleted();
	}

	/**
	 * @param request Client request.
	 * @param responseObserver Response.
	 */
	@Override
	public void deletePerson(DeletePersonRequest request, StreamObserver<DeletePersonResponse> responseObserver) {
		
		DeletePersonResponse reply = null;
		
		// TODO - Complete the transaction
		reply = DeletePersonResponse.newBuilder()
		    	.setUid("")
		   		.setError(i18n.tr("Not implemented yet!"))
		   		.build();
		/*Person person = m_environment.getFindProcessorReadOnly().findPerson(new Person(request.getUid(), "", "", ""));
		try {
			person = m_environment.deletePerson(person);
			reply = DeletePersonResponse.newBuilder().setUid(person.getUid()).build(); // uid is return to client.
			
		} catch (Exception e) {
			
			reply = DeletePersonResponse.newBuilder()
								    	.setUid("")
								   		.setError(e.toString())
								   		.build(); // no uid in response if failed.
			
			m_logger.error(e.toString());
			StdOutErrLog.tieSystemOutAndErrToLog();
			e.printStackTrace();
		}
		*/
		responseObserver.onNext(reply);
		responseObserver.onCompleted();
	}

	/**
	 * @param request Client request.
	 * @param responseObserver Response.
	 */
	@Override
	public void addEmail(AddEmailRequest request, StreamObserver<AddEmailResponse> responseObserver) {
		
		AddEmailResponse reply = null;
		
		// TODO - Complete the transaction
		reply = AddEmailResponse.newBuilder()
		    	.setUid("")
		   		.setError(i18n.tr("Not implemented yet!"))
		   		.build();
		
		/*
		Person person = null;
		try {
			Email email = new Email("", request.getEmail(), Email.EmailType.PERSONAL);
			reply = AddEmailResponse.newBuilder().setUid(email.getUid()).build(); // uid is return to client.
			
		} catch (Exception e) {
			
			reply = AddEmailResponse.newBuilder().setUid("")
												 .setError(e.toString())
												 .build(); // no uid in response if failed.
			
			m_logger.error(e.toString());
			StdOutErrLog.tieSystemOutAndErrToLog();
			e.printStackTrace();
		}*/
		
		responseObserver.onNext(reply);
		responseObserver.onCompleted();
	}

	/**
	 * @param request Client request.
	 * @param responseObserver Response.
	 */
	@Override
	public void updateEmail(UpdateEmailRequest request, StreamObserver<UpdateEmailResponse> responseObserver) {
		
		UpdateEmailResponse reply = null;
		
		// TODO - Complete the transaction
		reply = UpdateEmailResponse.newBuilder()
		    	.setUid("")
		   		.setError(i18n.tr("Not implemented yet!"))
		   		.build();
		
		/*
		Person person = null;
		try {
			Email email = new Email(request.getUid(), request.getEmail(), EmailType.values()[request.getType()]);
			person.updateEmail(email);
			m_environment.updatePerson(person);
			
			reply = UpdateEmailResponse.newBuilder().setUid(email.getUid()).build(); // uid is return to client.
		} catch (Exception e) {
			
			reply = UpdateEmailResponse.newBuilder()
								   	   .setUid("")
								       .setError(e.toString())
								       .build(); // no uid in response if failed.
			
			m_logger.error(e.toString());
			StdOutErrLog.tieSystemOutAndErrToLog();
			e.printStackTrace();
		}
		*/
		responseObserver.onNext(reply);
		responseObserver.onCompleted();	
	}

	/**
	 * @param request Client request.
	 * @param responseObserver Response.
	 */
	@Override
	public void deleteEmail(DeleteEmailRequest request, StreamObserver<DeleteEmailResponse> responseObserver) {
		// TODO Auto-generated method stub
		DeleteEmailResponse reply = null;
		
		reply = DeleteEmailResponse.newBuilder()
		    	.setUid("")
		   		.setError(i18n.tr("Not implemented yet!"))
		   		.build();
		
		responseObserver.onNext(reply);
		responseObserver.onCompleted();		
	}

	/**
	 * @param request Client request.
	 * @param responseObserver Response.
	 */
	@Override
	public void addPhone(AddPhoneRequest request, StreamObserver<AddPhoneResponse> responseObserver) {
		// TODO Auto-generated method stub
		AddPhoneResponse reply = null;
		
		reply = AddPhoneResponse.newBuilder()
		    	.setUid("")
		   		.setError(i18n.tr("Not implemented yet!"))
		   		.build();
		
		responseObserver.onNext(reply);
		responseObserver.onCompleted();
	}

	/**
	 * @param request Client request.
	 * @param responseObserver Response.
	 */
	@Override
	public void updatePhone(UpdatePhoneRequest request, StreamObserver<UpdatePhoneResponse> responseObserver) {
		// TODO Auto-generated method stub
		UpdatePhoneResponse reply = null;
		
		reply = UpdatePhoneResponse.newBuilder()
		    	.setUid("")
		   		.setError(i18n.tr("Not implemented yet!"))
		   		.build();
		
		responseObserver.onNext(reply);
		responseObserver.onCompleted();
	}

	/**
	 * @param request Client request.
	 * @param responseObserver Response.
	 */
	@Override
	public void deletePhone(DeletePhoneRequest request, StreamObserver<DeletePhoneResponse> responseObserver) {
		// TODO Auto-generated method stub
		DeletePhoneResponse reply = null;
		
		reply = DeletePhoneResponse.newBuilder()
		    	.setUid("")
		   		.setError(i18n.tr("Not implemented yet!"))
		   		.build();
		
		responseObserver.onNext(reply);
		responseObserver.onCompleted();
	}

	/**
	 * @param request Client request.
	 * @param responseObserver Response.
	 */
	@Override
	public void addAddress(AddAddressRequest request, StreamObserver<AddAddressResponse> responseObserver) {
		// TODO Auto-generated method stub
		AddAddressResponse reply = null;
		
		reply = AddAddressResponse.newBuilder()
		    	.setUid("")
		   		.setError(i18n.tr("Not implemented yet!"))
		   		.build();
		
		responseObserver.onNext(reply);
		responseObserver.onCompleted();
	}

	/**
	 * @param request Client request.
	 * @param responseObserver Response.
	 */
	@Override
	public void updateAddress(UpdateAddressRequest request, StreamObserver<UpdateAddressResponse> responseObserver) {
		// TODO Auto-generated method stub
		UpdateAddressResponse reply = null;
		
		reply = UpdateAddressResponse.newBuilder()
		    	.setUid("")
		   		.setError(i18n.tr("Not implemented yet!"))
		   		.build();
		
		responseObserver.onNext(reply);
		responseObserver.onCompleted();
	}

	/**
	 * @param request Client request.
	 * @param responseObserver Response.
	 */
	@Override
	public void deleteAddress(DeleteAddressRequest request, StreamObserver<DeleteAddressResponse> responseObserver) {
		// TODO Auto-generated method stub
		DeleteAddressResponse reply = null;
		
		reply = DeleteAddressResponse.newBuilder()
		    	.setUid("")
		   		.setError(i18n.tr("Not implemented yet!"))
		   		.build();
		
		responseObserver.onNext(reply);
		responseObserver.onCompleted();
	}
	
	/**
	 * @param request Client request.
	 * @param responseObserver Response.
	 * @see com.clapi.protocol.CLApiGrpc.CLApi#addAccessory(com.clapi.protocol.AddAccessoryRequest, io.grpc.stub.StreamObserver)
	 */
	@Override
	public void addAccessory(AddAccessoryRequest request, StreamObserver<AddAccessoryResponse> responseObserver) {
		Accessory accessory = new Accessory(null, null, null, null, request.getSerialnumber(), null, null, null, null);
		Room room = new Room(request.getUidRoom(), null);
		AddAccessoryResponse reply = null;
		try {
			CmdAddAccessory cmd = CmdFactory.getCmdAddAccesssory(accessory, room);
			m_environment.executeCommand(cmd);
			accessory = cmd.getAccessory();
			reply = AddAccessoryResponse.newBuilder().setUid(accessory.getUid()).build(); // uid is return to client.
			
		} catch (Exception e) {
			
			reply = AddAccessoryResponse.newBuilder()
										.setUid("")
										.setError(e.toString())
										.build(); // no uid in response if failed.
			
			m_logger.error(e.toString());
			StdOutErrLog.tieSystemOutAndErrToLog();
			e.printStackTrace();
		}
		
		responseObserver.onNext(reply);
		responseObserver.onCompleted();
	}

	/**
	 * @param request Client request.
	 * @param responseObserver Response.
	 * @see com.clapi.protocol.CLApiGrpc.CLApi#deleteAccessory(com.clapi.protocol.DeleteAccessoryRequest, io.grpc.stub.StreamObserver)
	 */
	@Override
	public void deleteAccessory(DeleteAccessoryRequest request,
			StreamObserver<DeleteAccessoryResponse> responseObserver) {
		Accessory accessory = new Accessory(request.getUid(), null, null, null, null, null, null, null, null);
		DeleteAccessoryResponse reply = null;
		try {
			CmdDeleteAccessory cmd = CmdFactory.getCmdDeleteAccesssory(accessory);
			m_environment.executeCommand(cmd);
			reply = DeleteAccessoryResponse.newBuilder().setUid(accessory.getUid()).build(); // uid is return to client.
			
		} catch (Exception e) {
			
			reply = DeleteAccessoryResponse.newBuilder()
										   .setUid("")
										   .setError(e.toString())
										   .build(); // no uid in response if failed.
			
			m_logger.error(e.toString());
			StdOutErrLog.tieSystemOutAndErrToLog();
			e.printStackTrace();
		}
		
		responseObserver.onNext(reply);
		responseObserver.onCompleted();
	}
	
	/**
	 * @param request
	 * @param responseObserver
	 * @see com.clapi.protocol.CLApiGrpc.CLApi#addAsset(com.clapi.protocol.AddAssetRequest, io.grpc.stub.StreamObserver)
	 */
	@Override
	public void addAsset(AddAssetRequest request, StreamObserver<AddAssetResponse> responseObserver) {
		// TODO: Manage type and mode correctly
		Asset asset = new Asset("", request.getLabel(), AssetType.IMAGE, AssetMode.USER);
		AddAssetResponse reply = null;
		try {
			CmdAddAsset cmd = CmdFactory.getCmdAddAsset(asset, request.getData());
			m_environment.executeCommand(cmd);
			reply = AddAssetResponse.newBuilder().setUid(asset.getUid()).build(); // uid is return to client.
			
		} catch (Exception e) {
			
			reply = AddAssetResponse.newBuilder()
									.setUid("")
									.setError(e.toString())
									.build(); // no uid in response if failed.
			
			m_logger.error(e.toString());
			StdOutErrLog.tieSystemOutAndErrToLog();
			e.printStackTrace();
		}
		
		responseObserver.onNext(reply);
		responseObserver.onCompleted();
	}

	/**
	 * @param request
	 * @param responseObserver
	 * @see com.clapi.protocol.CLApiGrpc.CLApi#updateAsset(com.clapi.protocol.UpdateAssetRequest, io.grpc.stub.StreamObserver)
	 */
	@Override
	public void updateAsset(UpdateAssetRequest request, StreamObserver<UpdateAssetResponse> responseObserver) {
		// TODO: Manage type and mode correctly
		Asset asset = new Asset(request.getUid(), request.getLabel(), AssetType.IMAGE, AssetMode.USER);
		UpdateAssetResponse reply = null;
		try {
			CmdUpdateAsset cmd = CmdFactory.getCmdUpdateAsset(asset, request.getData());
			m_environment.executeCommand(cmd);
			reply = UpdateAssetResponse.newBuilder().setUid(asset.getUid()).build(); // uid is return to client.
			
		} catch (Exception e) {
			
			reply = UpdateAssetResponse.newBuilder()
									   .setUid("")
									   .setError(e.toString())
									   .build(); // no uid in response if failed.
			
			m_logger.error(e.toString());
			StdOutErrLog.tieSystemOutAndErrToLog();
			e.printStackTrace();
		}
		
		responseObserver.onNext(reply);
		responseObserver.onCompleted();
	}

	/**
	 * @param request
	 * @param responseObserver
	 * @see com.clapi.protocol.CLApiGrpc.CLApi#deleteAsset(com.clapi.protocol.DeleteAssetRequest, io.grpc.stub.StreamObserver)
	 */
	@Override
	public void deleteAsset(DeleteAssetRequest request, StreamObserver<DeleteAssetResponse> responseObserver) {
		Asset asset = new Asset(request.getUid(), "", null, null);
		DeleteAssetResponse reply = null;
		try {
			CmdDeleteAsset cmd = CmdFactory.getCmdDeleteAsset(asset);
			m_environment.executeCommand(cmd);
			reply = DeleteAssetResponse.newBuilder().setUid(asset.getUid()).build(); // uid is return to client.
			
		} catch (Exception e) {
			
			reply = DeleteAssetResponse.newBuilder()
									   .setUid("")
									   .setError(e.toString())
									   .build(); // no uid in response if failed.
			
			m_logger.error(e.toString());
			StdOutErrLog.tieSystemOutAndErrToLog();
			e.printStackTrace();
		}
		
		responseObserver.onNext(reply);
		responseObserver.onCompleted();
	}

	/**
	 * @param request
	 * @param responseObserver
	 * @see com.clapi.protocol.CLApiGrpc.CLApi#getAssetUrl(com.clapi.protocol.GetAssetUrlRequest, io.grpc.stub.StreamObserver)
	 */
	@Override
	public void getAssetUrl(GetAssetUrlRequest request, StreamObserver<GetAssetUrlResponse> responseObserver) {
		Asset asset = new Asset(request.getUid(), "", null, null);
		GetAssetUrlResponse reply = null;
		try {
			CmdGetAssetUrl cmd = CmdFactory.getCmdGetAssetUrl(asset);
			m_environment.executeCommand(cmd);
			reply = GetAssetUrlResponse.newBuilder().setUrl(cmd.getUrl()).build(); // uid is return to client.
			
		} catch (Exception e) {
			
			reply = GetAssetUrlResponse.newBuilder()
									   .setUrl("")
									   .setError(e.toString())
									   .build(); // no url in response if failed.
			
			m_logger.error(e.toString());
			StdOutErrLog.tieSystemOutAndErrToLog();
			e.printStackTrace();
		}
		
		responseObserver.onNext(reply);
		responseObserver.onCompleted();
	}
	
	/**
	 * @param o Object source.
	 * @param arg Argument of the event.
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable o, Object arg) {
		if(m_environment == o){
			m_logger.info(i18n.tr("Environment was updated, send new environment at all client."));
			
			sendNotificationToAllClient( Notification.newBuilder()
													 .setType(NotificationType.ENV_UPDATED)
													 .setData(m_environment.getJsonEnvironment())
													 .build() );
		}
	}
}
