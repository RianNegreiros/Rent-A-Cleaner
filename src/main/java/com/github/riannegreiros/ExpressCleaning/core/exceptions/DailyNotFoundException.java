package com.github.riannegreiros.ExpressCleaning.core.exceptions;

import jakarta.persistence.EntityNotFoundException;

public class DailyNotFoundException extends EntityNotFoundException {
    public DailyNotFoundException(String message) {
        super(message);
    }
}
