package com.spond.club.exception;

import com.spond.club.api.model.ApiError;
import com.spond.club.api.model.FieldError;
import com.spond.club.api.model.ValidationError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.MethodArgumentNotValidException;


// centralized class to handle different type of exceptions
@RestControllerAdvice
public class ApiExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(ApiExceptionHandler.class);

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiError> handleNotFound(ResourceNotFoundException exception) {
        log.debug("Resource not found: {}", exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError(exception.getMessage()));
    }

    @ExceptionHandler(ValidationFailureException.class)
    public ResponseEntity<ValidationError> handleValidationFailure(ValidationFailureException exception) {
        log.debug("Domain validation failed: {}", exception.getFieldErrors());
        ValidationError response = validationError();
        exception.getFieldErrors().forEach((field, message) ->
                response.addFieldErrorsItem(new FieldError().field(field).message(message))
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationError> handleMethodArgumentNotValid(MethodArgumentNotValidException exception) {
        log.debug("Request body validation failed", exception);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validationError(exception.getBindingResult()));
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<ValidationError> handleBindException(BindException exception) {
        log.debug("Request binding failed", exception);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validationError(exception.getBindingResult()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleInternalServerError(Exception exception) {
        log.error("Unhandled server error", exception);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(apiError("Internal server error"));
    }

    private ApiError apiError(String message) {
        return new ApiError().message(message);
    }

    private ValidationError validationError() {
        return new ValidationError().message("Validation failed");
    }

    private ValidationError validationError(BindingResult bindingResult) {
        ValidationError response = validationError();
        bindingResult.getFieldErrors().forEach(fieldError -> {
                    response.addFieldErrorsItem(
                            new FieldError()
                                    .field(fieldError.getField())
                                    .message(fieldError.getDefaultMessage())
                    );
                }
        );
        return response;
    }
}
