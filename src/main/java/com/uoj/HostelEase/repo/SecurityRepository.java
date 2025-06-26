package com.uoj.HostelEase.repo;

import com.uoj.HostelEase.entity.SecurityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SecurityRepository extends JpaRepository<SecurityEntity, String> {
}
