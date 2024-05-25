package com.example.greatestdiarybackend.services;

import com.example.greatestdiarybackend.forms.RegistrationForm;
import com.example.greatestdiarybackend.entities.User;

import java.util.Optional;

public interface UserService {
    Optional<User> findUserByEmail(String email);
    User getUserByEmail(String email);
    void save(User user);
    User create(RegistrationForm form);
}
