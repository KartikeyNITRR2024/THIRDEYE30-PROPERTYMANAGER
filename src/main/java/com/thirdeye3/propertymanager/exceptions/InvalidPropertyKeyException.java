package com.thirdeye3.propertymanager.exceptions;

public class InvalidPropertyKeyException extends RuntimeException {
    public InvalidPropertyKeyException(String key) {
        super("Invalid property key: " + key);
    }
}
