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
public class WardenEntity {
    @Id
    private String wardenID;
    @OneToOne
    @JoinColumn(name = "wardenID", referencedColumnName = "regNo")
    private UserEntity user;

    @OneToMany(mappedBy = "warden")
    private List<StudentEntity> students;

    @OneToMany(mappedBy = "warden")
    private List<RoomEntity> rooms;

    @OneToMany(mappedBy = "warden")
    private List<PaymentEntity> payment;

    @OneToMany(mappedBy = "warden")
    private List<ComplainEntity> complain;

    @OneToMany(mappedBy = "warden")
    private List<VisitorEntity> visitor;
}
