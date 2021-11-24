package org.patsimas.optical_lens_store.controllers;

import org.patsimas.optical_lens_store.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class ErrorController {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> resourceNotFoundExceptionHandler(Exception ex) {

        ErrorResponse errorResponse = ErrorResponse.builder()
                .errorCode(HttpStatus.NOT_FOUND.value())
                .status(HttpStatus.NOT_FOUND)
                .message(ex.getMessage())
                .build();

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> resourceAlreadyExistsExceptionHandler(Exception ex) {

        ErrorResponse errorResponse = ErrorResponse.builder()
                .errorCode(HttpStatus.CONFLICT.value())
                .status(HttpStatus.CONFLICT)
                .message(ex.getMessage())
                .build();

        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UnacceptableActionException.class)
    public ResponseEntity<ErrorResponse> unacceptableActionExceptionHandler(Exception ex) {

        ErrorResponse errorResponse = ErrorResponse.builder()
                .errorCode(HttpStatus.NOT_ACCEPTABLE.value())
                .status(HttpStatus.NOT_ACCEPTABLE)
                .message(ex.getMessage())
                .build();

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_ACCEPTABLE);
    }
}
