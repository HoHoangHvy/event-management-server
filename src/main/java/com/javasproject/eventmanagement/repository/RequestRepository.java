package com.javasproject.eventmanagement.repository;

import com.javasproject.eventmanagement.entity.Department;
import com.javasproject.eventmanagement.entity.Employee;
import com.javasproject.eventmanagement.entity.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestRepository extends JpaRepository<Request, String> {

    @Query("SELECT r FROM Request r WHERE r.deleted != true AND r.createdBy = :employee ORDER BY r.date_entered DESC")
    List<Request> findAllActiveByUserId(@Param("employee") Employee employee);

    @Query("SELECT r FROM Request r INNER JOIN Employee e ON e.id = r.createdBy.id AND e.deleted != true " +
            "WHERE r.deleted != true AND e.department = :department ORDER BY r.date_entered DESC")
    List<Request> findAllActiveByDepartmentId(@Param("department") Department department);

    List<Request> findAllByDeletedFalse();

    @Query("SELECT r FROM Request r INNER JOIN RequestDepartment ed ON ed.request.id = r.id AND ed.deleted != true " +
            "WHERE r.deleted != true AND ed.department = :department ORDER BY r.date_entered DESC")
    List<Request> findAllActiveByManager(@Param("department") Department department);
}
