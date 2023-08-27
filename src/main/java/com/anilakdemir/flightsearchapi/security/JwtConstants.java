package com.anilakdemir.flightsearchapi.security;

import lombok.Getter;

@Getter
public enum JwtConstants {

    BEARER("Bearer ");

    private String constant;

    JwtConstants(String constant) {
        this.constant = constant;
    }

    @Override
    public String toString() {
        return constant;
    }
}
