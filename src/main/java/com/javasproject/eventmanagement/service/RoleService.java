package com.javasproject.eventmanagement.service;

import com.javasproject.eventmanagement.dto.request.RoleCreationRequest;
import com.javasproject.eventmanagement.entity.Role;
import com.javasproject.eventmanagement.exception.AppException;
import com.javasproject.eventmanagement.exception.ErrorCode;
import com.javasproject.eventmanagement.mapper.RoleMapper;
import com.javasproject.eventmanagement.repository.RoleRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleService {
    RoleRepository roleRepository;
    RoleMapper roleMapper;

    public Role createRole(RoleCreationRequest roleRequest){
        if(roleRepository.existsByName(roleRequest.getName())){
            throw new AppException(ErrorCode.ROLE_EXISTED);
        }
        Role role = roleMapper.toRole(roleRequest);
        return roleRepository.save(role);
    }
}
