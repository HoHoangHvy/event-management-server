package com.javasproject.eventmanagement.repository;

import com.javasproject.eventmanagement.entity.Facility;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FacilityRepository extends JpaRepository<Facility, String> {
    Optional<Facility> findById(String id);
}
