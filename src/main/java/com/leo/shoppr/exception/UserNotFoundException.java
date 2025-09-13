package com.leo.shoppr.exception;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String key, String value) {
        super("User does not exist with " + key + ": " + value);
    }
}
