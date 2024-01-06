package com.github.riannegreiros.ExpressCleaning.api.dtos.responses;

import java.time.LocalDateTime;

public class ErrorResponse {
  private Integer status;

  private LocalDateTime timestamp;

  private String mensagem;

  private String path;

  public ErrorResponse() {
  }

  public ErrorResponse(Integer status, LocalDateTime timestamp, String mensagem, String path) {
    this.status = status;
    this.timestamp = timestamp;
    this.mensagem = mensagem;
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

  public String getMensagem() {
    return mensagem;
  }

  public void setMensagem(String mensagem) {
    this.mensagem = mensagem;
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
    private String mensagem;
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

    public ErrorResponseBuilder mensagem(String mensagem) {
      this.mensagem = mensagem;
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
      errorResponse.setMensagem(mensagem);
      errorResponse.setPath(path);
      return errorResponse;
    }
  }
}
