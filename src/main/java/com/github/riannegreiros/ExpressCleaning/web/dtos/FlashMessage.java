package com.github.riannegreiros.ExpressCleaning.web.dtos;

public class FlashMessage {
  private String cssClass;
  private String message;

  public FlashMessage() {
  }

  public FlashMessage(String cssClass, String message) {
    this.cssClass = cssClass;
    this.message = message;
  }

  public String getCssClass() {
    return cssClass;
  }

  public void setCssClass(String cssClass) {
    this.cssClass = cssClass;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
