package com.example.StudyHub.service.user;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.StudyHub.entity.User;
import com.example.StudyHub.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserServiceImpl implements UserServiceLayer {

    private UserRepository ur;
    private PasswordEncoder pe;
    public UserServiceImpl(UserRepository ur,PasswordEncoder pe)
    {
        this.ur=ur;
        this.pe=pe;
    }
    
    @Override
    public User createUser(User user) {
         if(user.getName()==null && user.getPassword()==null)
            throw new RuntimeException("User Name & User Password isn't given");

        if(user.getName()==null)
            throw new RuntimeException("User Name  isn't given");

        if(user.getPassword()==null)
            throw new RuntimeException("User Password isn't given");

        if(ur.findByName(user.getName())!=null)
            throw new RuntimeException("User Name "+user.getName()+" already exists");
        
        User newUser=User.builder()
                        .name(user.getName())
                        .password(pe.encode(user.getPassword()))
                        .role(User.Role.SUPERVISOR)
                        .build();
        return ur.save(newUser);
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users=ur.findAll();
        if(users.isEmpty())
            throw new RuntimeException("No users found");
        return users;
    }

    @Transactional
    @Override
    public User updateUser(Long id, User user) {
        User existingUser=ur.findById(id).orElseThrow(()->
        new RuntimeException("User not found on User Id: "+id));
        if(user.getName()==null && user.getPassword()==null)
            throw new RuntimeException("User Name & User Password isn't given");
        if(user.getName()!=null)
            existingUser.setName(user.getName());
        if(user.getName()!=null)
            existingUser.setName(pe.encode(user.getPassword()));

        return ur.save(existingUser);
    }

    @Transactional
    @Override
    public User deleteUser(Long id) {
        User existingUser=ur.findById(id).orElseThrow(()->
        new RuntimeException("User not found on User Id: "+id));
        ur.delete(existingUser);
        return existingUser;
    }

    
    
}
