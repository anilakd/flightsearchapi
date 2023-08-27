package com.anilakdemir.flightsearchapi.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@Getter
@Setter
public class GeneralException {

    private Date errorDate;
    private String detail;
}
