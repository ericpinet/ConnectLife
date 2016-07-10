/**
 *  CLApiGrpcMock.java
 *  coreserver
 *
 *  Created by ericpinet on 2016-01-22.
 *  Copyright (c) 2016 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.connectlife.test.coreserver.apiserver;

import com.clapi.protocol.AddAccessoryRequest;
import com.clapi.protocol.AddAccessoryResponse;
import com.clapi.protocol.AddAddressRequest;
import com.clapi.protocol.AddAddressResponse;
import com.clapi.protocol.AddAssertRequest;
import com.clapi.protocol.AddAssertResponse;
import com.clapi.protocol.AddEmailRequest;
import com.clapi.protocol.AddEmailResponse;
import com.clapi.protocol.AddHomeRequest;
import com.clapi.protocol.AddHomeResponse;
import com.clapi.protocol.AddPersonRequest;
import com.clapi.protocol.AddPersonResponse;
import com.clapi.protocol.AddPhoneRequest;
import com.clapi.protocol.AddPhoneResponse;
import com.clapi.protocol.AddRoomRequest;
import com.clapi.protocol.AddRoomResponse;
import com.clapi.protocol.AddZoneRequest;
import com.clapi.protocol.AddZoneResponse;
import com.clapi.protocol.CLApiGrpc;
import com.clapi.protocol.CheckCompatibilityRequest;
import com.clapi.protocol.CheckCompatibilityResponse;
import com.clapi.protocol.DeleteAccessoryRequest;
import com.clapi.protocol.DeleteAccessoryResponse;
import com.clapi.protocol.DeleteAddressRequest;
import com.clapi.protocol.DeleteAddressResponse;
import com.clapi.protocol.DeleteAssertRequest;
import com.clapi.protocol.DeleteAssertResponse;
import com.clapi.protocol.DeleteEmailRequest;
import com.clapi.protocol.DeleteEmailResponse;
import com.clapi.protocol.DeleteHomeRequest;
import com.clapi.protocol.DeleteHomeResponse;
import com.clapi.protocol.DeletePersonRequest;
import com.clapi.protocol.DeletePersonResponse;
import com.clapi.protocol.DeletePhoneRequest;
import com.clapi.protocol.DeletePhoneResponse;
import com.clapi.protocol.DeleteRoomRequest;
import com.clapi.protocol.DeleteRoomResponse;
import com.clapi.protocol.DeleteZoneRequest;
import com.clapi.protocol.DeleteZoneResponse;
import com.clapi.protocol.GetAssertUrlRequest;
import com.clapi.protocol.GetAssertUrlResponse;
import com.clapi.protocol.GetJsonDataRequest;
import com.clapi.protocol.GetJsonDataResponse;
import com.clapi.protocol.GetVersionRequest;
import com.clapi.protocol.GetVersionResponse;
import com.clapi.protocol.UpdateAddressRequest;
import com.clapi.protocol.UpdateAddressResponse;
import com.clapi.protocol.UpdateAssertRequest;
import com.clapi.protocol.UpdateAssertResponse;
import com.clapi.protocol.UpdateEmailRequest;
import com.clapi.protocol.UpdateEmailResponse;
import com.clapi.protocol.UpdateHomeRequest;
import com.clapi.protocol.UpdateHomeResponse;
import com.clapi.protocol.UpdatePersonRequest;
import com.clapi.protocol.UpdatePersonResponse;
import com.clapi.protocol.UpdatePhoneRequest;
import com.clapi.protocol.UpdatePhoneResponse;
import com.clapi.protocol.UpdateRoomRequest;
import com.clapi.protocol.UpdateRoomResponse;
import com.clapi.protocol.UpdateZoneRequest;
import com.clapi.protocol.UpdateZoneResponse;
import com.clapi.protocol.WaitNotificationRequest;
import com.clapi.protocol.WaitNotificationResponse;

import io.grpc.stub.StreamObserver;

/**
 * 
 * 
 * @author ericpinet
 * <br> 2016-01-22
 */
public class CLApiGrpcMock implements CLApiGrpc.CLApi {

