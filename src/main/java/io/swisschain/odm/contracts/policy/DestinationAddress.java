package io.swisschain.odm.contracts.policy;

import io.swisschain.primitives.TagType;

public class DestinationAddress {
  public String address;
  public String name;
  public String group;
  public String tag;
  public TagType tagType;

  public DestinationAddress() {}

  public DestinationAddress(
      String address, String name, String group, String tag, TagType tagType) {
    this.address = address;
    this.name = name;
    this.group = group;
    this.tag = tag;
    this.tagType = tagType;
  }
}
