package com.uoj.HostelEase.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class VisitorEntity {
    @Id
    private int id;
    private String nic;
    private String name;
    private String date;
    private String entryTime;
    private String exitTime;

    @ManyToOne
    @JoinColumn(name = "studentId", referencedColumnName = "studentId")
    private StudentEntity student;

    @ManyToOne
    @JoinColumn(name = "wardenId", referencedColumnName = "wardenID")
    private WardenEntity warden;
}
