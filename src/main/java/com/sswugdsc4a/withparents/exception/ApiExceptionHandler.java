package com.sswugdsc4a.withparents.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = {CustomException.class})
    public ResponseEntity<ErrorResponse> CustomException(CustomException e){
        return ResponseEntity
                .status(e.getStatus())
                .body(CustomException.toErrorResponse(e));
    }

}
