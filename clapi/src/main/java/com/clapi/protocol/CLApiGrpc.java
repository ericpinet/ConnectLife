package com.clapi.protocol;

import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;

@javax.annotation.Generated("by gRPC proto compiler")
public class CLApiGrpc {

  private CLApiGrpc() {}

  public static final String SERVICE_NAME = "clapi.CLApi";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi
  public static final io.grpc.MethodDescriptor<com.clapi.protocol.GetVersionRequest,
      com.clapi.protocol.GetVersionResponse> METHOD_GET_VERSION =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "clapi.CLApi", "getVersion"),
          io.grpc.protobuf.ProtoUtils.marshaller(com.clapi.protocol.GetVersionRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(com.clapi.protocol.GetVersionResponse.getDefaultInstance()));
  @io.grpc.ExperimentalApi
  public static final io.grpc.MethodDescriptor<com.clapi.protocol.CheckCompatibilityRequest,
      com.clapi.protocol.CheckCompatibilityResponse> METHOD_CHECK_COMPATIBILITY =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "clapi.CLApi", "checkCompatibility"),
          io.grpc.protobuf.ProtoUtils.marshaller(com.clapi.protocol.CheckCompatibilityRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(com.clapi.protocol.CheckCompatibilityResponse.getDefaultInstance()));
  @io.grpc.ExperimentalApi
  public static final io.grpc.MethodDescriptor<com.clapi.protocol.WaitNotificationRequest,
      com.clapi.protocol.WaitNotificationResponse> METHOD_WAIT_NOTIFICATION =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "clapi.CLApi", "waitNotification"),
          io.grpc.protobuf.ProtoUtils.marshaller(com.clapi.protocol.WaitNotificationRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(com.clapi.protocol.WaitNotificationResponse.getDefaultInstance()));
  @io.grpc.ExperimentalApi
  public static final io.grpc.MethodDescriptor<com.clapi.protocol.GetJsonDataRequest,
      com.clapi.protocol.GetJsonDataResponse> METHOD_GET_JSON_DATA =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "clapi.CLApi", "getJsonData"),
          io.grpc.protobuf.ProtoUtils.marshaller(com.clapi.protocol.GetJsonDataRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(com.clapi.protocol.GetJsonDataResponse.getDefaultInstance()));
  @io.grpc.ExperimentalApi
  public static final io.grpc.MethodDescriptor<com.clapi.protocol.AddPersonRequest,
      com.clapi.protocol.AddPersonResponse> METHOD_ADD_PERSON =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "clapi.CLApi", "addPerson"),
          io.grpc.protobuf.ProtoUtils.marshaller(com.clapi.protocol.AddPersonRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(com.clapi.protocol.AddPersonResponse.getDefaultInstance()));
  @io.grpc.ExperimentalApi
  public static final io.grpc.MethodDescriptor<com.clapi.protocol.UpdatePersonRequest,
      com.clapi.protocol.UpdatePersonResponse> METHOD_UPDATE_PERSON =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "clapi.CLApi", "updatePerson"),
          io.grpc.protobuf.ProtoUtils.marshaller(com.clapi.protocol.UpdatePersonRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(com.clapi.protocol.UpdatePersonResponse.getDefaultInstance()));
  @io.grpc.ExperimentalApi
  public static final io.grpc.MethodDescriptor<com.clapi.protocol.DeletePersonRequest,
      com.clapi.protocol.DeletePersonResponse> METHOD_DELETE_PERSON =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "clapi.CLApi", "deletePerson"),
          io.grpc.protobuf.ProtoUtils.marshaller(com.clapi.protocol.DeletePersonRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(com.clapi.protocol.DeletePersonResponse.getDefaultInstance()));
  @io.grpc.ExperimentalApi
  public static final io.grpc.MethodDescriptor<com.clapi.protocol.AddEmailRequest,
      com.clapi.protocol.AddEmailResponse> METHOD_ADD_EMAIL =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "clapi.CLApi", "addEmail"),
          io.grpc.protobuf.ProtoUtils.marshaller(com.clapi.protocol.AddEmailRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(com.clapi.protocol.AddEmailResponse.getDefaultInstance()));
  @io.grpc.ExperimentalApi
  public static final io.grpc.MethodDescriptor<com.clapi.protocol.UpdateEmailRequest,
      com.clapi.protocol.UpdateEmailResponse> METHOD_UPDATE_EMAIL =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "clapi.CLApi", "updateEmail"),
          io.grpc.protobuf.ProtoUtils.marshaller(com.clapi.protocol.UpdateEmailRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(com.clapi.protocol.UpdateEmailResponse.getDefaultInstance()));
  @io.grpc.ExperimentalApi
  public static final io.grpc.MethodDescriptor<com.clapi.protocol.DeleteEmailRequest,
      com.clapi.protocol.DeleteEmailResponse> METHOD_DELETE_EMAIL =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "clapi.CLApi", "deleteEmail"),
          io.grpc.protobuf.ProtoUtils.marshaller(com.clapi.protocol.DeleteEmailRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(com.clapi.protocol.DeleteEmailResponse.getDefaultInstance()));
  @io.grpc.ExperimentalApi
  public static final io.grpc.MethodDescriptor<com.clapi.protocol.AddPhoneRequest,
      com.clapi.protocol.AddPhoneResponse> METHOD_ADD_PHONE =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "clapi.CLApi", "addPhone"),
          io.grpc.protobuf.ProtoUtils.marshaller(com.clapi.protocol.AddPhoneRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(com.clapi.protocol.AddPhoneResponse.getDefaultInstance()));
  @io.grpc.ExperimentalApi
  public static final io.grpc.MethodDescriptor<com.clapi.protocol.UpdatePhoneRequest,
      com.clapi.protocol.UpdatePhoneResponse> METHOD_UPDATE_PHONE =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "clapi.CLApi", "updatePhone"),
          io.grpc.protobuf.ProtoUtils.marshaller(com.clapi.protocol.UpdatePhoneRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(com.clapi.protocol.UpdatePhoneResponse.getDefaultInstance()));
  @io.grpc.ExperimentalApi
  public static final io.grpc.MethodDescriptor<com.clapi.protocol.DeletePhoneRequest,
      com.clapi.protocol.DeletePhoneResponse> METHOD_DELETE_PHONE =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "clapi.CLApi", "deletePhone"),
          io.grpc.protobuf.ProtoUtils.marshaller(com.clapi.protocol.DeletePhoneRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(com.clapi.protocol.DeletePhoneResponse.getDefaultInstance()));
  @io.grpc.ExperimentalApi
  public static final io.grpc.MethodDescriptor<com.clapi.protocol.AddAddressRequest,
      com.clapi.protocol.AddAddressResponse> METHOD_ADD_ADDRESS =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "clapi.CLApi", "addAddress"),
          io.grpc.protobuf.ProtoUtils.marshaller(com.clapi.protocol.AddAddressRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(com.clapi.protocol.AddAddressResponse.getDefaultInstance()));
  @io.grpc.ExperimentalApi
  public static final io.grpc.MethodDescriptor<com.clapi.protocol.UpdateAddressRequest,
      com.clapi.protocol.UpdateAddressResponse> METHOD_UPDATE_ADDRESS =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "clapi.CLApi", "updateAddress"),
          io.grpc.protobuf.ProtoUtils.marshaller(com.clapi.protocol.UpdateAddressRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(com.clapi.protocol.UpdateAddressResponse.getDefaultInstance()));
  @io.grpc.ExperimentalApi
  public static final io.grpc.MethodDescriptor<com.clapi.protocol.DeleteAddressRequest,
      com.clapi.protocol.DeleteAddressResponse> METHOD_DELETE_ADDRESS =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "clapi.CLApi", "deleteAddress"),
          io.grpc.protobuf.ProtoUtils.marshaller(com.clapi.protocol.DeleteAddressRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(com.clapi.protocol.DeleteAddressResponse.getDefaultInstance()));
  @io.grpc.ExperimentalApi
  public static final io.grpc.MethodDescriptor<com.clapi.protocol.AddAccessoryRequest,
      com.clapi.protocol.AddAccessoryResponse> METHOD_ADD_ACCESSORY =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "clapi.CLApi", "addAccessory"),
          io.grpc.protobuf.ProtoUtils.marshaller(com.clapi.protocol.AddAccessoryRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(com.clapi.protocol.AddAccessoryResponse.getDefaultInstance()));
  @io.grpc.ExperimentalApi
  public static final io.grpc.MethodDescriptor<com.clapi.protocol.DeleteAccessoryRequest,
      com.clapi.protocol.DeleteAccessoryResponse> METHOD_DELETE_ACCESSORY =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "clapi.CLApi", "deleteAccessory"),
          io.grpc.protobuf.ProtoUtils.marshaller(com.clapi.protocol.DeleteAccessoryRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(com.clapi.protocol.DeleteAccessoryResponse.getDefaultInstance()));

  public static CLApiStub newStub(io.grpc.Channel channel) {
    return new CLApiStub(channel);
  }

  public static CLApiBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new CLApiBlockingStub(channel);
  }

  public static CLApiFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new CLApiFutureStub(channel);
  }

  public static interface CLApi {

    public void getVersion(com.clapi.protocol.GetVersionRequest request,
        io.grpc.stub.StreamObserver<com.clapi.protocol.GetVersionResponse> responseObserver);

    public void checkCompatibility(com.clapi.protocol.CheckCompatibilityRequest request,
        io.grpc.stub.StreamObserver<com.clapi.protocol.CheckCompatibilityResponse> responseObserver);

    public void waitNotification(com.clapi.protocol.WaitNotificationRequest request,
        io.grpc.stub.StreamObserver<com.clapi.protocol.WaitNotificationResponse> responseObserver);

    public void getJsonData(com.clapi.protocol.GetJsonDataRequest request,
        io.grpc.stub.StreamObserver<com.clapi.protocol.GetJsonDataResponse> responseObserver);

    public void addPerson(com.clapi.protocol.AddPersonRequest request,
        io.grpc.stub.StreamObserver<com.clapi.protocol.AddPersonResponse> responseObserver);

    public void updatePerson(com.clapi.protocol.UpdatePersonRequest request,
        io.grpc.stub.StreamObserver<com.clapi.protocol.UpdatePersonResponse> responseObserver);

    public void deletePerson(com.clapi.protocol.DeletePersonRequest request,
        io.grpc.stub.StreamObserver<com.clapi.protocol.DeletePersonResponse> responseObserver);

    public void addEmail(com.clapi.protocol.AddEmailRequest request,
        io.grpc.stub.StreamObserver<com.clapi.protocol.AddEmailResponse> responseObserver);

    public void updateEmail(com.clapi.protocol.UpdateEmailRequest request,
        io.grpc.stub.StreamObserver<com.clapi.protocol.UpdateEmailResponse> responseObserver);

    public void deleteEmail(com.clapi.protocol.DeleteEmailRequest request,
        io.grpc.stub.StreamObserver<com.clapi.protocol.DeleteEmailResponse> responseObserver);

    public void addPhone(com.clapi.protocol.AddPhoneRequest request,
        io.grpc.stub.StreamObserver<com.clapi.protocol.AddPhoneResponse> responseObserver);

    public void updatePhone(com.clapi.protocol.UpdatePhoneRequest request,
        io.grpc.stub.StreamObserver<com.clapi.protocol.UpdatePhoneResponse> responseObserver);

    public void deletePhone(com.clapi.protocol.DeletePhoneRequest request,
        io.grpc.stub.StreamObserver<com.clapi.protocol.DeletePhoneResponse> responseObserver);

    public void addAddress(com.clapi.protocol.AddAddressRequest request,
        io.grpc.stub.StreamObserver<com.clapi.protocol.AddAddressResponse> responseObserver);

    public void updateAddress(com.clapi.protocol.UpdateAddressRequest request,
        io.grpc.stub.StreamObserver<com.clapi.protocol.UpdateAddressResponse> responseObserver);

    public void deleteAddress(com.clapi.protocol.DeleteAddressRequest request,
        io.grpc.stub.StreamObserver<com.clapi.protocol.DeleteAddressResponse> responseObserver);

    public void addAccessory(com.clapi.protocol.AddAccessoryRequest request,
        io.grpc.stub.StreamObserver<com.clapi.protocol.AddAccessoryResponse> responseObserver);

    public void deleteAccessory(com.clapi.protocol.DeleteAccessoryRequest request,
        io.grpc.stub.StreamObserver<com.clapi.protocol.DeleteAccessoryResponse> responseObserver);
  }

  public static interface CLApiBlockingClient {

    public com.clapi.protocol.GetVersionResponse getVersion(com.clapi.protocol.GetVersionRequest request);

    public com.clapi.protocol.CheckCompatibilityResponse checkCompatibility(com.clapi.protocol.CheckCompatibilityRequest request);

    public com.clapi.protocol.WaitNotificationResponse waitNotification(com.clapi.protocol.WaitNotificationRequest request);

    public com.clapi.protocol.GetJsonDataResponse getJsonData(com.clapi.protocol.GetJsonDataRequest request);

    public com.clapi.protocol.AddPersonResponse addPerson(com.clapi.protocol.AddPersonRequest request);

    public com.clapi.protocol.UpdatePersonResponse updatePerson(com.clapi.protocol.UpdatePersonRequest request);

    public com.clapi.protocol.DeletePersonResponse deletePerson(com.clapi.protocol.DeletePersonRequest request);

    public com.clapi.protocol.AddEmailResponse addEmail(com.clapi.protocol.AddEmailRequest request);

    public com.clapi.protocol.UpdateEmailResponse updateEmail(com.clapi.protocol.UpdateEmailRequest request);

    public com.clapi.protocol.DeleteEmailResponse deleteEmail(com.clapi.protocol.DeleteEmailRequest request);

    public com.clapi.protocol.AddPhoneResponse addPhone(com.clapi.protocol.AddPhoneRequest request);

    public com.clapi.protocol.UpdatePhoneResponse updatePhone(com.clapi.protocol.UpdatePhoneRequest request);

    public com.clapi.protocol.DeletePhoneResponse deletePhone(com.clapi.protocol.DeletePhoneRequest request);

    public com.clapi.protocol.AddAddressResponse addAddress(com.clapi.protocol.AddAddressRequest request);

    public com.clapi.protocol.UpdateAddressResponse updateAddress(com.clapi.protocol.UpdateAddressRequest request);

    public com.clapi.protocol.DeleteAddressResponse deleteAddress(com.clapi.protocol.DeleteAddressRequest request);

    public com.clapi.protocol.AddAccessoryResponse addAccessory(com.clapi.protocol.AddAccessoryRequest request);

    public com.clapi.protocol.DeleteAccessoryResponse deleteAccessory(com.clapi.protocol.DeleteAccessoryRequest request);
  }

  public static interface CLApiFutureClient {

    public com.google.common.util.concurrent.ListenableFuture<com.clapi.protocol.GetVersionResponse> getVersion(
        com.clapi.protocol.GetVersionRequest request);

    public com.google.common.util.concurrent.ListenableFuture<com.clapi.protocol.CheckCompatibilityResponse> checkCompatibility(
        com.clapi.protocol.CheckCompatibilityRequest request);

    public com.google.common.util.concurrent.ListenableFuture<com.clapi.protocol.WaitNotificationResponse> waitNotification(
        com.clapi.protocol.WaitNotificationRequest request);

    public com.google.common.util.concurrent.ListenableFuture<com.clapi.protocol.GetJsonDataResponse> getJsonData(
        com.clapi.protocol.GetJsonDataRequest request);

    public com.google.common.util.concurrent.ListenableFuture<com.clapi.protocol.AddPersonResponse> addPerson(
        com.clapi.protocol.AddPersonRequest request);

    public com.google.common.util.concurrent.ListenableFuture<com.clapi.protocol.UpdatePersonResponse> updatePerson(
        com.clapi.protocol.UpdatePersonRequest request);

    public com.google.common.util.concurrent.ListenableFuture<com.clapi.protocol.DeletePersonResponse> deletePerson(
        com.clapi.protocol.DeletePersonRequest request);

    public com.google.common.util.concurrent.ListenableFuture<com.clapi.protocol.AddEmailResponse> addEmail(
        com.clapi.protocol.AddEmailRequest request);

    public com.google.common.util.concurrent.ListenableFuture<com.clapi.protocol.UpdateEmailResponse> updateEmail(
        com.clapi.protocol.UpdateEmailRequest request);

    public com.google.common.util.concurrent.ListenableFuture<com.clapi.protocol.DeleteEmailResponse> deleteEmail(
        com.clapi.protocol.DeleteEmailRequest request);

    public com.google.common.util.concurrent.ListenableFuture<com.clapi.protocol.AddPhoneResponse> addPhone(
        com.clapi.protocol.AddPhoneRequest request);

    public com.google.common.util.concurrent.ListenableFuture<com.clapi.protocol.UpdatePhoneResponse> updatePhone(
        com.clapi.protocol.UpdatePhoneRequest request);

    public com.google.common.util.concurrent.ListenableFuture<com.clapi.protocol.DeletePhoneResponse> deletePhone(
        com.clapi.protocol.DeletePhoneRequest request);

    public com.google.common.util.concurrent.ListenableFuture<com.clapi.protocol.AddAddressResponse> addAddress(
        com.clapi.protocol.AddAddressRequest request);

    public com.google.common.util.concurrent.ListenableFuture<com.clapi.protocol.UpdateAddressResponse> updateAddress(
        com.clapi.protocol.UpdateAddressRequest request);

    public com.google.common.util.concurrent.ListenableFuture<com.clapi.protocol.DeleteAddressResponse> deleteAddress(
        com.clapi.protocol.DeleteAddressRequest request);

    public com.google.common.util.concurrent.ListenableFuture<com.clapi.protocol.AddAccessoryResponse> addAccessory(
        com.clapi.protocol.AddAccessoryRequest request);

    public com.google.common.util.concurrent.ListenableFuture<com.clapi.protocol.DeleteAccessoryResponse> deleteAccessory(
        com.clapi.protocol.DeleteAccessoryRequest request);
  }

  public static class CLApiStub extends io.grpc.stub.AbstractStub<CLApiStub>
      implements CLApi {
    private CLApiStub(io.grpc.Channel channel) {
      super(channel);
    }

    private CLApiStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CLApiStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new CLApiStub(channel, callOptions);
    }

    @java.lang.Override
    public void getVersion(com.clapi.protocol.GetVersionRequest request,
        io.grpc.stub.StreamObserver<com.clapi.protocol.GetVersionResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_GET_VERSION, getCallOptions()), request, responseObserver);
    }

    @java.lang.Override
    public void checkCompatibility(com.clapi.protocol.CheckCompatibilityRequest request,
        io.grpc.stub.StreamObserver<com.clapi.protocol.CheckCompatibilityResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_CHECK_COMPATIBILITY, getCallOptions()), request, responseObserver);
    }

    @java.lang.Override
    public void waitNotification(com.clapi.protocol.WaitNotificationRequest request,
        io.grpc.stub.StreamObserver<com.clapi.protocol.WaitNotificationResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_WAIT_NOTIFICATION, getCallOptions()), request, responseObserver);
    }

    @java.lang.Override
    public void getJsonData(com.clapi.protocol.GetJsonDataRequest request,
        io.grpc.stub.StreamObserver<com.clapi.protocol.GetJsonDataResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_GET_JSON_DATA, getCallOptions()), request, responseObserver);
    }

    @java.lang.Override
    public void addPerson(com.clapi.protocol.AddPersonRequest request,
        io.grpc.stub.StreamObserver<com.clapi.protocol.AddPersonResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_ADD_PERSON, getCallOptions()), request, responseObserver);
    }

    @java.lang.Override
    public void updatePerson(com.clapi.protocol.UpdatePersonRequest request,
        io.grpc.stub.StreamObserver<com.clapi.protocol.UpdatePersonResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_UPDATE_PERSON, getCallOptions()), request, responseObserver);
    }

    @java.lang.Override
    public void deletePerson(com.clapi.protocol.DeletePersonRequest request,
        io.grpc.stub.StreamObserver<com.clapi.protocol.DeletePersonResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_DELETE_PERSON, getCallOptions()), request, responseObserver);
    }

    @java.lang.Override
    public void addEmail(com.clapi.protocol.AddEmailRequest request,
        io.grpc.stub.StreamObserver<com.clapi.protocol.AddEmailResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_ADD_EMAIL, getCallOptions()), request, responseObserver);
    }

    @java.lang.Override
    public void updateEmail(com.clapi.protocol.UpdateEmailRequest request,
        io.grpc.stub.StreamObserver<com.clapi.protocol.UpdateEmailResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_UPDATE_EMAIL, getCallOptions()), request, responseObserver);
    }

    @java.lang.Override
    public void deleteEmail(com.clapi.protocol.DeleteEmailRequest request,
        io.grpc.stub.StreamObserver<com.clapi.protocol.DeleteEmailResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_DELETE_EMAIL, getCallOptions()), request, responseObserver);
    }

    @java.lang.Override
    public void addPhone(com.clapi.protocol.AddPhoneRequest request,
        io.grpc.stub.StreamObserver<com.clapi.protocol.AddPhoneResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_ADD_PHONE, getCallOptions()), request, responseObserver);
    }

    @java.lang.Override
    public void updatePhone(com.clapi.protocol.UpdatePhoneRequest request,
        io.grpc.stub.StreamObserver<com.clapi.protocol.UpdatePhoneResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_UPDATE_PHONE, getCallOptions()), request, responseObserver);
    }

    @java.lang.Override
    public void deletePhone(com.clapi.protocol.DeletePhoneRequest request,
        io.grpc.stub.StreamObserver<com.clapi.protocol.DeletePhoneResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_DELETE_PHONE, getCallOptions()), request, responseObserver);
    }

    @java.lang.Override
    public void addAddress(com.clapi.protocol.AddAddressRequest request,
        io.grpc.stub.StreamObserver<com.clapi.protocol.AddAddressResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_ADD_ADDRESS, getCallOptions()), request, responseObserver);
    }

    @java.lang.Override
    public void updateAddress(com.clapi.protocol.UpdateAddressRequest request,
        io.grpc.stub.StreamObserver<com.clapi.protocol.UpdateAddressResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_UPDATE_ADDRESS, getCallOptions()), request, responseObserver);
    }

    @java.lang.Override
    public void deleteAddress(com.clapi.protocol.DeleteAddressRequest request,
        io.grpc.stub.StreamObserver<com.clapi.protocol.DeleteAddressResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_DELETE_ADDRESS, getCallOptions()), request, responseObserver);
    }

    @java.lang.Override
    public void addAccessory(com.clapi.protocol.AddAccessoryRequest request,
        io.grpc.stub.StreamObserver<com.clapi.protocol.AddAccessoryResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_ADD_ACCESSORY, getCallOptions()), request, responseObserver);
    }

    @java.lang.Override
    public void deleteAccessory(com.clapi.protocol.DeleteAccessoryRequest request,
        io.grpc.stub.StreamObserver<com.clapi.protocol.DeleteAccessoryResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_DELETE_ACCESSORY, getCallOptions()), request, responseObserver);
    }
  }

  public static class CLApiBlockingStub extends io.grpc.stub.AbstractStub<CLApiBlockingStub>
      implements CLApiBlockingClient {
    private CLApiBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private CLApiBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CLApiBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new CLApiBlockingStub(channel, callOptions);
    }

    @java.lang.Override
    public com.clapi.protocol.GetVersionResponse getVersion(com.clapi.protocol.GetVersionRequest request) {
      return blockingUnaryCall(
          getChannel().newCall(METHOD_GET_VERSION, getCallOptions()), request);
    }

    @java.lang.Override
    public com.clapi.protocol.CheckCompatibilityResponse checkCompatibility(com.clapi.protocol.CheckCompatibilityRequest request) {
      return blockingUnaryCall(
          getChannel().newCall(METHOD_CHECK_COMPATIBILITY, getCallOptions()), request);
    }

    @java.lang.Override
    public com.clapi.protocol.WaitNotificationResponse waitNotification(com.clapi.protocol.WaitNotificationRequest request) {
      return blockingUnaryCall(
          getChannel().newCall(METHOD_WAIT_NOTIFICATION, getCallOptions()), request);
    }

    @java.lang.Override
    public com.clapi.protocol.GetJsonDataResponse getJsonData(com.clapi.protocol.GetJsonDataRequest request) {
      return blockingUnaryCall(
          getChannel().newCall(METHOD_GET_JSON_DATA, getCallOptions()), request);
    }

    @java.lang.Override
    public com.clapi.protocol.AddPersonResponse addPerson(com.clapi.protocol.AddPersonRequest request) {
      return blockingUnaryCall(
          getChannel().newCall(METHOD_ADD_PERSON, getCallOptions()), request);
    }

    @java.lang.Override
    public com.clapi.protocol.UpdatePersonResponse updatePerson(com.clapi.protocol.UpdatePersonRequest request) {
      return blockingUnaryCall(
          getChannel().newCall(METHOD_UPDATE_PERSON, getCallOptions()), request);
    }

    @java.lang.Override
    public com.clapi.protocol.DeletePersonResponse deletePerson(com.clapi.protocol.DeletePersonRequest request) {
      return blockingUnaryCall(
          getChannel().newCall(METHOD_DELETE_PERSON, getCallOptions()), request);
    }

    @java.lang.Override
    public com.clapi.protocol.AddEmailResponse addEmail(com.clapi.protocol.AddEmailRequest request) {
      return blockingUnaryCall(
          getChannel().newCall(METHOD_ADD_EMAIL, getCallOptions()), request);
    }

    @java.lang.Override
    public com.clapi.protocol.UpdateEmailResponse updateEmail(com.clapi.protocol.UpdateEmailRequest request) {
      return blockingUnaryCall(
          getChannel().newCall(METHOD_UPDATE_EMAIL, getCallOptions()), request);
    }

    @java.lang.Override
    public com.clapi.protocol.DeleteEmailResponse deleteEmail(com.clapi.protocol.DeleteEmailRequest request) {
      return blockingUnaryCall(
          getChannel().newCall(METHOD_DELETE_EMAIL, getCallOptions()), request);
    }

    @java.lang.Override
    public com.clapi.protocol.AddPhoneResponse addPhone(com.clapi.protocol.AddPhoneRequest request) {
      return blockingUnaryCall(
          getChannel().newCall(METHOD_ADD_PHONE, getCallOptions()), request);
    }

    @java.lang.Override
    public com.clapi.protocol.UpdatePhoneResponse updatePhone(com.clapi.protocol.UpdatePhoneRequest request) {
      return blockingUnaryCall(
          getChannel().newCall(METHOD_UPDATE_PHONE, getCallOptions()), request);
    }

    @java.lang.Override
    public com.clapi.protocol.DeletePhoneResponse deletePhone(com.clapi.protocol.DeletePhoneRequest request) {
      return blockingUnaryCall(
          getChannel().newCall(METHOD_DELETE_PHONE, getCallOptions()), request);
    }

    @java.lang.Override
    public com.clapi.protocol.AddAddressResponse addAddress(com.clapi.protocol.AddAddressRequest request) {
      return blockingUnaryCall(
          getChannel().newCall(METHOD_ADD_ADDRESS, getCallOptions()), request);
    }

    @java.lang.Override
    public com.clapi.protocol.UpdateAddressResponse updateAddress(com.clapi.protocol.UpdateAddressRequest request) {
      return blockingUnaryCall(
          getChannel().newCall(METHOD_UPDATE_ADDRESS, getCallOptions()), request);
    }

    @java.lang.Override
    public com.clapi.protocol.DeleteAddressResponse deleteAddress(com.clapi.protocol.DeleteAddressRequest request) {
      return blockingUnaryCall(
          getChannel().newCall(METHOD_DELETE_ADDRESS, getCallOptions()), request);
    }

    @java.lang.Override
    public com.clapi.protocol.AddAccessoryResponse addAccessory(com.clapi.protocol.AddAccessoryRequest request) {
      return blockingUnaryCall(
          getChannel().newCall(METHOD_ADD_ACCESSORY, getCallOptions()), request);
    }

    @java.lang.Override
    public com.clapi.protocol.DeleteAccessoryResponse deleteAccessory(com.clapi.protocol.DeleteAccessoryRequest request) {
      return blockingUnaryCall(
          getChannel().newCall(METHOD_DELETE_ACCESSORY, getCallOptions()), request);
    }
  }

  public static class CLApiFutureStub extends io.grpc.stub.AbstractStub<CLApiFutureStub>
      implements CLApiFutureClient {
    private CLApiFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private CLApiFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CLApiFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new CLApiFutureStub(channel, callOptions);
    }

    @java.lang.Override
    public com.google.common.util.concurrent.ListenableFuture<com.clapi.protocol.GetVersionResponse> getVersion(
        com.clapi.protocol.GetVersionRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_GET_VERSION, getCallOptions()), request);
    }

    @java.lang.Override
    public com.google.common.util.concurrent.ListenableFuture<com.clapi.protocol.CheckCompatibilityResponse> checkCompatibility(
        com.clapi.protocol.CheckCompatibilityRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_CHECK_COMPATIBILITY, getCallOptions()), request);
    }

    @java.lang.Override
    public com.google.common.util.concurrent.ListenableFuture<com.clapi.protocol.WaitNotificationResponse> waitNotification(
        com.clapi.protocol.WaitNotificationRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_WAIT_NOTIFICATION, getCallOptions()), request);
    }

    @java.lang.Override
    public com.google.common.util.concurrent.ListenableFuture<com.clapi.protocol.GetJsonDataResponse> getJsonData(
        com.clapi.protocol.GetJsonDataRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_GET_JSON_DATA, getCallOptions()), request);
    }

    @java.lang.Override
    public com.google.common.util.concurrent.ListenableFuture<com.clapi.protocol.AddPersonResponse> addPerson(
        com.clapi.protocol.AddPersonRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_ADD_PERSON, getCallOptions()), request);
    }

    @java.lang.Override
    public com.google.common.util.concurrent.ListenableFuture<com.clapi.protocol.UpdatePersonResponse> updatePerson(
        com.clapi.protocol.UpdatePersonRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_UPDATE_PERSON, getCallOptions()), request);
    }

    @java.lang.Override
    public com.google.common.util.concurrent.ListenableFuture<com.clapi.protocol.DeletePersonResponse> deletePerson(
        com.clapi.protocol.DeletePersonRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_DELETE_PERSON, getCallOptions()), request);
    }

    @java.lang.Override
    public com.google.common.util.concurrent.ListenableFuture<com.clapi.protocol.AddEmailResponse> addEmail(
        com.clapi.protocol.AddEmailRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_ADD_EMAIL, getCallOptions()), request);
    }

    @java.lang.Override
    public com.google.common.util.concurrent.ListenableFuture<com.clapi.protocol.UpdateEmailResponse> updateEmail(
        com.clapi.protocol.UpdateEmailRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_UPDATE_EMAIL, getCallOptions()), request);
    }

    @java.lang.Override
    public com.google.common.util.concurrent.ListenableFuture<com.clapi.protocol.DeleteEmailResponse> deleteEmail(
        com.clapi.protocol.DeleteEmailRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_DELETE_EMAIL, getCallOptions()), request);
    }

    @java.lang.Override
    public com.google.common.util.concurrent.ListenableFuture<com.clapi.protocol.AddPhoneResponse> addPhone(
        com.clapi.protocol.AddPhoneRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_ADD_PHONE, getCallOptions()), request);
    }

    @java.lang.Override
    public com.google.common.util.concurrent.ListenableFuture<com.clapi.protocol.UpdatePhoneResponse> updatePhone(
        com.clapi.protocol.UpdatePhoneRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_UPDATE_PHONE, getCallOptions()), request);
    }

    @java.lang.Override
    public com.google.common.util.concurrent.ListenableFuture<com.clapi.protocol.DeletePhoneResponse> deletePhone(
        com.clapi.protocol.DeletePhoneRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_DELETE_PHONE, getCallOptions()), request);
    }

    @java.lang.Override
    public com.google.common.util.concurrent.ListenableFuture<com.clapi.protocol.AddAddressResponse> addAddress(
        com.clapi.protocol.AddAddressRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_ADD_ADDRESS, getCallOptions()), request);
    }

    @java.lang.Override
    public com.google.common.util.concurrent.ListenableFuture<com.clapi.protocol.UpdateAddressResponse> updateAddress(
        com.clapi.protocol.UpdateAddressRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_UPDATE_ADDRESS, getCallOptions()), request);
    }

    @java.lang.Override
    public com.google.common.util.concurrent.ListenableFuture<com.clapi.protocol.DeleteAddressResponse> deleteAddress(
        com.clapi.protocol.DeleteAddressRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_DELETE_ADDRESS, getCallOptions()), request);
    }

    @java.lang.Override
    public com.google.common.util.concurrent.ListenableFuture<com.clapi.protocol.AddAccessoryResponse> addAccessory(
        com.clapi.protocol.AddAccessoryRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_ADD_ACCESSORY, getCallOptions()), request);
    }

    @java.lang.Override
    public com.google.common.util.concurrent.ListenableFuture<com.clapi.protocol.DeleteAccessoryResponse> deleteAccessory(
        com.clapi.protocol.DeleteAccessoryRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_DELETE_ACCESSORY, getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_VERSION = 0;
  private static final int METHODID_CHECK_COMPATIBILITY = 1;
  private static final int METHODID_WAIT_NOTIFICATION = 2;
  private static final int METHODID_GET_JSON_DATA = 3;
  private static final int METHODID_ADD_PERSON = 4;
  private static final int METHODID_UPDATE_PERSON = 5;
  private static final int METHODID_DELETE_PERSON = 6;
  private static final int METHODID_ADD_EMAIL = 7;
  private static final int METHODID_UPDATE_EMAIL = 8;
  private static final int METHODID_DELETE_EMAIL = 9;
  private static final int METHODID_ADD_PHONE = 10;
  private static final int METHODID_UPDATE_PHONE = 11;
  private static final int METHODID_DELETE_PHONE = 12;
  private static final int METHODID_ADD_ADDRESS = 13;
  private static final int METHODID_UPDATE_ADDRESS = 14;
  private static final int METHODID_DELETE_ADDRESS = 15;
  private static final int METHODID_ADD_ACCESSORY = 16;
  private static final int METHODID_DELETE_ACCESSORY = 17;

  private static class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final CLApi serviceImpl;
    private final int methodId;

    public MethodHandlers(CLApi serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_VERSION:
          serviceImpl.getVersion((com.clapi.protocol.GetVersionRequest) request,
              (io.grpc.stub.StreamObserver<com.clapi.protocol.GetVersionResponse>) responseObserver);
          break;
        case METHODID_CHECK_COMPATIBILITY:
          serviceImpl.checkCompatibility((com.clapi.protocol.CheckCompatibilityRequest) request,
              (io.grpc.stub.StreamObserver<com.clapi.protocol.CheckCompatibilityResponse>) responseObserver);
          break;
        case METHODID_WAIT_NOTIFICATION:
          serviceImpl.waitNotification((com.clapi.protocol.WaitNotificationRequest) request,
              (io.grpc.stub.StreamObserver<com.clapi.protocol.WaitNotificationResponse>) responseObserver);
          break;
        case METHODID_GET_JSON_DATA:
          serviceImpl.getJsonData((com.clapi.protocol.GetJsonDataRequest) request,
              (io.grpc.stub.StreamObserver<com.clapi.protocol.GetJsonDataResponse>) responseObserver);
          break;
        case METHODID_ADD_PERSON:
          serviceImpl.addPerson((com.clapi.protocol.AddPersonRequest) request,
              (io.grpc.stub.StreamObserver<com.clapi.protocol.AddPersonResponse>) responseObserver);
          break;
        case METHODID_UPDATE_PERSON:
          serviceImpl.updatePerson((com.clapi.protocol.UpdatePersonRequest) request,
              (io.grpc.stub.StreamObserver<com.clapi.protocol.UpdatePersonResponse>) responseObserver);
          break;
        case METHODID_DELETE_PERSON:
          serviceImpl.deletePerson((com.clapi.protocol.DeletePersonRequest) request,
              (io.grpc.stub.StreamObserver<com.clapi.protocol.DeletePersonResponse>) responseObserver);
          break;
        case METHODID_ADD_EMAIL:
          serviceImpl.addEmail((com.clapi.protocol.AddEmailRequest) request,
              (io.grpc.stub.StreamObserver<com.clapi.protocol.AddEmailResponse>) responseObserver);
          break;
        case METHODID_UPDATE_EMAIL:
          serviceImpl.updateEmail((com.clapi.protocol.UpdateEmailRequest) request,
              (io.grpc.stub.StreamObserver<com.clapi.protocol.UpdateEmailResponse>) responseObserver);
          break;
        case METHODID_DELETE_EMAIL:
          serviceImpl.deleteEmail((com.clapi.protocol.DeleteEmailRequest) request,
              (io.grpc.stub.StreamObserver<com.clapi.protocol.DeleteEmailResponse>) responseObserver);
          break;
        case METHODID_ADD_PHONE:
          serviceImpl.addPhone((com.clapi.protocol.AddPhoneRequest) request,
              (io.grpc.stub.StreamObserver<com.clapi.protocol.AddPhoneResponse>) responseObserver);
          break;
        case METHODID_UPDATE_PHONE:
          serviceImpl.updatePhone((com.clapi.protocol.UpdatePhoneRequest) request,
              (io.grpc.stub.StreamObserver<com.clapi.protocol.UpdatePhoneResponse>) responseObserver);
          break;
        case METHODID_DELETE_PHONE:
          serviceImpl.deletePhone((com.clapi.protocol.DeletePhoneRequest) request,
              (io.grpc.stub.StreamObserver<com.clapi.protocol.DeletePhoneResponse>) responseObserver);
          break;
        case METHODID_ADD_ADDRESS:
          serviceImpl.addAddress((com.clapi.protocol.AddAddressRequest) request,
              (io.grpc.stub.StreamObserver<com.clapi.protocol.AddAddressResponse>) responseObserver);
          break;
        case METHODID_UPDATE_ADDRESS:
          serviceImpl.updateAddress((com.clapi.protocol.UpdateAddressRequest) request,
              (io.grpc.stub.StreamObserver<com.clapi.protocol.UpdateAddressResponse>) responseObserver);
          break;
        case METHODID_DELETE_ADDRESS:
          serviceImpl.deleteAddress((com.clapi.protocol.DeleteAddressRequest) request,
              (io.grpc.stub.StreamObserver<com.clapi.protocol.DeleteAddressResponse>) responseObserver);
          break;
        case METHODID_ADD_ACCESSORY:
          serviceImpl.addAccessory((com.clapi.protocol.AddAccessoryRequest) request,
              (io.grpc.stub.StreamObserver<com.clapi.protocol.AddAccessoryResponse>) responseObserver);
          break;
        case METHODID_DELETE_ACCESSORY:
          serviceImpl.deleteAccessory((com.clapi.protocol.DeleteAccessoryRequest) request,
              (io.grpc.stub.StreamObserver<com.clapi.protocol.DeleteAccessoryResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  public static io.grpc.ServerServiceDefinition bindService(
      final CLApi serviceImpl) {
    return io.grpc.ServerServiceDefinition.builder(SERVICE_NAME)
        .addMethod(
          METHOD_GET_VERSION,
          asyncUnaryCall(
            new MethodHandlers<
              com.clapi.protocol.GetVersionRequest,
              com.clapi.protocol.GetVersionResponse>(
                serviceImpl, METHODID_GET_VERSION)))
        .addMethod(
          METHOD_CHECK_COMPATIBILITY,
          asyncUnaryCall(
            new MethodHandlers<
              com.clapi.protocol.CheckCompatibilityRequest,
              com.clapi.protocol.CheckCompatibilityResponse>(
                serviceImpl, METHODID_CHECK_COMPATIBILITY)))
        .addMethod(
          METHOD_WAIT_NOTIFICATION,
          asyncUnaryCall(
            new MethodHandlers<
              com.clapi.protocol.WaitNotificationRequest,
              com.clapi.protocol.WaitNotificationResponse>(
                serviceImpl, METHODID_WAIT_NOTIFICATION)))
        .addMethod(
          METHOD_GET_JSON_DATA,
          asyncUnaryCall(
            new MethodHandlers<
              com.clapi.protocol.GetJsonDataRequest,
              com.clapi.protocol.GetJsonDataResponse>(
                serviceImpl, METHODID_GET_JSON_DATA)))
        .addMethod(
          METHOD_ADD_PERSON,
          asyncUnaryCall(
            new MethodHandlers<
              com.clapi.protocol.AddPersonRequest,
              com.clapi.protocol.AddPersonResponse>(
                serviceImpl, METHODID_ADD_PERSON)))
        .addMethod(
          METHOD_UPDATE_PERSON,
          asyncUnaryCall(
            new MethodHandlers<
              com.clapi.protocol.UpdatePersonRequest,
              com.clapi.protocol.UpdatePersonResponse>(
                serviceImpl, METHODID_UPDATE_PERSON)))
        .addMethod(
          METHOD_DELETE_PERSON,
          asyncUnaryCall(
            new MethodHandlers<
              com.clapi.protocol.DeletePersonRequest,
              com.clapi.protocol.DeletePersonResponse>(
                serviceImpl, METHODID_DELETE_PERSON)))
        .addMethod(
          METHOD_ADD_EMAIL,
          asyncUnaryCall(
            new MethodHandlers<
              com.clapi.protocol.AddEmailRequest,
              com.clapi.protocol.AddEmailResponse>(
                serviceImpl, METHODID_ADD_EMAIL)))
        .addMethod(
          METHOD_UPDATE_EMAIL,
          asyncUnaryCall(
            new MethodHandlers<
              com.clapi.protocol.UpdateEmailRequest,
              com.clapi.protocol.UpdateEmailResponse>(
                serviceImpl, METHODID_UPDATE_EMAIL)))
        .addMethod(
          METHOD_DELETE_EMAIL,
          asyncUnaryCall(
            new MethodHandlers<
              com.clapi.protocol.DeleteEmailRequest,
              com.clapi.protocol.DeleteEmailResponse>(
                serviceImpl, METHODID_DELETE_EMAIL)))
        .addMethod(
          METHOD_ADD_PHONE,
          asyncUnaryCall(
            new MethodHandlers<
              com.clapi.protocol.AddPhoneRequest,
              com.clapi.protocol.AddPhoneResponse>(
                serviceImpl, METHODID_ADD_PHONE)))
        .addMethod(
          METHOD_UPDATE_PHONE,
          asyncUnaryCall(
            new MethodHandlers<
              com.clapi.protocol.UpdatePhoneRequest,
              com.clapi.protocol.UpdatePhoneResponse>(
                serviceImpl, METHODID_UPDATE_PHONE)))
        .addMethod(
          METHOD_DELETE_PHONE,
          asyncUnaryCall(
            new MethodHandlers<
              com.clapi.protocol.DeletePhoneRequest,
              com.clapi.protocol.DeletePhoneResponse>(
                serviceImpl, METHODID_DELETE_PHONE)))
        .addMethod(
          METHOD_ADD_ADDRESS,
          asyncUnaryCall(
            new MethodHandlers<
              com.clapi.protocol.AddAddressRequest,
              com.clapi.protocol.AddAddressResponse>(
                serviceImpl, METHODID_ADD_ADDRESS)))
        .addMethod(
          METHOD_UPDATE_ADDRESS,
          asyncUnaryCall(
            new MethodHandlers<
              com.clapi.protocol.UpdateAddressRequest,
              com.clapi.protocol.UpdateAddressResponse>(
                serviceImpl, METHODID_UPDATE_ADDRESS)))
        .addMethod(
          METHOD_DELETE_ADDRESS,
          asyncUnaryCall(
            new MethodHandlers<
              com.clapi.protocol.DeleteAddressRequest,
              com.clapi.protocol.DeleteAddressResponse>(
                serviceImpl, METHODID_DELETE_ADDRESS)))
        .addMethod(
          METHOD_ADD_ACCESSORY,
          asyncUnaryCall(
            new MethodHandlers<
              com.clapi.protocol.AddAccessoryRequest,
              com.clapi.protocol.AddAccessoryResponse>(
                serviceImpl, METHODID_ADD_ACCESSORY)))
        .addMethod(
          METHOD_DELETE_ACCESSORY,
          asyncUnaryCall(
            new MethodHandlers<
              com.clapi.protocol.DeleteAccessoryRequest,
              com.clapi.protocol.DeleteAccessoryResponse>(
                serviceImpl, METHODID_DELETE_ACCESSORY)))
        .build();
  }
}
