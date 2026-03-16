package com.example.StudyHub.service.user;

import java.util.List;
import com.example.StudyHub.entity.User;
import com.example.StudyHub.model.UserResponse;


public interface UserServiceLayer {

    UserResponse createUser(User user);

    List<UserResponse> getAllUsers();

    UserResponse updateUser(Long id, User user);

    UserResponse deleteUser(Long id);

    
}
