package com.interview.coins.exception;

import org.springframework.http.HttpStatus;

public class ApiErrorResponse {
    private final HttpStatus httpStatus;
    private final String message;
    private final String cause;

    static final String NO_SUGGESTIONS = "No Suggestions Available";
    public ApiErrorResponse(HttpStatus httpStatus, String message, String cause) {
        this.httpStatus = httpStatus;
        this.cause= cause == null || cause.isEmpty() ? NO_SUGGESTIONS: cause;
        this.message = message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getMessage() {
        return message;
    }

    public String getCause() {
        return cause;
    }
}
