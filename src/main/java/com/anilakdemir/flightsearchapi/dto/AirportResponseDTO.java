package com.anilakdemir.flightsearchapi.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AirportResponseDTO {

    private Long id;

    private String name;

    private Integer plateCode;

}
