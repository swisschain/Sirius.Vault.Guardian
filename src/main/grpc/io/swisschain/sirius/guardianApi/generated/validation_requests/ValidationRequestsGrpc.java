package io.swisschain.sirius.guardianApi.generated.validation_requests;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/** */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.31.1)",
    comments = "Source: validation-requests.proto")
public final class ValidationRequestsGrpc {

  private ValidationRequestsGrpc() {}

  public static final String SERVICE_NAME = "swisschain.sirius.guardian_api.ValidationRequests";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<
          io.swisschain
              .sirius
              .guardianApi
              .generated
              .validation_requests
              .ValidationRequestsOuterClass
              .CreateValidationRequestRequest,
          io.swisschain
              .sirius
              .guardianApi
              .generated
              .validation_requests
              .ValidationRequestsOuterClass
              .CreateValidationRequestResponse>
      getCreateMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Create",
      requestType =
          io.swisschain
              .sirius
              .guardianApi
              .generated
              .validation_requests
              .ValidationRequestsOuterClass
              .CreateValidationRequestRequest
              .class,
      responseType =
          io.swisschain
              .sirius
              .guardianApi
              .generated
              .validation_requests
              .ValidationRequestsOuterClass
              .CreateValidationRequestResponse
              .class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<
          io.swisschain
              .sirius
              .guardianApi
              .generated
              .validation_requests
              .ValidationRequestsOuterClass
              .CreateValidationRequestRequest,
          io.swisschain
              .sirius
              .guardianApi
              .generated
              .validation_requests
              .ValidationRequestsOuterClass
              .CreateValidationRequestResponse>
      getCreateMethod() {
    io.grpc.MethodDescriptor<
            io.swisschain
                .sirius
                .guardianApi
                .generated
                .validation_requests
                .ValidationRequestsOuterClass
                .CreateValidationRequestRequest,
            io.swisschain
                .sirius
                .guardianApi
                .generated
                .validation_requests
                .ValidationRequestsOuterClass
                .CreateValidationRequestResponse>
        getCreateMethod;
    if ((getCreateMethod = ValidationRequestsGrpc.getCreateMethod) == null) {
      synchronized (ValidationRequestsGrpc.class) {
        if ((getCreateMethod = ValidationRequestsGrpc.getCreateMethod) == null) {
          ValidationRequestsGrpc.getCreateMethod =
              getCreateMethod =
                  io.grpc.MethodDescriptor
                      .<io.swisschain
                              .sirius
                              .guardianApi
                              .generated
                              .validation_requests
                              .ValidationRequestsOuterClass
                              .CreateValidationRequestRequest,
                          io.swisschain
                              .sirius
                              .guardianApi
                              .generated
                              .validation_requests
                              .ValidationRequestsOuterClass
                              .CreateValidationRequestResponse>
                          newBuilder()
                      .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                      .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Create"))
                      .setSampledToLocalTracing(true)
                      .setRequestMarshaller(
                          io.grpc.protobuf.ProtoUtils.marshaller(
                              io.swisschain.sirius.guardianApi.generated.validation_requests
                                  .ValidationRequestsOuterClass.CreateValidationRequestRequest
                                  .getDefaultInstance()))
                      .setResponseMarshaller(
                          io.grpc.protobuf.ProtoUtils.marshaller(
                              io.swisschain.sirius.guardianApi.generated.validation_requests
                                  .ValidationRequestsOuterClass.CreateValidationRequestResponse
                                  .getDefaultInstance()))
                      .setSchemaDescriptor(new ValidationRequestsMethodDescriptorSupplier("Create"))
                      .build();
        }
      }
    }
    return getCreateMethod;
  }

