package com.uoj.HostelEase.repo;

import com.uoj.HostelEase.entity.AdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<AdminEntity, String> {
}
