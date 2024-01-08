package com.github.riannegreiros.ExpressCleaning.api.dtos.responses;

import java.time.LocalDateTime;

public class ErrorResponse {
  private Integer status;

  private LocalDateTime timestamp;

  private String message;

  private String path;

  public ErrorResponse() {
  }

  public ErrorResponse(Integer status, LocalDateTime timestamp, String message, String path) {
    this.status = status;
    this.timestamp = timestamp;
    this.message = message;
    this.path = path;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public LocalDateTime getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(LocalDateTime timestamp) {
    this.timestamp = timestamp;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }

  public static ErrorResponseBuilder builder() {
    return new ErrorResponseBuilder();
  }

  public static class ErrorResponseBuilder {
    private Integer status;
    private LocalDateTime timestamp;
    private String message;
    private String path;

    private ErrorResponseBuilder() {
    }

    public ErrorResponseBuilder status(Integer status) {
      this.status = status;
      return this;
    }

    public ErrorResponseBuilder timestamp(LocalDateTime timestamp) {
      this.timestamp = timestamp;
      return this;
    }

    public ErrorResponseBuilder message(String message) {
      this.message = message;
      return this;
    }

    public ErrorResponseBuilder path(String path) {
      this.path = path;
      return this;
    }

    public ErrorResponse build() {
      ErrorResponse errorResponse = new ErrorResponse();
      errorResponse.setStatus(status);
      errorResponse.setTimestamp(timestamp);
      errorResponse.setMessage(message);
      errorResponse.setPath(path);
      return errorResponse;
    }
  }
}
