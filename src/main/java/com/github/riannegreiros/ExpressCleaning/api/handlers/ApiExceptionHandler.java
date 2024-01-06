package com.github.riannegreiros.ExpressCleaning.api.handlers;

import java.time.LocalDateTime;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.github.riannegreiros.ExpressCleaning.api.dtos.responses.ErrorResponse;
import com.github.riannegreiros.ExpressCleaning.core.services.checkaddress.exceptions.AddressServiceException;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice(annotations = RestController.class)
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(AddressServiceException.class)
  public ResponseEntity<Object> handleEnderecoServiceException(
      AddressServiceException exception, HttpServletRequest request) {
    var errorReponse = ErrorResponse.builder()
        .status(400)
        .timestamp(LocalDateTime.now())
        .mensagem(exception.getLocalizedMessage())
        .path(request.getRequestURI())
        .build();

    return ResponseEntity.badRequest().body(errorReponse);
  }
}