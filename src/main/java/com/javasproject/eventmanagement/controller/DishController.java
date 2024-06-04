package com.javasproject.eventmanagement.controller;

import com.javasproject.eventmanagement.dto.request.ApiResponse;
import com.javasproject.eventmanagement.dto.request.DishCreationRequest;
import com.javasproject.eventmanagement.dto.response.DishResponse;
import com.javasproject.eventmanagement.dto.response.ListResponse;
import com.javasproject.eventmanagement.service.DishService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/")
public class DishController {

    @Autowired
    private DishService dishService;

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/dishes")
    public ApiResponse<DishResponse> createDish(@RequestBody DishCreationRequest request) {
        ApiResponse<DishResponse> apiResponse = new ApiResponse<>();
        apiResponse.setData(dishService.createDish(request));
        apiResponse.setMessage("Successfully created the dish.");
        return apiResponse;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/dishes")
    public ApiResponse<ListResponse> getAllDishes() {
        ApiResponse<ListResponse> apiResponse = new ApiResponse<>();
        var listResponse = new ListResponse<DishResponse>();
        List<DishResponse> dishes = dishService.getAllDishes();
        long totalData = dishService.countAllDishes();
        listResponse.setListData(dishes);
        listResponse.setTotalData(totalData);

        apiResponse.setData(listResponse);
        apiResponse.setMessage("Successfully retrieved the dish list.");
        return apiResponse;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/dishes/{dishId}")
    public ResponseEntity<ApiResponse<DishResponse>> getDishById(@PathVariable("dishId") String dishId) {
        ApiResponse<DishResponse> apiResponse = new ApiResponse<>();
        apiResponse.setData(dishService.getDishById(dishId));
        apiResponse.setMessage("Successfully retrieved the dish.");
        return ResponseEntity.ok(apiResponse);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/dishes/{id}")
    public ApiResponse<DishResponse> updateDish(@PathVariable String id, @RequestBody DishCreationRequest request) {
        ApiResponse<DishResponse> apiResponse = new ApiResponse<>();
        apiResponse.setData(dishService.updateDish(id, request));
        apiResponse.setMessage("Successfully updated the dish.");
        return apiResponse;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/dishes/{dishId}")
    public ApiResponse<String> deleteDish(@PathVariable String dishId) {
        ApiResponse<String> apiResponse = new ApiResponse<>();
        apiResponse.setData("Dish has been deleted");
        dishService.deleteDish(dishId);
        return apiResponse;
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/related/dishes")
    public ApiResponse<Map<String, Object>> getRelated(){
        ApiResponse<Map<String, Object>> apiResponse = new ApiResponse<>();
        Map<String, Object> listResponse = new HashMap<>();
        apiResponse.setData(listResponse);
        apiResponse.setMessage("Successfully get the employee's list");
        return apiResponse;
    }

}