  private static volatile io.grpc.MethodDescriptor<
          io.swisschain
              .sirius
              .guardianApi
              .generated
              .validation_requests
              .ValidationRequestsOuterClass
              .ConfirmValidationRequestRequest,
          io.swisschain
              .sirius
              .guardianApi
              .generated
              .validation_requests
              .ValidationRequestsOuterClass
              .ConfirmValidationRequestResponse>
      getConfirmMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Confirm",
      requestType =
          io.swisschain
              .sirius
              .guardianApi
              .generated
              .validation_requests
              .ValidationRequestsOuterClass
              .ConfirmValidationRequestRequest
              .class,
      responseType =
          io.swisschain
              .sirius
              .guardianApi
              .generated
              .validation_requests
              .ValidationRequestsOuterClass
              .ConfirmValidationRequestResponse
              .class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<
          io.swisschain
              .sirius
              .guardianApi
              .generated
              .validation_requests
              .ValidationRequestsOuterClass
              .ConfirmValidationRequestRequest,
          io.swisschain
              .sirius
              .guardianApi
              .generated
              .validation_requests
              .ValidationRequestsOuterClass
              .ConfirmValidationRequestResponse>
      getConfirmMethod() {
    io.grpc.MethodDescriptor<
            io.swisschain
                .sirius
                .guardianApi
                .generated
                .validation_requests
                .ValidationRequestsOuterClass
                .ConfirmValidationRequestRequest,
            io.swisschain
                .sirius
                .guardianApi
                .generated
                .validation_requests
                .ValidationRequestsOuterClass
                .ConfirmValidationRequestResponse>
        getConfirmMethod;
    if ((getConfirmMethod = ValidationRequestsGrpc.getConfirmMethod) == null) {
      synchronized (ValidationRequestsGrpc.class) {
        if ((getConfirmMethod = ValidationRequestsGrpc.getConfirmMethod) == null) {
          ValidationRequestsGrpc.getConfirmMethod =
              getConfirmMethod =
                  io.grpc.MethodDescriptor
                      .<io.swisschain
                              .sirius
                              .guardianApi
                              .generated
                              .validation_requests
                              .ValidationRequestsOuterClass
                              .ConfirmValidationRequestRequest,
                          io.swisschain
                              .sirius
                              .guardianApi
                              .generated
                              .validation_requests
                              .ValidationRequestsOuterClass
                              .ConfirmValidationRequestResponse>
                          newBuilder()
                      .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                      .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Confirm"))
                      .setSampledToLocalTracing(true)
                      .setRequestMarshaller(
                          io.grpc.protobuf.ProtoUtils.marshaller(
                              io.swisschain.sirius.guardianApi.generated.validation_requests
                                  .ValidationRequestsOuterClass.ConfirmValidationRequestRequest
                                  .getDefaultInstance()))
                      .setResponseMarshaller(
                          io.grpc.protobuf.ProtoUtils.marshaller(
                              io.swisschain.sirius.guardianApi.generated.validation_requests
                                  .ValidationRequestsOuterClass.ConfirmValidationRequestResponse
                                  .getDefaultInstance()))
                      .setSchemaDescriptor(
                          new ValidationRequestsMethodDescriptorSupplier("Confirm"))
                      .build();
        }
      }
    }
    return getConfirmMethod;
  }

