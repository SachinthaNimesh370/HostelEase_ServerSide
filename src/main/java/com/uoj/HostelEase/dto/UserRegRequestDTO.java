package com.uoj.HostelEase.dto;

import lombok.Data;

@Data
public class UserRegRequestDTO {
    int id;
    String reg_no;
    String f_name;
    String l_name;
    String email;
    String contact_no;
    String role;
    String gender;
    String password;
}
