package com.javasproject.eventmanagement.repository;

import com.javasproject.eventmanagement.entity.ResourceBookingDetail;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ResourceBookingRepository extends JpaRepository<ResourceBookingDetail, String> {

    List<ResourceBookingDetail> findByResourceIdAndDeletedFalse(String resourceId);

    @Query("SELECT rb FROM ResourceBookingDetail rb WHERE rb.resource.id = :resourceId AND rb.deleted = false AND ((:startDate BETWEEN rb.startDate AND rb.endDate) OR (:endDate BETWEEN rb.startDate AND rb.endDate))")
    List<ResourceBookingDetail> findByResourceIdAndDeletedFalseAndStartDateBetweenAndEndDateBetween(@Param("resourceId") String resourceId, @Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);
    @Modifying
    @Transactional
    @Query("update ResourceBookingDetail rb set rb.status = :status where rb.id = :id")
    int updateStatusById(@Param("id") String id, @Param("status") String status);
}
