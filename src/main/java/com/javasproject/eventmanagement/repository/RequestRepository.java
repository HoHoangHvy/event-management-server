package com.javasproject.eventmanagement.repository;

import com.javasproject.eventmanagement.entity.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestRepository extends JpaRepository<Request, String> {
}