	/**
	 * @param request
	 * @param responseObserver
	 * @see com.clapi.protocol.CLApiGrpc.CLApi#getVersion(com.clapi.protocol.GetVersionRequest, io.grpc.stub.StreamObserver)
	 */
	@Override
	public void getVersion(GetVersionRequest request, StreamObserver<GetVersionResponse> responseObserver) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @param request
	 * @param responseObserver
	 * @see com.clapi.protocol.CLApiGrpc.CLApi#checkCompatibility(com.clapi.protocol.CheckCompatibilityRequest, io.grpc.stub.StreamObserver)
	 */
	@Override
	public void checkCompatibility(CheckCompatibilityRequest request,
			StreamObserver<CheckCompatibilityResponse> responseObserver) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @param request
	 * @param responseObserver
	 * @see com.clapi.protocol.CLApiGrpc.CLApi#waitNotification(com.clapi.protocol.WaitNotificationRequest, io.grpc.stub.StreamObserver)
	 */
	@Override
	public void waitNotification(WaitNotificationRequest request,
			StreamObserver<WaitNotificationResponse> responseObserver) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @param request
	 * @param responseObserver
	 * @see com.clapi.protocol.CLApiGrpc.CLApi#getJsonData(com.clapi.protocol.GetJsonDataRequest, io.grpc.stub.StreamObserver)
	 */
	@Override
	public void getJsonData(GetJsonDataRequest request, StreamObserver<GetJsonDataResponse> responseObserver) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @param request
	 * @param responseObserver
	 * @see com.clapi.protocol.CLApiGrpc.CLApi#addPerson(com.clapi.protocol.AddPersonRequest, io.grpc.stub.StreamObserver)
	 */
	@Override
	public void addPerson(AddPersonRequest request, StreamObserver<AddPersonResponse> responseObserver) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @param request
	 * @param responseObserver
	 * @see com.clapi.protocol.CLApiGrpc.CLApi#updatePerson(com.clapi.protocol.UpdatePersonRequest, io.grpc.stub.StreamObserver)
	 */
	@Override
	public void updatePerson(UpdatePersonRequest request, StreamObserver<UpdatePersonResponse> responseObserver) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @param request
	 * @param responseObserver
	 * @see com.clapi.protocol.CLApiGrpc.CLApi#deletePerson(com.clapi.protocol.DeletePersonRequest, io.grpc.stub.StreamObserver)
	 */
	@Override
	public void deletePerson(DeletePersonRequest request, StreamObserver<DeletePersonResponse> responseObserver) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @param request
	 * @param responseObserver
	 * @see com.clapi.protocol.CLApiGrpc.CLApi#addEmail(com.clapi.protocol.AddEmailRequest, io.grpc.stub.StreamObserver)
	 */
	@Override
	public void addEmail(AddEmailRequest request, StreamObserver<AddEmailResponse> responseObserver) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @param request
	 * @param responseObserver
	 * @see com.clapi.protocol.CLApiGrpc.CLApi#updateEmail(com.clapi.protocol.UpdateEmailRequest, io.grpc.stub.StreamObserver)
	 */
	@Override
	public void updateEmail(UpdateEmailRequest request, StreamObserver<UpdateEmailResponse> responseObserver) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @param request
	 * @param responseObserver
	 * @see com.clapi.protocol.CLApiGrpc.CLApi#deleteEmail(com.clapi.protocol.DeleteEmailRequest, io.grpc.stub.StreamObserver)
	 */
	@Override
	public void deleteEmail(DeleteEmailRequest request, StreamObserver<DeleteEmailResponse> responseObserver) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @param request
	 * @param responseObserver
	 * @see com.clapi.protocol.CLApiGrpc.CLApi#addPhone(com.clapi.protocol.AddPhoneRequest, io.grpc.stub.StreamObserver)
	 */
	@Override
	public void addPhone(AddPhoneRequest request, StreamObserver<AddPhoneResponse> responseObserver) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @param request
	 * @param responseObserver
	 * @see com.clapi.protocol.CLApiGrpc.CLApi#updatePhone(com.clapi.protocol.UpdatePhoneRequest, io.grpc.stub.StreamObserver)
	 */
	@Override
	public void updatePhone(UpdatePhoneRequest request, StreamObserver<UpdatePhoneResponse> responseObserver) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @param request
	 * @param responseObserver
	 * @see com.clapi.protocol.CLApiGrpc.CLApi#deletePhone(com.clapi.protocol.DeletePhoneRequest, io.grpc.stub.StreamObserver)
	 */
	@Override
	public void deletePhone(DeletePhoneRequest request, StreamObserver<DeletePhoneResponse> responseObserver) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @param request
	 * @param responseObserver
	 * @see com.clapi.protocol.CLApiGrpc.CLApi#addAddress(com.clapi.protocol.AddAddressRequest, io.grpc.stub.StreamObserver)
	 */
	@Override
	public void addAddress(AddAddressRequest request, StreamObserver<AddAddressResponse> responseObserver) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @param request
	 * @param responseObserver
	 * @see com.clapi.protocol.CLApiGrpc.CLApi#updateAddress(com.clapi.protocol.UpdateAddressRequest, io.grpc.stub.StreamObserver)
	 */
	@Override
	public void updateAddress(UpdateAddressRequest request, StreamObserver<UpdateAddressResponse> responseObserver) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @param request
	 * @param responseObserver
	 * @see com.clapi.protocol.CLApiGrpc.CLApi#deleteAddress(com.clapi.protocol.DeleteAddressRequest, io.grpc.stub.StreamObserver)
	 */
	@Override
	public void deleteAddress(DeleteAddressRequest request, StreamObserver<DeleteAddressResponse> responseObserver) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @param request
	 * @param responseObserver
	 * @see com.clapi.protocol.CLApiGrpc.CLApi#addAccessory(com.clapi.protocol.AddAccessoryRequest, io.grpc.stub.StreamObserver)
	 */
	@Override
	public void addAccessory(AddAccessoryRequest request, StreamObserver<AddAccessoryResponse> responseObserver) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @param request
	 * @param responseObserver
	 * @see com.clapi.protocol.CLApiGrpc.CLApi#deleteAccessory(com.clapi.protocol.DeleteAccessoryRequest, io.grpc.stub.StreamObserver)
	 */
	@Override
	public void deleteAccessory(DeleteAccessoryRequest request,
			StreamObserver<DeleteAccessoryResponse> responseObserver) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @param request
	 * @param responseObserver
	 * @see com.clapi.protocol.CLApiGrpc.CLApi#addHome(com.clapi.protocol.AddHomeRequest, io.grpc.stub.StreamObserver)
	 */
	@Override
	public void addHome(AddHomeRequest request, StreamObserver<AddHomeResponse> responseObserver) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @param request
	 * @param responseObserver
	 * @see com.clapi.protocol.CLApiGrpc.CLApi#updateHome(com.clapi.protocol.UpdateHomeRequest, io.grpc.stub.StreamObserver)
	 */
	@Override
	public void updateHome(UpdateHomeRequest request, StreamObserver<UpdateHomeResponse> responseObserver) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @param request
	 * @param responseObserver
	 * @see com.clapi.protocol.CLApiGrpc.CLApi#deleteHome(com.clapi.protocol.DeleteHomeRequest, io.grpc.stub.StreamObserver)
	 */
	@Override
	public void deleteHome(DeleteHomeRequest request, StreamObserver<DeleteHomeResponse> responseObserver) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @param request
	 * @param responseObserver
	 * @see com.clapi.protocol.CLApiGrpc.CLApi#addZone(com.clapi.protocol.AddZoneRequest, io.grpc.stub.StreamObserver)
	 */
	@Override
	public void addZone(AddZoneRequest request, StreamObserver<AddZoneResponse> responseObserver) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @param request
	 * @param responseObserver
	 * @see com.clapi.protocol.CLApiGrpc.CLApi#updateZone(com.clapi.protocol.UpdateZoneRequest, io.grpc.stub.StreamObserver)
	 */
	@Override
	public void updateZone(UpdateZoneRequest request, StreamObserver<UpdateZoneResponse> responseObserver) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @param request
	 * @param responseObserver
	 * @see com.clapi.protocol.CLApiGrpc.CLApi#deleteZone(com.clapi.protocol.DeleteZoneRequest, io.grpc.stub.StreamObserver)
	 */
	@Override
	public void deleteZone(DeleteZoneRequest request, StreamObserver<DeleteZoneResponse> responseObserver) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @param request
	 * @param responseObserver
	 * @see com.clapi.protocol.CLApiGrpc.CLApi#addRoom(com.clapi.protocol.AddRoomRequest, io.grpc.stub.StreamObserver)
	 */
	@Override
	public void addRoom(AddRoomRequest request, StreamObserver<AddRoomResponse> responseObserver) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @param request
	 * @param responseObserver
	 * @see com.clapi.protocol.CLApiGrpc.CLApi#updateRoom(com.clapi.protocol.UpdateRoomRequest, io.grpc.stub.StreamObserver)
	 */
	@Override
	public void updateRoom(UpdateRoomRequest request, StreamObserver<UpdateRoomResponse> responseObserver) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @param request
	 * @param responseObserver
	 * @see com.clapi.protocol.CLApiGrpc.CLApi#deleteRoom(com.clapi.protocol.DeleteRoomRequest, io.grpc.stub.StreamObserver)
	 */
	@Override
	public void deleteRoom(DeleteRoomRequest request, StreamObserver<DeleteRoomResponse> responseObserver) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @param request
	 * @param responseObserver
	 * @see com.clapi.protocol.CLApiGrpc.CLApi#addAssert(com.clapi.protocol.AddAssertRequest, io.grpc.stub.StreamObserver)
	 */
	@Override
	public void addAssert(AddAssertRequest request, StreamObserver<AddAssertResponse> responseObserver) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @param request
	 * @param responseObserver
	 * @see com.clapi.protocol.CLApiGrpc.CLApi#updateAssert(com.clapi.protocol.UpdateAssertRequest, io.grpc.stub.StreamObserver)
	 */
	@Override
	public void updateAssert(UpdateAssertRequest request, StreamObserver<UpdateAssertResponse> responseObserver) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @param request
	 * @param responseObserver
	 * @see com.clapi.protocol.CLApiGrpc.CLApi#deleteAssert(com.clapi.protocol.DeleteAssertRequest, io.grpc.stub.StreamObserver)
	 */
	@Override
	public void deleteAssert(DeleteAssertRequest request, StreamObserver<DeleteAssertResponse> responseObserver) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @param request
	 * @param responseObserver
	 * @see com.clapi.protocol.CLApiGrpc.CLApi#getAssertUrl(com.clapi.protocol.GetAssertUrlRequest, io.grpc.stub.StreamObserver)
	 */
	@Override
	public void getAssertUrl(GetAssertUrlRequest request, StreamObserver<GetAssertUrlResponse> responseObserver) {
		// TODO Auto-generated method stub
		
	}

}
