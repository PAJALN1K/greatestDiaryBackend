package com.example.greatestdiarybackend.utils.valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.parameters.P;

import java.util.Objects;

public class TargetUtil {
    public static boolean validateTitile(String title) {
        if (StringUtils.isBlank(title)) {
            return false;
        }

        if (title.length() > 255) {
            return false;
        }

        return true;
    }

    public static boolean validateDescription(String description) {
        if (StringUtils.isBlank(description)) {
            return false;
        }

        if (description.length() > 255) {
            return false;
        }

        return true;
    }

    public static boolean validatePriority(Integer priority) {
        if (priority == null) {
            return false;
        }

        if (priority < 1 || priority > 10) {
            return false;
        }

        return true;
    }
}
