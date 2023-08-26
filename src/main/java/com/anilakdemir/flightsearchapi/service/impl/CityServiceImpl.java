package com.anilakdemir.flightsearchapi.service.impl;

import com.anilakdemir.flightsearchapi.entity.City;
import com.anilakdemir.flightsearchapi.repository.CityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CityServiceImpl implements CityService{

    private final CityRepository cityRepository;

    @Override
    public City getByPlateCode(Integer plateCode) {
        return cityRepository.findById(plateCode)
                .orElseThrow(() -> new RuntimeException("City could not found"));
    }
}
