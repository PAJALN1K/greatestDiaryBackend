package com.example.greatestdiarybackend.services;

import com.example.greatestdiarybackend.details.CustomUserDetailsService;
import com.example.greatestdiarybackend.entities.User;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class AuthenticatedUserServiceImpl implements AuthenticatedUserService {
    private static final Logger logger = Logger.getLogger(AuthenticatedUserServiceImpl.class.getName());

    private final UserService userService;
    private final CustomUserDetailsService customUserDetailsService;

    @Autowired
    public AuthenticatedUserServiceImpl(
            UserService userService,
            CustomUserDetailsService customUserDetailsService
    ) {
        this.userService = userService;
        this.customUserDetailsService = customUserDetailsService;
    }

    @Override
    public void setUserAuthentication(@NotNull String username) {
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
        setAuthentication(
                new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                )
        );

        logger.log(Level.INFO, "Аутентификация успешно установлена для пользователя {0}", userDetails.getUsername());
    }

    @Override
    public void setAuthentication(@NotNull Authentication authentication) {
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    @Override
    public User getAuthenticatedUser() {
        Authentication authenticatedUserContext = SecurityContextHolder.getContext().getAuthentication();

        return userService.getUserByEmail(authenticatedUserContext.getName());
    }
}
