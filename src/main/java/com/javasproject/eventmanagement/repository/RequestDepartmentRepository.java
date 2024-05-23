package com.javasproject.eventmanagement.repository;

import com.javasproject.eventmanagement.entity.Request;
import com.javasproject.eventmanagement.entity.RequestDepartment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RequestDepartmentRepository extends JpaRepository<RequestDepartment,String> {


    List<RequestDepartment> findRequestDepartmentsByRequest(Request request);
}
