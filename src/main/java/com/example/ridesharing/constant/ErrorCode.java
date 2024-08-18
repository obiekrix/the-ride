package com.example.ridesharing.constant;

import lombok.Getter;

@Getter
public enum ErrorCode {

    USER_NOT_FOUND("ERR_USER_NOT_FOUND", "System is unable to retrieve user details."),
    NO_RIDE_AVAILABLE("ERR_NO_RIDE_AVAILABLE", "There is no ride available at the moment."),
    ALREADY_EXISTS("ERR_ALREADY_EXISTS", "The information you are trying to add already exists."),
    INV_USER("ERR_INV_USER", "Invalid email or password."),
    ;

    private final String errorCode;
    private final String errorDescription;

    ErrorCode(String errorCode, String errorDescription) {
        this.errorCode = errorCode;
        this.errorDescription = errorDescription;
    }
}
