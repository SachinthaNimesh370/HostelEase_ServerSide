package com.uoj.HostelEase.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    private String hostel_name;
    private String block;


    @ManyToOne
    @JoinColumn(name = "admin_id")
    @JsonIgnore
    private AdminEntity admin;

    @OneToOne
    @JoinColumn(name = "warden_id", referencedColumnName = "regNo")
    @JsonManagedReference(value = "warden-user")
    private UserEntity user;

    @OneToMany(mappedBy = "warden")
    @JsonIgnore
    private List<StudentEntity> students;

    @OneToMany(mappedBy = "warden")
    @JsonIgnore
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
