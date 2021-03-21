package io.swisschain.common.durability;

import com.evanlennick.retry4j.CallExecutorBuilder;
import com.evanlennick.retry4j.Status;
import com.evanlennick.retry4j.config.RetryConfig;
import com.evanlennick.retry4j.config.RetryConfigBuilder;
import com.evanlennick.retry4j.listener.RetryListener;
import io.grpc.StatusException;
import io.grpc.StatusRuntimeException;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.SQLTimeoutException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.Callable;

public final class RetryPolicies {
  protected static final String UniqueViolationErrorCode = "23505";

  private static final RetryConfig defaultRepositoryRetryConfig;
  private static final RetryConfig defaultGrpcRetryConfig;
  private static final RetryConfig defaultRestRetryConfig;

  static {
    var delays =
        new ArrayList<>(
            Arrays.asList(
                Duration.ofMillis(100),
                Duration.ofSeconds(1),
                Duration.ofSeconds(4),
                Duration.ofSeconds(16),
                Duration.ofSeconds(32),
                Duration.ofSeconds(60)));

    defaultRepositoryRetryConfig =
        new RetryConfigBuilder()
            .retryOnCustomExceptionLogic(
                exception -> {
                  if (SQLTimeoutException.class.isAssignableFrom(exception.getClass())) {
                    return true;
                  }
                  if (SQLException.class.isAssignableFrom(exception.getClass())) {
                    var sqlException = ((SQLException) exception);
                    return sqlException.getSQLState() == null
                        || !sqlException.getSQLState().equals(UniqueViolationErrorCode);
                  }
                  return false;
                })
            .withMaxNumberOfTries(delays.size() + 1)
            .withBackoffStrategy(new FixedDelaysBackoffStrategy(delays))
            .build();

    defaultGrpcRetryConfig =
        new RetryConfigBuilder()
            .retryOnSpecificExceptions(
                StatusException.class, StatusRuntimeException.class, IOException.class)
            .withMaxNumberOfTries(7)
            .withDelayBetweenTries(Duration.ofMillis(500))
            .withExponentialBackoff()
            .build();

    defaultRestRetryConfig =
        new RetryConfigBuilder()
            .retryOnSpecificExceptions(IllegalStateException.class, IOException.class)
            .withMaxNumberOfTries(7)
            .withDelayBetweenTries(Duration.ofMillis(500))
            .withExponentialBackoff()
            .build();
  }

  @SuppressWarnings("unchecked")
  public static <T> Status<T> ExecuteWithDefaultRepositoryConfig(
      RetryListener<T> afterFailedTryListener, Callable<T> func) {
    return new CallExecutorBuilder<T>()
        .config(defaultRepositoryRetryConfig)
        .afterFailedTryListener(afterFailedTryListener)
        .build()
        .execute(func);
  }

  @SuppressWarnings("unchecked")
  public static <T> Status<T> ExecuteWithDefaultGrpcConfig(
      RetryListener<T> afterFailedTryListener, Callable<T> func) {
    return new CallExecutorBuilder<T>()
        .config(defaultGrpcRetryConfig)
        .afterFailedTryListener(afterFailedTryListener)
        .build()
        .execute(func);
  }

  @SuppressWarnings("unchecked")
  public static <T> Status<T> ExecuteWithDefaultRestConfig(
      RetryListener<T> afterFailedTryListener, Callable<T> func) {
    return new CallExecutorBuilder<T>()
        .config(defaultRestRetryConfig)
        .afterFailedTryListener(afterFailedTryListener)
        .build()
        .execute(func);
  }
}
