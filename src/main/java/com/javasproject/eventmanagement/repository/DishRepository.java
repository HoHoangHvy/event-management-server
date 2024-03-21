package com.javasproject.eventmanagement.repository;

import com.javasproject.eventmanagement.entity.Dish;
import com.javasproject.eventmanagement.entity.EventDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DishRepository extends JpaRepository<Dish, String> {
    Optional<Dish> findById(String id);
}
