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
import com.google.protobuf.ByteString;

/**
 * Client to connect with connectlife server. 
 * 
 * <p>
 * <pre>
 * {@code 
 * CLApiClient client = new CLApiClient("127.0.0.1", 9008, notification_listner);
 * m_logger.debug( i + ": Connect - server version : " + client.getVersion() );
 * if (client.checkCompatibility()) {
 *     client.getJsonData();
 * }
 * }
 * </pre>
 * @author ericpinet
 * <br> 2015-11-05
 */
public class CLApiClient {
	
	/**
	 * Init logger instance for this class
	 */
	private Logger m_logger = LogManager.getLogger(getClass().getName());
	
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
	 * @param _host Host name of the server ConnectLife.
	 * @param _port Port of the server ConnectLife.
	 * @param _listener Listner of the notification.
	 */
	public CLApiClient(String _host, int _port, NotificationListener _listener) {
	  m_channel = ManagedChannelBuilder.forAddress(_host, _port)
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
	 * Add home in environment. 
	 * 
	 * @param _label Label of the new home.
	 * @param _imageuid Image uid of the new home.
	 * @return Uid of the new home.
	 */
	public String addHome (String _label, String _imageuid) {
		AddHomeRequest request = AddHomeRequest.newBuilder()
											   .setLabel(_label)
											   .setImageuid(_imageuid)
											   .build();
		AddHomeResponse response = m_blockingStub.addHome(request);
		return response.getUid();
	}
	
	/**
	 * Update home in the environment.
	 * 
	 * @param _uid Uid of the environment to update.
	 * @param _label New label.
	 * @param _imageuid New image uid.
	 * @return Uid of the home updated.
	 */
	public String updateHome (String _uid, String _label, String _imageuid) {
		UpdateHomeRequest request = UpdateHomeRequest.newBuilder()
											 		 .setUid(_uid)
													 .setLabel(_label)
											 		 .setImageuid(_imageuid)
											 		 .build();
		UpdateHomeResponse response = m_blockingStub.updateHome(request);
		return response.getUid();
	}
	
	/**
	 * Delete home in the environment.
	 * 
	 * @param _uid Uid of the home to delete.
	 * @return Uid of the home deleted.
	 */
	public String deleteHome (String _uid) {
		DeleteHomeRequest request = DeleteHomeRequest.newBuilder()
											 		 .setUid(_uid)
											 		 .build();
		DeleteHomeResponse response = m_blockingStub.deleteHome(request);
		return response.getUid();
	}
	
	/**
	 * Add new zone in the environment.
	 * @param _uid Uid of the home where add the zone.
	 * @param _label Label of the new zone.
	 * @param _imageuid Image uid of the new zone.
	 * @return Uid of the new zone added.
	 */
	public String addZone (String _uid, String _label, String _imageuid) {
		AddZoneRequest request = AddZoneRequest.newBuilder()
											   .setUidHome(_uid)
											   .setLabel(_label)
											   .setImageuid(_imageuid)
											   .build();
		AddZoneResponse response = m_blockingStub.addZone(request);
		return response.getUid();
	}
	
	/**
	 * Update zone in the environment.
	 * 
	 * @param _uid Uid of the zone to update.
	 * @param _label New label of the zone.
	 * @param _imageuid New image uid of the zone.
	 * @return Uid of the updated zone.
	 */
	public String updateZone (String _uid, String _label, String _imageuid) {
		UpdateZoneRequest request = UpdateZoneRequest.newBuilder()
											 		 .setUid(_uid)
													 .setLabel(_label)
											 		 .setImageuid(_imageuid)
											 		 .build();
		UpdateZoneResponse response = m_blockingStub.updateZone(request);
		return response.getUid();
	}
	
	/**
	 * Delete zone from the environment.
	 * 
	 * @param _uid Uid of the zone to delete.
	 * @return Uid of the deleted zone.
	 */
	public String deleteZone (String _uid) {
		DeleteZoneRequest request = DeleteZoneRequest.newBuilder()
											 		 .setUid(_uid)
											 		 .build();
		DeleteZoneResponse response = m_blockingStub.deleteZone(request);
		return response.getUid();
	}
	
	/**
	 * Add new room in the environment.
	 * 
	 * @param _uid Uid of the zone where add room.
	 * @param _label Label of the new room.
	 * @param _imageuid Image uid of the new room.
	 * @return Uid of the added room.
	 */
	public String addRoom (String _uid, String _label, String _imageuid) {
		AddRoomRequest request = AddRoomRequest.newBuilder()
											   .setUidZone(_uid)
											   .setLabel(_label)
											   .setImageuid(_imageuid)
											   .build();
		AddRoomResponse response = m_blockingStub.addRoom(request);
		return response.getUid();
	}
	
	/**
	 * Update room of the environment.
	 * 
	 * @param _uid Uid  of the room to update.
	 * @param _label New lavel of the room.
	 * @param _imageuid New image uid of the room.
	 * @return Uid of the updated room.
	 */
	public String updateRoom (String _uid, String _label, String _imageuid) {
		UpdateRoomRequest request = UpdateRoomRequest.newBuilder()
											 		 .setUid(_uid)
													 .setLabel(_label)
											 		 .setImageuid(_imageuid)
											 		 .build();
		UpdateRoomResponse response = m_blockingStub.updateRoom(request);
		return response.getUid();
	}
	
	/**
	 * Delete room in the environment.
	 * 
	 * @param _uid Uid of the room the delete.
	 * @return Uid of the deleted room.
	 */
	public String deleteRoom (String _uid) {
		DeleteRoomRequest request = DeleteRoomRequest.newBuilder()
											 		 .setUid(_uid)
											 		 .build();
		DeleteRoomResponse response = m_blockingStub.deleteRoom(request);
		return response.getUid();
	}
	
	/**
	 * Add person in environment.
	 * 
	 * @param _firstname First name of the person.
	 * @param _lastname Last name of the person.
	 * @param _imageuid Image uid of the person.
	 * @return UID of the new person.
	 */
	public String addPerson(String _firstname, String _lastname, String _imageuid){
		AddPersonRequest request = AddPersonRequest.newBuilder()
												   .setFirstname(_firstname)
												   .setLastname(_lastname)
												   .setImageuid(_imageuid)
												   .build();
		AddPersonResponse response = m_blockingStub.addPerson(request);
		return response.getUid();
	}
	
	/**
	 * Update person in environment.
	 * @param _uid UID of the person.
	 * @param _firstname First name of the person. 
	 * @param _lastname Last name of the person.
	 * @param _imageuid Image uid of the person.
	 * @return UID of the person.
	 */
	public String updatePerson(String _uid, String _firstname, String _lastname, String _imageuid){
		UpdatePersonRequest request = UpdatePersonRequest.newBuilder()
												   .setUid(_uid)
												   .setFirstname(_firstname)
												   .setLastname(_lastname)
												   .setImageuid(_imageuid)
												   .build();
		UpdatePersonResponse response = m_blockingStub.updatePerson(request);
		return response.getUid();
	}
	
	/**
	 * Delete the person.
	 * @param _uid UID of the person.
	 * @return UID of the person.
	 */
	public String deletePerson(String _uid){
		DeletePersonRequest request = DeletePersonRequest.newBuilder()
												   .setUid(_uid)
												   .build();
		DeletePersonResponse response = m_blockingStub.deletePerson(request);
		return response.getUid();
	}
	
	/**
	 * Add email of the person.
	 * 
	 * @param _uid UID of the person.
	 * @param _email Email of the person.
	 * @param _type  Type d'email of the person.
	 * @return UID of the person.
	 */
	public String addEmail(String _uid, String _email, int _type){
		AddEmailRequest request = AddEmailRequest.newBuilder()
												 .setUidPerson(_uid)
												 .setEmail(_email)
												 .setType(_type)
												 .build();
		AddEmailResponse response = m_blockingStub.addEmail(request);
		return response.getUid();
	}
	
	/**
	 * Add a accessory in a room. 
	 * 
	 * @param _serial_number Serial number of the accessory.
	 * @param _room_uid Room uid where to add accessory.
	 * @return Uid of the added accessory.
	 */
	public String addAccessory(String _serial_number, String _room_uid) {
		AddAccessoryRequest request = AddAccessoryRequest.newBuilder()
														 .setSerialnumber(_serial_number)
														 .setUidRoom(_room_uid)
														 .build();
		AddAccessoryResponse response = m_blockingStub.addAccessory(request);
		return response.getUid();
	}
	
	/**
	 * Delete the accessory from the room. 
	 * 
	 * @param _uid Uid of the accessory to be delete.
	 * @return Uid of the delete accessory.
	 */
	public String deleteAccessory(String _uid) {
		DeleteAccessoryRequest request = DeleteAccessoryRequest.newBuilder()
															   .setUid(_uid)
															   .build();
		DeleteAccessoryResponse response = m_blockingStub.deleteAccessory(request);
		return response.getUid();
	}
	
	/**
	 * Update the email of the person.
	 * 
	 * @param _uid UID of the mail.
	 * @param _email Email to update.
	 * @param _type  Type d'email of the person.
	 * @return UID of the mail.
	 */
	public String updateEmail(String _uid, String _email, int _type){
		UpdateEmailRequest request = UpdateEmailRequest.newBuilder()
												 	   .setUid(_uid)
												 	   .setEmail(_email)
												 	   .setType(_type)
												 	   .build();
		UpdateEmailResponse response = m_blockingStub.updateEmail(request);
		return response.getUid();
	}
	
	/**
	 * Add asset (file) in the environment.
	 * 
	 * @param _label Label (filename) of the asset to add.
	 * @param _type Type of the asset (See AssetType)
	 * @param _mode Mode of the asset (See AssetMode)
	 * @param _data Binary data of the asset (file)
	 * @return String uid of the new asset.
	 */
	public String addAsset (String _label, int _type, int _mode, ByteString _data) {
		AddAssetRequest request = AddAssetRequest.newBuilder()
											     .setLabel(_label)
											     .setType(_type)
											     .setMode(_mode)
											     .setData(_data)
											     .build();
		AddAssetResponse response = m_blockingStub.addAsset(request);
		return response.getUid();
	}
	
	/**
	 * Update asset (file) in the environment.
	 * 
	 * @param _uid Uid of the environment to update.
	 * @param _label New label.
	 * @param _type New type. (See AssetType)
	 * @param _mode New mode. (See AssetMode)
	 * @param _data Binary data of the asset (file)
	 * @return Uid of the asset updated.
	 */
	public String updateAsset (String _uid, String _label, int _type, int _mode, ByteString _data) {
		UpdateAssetRequest request = UpdateAssetRequest.newBuilder()
													 .setUid(_uid)
												     .setLabel(_label)
												     .setType(_type)
												     .setMode(_mode)
												     .setData(_data)
												     .build();
		UpdateAssetResponse response = m_blockingStub.updateAsset(request);
		return response.getUid();
	}
	
	/**
	 * Delete asset in the environment.
	 * 
	 * @param _uid Uid of the asset to delete.
	 * @return Uid of the asset deleted.
	 */
	public String deleteAsset (String _uid) {
		DeleteAssetRequest request = DeleteAssetRequest.newBuilder()
											 		   .setUid(_uid)
											 		   .build();
		DeleteAssetResponse response = m_blockingStub.deleteAsset(request);
		return response.getUid();
	}
	
	/**
	 * Get url of the asset in the environment.
	 * 
	 * @param _uid Uid of the asset to get url.
	 * @return Url of the asset.
	 */
	public String getAssetUrl (String _uid) {
		GetAssetUrlRequest request = GetAssetUrlRequest.newBuilder()
											 		   .setUid(_uid)
											 		   .build();
		GetAssetUrlResponse response = m_blockingStub.getAssetUrl(request);
		return response.getUrl();
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
					m_logger.warn(e.getMessage());
				} catch (ExecutionException e) {
					m_logger.info(e.getMessage());
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
