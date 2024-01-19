package com.github.riannegreiros.ExpressCleaning.core.enums;

public enum UserType {
  ADMIN(1),
  CLIENT (2),
  CLEANER (3);

  private Integer id;

  UserType(Integer id) {
    this.id = id;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }
}
