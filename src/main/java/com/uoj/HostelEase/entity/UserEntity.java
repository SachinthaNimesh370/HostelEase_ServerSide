package com.uoj.HostelEase.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {
    @Id
    private int id;
    private String regNo;
    private String f_Name;
    private String l_Name;
    private String email;
    private String contactNo;
    private String role;
    private String gender;
    private String password;
}
