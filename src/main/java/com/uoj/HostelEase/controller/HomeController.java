package com.uoj.HostelEase.controller;

import com.uoj.HostelEase.service.JWTService;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/home")
public class HomeController {
    private final JWTService jwtService;

    public HomeController(JWTService jwtService) {
        this.jwtService = jwtService;
    }

    @GetMapping("/get")
    public String method(){
        return jwtService.jwtToken();
    }

    @GetMapping("/username")
    public String getUserName(@RequestParam String token){
        return jwtService.getUserName(token);
    }
}

