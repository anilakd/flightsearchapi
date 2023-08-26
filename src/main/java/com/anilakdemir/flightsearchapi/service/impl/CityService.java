package com.anilakdemir.flightsearchapi.service.impl;

import com.anilakdemir.flightsearchapi.entity.City;

public interface CityService {

    City getByPlateCode(Integer plateCode);
}
