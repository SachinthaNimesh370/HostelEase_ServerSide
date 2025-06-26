package com.uoj.HostelEase.repo;

import com.uoj.HostelEase.entity.VisitorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VisitorRepository extends JpaRepository<VisitorEntity, Integer> {
    long countByState(String state);
}
