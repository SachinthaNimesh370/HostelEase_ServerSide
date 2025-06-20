package com.uoj.HostelEase.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ComplainEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int complainId;
    private String catagory;
    private String content;
    private String date;
    private String time;
    private String status;

    @ManyToOne
    @JoinColumn(name = "studentId", referencedColumnName = "studentId")
    private StudentEntity student;

    @ManyToOne
    @JoinColumn(name = "wardenId", referencedColumnName = "wardenID")
    private WardenEntity warden;
}
