package com.anilakdemir.flightsearchapi.repository;

import com.anilakdemir.flightsearchapi.entity.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// @Repository is optional, it works even if we do not use the annotation.
@Repository
public interface AirportRepository extends JpaRepository<Airport, Long> {
    boolean existsByNameAndCity_PlateCode(String name, Integer plateCode);
}
