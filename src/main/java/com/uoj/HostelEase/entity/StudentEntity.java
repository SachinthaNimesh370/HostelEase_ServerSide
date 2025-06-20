package com.uoj.HostelEase.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

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
    @JoinColumn(name = "admin_id")
    @JsonIgnore
    private AdminEntity admin;

    @ManyToOne
    @JoinColumn(name = "warden_id")
    @JsonIgnore
    private WardenEntity warden;

    private String faculty;
    private String duration;
    private String acadYear;

    @ManyToOne
    @JoinColumn(name = "room_id")
    @JsonIgnore
    private RoomEntity room;

    @OneToMany(mappedBy = "student")
    @JsonIgnore
    private List<PaymentEntity> payment;

    @OneToMany(mappedBy = "student")
    @JsonIgnore
    private List<ComplainEntity> complain;

    @OneToMany(mappedBy = "student")
    @JsonIgnore
    private List<VisitorEntity> visitor;
}
