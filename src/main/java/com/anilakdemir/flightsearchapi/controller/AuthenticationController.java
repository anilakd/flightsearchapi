package com.anilakdemir.flightsearchapi.controller;

import com.anilakdemir.flightsearchapi.dto.LoginRequest;
import com.anilakdemir.flightsearchapi.dto.RestResponse;
import com.anilakdemir.flightsearchapi.dto.SignupRequest;
import com.anilakdemir.flightsearchapi.security.service.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @Operation(tags = "Authentication Controller", summary = "Login and get token")
    @PostMapping("/login")
    public ResponseEntity<RestResponse<String>> login(@RequestBody LoginRequest loginRequest) {

        String token = authenticationService.login(loginRequest);

        return ResponseEntity.ok(RestResponse.of(token));
    }

    @Operation(tags = "Authentication Controller", summary = "Register")
    @PostMapping("/register")
    public ResponseEntity<RestResponse<Void>> register(@RequestBody SignupRequest signupRequest) {

        authenticationService.register(signupRequest);

        return ResponseEntity.ok(RestResponse.empty());
    }

}
