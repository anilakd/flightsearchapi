package com.anilakdemir.flightsearchapi.service;

import com.anilakdemir.flightsearchapi.dto.FlightSearchResponseDTO;

import java.time.LocalDate;
import java.util.Optional;

public interface FlightSearchService {

    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    FlightSearchResponseDTO search(Long departureAirportId, Long arrivalAirportId, LocalDate departureTime, Optional<LocalDate> returnTime);
}
