package io.swisschain.sirius.guardianApi.generated.validation_results;

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
    comments = "Source: validation-results.proto")
public final class ValidationResultsGrpc {

  private ValidationResultsGrpc() {}

  public static final String SERVICE_NAME = "swisschain.sirius.guardian_api.ValidationResults";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<
          io.swisschain
              .sirius
              .guardianApi
              .generated
              .validation_results
              .ValidationResultsOuterClass
              .GetValidationResultsRequest,
          io.swisschain
              .sirius
              .guardianApi
              .generated
              .validation_results
              .ValidationResultsOuterClass
              .GetValidationResultsResponse>
      getGetMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Get",
      requestType =
          io.swisschain
              .sirius
              .guardianApi
              .generated
              .validation_results
              .ValidationResultsOuterClass
              .GetValidationResultsRequest
              .class,
      responseType =
          io.swisschain
              .sirius
              .guardianApi
              .generated
              .validation_results
              .ValidationResultsOuterClass
              .GetValidationResultsResponse
              .class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<
          io.swisschain
              .sirius
              .guardianApi
              .generated
              .validation_results
              .ValidationResultsOuterClass
              .GetValidationResultsRequest,
          io.swisschain
              .sirius
              .guardianApi
              .generated
              .validation_results
              .ValidationResultsOuterClass
              .GetValidationResultsResponse>
      getGetMethod() {
    io.grpc.MethodDescriptor<
            io.swisschain
                .sirius
                .guardianApi
                .generated
                .validation_results
                .ValidationResultsOuterClass
                .GetValidationResultsRequest,
            io.swisschain
                .sirius
                .guardianApi
                .generated
                .validation_results
                .ValidationResultsOuterClass
                .GetValidationResultsResponse>
        getGetMethod;
    if ((getGetMethod = ValidationResultsGrpc.getGetMethod) == null) {
      synchronized (ValidationResultsGrpc.class) {
        if ((getGetMethod = ValidationResultsGrpc.getGetMethod) == null) {
          ValidationResultsGrpc.getGetMethod =
              getGetMethod =
                  io.grpc.MethodDescriptor
                      .<io.swisschain
                              .sirius
                              .guardianApi
                              .generated
                              .validation_results
                              .ValidationResultsOuterClass
                              .GetValidationResultsRequest,
                          io.swisschain
                              .sirius
                              .guardianApi
                              .generated
                              .validation_results
                              .ValidationResultsOuterClass
                              .GetValidationResultsResponse>
                          newBuilder()
                      .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                      .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Get"))
                      .setSampledToLocalTracing(true)
                      .setRequestMarshaller(
                          io.grpc.protobuf.ProtoUtils.marshaller(
                              io.swisschain.sirius.guardianApi.generated.validation_results
                                  .ValidationResultsOuterClass.GetValidationResultsRequest
                                  .getDefaultInstance()))
                      .setResponseMarshaller(
                          io.grpc.protobuf.ProtoUtils.marshaller(
                              io.swisschain.sirius.guardianApi.generated.validation_results
                                  .ValidationResultsOuterClass.GetValidationResultsResponse
                                  .getDefaultInstance()))
                      .setSchemaDescriptor(new ValidationResultsMethodDescriptorSupplier("Get"))
                      .build();
        }
      }
    }
    return getGetMethod;
  }

