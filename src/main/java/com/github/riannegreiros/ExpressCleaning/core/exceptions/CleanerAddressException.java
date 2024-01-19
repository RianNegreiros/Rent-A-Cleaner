package com.github.riannegreiros.ExpressCleaning.core.exceptions;

import jakarta.persistence.EntityNotFoundException;

public class CleanerAddressException extends EntityNotFoundException {

    public CleanerAddressException(String message)
    {
        super(message);
    }
}
