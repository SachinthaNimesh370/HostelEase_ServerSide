package com.uoj.HostelEase.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {
    @Id
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
