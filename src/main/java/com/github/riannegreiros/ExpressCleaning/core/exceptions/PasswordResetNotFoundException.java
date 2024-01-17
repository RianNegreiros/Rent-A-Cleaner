package com.github.riannegreiros.ExpressCleaning.core.exceptions;

import jakarta.persistence.EntityNotFoundException;

public class PasswordResetNotFoundException extends EntityNotFoundException {
  public PasswordResetNotFoundException() {}
  public PasswordResetNotFoundException(String message) {
    super(message);
  }
}
