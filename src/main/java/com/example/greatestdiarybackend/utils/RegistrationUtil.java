package com.example.greatestdiarybackend.utils;

import com.example.greatestdiarybackend.pattern.Patterns;
import org.apache.commons.lang3.StringUtils;

public class RegistrationUtil {
    public static boolean validateName(String name) {
        if (StringUtils.isBlank(name)) {
            return false;
        }

        if (!(name.length() > 1 && name.length() < 20)) {
            return false;
        }

        return true;
    }

    public static boolean validateEmail(String email) {
        if (StringUtils.isBlank(email)) {
            return false;
        }

//        if (!Patterns.EMAIL_PATTERN.matcher(email).matches()) {
//            return false;
//        }

        return true;
    }

    public static boolean validatePassword(String password, String confirmPassword) {
        if (StringUtils.isBlank(password) || StringUtils.isBlank(confirmPassword)) {
            return false;
        }

        if (!(password.length() > 2 && password.length() < 30)) {
            return false;
        }

        return true;
    }
}
