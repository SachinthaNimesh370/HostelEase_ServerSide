package com.uoj.HostelEase.repo;

import com.uoj.HostelEase.entity.RoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RoomRepository extends JpaRepository<RoomEntity, String> {
    long countByCurrentCount(int i);

    @Query("SELECT SUM(r.currentCount) FROM RoomEntity r")
    Integer getTotalCurrentStudents();

    @Query("SELECT SUM(r.type) FROM RoomEntity r")
    Integer getTotalCapacity();
}
