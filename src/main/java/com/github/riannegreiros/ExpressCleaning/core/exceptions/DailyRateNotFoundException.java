package com.github.riannegreiros.ExpressCleaning.core.exceptions;

import jakarta.persistence.EntityNotFoundException;

public class DailyRateNotFoundException extends EntityNotFoundException {
  public DailyRateNotFoundException(String message) {
    super(message);
  }
}
