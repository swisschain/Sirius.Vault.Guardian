package io.swisschain.domain.validators;

public class Validator {
    public String id;
    public String name;
    public String publicKey;

    public Validator() {

    }

    public Validator(String id, String name, String publicKey) {
        this.id = id;
        this.name = name;
        this.publicKey = publicKey;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }
}
