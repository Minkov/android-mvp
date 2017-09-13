package com.example.doncho.androidmvpsample.data.models;

/**
 * Created by doncho on 9/12/17.
 */

public class Superhero {
    private String name;
    private String id;
    private String secretIdentity;

    public Superhero() {
        this(null, null);
    }

    public Superhero(String name, String secretIdentity) {
        this(null, name, secretIdentity);
    }

    public Superhero(String id, String name, String secretIdentity) {
        setId(id);
        setName(name);
        setSecretIdentity(secretIdentity);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setSecretIdentity(String secretIdentity) {
        this.secretIdentity = secretIdentity;
    }

    public String getSecretIdentity() {
        return secretIdentity;
    }
}
