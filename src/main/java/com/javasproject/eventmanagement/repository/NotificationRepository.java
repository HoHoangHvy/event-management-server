package com.javasproject.eventmanagement.repository;

import com.javasproject.eventmanagement.entity.Employee;
import com.javasproject.eventmanagement.entity.New;
import com.javasproject.eventmanagement.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, String> {

    // Fetch active employees (logical deletion handled via the "deleted" field)
    @Query("SELECT e FROM Notification e WHERE e.deleted != true AND e.employee = :employee  ORDER BY e.date_entered DESC")
    List<Notification> findAllActiveByUserId(@Param("employee") Employee employee);

    @Query("SELECT e FROM Notification e WHERE e.deleted != true AND e.isRead = false AND e.employee = :employee  ORDER BY e.date_entered DESC")
    List<Notification> findAllActiveUnreadByUserId(@Param("employee") Employee employee);
}
