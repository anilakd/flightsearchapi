package com.anilakdemir.flightsearchapi.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FlightResponseDTO {

    private Long id;

    private String departureAirportName;

    private String arrivalAirportName;

    private LocalDateTime departureTime;

    private LocalDateTime arrivalTime;

    private BigDecimal price;
}
