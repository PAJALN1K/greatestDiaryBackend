package com.example.greatestdiarybackend.utils;

import com.example.greatestdiarybackend.services.AuthenticatedUserService;
import com.example.greatestdiarybackend.services.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordSettingsUtil {
    private final PasswordEncoder encoder;
    private final AuthenticatedUserService authenticatedUserService;

    @Autowired
    public PasswordSettingsUtil(
            PasswordEncoder encoder,
            AuthenticatedUserService authenticatedUserService
    ) {
        this.encoder = encoder;
        this.authenticatedUserService = authenticatedUserService;
    }

    public boolean validateOldPassword(String oldPassword, String newPassword) {
        if (StringUtils.isBlank(oldPassword) || StringUtils.isBlank(newPassword)) {
            return false;
        }

        if (!encoder.matches(oldPassword, authenticatedUserService.getAuthenticatedUser().getPassword())) {
            return false;
        }

        if (encoder.matches(oldPassword, encoder.encode(newPassword))) {
            return false;
        }

        return true;
    }

    public boolean validateNewPassword(String newPassword) {
        if (StringUtils.isBlank(newPassword)) {
            return false;
        }

        if (!(newPassword.length() > 2 && newPassword.length() < 30)) {
            return false;
        }

        return true;
    }

    public boolean validateConfirmPassword(String confirmPassword, String newPassword) {
        if (StringUtils.isBlank(confirmPassword) || StringUtils.isBlank(newPassword)) {
            return false;
        }

        if (!encoder.matches(confirmPassword, encoder.encode(newPassword))) {
            return false;
        }

        return true;
    }
}
