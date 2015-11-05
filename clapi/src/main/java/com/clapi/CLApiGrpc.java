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
  }

  public static interface CLApiBlockingClient {

    public com.clapi.GetVersionResponse getVersion(com.clapi.GetVersionRequest request);
  }

  public static interface CLApiFutureClient {

    public com.google.common.util.concurrent.ListenableFuture<com.clapi.GetVersionResponse> getVersion(
        com.clapi.GetVersionRequest request);
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
          })).build();
  }
}
