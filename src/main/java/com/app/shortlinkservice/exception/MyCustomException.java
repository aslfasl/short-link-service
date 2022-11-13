package com.app.shortlinkservice.exception;

import lombok.Getter;


@Getter
public class MyCustomException extends RuntimeException {

    private final CustomErrorType errorType;

    public MyCustomException(String message, CustomErrorType errorType) {
        super(message);
        this.errorType = errorType;
    }
}
