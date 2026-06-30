package com.sarthak.empmanagement.exception;

/**
 * Thrown when an Employee or Department with a given ID doesn't exist.
 * Caught by GlobalExceptionHandler and converted into a clean 404 response.
 */
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
