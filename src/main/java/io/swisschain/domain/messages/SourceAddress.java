package io.swisschain.domain.messages;

public class SourceAddress {
    private String address;
    private String name;
    private String group;

    public SourceAddress() {}

    public SourceAddress(String address, String name, String group) {
        this.address = address;
        this.name = name;
        this.group = group;
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
}
