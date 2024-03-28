package com.javasproject.eventmanagement.service;

import com.javasproject.eventmanagement.entity.Dish;
import com.javasproject.eventmanagement.repository.DishRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DishService {
    @Autowired
    private DishRepository dishRepository;
    public Dish upsert(Dish dish){
        return dishRepository.save(dish);
    }

    public Dish getById(String id){
        Optional<Dish> findById = dishRepository.findById(id);
        return findById.orElse(null);
    }

    public List<Dish> getDishList() {
        return dishRepository.findAll();
    }

    public Boolean deleteById(String id){
        if(dishRepository.existsById(id)){
            dishRepository.deleteById(id);
            return true;
        }
        else {
            return false;
        }
    }

}
