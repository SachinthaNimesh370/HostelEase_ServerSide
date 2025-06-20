package com.uoj.HostelEase.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomEntity {
    @Id
    private String roomId;
    private String roomNo;
    private String type;
    private boolean ac;
    private int currentCount;

    @OneToMany(mappedBy = "room")
    private List<StudentEntity> students;

    @ManyToOne
    @JoinColumn(name = "wardenId", referencedColumnName = "wardenID")
    private WardenEntity warden;



}
