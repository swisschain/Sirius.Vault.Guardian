package io.swisschain.common.durability;

import com.evanlennick.retry4j.backoff.BackoffStrategy;
import com.evanlennick.retry4j.config.RetryConfig;
import com.evanlennick.retry4j.exception.InvalidRetryConfigException;

import java.time.Duration;
import java.util.ArrayList;

public class FixedDelaysBackoffStrategy implements BackoffStrategy {
  private final ArrayList<Duration> delays;

  public FixedDelaysBackoffStrategy(ArrayList<Duration> delays) {
    this.delays = delays;
  }

  @Override
  public Duration getDurationToWait(int numberOfTriesFailed, Duration delayBetweenAttempts) {
    var index = Integer.min(numberOfTriesFailed, delays.size()) - 1;
    return delays.get(index);
  }

  @Override
  public void validateConfig(RetryConfig config) {
    if (null == delays || delays.size() == 0) {
      throw new InvalidRetryConfigException("The delay between retries required!");
    }
  }
}
