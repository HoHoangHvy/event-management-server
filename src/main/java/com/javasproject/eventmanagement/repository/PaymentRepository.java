package com.javasproject.eventmanagement.repository;

import com.javasproject.eventmanagement.entity.Payment;
import com.javasproject.eventmanagement.entity.ThirdParty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment,String> {
    List<Payment> findAllByDeletedFalse();
}
