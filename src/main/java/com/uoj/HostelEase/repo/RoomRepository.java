package com.uoj.HostelEase.repo;

import com.uoj.HostelEase.entity.RoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<RoomEntity, String> {
    long countByCurrentCount(int i);
}
