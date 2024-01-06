package com.github.riannegreiros.ExpressCleaning.web.dtos;

import com.github.riannegreiros.ExpressCleaning.web.interfaces.IConfirmPassword;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UserRegisterForm implements IConfirmPassword {
  @NotNull
  @Size(min = 3, max = 255)
  private String fullname;

  @NotNull
  @Size(min = 3, max = 255)
  @Email
  private String email;

  @NotNull
  @NotEmpty
  private String password;

  @NotNull
  @NotEmpty
  private String confirmPassword;

  public UserRegisterForm() {
  }

  public UserRegisterForm(@NotNull @Size(min = 3, max = 255) String fullname,
      @NotNull @Size(min = 3, max = 255) @Email String email, @NotNull @NotEmpty String password,
      @NotNull @NotEmpty String confirmPassword) {
    this.fullname = fullname;
    this.email = email;
    this.password = password;
    this.confirmPassword = confirmPassword;
  }

  public String getFullname() {
    return fullname;
  }

  public void setFullname(String fullname) {
    this.fullname = fullname;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getConfirmPassword() {
    return confirmPassword;
  }

  public void setConfirmPassword(String confirmPassword) {
    this.confirmPassword = confirmPassword;
  }
}
