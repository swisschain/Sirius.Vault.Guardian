package io.swisschain.sirius.guardianApi.generated.validators;

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
    comments = "Source: validators.proto")
public final class ValidatorsGrpc {

  private ValidatorsGrpc() {}

  public static final String SERVICE_NAME = "swisschain.sirius.guardian_api.Validators";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<
          io.swisschain
              .sirius
              .guardianApi
              .generated
              .validators
              .ValidatorsOuterClass
              .GetValidatorsRequest,
          io.swisschain
              .sirius
              .guardianApi
              .generated
              .validators
              .ValidatorsOuterClass
              .GetValidatorsResponse>
      getGetMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Get",
      requestType =
          io.swisschain
              .sirius
              .guardianApi
              .generated
              .validators
              .ValidatorsOuterClass
              .GetValidatorsRequest
              .class,
      responseType =
          io.swisschain
              .sirius
              .guardianApi
              .generated
              .validators
              .ValidatorsOuterClass
              .GetValidatorsResponse
              .class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<
          io.swisschain
              .sirius
              .guardianApi
              .generated
              .validators
              .ValidatorsOuterClass
              .GetValidatorsRequest,
          io.swisschain
              .sirius
              .guardianApi
              .generated
              .validators
              .ValidatorsOuterClass
              .GetValidatorsResponse>
      getGetMethod() {
    io.grpc.MethodDescriptor<
            io.swisschain
                .sirius
                .guardianApi
                .generated
                .validators
                .ValidatorsOuterClass
                .GetValidatorsRequest,
            io.swisschain
                .sirius
                .guardianApi
                .generated
                .validators
                .ValidatorsOuterClass
                .GetValidatorsResponse>
        getGetMethod;
    if ((getGetMethod = ValidatorsGrpc.getGetMethod) == null) {
      synchronized (ValidatorsGrpc.class) {
        if ((getGetMethod = ValidatorsGrpc.getGetMethod) == null) {
          ValidatorsGrpc.getGetMethod =
              getGetMethod =
                  io.grpc.MethodDescriptor
                      .<io.swisschain
                              .sirius
                              .guardianApi
                              .generated
                              .validators
                              .ValidatorsOuterClass
                              .GetValidatorsRequest,
                          io.swisschain
                              .sirius
                              .guardianApi
                              .generated
                              .validators
                              .ValidatorsOuterClass
                              .GetValidatorsResponse>
                          newBuilder()
                      .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                      .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Get"))
                      .setSampledToLocalTracing(true)
                      .setRequestMarshaller(
                          io.grpc.protobuf.ProtoUtils.marshaller(
                              io.swisschain.sirius.guardianApi.generated.validators
                                  .ValidatorsOuterClass.GetValidatorsRequest.getDefaultInstance()))
                      .setResponseMarshaller(
                          io.grpc.protobuf.ProtoUtils.marshaller(
                              io.swisschain.sirius.guardianApi.generated.validators
                                  .ValidatorsOuterClass.GetValidatorsResponse.getDefaultInstance()))
                      .setSchemaDescriptor(new ValidatorsMethodDescriptorSupplier("Get"))
                      .build();
        }
      }
    }
    return getGetMethod;
  }

