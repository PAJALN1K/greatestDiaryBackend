package com.example.greatestdiarybackend.pattern;

import java.util.regex.Pattern;

public class Patterns {
    public static String EMAIL = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
            + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
    public static Pattern EMAIL_PATTERN = Pattern.compile(EMAIL);
}