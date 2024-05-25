package com.example.greatestdiarybackend.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String name;
    private String password;
    private String role;
    private boolean enable;
    private boolean nonLocked;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getName() {
        return name;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getRole() {
        return role;
    }

    public User setRole(String role) {
        this.role = role;
        return this;
    }

    public boolean isEnable() {
        return enable;
    }

    public User setEnable(boolean enable) {
        this.enable = enable;
        return this;
    }

    public boolean isNonLocked() {
        return nonLocked;
    }

    public User setNonLocked(boolean nonLocked) {
        this.nonLocked = nonLocked;
        return this;
    }
}
