package com.anilakdemir.flightsearchapi.service;

import com.anilakdemir.flightsearchapi.dto.SignupRequest;
import com.anilakdemir.flightsearchapi.entity.User;

public interface UserService {

    User findByUsername(String username);

    void save(SignupRequest usrUserSaveRequestDto);

    User findById(Long id);
}
