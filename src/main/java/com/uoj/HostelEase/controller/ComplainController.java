package com.uoj.HostelEase.controller;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/complain")
public class ComplainController {

    @PostMapping("/newcomplain")
    public String newComplain() {
        return null;
    }
}
