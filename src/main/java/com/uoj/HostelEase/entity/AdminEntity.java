package com.uoj.HostelEase.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AdminEntity {
    @Id
    private String admin_id;
    @OneToOne
    @JoinColumn(name = "admin_id", referencedColumnName = "regNo")
    private UserEntity user;

    @OneToMany(mappedBy = "admin")
    private List<StudentEntity> students;

}
