package com.javasproject.eventmanagement.repository;

import com.javasproject.eventmanagement.entity.Dish;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DishRepository extends JpaRepository<Dish, String> {
    Optional<Dish> findById(String id);

    List<Dish> findAllByDeletedFalse();
}
