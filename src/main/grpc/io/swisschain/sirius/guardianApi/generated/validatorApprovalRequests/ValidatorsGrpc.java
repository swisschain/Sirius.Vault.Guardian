package io.swisschain.sirius.guardianApi.generated.validatorApprovalRequests;

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

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.31.1)",
    comments = "Source: validator-approval-requests.proto")
public final class ValidatorsGrpc {

  private ValidatorsGrpc() {}

  public static final String SERVICE_NAME = "swisschain.sirius.GuardianValidatorApi.Validators";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<io.swisschain.sirius.guardianApi.generated.validatorApprovalRequests.ValidatorApprovalRequests.CreateApprovalRequestRequest,
      io.swisschain.sirius.guardianApi.generated.validatorApprovalRequests.ValidatorApprovalRequests.CreateApprovalRequestResponse> getCreateApprovalRequestMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreateApprovalRequest",
      requestType = io.swisschain.sirius.guardianApi.generated.validatorApprovalRequests.ValidatorApprovalRequests.CreateApprovalRequestRequest.class,
      responseType = io.swisschain.sirius.guardianApi.generated.validatorApprovalRequests.ValidatorApprovalRequests.CreateApprovalRequestResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<io.swisschain.sirius.guardianApi.generated.validatorApprovalRequests.ValidatorApprovalRequests.CreateApprovalRequestRequest,
      io.swisschain.sirius.guardianApi.generated.validatorApprovalRequests.ValidatorApprovalRequests.CreateApprovalRequestResponse> getCreateApprovalRequestMethod() {
    io.grpc.MethodDescriptor<io.swisschain.sirius.guardianApi.generated.validatorApprovalRequests.ValidatorApprovalRequests.CreateApprovalRequestRequest, io.swisschain.sirius.guardianApi.generated.validatorApprovalRequests.ValidatorApprovalRequests.CreateApprovalRequestResponse> getCreateApprovalRequestMethod;
    if ((getCreateApprovalRequestMethod = ValidatorsGrpc.getCreateApprovalRequestMethod) == null) {
      synchronized (ValidatorsGrpc.class) {
        if ((getCreateApprovalRequestMethod = ValidatorsGrpc.getCreateApprovalRequestMethod) == null) {
          ValidatorsGrpc.getCreateApprovalRequestMethod = getCreateApprovalRequestMethod =
              io.grpc.MethodDescriptor.<io.swisschain.sirius.guardianApi.generated.validatorApprovalRequests.ValidatorApprovalRequests.CreateApprovalRequestRequest, io.swisschain.sirius.guardianApi.generated.validatorApprovalRequests.ValidatorApprovalRequests.CreateApprovalRequestResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CreateApprovalRequest"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.swisschain.sirius.guardianApi.generated.validatorApprovalRequests.ValidatorApprovalRequests.CreateApprovalRequestRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.swisschain.sirius.guardianApi.generated.validatorApprovalRequests.ValidatorApprovalRequests.CreateApprovalRequestResponse.getDefaultInstance()))
              .setSchemaDescriptor(new ValidatorsMethodDescriptorSupplier("CreateApprovalRequest"))
              .build();
        }
      }
    }
    return getCreateApprovalRequestMethod;
  }

