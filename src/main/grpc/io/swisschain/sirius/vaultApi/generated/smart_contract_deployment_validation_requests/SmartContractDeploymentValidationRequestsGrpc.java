package io.swisschain.sirius.vaultApi.generated.smart_contract_deployment_validation_requests;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/** */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.31.1)",
    comments = "Source: smart-contract-deployment-validation-requests.proto")
public final class SmartContractDeploymentValidationRequestsGrpc {

  private SmartContractDeploymentValidationRequestsGrpc() {}

  public static final String SERVICE_NAME =
      "swisschain.sirius.vaultApi.smartContractDeploymentValidationRequests.SmartContractDeploymentValidationRequests";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<
          io.swisschain
              .sirius
              .vaultApi
              .generated
              .smart_contract_deployment_validation_requests
              .SmartContractDeploymentValidationRequestsOuterClass
              .GetSmartContractDeploymentValidationRequestsRequest,
          io.swisschain
              .sirius
              .vaultApi
              .generated
              .smart_contract_deployment_validation_requests
              .SmartContractDeploymentValidationRequestsOuterClass
              .GetSmartContractDeploymentValidationRequestsResponse>
      getGetMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Get",
      requestType =
          io.swisschain
              .sirius
              .vaultApi
              .generated
              .smart_contract_deployment_validation_requests
              .SmartContractDeploymentValidationRequestsOuterClass
              .GetSmartContractDeploymentValidationRequestsRequest
              .class,
      responseType =
          io.swisschain
              .sirius
              .vaultApi
              .generated
              .smart_contract_deployment_validation_requests
              .SmartContractDeploymentValidationRequestsOuterClass
              .GetSmartContractDeploymentValidationRequestsResponse
              .class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<
          io.swisschain
              .sirius
              .vaultApi
              .generated
              .smart_contract_deployment_validation_requests
              .SmartContractDeploymentValidationRequestsOuterClass
              .GetSmartContractDeploymentValidationRequestsRequest,
          io.swisschain
              .sirius
              .vaultApi
              .generated
              .smart_contract_deployment_validation_requests
              .SmartContractDeploymentValidationRequestsOuterClass
              .GetSmartContractDeploymentValidationRequestsResponse>
      getGetMethod() {
    io.grpc.MethodDescriptor<
            io.swisschain
                .sirius
                .vaultApi
                .generated
                .smart_contract_deployment_validation_requests
                .SmartContractDeploymentValidationRequestsOuterClass
                .GetSmartContractDeploymentValidationRequestsRequest,
            io.swisschain
                .sirius
                .vaultApi
                .generated
                .smart_contract_deployment_validation_requests
                .SmartContractDeploymentValidationRequestsOuterClass
                .GetSmartContractDeploymentValidationRequestsResponse>
        getGetMethod;
    if ((getGetMethod = SmartContractDeploymentValidationRequestsGrpc.getGetMethod) == null) {
      synchronized (SmartContractDeploymentValidationRequestsGrpc.class) {
        if ((getGetMethod = SmartContractDeploymentValidationRequestsGrpc.getGetMethod) == null) {
          SmartContractDeploymentValidationRequestsGrpc.getGetMethod =
              getGetMethod =
                  io.grpc.MethodDescriptor
                      .<io.swisschain
                              .sirius
                              .vaultApi
                              .generated
                              .smart_contract_deployment_validation_requests
                              .SmartContractDeploymentValidationRequestsOuterClass
                              .GetSmartContractDeploymentValidationRequestsRequest,
                          io.swisschain
                              .sirius
                              .vaultApi
                              .generated
                              .smart_contract_deployment_validation_requests
                              .SmartContractDeploymentValidationRequestsOuterClass
                              .GetSmartContractDeploymentValidationRequestsResponse>
                          newBuilder()
                      .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                      .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Get"))
                      .setSampledToLocalTracing(true)
                      .setRequestMarshaller(
                          io.grpc.protobuf.ProtoUtils.marshaller(
                              io.swisschain.sirius.vaultApi.generated
                                  .smart_contract_deployment_validation_requests
                                  .SmartContractDeploymentValidationRequestsOuterClass
                                  .GetSmartContractDeploymentValidationRequestsRequest
                                  .getDefaultInstance()))
                      .setResponseMarshaller(
                          io.grpc.protobuf.ProtoUtils.marshaller(
                              io.swisschain.sirius.vaultApi.generated
                                  .smart_contract_deployment_validation_requests
                                  .SmartContractDeploymentValidationRequestsOuterClass
                                  .GetSmartContractDeploymentValidationRequestsResponse
                                  .getDefaultInstance()))
                      .setSchemaDescriptor(
                          new SmartContractDeploymentValidationRequestsMethodDescriptorSupplier(
                              "Get"))
                      .build();
        }
      }
    }
    return getGetMethod;
  }

