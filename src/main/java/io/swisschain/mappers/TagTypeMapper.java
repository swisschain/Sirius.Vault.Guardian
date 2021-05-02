package io.swisschain.mappers;

import io.swisschain.domain.primitives.TagType;
import io.swisschain.sirius.vaultApi.generated.common.Common;
import org.jetbrains.annotations.NotNull;

public class TagTypeMapper {
  public static TagType map(@NotNull Common.TagType tagType) {
    switch (tagType) {
      case TEXT:
        return TagType.Text;
      case NUMBER:
        return TagType.Number;
      default:
        throw new IllegalArgumentException(String.format("Unknown tag type. %s", tagType.name()));
    }
  }
}
