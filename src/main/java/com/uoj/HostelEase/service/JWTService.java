package com.uoj.HostelEase.service;

import java.util.Map;

public interface JWTService {

    public String jwtToken(String subject, Map<String,String> clams);
    public String getRegNo(String token);
}
