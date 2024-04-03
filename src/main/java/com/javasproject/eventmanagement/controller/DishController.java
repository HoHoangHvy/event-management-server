package com.javasproject.eventmanagement.controller;

import com.javasproject.eventmanagement.dto.request.ApiResponse;
import com.javasproject.eventmanagement.entity.Dish;
import com.javasproject.eventmanagement.service.DishService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/dishes")
public class DishController {
    @Autowired
    DishService dishService;
    @PostMapping
    public ApiResponse<Dish> createDish(@RequestBody Dish dish){
        return ApiResponse.<Dish>builder()
                .data(dishService.upsert(dish))
                .build();
    }
    @GetMapping("/{id}")
    public ApiResponse<Dish> getDishById(@PathVariable String id){
        String message = "Successfully get the dish";
        Dish dish = dishService.getById(id);
        if (dish == null){
            message = "Not found this dish";
        }
        return ApiResponse.<Dish>builder()
                .data(dish)
                .message(message)
                .build();
    }
    @GetMapping
    public ApiResponse<List<Dish>> getAllDishes(){
        String message = "Successfully get the dish list";
        List<Dish> dishList = dishService.getDishList();
        if(dishList.isEmpty()){
            message = "Dish list is empty";

        }
        return ApiResponse.<List<Dish>>builder()
                .data(dishList)
                .message(message)
                .build();
    }
    @PutMapping
    public ApiResponse<Dish> updateDish(@RequestBody  Dish dish){
        return ApiResponse.<Dish>builder()
                .data(dishService.upsert(dish))
                .build();
    }
    @DeleteMapping("/{id}")
    public ApiResponse<String> deleteDish(@PathVariable String id){
        String message = "Delete successfully";
        Boolean status = dishService.deleteById(id);
        if (!status){
            message = "Delete fail! Not found this record";
        }
        return ApiResponse.<String>builder()
                .message(message)
                .build();
    }
}
