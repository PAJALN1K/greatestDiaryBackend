package com.example.greatestdiarybackend.services.authentication;

import com.example.greatestdiarybackend.entities.User;
import jakarta.validation.constraints.NotNull;
import org.springframework.security.core.Authentication;

public interface AuthenticatedUserService {
    void setUserAuthentication(String username);
    void setAuthentication(Authentication authentication);
    User getAuthenticatedUser();
}
