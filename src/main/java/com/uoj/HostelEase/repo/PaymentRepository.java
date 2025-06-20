package com.uoj.HostelEase.repo;

import com.uoj.HostelEase.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<PaymentEntity,Integer> {

}
