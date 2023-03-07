package com.example.imius.activity;

import com.example.imius.constants.Constants;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator {
    private Pattern pattern;
    private Matcher matcher;

    public EmailValidator() {
        pattern = Pattern.compile(Constants.EMAIL_PATTERN);
    }

    public boolean validate(final String hex){
        matcher = pattern.matcher(hex);
        return matcher.matches();
    }
}
