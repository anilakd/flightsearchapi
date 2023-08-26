package com.anilakdemir.flightsearchapi.controller;

import com.anilakdemir.flightsearchapi.dto.AirportCreateRequestDTO;
import com.anilakdemir.flightsearchapi.dto.AirportResponseDTO;
import com.anilakdemir.flightsearchapi.dto.AirportUpdateRequestDTO;
import com.anilakdemir.flightsearchapi.service.AirportService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/airports")
@RequiredArgsConstructor
@Validated
public class AirportController {

    private final AirportService airportService;

    @GetMapping
    public ResponseEntity<List<AirportResponseDTO>> getAll(
            @RequestParam @Valid @Min(value = 1, message = "Page number must not be less than 1") int pageNumber,
            @RequestParam @Valid @Min(value = 1, message = "Page size must not be less than 1") int pageSize) {

        List<AirportResponseDTO> airportResponseDTOList = airportService.getAll(pageNumber, pageSize);

        return ResponseEntity.ok(airportResponseDTOList);
    }

    @PostMapping
    public ResponseEntity<AirportResponseDTO> create(@RequestBody AirportCreateRequestDTO airportCreateRequestDTO) {

        AirportResponseDTO airportResponseDTO = airportService.create(airportCreateRequestDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(airportResponseDTO);
    }

    @PutMapping
    public ResponseEntity<AirportResponseDTO> update(@RequestBody AirportUpdateRequestDTO airportUpdateRequestDTO) {

        AirportResponseDTO airportResponseDTO = airportService.update(airportUpdateRequestDTO);

        return ResponseEntity.ok(airportResponseDTO);
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@RequestParam(name = "id") Long id) {

        airportService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