  private static volatile io.grpc.MethodDescriptor<
          io.swisschain
              .sirius
              .vaultApi
              .generated
              .smart_contract_deployment_validation_requests
              .SmartContractDeploymentValidationRequestsOuterClass
              .ConfirmSmartContractDeploymentValidationRequestRequest,
          io.swisschain
              .sirius
              .vaultApi
              .generated
              .smart_contract_deployment_validation_requests
              .SmartContractDeploymentValidationRequestsOuterClass
              .ConfirmSmartContractDeploymentValidationRequestResponse>
      getConfirmMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Confirm",
      requestType =
          io.swisschain
              .sirius
              .vaultApi
              .generated
              .smart_contract_deployment_validation_requests
              .SmartContractDeploymentValidationRequestsOuterClass
              .ConfirmSmartContractDeploymentValidationRequestRequest
              .class,
      responseType =
          io.swisschain
              .sirius
              .vaultApi
              .generated
              .smart_contract_deployment_validation_requests
              .SmartContractDeploymentValidationRequestsOuterClass
              .ConfirmSmartContractDeploymentValidationRequestResponse
              .class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<
          io.swisschain
              .sirius
              .vaultApi
              .generated
              .smart_contract_deployment_validation_requests
              .SmartContractDeploymentValidationRequestsOuterClass
              .ConfirmSmartContractDeploymentValidationRequestRequest,
          io.swisschain
              .sirius
              .vaultApi
              .generated
              .smart_contract_deployment_validation_requests
              .SmartContractDeploymentValidationRequestsOuterClass
              .ConfirmSmartContractDeploymentValidationRequestResponse>
      getConfirmMethod() {
    io.grpc.MethodDescriptor<
            io.swisschain
                .sirius
                .vaultApi
                .generated
                .smart_contract_deployment_validation_requests
                .SmartContractDeploymentValidationRequestsOuterClass
                .ConfirmSmartContractDeploymentValidationRequestRequest,
            io.swisschain
                .sirius
                .vaultApi
                .generated
                .smart_contract_deployment_validation_requests
                .SmartContractDeploymentValidationRequestsOuterClass
                .ConfirmSmartContractDeploymentValidationRequestResponse>
        getConfirmMethod;
    if ((getConfirmMethod = SmartContractDeploymentValidationRequestsGrpc.getConfirmMethod)
        == null) {
      synchronized (SmartContractDeploymentValidationRequestsGrpc.class) {
        if ((getConfirmMethod = SmartContractDeploymentValidationRequestsGrpc.getConfirmMethod)
            == null) {
          SmartContractDeploymentValidationRequestsGrpc.getConfirmMethod =
              getConfirmMethod =
                  io.grpc.MethodDescriptor
                      .<io.swisschain
                              .sirius
                              .vaultApi
                              .generated
                              .smart_contract_deployment_validation_requests
                              .SmartContractDeploymentValidationRequestsOuterClass
                              .ConfirmSmartContractDeploymentValidationRequestRequest,
                          io.swisschain
                              .sirius
                              .vaultApi
                              .generated
                              .smart_contract_deployment_validation_requests
                              .SmartContractDeploymentValidationRequestsOuterClass
                              .ConfirmSmartContractDeploymentValidationRequestResponse>
                          newBuilder()
                      .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                      .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Confirm"))
                      .setSampledToLocalTracing(true)
                      .setRequestMarshaller(
                          io.grpc.protobuf.ProtoUtils.marshaller(
                              io.swisschain.sirius.vaultApi.generated
                                  .smart_contract_deployment_validation_requests
                                  .SmartContractDeploymentValidationRequestsOuterClass
                                  .ConfirmSmartContractDeploymentValidationRequestRequest
                                  .getDefaultInstance()))
                      .setResponseMarshaller(
                          io.grpc.protobuf.ProtoUtils.marshaller(
                              io.swisschain.sirius.vaultApi.generated
                                  .smart_contract_deployment_validation_requests
                                  .SmartContractDeploymentValidationRequestsOuterClass
                                  .ConfirmSmartContractDeploymentValidationRequestResponse
                                  .getDefaultInstance()))
                      .setSchemaDescriptor(
                          new SmartContractDeploymentValidationRequestsMethodDescriptorSupplier(
                              "Confirm"))
                      .build();
        }
      }
    }
    return getConfirmMethod;
  }

