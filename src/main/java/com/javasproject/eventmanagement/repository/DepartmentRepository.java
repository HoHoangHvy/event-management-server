package com.javasproject.eventmanagement.repository;

import com.javasproject.eventmanagement.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, String> {
}
