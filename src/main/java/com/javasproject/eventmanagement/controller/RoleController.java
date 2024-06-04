package com.javasproject.eventmanagement.controller;

import com.javasproject.eventmanagement.dto.request.ApiResponse;
import com.javasproject.eventmanagement.dto.request.RoleCreationRequest;
//import com.javasproject.eventmanagement.entity.Role;
import com.javasproject.eventmanagement.dto.response.ListResponse;
import com.javasproject.eventmanagement.dto.response.RoleListResponse;
import com.javasproject.eventmanagement.dto.response.RoleResponse;
import com.javasproject.eventmanagement.entity.Role;
import com.javasproject.eventmanagement.service.RoleService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("api/")
public class RoleController {
    RoleService roleService;

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/roles")
    public ApiResponse<Role> createRole(@RequestBody @Valid RoleCreationRequest request){
        ApiResponse<Role> response = new ApiResponse<>();
        response.setData(roleService.create(request));
        return response;
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/roles/{id}")
    public ApiResponse<RoleResponse> getRoleById(@PathVariable String id){
        ApiResponse<RoleResponse> response = new ApiResponse<>();
        response.setData(roleService.findById(id));
        return response;
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/roles/{id}")
    public ApiResponse<RoleResponse> updateRole(@PathVariable String id, @RequestBody @Valid RoleCreationRequest request){
        ApiResponse<RoleResponse> response = new ApiResponse<>();
        response.setData(roleService.update(id, request));
        return response;
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/roles")
    public ApiResponse<ListResponse> getAllRoles(){
        ListResponse listResponse = new ListResponse<RoleResponse>();
        listResponse.setListData(roleService.findAllByDeletedFalse());
        listResponse.setTotalData(roleService.countAll());
        return ApiResponse.<ListResponse>builder()
                .data(listResponse)
                .build();
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/roles/{id}")
    public ApiResponse<String> deleteRole(@PathVariable String id){
        roleService.delete(id);
        return ApiResponse.<String>builder()
                .message("Role deleted successfully")
                .build();
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/related/roles")
    public ApiResponse<Map<String, Object>> getRelated(){
        ApiResponse<Map<String, Object>> apiResponse = new ApiResponse<>();
        Map<String, Object> listResponse = roleService.getRelated();
        apiResponse.setData(listResponse);
        apiResponse.setMessage("Successfully get the employee's list");
        return apiResponse;
    }

}
