package com.github.riannegreiros.ExpressCleaning.core.exceptions;

import jakarta.persistence.EntityNotFoundException;

public class ServiceModelNotFoundException extends EntityNotFoundException {
  public ServiceModelNotFoundException(String message) {
    super(message);
  }
}
