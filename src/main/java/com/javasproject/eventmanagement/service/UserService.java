package com.javasproject.eventmanagement.service;

import com.javasproject.eventmanagement.dto.request.UserCreationRequest;
import com.javasproject.eventmanagement.dto.response.UserResponse;
import com.javasproject.eventmanagement.entity.User;
import com.javasproject.eventmanagement.exception.AppException;
import com.javasproject.eventmanagement.exception.ErrorCode;
import com.javasproject.eventmanagement.mapper.UserMapper;
import com.javasproject.eventmanagement.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {
    UserRepository userRepository;
    UserMapper userMapper;

    public User createUser(UserCreationRequest request){
        if(userRepository.existsByUserName(request.getUserName())){
            throw new AppException(ErrorCode.USER_EXISTED);
        }
        User user = userMapper.toUser(request);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        return userRepository.save(user);
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
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

    public void deleteUser(String id){
        userRepository.deleteById(id);
    }
}
