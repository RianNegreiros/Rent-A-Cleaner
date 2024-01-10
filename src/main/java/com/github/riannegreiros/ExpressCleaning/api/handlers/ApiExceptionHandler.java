package com.github.riannegreiros.ExpressCleaning.api.handlers;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.github.riannegreiros.ExpressCleaning.api.dtos.responses.ErrorResponse;
import com.github.riannegreiros.ExpressCleaning.core.exceptions.TokenInTheBlackListException;
import com.github.riannegreiros.ExpressCleaning.core.exceptions.ValidationException;
import com.github.riannegreiros.ExpressCleaning.core.services.checkaddress.exceptions.AddressServiceException;
import com.github.riannegreiros.ExpressCleaning.core.services.token.exceptions.TokenServiceException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
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
            AddressServiceException exception, HttpServletRequest request
    ) {
        return createErrorResponse(HttpStatus.BAD_REQUEST, exception.getLocalizedMessage(), request.getRequestURI());
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<Object> handleValidationException(ValidationException exception) {
        var body = new HashMap<String, List<String>>();

        var fieldError = exception.getFieldError();
        var fieldErrors = new ArrayList<String>();

        fieldErrors.add(fieldError.getDefaultMessage());
        var field = camelCaseToSnakeCase.translate(fieldError.getField());
        body.put(field, fieldErrors);

        return ResponseEntity.badRequest().body(body);
    }

    @ExceptionHandler(TokenServiceException.class)
    public ResponseEntity<Object> handleTokenServiceException(
            TokenServiceException exception, HttpServletRequest request
    ) {
        return createErrorResponse(HttpStatus.UNAUTHORIZED, exception.getLocalizedMessage(), request.getRequestURI());
    }

    @ExceptionHandler(TokenInTheBlackListException.class)
    public ResponseEntity<Object> handleTokenInTheBlackListException(
            TokenInTheBlackListException exception, HttpServletRequest request
    ) {
        return createErrorResponse(HttpStatus.UNAUTHORIZED, exception.getLocalizedMessage(), request.getRequestURI());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handleEntityNotFoundException(
            EntityNotFoundException exception, HttpServletRequest request
    ) {
        return createErrorResponse(HttpStatus.NOT_FOUND, exception.getLocalizedMessage(), request.getRequestURI());
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

    private ResponseEntity<Object> createErrorResponse(HttpStatus status, String message, String path) {
        var errorResponse = ErrorResponse.builder()
                .status(status.value())
                .timestamp(LocalDateTime.now())
                .message(message)
                .path(path)
                .build();

        return new ResponseEntity<>(errorResponse, status);
    }
}
