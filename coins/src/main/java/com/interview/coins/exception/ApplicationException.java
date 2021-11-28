package com.interview.coins.exception;

import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;

public class ApplicationException extends RuntimeException{

    private final ApiErrorResponse responseBody;

    public ApplicationException(String message, HttpStatus status) {
        super(message);
        responseBody = new ApiErrorResponse(  status ,message, null);
    }

    public ApplicationException(String message,HttpStatus status, String suggestions) {
        super(message);
        responseBody = new ApiErrorResponse(  status,message,suggestions);
    }

    public ApiErrorResponse getResponseBody() {
        return responseBody;
    }
}
