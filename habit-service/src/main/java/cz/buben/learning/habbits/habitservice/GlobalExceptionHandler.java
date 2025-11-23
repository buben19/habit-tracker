package cz.buben.learning.habbits.habitservice;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

  // Handles @Valid @RequestBody errors
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ValidationErrorResponse> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {

    List<ValidationErrorResponse.FieldError> fieldErrors = ex.getBindingResult()
        .getFieldErrors()
        .stream()
        .map(error -> new ValidationErrorResponse.FieldError(
            error.getField(),
            error.getDefaultMessage()
        ))
        .collect(Collectors.toList());

    ValidationErrorResponse body = new ValidationErrorResponse();
    body.setStatus(HttpStatus.BAD_REQUEST.value());
    body.setError("Validation failed");
    body.setFieldErrors(fieldErrors);

    return ResponseEntity.badRequest().body(body);
  }

  // Handles @Validated on path params, query params, etc.
  @ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity<ValidationErrorResponse> handleConstraintViolation(ConstraintViolationException ex) {

    List<ValidationErrorResponse.FieldError> fieldErrors = ex.getConstraintViolations()
        .stream()
        .map(violation -> new ValidationErrorResponse.FieldError(
            extractPropertyPath(violation),   // see helper below
            violation.getMessage()
        ))
        .collect(Collectors.toList());

    ValidationErrorResponse body = new ValidationErrorResponse();
    body.setStatus(HttpStatus.BAD_REQUEST.value());
    body.setError("Validation failed");
    body.setFieldErrors(fieldErrors);

    return ResponseEntity.badRequest().body(body);
  }

  private String extractPropertyPath(ConstraintViolation<?> violation) {
    // full path can be like "createUser.request.email"
    String path = violation.getPropertyPath().toString();
    int dotIndex = path.lastIndexOf('.');
    return dotIndex != -1 ? path.substring(dotIndex + 1) : path;
  }
}