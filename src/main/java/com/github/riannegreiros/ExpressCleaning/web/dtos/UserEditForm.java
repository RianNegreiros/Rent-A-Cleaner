package com.github.riannegreiros.ExpressCleaning.web.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UserEditForm {
  @NotNull
  @Size(min = 3, max = 255)
  private String fullname;

  @NotNull
  @Size(min = 3, max = 255)
  @Email
  private String email;

  public UserEditForm() {
  }

  public UserEditForm(@NotNull @Size(min = 3, max = 255) String fullname,
      @NotNull @Size(min = 3, max = 255) @Email String email) {
    this.fullname = fullname;
    this.email = email;
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
}
