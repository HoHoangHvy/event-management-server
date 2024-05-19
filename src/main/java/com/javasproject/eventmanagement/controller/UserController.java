package com.javasproject.eventmanagement.controller;

import com.javasproject.eventmanagement.dto.request.ApiResponse;
import com.javasproject.eventmanagement.dto.request.UserCreationRequest;
import com.javasproject.eventmanagement.dto.request.UserUpdateRequest;
import com.javasproject.eventmanagement.dto.response.ListResponse;
import com.javasproject.eventmanagement.dto.response.UserListResponse;
import com.javasproject.eventmanagement.dto.response.UserResponse;
import com.javasproject.eventmanagement.entity.User;
import com.javasproject.eventmanagement.enums.Permission;
import com.javasproject.eventmanagement.service.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
@RequestMapping("api/")
public class UserController {
    UserService userService;
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/users")
    public ApiResponse<UserResponse> createUser(@RequestBody @Valid UserCreationRequest request){
        ApiResponse<UserResponse> response = new ApiResponse<>();
        response.setData(userService.createUser(request));
        return response;
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/users")
    public ApiResponse<ListResponse> getAllUsers(){
        ListResponse listResponse = new ListResponse<UserListResponse>();
        listResponse.setListData(userService.getAllUsers());
        listResponse.setTotalData(userService.countAllUsers());
        return ApiResponse.<ListResponse>builder()
                .data(listResponse)
                .build();
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/users/{id}")
    public ApiResponse<UserListResponse> getUserById(@PathVariable("id") String id){
        return ApiResponse.<UserListResponse>builder()
                .data(userService.getUserById(id))
                .build();
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/users/{id}")
    public ApiResponse<UserResponse> updateUser(@PathVariable("id") String id, @RequestBody UserUpdateRequest request){
        ApiResponse response = new ApiResponse();

        response.setData(userService.updateUser(id, request));
        return response;
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable("id") String id){
        userService.deleteUser(id);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/related/users")
    public ApiResponse<Map<String, Object>> getRelated(){
        ApiResponse<Map<String, Object>> apiResponse = new ApiResponse<>();
        Map<String, Object> listResponse = userService.getRelated();
        apiResponse.setData(listResponse);
        apiResponse.setMessage("Successfully get the employee's list");
        return apiResponse;
    }
}
