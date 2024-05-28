package com.example.greatestdiarybackend.utils.valid;

import com.example.greatestdiarybackend.pattern.Patterns;
import com.example.greatestdiarybackend.services.user.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RegistrationUtil {
    private final UserService userService;

    @Autowired
    public RegistrationUtil(UserService userService) {
        this.userService = userService;
    }

    public boolean validateName(String name) {
        if (StringUtils.isBlank(name)) {
            return false;
        }

        if (!(name.length() > 1 && name.length() < 20)) {
            return false;
        }

        if (userService.findUserByName(name).isPresent()) {
            return false;
        }

        return true;
    }

    public boolean validateEmail(String email) {
        if (StringUtils.isBlank(email)) {
            return false;
        }

        if (!Patterns.EMAIL_PATTERN.matcher(email).matches()) {
            return false;
        }

        if (userService.findUserByEmail(email).isPresent()) {
            return false;
        }

        return true;
    }

    public boolean validatePassword(
            String password,
            String confirmPassword
    ) {
        if (StringUtils.isBlank(password) || StringUtils.isBlank(confirmPassword)) {
            return false;
        }

        if (!(password.length() > 2 && password.length() < 30)) {
            return false;
        }

        if (!password.equals(confirmPassword)) {
            return false;
        }

        return true;
    }
}
