package com.uoj.HostelEase.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    private String room_id;

    private String roomNo;
    private String type;
    private boolean ac;
    private int currentCount;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    @JsonManagedReference // Forward part of the reference
    private List<StudentEntity> students;

    @ManyToOne
    @JoinColumn(name = "warden_id", referencedColumnName = "warden_id")
    private WardenEntity warden;
}
