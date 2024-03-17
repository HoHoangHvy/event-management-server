package com.javasproject.eventmanagement.service;

import com.javasproject.eventmanagement.dto.request.UserCreationRequest;
import com.javasproject.eventmanagement.dto.response.UserResponse;
import com.javasproject.eventmanagement.entity.User;
import com.javasproject.eventmanagement.enums.RoleEnum;
import com.javasproject.eventmanagement.exception.AppException;
import com.javasproject.eventmanagement.exception.ErrorCode;
import com.javasproject.eventmanagement.mapper.UserMapper;
import com.javasproject.eventmanagement.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {
    UserRepository userRepository;
    UserMapper userMapper;
    PasswordEncoder passwordEncoder;

    public UserResponse createUser(UserCreationRequest request){
        if(userRepository.existsByUserName(request.getUserName())){
            throw new AppException(ErrorCode.USER_EXISTED);
        }
        User user = userMapper.toUser(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        String role = RoleEnum.USER.name();
        user.setRole(role);

        return userMapper.toUserResponse(userRepository.save(user));
    }

    public List<UserResponse> getAllUsers(){
        return userRepository.findAll().stream().map(userMapper::toUserResponse).collect(Collectors.toList());
    }

    public UserResponse getUserById(String id){
        return userMapper.toUserResponse(userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found")));
    }

    public User updateUser(String id, UserCreationRequest request){
        User user = userRepository.findById(id).orElse(null);
        if(user != null){
            if(userRepository.existsByUserName(request.getUserName())){
                throw new AppException(ErrorCode.USER_EXISTED);
            }
            user = userMapper.toUser(request);
            return userRepository.save(user);
        }
        return null;
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
}
