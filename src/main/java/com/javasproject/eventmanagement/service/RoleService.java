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

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleService {
    RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    public Role create(RoleCreationRequest roleRequest){
        if(roleRepository.existsByName(roleRequest.getName())){
            throw new AppException(ErrorCode.ROLE_EXISTED);
        }
        Role role = new Role();
        role.setName(roleRequest.getName());
        role.setPermission(roleRequest.getPermission());
        return roleRepository.save(role);
    }

    public Role findById(String id) {
        return roleRepository.findById(id).orElse(null);
    }

    public Role update(String id, RoleCreationRequest entity) {
        Role role = roleRepository.findById(id).orElse(null);
        if(role != null){
            if(roleRepository.existsByName(entity.getName())){
                throw new AppException(ErrorCode.ROLE_EXISTED);
            }
            role.setName(entity.getName());
            role.setPermission(entity.getPermission());
            return roleRepository.save(role);
        }
        return null;
    }

    public void delete(String id) {
        if(roleRepository.existsById(id)){
            roleRepository.deleteById(id);
        }
    }
    public List<Role> findAll() {
        return  roleRepository.findAll();
    }
    public Optional<Role> findByName(String name){
        return roleRepository.findByName(name);
    }
}
