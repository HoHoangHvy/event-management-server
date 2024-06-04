package com.javasproject.eventmanagement.repository;

import com.javasproject.eventmanagement.entity.Department;
import com.javasproject.eventmanagement.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department, String> {
    List<Department> findAllByDeletedFalse();
}
