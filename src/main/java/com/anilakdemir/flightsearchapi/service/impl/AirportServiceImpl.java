package com.anilakdemir.flightsearchapi.service.impl;

import com.anilakdemir.flightsearchapi.dto.AirportCreateRequestDTO;
import com.anilakdemir.flightsearchapi.dto.AirportResponseDTO;
import com.anilakdemir.flightsearchapi.dto.AirportUpdateRequestDTO;
import com.anilakdemir.flightsearchapi.entity.Airport;
import com.anilakdemir.flightsearchapi.exception.AlreadyExistsException;
import com.anilakdemir.flightsearchapi.exception.NotFoundException;
import com.anilakdemir.flightsearchapi.exception.enums.AirportErrorMessage;
import com.anilakdemir.flightsearchapi.mapper.AirportMapper;
import com.anilakdemir.flightsearchapi.repository.AirportRepository;
import com.anilakdemir.flightsearchapi.service.AirportService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AirportServiceImpl implements AirportService {

    private final AirportRepository airportRepository;
    private final AirportMapper airportMapper;


    @Override
    public AirportResponseDTO create(AirportCreateRequestDTO airportCreateRequestDTO) {

        String name = airportCreateRequestDTO.getName();
        Integer plateCode = airportCreateRequestDTO.getPlateCode();

        // checks to have an airport with the same name in a city.
        if (isAirportExistsByNameAndPlateCode(name, plateCode)) {
            throw new AlreadyExistsException(AirportErrorMessage.ALREADY_IN_USE);
        }

        Airport airport = airportMapper.mapToAirport(airportCreateRequestDTO);

        airport = airportRepository.save(airport);

        return airportMapper.mapToAirportResponseDTO(airport);
    }

    @Override
    public AirportResponseDTO update(AirportUpdateRequestDTO airportUpdateRequestDTO) {


        if (!isExistsById(airportUpdateRequestDTO.getId())) {
            throw new NotFoundException(AirportErrorMessage.AIRPORT_NOT_FOUND);
        }

        String name = airportUpdateRequestDTO.getName();
        Integer plateCode = airportUpdateRequestDTO.getPlateCode();

        if (isAirportExistsByNameAndPlateCode(name, plateCode)) {
            throw new AlreadyExistsException(AirportErrorMessage.ALREADY_IN_USE);
        }

        Airport airport = airportMapper.mapToAirport(airportUpdateRequestDTO);

        airport = airportRepository.save(airport);

        return airportMapper.mapToAirportResponseDTO(airport);
    }

    @Override
    public void deleteById(Long id) {

        boolean existsById = airportRepository.existsById(id);

        if (existsById) {
            throw new NotFoundException(AirportErrorMessage.AIRPORT_NOT_FOUND);
        }

        airportRepository.deleteById(id);
    }

    @Override
    public List<AirportResponseDTO> getAll(int pageNumber, int pageSize) {

        Sort sort = Sort.by("city.plateCode");

        pageNumber -= 1;

        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sort);

        Page<Airport> pageResponse = airportRepository.findAll(pageRequest);

        return pageResponse.get().map(airportMapper::mapToAirportResponseDTO).collect(Collectors.toList());
    }

    @Override
    public Airport findById(Long id) {
        return airportRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(AirportErrorMessage.AIRPORT_NOT_FOUND));
    }

    private boolean isAirportExistsByNameAndPlateCode(String name, Integer plateCode) {
        return airportRepository.existsByNameAndCity_PlateCode(name, plateCode);
    }

    private boolean isExistsById(Long id) {
        return airportRepository.existsById(id);
    }

}
