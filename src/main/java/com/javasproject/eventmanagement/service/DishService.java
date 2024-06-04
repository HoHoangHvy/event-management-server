package com.javasproject.eventmanagement.service;

import com.javasproject.eventmanagement.dto.request.DishCreationRequest;
import com.javasproject.eventmanagement.dto.response.DishResponse;
import com.javasproject.eventmanagement.entity.Dish;
import com.javasproject.eventmanagement.mapper.DishMapper;
import com.javasproject.eventmanagement.repository.DishRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Service
public class DishService {
    DishRepository dishRepository;
    DishMapper dishMapper;

    public DishResponse createDish(DishCreationRequest request) {
        Dish dish = dishMapper.toDish(request);
        dish.setDateEntered(LocalDateTime.now());
        dish.setDeleted(false);
        Dish savedDish = dishRepository.save(dish);
        return dishMapper.toDishResponse(savedDish);
    }

    public DishResponse updateDish(String dishId, DishCreationRequest request) {
        Dish dish = dishRepository.findById(dishId).orElseThrow(() -> new RuntimeException("Dish not found"));

        if (request.getName() != null) {
            dish.setName(request.getName());
        }
        if (request.getPrice() > 0) {
            dish.setPrice(request.getPrice());
        }
        if (request.getCost() > 0) {
            dish.setCost(request.getCost());
        }
        if (request.getUnit() != null) {
            dish.setUnit(request.getUnit());
        }

        return dishMapper.toDishResponse(dishRepository.save(dish));
    }

    public void deleteDish(String dishId) {
        Dish dish = dishRepository.findById(dishId).orElseThrow(() -> new RuntimeException("Dish not found"));
        dish.setDeleted(true);
        dishRepository.save(dish);
    }

    public List<DishResponse> getAllDishes() {
        return dishRepository.findAllByDeletedFalse().stream().map(dishMapper::toDishResponse).collect(Collectors.toList());
    }

    public long countAllDishes() {
        return dishRepository.count();
    }

    public DishResponse getDishById(String id) {
        return dishRepository.findById(id).map(dishMapper::toDishResponse).orElseThrow(() -> new RuntimeException("Dish not found"));
    }
}
