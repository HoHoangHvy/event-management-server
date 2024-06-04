package com.javasproject.eventmanagement.repository;

import com.javasproject.eventmanagement.entity.Employee;
import com.javasproject.eventmanagement.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface EventBookingRepository  extends JpaRepository<Event, String>  {


    @Query("SELECT COALESCE(SUM(edf.quantity), 0) " +
            "FROM EventDetailFacility edf " +
            "INNER JOIN EventDetails ed ON ed.id = edf.eventDetails.id " +
            "INNER JOIN Event e ON e.id = ed.events.id " +
            "WHERE edf.facility.id = :facilityId " +
            "AND e.deleted = false " +
            "AND ((:startDate BETWEEN e.startDate AND e.endDate) OR (:endDate BETWEEN e.startDate AND e.endDate))")
    long countBookedFacilityById(@Param("facilityId") String resourceId, @Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

}