  private static volatile io.grpc.MethodDescriptor<
          io.swisschain
              .sirius
              .vaultApi
              .generated
              .smart_contract_deployment_validation_requests
              .SmartContractDeploymentValidationRequestsOuterClass
              .RejectSmartContractDeploymentValidationRequestRequest,
          io.swisschain
              .sirius
              .vaultApi
              .generated
              .smart_contract_deployment_validation_requests
              .SmartContractDeploymentValidationRequestsOuterClass
              .RejectSmartContractDeploymentValidationRequestResponse>
      getRejectMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Reject",
      requestType =
          io.swisschain
              .sirius
              .vaultApi
              .generated
              .smart_contract_deployment_validation_requests
              .SmartContractDeploymentValidationRequestsOuterClass
              .RejectSmartContractDeploymentValidationRequestRequest
              .class,
      responseType =
          io.swisschain
              .sirius
              .vaultApi
              .generated
              .smart_contract_deployment_validation_requests
              .SmartContractDeploymentValidationRequestsOuterClass
              .RejectSmartContractDeploymentValidationRequestResponse
              .class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<
          io.swisschain
              .sirius
              .vaultApi
              .generated
              .smart_contract_deployment_validation_requests
              .SmartContractDeploymentValidationRequestsOuterClass
              .RejectSmartContractDeploymentValidationRequestRequest,
          io.swisschain
              .sirius
              .vaultApi
              .generated
              .smart_contract_deployment_validation_requests
              .SmartContractDeploymentValidationRequestsOuterClass
              .RejectSmartContractDeploymentValidationRequestResponse>
      getRejectMethod() {
    io.grpc.MethodDescriptor<
            io.swisschain
                .sirius
                .vaultApi
                .generated
                .smart_contract_deployment_validation_requests
                .SmartContractDeploymentValidationRequestsOuterClass
                .RejectSmartContractDeploymentValidationRequestRequest,
            io.swisschain
                .sirius
                .vaultApi
                .generated
                .smart_contract_deployment_validation_requests
                .SmartContractDeploymentValidationRequestsOuterClass
                .RejectSmartContractDeploymentValidationRequestResponse>
        getRejectMethod;
    if ((getRejectMethod = SmartContractDeploymentValidationRequestsGrpc.getRejectMethod) == null) {
      synchronized (SmartContractDeploymentValidationRequestsGrpc.class) {
        if ((getRejectMethod = SmartContractDeploymentValidationRequestsGrpc.getRejectMethod)
            == null) {
          SmartContractDeploymentValidationRequestsGrpc.getRejectMethod =
              getRejectMethod =
                  io.grpc.MethodDescriptor
                      .<io.swisschain
                              .sirius
                              .vaultApi
                              .generated
                              .smart_contract_deployment_validation_requests
                              .SmartContractDeploymentValidationRequestsOuterClass
                              .RejectSmartContractDeploymentValidationRequestRequest,
                          io.swisschain
                              .sirius
                              .vaultApi
                              .generated
                              .smart_contract_deployment_validation_requests
                              .SmartContractDeploymentValidationRequestsOuterClass
                              .RejectSmartContractDeploymentValidationRequestResponse>
                          newBuilder()
                      .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                      .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Reject"))
                      .setSampledToLocalTracing(true)
                      .setRequestMarshaller(
                          io.grpc.protobuf.ProtoUtils.marshaller(
                              io.swisschain.sirius.vaultApi.generated
                                  .smart_contract_deployment_validation_requests
                                  .SmartContractDeploymentValidationRequestsOuterClass
                                  .RejectSmartContractDeploymentValidationRequestRequest
                                  .getDefaultInstance()))
                      .setResponseMarshaller(
                          io.grpc.protobuf.ProtoUtils.marshaller(
                              io.swisschain.sirius.vaultApi.generated
                                  .smart_contract_deployment_validation_requests
                                  .SmartContractDeploymentValidationRequestsOuterClass
                                  .RejectSmartContractDeploymentValidationRequestResponse
                                  .getDefaultInstance()))
                      .setSchemaDescriptor(
                          new SmartContractDeploymentValidationRequestsMethodDescriptorSupplier(
                              "Reject"))
                      .build();
        }
      }
    }
    return getRejectMethod;
  }

