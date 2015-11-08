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
  public static final io.grpc.MethodDescriptor<com.clapi.WaitNotificationRequest,
      com.clapi.WaitNotificationResponse> METHOD_WAIT_NOTIFICATION =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "clapi.CLApi", "waitNotification"),
          io.grpc.protobuf.ProtoUtils.marshaller(com.clapi.WaitNotificationRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(com.clapi.WaitNotificationResponse.getDefaultInstance()));
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
  public static final io.grpc.MethodDescriptor<com.clapi.DeleteAllPersonRequest,
      com.clapi.DeleteAllPersonResponse> METHOD_DELETE_ALL_PERSON =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "clapi.CLApi", "deleteAllPerson"),
          io.grpc.protobuf.ProtoUtils.marshaller(com.clapi.DeleteAllPersonRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(com.clapi.DeleteAllPersonResponse.getDefaultInstance()));

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

    public void waitNotification(com.clapi.WaitNotificationRequest request,
        io.grpc.stub.StreamObserver<com.clapi.WaitNotificationResponse> responseObserver);

    public void addPerson(com.clapi.AddPersonRequest request,
        io.grpc.stub.StreamObserver<com.clapi.AddPersonResponse> responseObserver);

    public void updatePerson(com.clapi.UpdatePersonRequest request,
        io.grpc.stub.StreamObserver<com.clapi.UpdatePersonResponse> responseObserver);

    public void deletePerson(com.clapi.DeletePersonRequest request,
        io.grpc.stub.StreamObserver<com.clapi.DeletePersonResponse> responseObserver);

    public void deleteAllPerson(com.clapi.DeleteAllPersonRequest request,
        io.grpc.stub.StreamObserver<com.clapi.DeleteAllPersonResponse> responseObserver);
  }

  public static interface CLApiBlockingClient {

    public com.clapi.GetVersionResponse getVersion(com.clapi.GetVersionRequest request);

    public com.clapi.WaitNotificationResponse waitNotification(com.clapi.WaitNotificationRequest request);

    public com.clapi.AddPersonResponse addPerson(com.clapi.AddPersonRequest request);

    public com.clapi.UpdatePersonResponse updatePerson(com.clapi.UpdatePersonRequest request);

    public com.clapi.DeletePersonResponse deletePerson(com.clapi.DeletePersonRequest request);

    public com.clapi.DeleteAllPersonResponse deleteAllPerson(com.clapi.DeleteAllPersonRequest request);
  }

  public static interface CLApiFutureClient {

    public com.google.common.util.concurrent.ListenableFuture<com.clapi.GetVersionResponse> getVersion(
        com.clapi.GetVersionRequest request);

    public com.google.common.util.concurrent.ListenableFuture<com.clapi.WaitNotificationResponse> waitNotification(
        com.clapi.WaitNotificationRequest request);

    public com.google.common.util.concurrent.ListenableFuture<com.clapi.AddPersonResponse> addPerson(
        com.clapi.AddPersonRequest request);

    public com.google.common.util.concurrent.ListenableFuture<com.clapi.UpdatePersonResponse> updatePerson(
        com.clapi.UpdatePersonRequest request);

    public com.google.common.util.concurrent.ListenableFuture<com.clapi.DeletePersonResponse> deletePerson(
        com.clapi.DeletePersonRequest request);

    public com.google.common.util.concurrent.ListenableFuture<com.clapi.DeleteAllPersonResponse> deleteAllPerson(
        com.clapi.DeleteAllPersonRequest request);
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
    public void waitNotification(com.clapi.WaitNotificationRequest request,
        io.grpc.stub.StreamObserver<com.clapi.WaitNotificationResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_WAIT_NOTIFICATION, getCallOptions()), request, responseObserver);
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
    public void deleteAllPerson(com.clapi.DeleteAllPersonRequest request,
        io.grpc.stub.StreamObserver<com.clapi.DeleteAllPersonResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_DELETE_ALL_PERSON, getCallOptions()), request, responseObserver);
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
    public com.clapi.WaitNotificationResponse waitNotification(com.clapi.WaitNotificationRequest request) {
      return blockingUnaryCall(
          getChannel().newCall(METHOD_WAIT_NOTIFICATION, getCallOptions()), request);
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
    public com.clapi.DeleteAllPersonResponse deleteAllPerson(com.clapi.DeleteAllPersonRequest request) {
      return blockingUnaryCall(
          getChannel().newCall(METHOD_DELETE_ALL_PERSON, getCallOptions()), request);
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
    public com.google.common.util.concurrent.ListenableFuture<com.clapi.WaitNotificationResponse> waitNotification(
        com.clapi.WaitNotificationRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_WAIT_NOTIFICATION, getCallOptions()), request);
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
    public com.google.common.util.concurrent.ListenableFuture<com.clapi.DeleteAllPersonResponse> deleteAllPerson(
        com.clapi.DeleteAllPersonRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_DELETE_ALL_PERSON, getCallOptions()), request);
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
        METHOD_DELETE_ALL_PERSON,
        asyncUnaryCall(
          new io.grpc.stub.ServerCalls.UnaryMethod<
              com.clapi.DeleteAllPersonRequest,
              com.clapi.DeleteAllPersonResponse>() {
            @java.lang.Override
            public void invoke(
                com.clapi.DeleteAllPersonRequest request,
                io.grpc.stub.StreamObserver<com.clapi.DeleteAllPersonResponse> responseObserver) {
              serviceImpl.deleteAllPerson(request, responseObserver);
            }
          })).build();
  }
}
