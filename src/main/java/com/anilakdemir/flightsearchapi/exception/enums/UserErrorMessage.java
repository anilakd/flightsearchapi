package com.anilakdemir.flightsearchapi.exception.enums;

import com.anilakdemir.flightsearchapi.exception.BaseErrorMessage;

public enum UserErrorMessage implements BaseErrorMessage {

    USER_NOT_FOUND("User could not found"),
    ;


    private String message;

    UserErrorMessage(String message) {
        this.message = message;
    }

    ;

    @Override
    public String getMessage() {
        return this.message;
    }
}
