package com.javasproject.eventmanagement.controller;

import com.javasproject.eventmanagement.dto.request.ApiRespone;
import com.javasproject.eventmanagement.dto.request.UserCreationRequest;
import com.javasproject.eventmanagement.entity.User;
import com.javasproject.eventmanagement.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ApiRespone<User> createUser(@RequestBody @Valid UserCreationRequest request){
        ApiRespone<User> respone = new ApiRespone<>();
        respone.setData(userService.createUser(request));
        return respone;
    }

    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable("id") String id){
        return userService.getUserById(id);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable("id") String id, @RequestBody UserCreationRequest request){
        return userService.updateUser(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") String id){
        userService.deleteUser(id);
    }
}
