package com.thirdeye3.propertymanager.exceptions;

public class UnauthorizedUpdateException extends RuntimeException {
    public UnauthorizedUpdateException() {
        super("You are not authorized to update properties");
    }
}
