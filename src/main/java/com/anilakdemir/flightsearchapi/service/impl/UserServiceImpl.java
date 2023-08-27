package com.anilakdemir.flightsearchapi.service.impl;

import com.anilakdemir.flightsearchapi.dto.SignupRequest;
import com.anilakdemir.flightsearchapi.entity.User;
import com.anilakdemir.flightsearchapi.exception.NotFoundException;
import com.anilakdemir.flightsearchapi.exception.enums.UserErrorMessage;
import com.anilakdemir.flightsearchapi.repository.UserRepository;
import com.anilakdemir.flightsearchapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new NotFoundException(UserErrorMessage.USER_NOT_FOUND));
    }

    @Override
    public void save(SignupRequest signupRequest) {
        User user = new User();
        user.setUsername(signupRequest.getUsername());
        user.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
        userRepository.save(user);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException(UserErrorMessage.USER_NOT_FOUND));
    }


    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
}
