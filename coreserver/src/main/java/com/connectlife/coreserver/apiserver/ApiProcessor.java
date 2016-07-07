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

import com.clapi.data.Accessory;
import com.clapi.data.Home;
import com.clapi.data.Person;
import com.clapi.data.Room;
import com.clapi.data.Zone;
import com.clapi.protocol.*;
import com.clapi.protocol.Notification.NotificationType;
import com.connectlife.coreserver.environment.Environment;
import com.connectlife.coreserver.environment.cmd.CmdAddAccessory;
import com.connectlife.coreserver.environment.cmd.CmdAddHome;
import com.connectlife.coreserver.environment.cmd.CmdAddPerson;
import com.connectlife.coreserver.environment.cmd.CmdAddRoom;
import com.connectlife.coreserver.environment.cmd.CmdAddZone;
import com.connectlife.coreserver.environment.cmd.CmdDeleteAccessory;
import com.connectlife.coreserver.environment.cmd.CmdDeleteHome;
import com.connectlife.coreserver.environment.cmd.CmdDeleteRoom;
import com.connectlife.coreserver.environment.cmd.CmdDeleteZone;
import com.connectlife.coreserver.environment.cmd.CmdFactory;
import com.connectlife.coreserver.environment.cmd.CmdUpdateHome;
import com.connectlife.coreserver.environment.cmd.CmdUpdateRoom;
import com.connectlife.coreserver.environment.cmd.CmdUpdateZone;
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
	 * @param request Client request.
	 * @param responseObserver Response.
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
				
				m_logger.info("Notify client.");
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
		GetJsonDataResponse reply = GetJsonDataResponse.newBuilder().setData(m_environment.getJsonEnvironment()).build();
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
		Home home = new Home("", request.getLabel(), null, request.getImageurl());
		AddHomeResponse reply = null;
		try {
			CmdAddHome cmd = CmdFactory.getCmdAddHome(home);
			m_environment.executeCommand(cmd);
			reply = AddHomeResponse.newBuilder().setUid(home.getUid()).build(); // uid is return to client.
			
		} catch (Exception e) {
			
			reply = AddHomeResponse.newBuilder().setUid("").build(); // no uid in response if failed.
			
			m_logger.error(e.getMessage());
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
		Home home = new Home(request.getUid(), request.getLabel(), null, request.getImageurl());
		UpdateHomeResponse reply = null;
		try {
			CmdUpdateHome cmd = CmdFactory.getCmdUpdateHome(home);
			m_environment.executeCommand(cmd);
			reply = UpdateHomeResponse.newBuilder().setUid(home.getUid()).build(); // uid is return to client.
			
		} catch (Exception e) {
			
			reply = UpdateHomeResponse.newBuilder().setUid("").build(); // no uid in response if failed.
			
			m_logger.error(e.getMessage());
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
			
			reply = DeleteHomeResponse.newBuilder().setUid("").build(); // no uid in response if failed.
			
			m_logger.error(e.getMessage());
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
		Zone zone = new Zone("", request.getLabel(), null, request.getImageurl());
		AddZoneResponse reply = null;
		try {
			CmdAddZone cmd = CmdFactory.getCmdAddZone(zone, home);
			m_environment.executeCommand(cmd);
			reply = AddZoneResponse.newBuilder().setUid(zone.getUid()).build(); // uid is return to client.
			
		} catch (Exception e) {
			
			reply = AddZoneResponse.newBuilder().setUid("").build(); // no uid in response if failed.
			
			m_logger.error(e.getMessage());
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
		Zone zone = new Zone(request.getUid(), request.getLabel(), null, request.getImageurl());
		UpdateZoneResponse reply = null;
		try {
			CmdUpdateZone cmd = CmdFactory.getCmdUpdateZone(zone);
			m_environment.executeCommand(cmd);
			reply = UpdateZoneResponse.newBuilder().setUid(zone.getUid()).build(); // uid is return to client.
			
		} catch (Exception e) {
			
			reply = UpdateZoneResponse.newBuilder().setUid("").build(); // no uid in response if failed.
			
			m_logger.error(e.getMessage());
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
			
			reply = DeleteZoneResponse.newBuilder().setUid("").build(); // no uid in response if failed.
			
			m_logger.error(e.getMessage());
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
		Room room = new Room("", request.getLabel(), null, request.getImageurl());
		AddRoomResponse reply = null;
		try {
			CmdAddRoom cmd = CmdFactory.getCmdAddRoom(room);
			m_environment.executeCommand(cmd);
			reply = AddRoomResponse.newBuilder().setUid(room.getUid()).build(); // uid is return to client.
			
		} catch (Exception e) {
			
			reply = AddRoomResponse.newBuilder().setUid("").build(); // no uid in response if failed.
			
			m_logger.error(e.getMessage());
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
		Room room = new Room(request.getUid(), request.getLabel(), null, request.getImageurl());
		UpdateRoomResponse reply = null;
		try {
			CmdUpdateRoom cmd = CmdFactory.getCmdUpdateRoom(room);
			m_environment.executeCommand(cmd);
			reply = UpdateRoomResponse.newBuilder().setUid(room.getUid()).build(); // uid is return to client.
			
		} catch (Exception e) {
			
			reply = UpdateRoomResponse.newBuilder().setUid("").build(); // no uid in response if failed.
			
			m_logger.error(e.getMessage());
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
			
			reply = DeleteRoomResponse.newBuilder().setUid("").build(); // no uid in response if failed.
			
			m_logger.error(e.getMessage());
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
		Person person = new Person("", request.getFirstname(), request.getLastname(), request.getImageurl());
		AddPersonResponse reply = null;
		try {
			CmdAddPerson cmd = CmdFactory.getCmdAddPerson(person);
			m_environment.executeCommand(cmd);
			reply = AddPersonResponse.newBuilder().setUid(person.getUid()).build(); // uid is return to client.
			
		} catch (Exception e) {
			
			reply = AddPersonResponse.newBuilder().setUid("").build(); // no uid in response if failed.
			
			m_logger.error(e.getMessage());
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
			
			reply = AddPersonResponse.newBuilder().setUid("").build(); // no uid in response if failed.
			
			m_logger.error(e.getMessage());
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
		/*Person person = m_environment.getFindProcessorReadOnly().findPerson(new Person(request.getUid(), "", "", ""));
		try {
			person = m_environment.deletePerson(person);
			reply = DeletePersonResponse.newBuilder().setUid(person.getUid()).build(); // uid is return to client.
			
		} catch (Exception e) {
			
			reply = DeletePersonResponse.newBuilder().setUid("").build(); // no uid in response if failed.
			
			m_logger.error(e.getMessage());
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
		/*
		Person person = m_environment.getFindProcessorReadOnly().findPerson(new Person(request.getUidPerson(), "", "", ""));
		try {
			Email email = new Email(UIDGenerator.getUID(), request.getEmail(), EmailType.values()[request.getType()]);
			person.addEmails(email);
			m_environment.updatePerson(person);
			
			reply = AddEmailResponse.newBuilder().setUid(email.getUid()).build(); // uid is return to client.
			
		} catch (Exception e) {
			
			reply = AddEmailResponse.newBuilder().setUid("").build(); // no uid in response if failed.
			
			m_logger.error(e.getMessage());
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
	public void updateEmail(UpdateEmailRequest request, StreamObserver<UpdateEmailResponse> responseObserver) {
		
		UpdateEmailResponse reply = null;
		/*Person person = null;
		try {
			Email email = new Email(request.getUid(), request.getEmail(), EmailType.values()[request.getType()]);
			person.updateEmail(email);
			m_environment.updatePerson(person);
			
			reply = UpdateEmailResponse.newBuilder().setUid(email.getUid()).build(); // uid is return to client.
		} catch (Exception e) {
			
			reply = UpdateEmailResponse.newBuilder().setUid("").build(); // no uid in response if failed.
			
			m_logger.error(e.getMessage());
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
	public void deleteEmail(DeleteEmailRequest request, StreamObserver<DeleteEmailResponse> responseObserver) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @param request Client request.
	 * @param responseObserver Response.
	 */
	@Override
	public void addPhone(AddPhoneRequest request, StreamObserver<AddPhoneResponse> responseObserver) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @param request Client request.
	 * @param responseObserver Response.
	 */
	@Override
	public void updatePhone(UpdatePhoneRequest request, StreamObserver<UpdatePhoneResponse> responseObserver) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @param request Client request.
	 * @param responseObserver Response.
	 */
	@Override
	public void deletePhone(DeletePhoneRequest request, StreamObserver<DeletePhoneResponse> responseObserver) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @param request Client request.
	 * @param responseObserver Response.
	 */
	@Override
	public void addAddress(AddAddressRequest request, StreamObserver<AddAddressResponse> responseObserver) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @param request Client request.
	 * @param responseObserver Response.
	 */
	@Override
	public void updateAddress(UpdateAddressRequest request, StreamObserver<UpdateAddressResponse> responseObserver) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @param request Client request.
	 * @param responseObserver Response.
	 */
	@Override
	public void deleteAddress(DeleteAddressRequest request, StreamObserver<DeleteAddressResponse> responseObserver) {
		// TODO Auto-generated method stub
		
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
			
			reply = AddAccessoryResponse.newBuilder().setUid("").build(); // no uid in response if failed.
			
			m_logger.error(e.getMessage());
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
			
			reply = DeleteAccessoryResponse.newBuilder().setUid("").build(); // no uid in response if failed.
			
			m_logger.error(e.getMessage());
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
			m_logger.info("Environment was updated, send new environment at all client.");
			
			sendNotificationToAllClient( Notification.newBuilder()
													 .setType(NotificationType.ENV_UPDATED)
													 .setData(m_environment.getJsonEnvironment())
													 .build() );
		}
	}
}
