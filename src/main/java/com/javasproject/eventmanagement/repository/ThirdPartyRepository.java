package com.javasproject.eventmanagement.repository;

import com.javasproject.eventmanagement.entity.Dish;
import com.javasproject.eventmanagement.entity.ThirdParty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ThirdPartyRepository extends JpaRepository<ThirdParty, String> {
    Optional<ThirdParty> findById(String id);
}