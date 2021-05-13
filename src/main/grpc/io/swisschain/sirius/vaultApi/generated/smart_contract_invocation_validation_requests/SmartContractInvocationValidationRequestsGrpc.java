package io.swisschain.sirius.vaultApi.generated.smart_contract_invocation_validation_requests;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/** */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.31.1)",
    comments = "Source: smart-contract-invocation-validation-requests.proto")
public final class SmartContractInvocationValidationRequestsGrpc {

  private SmartContractInvocationValidationRequestsGrpc() {}

  public static final String SERVICE_NAME =
      "swisschain.sirius.vaultApi.smartContractInvocationValidationRequests.SmartContractInvocationValidationRequests";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<
          io.swisschain
              .sirius
              .vaultApi
              .generated
              .smart_contract_invocation_validation_requests
              .SmartContractInvocationValidationRequestsOuterClass
              .GetSmartContractInvocationValidationRequestsRequest,
          io.swisschain
              .sirius
              .vaultApi
              .generated
              .smart_contract_invocation_validation_requests
              .SmartContractInvocationValidationRequestsOuterClass
              .GetSmartContractInvocationValidationRequestsResponse>
      getGetMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Get",
      requestType =
          io.swisschain
              .sirius
              .vaultApi
              .generated
              .smart_contract_invocation_validation_requests
              .SmartContractInvocationValidationRequestsOuterClass
              .GetSmartContractInvocationValidationRequestsRequest
              .class,
      responseType =
          io.swisschain
              .sirius
              .vaultApi
              .generated
              .smart_contract_invocation_validation_requests
              .SmartContractInvocationValidationRequestsOuterClass
              .GetSmartContractInvocationValidationRequestsResponse
              .class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<
          io.swisschain
              .sirius
              .vaultApi
              .generated
              .smart_contract_invocation_validation_requests
              .SmartContractInvocationValidationRequestsOuterClass
              .GetSmartContractInvocationValidationRequestsRequest,
          io.swisschain
              .sirius
              .vaultApi
              .generated
              .smart_contract_invocation_validation_requests
              .SmartContractInvocationValidationRequestsOuterClass
              .GetSmartContractInvocationValidationRequestsResponse>
      getGetMethod() {
    io.grpc.MethodDescriptor<
            io.swisschain
                .sirius
                .vaultApi
                .generated
                .smart_contract_invocation_validation_requests
                .SmartContractInvocationValidationRequestsOuterClass
                .GetSmartContractInvocationValidationRequestsRequest,
            io.swisschain
                .sirius
                .vaultApi
                .generated
                .smart_contract_invocation_validation_requests
                .SmartContractInvocationValidationRequestsOuterClass
                .GetSmartContractInvocationValidationRequestsResponse>
        getGetMethod;
    if ((getGetMethod = SmartContractInvocationValidationRequestsGrpc.getGetMethod) == null) {
      synchronized (SmartContractInvocationValidationRequestsGrpc.class) {
        if ((getGetMethod = SmartContractInvocationValidationRequestsGrpc.getGetMethod) == null) {
          SmartContractInvocationValidationRequestsGrpc.getGetMethod =
              getGetMethod =
                  io.grpc.MethodDescriptor
                      .<io.swisschain
                              .sirius
                              .vaultApi
                              .generated
                              .smart_contract_invocation_validation_requests
                              .SmartContractInvocationValidationRequestsOuterClass
                              .GetSmartContractInvocationValidationRequestsRequest,
                          io.swisschain
                              .sirius
                              .vaultApi
                              .generated
                              .smart_contract_invocation_validation_requests
                              .SmartContractInvocationValidationRequestsOuterClass
                              .GetSmartContractInvocationValidationRequestsResponse>
                          newBuilder()
                      .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                      .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Get"))
                      .setSampledToLocalTracing(true)
                      .setRequestMarshaller(
                          io.grpc.protobuf.ProtoUtils.marshaller(
                              io.swisschain.sirius.vaultApi.generated
                                  .smart_contract_invocation_validation_requests
                                  .SmartContractInvocationValidationRequestsOuterClass
                                  .GetSmartContractInvocationValidationRequestsRequest
                                  .getDefaultInstance()))
                      .setResponseMarshaller(
                          io.grpc.protobuf.ProtoUtils.marshaller(
                              io.swisschain.sirius.vaultApi.generated
                                  .smart_contract_invocation_validation_requests
                                  .SmartContractInvocationValidationRequestsOuterClass
                                  .GetSmartContractInvocationValidationRequestsResponse
                                  .getDefaultInstance()))
                      .setSchemaDescriptor(
                          new SmartContractInvocationValidationRequestsMethodDescriptorSupplier(
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
              .smart_contract_invocation_validation_requests
              .SmartContractInvocationValidationRequestsOuterClass
              .ConfirmSmartContractInvocationValidationRequestRequest,
          io.swisschain
              .sirius
              .vaultApi
              .generated
              .smart_contract_invocation_validation_requests
              .SmartContractInvocationValidationRequestsOuterClass
              .ConfirmSmartContractInvocationValidationRequestResponse>
      getConfirmMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Confirm",
      requestType =
          io.swisschain
              .sirius
              .vaultApi
              .generated
              .smart_contract_invocation_validation_requests
              .SmartContractInvocationValidationRequestsOuterClass
              .ConfirmSmartContractInvocationValidationRequestRequest
              .class,
      responseType =
          io.swisschain
              .sirius
              .vaultApi
              .generated
              .smart_contract_invocation_validation_requests
              .SmartContractInvocationValidationRequestsOuterClass
              .ConfirmSmartContractInvocationValidationRequestResponse
              .class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<
          io.swisschain
              .sirius
              .vaultApi
              .generated
              .smart_contract_invocation_validation_requests
              .SmartContractInvocationValidationRequestsOuterClass
              .ConfirmSmartContractInvocationValidationRequestRequest,
          io.swisschain
              .sirius
              .vaultApi
              .generated
              .smart_contract_invocation_validation_requests
              .SmartContractInvocationValidationRequestsOuterClass
              .ConfirmSmartContractInvocationValidationRequestResponse>
      getConfirmMethod() {
    io.grpc.MethodDescriptor<
            io.swisschain
                .sirius
                .vaultApi
                .generated
                .smart_contract_invocation_validation_requests
                .SmartContractInvocationValidationRequestsOuterClass
                .ConfirmSmartContractInvocationValidationRequestRequest,
            io.swisschain
                .sirius
                .vaultApi
                .generated
                .smart_contract_invocation_validation_requests
                .SmartContractInvocationValidationRequestsOuterClass
                .ConfirmSmartContractInvocationValidationRequestResponse>
        getConfirmMethod;
    if ((getConfirmMethod = SmartContractInvocationValidationRequestsGrpc.getConfirmMethod)
        == null) {
      synchronized (SmartContractInvocationValidationRequestsGrpc.class) {
        if ((getConfirmMethod = SmartContractInvocationValidationRequestsGrpc.getConfirmMethod)
            == null) {
          SmartContractInvocationValidationRequestsGrpc.getConfirmMethod =
              getConfirmMethod =
                  io.grpc.MethodDescriptor
                      .<io.swisschain
                              .sirius
                              .vaultApi
                              .generated
                              .smart_contract_invocation_validation_requests
                              .SmartContractInvocationValidationRequestsOuterClass
                              .ConfirmSmartContractInvocationValidationRequestRequest,
                          io.swisschain
                              .sirius
                              .vaultApi
                              .generated
                              .smart_contract_invocation_validation_requests
                              .SmartContractInvocationValidationRequestsOuterClass
                              .ConfirmSmartContractInvocationValidationRequestResponse>
                          newBuilder()
                      .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                      .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Confirm"))
                      .setSampledToLocalTracing(true)
                      .setRequestMarshaller(
                          io.grpc.protobuf.ProtoUtils.marshaller(
                              io.swisschain.sirius.vaultApi.generated
                                  .smart_contract_invocation_validation_requests
                                  .SmartContractInvocationValidationRequestsOuterClass
                                  .ConfirmSmartContractInvocationValidationRequestRequest
                                  .getDefaultInstance()))
                      .setResponseMarshaller(
                          io.grpc.protobuf.ProtoUtils.marshaller(
                              io.swisschain.sirius.vaultApi.generated
                                  .smart_contract_invocation_validation_requests
                                  .SmartContractInvocationValidationRequestsOuterClass
                                  .ConfirmSmartContractInvocationValidationRequestResponse
                                  .getDefaultInstance()))
                      .setSchemaDescriptor(
                          new SmartContractInvocationValidationRequestsMethodDescriptorSupplier(
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
              .smart_contract_invocation_validation_requests
              .SmartContractInvocationValidationRequestsOuterClass
              .RejectSmartContractInvocationValidationRequestRequest,
          io.swisschain
              .sirius
              .vaultApi
              .generated
              .smart_contract_invocation_validation_requests
              .SmartContractInvocationValidationRequestsOuterClass
              .RejectSmartContractInvocationValidationRequestResponse>
      getRejectMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Reject",
      requestType =
          io.swisschain
              .sirius
              .vaultApi
              .generated
              .smart_contract_invocation_validation_requests
              .SmartContractInvocationValidationRequestsOuterClass
              .RejectSmartContractInvocationValidationRequestRequest
              .class,
      responseType =
          io.swisschain
              .sirius
              .vaultApi
              .generated
              .smart_contract_invocation_validation_requests
              .SmartContractInvocationValidationRequestsOuterClass
              .RejectSmartContractInvocationValidationRequestResponse
              .class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<
          io.swisschain
              .sirius
              .vaultApi
              .generated
              .smart_contract_invocation_validation_requests
              .SmartContractInvocationValidationRequestsOuterClass
              .RejectSmartContractInvocationValidationRequestRequest,
          io.swisschain
              .sirius
              .vaultApi
              .generated
              .smart_contract_invocation_validation_requests
              .SmartContractInvocationValidationRequestsOuterClass
              .RejectSmartContractInvocationValidationRequestResponse>
      getRejectMethod() {
    io.grpc.MethodDescriptor<
            io.swisschain
                .sirius
                .vaultApi
                .generated
                .smart_contract_invocation_validation_requests
                .SmartContractInvocationValidationRequestsOuterClass
                .RejectSmartContractInvocationValidationRequestRequest,
            io.swisschain
                .sirius
                .vaultApi
                .generated
                .smart_contract_invocation_validation_requests
                .SmartContractInvocationValidationRequestsOuterClass
                .RejectSmartContractInvocationValidationRequestResponse>
        getRejectMethod;
    if ((getRejectMethod = SmartContractInvocationValidationRequestsGrpc.getRejectMethod) == null) {
      synchronized (SmartContractInvocationValidationRequestsGrpc.class) {
        if ((getRejectMethod = SmartContractInvocationValidationRequestsGrpc.getRejectMethod)
            == null) {
          SmartContractInvocationValidationRequestsGrpc.getRejectMethod =
              getRejectMethod =
                  io.grpc.MethodDescriptor
                      .<io.swisschain
                              .sirius
                              .vaultApi
                              .generated
                              .smart_contract_invocation_validation_requests
                              .SmartContractInvocationValidationRequestsOuterClass
                              .RejectSmartContractInvocationValidationRequestRequest,
                          io.swisschain
                              .sirius
                              .vaultApi
                              .generated
                              .smart_contract_invocation_validation_requests
                              .SmartContractInvocationValidationRequestsOuterClass
                              .RejectSmartContractInvocationValidationRequestResponse>
                          newBuilder()
                      .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                      .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Reject"))
                      .setSampledToLocalTracing(true)
                      .setRequestMarshaller(
                          io.grpc.protobuf.ProtoUtils.marshaller(
                              io.swisschain.sirius.vaultApi.generated
                                  .smart_contract_invocation_validation_requests
                                  .SmartContractInvocationValidationRequestsOuterClass
                                  .RejectSmartContractInvocationValidationRequestRequest
                                  .getDefaultInstance()))
                      .setResponseMarshaller(
                          io.grpc.protobuf.ProtoUtils.marshaller(
                              io.swisschain.sirius.vaultApi.generated
                                  .smart_contract_invocation_validation_requests
                                  .SmartContractInvocationValidationRequestsOuterClass
                                  .RejectSmartContractInvocationValidationRequestResponse
                                  .getDefaultInstance()))
                      .setSchemaDescriptor(
                          new SmartContractInvocationValidationRequestsMethodDescriptorSupplier(
                              "Reject"))
                      .build();
        }
      }
    }
    return getRejectMethod;
  }

  /** Creates a new async stub that supports all call types for the service */
  public static SmartContractInvocationValidationRequestsStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<SmartContractInvocationValidationRequestsStub> factory =
        new io.grpc.stub.AbstractStub.StubFactory<SmartContractInvocationValidationRequestsStub>() {
          @java.lang.Override
          public SmartContractInvocationValidationRequestsStub newStub(
              io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            return new SmartContractInvocationValidationRequestsStub(channel, callOptions);
          }
        };
    return SmartContractInvocationValidationRequestsStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static SmartContractInvocationValidationRequestsBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<SmartContractInvocationValidationRequestsBlockingStub>
        factory =
            new io.grpc.stub.AbstractStub.StubFactory<
                SmartContractInvocationValidationRequestsBlockingStub>() {
              @java.lang.Override
              public SmartContractInvocationValidationRequestsBlockingStub newStub(
                  io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
                return new SmartContractInvocationValidationRequestsBlockingStub(
                    channel, callOptions);
              }
            };
    return SmartContractInvocationValidationRequestsBlockingStub.newStub(factory, channel);
  }

  /** Creates a new ListenableFuture-style stub that supports unary calls on the service */
  public static SmartContractInvocationValidationRequestsFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<SmartContractInvocationValidationRequestsFutureStub>
        factory =
            new io.grpc.stub.AbstractStub.StubFactory<
                SmartContractInvocationValidationRequestsFutureStub>() {
              @java.lang.Override
              public SmartContractInvocationValidationRequestsFutureStub newStub(
                  io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
                return new SmartContractInvocationValidationRequestsFutureStub(
                    channel, callOptions);
              }
            };
    return SmartContractInvocationValidationRequestsFutureStub.newStub(factory, channel);
  }

  /** */
  public abstract static class SmartContractInvocationValidationRequestsImplBase
      implements io.grpc.BindableService {

    /** */
    public void get(
        io.swisschain
                .sirius
                .vaultApi
                .generated
                .smart_contract_invocation_validation_requests
                .SmartContractInvocationValidationRequestsOuterClass
                .GetSmartContractInvocationValidationRequestsRequest
            request,
        io.grpc.stub.StreamObserver<
                io.swisschain
                    .sirius
                    .vaultApi
                    .generated
                    .smart_contract_invocation_validation_requests
                    .SmartContractInvocationValidationRequestsOuterClass
                    .GetSmartContractInvocationValidationRequestsResponse>
            responseObserver) {
      asyncUnimplementedUnaryCall(getGetMethod(), responseObserver);
    }

    /** */
    public void confirm(
        io.swisschain
                .sirius
                .vaultApi
                .generated
                .smart_contract_invocation_validation_requests
                .SmartContractInvocationValidationRequestsOuterClass
                .ConfirmSmartContractInvocationValidationRequestRequest
            request,
        io.grpc.stub.StreamObserver<
                io.swisschain
                    .sirius
                    .vaultApi
                    .generated
                    .smart_contract_invocation_validation_requests
                    .SmartContractInvocationValidationRequestsOuterClass
                    .ConfirmSmartContractInvocationValidationRequestResponse>
            responseObserver) {
      asyncUnimplementedUnaryCall(getConfirmMethod(), responseObserver);
    }

    /** */
    public void reject(
        io.swisschain
                .sirius
                .vaultApi
                .generated
                .smart_contract_invocation_validation_requests
                .SmartContractInvocationValidationRequestsOuterClass
                .RejectSmartContractInvocationValidationRequestRequest
            request,
        io.grpc.stub.StreamObserver<
                io.swisschain
                    .sirius
                    .vaultApi
                    .generated
                    .smart_contract_invocation_validation_requests
                    .SmartContractInvocationValidationRequestsOuterClass
                    .RejectSmartContractInvocationValidationRequestResponse>
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
                          .smart_contract_invocation_validation_requests
                          .SmartContractInvocationValidationRequestsOuterClass
                          .GetSmartContractInvocationValidationRequestsRequest,
                      io.swisschain
                          .sirius
                          .vaultApi
                          .generated
                          .smart_contract_invocation_validation_requests
                          .SmartContractInvocationValidationRequestsOuterClass
                          .GetSmartContractInvocationValidationRequestsResponse>(
                      this, METHODID_GET)))
          .addMethod(
              getConfirmMethod(),
              asyncUnaryCall(
                  new MethodHandlers<
                      io.swisschain
                          .sirius
                          .vaultApi
                          .generated
                          .smart_contract_invocation_validation_requests
                          .SmartContractInvocationValidationRequestsOuterClass
                          .ConfirmSmartContractInvocationValidationRequestRequest,
                      io.swisschain
                          .sirius
                          .vaultApi
                          .generated
                          .smart_contract_invocation_validation_requests
                          .SmartContractInvocationValidationRequestsOuterClass
                          .ConfirmSmartContractInvocationValidationRequestResponse>(
                      this, METHODID_CONFIRM)))
          .addMethod(
              getRejectMethod(),
              asyncUnaryCall(
                  new MethodHandlers<
                      io.swisschain
                          .sirius
                          .vaultApi
                          .generated
                          .smart_contract_invocation_validation_requests
                          .SmartContractInvocationValidationRequestsOuterClass
                          .RejectSmartContractInvocationValidationRequestRequest,
                      io.swisschain
                          .sirius
                          .vaultApi
                          .generated
                          .smart_contract_invocation_validation_requests
                          .SmartContractInvocationValidationRequestsOuterClass
                          .RejectSmartContractInvocationValidationRequestResponse>(
                      this, METHODID_REJECT)))
          .build();
    }
  }

  /** */
  public static final class SmartContractInvocationValidationRequestsStub
      extends io.grpc.stub.AbstractAsyncStub<SmartContractInvocationValidationRequestsStub> {
    private SmartContractInvocationValidationRequestsStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SmartContractInvocationValidationRequestsStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new SmartContractInvocationValidationRequestsStub(channel, callOptions);
    }

    /** */
    public void get(
        io.swisschain
                .sirius
                .vaultApi
                .generated
                .smart_contract_invocation_validation_requests
                .SmartContractInvocationValidationRequestsOuterClass
                .GetSmartContractInvocationValidationRequestsRequest
            request,
        io.grpc.stub.StreamObserver<
                io.swisschain
                    .sirius
                    .vaultApi
                    .generated
                    .smart_contract_invocation_validation_requests
                    .SmartContractInvocationValidationRequestsOuterClass
                    .GetSmartContractInvocationValidationRequestsResponse>
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
                .smart_contract_invocation_validation_requests
                .SmartContractInvocationValidationRequestsOuterClass
                .ConfirmSmartContractInvocationValidationRequestRequest
            request,
        io.grpc.stub.StreamObserver<
                io.swisschain
                    .sirius
                    .vaultApi
                    .generated
                    .smart_contract_invocation_validation_requests
                    .SmartContractInvocationValidationRequestsOuterClass
                    .ConfirmSmartContractInvocationValidationRequestResponse>
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
                .smart_contract_invocation_validation_requests
                .SmartContractInvocationValidationRequestsOuterClass
                .RejectSmartContractInvocationValidationRequestRequest
            request,
        io.grpc.stub.StreamObserver<
                io.swisschain
                    .sirius
                    .vaultApi
                    .generated
                    .smart_contract_invocation_validation_requests
                    .SmartContractInvocationValidationRequestsOuterClass
                    .RejectSmartContractInvocationValidationRequestResponse>
            responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRejectMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /** */
  public static final class SmartContractInvocationValidationRequestsBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<
          SmartContractInvocationValidationRequestsBlockingStub> {
    private SmartContractInvocationValidationRequestsBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SmartContractInvocationValidationRequestsBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new SmartContractInvocationValidationRequestsBlockingStub(channel, callOptions);
    }

    /** */
    public io.swisschain
            .sirius
            .vaultApi
            .generated
            .smart_contract_invocation_validation_requests
            .SmartContractInvocationValidationRequestsOuterClass
            .GetSmartContractInvocationValidationRequestsResponse
        get(
            io.swisschain
                    .sirius
                    .vaultApi
                    .generated
                    .smart_contract_invocation_validation_requests
                    .SmartContractInvocationValidationRequestsOuterClass
                    .GetSmartContractInvocationValidationRequestsRequest
                request) {
      return blockingUnaryCall(getChannel(), getGetMethod(), getCallOptions(), request);
    }

    /** */
    public io.swisschain
            .sirius
            .vaultApi
            .generated
            .smart_contract_invocation_validation_requests
            .SmartContractInvocationValidationRequestsOuterClass
            .ConfirmSmartContractInvocationValidationRequestResponse
        confirm(
            io.swisschain
                    .sirius
                    .vaultApi
                    .generated
                    .smart_contract_invocation_validation_requests
                    .SmartContractInvocationValidationRequestsOuterClass
                    .ConfirmSmartContractInvocationValidationRequestRequest
                request) {
      return blockingUnaryCall(getChannel(), getConfirmMethod(), getCallOptions(), request);
    }

    /** */
    public io.swisschain
            .sirius
            .vaultApi
            .generated
            .smart_contract_invocation_validation_requests
            .SmartContractInvocationValidationRequestsOuterClass
            .RejectSmartContractInvocationValidationRequestResponse
        reject(
            io.swisschain
                    .sirius
                    .vaultApi
                    .generated
                    .smart_contract_invocation_validation_requests
                    .SmartContractInvocationValidationRequestsOuterClass
                    .RejectSmartContractInvocationValidationRequestRequest
                request) {
      return blockingUnaryCall(getChannel(), getRejectMethod(), getCallOptions(), request);
    }
  }

  /** */
  public static final class SmartContractInvocationValidationRequestsFutureStub
      extends io.grpc.stub.AbstractFutureStub<SmartContractInvocationValidationRequestsFutureStub> {
    private SmartContractInvocationValidationRequestsFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SmartContractInvocationValidationRequestsFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new SmartContractInvocationValidationRequestsFutureStub(channel, callOptions);
    }

    /** */
    public com.google.common.util.concurrent.ListenableFuture<
            io.swisschain
                .sirius
                .vaultApi
                .generated
                .smart_contract_invocation_validation_requests
                .SmartContractInvocationValidationRequestsOuterClass
                .GetSmartContractInvocationValidationRequestsResponse>
        get(
            io.swisschain
                    .sirius
                    .vaultApi
                    .generated
                    .smart_contract_invocation_validation_requests
                    .SmartContractInvocationValidationRequestsOuterClass
                    .GetSmartContractInvocationValidationRequestsRequest
                request) {
      return futureUnaryCall(getChannel().newCall(getGetMethod(), getCallOptions()), request);
    }

    /** */
    public com.google.common.util.concurrent.ListenableFuture<
            io.swisschain
                .sirius
                .vaultApi
                .generated
                .smart_contract_invocation_validation_requests
                .SmartContractInvocationValidationRequestsOuterClass
                .ConfirmSmartContractInvocationValidationRequestResponse>
        confirm(
            io.swisschain
                    .sirius
                    .vaultApi
                    .generated
                    .smart_contract_invocation_validation_requests
                    .SmartContractInvocationValidationRequestsOuterClass
                    .ConfirmSmartContractInvocationValidationRequestRequest
                request) {
      return futureUnaryCall(getChannel().newCall(getConfirmMethod(), getCallOptions()), request);
    }

    /** */
    public com.google.common.util.concurrent.ListenableFuture<
            io.swisschain
                .sirius
                .vaultApi
                .generated
                .smart_contract_invocation_validation_requests
                .SmartContractInvocationValidationRequestsOuterClass
                .RejectSmartContractInvocationValidationRequestResponse>
        reject(
            io.swisschain
                    .sirius
                    .vaultApi
                    .generated
                    .smart_contract_invocation_validation_requests
                    .SmartContractInvocationValidationRequestsOuterClass
                    .RejectSmartContractInvocationValidationRequestRequest
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
    private final SmartContractInvocationValidationRequestsImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(SmartContractInvocationValidationRequestsImplBase serviceImpl, int methodId) {
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
                      .smart_contract_invocation_validation_requests
                      .SmartContractInvocationValidationRequestsOuterClass
                      .GetSmartContractInvocationValidationRequestsRequest)
                  request,
              (io.grpc.stub.StreamObserver<
                      io.swisschain
                          .sirius
                          .vaultApi
                          .generated
                          .smart_contract_invocation_validation_requests
                          .SmartContractInvocationValidationRequestsOuterClass
                          .GetSmartContractInvocationValidationRequestsResponse>)
                  responseObserver);
          break;
        case METHODID_CONFIRM:
          serviceImpl.confirm(
              (io.swisschain
                      .sirius
                      .vaultApi
                      .generated
                      .smart_contract_invocation_validation_requests
                      .SmartContractInvocationValidationRequestsOuterClass
                      .ConfirmSmartContractInvocationValidationRequestRequest)
                  request,
              (io.grpc.stub.StreamObserver<
                      io.swisschain
                          .sirius
                          .vaultApi
                          .generated
                          .smart_contract_invocation_validation_requests
                          .SmartContractInvocationValidationRequestsOuterClass
                          .ConfirmSmartContractInvocationValidationRequestResponse>)
                  responseObserver);
          break;
        case METHODID_REJECT:
          serviceImpl.reject(
              (io.swisschain
                      .sirius
                      .vaultApi
                      .generated
                      .smart_contract_invocation_validation_requests
                      .SmartContractInvocationValidationRequestsOuterClass
                      .RejectSmartContractInvocationValidationRequestRequest)
                  request,
              (io.grpc.stub.StreamObserver<
                      io.swisschain
                          .sirius
                          .vaultApi
                          .generated
                          .smart_contract_invocation_validation_requests
                          .SmartContractInvocationValidationRequestsOuterClass
                          .RejectSmartContractInvocationValidationRequestResponse>)
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

  private abstract static class SmartContractInvocationValidationRequestsBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier,
          io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    SmartContractInvocationValidationRequestsBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return io.swisschain.sirius.vaultApi.generated.smart_contract_invocation_validation_requests
          .SmartContractInvocationValidationRequestsOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("SmartContractInvocationValidationRequests");
    }
  }

  private static final class SmartContractInvocationValidationRequestsFileDescriptorSupplier
      extends SmartContractInvocationValidationRequestsBaseDescriptorSupplier {
    SmartContractInvocationValidationRequestsFileDescriptorSupplier() {}
  }

  private static final class SmartContractInvocationValidationRequestsMethodDescriptorSupplier
      extends SmartContractInvocationValidationRequestsBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    SmartContractInvocationValidationRequestsMethodDescriptorSupplier(String methodName) {
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
      synchronized (SmartContractInvocationValidationRequestsGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor =
              result =
                  io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
                      .setSchemaDescriptor(
                          new SmartContractInvocationValidationRequestsFileDescriptorSupplier())
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