  /** Creates a new async stub that supports all call types for the service */
  public static ValidationRequestsStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ValidationRequestsStub> factory =
        new io.grpc.stub.AbstractStub.StubFactory<ValidationRequestsStub>() {
          @java.lang.Override
          public ValidationRequestsStub newStub(
              io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            return new ValidationRequestsStub(channel, callOptions);
          }
        };
    return ValidationRequestsStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static ValidationRequestsBlockingStub newBlockingStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ValidationRequestsBlockingStub> factory =
        new io.grpc.stub.AbstractStub.StubFactory<ValidationRequestsBlockingStub>() {
          @java.lang.Override
          public ValidationRequestsBlockingStub newStub(
              io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            return new ValidationRequestsBlockingStub(channel, callOptions);
          }
        };
    return ValidationRequestsBlockingStub.newStub(factory, channel);
  }

  /** Creates a new ListenableFuture-style stub that supports unary calls on the service */
  public static ValidationRequestsFutureStub newFutureStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ValidationRequestsFutureStub> factory =
        new io.grpc.stub.AbstractStub.StubFactory<ValidationRequestsFutureStub>() {
          @java.lang.Override
          public ValidationRequestsFutureStub newStub(
              io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            return new ValidationRequestsFutureStub(channel, callOptions);
          }
        };
    return ValidationRequestsFutureStub.newStub(factory, channel);
  }

  /** */
  public abstract static class ValidationRequestsImplBase implements io.grpc.BindableService {

    /** */
    public void create(
        io.swisschain
                .sirius
                .guardianApi
                .generated
                .validation_requests
                .ValidationRequestsOuterClass
                .CreateValidationRequestRequest
            request,
        io.grpc.stub.StreamObserver<
                io.swisschain
                    .sirius
                    .guardianApi
                    .generated
                    .validation_requests
                    .ValidationRequestsOuterClass
                    .CreateValidationRequestResponse>
            responseObserver) {
      asyncUnimplementedUnaryCall(getCreateMethod(), responseObserver);
    }

    /** */
    public void confirm(
        io.swisschain
                .sirius
                .guardianApi
                .generated
                .validation_requests
                .ValidationRequestsOuterClass
                .ConfirmValidationRequestRequest
            request,
        io.grpc.stub.StreamObserver<
                io.swisschain
                    .sirius
                    .guardianApi
                    .generated
                    .validation_requests
                    .ValidationRequestsOuterClass
                    .ConfirmValidationRequestResponse>
            responseObserver) {
      asyncUnimplementedUnaryCall(getConfirmMethod(), responseObserver);
    }

    @java.lang.Override
    public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
              getCreateMethod(),
              asyncUnaryCall(
                  new MethodHandlers<
                      io.swisschain
                          .sirius
                          .guardianApi
                          .generated
                          .validation_requests
                          .ValidationRequestsOuterClass
                          .CreateValidationRequestRequest,
                      io.swisschain
                          .sirius
                          .guardianApi
                          .generated
                          .validation_requests
                          .ValidationRequestsOuterClass
                          .CreateValidationRequestResponse>(this, METHODID_CREATE)))
          .addMethod(
              getConfirmMethod(),
              asyncUnaryCall(
                  new MethodHandlers<
                      io.swisschain
                          .sirius
                          .guardianApi
                          .generated
                          .validation_requests
                          .ValidationRequestsOuterClass
                          .ConfirmValidationRequestRequest,
                      io.swisschain
                          .sirius
                          .guardianApi
                          .generated
                          .validation_requests
                          .ValidationRequestsOuterClass
                          .ConfirmValidationRequestResponse>(this, METHODID_CONFIRM)))
          .build();
    }
  }

  /** */
  public static final class ValidationRequestsStub
      extends io.grpc.stub.AbstractAsyncStub<ValidationRequestsStub> {
    private ValidationRequestsStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ValidationRequestsStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ValidationRequestsStub(channel, callOptions);
    }

    /** */
    public void create(
        io.swisschain
                .sirius
                .guardianApi
                .generated
                .validation_requests
                .ValidationRequestsOuterClass
                .CreateValidationRequestRequest
            request,
        io.grpc.stub.StreamObserver<
                io.swisschain
                    .sirius
                    .guardianApi
                    .generated
                    .validation_requests
                    .ValidationRequestsOuterClass
                    .CreateValidationRequestResponse>
            responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCreateMethod(), getCallOptions()), request, responseObserver);
    }

    /** */
    public void confirm(
        io.swisschain
                .sirius
                .guardianApi
                .generated
                .validation_requests
                .ValidationRequestsOuterClass
                .ConfirmValidationRequestRequest
            request,
        io.grpc.stub.StreamObserver<
                io.swisschain
                    .sirius
                    .guardianApi
                    .generated
                    .validation_requests
                    .ValidationRequestsOuterClass
                    .ConfirmValidationRequestResponse>
            responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getConfirmMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /** */
  public static final class ValidationRequestsBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<ValidationRequestsBlockingStub> {
    private ValidationRequestsBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ValidationRequestsBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ValidationRequestsBlockingStub(channel, callOptions);
    }

    /** */
    public io.swisschain
            .sirius
            .guardianApi
            .generated
            .validation_requests
            .ValidationRequestsOuterClass
            .CreateValidationRequestResponse
        create(
            io.swisschain
                    .sirius
                    .guardianApi
                    .generated
                    .validation_requests
                    .ValidationRequestsOuterClass
                    .CreateValidationRequestRequest
                request) {
      return blockingUnaryCall(getChannel(), getCreateMethod(), getCallOptions(), request);
    }

    /** */
    public io.swisschain
            .sirius
            .guardianApi
            .generated
            .validation_requests
            .ValidationRequestsOuterClass
            .ConfirmValidationRequestResponse
        confirm(
            io.swisschain
                    .sirius
                    .guardianApi
                    .generated
                    .validation_requests
                    .ValidationRequestsOuterClass
                    .ConfirmValidationRequestRequest
                request) {
      return blockingUnaryCall(getChannel(), getConfirmMethod(), getCallOptions(), request);
    }
  }

  /** */
  public static final class ValidationRequestsFutureStub
      extends io.grpc.stub.AbstractFutureStub<ValidationRequestsFutureStub> {
    private ValidationRequestsFutureStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ValidationRequestsFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ValidationRequestsFutureStub(channel, callOptions);
    }

    /** */
    public com.google.common.util.concurrent.ListenableFuture<
            io.swisschain
                .sirius
                .guardianApi
                .generated
                .validation_requests
                .ValidationRequestsOuterClass
                .CreateValidationRequestResponse>
        create(
            io.swisschain
                    .sirius
                    .guardianApi
                    .generated
                    .validation_requests
                    .ValidationRequestsOuterClass
                    .CreateValidationRequestRequest
                request) {
      return futureUnaryCall(getChannel().newCall(getCreateMethod(), getCallOptions()), request);
    }

    /** */
    public com.google.common.util.concurrent.ListenableFuture<
            io.swisschain
                .sirius
                .guardianApi
                .generated
                .validation_requests
                .ValidationRequestsOuterClass
                .ConfirmValidationRequestResponse>
        confirm(
            io.swisschain
                    .sirius
                    .guardianApi
                    .generated
                    .validation_requests
                    .ValidationRequestsOuterClass
                    .ConfirmValidationRequestRequest
                request) {
      return futureUnaryCall(getChannel().newCall(getConfirmMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CREATE = 0;
  private static final int METHODID_CONFIRM = 1;

  private static final class MethodHandlers<Req, Resp>
      implements io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
          io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
          io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
          io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final ValidationRequestsImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(ValidationRequestsImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_CREATE:
          serviceImpl.create(
              (io.swisschain
                      .sirius
                      .guardianApi
                      .generated
                      .validation_requests
                      .ValidationRequestsOuterClass
                      .CreateValidationRequestRequest)
                  request,
              (io.grpc.stub.StreamObserver<
                      io.swisschain
                          .sirius
                          .guardianApi
                          .generated
                          .validation_requests
                          .ValidationRequestsOuterClass
                          .CreateValidationRequestResponse>)
                  responseObserver);
          break;
        case METHODID_CONFIRM:
          serviceImpl.confirm(
              (io.swisschain
                      .sirius
                      .guardianApi
                      .generated
                      .validation_requests
                      .ValidationRequestsOuterClass
                      .ConfirmValidationRequestRequest)
                  request,
              (io.grpc.stub.StreamObserver<
                      io.swisschain
                          .sirius
                          .guardianApi
                          .generated
                          .validation_requests
                          .ValidationRequestsOuterClass
                          .ConfirmValidationRequestResponse>)
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

  private abstract static class ValidationRequestsBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier,
          io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    ValidationRequestsBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return io.swisschain.sirius.guardianApi.generated.validation_requests
          .ValidationRequestsOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("ValidationRequests");
    }
  }

  private static final class ValidationRequestsFileDescriptorSupplier
      extends ValidationRequestsBaseDescriptorSupplier {
    ValidationRequestsFileDescriptorSupplier() {}
  }

  private static final class ValidationRequestsMethodDescriptorSupplier
      extends ValidationRequestsBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    ValidationRequestsMethodDescriptorSupplier(String methodName) {
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
      synchronized (ValidationRequestsGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor =
              result =
                  io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
                      .setSchemaDescriptor(new ValidationRequestsFileDescriptorSupplier())
                      .addMethod(getCreateMethod())
                      .addMethod(getConfirmMethod())
                      .build();
        }
      }
    }
    return result;
  }
}
