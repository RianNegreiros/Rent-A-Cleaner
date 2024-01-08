package com.github.riannegreiros.ExpressCleaning.core.exceptions;

import org.springframework.validation.FieldError;

public class ValidationException extends RuntimeException {
  private final FieldError fieldError;

  public ValidationException(String message, FieldError fieldError) {
    super(message);
    this.fieldError = fieldError;
  }

  public FieldError getFieldError() {
    return fieldError;
  }
}
