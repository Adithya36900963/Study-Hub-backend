package com.example.StudyHub.service.user;

import java.util.List;
import com.example.StudyHub.entity.User;

public interface UserServiceLayer {

    User createUser(User user);

    List<User> getAllUsers();

    User updateUser(Long id, User user);

    User deleteUser(Long id);

   
    
}
