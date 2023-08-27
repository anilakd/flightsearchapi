package com.anilakdemir.flightsearchapi.dto;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RestResponse<T> implements Serializable {

    private T data;
    private Date responseDate;
    private boolean isSuccess;
    private String message;

    public static <T> RestResponse<T> of (T t) {
        return RestResponse.<T>builder()
                .responseDate(new Date())
                .data(t)
                .isSuccess(true)
                .build();
    }

    public static <T> RestResponse<T> error (T t) {
        return RestResponse.<T>builder()
                .responseDate(new Date())
                .data(t)
                .isSuccess(false)
                .build();
    }

    public static <T> RestResponse<T> empty () {
        return RestResponse.<T>builder()
                .responseDate(new Date())
                .data(null)
                .isSuccess(true)
                .build();
    }

    public void setMessage (String message) {
        this.message = message;
    }
}
