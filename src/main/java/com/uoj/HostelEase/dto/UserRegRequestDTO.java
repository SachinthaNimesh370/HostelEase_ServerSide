package com.uoj.HostelEase.dto;

import lombok.Data;

@Data
public class UserRegRequestDTO {
    int id;
    String name;
    String email;
    String role;
    String password;
}
