package com.anilakdemir.flightsearchapi.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@AllArgsConstructor
@NoArgsConstructor
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
@Getter
@Setter
public abstract class BusinessException extends RuntimeException {

    private BaseErrorMessage baseErrorMessage;
}
