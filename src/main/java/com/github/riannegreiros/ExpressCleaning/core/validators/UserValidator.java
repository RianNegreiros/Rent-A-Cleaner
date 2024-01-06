package com.github.riannegreiros.ExpressCleaning.core.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;

import com.github.riannegreiros.ExpressCleaning.core.exceptions.UserRegisteredException;
import com.github.riannegreiros.ExpressCleaning.core.models.User;
import com.github.riannegreiros.ExpressCleaning.core.repositories.UserRepository;

@Component
public class UserValidator {
  @Autowired
  private UserRepository repository;

  public void validate(User user) {
    validateEmail(user);
  }

  private void validateEmail(User user) {
    if (repository.isEmailInUse(user)) {
      var mensagem = "There is already a user registered with this e-mail address";
      var fieldError = new FieldError(user.getClass().getName(), "email", user.getEmail(), false, null, null,
          mensagem);

      throw new UserRegisteredException(mensagem, fieldError);
    }
  }
}
