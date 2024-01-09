package com.github.riannegreiros.ExpressCleaning.api.handlers;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.github.riannegreiros.ExpressCleaning.api.dtos.responses.ErrorResponse;
import com.github.riannegreiros.ExpressCleaning.core.services.checkaddress.exceptions.AddressServiceException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@RestControllerAdvice(annotations = RestController.class)
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

  private final SnakeCaseStrategy camelCaseToSnakeCase = new SnakeCaseStrategy();

  @ExceptionHandler(AddressServiceException.class)
  public ResponseEntity<Object> handleAddressServiceException(
      AddressServiceException exception, HttpServletRequest request) {
    var errorResponse = ErrorResponse.builder()
        .status(400)
        .timestamp(LocalDateTime.now())
        .message(exception.getLocalizedMessage())
        .path(request.getRequestURI())
        .build();

    return ResponseEntity.badRequest().body(errorResponse);
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
          MethodArgumentNotValidException exception,
          HttpHeaders headers,
          HttpStatusCode status,
          WebRequest request
  ) {
    var body = new HashMap<String, List<String>>();

    exception.getBindingResult().getFieldErrors()
            .forEach(fieldError -> {
              var field = camelCaseToSnakeCase.translate(fieldError.getField());

              if (!body.containsKey(field)) {
                var fieldErrors = new ArrayList<String>();
                fieldErrors.add(fieldError.getDefaultMessage());

                body.put(field, fieldErrors);
              } else {
                body.get(field).add(fieldError.getDefaultMessage());
              }
            });

    return ResponseEntity.badRequest().body(body);
  }
}