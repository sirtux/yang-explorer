package com.huawei.yang.explorer.grpc;

import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;

/**
 */
public final class gNMIGrpc {

  private gNMIGrpc() {}

  public static final String SERVICE_NAME = "gnmi.gNMI";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<GNMI.CapabilityRequest,
          GNMI.CapabilityResponse> METHOD_CAPABILITIES =
      io.grpc.MethodDescriptor.<GNMI.CapabilityRequest, GNMI.CapabilityResponse>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
          .setFullMethodName(generateFullMethodName(
              "gnmi.gNMI", "Capabilities"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              GNMI.CapabilityRequest.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              GNMI.CapabilityResponse.getDefaultInstance()))
          .build();
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<GNMI.GetRequest,
          GNMI.GetResponse> METHOD_GET =
      io.grpc.MethodDescriptor.<GNMI.GetRequest, GNMI.GetResponse>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
          .setFullMethodName(generateFullMethodName(
              "gnmi.gNMI", "Get"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              GNMI.GetRequest.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              GNMI.GetResponse.getDefaultInstance()))
          .build();
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<GNMI.SetRequest,
          GNMI.SetResponse> METHOD_SET =
      io.grpc.MethodDescriptor.<GNMI.SetRequest, GNMI.SetResponse>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
          .setFullMethodName(generateFullMethodName(
              "gnmi.gNMI", "Set"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              GNMI.SetRequest.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              GNMI.SetResponse.getDefaultInstance()))
          .build();
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<GNMI.SubscribeRequest,
          GNMI.SubscribeResponse> METHOD_SUBSCRIBE =
      io.grpc.MethodDescriptor.<GNMI.SubscribeRequest, GNMI.SubscribeResponse>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
          .setFullMethodName(generateFullMethodName(
              "gnmi.gNMI", "Subscribe"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              GNMI.SubscribeRequest.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              GNMI.SubscribeResponse.getDefaultInstance()))
          .build();

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static gNMIStub newStub(io.grpc.Channel channel) {
    return new gNMIStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static gNMIBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new gNMIBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static gNMIFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new gNMIFutureStub(channel);
  }

