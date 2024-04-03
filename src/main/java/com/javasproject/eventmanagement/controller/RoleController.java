package com.javasproject.eventmanagement.controller;

import com.javasproject.eventmanagement.dto.request.ApiResponse;
import com.javasproject.eventmanagement.dto.request.RoleCreationRequest;
//import com.javasproject.eventmanagement.entity.Role;
import com.javasproject.eventmanagement.dto.response.RoleResponse;
import com.javasproject.eventmanagement.entity.Role;
import com.javasproject.eventmanagement.service.RoleService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/roles")
public class RoleController {
    RoleService roleService;

    @PostMapping
    public ApiResponse<Role> createRole(@RequestBody @Valid RoleCreationRequest request){
        ApiResponse<Role> response = new ApiResponse<>();
        response.setData(roleService.create(request));
        return response;
    }
    @GetMapping
    public ApiResponse<List<Role>> getAllRoles(){
        return ApiResponse.<List<Role>>builder()
                .data(roleService.findAll())
                .build();
    }
    @DeleteMapping("/{id}")
    public ApiResponse<String> deleteRole(@PathVariable String id){
        roleService.delete(id);
        return ApiResponse.<String>builder()
                .message("Role deleted successfully")
                .build();
    }
}
