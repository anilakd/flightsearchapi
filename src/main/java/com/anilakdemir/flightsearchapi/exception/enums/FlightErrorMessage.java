package com.anilakdemir.flightsearchapi.exception.enums;

import com.anilakdemir.flightsearchapi.exception.BaseErrorMessage;

public enum FlightErrorMessage implements BaseErrorMessage {

    FLIGHT_NOT_FOUND("Airport could not found"),
    ;
    private final String message;

    FlightErrorMessage (String message) {
        this.message = message;
    }

    @Override
    public String getMessage () {
        return message;
    }
}
