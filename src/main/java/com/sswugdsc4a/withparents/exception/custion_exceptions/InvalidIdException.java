package com.sswugdsc4a.withparents.exception.custion_exceptions;

import com.sswugdsc4a.withparents.exception.ErrorResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public class InvalidIdException extends RuntimeException {

    private HttpStatus status = HttpStatus.BAD_REQUEST;
    private final Long id;
    private final String item;

    public String getMessage(){
        return item+"_id가 유효하지 않습니다. id="+id.toString();
    }

    public static ErrorResponse toErrorResponse(InvalidIdException e){
        return ErrorResponse.builder()
                .status(e.getStatus())
                .type("InvalidUserIdException")
                .message(e.getMessage())
                .build();
    }

}
