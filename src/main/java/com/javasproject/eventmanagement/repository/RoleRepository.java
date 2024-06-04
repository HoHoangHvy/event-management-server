package com.javasproject.eventmanagement.repository;

import com.javasproject.eventmanagement.entity.Role;
import com.javasproject.eventmanagement.entity.ThirdParty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, String>{
    List<Role> findAllByDeletedFalse();
    boolean existsByName(String name);
    Optional<Role> findByName(String name);
}
