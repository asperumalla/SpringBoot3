package com.interview.coins.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice(basePackages = "com.interview.coins")
public class ExceptionController {

    @ExceptionHandler(value = ApplicationException.class)
    public ResponseEntity<ApiErrorResponse> handleExceptions(ApplicationException ex){
        System.out.println( ex.getResponseBody() +" <--: -->"+ ex.getMessage() );
            return new ResponseEntity<ApiErrorResponse>(ex.getResponseBody(), ex.getResponseBody().getHttpStatus());
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ApiErrorResponse> handleExceptions(Exception ex){
        ApplicationException exception = new ApplicationException(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<ApiErrorResponse>(exception.getResponseBody(), exception.getResponseBody().getHttpStatus() );
    }
}
