package com.javasproject.eventmanagement.repository;

import com.javasproject.eventmanagement.entity.ThirdParty;
import com.javasproject.eventmanagement.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    List<User> findAllByDeletedFalse();
    boolean existsByUserName(String userName);
    Optional<User> findByUserName(String userName);
    Optional<User> findByEmployeeId(String employeeId);
}
