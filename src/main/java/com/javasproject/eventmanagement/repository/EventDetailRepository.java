package com.javasproject.eventmanagement.repository;


import com.javasproject.eventmanagement.entity.EventDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EventDetailRepository extends JpaRepository<EventDetail, String> {
    Optional<EventDetail> findById(String id);
}
