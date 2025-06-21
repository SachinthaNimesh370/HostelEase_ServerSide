package com.uoj.HostelEase.repo;

import com.uoj.HostelEase.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<StudentEntity,String> {
}