  /**
   */
  public static abstract class gNMIImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * Capabilities allows the client to retrieve the set of capabilities that
     * is supported by the target. This allows the target to validate the
     * service version that is implemented and retrieve the set of models that
     * the target supports. The models can then be specified in subsequent RPCs
     * to restrict the set of data that is utilized.
     * Reference: gNMI Specification Section 3.2
     * </pre>
     */
    public void capabilities(GNMI.CapabilityRequest request,
                             io.grpc.stub.StreamObserver<GNMI.CapabilityResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_CAPABILITIES, responseObserver);
    }

    /**
     * <pre>
     * Retrieve a snapshot of data from the target. A Get RPC requests that the
     * target snapshots a subset of the data tree as specified by the paths
     * included in the message and serializes this to be returned to the
     * client using the specified encoding.
     * Reference: gNMI Specification Section 3.3
     * </pre>
     */
    public void get(GNMI.GetRequest request,
                    io.grpc.stub.StreamObserver<GNMI.GetResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_GET, responseObserver);
    }

    /**
     * <pre>
     * Set allows the client to modify the state of data on the target. The
     * paths to modified along with the new values that the client wishes
     * to set the value to.
     * Reference: gNMI Specification Section 3.4
     * </pre>
     */
    public void set(GNMI.SetRequest request,
                    io.grpc.stub.StreamObserver<GNMI.SetResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_SET, responseObserver);
    }

    /**
     * <pre>
     * Subscribe allows a client to request the target to send it values
     * of particular paths within the data tree. These values may be streamed
     * at a particular cadence (STREAM), sent one off on a long-lived channel
     * (POLL), or sent as a one-off retrieval (ONCE).
     * Reference: gNMI Specification Section 3.5
     * </pre>
     */
    public io.grpc.stub.StreamObserver<GNMI.SubscribeRequest> subscribe(
        io.grpc.stub.StreamObserver<GNMI.SubscribeResponse> responseObserver) {
      return asyncUnimplementedStreamingCall(METHOD_SUBSCRIBE, responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            METHOD_CAPABILITIES,
            asyncUnaryCall(
              new MethodHandlers<
                      GNMI.CapabilityRequest,
                      GNMI.CapabilityResponse>(
                  this, METHODID_CAPABILITIES)))
          .addMethod(
            METHOD_GET,
            asyncUnaryCall(
              new MethodHandlers<
                      GNMI.GetRequest,
                      GNMI.GetResponse>(
                  this, METHODID_GET)))
          .addMethod(
            METHOD_SET,
            asyncUnaryCall(
              new MethodHandlers<
                      GNMI.SetRequest,
                      GNMI.SetResponse>(
                  this, METHODID_SET)))
          .addMethod(
            METHOD_SUBSCRIBE,
            asyncBidiStreamingCall(
              new MethodHandlers<
                      GNMI.SubscribeRequest,
                      GNMI.SubscribeResponse>(
                  this, METHODID_SUBSCRIBE)))
          .build();
    }
  }

  /**
   */
  public static final class gNMIStub extends io.grpc.stub.AbstractStub<gNMIStub> {
    private gNMIStub(io.grpc.Channel channel) {
      super(channel);
    }

    private gNMIStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected gNMIStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new gNMIStub(channel, callOptions);
    }

    /**
     * <pre>
     * Capabilities allows the client to retrieve the set of capabilities that
     * is supported by the target. This allows the target to validate the
     * service version that is implemented and retrieve the set of models that
     * the target supports. The models can then be specified in subsequent RPCs
     * to restrict the set of data that is utilized.
     * Reference: gNMI Specification Section 3.2
     * </pre>
     */
    public void capabilities(GNMI.CapabilityRequest request,
                             io.grpc.stub.StreamObserver<GNMI.CapabilityResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_CAPABILITIES, getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Retrieve a snapshot of data from the target. A Get RPC requests that the
     * target snapshots a subset of the data tree as specified by the paths
     * included in the message and serializes this to be returned to the
     * client using the specified encoding.
     * Reference: gNMI Specification Section 3.3
     * </pre>
     */
    public void get(GNMI.GetRequest request,
                    io.grpc.stub.StreamObserver<GNMI.GetResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_GET, getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Set allows the client to modify the state of data on the target. The
     * paths to modified along with the new values that the client wishes
     * to set the value to.
     * Reference: gNMI Specification Section 3.4
     * </pre>
     */
    public void set(GNMI.SetRequest request,
                    io.grpc.stub.StreamObserver<GNMI.SetResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_SET, getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Subscribe allows a client to request the target to send it values
     * of particular paths within the data tree. These values may be streamed
     * at a particular cadence (STREAM), sent one off on a long-lived channel
     * (POLL), or sent as a one-off retrieval (ONCE).
     * Reference: gNMI Specification Section 3.5
     * </pre>
     */
    public io.grpc.stub.StreamObserver<GNMI.SubscribeRequest> subscribe(
        io.grpc.stub.StreamObserver<GNMI.SubscribeResponse> responseObserver) {
      return asyncBidiStreamingCall(
          getChannel().newCall(METHOD_SUBSCRIBE, getCallOptions()), responseObserver);
    }
  }

  /**
   */
  public static final class gNMIBlockingStub extends io.grpc.stub.AbstractStub<gNMIBlockingStub> {
    private gNMIBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private gNMIBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected gNMIBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new gNMIBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * Capabilities allows the client to retrieve the set of capabilities that
     * is supported by the target. This allows the target to validate the
     * service version that is implemented and retrieve the set of models that
     * the target supports. The models can then be specified in subsequent RPCs
     * to restrict the set of data that is utilized.
     * Reference: gNMI Specification Section 3.2
     * </pre>
     */
    public GNMI.CapabilityResponse capabilities(GNMI.CapabilityRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_CAPABILITIES, getCallOptions(), request);
    }

    /**
     * <pre>
     * Retrieve a snapshot of data from the target. A Get RPC requests that the
     * target snapshots a subset of the data tree as specified by the paths
     * included in the message and serializes this to be returned to the
     * client using the specified encoding.
     * Reference: gNMI Specification Section 3.3
     * </pre>
     */
    public GNMI.GetResponse get(GNMI.GetRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_GET, getCallOptions(), request);
    }

    /**
     * <pre>
     * Set allows the client to modify the state of data on the target. The
     * paths to modified along with the new values that the client wishes
     * to set the value to.
     * Reference: gNMI Specification Section 3.4
     * </pre>
     */
    public GNMI.SetResponse set(GNMI.SetRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_SET, getCallOptions(), request);
    }
  }

  /**
   */
  public static final class gNMIFutureStub extends io.grpc.stub.AbstractStub<gNMIFutureStub> {
    private gNMIFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private gNMIFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected gNMIFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new gNMIFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * Capabilities allows the client to retrieve the set of capabilities that
     * is supported by the target. This allows the target to validate the
     * service version that is implemented and retrieve the set of models that
     * the target supports. The models can then be specified in subsequent RPCs
     * to restrict the set of data that is utilized.
     * Reference: gNMI Specification Section 3.2
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<GNMI.CapabilityResponse> capabilities(
        GNMI.CapabilityRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_CAPABILITIES, getCallOptions()), request);
    }

    /**
     * <pre>
     * Retrieve a snapshot of data from the target. A Get RPC requests that the
     * target snapshots a subset of the data tree as specified by the paths
     * included in the message and serializes this to be returned to the
     * client using the specified encoding.
     * Reference: gNMI Specification Section 3.3
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<GNMI.GetResponse> get(
        GNMI.GetRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_GET, getCallOptions()), request);
    }

    /**
     * <pre>
     * Set allows the client to modify the state of data on the target. The
     * paths to modified along with the new values that the client wishes
     * to set the value to.
     * Reference: gNMI Specification Section 3.4
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<GNMI.SetResponse> set(
        GNMI.SetRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_SET, getCallOptions()), request);
    }
  }

  private static final int METHODID_CAPABILITIES = 0;
  private static final int METHODID_GET = 1;
  private static final int METHODID_SET = 2;
  private static final int METHODID_SUBSCRIBE = 3;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final gNMIImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(gNMIImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_CAPABILITIES:
          serviceImpl.capabilities((GNMI.CapabilityRequest) request,
              (io.grpc.stub.StreamObserver<GNMI.CapabilityResponse>) responseObserver);
          break;
        case METHODID_GET:
          serviceImpl.get((GNMI.GetRequest) request,
              (io.grpc.stub.StreamObserver<GNMI.GetResponse>) responseObserver);
          break;
        case METHODID_SET:
          serviceImpl.set((GNMI.SetRequest) request,
              (io.grpc.stub.StreamObserver<GNMI.SetResponse>) responseObserver);
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
        case METHODID_SUBSCRIBE:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.subscribe(
              (io.grpc.stub.StreamObserver<GNMI.SubscribeResponse>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  private static final class gNMIDescriptorSupplier implements io.grpc.protobuf.ProtoFileDescriptorSupplier {
    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return GNMI.getDescriptor();
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (gNMIGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new gNMIDescriptorSupplier())
              .addMethod(METHOD_CAPABILITIES)
              .addMethod(METHOD_GET)
              .addMethod(METHOD_SET)
              .addMethod(METHOD_SUBSCRIBE)
              .build();
        }
      }
    }
    return result;
  }
}
