package com.anilakdemir.flightsearchapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FlightUpdateRequestDTO {

    private Long id;

    private Long departureAirportId;

    private Long arrivalAirportId;

    @Schema(type = "string", example = "2023-08-27T00:00")
    private LocalDateTime departureTime;

    @Schema(type = "string", example = "2023-08-27T00:00")
    private LocalDateTime arrivalTime;

    private BigDecimal price;

}
