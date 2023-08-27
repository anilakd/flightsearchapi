package com.anilakdemir.flightsearchapi.controller;

import com.anilakdemir.flightsearchapi.dto.FlightSearchResponseDTO;
import com.anilakdemir.flightsearchapi.dto.RestResponse;
import com.anilakdemir.flightsearchapi.service.FlightSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Optional;

@RestController
@RequestMapping("v1/search")
@RequiredArgsConstructor
public class FlightSearchController {

    private final FlightSearchService flightSearchService;

    @GetMapping
    public ResponseEntity<RestResponse<FlightSearchResponseDTO>> search(
            @RequestParam("departureAirportId") Long departureAirportId,
            @RequestParam("arrivalAirportId") Long arrivalAirportId,
            @RequestParam("departureTime") LocalDate departureTime,
            @RequestParam(value = "returnTime", required = false) Optional<LocalDate> returnTime) {

        FlightSearchResponseDTO result = flightSearchService.search(departureAirportId, arrivalAirportId, departureTime, returnTime);

        return ResponseEntity.ok(RestResponse.of(result));
    }
}
