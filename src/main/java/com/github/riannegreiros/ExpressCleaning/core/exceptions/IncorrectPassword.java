package com.github.riannegreiros.ExpressCleaning.core.exceptions;

import org.springframework.validation.FieldError;

public class IncorrectPassword extends ValidationException {
  public IncorrectPassword(String message, FieldError fieldError) {
    super(message, fieldError);
  }
}
