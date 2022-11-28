package com.app.shortlinkservice.exception;

import lombok.Data;

@Data
public class ErrorMessage {

    private final int code;
    private final String name;
    private final String message;
}
