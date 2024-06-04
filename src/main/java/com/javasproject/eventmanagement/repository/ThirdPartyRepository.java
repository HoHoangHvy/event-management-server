package com.javasproject.eventmanagement.repository;

import com.javasproject.eventmanagement.entity.ThirdParty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ThirdPartyRepository extends JpaRepository<ThirdParty, String> {
    List<ThirdParty> findAllByDeletedFalse();
}