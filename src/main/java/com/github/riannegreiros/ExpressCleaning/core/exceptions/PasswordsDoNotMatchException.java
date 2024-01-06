package com.github.riannegreiros.ExpressCleaning.core.exceptions;

import org.springframework.validation.FieldError;

public class PasswordsDoNotMatchException extends ValidationException {

  public PasswordsDoNotMatchException(String message, FieldError fieldError) {
    super(message, fieldError);
  }
}
