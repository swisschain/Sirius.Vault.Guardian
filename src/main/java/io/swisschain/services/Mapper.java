package io.swisschain.services;

import com.google.protobuf.Timestamp;
import io.swisschain.contracts.common.*;
import io.swisschain.contracts.smart_contracts.DataMetamodel;
import io.swisschain.contracts.smart_contracts.DataType;
import io.swisschain.contracts.smart_contracts.FunctionArgument;
import io.swisschain.domain.primitives.NetworkType;
import io.swisschain.domain.primitives.TagType;
import io.swisschain.sirius.vaultApi.generated.common.Common;
import io.swisschain.sirius.vaultApi.generated.smart_contracts.SmartContracts;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

public final class Mapper {
  public static Blockchain map(Common.Blockchain blockchain) {
    return new Blockchain(
        blockchain.getId(), blockchain.getProtocolId(), map(blockchain.getNetworkType()));
  }

  public static BrokerAccount map(Common.BrokerAccount brokerAccount) {
    return brokerAccount != null
        ? new BrokerAccount(brokerAccount.getId(), brokerAccount.getName())
        : null;
  }

  public static Account map(Common.Account account) {
    return account != null
        ? new Account(
            account.getId(),
            account.hasReferenceId() ? account.getReferenceId().getValue() : null,
            map(account.getUser()))
        : null;
  }

  public static User map(Common.User user) {
    return user != null ? new User(user.getId(), user.getNativeId()) : null;
  }

  public static Unit map(Common.Unit unit) {
    return unit != null
        ? new Unit(map(unit.getAsset()), new BigDecimal(unit.getAmount().getValue()))
        : null;
  }

  public static Asset map(Common.Asset asset) {
    return new Asset(
        asset.getId(),
        asset.getSymbol(),
        asset.hasAddress() ? asset.getAddress().getValue() : null);
  }

  public static RequestContext map(Common.RequestContext requestContext) {
    return new RequestContext(
        requestContext.hasUserId() ? requestContext.getUserId().getValue() : null,
        requestContext.hasApiKeyId() ? requestContext.getApiKeyId().getValue() : null,
        requestContext.hasIp() ? requestContext.getIp().getValue() : null,
        map(requestContext.getTimestamp()));
  }

  public static Instant map(Timestamp timestamp) {
    return Instant.ofEpochSecond(timestamp.getSeconds(), timestamp.getNanos());
  }

  public static List<FunctionArgument> map(
      List<SmartContracts.FunctionArgument> functionArguments) {
    return functionArguments.stream().map(Mapper::map).collect(Collectors.toList());
  }

  public static FunctionArgument map(SmartContracts.FunctionArgument functionArgument) {
    return new FunctionArgument(
        map(functionArgument.getDataModel()),
        map(functionArgument.getComponentsList()),
        functionArgument.hasValue() ? functionArgument.getValue().getValue() : null);
  }

  public static List<DataMetamodel> mapDataMetamodels(
      List<SmartContracts.DataMetamodel> dataMetamodels) {
    return dataMetamodels.stream().map(Mapper::map).collect(Collectors.toList());
  }

  public static DataMetamodel map(SmartContracts.DataMetamodel dataMetamodel) {
    return new DataMetamodel(
        dataMetamodel.getName(),
        map(dataMetamodel.getDataType()),
        dataMetamodel.getSize(),
        dataMetamodel.getScale(),
        dataMetamodel.getIsArray(),
        dataMetamodel.hasNativeType() ? dataMetamodel.getNativeType().getValue() : null,
        mapDataMetamodels(dataMetamodel.getComponentsList()),
        dataMetamodel.getIsRequired());
  }

  public static DataType map(SmartContracts.DataType dataType) {
    switch (dataType) {
      case COMPOSITE:
        return DataType.Composite;
      case NATIVE:
        return DataType.Native;
      case VOID:
        return DataType.Void;
      case STRING:
        return DataType.String;
      case INT:
        return DataType.Int;
      case UINT:
        return DataType.UInt;
      case BOOL:
        return DataType.Bool;
      case BYTES:
        return DataType.Bytes;
      case DECIMAL:
        return DataType.Decimal;
      case ADDRESS:
        return DataType.Address;
      case TIMESTAMP:
        return DataType.Timestamp;
    }
    throw new IllegalArgumentException(String.format("Unknown data type %s.", dataType.name()));
  }

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
