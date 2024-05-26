package com.example.greatestdiarybackend.services;

import com.example.greatestdiarybackend.forms.RegistrationForm;
import com.example.greatestdiarybackend.entities.User;

import java.util.Optional;

public interface UserService {
    Optional<User> findUserByEmail(String email);
    Optional<User> findUserByName(String name);
    User getUserByEmail(String email);
    User getUserByName(String name);
    void save(User user);
    User create(RegistrationForm form);
    void updatePassword(String oldPassword, User authenticatedUser);
}
