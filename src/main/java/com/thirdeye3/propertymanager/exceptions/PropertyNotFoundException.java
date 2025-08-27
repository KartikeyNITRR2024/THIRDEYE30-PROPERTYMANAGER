package com.thirdeye3.propertymanager.exceptions;

public class PropertyNotFoundException extends RuntimeException {
    public PropertyNotFoundException(Long id) {
        super("Property with id=" + id + " not found");
    }
}
