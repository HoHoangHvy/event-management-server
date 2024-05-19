package com.javasproject.eventmanagement.repository;

import com.javasproject.eventmanagement.entity.Employee;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {

    // Fetch active employees (logical deletion handled via the "deleted" field)
    @Query("SELECT e FROM Employee e WHERE (e.deleted != true OR e.deleted IS NULL) AND e.name <> 'Admin' ORDER BY e.date_entered ASC")
    List<Employee> findAllActive();

    // Soft delete an employee by setting the "deleted" field to 1
    @Modifying
    @Transactional
    @Query("UPDATE Employee e SET e.deleted = true WHERE e.id = :id")
    void softDeleteById(@Param("id") String id);

    Boolean existsByEmail(String email);
    Boolean existsByPhone(String phone);
}
