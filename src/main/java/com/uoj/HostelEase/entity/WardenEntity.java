package com.uoj.HostelEase.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private String warden_id;

    @OneToOne
    @JoinColumn(name = "warden_id", referencedColumnName = "regNo")
    private UserEntity user;

    @OneToMany(mappedBy = "warden")
    @JsonIgnore
    private List<StudentEntity> students;

    @OneToMany(mappedBy = "warden")
    @JsonIgnore // 🔴 This is the key to stopping the recursive loop
    private List<RoomEntity> rooms;

    @OneToMany(mappedBy = "warden")
    @JsonIgnore
    private List<PaymentEntity> payment;

    @OneToMany(mappedBy = "warden")
    @JsonIgnore
    private List<ComplainEntity> complain;

    @OneToMany(mappedBy = "warden")
    @JsonIgnore
    private List<VisitorEntity> visitor;
}
