package com.sswugdsc4a.withparents.exception.custion_exceptions;

import com.sswugdsc4a.withparents.exception.ErrorResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public class TokenDoesNotExistException extends RuntimeException{

    private HttpStatus status = HttpStatus.BAD_REQUEST;

    public String getMessage(){
        return "token이 존재하지 않습니다";
    }

    public static ErrorResponse toErrorResponse(TokenDoesNotExistException e){
        return ErrorResponse.builder()
                .status(e.getStatus())
                .type("TokenDoesNotExistException")
                .message(e.getMessage())
                .build();
    }

}
