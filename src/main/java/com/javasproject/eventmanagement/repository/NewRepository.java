package com.javasproject.eventmanagement.repository;

import com.javasproject.eventmanagement.entity.Employee;
import com.javasproject.eventmanagement.entity.New;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewRepository extends JpaRepository<New, String> {

    // Fetch active employees (logical deletion handled via the "deleted" field)
    @Query("SELECT e FROM New e WHERE e.deleted != true ORDER BY e.date_entered DESC")
    List<New> findAllActive();

}
