package io.swisschain.sirius;

import io.grpc.CallCredentials;
import io.grpc.Metadata;
import io.grpc.Status;

import java.util.concurrent.Executor;

import static io.grpc.Metadata.ASCII_STRING_MARSHALLER;

public class ApiKeyCredential extends CallCredentials {
  private final String token;

  public ApiKeyCredential(String token) {
    this.token = token;
  }

  @Override
  public void applyRequestMetadata(
      RequestInfo requestInfo, Executor executor, MetadataApplier applier) {
    executor.execute(
        () -> {
          try {
            Metadata headers = new Metadata();
            headers.put(
                Metadata.Key.of("Authorization", ASCII_STRING_MARSHALLER),
                String.format("Bearer %s", this.token));
            applier.apply(headers);
          } catch (Throwable e) {
            applier.fail(Status.UNAUTHENTICATED.withCause(e));
          }
        });
  }

  @Override
  public void thisUsesUnstableApi() {}
}
