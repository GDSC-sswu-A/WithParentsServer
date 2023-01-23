package com.sswugdsc4a.withparents.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public class CustomException extends RuntimeException{

    private HttpStatus status = HttpStatus.BAD_REQUEST;
    private final String reason;

    public static ErrorResponse toErrorResponse(CustomException e){
        return ErrorResponse.builder()
                .status(e.getStatus())
                .type("custom exception")
                .message(e.getReason())
                .build();
    }

}