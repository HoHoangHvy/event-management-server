package com.javasproject.eventmanagement.repository;


import com.javasproject.eventmanagement.entity.EventDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EventDetailRepository extends JpaRepository<EventDetails, String> {
    Optional<EventDetails> findById(String id);
}
