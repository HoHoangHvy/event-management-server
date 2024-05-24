package com.javasproject.eventmanagement.repository;

import com.javasproject.eventmanagement.entity.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResourceRepository extends JpaRepository<Resource, String> {

    List<Resource> findAllByDeletedFalse();
}
