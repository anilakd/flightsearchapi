package com.anilakdemir.flightsearchapi.mapper;

import com.anilakdemir.flightsearchapi.dto.FlightCreateRequestDTO;
import com.anilakdemir.flightsearchapi.dto.FlightResponseDTO;
import com.anilakdemir.flightsearchapi.dto.FlightUpdateRequestDTO;
import com.anilakdemir.flightsearchapi.entity.Airport;
import com.anilakdemir.flightsearchapi.entity.Flight;
import com.anilakdemir.flightsearchapi.service.AirportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public final class FlightMapper {

    private final AirportService airportService;

    public Flight mapToFlight(FlightCreateRequestDTO flightCreateRequestDTO) {

        Airport departureAirport = airportService.findById(flightCreateRequestDTO.getDepartureAirportId());
        Airport arrivalAirport = airportService.findById(flightCreateRequestDTO.getArrivalAirportId());

        return Flight.builder()
                .departureAirport(departureAirport)
                .arrivalAirport(arrivalAirport)
                .price(flightCreateRequestDTO.getPrice())
                .departureTime(flightCreateRequestDTO.getDepartureTime())
                .arrivalTime(flightCreateRequestDTO.getArrivalTime())
                .build();
    }

    public FlightResponseDTO mapToFlightResponseDTO(Flight flight) {
        return FlightResponseDTO.builder()
                .id(flight.getId())
                .departureAirportName(flight.getDepartureAirport().getFullName())
                .arrivalAirportName(flight.getArrivalAirport().getFullName())
                .departureTime(flight.getDepartureTime())
                .arrivalTime(flight.getArrivalTime())
                .price(flight.getPrice())
                .build();
    }

    public Flight mapToFlight(FlightUpdateRequestDTO flightUpdateRequestDTO) {

        Airport arrivalAirport = airportService.findById(flightUpdateRequestDTO.getArrivalAirportId());
        Airport departureAirport = airportService.findById(flightUpdateRequestDTO.getDepartureAirportId());

        return Flight.builder()
                .id(flightUpdateRequestDTO.getId())
                .departureAirport(departureAirport)
                .arrivalAirport(arrivalAirport)
                .departureTime(flightUpdateRequestDTO.getDepartureTime())
                .arrivalTime(flightUpdateRequestDTO.getArrivalTime())
                .price(flightUpdateRequestDTO.getPrice())
                .build();
    }
}