  private static volatile io.grpc.MethodDescriptor<io.swisschain.sirius.guardianApi.generated.validatorApprovalRequests.ValidatorApprovalRequests.GetApprovalResultsRequest,
      io.swisschain.sirius.guardianApi.generated.validatorApprovalRequests.ValidatorApprovalRequests.GetApprovalResponse> getGetApprovalResultsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetApprovalResults",
      requestType = io.swisschain.sirius.guardianApi.generated.validatorApprovalRequests.ValidatorApprovalRequests.GetApprovalResultsRequest.class,
      responseType = io.swisschain.sirius.guardianApi.generated.validatorApprovalRequests.ValidatorApprovalRequests.GetApprovalResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<io.swisschain.sirius.guardianApi.generated.validatorApprovalRequests.ValidatorApprovalRequests.GetApprovalResultsRequest,
      io.swisschain.sirius.guardianApi.generated.validatorApprovalRequests.ValidatorApprovalRequests.GetApprovalResponse> getGetApprovalResultsMethod() {
    io.grpc.MethodDescriptor<io.swisschain.sirius.guardianApi.generated.validatorApprovalRequests.ValidatorApprovalRequests.GetApprovalResultsRequest, io.swisschain.sirius.guardianApi.generated.validatorApprovalRequests.ValidatorApprovalRequests.GetApprovalResponse> getGetApprovalResultsMethod;
    if ((getGetApprovalResultsMethod = ValidatorsGrpc.getGetApprovalResultsMethod) == null) {
      synchronized (ValidatorsGrpc.class) {
        if ((getGetApprovalResultsMethod = ValidatorsGrpc.getGetApprovalResultsMethod) == null) {
          ValidatorsGrpc.getGetApprovalResultsMethod = getGetApprovalResultsMethod =
              io.grpc.MethodDescriptor.<io.swisschain.sirius.guardianApi.generated.validatorApprovalRequests.ValidatorApprovalRequests.GetApprovalResultsRequest, io.swisschain.sirius.guardianApi.generated.validatorApprovalRequests.ValidatorApprovalRequests.GetApprovalResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetApprovalResults"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.swisschain.sirius.guardianApi.generated.validatorApprovalRequests.ValidatorApprovalRequests.GetApprovalResultsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.swisschain.sirius.guardianApi.generated.validatorApprovalRequests.ValidatorApprovalRequests.GetApprovalResponse.getDefaultInstance()))
              .setSchemaDescriptor(new ValidatorsMethodDescriptorSupplier("GetApprovalResults"))
              .build();
        }
      }
    }
    return getGetApprovalResultsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<io.swisschain.sirius.guardianApi.generated.validatorApprovalRequests.ValidatorApprovalRequests.AcknowledgeResultRequest,
      io.swisschain.sirius.guardianApi.generated.validatorApprovalRequests.ValidatorApprovalRequests.AcknowledgeResultResponse> getAcknowledgeResultMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "AcknowledgeResult",
      requestType = io.swisschain.sirius.guardianApi.generated.validatorApprovalRequests.ValidatorApprovalRequests.AcknowledgeResultRequest.class,
      responseType = io.swisschain.sirius.guardianApi.generated.validatorApprovalRequests.ValidatorApprovalRequests.AcknowledgeResultResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<io.swisschain.sirius.guardianApi.generated.validatorApprovalRequests.ValidatorApprovalRequests.AcknowledgeResultRequest,
      io.swisschain.sirius.guardianApi.generated.validatorApprovalRequests.ValidatorApprovalRequests.AcknowledgeResultResponse> getAcknowledgeResultMethod() {
    io.grpc.MethodDescriptor<io.swisschain.sirius.guardianApi.generated.validatorApprovalRequests.ValidatorApprovalRequests.AcknowledgeResultRequest, io.swisschain.sirius.guardianApi.generated.validatorApprovalRequests.ValidatorApprovalRequests.AcknowledgeResultResponse> getAcknowledgeResultMethod;
    if ((getAcknowledgeResultMethod = ValidatorsGrpc.getAcknowledgeResultMethod) == null) {
      synchronized (ValidatorsGrpc.class) {
        if ((getAcknowledgeResultMethod = ValidatorsGrpc.getAcknowledgeResultMethod) == null) {
          ValidatorsGrpc.getAcknowledgeResultMethod = getAcknowledgeResultMethod =
              io.grpc.MethodDescriptor.<io.swisschain.sirius.guardianApi.generated.validatorApprovalRequests.ValidatorApprovalRequests.AcknowledgeResultRequest, io.swisschain.sirius.guardianApi.generated.validatorApprovalRequests.ValidatorApprovalRequests.AcknowledgeResultResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "AcknowledgeResult"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.swisschain.sirius.guardianApi.generated.validatorApprovalRequests.ValidatorApprovalRequests.AcknowledgeResultRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.swisschain.sirius.guardianApi.generated.validatorApprovalRequests.ValidatorApprovalRequests.AcknowledgeResultResponse.getDefaultInstance()))
              .setSchemaDescriptor(new ValidatorsMethodDescriptorSupplier("AcknowledgeResult"))
              .build();
        }
      }
    }
    return getAcknowledgeResultMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static ValidatorsStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ValidatorsStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ValidatorsStub>() {
        @java.lang.Override
        public ValidatorsStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ValidatorsStub(channel, callOptions);
        }
      };
    return ValidatorsStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static ValidatorsBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ValidatorsBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ValidatorsBlockingStub>() {
        @java.lang.Override
        public ValidatorsBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ValidatorsBlockingStub(channel, callOptions);
        }
      };
    return ValidatorsBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static ValidatorsFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ValidatorsFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ValidatorsFutureStub>() {
        @java.lang.Override
        public ValidatorsFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ValidatorsFutureStub(channel, callOptions);
        }
      };
    return ValidatorsFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class ValidatorsImplBase implements io.grpc.BindableService {

    /**
     */
    public void createApprovalRequest(io.swisschain.sirius.guardianApi.generated.validatorApprovalRequests.ValidatorApprovalRequests.CreateApprovalRequestRequest request,
        io.grpc.stub.StreamObserver<io.swisschain.sirius.guardianApi.generated.validatorApprovalRequests.ValidatorApprovalRequests.CreateApprovalRequestResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getCreateApprovalRequestMethod(), responseObserver);
    }

    /**
     */
    public void getApprovalResults(io.swisschain.sirius.guardianApi.generated.validatorApprovalRequests.ValidatorApprovalRequests.GetApprovalResultsRequest request,
        io.grpc.stub.StreamObserver<io.swisschain.sirius.guardianApi.generated.validatorApprovalRequests.ValidatorApprovalRequests.GetApprovalResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGetApprovalResultsMethod(), responseObserver);
    }

    /**
     */
    public void acknowledgeResult(io.swisschain.sirius.guardianApi.generated.validatorApprovalRequests.ValidatorApprovalRequests.AcknowledgeResultRequest request,
        io.grpc.stub.StreamObserver<io.swisschain.sirius.guardianApi.generated.validatorApprovalRequests.ValidatorApprovalRequests.AcknowledgeResultResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getAcknowledgeResultMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getCreateApprovalRequestMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                io.swisschain.sirius.guardianApi.generated.validatorApprovalRequests.ValidatorApprovalRequests.CreateApprovalRequestRequest,
                io.swisschain.sirius.guardianApi.generated.validatorApprovalRequests.ValidatorApprovalRequests.CreateApprovalRequestResponse>(
                  this, METHODID_CREATE_APPROVAL_REQUEST)))
          .addMethod(
            getGetApprovalResultsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                io.swisschain.sirius.guardianApi.generated.validatorApprovalRequests.ValidatorApprovalRequests.GetApprovalResultsRequest,
                io.swisschain.sirius.guardianApi.generated.validatorApprovalRequests.ValidatorApprovalRequests.GetApprovalResponse>(
                  this, METHODID_GET_APPROVAL_RESULTS)))
          .addMethod(
            getAcknowledgeResultMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                io.swisschain.sirius.guardianApi.generated.validatorApprovalRequests.ValidatorApprovalRequests.AcknowledgeResultRequest,
                io.swisschain.sirius.guardianApi.generated.validatorApprovalRequests.ValidatorApprovalRequests.AcknowledgeResultResponse>(
                  this, METHODID_ACKNOWLEDGE_RESULT)))
          .build();
    }
  }

  /**
   */
  public static final class ValidatorsStub extends io.grpc.stub.AbstractAsyncStub<ValidatorsStub> {
    private ValidatorsStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ValidatorsStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ValidatorsStub(channel, callOptions);
    }

    /**
     */
    public void createApprovalRequest(io.swisschain.sirius.guardianApi.generated.validatorApprovalRequests.ValidatorApprovalRequests.CreateApprovalRequestRequest request,
        io.grpc.stub.StreamObserver<io.swisschain.sirius.guardianApi.generated.validatorApprovalRequests.ValidatorApprovalRequests.CreateApprovalRequestResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCreateApprovalRequestMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getApprovalResults(io.swisschain.sirius.guardianApi.generated.validatorApprovalRequests.ValidatorApprovalRequests.GetApprovalResultsRequest request,
        io.grpc.stub.StreamObserver<io.swisschain.sirius.guardianApi.generated.validatorApprovalRequests.ValidatorApprovalRequests.GetApprovalResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetApprovalResultsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void acknowledgeResult(io.swisschain.sirius.guardianApi.generated.validatorApprovalRequests.ValidatorApprovalRequests.AcknowledgeResultRequest request,
        io.grpc.stub.StreamObserver<io.swisschain.sirius.guardianApi.generated.validatorApprovalRequests.ValidatorApprovalRequests.AcknowledgeResultResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getAcknowledgeResultMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class ValidatorsBlockingStub extends io.grpc.stub.AbstractBlockingStub<ValidatorsBlockingStub> {
    private ValidatorsBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ValidatorsBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ValidatorsBlockingStub(channel, callOptions);
    }

    /**
     */
    public io.swisschain.sirius.guardianApi.generated.validatorApprovalRequests.ValidatorApprovalRequests.CreateApprovalRequestResponse createApprovalRequest(io.swisschain.sirius.guardianApi.generated.validatorApprovalRequests.ValidatorApprovalRequests.CreateApprovalRequestRequest request) {
      return blockingUnaryCall(
          getChannel(), getCreateApprovalRequestMethod(), getCallOptions(), request);
    }

    /**
     */
    public io.swisschain.sirius.guardianApi.generated.validatorApprovalRequests.ValidatorApprovalRequests.GetApprovalResponse getApprovalResults(io.swisschain.sirius.guardianApi.generated.validatorApprovalRequests.ValidatorApprovalRequests.GetApprovalResultsRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetApprovalResultsMethod(), getCallOptions(), request);
    }

    /**
     */
    public io.swisschain.sirius.guardianApi.generated.validatorApprovalRequests.ValidatorApprovalRequests.AcknowledgeResultResponse acknowledgeResult(io.swisschain.sirius.guardianApi.generated.validatorApprovalRequests.ValidatorApprovalRequests.AcknowledgeResultRequest request) {
      return blockingUnaryCall(
          getChannel(), getAcknowledgeResultMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class ValidatorsFutureStub extends io.grpc.stub.AbstractFutureStub<ValidatorsFutureStub> {
    private ValidatorsFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ValidatorsFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ValidatorsFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<io.swisschain.sirius.guardianApi.generated.validatorApprovalRequests.ValidatorApprovalRequests.CreateApprovalRequestResponse> createApprovalRequest(
        io.swisschain.sirius.guardianApi.generated.validatorApprovalRequests.ValidatorApprovalRequests.CreateApprovalRequestRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getCreateApprovalRequestMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<io.swisschain.sirius.guardianApi.generated.validatorApprovalRequests.ValidatorApprovalRequests.GetApprovalResponse> getApprovalResults(
        io.swisschain.sirius.guardianApi.generated.validatorApprovalRequests.ValidatorApprovalRequests.GetApprovalResultsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetApprovalResultsMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<io.swisschain.sirius.guardianApi.generated.validatorApprovalRequests.ValidatorApprovalRequests.AcknowledgeResultResponse> acknowledgeResult(
        io.swisschain.sirius.guardianApi.generated.validatorApprovalRequests.ValidatorApprovalRequests.AcknowledgeResultRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getAcknowledgeResultMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CREATE_APPROVAL_REQUEST = 0;
  private static final int METHODID_GET_APPROVAL_RESULTS = 1;
  private static final int METHODID_ACKNOWLEDGE_RESULT = 2;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final ValidatorsImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(ValidatorsImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_CREATE_APPROVAL_REQUEST:
          serviceImpl.createApprovalRequest((io.swisschain.sirius.guardianApi.generated.validatorApprovalRequests.ValidatorApprovalRequests.CreateApprovalRequestRequest) request,
              (io.grpc.stub.StreamObserver<io.swisschain.sirius.guardianApi.generated.validatorApprovalRequests.ValidatorApprovalRequests.CreateApprovalRequestResponse>) responseObserver);
          break;
        case METHODID_GET_APPROVAL_RESULTS:
          serviceImpl.getApprovalResults((io.swisschain.sirius.guardianApi.generated.validatorApprovalRequests.ValidatorApprovalRequests.GetApprovalResultsRequest) request,
              (io.grpc.stub.StreamObserver<io.swisschain.sirius.guardianApi.generated.validatorApprovalRequests.ValidatorApprovalRequests.GetApprovalResponse>) responseObserver);
          break;
        case METHODID_ACKNOWLEDGE_RESULT:
          serviceImpl.acknowledgeResult((io.swisschain.sirius.guardianApi.generated.validatorApprovalRequests.ValidatorApprovalRequests.AcknowledgeResultRequest) request,
              (io.grpc.stub.StreamObserver<io.swisschain.sirius.guardianApi.generated.validatorApprovalRequests.ValidatorApprovalRequests.AcknowledgeResultResponse>) responseObserver);
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

  private static abstract class ValidatorsBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    ValidatorsBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return io.swisschain.sirius.guardianApi.generated.validatorApprovalRequests.ValidatorApprovalRequests.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("Validators");
    }
  }

  private static final class ValidatorsFileDescriptorSupplier
      extends ValidatorsBaseDescriptorSupplier {
    ValidatorsFileDescriptorSupplier() {}
  }

  private static final class ValidatorsMethodDescriptorSupplier
      extends ValidatorsBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    ValidatorsMethodDescriptorSupplier(String methodName) {
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
      synchronized (ValidatorsGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new ValidatorsFileDescriptorSupplier())
              .addMethod(getCreateApprovalRequestMethod())
              .addMethod(getGetApprovalResultsMethod())
              .addMethod(getAcknowledgeResultMethod())
              .build();
        }
      }
    }
    return result;
  }
}
