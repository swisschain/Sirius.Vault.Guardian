package io.swisschain.domain.messages;

public class DestinationAddress {
    private String address;
    private String name;
    private String group;
    private String tag;
    private String tagType;

    public DestinationAddress() {}

    public DestinationAddress(
            String address, String name, String group, String tag, String tagType) {
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

    public String getTagType() {
        return tagType;
    }

    public void setTagType(String tagType) {
        this.tagType = tagType;
    }
}
