package com.javasproject.eventmanagement.service;

import com.javasproject.eventmanagement.dto.request.RoleCreationRequest;
import com.javasproject.eventmanagement.dto.response.OptionResponse;
import com.javasproject.eventmanagement.dto.response.RoleListResponse;
import com.javasproject.eventmanagement.dto.response.RoleResponse;
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
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public RoleResponse findById(String id) {
        return roleMapper.toRoleResponse(roleRepository.findById(id).orElse(null));
    }
    public Role findObjectById(String id) {
        return roleRepository.findById(id).orElse(null);
    }

    public RoleResponse update(String id, RoleCreationRequest entity) {
        Role role = roleRepository.findById(id).orElse(null);
        if(role != null){
            if(roleRepository.existsByName(entity.getName()) && roleRepository.findByName(entity.getName()).get().getId() != role.getId()){
                throw new AppException(ErrorCode.ROLE_EXISTED);
            }
            role.setName(entity.getName());
            role.setPermission(entity.getPermission());
            return roleMapper.toRoleResponse(roleRepository.save(role));
        }
        return null;
    }

    public void delete(String id) {
        if(roleRepository.existsById(id)){
            roleRepository.deleteById(id);
        }
    }
    public List<RoleListResponse> findAll() {
        return roleRepository.findAll().stream().map(roleMapper::toRoleListResponse).collect(Collectors.toList());
    }

    public List<OptionResponse> getAllOption() {
        return roleRepository.findAll()
                .stream()
                .filter(role -> !role.getName().equals("ADMIN")) // Filter out roles with name "ADMIN"
                .map(roleMapper::toOptionResponse)
                .collect(Collectors.toList());
    }
    public Optional<Role> findByName(String name){
        return roleRepository.findByName(name);
    }

    public Map<String, Object> getRelated() {
        List<OptionResponse> roleList = this.getAllOption();
        return Map.of("roleId", roleList);
    }

    public long countAll() {
        return roleRepository.count();
    }
}
