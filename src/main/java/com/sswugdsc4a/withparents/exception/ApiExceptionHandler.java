package com.sswugdsc4a.withparents.exception;

import com.sswugdsc4a.withparents.exception.custion_exceptions.InvalidIdException;
import com.sswugdsc4a.withparents.exception.custion_exceptions.InvalidValueException;
import com.sswugdsc4a.withparents.exception.custion_exceptions.TokenDoesNotExistException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = {InvalidIdException.class})
    public ResponseEntity<ErrorResponse> InvalidIdException(InvalidIdException e){
        return ResponseEntity
                .status(e.getStatus())
                .body(InvalidIdException.toErrorResponse(e));
    }

    @ExceptionHandler(value = {InvalidValueException.class})
    public ResponseEntity<ErrorResponse> InvalidDataException(InvalidValueException e){
        return ResponseEntity
                .status(e.getStatus())
                .body(InvalidValueException.toErrorResponse(e));
    }

    @ExceptionHandler(value = {TokenDoesNotExistException.class})
    public ResponseEntity<ErrorResponse> TokenDoesNotExistException(TokenDoesNotExistException e){
        return ResponseEntity
                .status(e.getStatus())
                .body(TokenDoesNotExistException.toErrorResponse(e));
    }

}
