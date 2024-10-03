package com.sky.skystride.utils;

public class AuthenticationResponse {

    private final String jwt;
    private final Integer userId;

    public AuthenticationResponse(String jwt, Integer userId) {
        this.jwt = jwt;
        this.userId = userId;
    }

    public String getJwt() {
        return jwt;
    }

    public Integer getUserId() {
        return userId;
    }
}
