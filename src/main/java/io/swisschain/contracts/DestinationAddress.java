package io.swisschain.contracts;

import io.swisschain.primitives.TagType;

import java.util.Objects;

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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    DestinationAddress that = (DestinationAddress) o;
    return address.equals(that.address)
        && Objects.equals(name, that.name)
        && Objects.equals(group, that.group)
        && Objects.equals(tag, that.tag)
        && tagType == that.tagType;
  }

  @Override
  public int hashCode() {
    return Objects.hash(address, name, group, tag, tagType);
  }
}
