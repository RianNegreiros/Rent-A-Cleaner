package com.github.riannegreiros.ExpressCleaning.core.exceptions;

import org.springframework.validation.FieldError;

public class WrongPasswordException extends ValidationException {

  public WrongPasswordException(String message, FieldError fieldError) {
    super(message, fieldError);
  }
}
