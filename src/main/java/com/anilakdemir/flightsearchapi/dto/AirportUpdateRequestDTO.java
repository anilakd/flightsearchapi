package com.anilakdemir.flightsearchapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AirportUpdateRequestDTO {

    private Long id;

    private String name;

    private Integer plateCode;
}
