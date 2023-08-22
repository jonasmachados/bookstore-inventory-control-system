package com.jonas.backend.resource.exceptions;

import com.jonas.backend.services.exceptions.DatabaseException;
import com.jonas.backend.services.exceptions.ResourceNotFoundException;
import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetails> resourceNotFound(ResourceNotFoundException e,
            HttpServletRequest request) {
        String error = "Objeto não encontrado";
        HttpStatus status = HttpStatus.NOT_FOUND;
        ErrorDetails err = new ErrorDetails(Instant.now(),
                status.value(),
                error,
                e.getMessage(),
                request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<ErrorDetails> database(DatabaseException e, HttpServletRequest request) {
        String error = "Database error";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErrorDetails err = new ErrorDetails(Instant.now(),
                status.value(),
                error,
                e.getMessage(),
                request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }
    
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorDetails> illegalArgument(IllegalArgumentException e, HttpServletRequest request) {
        String error = "Illegal Argument Exception";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErrorDetails err = new ErrorDetails(Instant.now(),
                status.value(),
                error,
                e.getMessage(),
                request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

}
