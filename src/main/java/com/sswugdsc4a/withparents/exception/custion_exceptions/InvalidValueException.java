package com.sswugdsc4a.withparents.exception.custion_exceptions;

import com.sswugdsc4a.withparents.exception.ErrorResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public class InvalidValueException extends RuntimeException{

    private HttpStatus status = HttpStatus.BAD_REQUEST;
    private final String value;
    private final String description;

    public String getMessage(){
        return "형식에 맞지 않는 값입니다. value=" +value+"\n" + description;
    }

    public static ErrorResponse toErrorResponse(InvalidValueException e){
        return ErrorResponse.builder()
                .status(e.getStatus())
                .type("InvalidValueException")
                .message(e.getMessage())
                .build();
    }

}
