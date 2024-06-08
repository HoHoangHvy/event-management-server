package com.javasproject.eventmanagement.repository;

import com.javasproject.eventmanagement.entity.Event;
import com.javasproject.eventmanagement.entity.Facility;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, String> {
    List<Event> findAllByDeletedFalse();


    @Query("SELECT COUNT(e) FROM Event e WHERE e.approvedBy.id <> '' OR e.approvedBy.id IS NOT NULL AND e.deleted = false")
    long countAllApprovedEvent();


    @Query("SELECT e FROM Event e WHERE e.deleted = false")
    List<Event> findEventSchedule();


    @Query("SELECT e FROM Event e WHERE e.deleted = false AND e.status = 'Draft'")
    List<Event> findAllByDeletedFalseAndStatusDraft();

//    @Query("SELECT e FROM Event e WHERE e.approvedBy.id <> '' OR e.approvedBy.id IS NOT NULL AND e.deleted = false")
//    List<Event> findEventSchedule();

    @Modifying
    @Transactional
    @Query("update Event c set c.status = :status where c.id = :id")
    int updateEventStatus(@Param("id") String id, @Param("status") String status);
}
