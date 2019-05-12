package com.grpc.dto;

import io.grpc.stub.ClientCalls;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.20.0)",
    comments = "Source: demo.proto")
public final class DemoServerGrpc {

  private DemoServerGrpc() {}

  public static final String SERVICE_NAME = "helloworld.DemoServer";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<DemoRquest,
      DemoResponse> getHelloMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "hello",
      requestType = DemoRquest.class,
      responseType = DemoResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<DemoRquest,
      DemoResponse> getHelloMethod() {
    io.grpc.MethodDescriptor<DemoRquest, DemoResponse> getHelloMethod;
    if ((getHelloMethod = DemoServerGrpc.getHelloMethod) == null) {
      synchronized (DemoServerGrpc.class) {
        if ((getHelloMethod = DemoServerGrpc.getHelloMethod) == null) {
          DemoServerGrpc.getHelloMethod = getHelloMethod =
              io.grpc.MethodDescriptor.<DemoRquest, DemoResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "helloworld.DemoServer", "hello"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  DemoRquest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  DemoResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new DemoServerMethodDescriptorSupplier("hello"))
                  .build();
          }
        }
     }
     return getHelloMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static DemoServerStub newStub(io.grpc.Channel channel) {
    return new DemoServerStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static DemoServerBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new DemoServerBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static DemoServerFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new DemoServerFutureStub(channel);
  }

  /**
   */
  public static abstract class DemoServerImplBase implements io.grpc.BindableService {

    /**
     */
    public void hello(DemoRquest request,
        io.grpc.stub.StreamObserver<DemoResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getHelloMethod(), responseObserver);
    }

    @Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getHelloMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                DemoRquest,
                DemoResponse>(
                  this, METHODID_HELLO)))
          .build();
    }
  }

  /**
   */
  public static final class DemoServerStub extends io.grpc.stub.AbstractStub<DemoServerStub> {
    private DemoServerStub(io.grpc.Channel channel) {
      super(channel);
    }

    private DemoServerStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected DemoServerStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new DemoServerStub(channel, callOptions);
    }

    /**
     */
    public void hello(DemoRquest request,
        io.grpc.stub.StreamObserver<DemoResponse> responseObserver) {
      ClientCalls.asyncUnaryCall(
          getChannel().newCall(getHelloMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class DemoServerBlockingStub extends io.grpc.stub.AbstractStub<DemoServerBlockingStub> {
    private DemoServerBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private DemoServerBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected DemoServerBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new DemoServerBlockingStub(channel, callOptions);
    }

    /**
     */
    public DemoResponse hello(DemoRquest request) {
      return blockingUnaryCall(
          getChannel(), getHelloMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class DemoServerFutureStub extends io.grpc.stub.AbstractStub<DemoServerFutureStub> {
    private DemoServerFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private DemoServerFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected DemoServerFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new DemoServerFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<DemoResponse> hello(
        DemoRquest request) {
      return futureUnaryCall(
          getChannel().newCall(getHelloMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_HELLO = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final DemoServerImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(DemoServerImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_HELLO:
          serviceImpl.hello((DemoRquest) request,
              (io.grpc.stub.StreamObserver<DemoResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @Override
    @SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class DemoServerBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    DemoServerBaseDescriptorSupplier() {}

    @Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return DemoServerRpc.getDescriptor();
    }

    @Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("DemoServer");
    }
  }

  private static final class DemoServerFileDescriptorSupplier
      extends DemoServerBaseDescriptorSupplier {
    DemoServerFileDescriptorSupplier() {}
  }

  private static final class DemoServerMethodDescriptorSupplier
      extends DemoServerBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    DemoServerMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (DemoServerGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new DemoServerFileDescriptorSupplier())
              .addMethod(getHelloMethod())
              .build();
        }
      }
    }
    return result;
  }
}
