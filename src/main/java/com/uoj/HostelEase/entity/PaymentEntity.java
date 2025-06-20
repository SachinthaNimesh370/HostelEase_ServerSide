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
public class PaymentEntity {
    @Id
    private int PaymentId;
    private double amount;
    private String description;
    private String date;
    private String status;

    @ManyToOne
    @JoinColumn(name = "studentId", referencedColumnName = "studentId")
    private StudentEntity student;

    @ManyToOne
    @JoinColumn(name = "wardenId", referencedColumnName = "wardenID")
    private WardenEntity warden;


}
