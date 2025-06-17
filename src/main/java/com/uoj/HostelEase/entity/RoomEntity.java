package com.uoj.HostelEase.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
    private List<UserEntity> users;
}
