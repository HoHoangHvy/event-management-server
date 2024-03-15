package com.javasproject.eventmanagement.service;

import com.javasproject.eventmanagement.dto.request.UserCreationRequest;
import com.javasproject.eventmanagement.entity.User;
import com.javasproject.eventmanagement.exception.AppException;
import com.javasproject.eventmanagement.exception.ErrorCode;
import com.javasproject.eventmanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User createUser(UserCreationRequest request){
        User user = new User();

        if(userRepository.existsByUserName(request.getUserName())){
            throw new AppException(ErrorCode.USER_EXISTED);
        }

        user.setUserName(request.getUserName());
        user.setPassword(request.getPassword());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setDateOfBirth(request.getDateOfBirth());
        user.setStatus(request.getStatus());

        return userRepository.save(user);
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User getUserById(String id){
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public User updateUser(String id, UserCreationRequest request){
        User user = userRepository.findById(id).orElse(null);
        if(user != null){

            if(userRepository.existsByUserName(request.getUserName())){
                throw new AppException(ErrorCode.USER_EXISTED);
            }

            user.setUserName(request.getUserName());
            user.setPassword(request.getPassword());
            user.setFirstName(request.getFirstName());
            user.setLastName(request.getLastName());
            user.setDateOfBirth(request.getDateOfBirth());
            user.setStatus(request.getStatus());
            return userRepository.save(user);
        }
        return null;
    }

    public void deleteUser(String id){
        userRepository.deleteById(id);
    }
}
