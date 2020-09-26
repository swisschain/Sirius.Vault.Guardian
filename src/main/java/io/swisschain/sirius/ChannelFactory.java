package io.swisschain.sirius;

import io.grpc.*;

import static io.grpc.Metadata.ASCII_STRING_MARSHALLER;

public final class ChannelFactory {

  public static ManagedChannel create(String host, int port, boolean ssl, String apiKey) {
    if (ssl) {
      return ManagedChannelBuilder.forAddress(host, port)
          .useTransportSecurity()
          .intercept(metadataInterceptor(apiKey))
          .build();
    }

    return ManagedChannelBuilder.forAddress(host, port)
        .usePlaintext()
        .intercept(metadataInterceptor(apiKey))
        .build();
  }

  private static ClientInterceptor metadataInterceptor(String apiKey) {
    return new ClientInterceptor() {
      @Override
      public <ReqT, RespT> ClientCall<ReqT, RespT> interceptCall(
          final MethodDescriptor<ReqT, RespT> method, CallOptions callOptions, final Channel next) {

        return new ClientInterceptors.CheckedForwardingClientCall<>(
            next.newCall(method, callOptions)) {
          @Override
          protected void checkedStart(Listener<RespT> responseListener, Metadata headers) {
            headers.put(
                Metadata.Key.of("Authorization", ASCII_STRING_MARSHALLER),
                String.format("Bearer %s", apiKey));
            delegate().start(responseListener, headers);
          }
        };
      }
    };
  }
}
