package io.swisschain.mappers;

import io.swisschain.primitives.NetworkType;
import org.jetbrains.annotations.NotNull;

public final class NetworkTypeMapper {
  public static NetworkType map(
      @NotNull io.swisschain.sirius.vaultApi.generated.common.Common.NetworkType networkType) {
    switch (networkType) {
      case PRIVATE:
        return NetworkType.Private;
      case TEST:
        return NetworkType.Test;
      case PUBLIC:
        return NetworkType.Public;
      default:
        throw new IllegalArgumentException(
            String.format("Unknown network type. %s", networkType.name()));
    }
  }
}
