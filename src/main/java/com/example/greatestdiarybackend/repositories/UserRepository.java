package com.example.greatestdiarybackend.repositories;

import com.example.greatestdiarybackend.entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
