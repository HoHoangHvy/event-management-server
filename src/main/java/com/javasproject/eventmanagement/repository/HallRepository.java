package com.javasproject.eventmanagement.repository;

import com.javasproject.eventmanagement.entity.Hall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HallRepository extends JpaRepository<Hall, String> {
    List<Hall> findAllByDeletedFalse();
}
