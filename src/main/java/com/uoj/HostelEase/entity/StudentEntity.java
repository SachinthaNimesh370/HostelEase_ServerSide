package com.uoj.HostelEase.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class StudentEntity {
    @Id
    private String studentId;
    @OneToOne
    @JoinColumn(name = "studentId", referencedColumnName = "regNo")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "admin")
    private AdminEntity admin;

    @ManyToOne
    @JoinColumn(name = "warden")
    private WardenEntity warden;

    private String faculty;
    private String duration;
    private String acadYear;

    @ManyToOne
    @JoinColumn(name = "roomid")
    private RoomEntity room;
}
