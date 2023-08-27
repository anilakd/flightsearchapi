package com.anilakdemir.flightsearchapi.mapper;

import com.anilakdemir.flightsearchapi.dto.FlightResponseDTO;
import com.anilakdemir.flightsearchapi.dto.FlightSearchResponseDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public final class FlightSearchMapper {
    public FlightSearchResponseDTO mapToFlightSearchResponseDTO(List<FlightResponseDTO> outboundFlights, List<FlightResponseDTO> inboundFlights) {
        return FlightSearchResponseDTO.builder()
                .outboundFlights(outboundFlights)
                .inboundFlights(inboundFlights)
                .build();
    }

    public FlightSearchResponseDTO mapToFlightSearchResponseDTO(List<FlightResponseDTO> outboundFlights) {
        return FlightSearchResponseDTO.builder()
                .outboundFlights(outboundFlights)
                .inboundFlights(List.of())
                .build();
    }
}
