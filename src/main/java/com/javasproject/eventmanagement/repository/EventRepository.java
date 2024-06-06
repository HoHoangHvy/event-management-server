package com.javasproject.eventmanagement.repository;

import com.javasproject.eventmanagement.entity.Event;
import com.javasproject.eventmanagement.entity.Facility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, String> {
    List<Event> findAllByDeletedFalse();


    @Query("SELECT COUNT(e) FROM Event e WHERE e.approvedBy.id <> '' OR e.approvedBy.id IS NOT NULL AND e.deleted = false")
    long countAllApprovedEvent();


    @Query("SELECT e FROM Event e WHERE e.deleted = false")
    List<Event> findEventSchedule();

//    @Query("SELECT e FROM Event e WHERE e.approvedBy.id <> '' OR e.approvedBy.id IS NOT NULL AND e.deleted = false")
//    List<Event> findEventSchedule();


}
