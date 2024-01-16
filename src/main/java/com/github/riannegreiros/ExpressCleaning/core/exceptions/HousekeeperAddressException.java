package com.github.riannegreiros.ExpressCleaning.core.exceptions;

import jakarta.persistence.EntityNotFoundException;

public class HousekeeperAddressException extends EntityNotFoundException {

    public HousekeeperAddressException(String message)
    {
        super(message);
    }
}
