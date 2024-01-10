package com.github.riannegreiros.ExpressCleaning.core.services.token.exceptions;

public class TokenServiceException extends RuntimeException {

    public TokenServiceException() {}

    public TokenServiceException(String message) {
        super(message);
    }

}