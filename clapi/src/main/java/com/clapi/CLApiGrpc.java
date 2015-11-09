package com.clapi;

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
  public static final io.grpc.MethodDescriptor<com.clapi.GetVersionRequest,
      com.clapi.GetVersionResponse> METHOD_GET_VERSION =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "clapi.CLApi", "getVersion"),
          io.grpc.protobuf.ProtoUtils.marshaller(com.clapi.GetVersionRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(com.clapi.GetVersionResponse.getDefaultInstance()));
  @io.grpc.ExperimentalApi
  public static final io.grpc.MethodDescriptor<com.clapi.CheckCompatibilityRequest,
      com.clapi.CheckCompatibilityResponse> METHOD_CHECK_COMPATIBILITY =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "clapi.CLApi", "checkCompatibility"),
          io.grpc.protobuf.ProtoUtils.marshaller(com.clapi.CheckCompatibilityRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(com.clapi.CheckCompatibilityResponse.getDefaultInstance()));
  @io.grpc.ExperimentalApi
  public static final io.grpc.MethodDescriptor<com.clapi.WaitNotificationRequest,
      com.clapi.WaitNotificationResponse> METHOD_WAIT_NOTIFICATION =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "clapi.CLApi", "waitNotification"),
          io.grpc.protobuf.ProtoUtils.marshaller(com.clapi.WaitNotificationRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(com.clapi.WaitNotificationResponse.getDefaultInstance()));
  @io.grpc.ExperimentalApi
  public static final io.grpc.MethodDescriptor<com.clapi.GetJsonDataRequest,
      com.clapi.GetJsonDataResponse> METHOD_GET_JSON_DATA =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "clapi.CLApi", "getJsonData"),
          io.grpc.protobuf.ProtoUtils.marshaller(com.clapi.GetJsonDataRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(com.clapi.GetJsonDataResponse.getDefaultInstance()));
  @io.grpc.ExperimentalApi
  public static final io.grpc.MethodDescriptor<com.clapi.AddPersonRequest,
      com.clapi.AddPersonResponse> METHOD_ADD_PERSON =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "clapi.CLApi", "addPerson"),
          io.grpc.protobuf.ProtoUtils.marshaller(com.clapi.AddPersonRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(com.clapi.AddPersonResponse.getDefaultInstance()));
  @io.grpc.ExperimentalApi
  public static final io.grpc.MethodDescriptor<com.clapi.UpdatePersonRequest,
      com.clapi.UpdatePersonResponse> METHOD_UPDATE_PERSON =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "clapi.CLApi", "updatePerson"),
          io.grpc.protobuf.ProtoUtils.marshaller(com.clapi.UpdatePersonRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(com.clapi.UpdatePersonResponse.getDefaultInstance()));
  @io.grpc.ExperimentalApi
  public static final io.grpc.MethodDescriptor<com.clapi.DeletePersonRequest,
      com.clapi.DeletePersonResponse> METHOD_DELETE_PERSON =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "clapi.CLApi", "deletePerson"),
          io.grpc.protobuf.ProtoUtils.marshaller(com.clapi.DeletePersonRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(com.clapi.DeletePersonResponse.getDefaultInstance()));
  @io.grpc.ExperimentalApi
  public static final io.grpc.MethodDescriptor<com.clapi.AddEmailRequest,
      com.clapi.AddEmailResponse> METHOD_ADD_EMAIL =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "clapi.CLApi", "addEmail"),
          io.grpc.protobuf.ProtoUtils.marshaller(com.clapi.AddEmailRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(com.clapi.AddEmailResponse.getDefaultInstance()));
  @io.grpc.ExperimentalApi
  public static final io.grpc.MethodDescriptor<com.clapi.UpdateEmailRequest,
      com.clapi.UpdateEmailResponse> METHOD_UPDATE_EMAIL =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "clapi.CLApi", "updateEmail"),
          io.grpc.protobuf.ProtoUtils.marshaller(com.clapi.UpdateEmailRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(com.clapi.UpdateEmailResponse.getDefaultInstance()));
  @io.grpc.ExperimentalApi
  public static final io.grpc.MethodDescriptor<com.clapi.DeleteEmailRequest,
      com.clapi.DeleteEmailResponse> METHOD_DELETE_EMAIL =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "clapi.CLApi", "deleteEmail"),
          io.grpc.protobuf.ProtoUtils.marshaller(com.clapi.DeleteEmailRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(com.clapi.DeleteEmailResponse.getDefaultInstance()));
  @io.grpc.ExperimentalApi
  public static final io.grpc.MethodDescriptor<com.clapi.AddPhoneRequest,
      com.clapi.AddPhoneResponse> METHOD_ADD_PHONE =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "clapi.CLApi", "addPhone"),
          io.grpc.protobuf.ProtoUtils.marshaller(com.clapi.AddPhoneRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(com.clapi.AddPhoneResponse.getDefaultInstance()));
  @io.grpc.ExperimentalApi
  public static final io.grpc.MethodDescriptor<com.clapi.UpdatePhoneRequest,
      com.clapi.UpdatePhoneResponse> METHOD_UPDATE_PHONE =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "clapi.CLApi", "updatePhone"),
          io.grpc.protobuf.ProtoUtils.marshaller(com.clapi.UpdatePhoneRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(com.clapi.UpdatePhoneResponse.getDefaultInstance()));
  @io.grpc.ExperimentalApi
  public static final io.grpc.MethodDescriptor<com.clapi.DeletePhoneRequest,
      com.clapi.DeletePhoneResponse> METHOD_DELETE_PHONE =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "clapi.CLApi", "deletePhone"),
          io.grpc.protobuf.ProtoUtils.marshaller(com.clapi.DeletePhoneRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(com.clapi.DeletePhoneResponse.getDefaultInstance()));
  @io.grpc.ExperimentalApi
  public static final io.grpc.MethodDescriptor<com.clapi.AddAddressRequest,
      com.clapi.AddAddressResponse> METHOD_ADD_ADDRESS =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "clapi.CLApi", "addAddress"),
          io.grpc.protobuf.ProtoUtils.marshaller(com.clapi.AddAddressRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(com.clapi.AddAddressResponse.getDefaultInstance()));
  @io.grpc.ExperimentalApi
  public static final io.grpc.MethodDescriptor<com.clapi.UpdateAddressRequest,
      com.clapi.UpdateAddressResponse> METHOD_UPDATE_ADDRESS =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "clapi.CLApi", "updateAddress"),
          io.grpc.protobuf.ProtoUtils.marshaller(com.clapi.UpdateAddressRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(com.clapi.UpdateAddressResponse.getDefaultInstance()));
  @io.grpc.ExperimentalApi
  public static final io.grpc.MethodDescriptor<com.clapi.DeleteAddressRequest,
      com.clapi.DeleteAddressResponse> METHOD_DELETE_ADDRESS =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "clapi.CLApi", "deleteAddress"),
          io.grpc.protobuf.ProtoUtils.marshaller(com.clapi.DeleteAddressRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(com.clapi.DeleteAddressResponse.getDefaultInstance()));

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

    public void getVersion(com.clapi.GetVersionRequest request,
        io.grpc.stub.StreamObserver<com.clapi.GetVersionResponse> responseObserver);

    public void checkCompatibility(com.clapi.CheckCompatibilityRequest request,
        io.grpc.stub.StreamObserver<com.clapi.CheckCompatibilityResponse> responseObserver);

    public void waitNotification(com.clapi.WaitNotificationRequest request,
        io.grpc.stub.StreamObserver<com.clapi.WaitNotificationResponse> responseObserver);

    public void getJsonData(com.clapi.GetJsonDataRequest request,
        io.grpc.stub.StreamObserver<com.clapi.GetJsonDataResponse> responseObserver);

    public void addPerson(com.clapi.AddPersonRequest request,
        io.grpc.stub.StreamObserver<com.clapi.AddPersonResponse> responseObserver);

    public void updatePerson(com.clapi.UpdatePersonRequest request,
        io.grpc.stub.StreamObserver<com.clapi.UpdatePersonResponse> responseObserver);

    public void deletePerson(com.clapi.DeletePersonRequest request,
        io.grpc.stub.StreamObserver<com.clapi.DeletePersonResponse> responseObserver);

    public void addEmail(com.clapi.AddEmailRequest request,
        io.grpc.stub.StreamObserver<com.clapi.AddEmailResponse> responseObserver);

    public void updateEmail(com.clapi.UpdateEmailRequest request,
        io.grpc.stub.StreamObserver<com.clapi.UpdateEmailResponse> responseObserver);

    public void deleteEmail(com.clapi.DeleteEmailRequest request,
        io.grpc.stub.StreamObserver<com.clapi.DeleteEmailResponse> responseObserver);

    public void addPhone(com.clapi.AddPhoneRequest request,
        io.grpc.stub.StreamObserver<com.clapi.AddPhoneResponse> responseObserver);

    public void updatePhone(com.clapi.UpdatePhoneRequest request,
        io.grpc.stub.StreamObserver<com.clapi.UpdatePhoneResponse> responseObserver);

    public void deletePhone(com.clapi.DeletePhoneRequest request,
        io.grpc.stub.StreamObserver<com.clapi.DeletePhoneResponse> responseObserver);

    public void addAddress(com.clapi.AddAddressRequest request,
        io.grpc.stub.StreamObserver<com.clapi.AddAddressResponse> responseObserver);

    public void updateAddress(com.clapi.UpdateAddressRequest request,
        io.grpc.stub.StreamObserver<com.clapi.UpdateAddressResponse> responseObserver);

    public void deleteAddress(com.clapi.DeleteAddressRequest request,
        io.grpc.stub.StreamObserver<com.clapi.DeleteAddressResponse> responseObserver);
  }

  public static interface CLApiBlockingClient {

    public com.clapi.GetVersionResponse getVersion(com.clapi.GetVersionRequest request);

    public com.clapi.CheckCompatibilityResponse checkCompatibility(com.clapi.CheckCompatibilityRequest request);

    public com.clapi.WaitNotificationResponse waitNotification(com.clapi.WaitNotificationRequest request);

    public com.clapi.GetJsonDataResponse getJsonData(com.clapi.GetJsonDataRequest request);

    public com.clapi.AddPersonResponse addPerson(com.clapi.AddPersonRequest request);

    public com.clapi.UpdatePersonResponse updatePerson(com.clapi.UpdatePersonRequest request);

    public com.clapi.DeletePersonResponse deletePerson(com.clapi.DeletePersonRequest request);

    public com.clapi.AddEmailResponse addEmail(com.clapi.AddEmailRequest request);

    public com.clapi.UpdateEmailResponse updateEmail(com.clapi.UpdateEmailRequest request);

    public com.clapi.DeleteEmailResponse deleteEmail(com.clapi.DeleteEmailRequest request);

    public com.clapi.AddPhoneResponse addPhone(com.clapi.AddPhoneRequest request);

    public com.clapi.UpdatePhoneResponse updatePhone(com.clapi.UpdatePhoneRequest request);

    public com.clapi.DeletePhoneResponse deletePhone(com.clapi.DeletePhoneRequest request);

    public com.clapi.AddAddressResponse addAddress(com.clapi.AddAddressRequest request);

    public com.clapi.UpdateAddressResponse updateAddress(com.clapi.UpdateAddressRequest request);

    public com.clapi.DeleteAddressResponse deleteAddress(com.clapi.DeleteAddressRequest request);
  }

  public static interface CLApiFutureClient {

    public com.google.common.util.concurrent.ListenableFuture<com.clapi.GetVersionResponse> getVersion(
        com.clapi.GetVersionRequest request);

    public com.google.common.util.concurrent.ListenableFuture<com.clapi.CheckCompatibilityResponse> checkCompatibility(
        com.clapi.CheckCompatibilityRequest request);

    public com.google.common.util.concurrent.ListenableFuture<com.clapi.WaitNotificationResponse> waitNotification(
        com.clapi.WaitNotificationRequest request);

    public com.google.common.util.concurrent.ListenableFuture<com.clapi.GetJsonDataResponse> getJsonData(
        com.clapi.GetJsonDataRequest request);

    public com.google.common.util.concurrent.ListenableFuture<com.clapi.AddPersonResponse> addPerson(
        com.clapi.AddPersonRequest request);

    public com.google.common.util.concurrent.ListenableFuture<com.clapi.UpdatePersonResponse> updatePerson(
        com.clapi.UpdatePersonRequest request);

    public com.google.common.util.concurrent.ListenableFuture<com.clapi.DeletePersonResponse> deletePerson(
        com.clapi.DeletePersonRequest request);

    public com.google.common.util.concurrent.ListenableFuture<com.clapi.AddEmailResponse> addEmail(
        com.clapi.AddEmailRequest request);

    public com.google.common.util.concurrent.ListenableFuture<com.clapi.UpdateEmailResponse> updateEmail(
        com.clapi.UpdateEmailRequest request);

    public com.google.common.util.concurrent.ListenableFuture<com.clapi.DeleteEmailResponse> deleteEmail(
        com.clapi.DeleteEmailRequest request);

    public com.google.common.util.concurrent.ListenableFuture<com.clapi.AddPhoneResponse> addPhone(
        com.clapi.AddPhoneRequest request);

    public com.google.common.util.concurrent.ListenableFuture<com.clapi.UpdatePhoneResponse> updatePhone(
        com.clapi.UpdatePhoneRequest request);

    public com.google.common.util.concurrent.ListenableFuture<com.clapi.DeletePhoneResponse> deletePhone(
        com.clapi.DeletePhoneRequest request);

    public com.google.common.util.concurrent.ListenableFuture<com.clapi.AddAddressResponse> addAddress(
        com.clapi.AddAddressRequest request);

    public com.google.common.util.concurrent.ListenableFuture<com.clapi.UpdateAddressResponse> updateAddress(
        com.clapi.UpdateAddressRequest request);

    public com.google.common.util.concurrent.ListenableFuture<com.clapi.DeleteAddressResponse> deleteAddress(
        com.clapi.DeleteAddressRequest request);
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
    public void getVersion(com.clapi.GetVersionRequest request,
        io.grpc.stub.StreamObserver<com.clapi.GetVersionResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_GET_VERSION, getCallOptions()), request, responseObserver);
    }

    @java.lang.Override
    public void checkCompatibility(com.clapi.CheckCompatibilityRequest request,
        io.grpc.stub.StreamObserver<com.clapi.CheckCompatibilityResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_CHECK_COMPATIBILITY, getCallOptions()), request, responseObserver);
    }

    @java.lang.Override
    public void waitNotification(com.clapi.WaitNotificationRequest request,
        io.grpc.stub.StreamObserver<com.clapi.WaitNotificationResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_WAIT_NOTIFICATION, getCallOptions()), request, responseObserver);
    }

    @java.lang.Override
    public void getJsonData(com.clapi.GetJsonDataRequest request,
        io.grpc.stub.StreamObserver<com.clapi.GetJsonDataResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_GET_JSON_DATA, getCallOptions()), request, responseObserver);
    }

    @java.lang.Override
    public void addPerson(com.clapi.AddPersonRequest request,
        io.grpc.stub.StreamObserver<com.clapi.AddPersonResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_ADD_PERSON, getCallOptions()), request, responseObserver);
    }

    @java.lang.Override
    public void updatePerson(com.clapi.UpdatePersonRequest request,
        io.grpc.stub.StreamObserver<com.clapi.UpdatePersonResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_UPDATE_PERSON, getCallOptions()), request, responseObserver);
    }

    @java.lang.Override
    public void deletePerson(com.clapi.DeletePersonRequest request,
        io.grpc.stub.StreamObserver<com.clapi.DeletePersonResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_DELETE_PERSON, getCallOptions()), request, responseObserver);
    }

    @java.lang.Override
    public void addEmail(com.clapi.AddEmailRequest request,
        io.grpc.stub.StreamObserver<com.clapi.AddEmailResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_ADD_EMAIL, getCallOptions()), request, responseObserver);
    }

    @java.lang.Override
    public void updateEmail(com.clapi.UpdateEmailRequest request,
        io.grpc.stub.StreamObserver<com.clapi.UpdateEmailResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_UPDATE_EMAIL, getCallOptions()), request, responseObserver);
    }

    @java.lang.Override
    public void deleteEmail(com.clapi.DeleteEmailRequest request,
        io.grpc.stub.StreamObserver<com.clapi.DeleteEmailResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_DELETE_EMAIL, getCallOptions()), request, responseObserver);
    }

    @java.lang.Override
    public void addPhone(com.clapi.AddPhoneRequest request,
        io.grpc.stub.StreamObserver<com.clapi.AddPhoneResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_ADD_PHONE, getCallOptions()), request, responseObserver);
    }

    @java.lang.Override
    public void updatePhone(com.clapi.UpdatePhoneRequest request,
        io.grpc.stub.StreamObserver<com.clapi.UpdatePhoneResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_UPDATE_PHONE, getCallOptions()), request, responseObserver);
    }

    @java.lang.Override
    public void deletePhone(com.clapi.DeletePhoneRequest request,
        io.grpc.stub.StreamObserver<com.clapi.DeletePhoneResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_DELETE_PHONE, getCallOptions()), request, responseObserver);
    }

    @java.lang.Override
    public void addAddress(com.clapi.AddAddressRequest request,
        io.grpc.stub.StreamObserver<com.clapi.AddAddressResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_ADD_ADDRESS, getCallOptions()), request, responseObserver);
    }

    @java.lang.Override
    public void updateAddress(com.clapi.UpdateAddressRequest request,
        io.grpc.stub.StreamObserver<com.clapi.UpdateAddressResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_UPDATE_ADDRESS, getCallOptions()), request, responseObserver);
    }

    @java.lang.Override
    public void deleteAddress(com.clapi.DeleteAddressRequest request,
        io.grpc.stub.StreamObserver<com.clapi.DeleteAddressResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_DELETE_ADDRESS, getCallOptions()), request, responseObserver);
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
    public com.clapi.GetVersionResponse getVersion(com.clapi.GetVersionRequest request) {
      return blockingUnaryCall(
          getChannel().newCall(METHOD_GET_VERSION, getCallOptions()), request);
    }

    @java.lang.Override
    public com.clapi.CheckCompatibilityResponse checkCompatibility(com.clapi.CheckCompatibilityRequest request) {
      return blockingUnaryCall(
          getChannel().newCall(METHOD_CHECK_COMPATIBILITY, getCallOptions()), request);
    }

    @java.lang.Override
    public com.clapi.WaitNotificationResponse waitNotification(com.clapi.WaitNotificationRequest request) {
      return blockingUnaryCall(
          getChannel().newCall(METHOD_WAIT_NOTIFICATION, getCallOptions()), request);
    }

    @java.lang.Override
    public com.clapi.GetJsonDataResponse getJsonData(com.clapi.GetJsonDataRequest request) {
      return blockingUnaryCall(
          getChannel().newCall(METHOD_GET_JSON_DATA, getCallOptions()), request);
    }

    @java.lang.Override
    public com.clapi.AddPersonResponse addPerson(com.clapi.AddPersonRequest request) {
      return blockingUnaryCall(
          getChannel().newCall(METHOD_ADD_PERSON, getCallOptions()), request);
    }

    @java.lang.Override
    public com.clapi.UpdatePersonResponse updatePerson(com.clapi.UpdatePersonRequest request) {
      return blockingUnaryCall(
          getChannel().newCall(METHOD_UPDATE_PERSON, getCallOptions()), request);
    }

    @java.lang.Override
    public com.clapi.DeletePersonResponse deletePerson(com.clapi.DeletePersonRequest request) {
      return blockingUnaryCall(
          getChannel().newCall(METHOD_DELETE_PERSON, getCallOptions()), request);
    }

    @java.lang.Override
    public com.clapi.AddEmailResponse addEmail(com.clapi.AddEmailRequest request) {
      return blockingUnaryCall(
          getChannel().newCall(METHOD_ADD_EMAIL, getCallOptions()), request);
    }

    @java.lang.Override
    public com.clapi.UpdateEmailResponse updateEmail(com.clapi.UpdateEmailRequest request) {
      return blockingUnaryCall(
          getChannel().newCall(METHOD_UPDATE_EMAIL, getCallOptions()), request);
    }

    @java.lang.Override
    public com.clapi.DeleteEmailResponse deleteEmail(com.clapi.DeleteEmailRequest request) {
      return blockingUnaryCall(
          getChannel().newCall(METHOD_DELETE_EMAIL, getCallOptions()), request);
    }

    @java.lang.Override
    public com.clapi.AddPhoneResponse addPhone(com.clapi.AddPhoneRequest request) {
      return blockingUnaryCall(
          getChannel().newCall(METHOD_ADD_PHONE, getCallOptions()), request);
    }

    @java.lang.Override
    public com.clapi.UpdatePhoneResponse updatePhone(com.clapi.UpdatePhoneRequest request) {
      return blockingUnaryCall(
          getChannel().newCall(METHOD_UPDATE_PHONE, getCallOptions()), request);
    }

    @java.lang.Override
    public com.clapi.DeletePhoneResponse deletePhone(com.clapi.DeletePhoneRequest request) {
      return blockingUnaryCall(
          getChannel().newCall(METHOD_DELETE_PHONE, getCallOptions()), request);
    }

    @java.lang.Override
    public com.clapi.AddAddressResponse addAddress(com.clapi.AddAddressRequest request) {
      return blockingUnaryCall(
          getChannel().newCall(METHOD_ADD_ADDRESS, getCallOptions()), request);
    }

    @java.lang.Override
    public com.clapi.UpdateAddressResponse updateAddress(com.clapi.UpdateAddressRequest request) {
      return blockingUnaryCall(
          getChannel().newCall(METHOD_UPDATE_ADDRESS, getCallOptions()), request);
    }

    @java.lang.Override
    public com.clapi.DeleteAddressResponse deleteAddress(com.clapi.DeleteAddressRequest request) {
      return blockingUnaryCall(
          getChannel().newCall(METHOD_DELETE_ADDRESS, getCallOptions()), request);
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
    public com.google.common.util.concurrent.ListenableFuture<com.clapi.GetVersionResponse> getVersion(
        com.clapi.GetVersionRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_GET_VERSION, getCallOptions()), request);
    }

    @java.lang.Override
    public com.google.common.util.concurrent.ListenableFuture<com.clapi.CheckCompatibilityResponse> checkCompatibility(
        com.clapi.CheckCompatibilityRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_CHECK_COMPATIBILITY, getCallOptions()), request);
    }

    @java.lang.Override
    public com.google.common.util.concurrent.ListenableFuture<com.clapi.WaitNotificationResponse> waitNotification(
        com.clapi.WaitNotificationRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_WAIT_NOTIFICATION, getCallOptions()), request);
    }

    @java.lang.Override
    public com.google.common.util.concurrent.ListenableFuture<com.clapi.GetJsonDataResponse> getJsonData(
        com.clapi.GetJsonDataRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_GET_JSON_DATA, getCallOptions()), request);
    }

    @java.lang.Override
    public com.google.common.util.concurrent.ListenableFuture<com.clapi.AddPersonResponse> addPerson(
        com.clapi.AddPersonRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_ADD_PERSON, getCallOptions()), request);
    }

    @java.lang.Override
    public com.google.common.util.concurrent.ListenableFuture<com.clapi.UpdatePersonResponse> updatePerson(
        com.clapi.UpdatePersonRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_UPDATE_PERSON, getCallOptions()), request);
    }

    @java.lang.Override
    public com.google.common.util.concurrent.ListenableFuture<com.clapi.DeletePersonResponse> deletePerson(
        com.clapi.DeletePersonRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_DELETE_PERSON, getCallOptions()), request);
    }

    @java.lang.Override
    public com.google.common.util.concurrent.ListenableFuture<com.clapi.AddEmailResponse> addEmail(
        com.clapi.AddEmailRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_ADD_EMAIL, getCallOptions()), request);
    }

    @java.lang.Override
    public com.google.common.util.concurrent.ListenableFuture<com.clapi.UpdateEmailResponse> updateEmail(
        com.clapi.UpdateEmailRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_UPDATE_EMAIL, getCallOptions()), request);
    }

    @java.lang.Override
    public com.google.common.util.concurrent.ListenableFuture<com.clapi.DeleteEmailResponse> deleteEmail(
        com.clapi.DeleteEmailRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_DELETE_EMAIL, getCallOptions()), request);
    }

    @java.lang.Override
    public com.google.common.util.concurrent.ListenableFuture<com.clapi.AddPhoneResponse> addPhone(
        com.clapi.AddPhoneRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_ADD_PHONE, getCallOptions()), request);
    }

    @java.lang.Override
    public com.google.common.util.concurrent.ListenableFuture<com.clapi.UpdatePhoneResponse> updatePhone(
        com.clapi.UpdatePhoneRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_UPDATE_PHONE, getCallOptions()), request);
    }

    @java.lang.Override
    public com.google.common.util.concurrent.ListenableFuture<com.clapi.DeletePhoneResponse> deletePhone(
        com.clapi.DeletePhoneRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_DELETE_PHONE, getCallOptions()), request);
    }

    @java.lang.Override
    public com.google.common.util.concurrent.ListenableFuture<com.clapi.AddAddressResponse> addAddress(
        com.clapi.AddAddressRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_ADD_ADDRESS, getCallOptions()), request);
    }

    @java.lang.Override
    public com.google.common.util.concurrent.ListenableFuture<com.clapi.UpdateAddressResponse> updateAddress(
        com.clapi.UpdateAddressRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_UPDATE_ADDRESS, getCallOptions()), request);
    }

    @java.lang.Override
    public com.google.common.util.concurrent.ListenableFuture<com.clapi.DeleteAddressResponse> deleteAddress(
        com.clapi.DeleteAddressRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_DELETE_ADDRESS, getCallOptions()), request);
    }
  }

  public static io.grpc.ServerServiceDefinition bindService(
      final CLApi serviceImpl) {
    return io.grpc.ServerServiceDefinition.builder(SERVICE_NAME)
      .addMethod(
        METHOD_GET_VERSION,
        asyncUnaryCall(
          new io.grpc.stub.ServerCalls.UnaryMethod<
              com.clapi.GetVersionRequest,
              com.clapi.GetVersionResponse>() {
            @java.lang.Override
            public void invoke(
                com.clapi.GetVersionRequest request,
                io.grpc.stub.StreamObserver<com.clapi.GetVersionResponse> responseObserver) {
              serviceImpl.getVersion(request, responseObserver);
            }
          }))
      .addMethod(
        METHOD_CHECK_COMPATIBILITY,
        asyncUnaryCall(
          new io.grpc.stub.ServerCalls.UnaryMethod<
              com.clapi.CheckCompatibilityRequest,
              com.clapi.CheckCompatibilityResponse>() {
            @java.lang.Override
            public void invoke(
                com.clapi.CheckCompatibilityRequest request,
                io.grpc.stub.StreamObserver<com.clapi.CheckCompatibilityResponse> responseObserver) {
              serviceImpl.checkCompatibility(request, responseObserver);
            }
          }))
      .addMethod(
        METHOD_WAIT_NOTIFICATION,
        asyncUnaryCall(
          new io.grpc.stub.ServerCalls.UnaryMethod<
              com.clapi.WaitNotificationRequest,
              com.clapi.WaitNotificationResponse>() {
            @java.lang.Override
            public void invoke(
                com.clapi.WaitNotificationRequest request,
                io.grpc.stub.StreamObserver<com.clapi.WaitNotificationResponse> responseObserver) {
              serviceImpl.waitNotification(request, responseObserver);
            }
          }))
      .addMethod(
        METHOD_GET_JSON_DATA,
        asyncUnaryCall(
          new io.grpc.stub.ServerCalls.UnaryMethod<
              com.clapi.GetJsonDataRequest,
              com.clapi.GetJsonDataResponse>() {
            @java.lang.Override
            public void invoke(
                com.clapi.GetJsonDataRequest request,
                io.grpc.stub.StreamObserver<com.clapi.GetJsonDataResponse> responseObserver) {
              serviceImpl.getJsonData(request, responseObserver);
            }
          }))
      .addMethod(
        METHOD_ADD_PERSON,
        asyncUnaryCall(
          new io.grpc.stub.ServerCalls.UnaryMethod<
              com.clapi.AddPersonRequest,
              com.clapi.AddPersonResponse>() {
            @java.lang.Override
            public void invoke(
                com.clapi.AddPersonRequest request,
                io.grpc.stub.StreamObserver<com.clapi.AddPersonResponse> responseObserver) {
              serviceImpl.addPerson(request, responseObserver);
            }
          }))
      .addMethod(
        METHOD_UPDATE_PERSON,
        asyncUnaryCall(
          new io.grpc.stub.ServerCalls.UnaryMethod<
              com.clapi.UpdatePersonRequest,
              com.clapi.UpdatePersonResponse>() {
            @java.lang.Override
            public void invoke(
                com.clapi.UpdatePersonRequest request,
                io.grpc.stub.StreamObserver<com.clapi.UpdatePersonResponse> responseObserver) {
              serviceImpl.updatePerson(request, responseObserver);
            }
          }))
      .addMethod(
        METHOD_DELETE_PERSON,
        asyncUnaryCall(
          new io.grpc.stub.ServerCalls.UnaryMethod<
              com.clapi.DeletePersonRequest,
              com.clapi.DeletePersonResponse>() {
            @java.lang.Override
            public void invoke(
                com.clapi.DeletePersonRequest request,
                io.grpc.stub.StreamObserver<com.clapi.DeletePersonResponse> responseObserver) {
              serviceImpl.deletePerson(request, responseObserver);
            }
          }))
      .addMethod(
        METHOD_ADD_EMAIL,
        asyncUnaryCall(
          new io.grpc.stub.ServerCalls.UnaryMethod<
              com.clapi.AddEmailRequest,
              com.clapi.AddEmailResponse>() {
            @java.lang.Override
            public void invoke(
                com.clapi.AddEmailRequest request,
                io.grpc.stub.StreamObserver<com.clapi.AddEmailResponse> responseObserver) {
              serviceImpl.addEmail(request, responseObserver);
            }
          }))
      .addMethod(
        METHOD_UPDATE_EMAIL,
        asyncUnaryCall(
          new io.grpc.stub.ServerCalls.UnaryMethod<
              com.clapi.UpdateEmailRequest,
              com.clapi.UpdateEmailResponse>() {
            @java.lang.Override
            public void invoke(
                com.clapi.UpdateEmailRequest request,
                io.grpc.stub.StreamObserver<com.clapi.UpdateEmailResponse> responseObserver) {
              serviceImpl.updateEmail(request, responseObserver);
            }
          }))
      .addMethod(
        METHOD_DELETE_EMAIL,
        asyncUnaryCall(
          new io.grpc.stub.ServerCalls.UnaryMethod<
              com.clapi.DeleteEmailRequest,
              com.clapi.DeleteEmailResponse>() {
            @java.lang.Override
            public void invoke(
                com.clapi.DeleteEmailRequest request,
                io.grpc.stub.StreamObserver<com.clapi.DeleteEmailResponse> responseObserver) {
              serviceImpl.deleteEmail(request, responseObserver);
            }
          }))
      .addMethod(
        METHOD_ADD_PHONE,
        asyncUnaryCall(
          new io.grpc.stub.ServerCalls.UnaryMethod<
              com.clapi.AddPhoneRequest,
              com.clapi.AddPhoneResponse>() {
            @java.lang.Override
            public void invoke(
                com.clapi.AddPhoneRequest request,
                io.grpc.stub.StreamObserver<com.clapi.AddPhoneResponse> responseObserver) {
              serviceImpl.addPhone(request, responseObserver);
            }
          }))
      .addMethod(
        METHOD_UPDATE_PHONE,
        asyncUnaryCall(
          new io.grpc.stub.ServerCalls.UnaryMethod<
              com.clapi.UpdatePhoneRequest,
              com.clapi.UpdatePhoneResponse>() {
            @java.lang.Override
            public void invoke(
                com.clapi.UpdatePhoneRequest request,
                io.grpc.stub.StreamObserver<com.clapi.UpdatePhoneResponse> responseObserver) {
              serviceImpl.updatePhone(request, responseObserver);
            }
          }))
      .addMethod(
        METHOD_DELETE_PHONE,
        asyncUnaryCall(
          new io.grpc.stub.ServerCalls.UnaryMethod<
              com.clapi.DeletePhoneRequest,
              com.clapi.DeletePhoneResponse>() {
            @java.lang.Override
            public void invoke(
                com.clapi.DeletePhoneRequest request,
                io.grpc.stub.StreamObserver<com.clapi.DeletePhoneResponse> responseObserver) {
              serviceImpl.deletePhone(request, responseObserver);
            }
          }))
      .addMethod(
        METHOD_ADD_ADDRESS,
        asyncUnaryCall(
          new io.grpc.stub.ServerCalls.UnaryMethod<
              com.clapi.AddAddressRequest,
              com.clapi.AddAddressResponse>() {
            @java.lang.Override
            public void invoke(
                com.clapi.AddAddressRequest request,
                io.grpc.stub.StreamObserver<com.clapi.AddAddressResponse> responseObserver) {
              serviceImpl.addAddress(request, responseObserver);
            }
          }))
      .addMethod(
        METHOD_UPDATE_ADDRESS,
        asyncUnaryCall(
          new io.grpc.stub.ServerCalls.UnaryMethod<
              com.clapi.UpdateAddressRequest,
              com.clapi.UpdateAddressResponse>() {
            @java.lang.Override
            public void invoke(
                com.clapi.UpdateAddressRequest request,
                io.grpc.stub.StreamObserver<com.clapi.UpdateAddressResponse> responseObserver) {
              serviceImpl.updateAddress(request, responseObserver);
            }
          }))
      .addMethod(
        METHOD_DELETE_ADDRESS,
        asyncUnaryCall(
          new io.grpc.stub.ServerCalls.UnaryMethod<
              com.clapi.DeleteAddressRequest,
              com.clapi.DeleteAddressResponse>() {
            @java.lang.Override
            public void invoke(
                com.clapi.DeleteAddressRequest request,
                io.grpc.stub.StreamObserver<com.clapi.DeleteAddressResponse> responseObserver) {
              serviceImpl.deleteAddress(request, responseObserver);
            }
          })).build();
  }
}
