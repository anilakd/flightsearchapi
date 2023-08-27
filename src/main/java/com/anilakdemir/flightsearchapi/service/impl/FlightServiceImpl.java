package com.anilakdemir.flightsearchapi.service.impl;

import com.anilakdemir.flightsearchapi.dto.FlightCreateRequestDTO;
import com.anilakdemir.flightsearchapi.dto.FlightResponseDTO;
import com.anilakdemir.flightsearchapi.dto.FlightUpdateRequestDTO;
import com.anilakdemir.flightsearchapi.entity.Flight;
import com.anilakdemir.flightsearchapi.exception.NotFoundException;
import com.anilakdemir.flightsearchapi.exception.enums.FlightErrorMessage;
import com.anilakdemir.flightsearchapi.mapper.FlightMapper;
import com.anilakdemir.flightsearchapi.repository.FlightRepository;
import com.anilakdemir.flightsearchapi.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FlightServiceImpl implements FlightService {

    private final FlightRepository flightRepository;
    private final FlightMapper flightMapper;
    @Override
    public FlightResponseDTO create(FlightCreateRequestDTO flightCreateRequestDTO) {

        Flight flight = flightMapper.mapToFlight(flightCreateRequestDTO);

        flight = flightRepository.save(flight);

        return flightMapper.mapToFlightResponseDTO(flight);
    }

    @Override
    public FlightResponseDTO update(FlightUpdateRequestDTO flightUpdateRequestDTO) {

        Long id = flightUpdateRequestDTO.getId();

        if (!isExistsById(id)) {
            throw new NotFoundException(FlightErrorMessage.FLIGHT_NOT_FOUND);
        }

        Flight flight = flightMapper.mapToFlight(flightUpdateRequestDTO);

        flight = flightRepository.save(flight);

        return flightMapper.mapToFlightResponseDTO(flight);
    }

    @Override
    public void deleteById(Long id) {

        boolean existsById = isExistsById(id);

        if (existsById) {
            throw new NotFoundException(FlightErrorMessage.FLIGHT_NOT_FOUND);
        }

        flightRepository.deleteById(id);
    }

    @Override
    public List<FlightResponseDTO> getAll(int pageNumber, int pageSize) {

        Sort sort = Sort.by("departureTime");

        pageNumber -= 1;

        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sort);

        Page<Flight> pageResponse = flightRepository.findAll(pageRequest);

        return pageResponse.get().map(flightMapper::mapToFlightResponseDTO).collect(Collectors.toList());

    }

    @Override
    public List<FlightResponseDTO> findByDepartureIdAndArrivalIdAndDepartureTime(Long departureAirportId, Long arrivalAirportId, LocalDate departureTime) {

        LocalDateTime startDate = LocalDateTime.of(departureTime, LocalTime.of(0, 0, 0, 0));
        LocalDateTime endDate = LocalDateTime.of(departureTime.plusDays(1), LocalTime.of(0, 0, 0, 0));

        List<Flight> flights = flightRepository.findAllByArrivalAirport_IdAndDepartureAirport_IdAndDepartureTimeBetweenOrderByDepartureTime(arrivalAirportId, departureAirportId, startDate, endDate);

        return flights.stream().map(flightMapper::mapToFlightResponseDTO).toList();
    }

    private boolean isExistsById(Long id) {
        return flightRepository.existsById(id);
    }
}