  /** Creates a new async stub that supports all call types for the service */
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
  public static ValidatorsBlockingStub newBlockingStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ValidatorsBlockingStub> factory =
        new io.grpc.stub.AbstractStub.StubFactory<ValidatorsBlockingStub>() {
          @java.lang.Override
          public ValidatorsBlockingStub newStub(
              io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            return new ValidatorsBlockingStub(channel, callOptions);
          }
        };
    return ValidatorsBlockingStub.newStub(factory, channel);
  }

  /** Creates a new ListenableFuture-style stub that supports unary calls on the service */
  public static ValidatorsFutureStub newFutureStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ValidatorsFutureStub> factory =
        new io.grpc.stub.AbstractStub.StubFactory<ValidatorsFutureStub>() {
          @java.lang.Override
          public ValidatorsFutureStub newStub(
              io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            return new ValidatorsFutureStub(channel, callOptions);
          }
        };
    return ValidatorsFutureStub.newStub(factory, channel);
  }

  /** */
  public abstract static class ValidatorsImplBase implements io.grpc.BindableService {

    /** */
    public void get(
        io.swisschain
                .sirius
                .guardianApi
                .generated
                .validators
                .ValidatorsOuterClass
                .GetValidatorsRequest
            request,
        io.grpc.stub.StreamObserver<
                io.swisschain
                    .sirius
                    .guardianApi
                    .generated
                    .validators
                    .ValidatorsOuterClass
                    .GetValidatorsResponse>
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
                          .validators
                          .ValidatorsOuterClass
                          .GetValidatorsRequest,
                      io.swisschain
                          .sirius
                          .guardianApi
                          .generated
                          .validators
                          .ValidatorsOuterClass
                          .GetValidatorsResponse>(this, METHODID_GET)))
          .build();
    }
  }

  /** */
  public static final class ValidatorsStub extends io.grpc.stub.AbstractAsyncStub<ValidatorsStub> {
    private ValidatorsStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ValidatorsStub build(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ValidatorsStub(channel, callOptions);
    }

    /** */
    public void get(
        io.swisschain
                .sirius
                .guardianApi
                .generated
                .validators
                .ValidatorsOuterClass
                .GetValidatorsRequest
            request,
        io.grpc.stub.StreamObserver<
                io.swisschain
                    .sirius
                    .guardianApi
                    .generated
                    .validators
                    .ValidatorsOuterClass
                    .GetValidatorsResponse>
            responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /** */
  public static final class ValidatorsBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<ValidatorsBlockingStub> {
    private ValidatorsBlockingStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ValidatorsBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ValidatorsBlockingStub(channel, callOptions);
    }

    /** */
    public io.swisschain
            .sirius
            .guardianApi
            .generated
            .validators
            .ValidatorsOuterClass
            .GetValidatorsResponse
        get(
            io.swisschain
                    .sirius
                    .guardianApi
                    .generated
                    .validators
                    .ValidatorsOuterClass
                    .GetValidatorsRequest
                request) {
      return blockingUnaryCall(getChannel(), getGetMethod(), getCallOptions(), request);
    }
  }

  /** */
  public static final class ValidatorsFutureStub
      extends io.grpc.stub.AbstractFutureStub<ValidatorsFutureStub> {
    private ValidatorsFutureStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ValidatorsFutureStub build(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ValidatorsFutureStub(channel, callOptions);
    }

    /** */
    public com.google.common.util.concurrent.ListenableFuture<
            io.swisschain
                .sirius
                .guardianApi
                .generated
                .validators
                .ValidatorsOuterClass
                .GetValidatorsResponse>
        get(
            io.swisschain
                    .sirius
                    .guardianApi
                    .generated
                    .validators
                    .ValidatorsOuterClass
                    .GetValidatorsRequest
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
        case METHODID_GET:
          serviceImpl.get(
              (io.swisschain
                      .sirius
                      .guardianApi
                      .generated
                      .validators
                      .ValidatorsOuterClass
                      .GetValidatorsRequest)
                  request,
              (io.grpc.stub.StreamObserver<
                      io.swisschain
                          .sirius
                          .guardianApi
                          .generated
                          .validators
                          .ValidatorsOuterClass
                          .GetValidatorsResponse>)
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

  private abstract static class ValidatorsBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier,
          io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    ValidatorsBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return io.swisschain.sirius.guardianApi.generated.validators.ValidatorsOuterClass
          .getDescriptor();
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
          serviceDescriptor =
              result =
                  io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
                      .setSchemaDescriptor(new ValidatorsFileDescriptorSupplier())
                      .addMethod(getGetMethod())
                      .build();
        }
      }
    }
    return result;
  }
}
