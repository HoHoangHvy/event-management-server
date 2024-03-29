//package com.javasproject.eventmanagement.controller;
//
//import com.javasproject.eventmanagement.dto.request.ApiResponse;
//import com.javasproject.eventmanagement.dto.request.RoleCreationRequest;
////import com.javasproject.eventmanagement.entity.Role;
//import com.javasproject.eventmanagement.service.RoleService;
//import jakarta.validation.Valid;
//import lombok.AccessLevel;
//import lombok.RequiredArgsConstructor;
//import lombok.experimental.FieldDefaults;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequiredArgsConstructor
//@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
//@RequestMapping("/roles")
//public class RoleController {
//    RoleService roleService;
//
//    @PostMapping
//    public ApiResponse<Role> createRole(@RequestBody @Valid RoleCreationRequest request){
//        ApiResponse<Role> response = new ApiResponse<>();
//        response.setData(roleService.createRole(request));
//        return response;
//    }
//}
