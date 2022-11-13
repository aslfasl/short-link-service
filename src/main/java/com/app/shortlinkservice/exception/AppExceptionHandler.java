package com.app.shortlinkservice.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler(value = {MyCustomException.class})
    public ResponseEntity<ErrorMessage> handleRuntimeException(MyCustomException e) {
        CustomErrorType errorType = e.getErrorType();
        return new ResponseEntity<>(
                new ErrorMessage(errorType.getCode(), errorType.name(), e.getMessage()), errorType.getStatus());
    }

}
