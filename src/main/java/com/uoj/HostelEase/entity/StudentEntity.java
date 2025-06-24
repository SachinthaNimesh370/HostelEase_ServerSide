package com.uoj.HostelEase.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    private String student_id;

    @OneToOne
    @JoinColumn(name = "student_id", referencedColumnName = "regNo")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "admin_id")
    @JsonIgnore
    private AdminEntity admin;

    @ManyToOne
    @JoinColumn(name = "warden_id")
    @JsonIgnore
    private WardenEntity warden;

    private String duration;
    private String acadYear;

    @ManyToOne
    @JoinColumn(name = "room_id")
    @JsonBackReference
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
