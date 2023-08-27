package com.anilakdemir.flightsearchapi.service;

import com.anilakdemir.flightsearchapi.dto.AirportCreateRequestDTO;
import com.anilakdemir.flightsearchapi.dto.AirportResponseDTO;
import com.anilakdemir.flightsearchapi.dto.AirportUpdateRequestDTO;
import com.anilakdemir.flightsearchapi.entity.Airport;

import java.util.List;

public interface AirportService {

    AirportResponseDTO create(AirportCreateRequestDTO airportCreateRequestDTO);

    AirportResponseDTO update(AirportUpdateRequestDTO airportUpdateRequestDTO);

    void deleteById(Long id);

    List<AirportResponseDTO> getAll(int pageNumber, int pageSize);

    Airport findById(Long id);
}
