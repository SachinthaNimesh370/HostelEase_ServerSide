package com.uoj.HostelEase.repo;

import com.uoj.HostelEase.entity.ComplainEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComplainRepository extends JpaRepository<ComplainEntity,Integer> {
    long countByStatus(String status);
}
