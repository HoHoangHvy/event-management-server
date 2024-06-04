package com.javasproject.eventmanagement.repository;

import com.javasproject.eventmanagement.entity.Facility;
import com.javasproject.eventmanagement.entity.New;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FacilityRepository extends JpaRepository<Facility, String> {
    List<Facility> findAllByDeletedFalse();

}