  /** Creates a new async stub that supports all call types for the service */
  public static ValidationResultsStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ValidationResultsStub> factory =
        new io.grpc.stub.AbstractStub.StubFactory<ValidationResultsStub>() {
          @java.lang.Override
          public ValidationResultsStub newStub(
              io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            return new ValidationResultsStub(channel, callOptions);
          }
        };
    return ValidationResultsStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static ValidationResultsBlockingStub newBlockingStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ValidationResultsBlockingStub> factory =
        new io.grpc.stub.AbstractStub.StubFactory<ValidationResultsBlockingStub>() {
          @java.lang.Override
          public ValidationResultsBlockingStub newStub(
              io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            return new ValidationResultsBlockingStub(channel, callOptions);
          }
        };
    return ValidationResultsBlockingStub.newStub(factory, channel);
  }

  /** Creates a new ListenableFuture-style stub that supports unary calls on the service */
  public static ValidationResultsFutureStub newFutureStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ValidationResultsFutureStub> factory =
        new io.grpc.stub.AbstractStub.StubFactory<ValidationResultsFutureStub>() {
          @java.lang.Override
          public ValidationResultsFutureStub newStub(
              io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            return new ValidationResultsFutureStub(channel, callOptions);
          }
        };
    return ValidationResultsFutureStub.newStub(factory, channel);
  }

  /** */
  public abstract static class ValidationResultsImplBase implements io.grpc.BindableService {

    /** */
    public void get(
        io.swisschain
                .sirius
                .guardianApi
                .generated
                .validation_results
                .ValidationResultsOuterClass
                .GetValidationResultsRequest
            request,
        io.grpc.stub.StreamObserver<
                io.swisschain
                    .sirius
                    .guardianApi
                    .generated
                    .validation_results
                    .ValidationResultsOuterClass
                    .GetValidationResultsResponse>
            responseObserver) {
      asyncUnimplementedUnaryCall(getGetMethod(), responseObserver);
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
                          .guardianApi
                          .generated
                          .validation_results
                          .ValidationResultsOuterClass
                          .GetValidationResultsRequest,
                      io.swisschain
                          .sirius
                          .guardianApi
                          .generated
                          .validation_results
                          .ValidationResultsOuterClass
                          .GetValidationResultsResponse>(this, METHODID_GET)))
          .build();
    }
  }

  /** */
  public static final class ValidationResultsStub
      extends io.grpc.stub.AbstractAsyncStub<ValidationResultsStub> {
    private ValidationResultsStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ValidationResultsStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ValidationResultsStub(channel, callOptions);
    }

    /** */
    public void get(
        io.swisschain
                .sirius
                .guardianApi
                .generated
                .validation_results
                .ValidationResultsOuterClass
                .GetValidationResultsRequest
            request,
        io.grpc.stub.StreamObserver<
                io.swisschain
                    .sirius
                    .guardianApi
                    .generated
                    .validation_results
                    .ValidationResultsOuterClass
                    .GetValidationResultsResponse>
            responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /** */
  public static final class ValidationResultsBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<ValidationResultsBlockingStub> {
    private ValidationResultsBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ValidationResultsBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ValidationResultsBlockingStub(channel, callOptions);
    }

    /** */
    public io.swisschain
            .sirius
            .guardianApi
            .generated
            .validation_results
            .ValidationResultsOuterClass
            .GetValidationResultsResponse
        get(
            io.swisschain
                    .sirius
                    .guardianApi
                    .generated
                    .validation_results
                    .ValidationResultsOuterClass
                    .GetValidationResultsRequest
                request) {
      return blockingUnaryCall(getChannel(), getGetMethod(), getCallOptions(), request);
    }
  }

  /** */
  public static final class ValidationResultsFutureStub
      extends io.grpc.stub.AbstractFutureStub<ValidationResultsFutureStub> {
    private ValidationResultsFutureStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ValidationResultsFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ValidationResultsFutureStub(channel, callOptions);
    }

    /** */
    public com.google.common.util.concurrent.ListenableFuture<
            io.swisschain
                .sirius
                .guardianApi
                .generated
                .validation_results
                .ValidationResultsOuterClass
                .GetValidationResultsResponse>
        get(
            io.swisschain
                    .sirius
                    .guardianApi
                    .generated
                    .validation_results
                    .ValidationResultsOuterClass
                    .GetValidationResultsRequest
                request) {
      return futureUnaryCall(getChannel().newCall(getGetMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET = 0;

  private static final class MethodHandlers<Req, Resp>
      implements io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
          io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
          io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
          io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final ValidationResultsImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(ValidationResultsImplBase serviceImpl, int methodId) {
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
                      .guardianApi
                      .generated
                      .validation_results
                      .ValidationResultsOuterClass
                      .GetValidationResultsRequest)
                  request,
              (io.grpc.stub.StreamObserver<
                      io.swisschain
                          .sirius
                          .guardianApi
                          .generated
                          .validation_results
                          .ValidationResultsOuterClass
                          .GetValidationResultsResponse>)
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

  private abstract static class ValidationResultsBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier,
          io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    ValidationResultsBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return io.swisschain.sirius.guardianApi.generated.validation_results
          .ValidationResultsOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("ValidationResults");
    }
  }

  private static final class ValidationResultsFileDescriptorSupplier
      extends ValidationResultsBaseDescriptorSupplier {
    ValidationResultsFileDescriptorSupplier() {}
  }

  private static final class ValidationResultsMethodDescriptorSupplier
      extends ValidationResultsBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    ValidationResultsMethodDescriptorSupplier(String methodName) {
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
      synchronized (ValidationResultsGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor =
              result =
                  io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
                      .setSchemaDescriptor(new ValidationResultsFileDescriptorSupplier())
                      .addMethod(getGetMethod())
                      .build();
        }
      }
    }
    return result;
  }
}
