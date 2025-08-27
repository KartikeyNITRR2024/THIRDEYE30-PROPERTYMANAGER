package com.thirdeye3.propertymanager.exceptions.handler;

import com.thirdeye3.propertymanager.dtos.Response;
import com.thirdeye3.propertymanager.exceptions.ConfigurationNotFoundException;
import com.thirdeye3.propertymanager.exceptions.InvalidConfigurationPasswordException;
import com.thirdeye3.propertymanager.exceptions.InvalidMachineException;
import com.thirdeye3.propertymanager.exceptions.InvalidPropertyKeyException;
import com.thirdeye3.propertymanager.exceptions.PropertyNotFoundException;
import com.thirdeye3.propertymanager.exceptions.UnauthorizedUpdateException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(PropertyNotFoundException.class)
    public ResponseEntity<Response<Void>> handleNotFound(PropertyNotFoundException ex) {
        return buildResponse(false, HttpStatus.NOT_FOUND.value(), ex.getMessage(), null);
    }

    @ExceptionHandler(UnauthorizedUpdateException.class)
    public ResponseEntity<Response<Void>> handleUnauthorized(UnauthorizedUpdateException ex) {
        return buildResponse(false, HttpStatus.FORBIDDEN.value(), ex.getMessage(), null);
    }

    @ExceptionHandler(InvalidPropertyKeyException.class)
    public ResponseEntity<Response<Void>> handleInvalidKey(InvalidPropertyKeyException ex) {
        return buildResponse(false, HttpStatus.BAD_REQUEST.value(), ex.getMessage(), null);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response<Void>> handleGeneral(Exception ex) {
        return buildResponse(false, HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Unexpected error: " + ex.getMessage(), null);
    }
    
    @ExceptionHandler(ConfigurationNotFoundException.class)
    public ResponseEntity<Response<Void>> handleConfigNotFound(ConfigurationNotFoundException ex) {
        return buildResponse(false, HttpStatus.NOT_FOUND.value(), ex.getMessage(), null);
    }

    @ExceptionHandler(InvalidConfigurationPasswordException.class)
    public ResponseEntity<Response<Void>> handleInvalidPassword(InvalidConfigurationPasswordException ex) {
        return buildResponse(false, HttpStatus.UNAUTHORIZED.value(), ex.getMessage(), null);
    }
    
    @ExceptionHandler(InvalidMachineException.class)
    public ResponseEntity<Response<Void>> handleInvalidPassword(InvalidMachineException ex) {
        return buildResponse(false, HttpStatus.UNAUTHORIZED.value(), ex.getMessage(), null);
    }
    

    private <T> ResponseEntity<Response<T>> buildResponse(
            boolean success, int errorCode, String errorMessage, T body) {
        return ResponseEntity
                .status(errorCode)
                .body(new Response<>(success, errorCode, errorMessage, body));
    }
}
