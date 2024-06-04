package com.javasproject.eventmanagement.repository;

import com.javasproject.eventmanagement.entity.Event;
import com.javasproject.eventmanagement.entity.Facility;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, String> {
    List<Event> findAllByDeletedFalse();

}
