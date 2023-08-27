package com.anilakdemir.flightsearchapi.repository;

import com.anilakdemir.flightsearchapi.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {

    List<Flight> findAllByArrivalAirport_IdAndDepartureAirport_IdAndDepartureTimeBetweenOrderByDepartureTime(Long arrivalAirportId, Long departureAirportId, LocalDateTime startDate, LocalDateTime endDate);
}
