package com.anilakdemir.flightsearchapi.exception;

import com.anilakdemir.flightsearchapi.dto.RestResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@RestControllerAdvice
public final class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<RestResponse<GeneralException>> handleAlreadyExistsException (AlreadyExistsException exception, WebRequest webRequest) {

        RestResponse<GeneralException> restResponse = getGeneralExceptionRestResponse(exception.getBaseErrorMessage(), webRequest);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(restResponse);
    }

    @ExceptionHandler
    public ResponseEntity<RestResponse<GeneralException>> handleEntityNotFoundException (NotFoundException exception, WebRequest webRequest) {

        RestResponse<GeneralException> restResponse = getGeneralExceptionRestResponse(exception.getBaseErrorMessage(), webRequest);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(restResponse);
    }



    private static RestResponse<GeneralException> getGeneralExceptionRestResponse(BaseErrorMessage exception, WebRequest webRequest) {

        Date errorDate = new Date();
        String message = exception.getMessage();
        String description = webRequest.getDescription(false);

        GeneralException generalException = new GeneralException(errorDate, description);

        RestResponse<GeneralException> restResponse = RestResponse.error(generalException);
        restResponse.setMessage(message);
        return restResponse;
    }
}
