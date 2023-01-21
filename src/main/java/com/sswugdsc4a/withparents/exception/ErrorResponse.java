package com.sswugdsc4a.withparents.exception;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
public class ErrorResponse {

    private HttpStatus status;
    private String type;
    private String message;

}
