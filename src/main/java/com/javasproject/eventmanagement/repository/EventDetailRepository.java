package com.javasproject.eventmanagement.repository;


import com.javasproject.eventmanagement.entity.Event;
import com.javasproject.eventmanagement.entity.EventDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EventDetailRepository extends JpaRepository<EventDetails, String> {
    Optional<EventDetails> findById(String id);


    @Query("SELECT SUM(e.price) FROM EventDetails e WHERE e.deleted = false AND e.events.id = :id")
    Double calculateTotalAmount(@Param("id") String id);
}
