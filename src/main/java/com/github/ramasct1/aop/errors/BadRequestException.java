package com.github.ramasct1.aop.errors;

/**
 * Bad Request Exception throws 400 status code.
 */
public class BadRequestException extends RuntimeException {

    /**
     * Get Bad Request message .
     *
     * @param message .
     */
    public BadRequestException(String message) {
        super(message);
    }
}

