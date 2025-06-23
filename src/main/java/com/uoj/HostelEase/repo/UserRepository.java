package com.uoj.HostelEase.repo;

import com.uoj.HostelEase.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity,String> {

    Optional<UserEntity> findByRegNo(String regNo);
    boolean existsByRegNo(String regNo);


    // Custom query method to find state by regNo
    @Query("SELECT u.state FROM UserEntity u WHERE u.regNo = :regNo")
    Boolean findStateByRegNo(String regNo);

    @Query("SELECT u.role FROM UserEntity u WHERE u.regNo = :regNo")
    String findRoleByRegNo(String regNo);

    List<UserEntity> findByRoleLike(String role);



}
