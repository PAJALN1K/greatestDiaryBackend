package com.example.greatestdiarybackend.models;

public class UserModel {
    private String email;
    private String name;

    public String getEmail() {
        return email;
    }

    public UserModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getName() {
        return name;
    }

    public UserModel setName(String name) {
        this.name = name;
        return this;
    }
}
