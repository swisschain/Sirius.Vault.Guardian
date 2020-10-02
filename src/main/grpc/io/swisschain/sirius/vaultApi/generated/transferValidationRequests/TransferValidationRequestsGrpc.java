package io.swisschain.sirius.vaultApi.generated.transferValidationRequests;

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
    comments = "Source: transfer-validation-requests.proto")
public final class TransferValidationRequestsGrpc {

  private TransferValidationRequestsGrpc() {}

  public static final String SERVICE_NAME = "swisschain.sirius.vaultApi.transferValidationRequests.TransferValidationRequests";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<io.swisschain.sirius.vaultApi.generated.transferValidationRequests.TransferValidationRequestsOuterClass.GetTransferValidationRequestsRequest,
      io.swisschain.sirius.vaultApi.generated.transferValidationRequests.TransferValidationRequestsOuterClass.GetTransferValidationRequestsResponse> getGetMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Get",
      requestType = io.swisschain.sirius.vaultApi.generated.transferValidationRequests.TransferValidationRequestsOuterClass.GetTransferValidationRequestsRequest.class,
      responseType = io.swisschain.sirius.vaultApi.generated.transferValidationRequests.TransferValidationRequestsOuterClass.GetTransferValidationRequestsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<io.swisschain.sirius.vaultApi.generated.transferValidationRequests.TransferValidationRequestsOuterClass.GetTransferValidationRequestsRequest,
      io.swisschain.sirius.vaultApi.generated.transferValidationRequests.TransferValidationRequestsOuterClass.GetTransferValidationRequestsResponse> getGetMethod() {
    io.grpc.MethodDescriptor<io.swisschain.sirius.vaultApi.generated.transferValidationRequests.TransferValidationRequestsOuterClass.GetTransferValidationRequestsRequest, io.swisschain.sirius.vaultApi.generated.transferValidationRequests.TransferValidationRequestsOuterClass.GetTransferValidationRequestsResponse> getGetMethod;
    if ((getGetMethod = TransferValidationRequestsGrpc.getGetMethod) == null) {
      synchronized (TransferValidationRequestsGrpc.class) {
        if ((getGetMethod = TransferValidationRequestsGrpc.getGetMethod) == null) {
          TransferValidationRequestsGrpc.getGetMethod = getGetMethod =
              io.grpc.MethodDescriptor.<io.swisschain.sirius.vaultApi.generated.transferValidationRequests.TransferValidationRequestsOuterClass.GetTransferValidationRequestsRequest, io.swisschain.sirius.vaultApi.generated.transferValidationRequests.TransferValidationRequestsOuterClass.GetTransferValidationRequestsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Get"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.swisschain.sirius.vaultApi.generated.transferValidationRequests.TransferValidationRequestsOuterClass.GetTransferValidationRequestsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.swisschain.sirius.vaultApi.generated.transferValidationRequests.TransferValidationRequestsOuterClass.GetTransferValidationRequestsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new TransferValidationRequestsMethodDescriptorSupplier("Get"))
              .build();
        }
      }
    }
    return getGetMethod;
  }

  private static volatile io.grpc.MethodDescriptor<io.swisschain.sirius.vaultApi.generated.transferValidationRequests.TransferValidationRequestsOuterClass.ConfirmTransferValidationRequestRequest,
      io.swisschain.sirius.vaultApi.generated.transferValidationRequests.TransferValidationRequestsOuterClass.ConfirmTransferValidationRequestResponse> getConfirmMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Confirm",
      requestType = io.swisschain.sirius.vaultApi.generated.transferValidationRequests.TransferValidationRequestsOuterClass.ConfirmTransferValidationRequestRequest.class,
      responseType = io.swisschain.sirius.vaultApi.generated.transferValidationRequests.TransferValidationRequestsOuterClass.ConfirmTransferValidationRequestResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<io.swisschain.sirius.vaultApi.generated.transferValidationRequests.TransferValidationRequestsOuterClass.ConfirmTransferValidationRequestRequest,
      io.swisschain.sirius.vaultApi.generated.transferValidationRequests.TransferValidationRequestsOuterClass.ConfirmTransferValidationRequestResponse> getConfirmMethod() {
    io.grpc.MethodDescriptor<io.swisschain.sirius.vaultApi.generated.transferValidationRequests.TransferValidationRequestsOuterClass.ConfirmTransferValidationRequestRequest, io.swisschain.sirius.vaultApi.generated.transferValidationRequests.TransferValidationRequestsOuterClass.ConfirmTransferValidationRequestResponse> getConfirmMethod;
    if ((getConfirmMethod = TransferValidationRequestsGrpc.getConfirmMethod) == null) {
      synchronized (TransferValidationRequestsGrpc.class) {
        if ((getConfirmMethod = TransferValidationRequestsGrpc.getConfirmMethod) == null) {
          TransferValidationRequestsGrpc.getConfirmMethod = getConfirmMethod =
              io.grpc.MethodDescriptor.<io.swisschain.sirius.vaultApi.generated.transferValidationRequests.TransferValidationRequestsOuterClass.ConfirmTransferValidationRequestRequest, io.swisschain.sirius.vaultApi.generated.transferValidationRequests.TransferValidationRequestsOuterClass.ConfirmTransferValidationRequestResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Confirm"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.swisschain.sirius.vaultApi.generated.transferValidationRequests.TransferValidationRequestsOuterClass.ConfirmTransferValidationRequestRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.swisschain.sirius.vaultApi.generated.transferValidationRequests.TransferValidationRequestsOuterClass.ConfirmTransferValidationRequestResponse.getDefaultInstance()))
              .setSchemaDescriptor(new TransferValidationRequestsMethodDescriptorSupplier("Confirm"))
              .build();
        }
      }
    }
    return getConfirmMethod;
  }

