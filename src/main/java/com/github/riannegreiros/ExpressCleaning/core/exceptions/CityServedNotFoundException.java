package com.github.riannegreiros.ExpressCleaning.core.exceptions;

import jakarta.persistence.EntityNotFoundException;

public class CityServedNotFoundException extends EntityNotFoundException {
    public CityServedNotFoundException(String message) {
        super(message);
    }
}
