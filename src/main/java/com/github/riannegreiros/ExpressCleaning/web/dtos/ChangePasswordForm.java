package com.github.riannegreiros.ExpressCleaning.web.dtos;

import com.github.riannegreiros.ExpressCleaning.web.interfaces.IConfirmPassword;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class ChangePasswordForm implements IConfirmPassword {
  @NotNull
  @NotEmpty
  private String oldPassword;

  @NotNull
  @NotEmpty
  private String password;

  @NotNull
  @NotEmpty
  private String confirmPassword;

  public ChangePasswordForm() {
  }

  public ChangePasswordForm(@NotNull @NotEmpty String oldPassword, @NotNull @NotEmpty String password,
      @NotNull @NotEmpty String confirmPassword) {
    this.oldPassword = oldPassword;
    this.password = password;
    this.confirmPassword = confirmPassword;
  }

  public String getOldPassword() {
    return oldPassword;
  }

  public void setOldPassword(String oldPassword) {
    this.oldPassword = oldPassword;
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
