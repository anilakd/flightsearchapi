package com.anilakdemir.flightsearchapi.exception.enums;

import com.anilakdemir.flightsearchapi.exception.BaseErrorMessage;

public enum AirportErrorMessage implements BaseErrorMessage {

    ALREADY_IN_USE("Airport is already in use"),

    AIRPORT_NOT_FOUND("Airport could not found"),
    ;
    private final String message;

    AirportErrorMessage (String message) {
        this.message = message;
    }

    @Override
    public String getMessage () {
        return message;
    }
}
