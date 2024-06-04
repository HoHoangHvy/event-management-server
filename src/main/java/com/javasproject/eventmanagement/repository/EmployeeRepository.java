package com.javasproject.eventmanagement.repository;

import com.javasproject.eventmanagement.entity.Employee;
import com.javasproject.eventmanagement.entity.Event;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
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

    @Query("SELECT e FROM Employee e WHERE e.deleted != true AND e.empLevel = 'Manager' AND e.department.id = :departmentId")
    List<Employee> findManagerByDepartmentId(@Param("departmentId") String departmentId);


    @Query("SELECT e FROM Employee e WHERE MONTH(e.dob) = :month AND DAY(e.dob) = :day")
    List<Employee> findAllByDobMonthAndDay(@Param("month") int month, @Param("day") int day);

    default List<Employee> findAllWithDobToday() {
        LocalDate today = LocalDate.now();
        int month = today.getMonthValue();
        int day = today.getDayOfMonth();
        return findAllByDobMonthAndDay(month, day);
    }}
