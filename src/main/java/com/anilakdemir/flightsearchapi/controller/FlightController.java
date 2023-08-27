package com.anilakdemir.flightsearchapi.controller;

import com.anilakdemir.flightsearchapi.dto.FlightCreateRequestDTO;
import com.anilakdemir.flightsearchapi.dto.FlightResponseDTO;
import com.anilakdemir.flightsearchapi.dto.FlightUpdateRequestDTO;
import com.anilakdemir.flightsearchapi.dto.RestResponse;
import com.anilakdemir.flightsearchapi.service.FlightService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/flights")
@RequiredArgsConstructor
public class FlightController {

    private final FlightService flightService;

    @PostMapping
    public ResponseEntity<RestResponse<FlightResponseDTO>> create(@RequestBody FlightCreateRequestDTO flightCreateRequestDTO) {

        FlightResponseDTO flightResponseDTO = flightService.create(flightCreateRequestDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(RestResponse.of(flightResponseDTO));
    }

    @PutMapping
    public ResponseEntity<RestResponse<FlightResponseDTO>> update(@RequestBody FlightUpdateRequestDTO flightUpdateRequestDTO) {
        FlightResponseDTO flightResponseDTO = flightService.update(flightUpdateRequestDTO);
        return ResponseEntity.ok(RestResponse.of(flightResponseDTO));
    }

    @DeleteMapping
    public ResponseEntity<RestResponse<Void>> deleteById(@RequestParam(name = "id") Long id) {

        flightService.deleteById(id);

        return ResponseEntity.ok(RestResponse.empty());
    }

    @GetMapping
    public ResponseEntity<RestResponse<List<FlightResponseDTO>>> getAll(
            @RequestParam @Valid @Min(value = 1, message = "Page number must not be less than 1") int pageNumber,
            @RequestParam @Valid @Min(value = 1, message = "Page size must not be less than 1") int pageSize) {

        List<FlightResponseDTO> airportResponseDTOList = flightService.getAll(pageNumber, pageSize);

        return ResponseEntity.ok(RestResponse.of(airportResponseDTOList));
    }
}