  /** Creates a new async stub that supports all call types for the service */
  public static SmartContractDeploymentValidationRequestsStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<SmartContractDeploymentValidationRequestsStub> factory =
        new io.grpc.stub.AbstractStub.StubFactory<SmartContractDeploymentValidationRequestsStub>() {
          @java.lang.Override
          public SmartContractDeploymentValidationRequestsStub newStub(
              io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            return new SmartContractDeploymentValidationRequestsStub(channel, callOptions);
          }
        };
    return SmartContractDeploymentValidationRequestsStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static SmartContractDeploymentValidationRequestsBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<SmartContractDeploymentValidationRequestsBlockingStub>
        factory =
            new io.grpc.stub.AbstractStub.StubFactory<
                SmartContractDeploymentValidationRequestsBlockingStub>() {
              @java.lang.Override
              public SmartContractDeploymentValidationRequestsBlockingStub newStub(
                  io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
                return new SmartContractDeploymentValidationRequestsBlockingStub(
                    channel, callOptions);
              }
            };
    return SmartContractDeploymentValidationRequestsBlockingStub.newStub(factory, channel);
  }

  /** Creates a new ListenableFuture-style stub that supports unary calls on the service */
  public static SmartContractDeploymentValidationRequestsFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<SmartContractDeploymentValidationRequestsFutureStub>
        factory =
            new io.grpc.stub.AbstractStub.StubFactory<
                SmartContractDeploymentValidationRequestsFutureStub>() {
              @java.lang.Override
              public SmartContractDeploymentValidationRequestsFutureStub newStub(
                  io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
                return new SmartContractDeploymentValidationRequestsFutureStub(
                    channel, callOptions);
              }
            };
    return SmartContractDeploymentValidationRequestsFutureStub.newStub(factory, channel);
  }

  /** */
  public abstract static class SmartContractDeploymentValidationRequestsImplBase
      implements io.grpc.BindableService {

    /** */
    public void get(
        io.swisschain
                .sirius
                .vaultApi
                .generated
                .smart_contract_deployment_validation_requests
                .SmartContractDeploymentValidationRequestsOuterClass
                .GetSmartContractDeploymentValidationRequestsRequest
            request,
        io.grpc.stub.StreamObserver<
                io.swisschain
                    .sirius
                    .vaultApi
                    .generated
                    .smart_contract_deployment_validation_requests
                    .SmartContractDeploymentValidationRequestsOuterClass
                    .GetSmartContractDeploymentValidationRequestsResponse>
            responseObserver) {
      asyncUnimplementedUnaryCall(getGetMethod(), responseObserver);
    }

    /** */
    public void confirm(
        io.swisschain
                .sirius
                .vaultApi
                .generated
                .smart_contract_deployment_validation_requests
                .SmartContractDeploymentValidationRequestsOuterClass
                .ConfirmSmartContractDeploymentValidationRequestRequest
            request,
        io.grpc.stub.StreamObserver<
                io.swisschain
                    .sirius
                    .vaultApi
                    .generated
                    .smart_contract_deployment_validation_requests
                    .SmartContractDeploymentValidationRequestsOuterClass
                    .ConfirmSmartContractDeploymentValidationRequestResponse>
            responseObserver) {
      asyncUnimplementedUnaryCall(getConfirmMethod(), responseObserver);
    }

    /** */
    public void reject(
        io.swisschain
                .sirius
                .vaultApi
                .generated
                .smart_contract_deployment_validation_requests
                .SmartContractDeploymentValidationRequestsOuterClass
                .RejectSmartContractDeploymentValidationRequestRequest
            request,
        io.grpc.stub.StreamObserver<
                io.swisschain
                    .sirius
                    .vaultApi
                    .generated
                    .smart_contract_deployment_validation_requests
                    .SmartContractDeploymentValidationRequestsOuterClass
                    .RejectSmartContractDeploymentValidationRequestResponse>
            responseObserver) {
      asyncUnimplementedUnaryCall(getRejectMethod(), responseObserver);
    }

    @java.lang.Override
    public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
              getGetMethod(),
              asyncUnaryCall(
                  new MethodHandlers<
                      io.swisschain
                          .sirius
                          .vaultApi
                          .generated
                          .smart_contract_deployment_validation_requests
                          .SmartContractDeploymentValidationRequestsOuterClass
                          .GetSmartContractDeploymentValidationRequestsRequest,
                      io.swisschain
                          .sirius
                          .vaultApi
                          .generated
                          .smart_contract_deployment_validation_requests
                          .SmartContractDeploymentValidationRequestsOuterClass
                          .GetSmartContractDeploymentValidationRequestsResponse>(
                      this, METHODID_GET)))
          .addMethod(
              getConfirmMethod(),
              asyncUnaryCall(
                  new MethodHandlers<
                      io.swisschain
                          .sirius
                          .vaultApi
                          .generated
                          .smart_contract_deployment_validation_requests
                          .SmartContractDeploymentValidationRequestsOuterClass
                          .ConfirmSmartContractDeploymentValidationRequestRequest,
                      io.swisschain
                          .sirius
                          .vaultApi
                          .generated
                          .smart_contract_deployment_validation_requests
                          .SmartContractDeploymentValidationRequestsOuterClass
                          .ConfirmSmartContractDeploymentValidationRequestResponse>(
                      this, METHODID_CONFIRM)))
          .addMethod(
              getRejectMethod(),
              asyncUnaryCall(
                  new MethodHandlers<
                      io.swisschain
                          .sirius
                          .vaultApi
                          .generated
                          .smart_contract_deployment_validation_requests
                          .SmartContractDeploymentValidationRequestsOuterClass
                          .RejectSmartContractDeploymentValidationRequestRequest,
                      io.swisschain
                          .sirius
                          .vaultApi
                          .generated
                          .smart_contract_deployment_validation_requests
                          .SmartContractDeploymentValidationRequestsOuterClass
                          .RejectSmartContractDeploymentValidationRequestResponse>(
                      this, METHODID_REJECT)))
          .build();
    }
  }

  /** */
  public static final class SmartContractDeploymentValidationRequestsStub
      extends io.grpc.stub.AbstractAsyncStub<SmartContractDeploymentValidationRequestsStub> {
    private SmartContractDeploymentValidationRequestsStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SmartContractDeploymentValidationRequestsStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new SmartContractDeploymentValidationRequestsStub(channel, callOptions);
    }

    /** */
    public void get(
        io.swisschain
                .sirius
                .vaultApi
                .generated
                .smart_contract_deployment_validation_requests
                .SmartContractDeploymentValidationRequestsOuterClass
                .GetSmartContractDeploymentValidationRequestsRequest
            request,
        io.grpc.stub.StreamObserver<
                io.swisschain
                    .sirius
                    .vaultApi
                    .generated
                    .smart_contract_deployment_validation_requests
                    .SmartContractDeploymentValidationRequestsOuterClass
                    .GetSmartContractDeploymentValidationRequestsResponse>
            responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetMethod(), getCallOptions()), request, responseObserver);
    }

    /** */
    public void confirm(
        io.swisschain
                .sirius
                .vaultApi
                .generated
                .smart_contract_deployment_validation_requests
                .SmartContractDeploymentValidationRequestsOuterClass
                .ConfirmSmartContractDeploymentValidationRequestRequest
            request,
        io.grpc.stub.StreamObserver<
                io.swisschain
                    .sirius
                    .vaultApi
                    .generated
                    .smart_contract_deployment_validation_requests
                    .SmartContractDeploymentValidationRequestsOuterClass
                    .ConfirmSmartContractDeploymentValidationRequestResponse>
            responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getConfirmMethod(), getCallOptions()), request, responseObserver);
    }

    /** */
    public void reject(
        io.swisschain
                .sirius
                .vaultApi
                .generated
                .smart_contract_deployment_validation_requests
                .SmartContractDeploymentValidationRequestsOuterClass
                .RejectSmartContractDeploymentValidationRequestRequest
            request,
        io.grpc.stub.StreamObserver<
                io.swisschain
                    .sirius
                    .vaultApi
                    .generated
                    .smart_contract_deployment_validation_requests
                    .SmartContractDeploymentValidationRequestsOuterClass
                    .RejectSmartContractDeploymentValidationRequestResponse>
            responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRejectMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /** */
  public static final class SmartContractDeploymentValidationRequestsBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<
          SmartContractDeploymentValidationRequestsBlockingStub> {
    private SmartContractDeploymentValidationRequestsBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SmartContractDeploymentValidationRequestsBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new SmartContractDeploymentValidationRequestsBlockingStub(channel, callOptions);
    }

    /** */
    public io.swisschain
            .sirius
            .vaultApi
            .generated
            .smart_contract_deployment_validation_requests
            .SmartContractDeploymentValidationRequestsOuterClass
            .GetSmartContractDeploymentValidationRequestsResponse
        get(
            io.swisschain
                    .sirius
                    .vaultApi
                    .generated
                    .smart_contract_deployment_validation_requests
                    .SmartContractDeploymentValidationRequestsOuterClass
                    .GetSmartContractDeploymentValidationRequestsRequest
                request) {
      return blockingUnaryCall(getChannel(), getGetMethod(), getCallOptions(), request);
    }

    /** */
    public io.swisschain
            .sirius
            .vaultApi
            .generated
            .smart_contract_deployment_validation_requests
            .SmartContractDeploymentValidationRequestsOuterClass
            .ConfirmSmartContractDeploymentValidationRequestResponse
        confirm(
            io.swisschain
                    .sirius
                    .vaultApi
                    .generated
                    .smart_contract_deployment_validation_requests
                    .SmartContractDeploymentValidationRequestsOuterClass
                    .ConfirmSmartContractDeploymentValidationRequestRequest
                request) {
      return blockingUnaryCall(getChannel(), getConfirmMethod(), getCallOptions(), request);
    }

    /** */
    public io.swisschain
            .sirius
            .vaultApi
            .generated
            .smart_contract_deployment_validation_requests
            .SmartContractDeploymentValidationRequestsOuterClass
            .RejectSmartContractDeploymentValidationRequestResponse
        reject(
            io.swisschain
                    .sirius
                    .vaultApi
                    .generated
                    .smart_contract_deployment_validation_requests
                    .SmartContractDeploymentValidationRequestsOuterClass
                    .RejectSmartContractDeploymentValidationRequestRequest
                request) {
      return blockingUnaryCall(getChannel(), getRejectMethod(), getCallOptions(), request);
    }
  }

  /** */
  public static final class SmartContractDeploymentValidationRequestsFutureStub
      extends io.grpc.stub.AbstractFutureStub<SmartContractDeploymentValidationRequestsFutureStub> {
    private SmartContractDeploymentValidationRequestsFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SmartContractDeploymentValidationRequestsFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new SmartContractDeploymentValidationRequestsFutureStub(channel, callOptions);
    }

    /** */
    public com.google.common.util.concurrent.ListenableFuture<
            io.swisschain
                .sirius
                .vaultApi
                .generated
                .smart_contract_deployment_validation_requests
                .SmartContractDeploymentValidationRequestsOuterClass
                .GetSmartContractDeploymentValidationRequestsResponse>
        get(
            io.swisschain
                    .sirius
                    .vaultApi
                    .generated
                    .smart_contract_deployment_validation_requests
                    .SmartContractDeploymentValidationRequestsOuterClass
                    .GetSmartContractDeploymentValidationRequestsRequest
                request) {
      return futureUnaryCall(getChannel().newCall(getGetMethod(), getCallOptions()), request);
    }

    /** */
    public com.google.common.util.concurrent.ListenableFuture<
            io.swisschain
                .sirius
                .vaultApi
                .generated
                .smart_contract_deployment_validation_requests
                .SmartContractDeploymentValidationRequestsOuterClass
                .ConfirmSmartContractDeploymentValidationRequestResponse>
        confirm(
            io.swisschain
                    .sirius
                    .vaultApi
                    .generated
                    .smart_contract_deployment_validation_requests
                    .SmartContractDeploymentValidationRequestsOuterClass
                    .ConfirmSmartContractDeploymentValidationRequestRequest
                request) {
      return futureUnaryCall(getChannel().newCall(getConfirmMethod(), getCallOptions()), request);
    }

    /** */
    public com.google.common.util.concurrent.ListenableFuture<
            io.swisschain
                .sirius
                .vaultApi
                .generated
                .smart_contract_deployment_validation_requests
                .SmartContractDeploymentValidationRequestsOuterClass
                .RejectSmartContractDeploymentValidationRequestResponse>
        reject(
            io.swisschain
                    .sirius
                    .vaultApi
                    .generated
                    .smart_contract_deployment_validation_requests
                    .SmartContractDeploymentValidationRequestsOuterClass
                    .RejectSmartContractDeploymentValidationRequestRequest
                request) {
      return futureUnaryCall(getChannel().newCall(getRejectMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET = 0;
  private static final int METHODID_CONFIRM = 1;
  private static final int METHODID_REJECT = 2;

  private static final class MethodHandlers<Req, Resp>
      implements io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
          io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
          io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
          io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final SmartContractDeploymentValidationRequestsImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(SmartContractDeploymentValidationRequestsImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET:
          serviceImpl.get(
              (io.swisschain
                      .sirius
                      .vaultApi
                      .generated
                      .smart_contract_deployment_validation_requests
                      .SmartContractDeploymentValidationRequestsOuterClass
                      .GetSmartContractDeploymentValidationRequestsRequest)
                  request,
              (io.grpc.stub.StreamObserver<
                      io.swisschain
                          .sirius
                          .vaultApi
                          .generated
                          .smart_contract_deployment_validation_requests
                          .SmartContractDeploymentValidationRequestsOuterClass
                          .GetSmartContractDeploymentValidationRequestsResponse>)
                  responseObserver);
          break;
        case METHODID_CONFIRM:
          serviceImpl.confirm(
              (io.swisschain
                      .sirius
                      .vaultApi
                      .generated
                      .smart_contract_deployment_validation_requests
                      .SmartContractDeploymentValidationRequestsOuterClass
                      .ConfirmSmartContractDeploymentValidationRequestRequest)
                  request,
              (io.grpc.stub.StreamObserver<
                      io.swisschain
                          .sirius
                          .vaultApi
                          .generated
                          .smart_contract_deployment_validation_requests
                          .SmartContractDeploymentValidationRequestsOuterClass
                          .ConfirmSmartContractDeploymentValidationRequestResponse>)
                  responseObserver);
          break;
        case METHODID_REJECT:
          serviceImpl.reject(
              (io.swisschain
                      .sirius
                      .vaultApi
                      .generated
                      .smart_contract_deployment_validation_requests
                      .SmartContractDeploymentValidationRequestsOuterClass
                      .RejectSmartContractDeploymentValidationRequestRequest)
                  request,
              (io.grpc.stub.StreamObserver<
                      io.swisschain
                          .sirius
                          .vaultApi
                          .generated
                          .smart_contract_deployment_validation_requests
                          .SmartContractDeploymentValidationRequestsOuterClass
                          .RejectSmartContractDeploymentValidationRequestResponse>)
                  responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private abstract static class SmartContractDeploymentValidationRequestsBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier,
          io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    SmartContractDeploymentValidationRequestsBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return io.swisschain.sirius.vaultApi.generated.smart_contract_deployment_validation_requests
          .SmartContractDeploymentValidationRequestsOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("SmartContractDeploymentValidationRequests");
    }
  }

  private static final class SmartContractDeploymentValidationRequestsFileDescriptorSupplier
      extends SmartContractDeploymentValidationRequestsBaseDescriptorSupplier {
    SmartContractDeploymentValidationRequestsFileDescriptorSupplier() {}
  }

  private static final class SmartContractDeploymentValidationRequestsMethodDescriptorSupplier
      extends SmartContractDeploymentValidationRequestsBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    SmartContractDeploymentValidationRequestsMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (SmartContractDeploymentValidationRequestsGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor =
              result =
                  io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
                      .setSchemaDescriptor(
                          new SmartContractDeploymentValidationRequestsFileDescriptorSupplier())
                      .addMethod(getGetMethod())
                      .addMethod(getConfirmMethod())
                      .addMethod(getRejectMethod())
                      .build();
        }
      }
    }
    return result;
  }
}
