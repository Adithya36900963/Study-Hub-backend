package com.example.StudyHub.service.user;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.StudyHub.entity.User;
import com.example.StudyHub.model.UserResponse;
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
    
    public void customPasswordValidator(String pass)
    {
        int len=8;
        int upperCase=0;
        int lowerCase=0;
        int digit=0;
        int specialChar=0;
        if(pass.length()<len)
            throw new RuntimeException("Password must be at least "+len+" characters long");
        for(char c: pass.toCharArray())
        {
            if(Character.isUpperCase(c))
                upperCase++;
            else if(Character.isLowerCase(c))
                lowerCase++;
            else if(Character.isDigit(c))
                digit++;
            else
                specialChar++;
            
        }
    }
    @Override
    public UserResponse createUser(User user) {
         if(user.getName()==null && user.getPassword()==null)
            throw new RuntimeException("User Name & User Password isn't given");

        if(user.getName()==null)
            throw new RuntimeException("User Name  isn't given");


        String password=user.getPassword();
        
        if(password==null || "".equals(password))
            throw new RuntimeException("User Password isn't given");

        if(ur.findByName(user.getName())!=null)
            throw new RuntimeException("User Name "+user.getName()+" already exists");
        
        User newUser=User.builder()
                        .name(user.getName())
                        .password(pe.encode(password))
                        .role(User.Role.SUPERVISOR)
                        .build();
        User savedUser=ur.save(newUser);
        return UserResponse.builder()
                        .id(savedUser.getId())
                        .name(savedUser.getName())
                        .role(savedUser.getRole())
                        .build();
    }

   @Override
public List<UserResponse> getAllUsers() {

    List<User> users = ur.findAll();
    if(users.isEmpty())
        return List.of();
    return users.stream()
            .map(user -> UserResponse.builder()
                    .id(user.getId())
                    .name(user.getName())
                    .role(user.getRole())
                    .build())
            .toList();
}

    @Transactional
    @Override
    public UserResponse updateUser(Long id, User user) {
        User existingUser=ur.findById(id).orElseThrow(()->
        new RuntimeException("User not found on User Id: "+id));
        if(user.getName()==null && user.getPassword()==null)
            throw new RuntimeException("User Name & User Password isn't given");
        if(user.getName()!=null)
            existingUser.setName(user.getName());
        if(user.getPassword()!=null)
            existingUser.setPassword(pe.encode(user.getPassword()));

        return UserResponse.builder()
                        .id(existingUser.getId())
                        .name(existingUser.getName())
                        .role(existingUser.getRole())
                        .build();
    }

    @Transactional
    @Override
    public UserResponse deleteUser(Long id) {
        User existingUser=ur.findById(id).orElseThrow(()->
        new RuntimeException("User not found on User Id: "+id));
        ur.delete(existingUser);
        return UserResponse.builder()
                        .id(existingUser.getId())
                        .name(existingUser.getName())
                        .role(existingUser.getRole())
                        .build();
    }

    
    
}
