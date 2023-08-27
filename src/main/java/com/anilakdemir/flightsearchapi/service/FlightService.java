package com.anilakdemir.flightsearchapi.service;

import com.anilakdemir.flightsearchapi.dto.FlightCreateRequestDTO;
import com.anilakdemir.flightsearchapi.dto.FlightResponseDTO;
import com.anilakdemir.flightsearchapi.dto.FlightUpdateRequestDTO;

import java.time.LocalDate;
import java.util.List;

public interface FlightService {

    FlightResponseDTO create(FlightCreateRequestDTO flightCreateRequestDTO);

    FlightResponseDTO update(FlightUpdateRequestDTO flightUpdateRequestDTO);

    void deleteById(Long id);

    List<FlightResponseDTO> getAll(int pageNumber, int pageSize);

    List<FlightResponseDTO> findByDepartureIdAndArrivalIdAndDepartureTime(Long departureAirportId, Long arrivalAirportId, LocalDate departureTime);
}
