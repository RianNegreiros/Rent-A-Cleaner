package com.github.riannegreiros.ExpressCleaning.web.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;

import com.github.riannegreiros.ExpressCleaning.core.enums.UserType;
import com.github.riannegreiros.ExpressCleaning.core.exceptions.PasswordsDoNotMatchException;
import com.github.riannegreiros.ExpressCleaning.core.exceptions.UserNotFoundException;
import com.github.riannegreiros.ExpressCleaning.core.models.User;
import com.github.riannegreiros.ExpressCleaning.core.repositories.UserRepository;
import com.github.riannegreiros.ExpressCleaning.core.validators.UserValidator;
import com.github.riannegreiros.ExpressCleaning.web.dtos.ChangePasswordForm;
import com.github.riannegreiros.ExpressCleaning.web.dtos.UserEditForm;
import com.github.riannegreiros.ExpressCleaning.web.dtos.UserRegisterForm;
import com.github.riannegreiros.ExpressCleaning.web.interfaces.IConfirmPassword;
import com.github.riannegreiros.ExpressCleaning.web.mappers.UserMapper;

@Service
public class UserService {

  @Autowired
  private UserRepository repository;

  @Autowired
  private UserMapper mapper;

  @Autowired
  private UserValidator validator;

  public List<User> getAll() {
    return repository.findAll();
  }

  public User register(UserRegisterForm form) {
    validateConfirmPassword(form);

    var model = mapper.toModel(form);

    model.setPassword(form.getPassword());
    model.setUserType(UserType.ADMIN);

    validator.validate(model);

    return repository.save(model);
  }

  public User getById(Long id) {
    var message = String.format("User with ID %d not found", id);

    return repository.findById(id)
        .orElseThrow(() -> new UserNotFoundException(message));
  }

  public User buscarPorEmail(String email) {
    var message = String.format("User with email %s not found", email);

    return repository.findByEmail(email)
        .orElseThrow(() -> new UserNotFoundException(message));
  }

  public UserEditForm getFormById(Long id) {
    var user = getById(id);

    return mapper.toForm(user);
  }

  public User editar(UserEditForm form, Long id) {
    var User = getById(id);

    var model = mapper.toModel(form);
    model.setId(User.getId());
    model.setPassword(User.getPassword());
    model.setUserType(User.getUserType());

    validator.validate(model);

    return repository.save(model);
  }

  public void deleteById(Long id) {
    var User = getById(id);

    repository.delete(User);
  }

  public void alterarSenha(ChangePasswordForm form, String email) {
    var user = buscarPorEmail(email);

    validateConfirmPassword(form);

    var password = form.getPassword();

    user.setPassword(password);

    repository.save(user);
  }

  private void validateConfirmPassword(IConfirmPassword obj) {
    var password = obj.getPassword();
    var confirmPassword = obj.getConfirmPassword();

    if (!password.equals(confirmPassword)) {
      var message = "The two password fields don't match";
      var fieldError = new FieldError(obj.getClass().getName(), "confirmPassword", obj.getConfirmPassword(), false,
          null, null, message);

      throw new PasswordsDoNotMatchException(message, fieldError);
    }
  }
}
