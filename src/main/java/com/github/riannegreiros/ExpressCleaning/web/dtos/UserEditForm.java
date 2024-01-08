package com.github.riannegreiros.ExpressCleaning.web.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UserEditForm {
  @NotNull
  @Size(min = 3, max = 255)
  private String fullName;

  @NotNull
  @Size(min = 3, max = 255)
  @Email
  private String email;

  public UserEditForm() {
  }

  public UserEditForm(@NotNull @Size(min = 3, max = 255) String fullName,
      @NotNull @Size(min = 3, max = 255) @Email String email) {
    this.fullName = fullName;
    this.email = email;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
}
