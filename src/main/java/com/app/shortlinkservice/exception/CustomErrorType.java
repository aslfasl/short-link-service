package com.app.shortlinkservice.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum CustomErrorType {

    NOT_FOUND(HttpStatus.BAD_REQUEST, -111);

    private final HttpStatus status;
    private final int code;

    CustomErrorType(HttpStatus status, int code) {
        this.status = status;
        this.code = code;
    }
}
