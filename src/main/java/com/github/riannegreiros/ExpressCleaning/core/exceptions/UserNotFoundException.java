package com.github.riannegreiros.ExpressCleaning.core.exceptions;

import jakarta.persistence.EntityNotFoundException;

public class UserNotFoundException extends EntityNotFoundException {
  public UserNotFoundException(String message) {
    super(message);
  }
}
