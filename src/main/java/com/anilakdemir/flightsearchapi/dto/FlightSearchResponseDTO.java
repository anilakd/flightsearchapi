package com.anilakdemir.flightsearchapi.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FlightSearchResponseDTO {

    List<FlightResponseDTO> outboundFlights;

    List<FlightResponseDTO> inboundFlights;
}
