package com.github.riannegreiros.ExpressCleaning.core.exceptions;

import org.springframework.validation.FieldError;

public class UserRegisteredException extends ValidationException {

  public UserRegisteredException(String message, FieldError fieldError) {
    super(message, fieldError);
  }
}
