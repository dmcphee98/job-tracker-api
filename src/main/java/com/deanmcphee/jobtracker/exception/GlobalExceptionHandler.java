package com.deanmcphee.jobtracker.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDateTime;

/**
 * Global exception handler for REST controllers.
 * Converts exceptions into meaningful HTTP responses with appropriate status codes and messages.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Represents a structured API error for consistent responses.
     */
    public record ApiError(HttpStatus status, String message, LocalDateTime timestamp) { }

    /**
     * Handles type mismatch errors for path variables or request parameters.
     * @param ex the thrown {@link MethodArgumentTypeMismatchException}
     * @return a {@link ResponseEntity} with HTTP status 400 (Bad Request)
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ApiError> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ApiError(HttpStatus.BAD_REQUEST, ex.getMessage(), LocalDateTime.now()));
    }

    /**
     * Handles database constraint violations, such as UNIQUE constraints.
     * @param ex the {@link DataIntegrityViolationException} thrown by the repository or JPA layer
     * @return a {@link ResponseEntity} with HTTP status 409 (Conflict)
     */
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiError> handleConflict(DataIntegrityViolationException ex) {
        String message = "Data integrity violation: " + ex.getMessage();
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ApiError(HttpStatus.CONFLICT, message, LocalDateTime.now()));
    }

    /**
     * Handles cases where a requested entity is not found in the database.
     * @param ex the {@link EntityNotFoundException} thrown by the repository or JPA layer
     * @return a {@link ResponseEntity} with HTTP status 404 (Not Found)
     */
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ApiError> handleNotFound(EntityNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiError(HttpStatus.NOT_FOUND, ex.getMessage(), LocalDateTime.now()));
    }

    /**
     * Handles any other uncaught exceptions that occur during request processing.
     * Acts as a catch-all to ensure that unexpected errors return a 500 Internal Server Error
     * instead of exposing internal stack traces to the client.
     * @param ex the uncaught {@link Exception} thrown by the repository or JPA layer
     * @return a {@link ResponseEntity} with 500 (Internal Server Error)
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleServerError(Exception ex) {
        String message = "Unexpected error: " + ex.getMessage();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, message, LocalDateTime.now()));
    }
}