  private static volatile io.grpc.MethodDescriptor<io.swisschain.sirius.vaultApi.generated.transferValidationRequests.TransferValidationRequestsOuterClass.RejectTransferValidationRequestRequest,
      io.swisschain.sirius.vaultApi.generated.transferValidationRequests.TransferValidationRequestsOuterClass.RejectTransferValidationRequestResponse> getRejectMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Reject",
      requestType = io.swisschain.sirius.vaultApi.generated.transferValidationRequests.TransferValidationRequestsOuterClass.RejectTransferValidationRequestRequest.class,
      responseType = io.swisschain.sirius.vaultApi.generated.transferValidationRequests.TransferValidationRequestsOuterClass.RejectTransferValidationRequestResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<io.swisschain.sirius.vaultApi.generated.transferValidationRequests.TransferValidationRequestsOuterClass.RejectTransferValidationRequestRequest,
      io.swisschain.sirius.vaultApi.generated.transferValidationRequests.TransferValidationRequestsOuterClass.RejectTransferValidationRequestResponse> getRejectMethod() {
    io.grpc.MethodDescriptor<io.swisschain.sirius.vaultApi.generated.transferValidationRequests.TransferValidationRequestsOuterClass.RejectTransferValidationRequestRequest, io.swisschain.sirius.vaultApi.generated.transferValidationRequests.TransferValidationRequestsOuterClass.RejectTransferValidationRequestResponse> getRejectMethod;
    if ((getRejectMethod = TransferValidationRequestsGrpc.getRejectMethod) == null) {
      synchronized (TransferValidationRequestsGrpc.class) {
        if ((getRejectMethod = TransferValidationRequestsGrpc.getRejectMethod) == null) {
          TransferValidationRequestsGrpc.getRejectMethod = getRejectMethod =
              io.grpc.MethodDescriptor.<io.swisschain.sirius.vaultApi.generated.transferValidationRequests.TransferValidationRequestsOuterClass.RejectTransferValidationRequestRequest, io.swisschain.sirius.vaultApi.generated.transferValidationRequests.TransferValidationRequestsOuterClass.RejectTransferValidationRequestResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Reject"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.swisschain.sirius.vaultApi.generated.transferValidationRequests.TransferValidationRequestsOuterClass.RejectTransferValidationRequestRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.swisschain.sirius.vaultApi.generated.transferValidationRequests.TransferValidationRequestsOuterClass.RejectTransferValidationRequestResponse.getDefaultInstance()))
              .setSchemaDescriptor(new TransferValidationRequestsMethodDescriptorSupplier("Reject"))
              .build();
        }
      }
    }
    return getRejectMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static TransferValidationRequestsStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<TransferValidationRequestsStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<TransferValidationRequestsStub>() {
        @java.lang.Override
        public TransferValidationRequestsStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new TransferValidationRequestsStub(channel, callOptions);
        }
      };
    return TransferValidationRequestsStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static TransferValidationRequestsBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<TransferValidationRequestsBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<TransferValidationRequestsBlockingStub>() {
        @java.lang.Override
        public TransferValidationRequestsBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new TransferValidationRequestsBlockingStub(channel, callOptions);
        }
      };
    return TransferValidationRequestsBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static TransferValidationRequestsFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<TransferValidationRequestsFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<TransferValidationRequestsFutureStub>() {
        @java.lang.Override
        public TransferValidationRequestsFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new TransferValidationRequestsFutureStub(channel, callOptions);
        }
      };
    return TransferValidationRequestsFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class TransferValidationRequestsImplBase implements io.grpc.BindableService {

    /**
     */
    public void get(io.swisschain.sirius.vaultApi.generated.transferValidationRequests.TransferValidationRequestsOuterClass.GetTransferValidationRequestsRequest request,
        io.grpc.stub.StreamObserver<io.swisschain.sirius.vaultApi.generated.transferValidationRequests.TransferValidationRequestsOuterClass.GetTransferValidationRequestsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGetMethod(), responseObserver);
    }

    /**
     */
    public void confirm(io.swisschain.sirius.vaultApi.generated.transferValidationRequests.TransferValidationRequestsOuterClass.ConfirmTransferValidationRequestRequest request,
        io.grpc.stub.StreamObserver<io.swisschain.sirius.vaultApi.generated.transferValidationRequests.TransferValidationRequestsOuterClass.ConfirmTransferValidationRequestResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getConfirmMethod(), responseObserver);
    }

    /**
     */
    public void reject(io.swisschain.sirius.vaultApi.generated.transferValidationRequests.TransferValidationRequestsOuterClass.RejectTransferValidationRequestRequest request,
        io.grpc.stub.StreamObserver<io.swisschain.sirius.vaultApi.generated.transferValidationRequests.TransferValidationRequestsOuterClass.RejectTransferValidationRequestResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getRejectMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getGetMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                io.swisschain.sirius.vaultApi.generated.transferValidationRequests.TransferValidationRequestsOuterClass.GetTransferValidationRequestsRequest,
                io.swisschain.sirius.vaultApi.generated.transferValidationRequests.TransferValidationRequestsOuterClass.GetTransferValidationRequestsResponse>(
                  this, METHODID_GET)))
          .addMethod(
            getConfirmMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                io.swisschain.sirius.vaultApi.generated.transferValidationRequests.TransferValidationRequestsOuterClass.ConfirmTransferValidationRequestRequest,
                io.swisschain.sirius.vaultApi.generated.transferValidationRequests.TransferValidationRequestsOuterClass.ConfirmTransferValidationRequestResponse>(
                  this, METHODID_CONFIRM)))
          .addMethod(
            getRejectMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                io.swisschain.sirius.vaultApi.generated.transferValidationRequests.TransferValidationRequestsOuterClass.RejectTransferValidationRequestRequest,
                io.swisschain.sirius.vaultApi.generated.transferValidationRequests.TransferValidationRequestsOuterClass.RejectTransferValidationRequestResponse>(
                  this, METHODID_REJECT)))
          .build();
    }
  }

  /**
   */
  public static final class TransferValidationRequestsStub extends io.grpc.stub.AbstractAsyncStub<TransferValidationRequestsStub> {
    private TransferValidationRequestsStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TransferValidationRequestsStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new TransferValidationRequestsStub(channel, callOptions);
    }

    /**
     */
    public void get(io.swisschain.sirius.vaultApi.generated.transferValidationRequests.TransferValidationRequestsOuterClass.GetTransferValidationRequestsRequest request,
        io.grpc.stub.StreamObserver<io.swisschain.sirius.vaultApi.generated.transferValidationRequests.TransferValidationRequestsOuterClass.GetTransferValidationRequestsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void confirm(io.swisschain.sirius.vaultApi.generated.transferValidationRequests.TransferValidationRequestsOuterClass.ConfirmTransferValidationRequestRequest request,
        io.grpc.stub.StreamObserver<io.swisschain.sirius.vaultApi.generated.transferValidationRequests.TransferValidationRequestsOuterClass.ConfirmTransferValidationRequestResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getConfirmMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void reject(io.swisschain.sirius.vaultApi.generated.transferValidationRequests.TransferValidationRequestsOuterClass.RejectTransferValidationRequestRequest request,
        io.grpc.stub.StreamObserver<io.swisschain.sirius.vaultApi.generated.transferValidationRequests.TransferValidationRequestsOuterClass.RejectTransferValidationRequestResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRejectMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class TransferValidationRequestsBlockingStub extends io.grpc.stub.AbstractBlockingStub<TransferValidationRequestsBlockingStub> {
    private TransferValidationRequestsBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TransferValidationRequestsBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new TransferValidationRequestsBlockingStub(channel, callOptions);
    }

    /**
     */
    public io.swisschain.sirius.vaultApi.generated.transferValidationRequests.TransferValidationRequestsOuterClass.GetTransferValidationRequestsResponse get(io.swisschain.sirius.vaultApi.generated.transferValidationRequests.TransferValidationRequestsOuterClass.GetTransferValidationRequestsRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetMethod(), getCallOptions(), request);
    }

    /**
     */
    public io.swisschain.sirius.vaultApi.generated.transferValidationRequests.TransferValidationRequestsOuterClass.ConfirmTransferValidationRequestResponse confirm(io.swisschain.sirius.vaultApi.generated.transferValidationRequests.TransferValidationRequestsOuterClass.ConfirmTransferValidationRequestRequest request) {
      return blockingUnaryCall(
          getChannel(), getConfirmMethod(), getCallOptions(), request);
    }

    /**
     */
    public io.swisschain.sirius.vaultApi.generated.transferValidationRequests.TransferValidationRequestsOuterClass.RejectTransferValidationRequestResponse reject(io.swisschain.sirius.vaultApi.generated.transferValidationRequests.TransferValidationRequestsOuterClass.RejectTransferValidationRequestRequest request) {
      return blockingUnaryCall(
          getChannel(), getRejectMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class TransferValidationRequestsFutureStub extends io.grpc.stub.AbstractFutureStub<TransferValidationRequestsFutureStub> {
    private TransferValidationRequestsFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TransferValidationRequestsFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new TransferValidationRequestsFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<io.swisschain.sirius.vaultApi.generated.transferValidationRequests.TransferValidationRequestsOuterClass.GetTransferValidationRequestsResponse> get(
        io.swisschain.sirius.vaultApi.generated.transferValidationRequests.TransferValidationRequestsOuterClass.GetTransferValidationRequestsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<io.swisschain.sirius.vaultApi.generated.transferValidationRequests.TransferValidationRequestsOuterClass.ConfirmTransferValidationRequestResponse> confirm(
        io.swisschain.sirius.vaultApi.generated.transferValidationRequests.TransferValidationRequestsOuterClass.ConfirmTransferValidationRequestRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getConfirmMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<io.swisschain.sirius.vaultApi.generated.transferValidationRequests.TransferValidationRequestsOuterClass.RejectTransferValidationRequestResponse> reject(
        io.swisschain.sirius.vaultApi.generated.transferValidationRequests.TransferValidationRequestsOuterClass.RejectTransferValidationRequestRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getRejectMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET = 0;
  private static final int METHODID_CONFIRM = 1;
  private static final int METHODID_REJECT = 2;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final TransferValidationRequestsImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(TransferValidationRequestsImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET:
          serviceImpl.get((io.swisschain.sirius.vaultApi.generated.transferValidationRequests.TransferValidationRequestsOuterClass.GetTransferValidationRequestsRequest) request,
              (io.grpc.stub.StreamObserver<io.swisschain.sirius.vaultApi.generated.transferValidationRequests.TransferValidationRequestsOuterClass.GetTransferValidationRequestsResponse>) responseObserver);
          break;
        case METHODID_CONFIRM:
          serviceImpl.confirm((io.swisschain.sirius.vaultApi.generated.transferValidationRequests.TransferValidationRequestsOuterClass.ConfirmTransferValidationRequestRequest) request,
              (io.grpc.stub.StreamObserver<io.swisschain.sirius.vaultApi.generated.transferValidationRequests.TransferValidationRequestsOuterClass.ConfirmTransferValidationRequestResponse>) responseObserver);
          break;
        case METHODID_REJECT:
          serviceImpl.reject((io.swisschain.sirius.vaultApi.generated.transferValidationRequests.TransferValidationRequestsOuterClass.RejectTransferValidationRequestRequest) request,
              (io.grpc.stub.StreamObserver<io.swisschain.sirius.vaultApi.generated.transferValidationRequests.TransferValidationRequestsOuterClass.RejectTransferValidationRequestResponse>) responseObserver);
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

  private static abstract class TransferValidationRequestsBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    TransferValidationRequestsBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return io.swisschain.sirius.vaultApi.generated.transferValidationRequests.TransferValidationRequestsOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("TransferValidationRequests");
    }
  }

  private static final class TransferValidationRequestsFileDescriptorSupplier
      extends TransferValidationRequestsBaseDescriptorSupplier {
    TransferValidationRequestsFileDescriptorSupplier() {}
  }

  private static final class TransferValidationRequestsMethodDescriptorSupplier
      extends TransferValidationRequestsBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    TransferValidationRequestsMethodDescriptorSupplier(String methodName) {
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
      synchronized (TransferValidationRequestsGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new TransferValidationRequestsFileDescriptorSupplier())
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
