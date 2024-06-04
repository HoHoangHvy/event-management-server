package com.javasproject.eventmanagement.repository;

import com.javasproject.eventmanagement.entity.Contract;
import com.javasproject.eventmanagement.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContractRepository extends JpaRepository<Contract, String> {
    Optional<Contract> findById(String id);
    List<Contract> findAllByDeletedFalse();

}
