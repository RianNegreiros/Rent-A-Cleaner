package com.github.riannegreiros.ExpressCleaning.core.exceptions;

import jakarta.persistence.EntityNotFoundException;

public class TokenInTheBlackListException extends EntityNotFoundException {
  public TokenInTheBlackListException(String message) {
    super(message);
  }

  public TokenInTheBlackListException() {
    super("The token entered is invalid");
  }
}
