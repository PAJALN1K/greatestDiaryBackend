package com.example.greatestdiarybackend.forms;

public class RegistrationForm {
    private String email;
    private String name;
    private String password;
    private String confirmPassword;

    public String getEmail() {
        return email;
    }

    public RegistrationForm setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getName() {
        return name;
    }

    public RegistrationForm setName(String name) {
        this.name = name;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public RegistrationForm setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public RegistrationForm setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        return this;
    }
}
