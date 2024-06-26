package com.javasproject.eventmanagement.repository;

import com.javasproject.eventmanagement.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, String> {
    Optional<Payment> findById(String id);
    List<Payment> findAllByDeletedFalse();
}
