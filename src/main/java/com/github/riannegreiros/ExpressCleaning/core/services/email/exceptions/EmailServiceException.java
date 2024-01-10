package com.github.riannegreiros.ExpressCleaning.core.services.email.exceptions;

public class EmailServiceException extends RuntimeException {

    public EmailServiceException() {}

    public EmailServiceException(String message) {
        super(message);
    }

}