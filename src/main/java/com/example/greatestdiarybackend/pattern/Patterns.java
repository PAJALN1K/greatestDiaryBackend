package com.example.greatestdiarybackend.pattern;

import java.util.regex.Pattern;

public class Patterns {
    public static String EMAIL = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$\n";
    public static Pattern EMAIL_PATTERN = Pattern.compile(EMAIL);
}
cvfd