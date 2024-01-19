package com.github.riannegreiros.ExpressCleaning.core.validators;

import com.github.riannegreiros.ExpressCleaning.core.exceptions.UserRegisteredException;
import com.github.riannegreiros.ExpressCleaning.core.exceptions.ValidationException;
import com.github.riannegreiros.ExpressCleaning.core.models.User;
import com.github.riannegreiros.ExpressCleaning.core.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;

@Component
public class UserValidator {
  @Autowired
  private UserRepository repository;

  public void validate(User user) {
    validateEmail(user);
  }

  private void validateEmail(User user) {
    if (repository.isEmailAlreadyRegistered(user)) {
      var message = "There is already a user registered with this e-mail address";
      var fieldError = new FieldError(user.getClass().getName(), "email", user.getEmail(), false, null, null, message);

      throw new UserRegisteredException(message, fieldError);
    }

    validateCpf(user);
  }

  private void validateCpf(User user) {
    if (repository.isCpfIsAlreadyRegistered(user)) {
      var message = "There is already a user registered with this cpf";
      var fieldError = new FieldError(user.getClass().getName(), "cpf", user.getCpf(), false, null, null, message);

      throw new UserRegisteredException(message, fieldError);
    }

    validatePixKey(user);
  }

  private void validatePixKey(User user) {
    if (repository.isPixKeyAlreadyRegistered(user)) {
      var message = "There is already a user registered with this pix key";
      var fieldError = new FieldError(user.getClass().getName(), "pixKey", user.getPixKey(), false, null, null, message);

      throw new UserRegisteredException(message, fieldError);
    }

    if (user.isCleaner() && user.getPixKey() == null) {
      var message = "HOUSEKEEPER user must have the pix key";
      var fieldError = new FieldError(user.getClass().getName(), "pixKey", user.getPixKey(), false, null, null, message);

      throw new ValidationException(message, fieldError);
    }
  }
}