package com.anilakdemir.flightsearchapi.service.impl;

import com.anilakdemir.flightsearchapi.dto.FlightResponseDTO;
import com.anilakdemir.flightsearchapi.dto.FlightSearchResponseDTO;
import com.anilakdemir.flightsearchapi.mapper.FlightSearchMapper;
import com.anilakdemir.flightsearchapi.service.FlightSearchService;
import com.anilakdemir.flightsearchapi.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FlightSearchServiceImpl implements FlightSearchService {

    private final FlightService flightService;
    private final FlightSearchMapper flightSearchMapper;


    @Override
    public FlightSearchResponseDTO search(Long departureAirportId, Long arrivalAirportId, LocalDate departureTime, Optional<LocalDate> returnTime) {

        if (returnTime.isEmpty()) {

            return searchOneWayFlight(departureAirportId, arrivalAirportId, departureTime);

        } else {

            return searchRoundTripFlights(departureAirportId, arrivalAirportId, departureTime, returnTime.get());
        }

    }

    private FlightSearchResponseDTO searchOneWayFlight(Long departureAirportId, Long arrivalAirportId, LocalDate departureTime) {

        List<FlightResponseDTO> outboundFlights = flightService.findByDepartureIdAndArrivalIdAndDepartureTime(departureAirportId, arrivalAirportId, departureTime);

        return flightSearchMapper.mapToFlightSearchResponseDTO(outboundFlights);
    }

    private FlightSearchResponseDTO searchRoundTripFlights(Long departureAirportId, Long arrivalAirportId, LocalDate departureTime, LocalDate returnTime) {

        List<FlightResponseDTO> outboundFlights = flightService.findByDepartureIdAndArrivalIdAndDepartureTime(departureAirportId, arrivalAirportId, departureTime);

        List<FlightResponseDTO> inboundFlights = flightService.findByDepartureIdAndArrivalIdAndDepartureTime(arrivalAirportId, departureAirportId, returnTime);

        return flightSearchMapper.mapToFlightSearchResponseDTO(outboundFlights, inboundFlights);
    }
}
