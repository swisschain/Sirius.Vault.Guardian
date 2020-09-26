package io.swisschain.domain.transfers;

import io.swisschain.primitives.TagType;

public class DestinationAddress {
  private String address;
  private String name;
  private String group;
  private String tag;
  private TagType tagType;

  public DestinationAddress() {}

  public DestinationAddress(
      String address, String name, String group, String tag, TagType tagType) {
    this.address = address;
    this.name = name;
    this.group = group;
    this.tag = tag;
    this.tagType = tagType;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getGroup() {
    return group;
  }

  public void setGroup(String group) {
    this.group = group;
  }

  public String getTag() {
    return tag;
  }

  public void setTag(String tag) {
    this.tag = tag;
  }

  public TagType getTagType() {
    return tagType;
  }

  public void setTagType(TagType tagType) {
    this.tagType = tagType;
  }
}
