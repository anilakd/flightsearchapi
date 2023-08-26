package com.anilakdemir.flightsearchapi.mapper;

import com.anilakdemir.flightsearchapi.dto.AirportCreateRequestDTO;
import com.anilakdemir.flightsearchapi.dto.AirportResponseDTO;
import com.anilakdemir.flightsearchapi.dto.AirportUpdateRequestDTO;
import com.anilakdemir.flightsearchapi.entity.Airport;
import com.anilakdemir.flightsearchapi.entity.City;
import com.anilakdemir.flightsearchapi.service.impl.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AirportMapper {

    @Lazy
    private final CityService cityService;

    public Airport mapToAirport(AirportCreateRequestDTO airportCreateRequestDTO) {

        City city = cityService.getByPlateCode(airportCreateRequestDTO.getPlateCode());

        return Airport.builder()
                .name(airportCreateRequestDTO.getName())
                .city(city)
                .build();
    }

    public AirportResponseDTO mapToAirportResponseDTO(Airport airport) {
        return AirportResponseDTO.builder()
                .id(airport.getId())
                .name(airport.getName())
                .plateCode(airport.getCity().getPlateCode())
                .build();
    }

    public Airport mapToAirport(AirportUpdateRequestDTO airportUpdateRequestDTO) {

        City city = cityService.getByPlateCode(airportUpdateRequestDTO.getPlateCode());

        return Airport.builder()
                .id(airportUpdateRequestDTO.getId())
                .name(airportUpdateRequestDTO.getName())
                .city(city)
                .build();
    }
}
