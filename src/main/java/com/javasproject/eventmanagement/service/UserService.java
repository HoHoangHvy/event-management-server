package com.javasproject.eventmanagement.service;

import com.javasproject.eventmanagement.dto.request.ChangePasswordRequest;
import com.javasproject.eventmanagement.dto.request.RoleCreationRequest;
import com.javasproject.eventmanagement.dto.request.UserCreationRequest;
import com.javasproject.eventmanagement.dto.request.UserUpdateRequest;
import com.javasproject.eventmanagement.dto.response.OptionResponse;
import com.javasproject.eventmanagement.dto.response.UserListResponse;
import com.javasproject.eventmanagement.dto.response.UserResponse;
import com.javasproject.eventmanagement.entity.Employee;
import com.javasproject.eventmanagement.entity.Role;
import com.javasproject.eventmanagement.entity.User;
import com.javasproject.eventmanagement.enums.Permission;
import com.javasproject.eventmanagement.enums.RoleEnum;
import com.javasproject.eventmanagement.exception.AppException;
import com.javasproject.eventmanagement.exception.ErrorCode;
import com.javasproject.eventmanagement.mapper.RoleMapper;
import com.javasproject.eventmanagement.mapper.UserMapper;
import com.javasproject.eventmanagement.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {
    UserRepository userRepository;
    UserMapper userMapper;
    PasswordEncoder passwordEncoder;
    RoleService roleService;
    RoleMapper roleMapper;
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    public UserResponse createUser(UserCreationRequest request){
        if(userRepository.existsByUserName(request.getUserName())){
            throw new AppException(ErrorCode.USER_EXISTED);
        }
        User user = userMapper.toUser(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        if(!request.getRoleId().isEmpty()){
            Role role = roleService.findObjectById(request.getRoleId());
            user.setRole(Optional.of(role));
        }
        return userMapper.toUserResponse(userRepository.save(user));
    }

    @PreAuthorize("hasRole('ADMIN')")
    public List<UserListResponse> getAllUsers(){
        return userRepository.findAllByDeletedFalse().stream().map(userMapper::toUserListResponse).collect(Collectors.toList());
    }
    @PreAuthorize("hasRole('ADMIN')")
    public UserListResponse getUserById(String id){
        return userMapper.toUserListResponse(userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found")));
    }
    @PreAuthorize("hasRole('ADMIN')")
    public UserResponse updateUser(String id, UserUpdateRequest request){
        User user = userRepository.findById(id).orElse(null);
        if(user != null){
            Role role = roleService.findObjectById(request.getRoleId());
            if(request.getUserName() != null) user.setUserName(request.getUserName());
            if(request.getStatus() != null) user.setStatus(request.getStatus());
            if(role != null) user.setRole(Optional.ofNullable(role));

            return userMapper.toUserResponse(userRepository.save(user));
        }
        return null;
    }

    @PreAuthorize("hasRole('ADMIN')")
    public boolean updateUserRole(String empId, String roleId){
        User user = userRepository.findByEmployeeId(empId).orElse(null);
        if(user != null){
            Role role = roleService.findObjectById(roleId);
            user.setRole(Optional.ofNullable(role));
            userRepository.save(user);
            return true;
        }
        return false;
    }


//    public boolean assignRoleToUser(AssignRoleRequest request){
//        User user = userRepository.findById(request.getUserId()).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXIST));
//        if(user != null){
//            Role role = roleRepository.findById(request.getRoleId()).orElseThrow(() -> new AppException(ErrorCode.ROLE_NOT_EXIST));
//            if(role != null){
//                user.getRole().add(role);
//                userRepository.save(user);
//                return true;
//            }
//        }
//        return false;
//    }

    public void deleteUser(String id){
        userRepository.deleteById(id);
    }
    public UserResponse getUserByUserName(String userName){
        return userMapper.toUserResponse(userRepository.findByUserName(userName).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXIST)));
    }
    public Set<Permission> getUserPermission(String id){
        User user = userRepository.findById(id).orElse(null);
//        if(user != null){
//            return user.getRole().getPermissions();
//        }
        return null;
    }

    public boolean changePassword(ChangePasswordRequest request){
        User user = userRepository.findById(request.getId()).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXIST));
        if(user != null){
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
            boolean authenticated = passwordEncoder.matches(request.getOldPassword(), user.getPassword());
            if(!authenticated){
                throw new AppException(ErrorCode.INVALID_CREDENTIALS);
            }
            user.setPassword(passwordEncoder.encode(request.getNewPassword()));
            userRepository.save(user);
            return true;
        }
        return false;
    }

    public User getCurrentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findByUserName(authentication.getName()).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXIST));
    }

    public long countAllUsers(){
        return userRepository.count();
    }

    public Map<String, Object> getRelated() {
        List<OptionResponse> roleList = roleService.getAllOption();
        return Map.of("roleId", roleList);
    }
}
