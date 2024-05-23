package com.javasproject.eventmanagement.repository;

import com.javasproject.eventmanagement.entity.ResourceBookingDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface ResourceBookingRepository extends JpaRepository<ResourceBookingDetail, String> {
}
