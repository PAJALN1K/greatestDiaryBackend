package com.example.greatestdiarybackend.utils.valid;

import org.apache.commons.lang3.StringUtils;

public class NoteUtil {
    public static boolean validateDescription(String description) {
        if (StringUtils.isBlank(description)) {
            return false;
        }

        if (description.length() > 4000) {
            return false;
        }

        return true;
    }

    public static boolean validateLocation(String location) {
        if (StringUtils.isBlank(location)) {
            return false;
        }

        if (location.length() > 255) {
            return false;
        }

        return true;
    }

    public static boolean validateDate(String date) {
        if (StringUtils.isBlank(date)) {
            return false;
        }

        if (date.length() > 255) {
            return false;
        }

        return true;
    }

    public static boolean validateEmojiName(String emojiName) {
        if (StringUtils.isBlank(emojiName)) {
            return false;
        }

        if (emojiName.length() > 255) {
            return false;
        }

        return true;
    }
}
