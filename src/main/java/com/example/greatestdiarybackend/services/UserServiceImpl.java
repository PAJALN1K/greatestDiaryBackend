package com.example.greatestdiarybackend.services;

import com.example.greatestdiarybackend.forms.RegistrationForm;
import com.example.greatestdiarybackend.entities.roles.Role;
import com.example.greatestdiarybackend.entities.User;
import com.example.greatestdiarybackend.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class UserServiceImpl implements UserService{
    private static final Logger logger = Logger.getLogger(UserServiceImpl.class.getName());

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Optional<User> findUserByName(String name) {
        return userRepository.findByName(name);
    }

    @Override
    public User getUserByEmail(String email) {
        return findUserByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("Пользователя с почтой '" + email + "' не существует!"));
    }

    @Override
    public User getUserByName(String name) {
        return findUserByName(name)
                .orElseThrow(() -> new EntityNotFoundException("Пользователя c ником '" + name + "' не существует!"));
    }

    @Override
    public void save(User user) {
        userRepository.save(user);

        logger.log(Level.INFO, "Пользователь с почтой {0} успешно сохранен", user.getEmail());
    }

    @Override
    public User create(RegistrationForm form) {
        User createdUser = new User()
                .setEmail(form.getEmail())
                .setName(form.getName())
                .setPassword(encoder.encode(form.getPassword()))
                .setRole(Role.USER.getAuthority())
                .setEnable(true)
                .setNonLocked(true);
        logger.log(Level.INFO, "Пользователь с именем {0} успешно создан", createdUser.getName());

        return createdUser;
    }

    @Override
    public void updatePassword(String oldPassword, User authenticatedUser) {
        User userAfterUpdatePassword = authenticatedUser.setPassword(encoder.encode(oldPassword));
        save(userAfterUpdatePassword);
    }
}
