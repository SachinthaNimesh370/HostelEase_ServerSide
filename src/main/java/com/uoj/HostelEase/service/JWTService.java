package com.uoj.HostelEase.service;

public interface JWTService {

    public String jwtToken();
    public String getUserName(String token);
}
