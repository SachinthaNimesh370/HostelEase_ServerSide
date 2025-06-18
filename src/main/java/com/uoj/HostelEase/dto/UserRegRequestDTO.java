package com.uoj.HostelEase.dto;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class UserRegRequestDTO {
    private String regNo;
    private String f_Name;
    private String l_Name;
    private String email;
    private String contactNo;
    private String role;
    private String gender;
    private boolean state;
    private String password;
}
