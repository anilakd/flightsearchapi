package com.anilakdemir.flightsearchapi.service;

import com.anilakdemir.flightsearchapi.entity.City;

public interface CityService {

    City getByPlateCode(Integer plateCode);
}
