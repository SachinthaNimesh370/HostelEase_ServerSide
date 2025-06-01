package com.uoj.HostelEase.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class UserEntity {
    @Id
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
