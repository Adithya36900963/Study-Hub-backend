package com.example.StudyHub.controller;


import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.example.StudyHub.entity.User;
import com.example.StudyHub.model.ResponseModel;
import com.example.StudyHub.service.user.UserServiceLayer;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api/users")
public class UserController {
    private UserServiceLayer usl;
    public UserController(UserServiceLayer usl)
    {
        this.usl=usl;
    }
    @PostMapping()
    public ResponseEntity<Map<String,Object>> creatUser(@RequestBody User user)
    {
        ResponseModel<User> res=new 
        ResponseModel(201,"User Created Sucessfully",usl.createUser(user));
       return res.res();
    }

    @GetMapping()
    public ResponseEntity<Map<String,Object>> getAllUsers()
    {
        ResponseModel<List<User>> res=new 
        ResponseModel(200,"User Fetched Sucessfully",usl.getAllUsers());
       return res.res();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Map<String,Object>> updateUser(@PathVariable Long id,@RequestBody User user)
    {
        ResponseModel<User> res=new 
        ResponseModel(200,"User Updated Sucessfully",usl.updateUser(id,user));
       return res.res();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String,Object>> deleteUser(@PathVariable("id") Long id)
    {
        ResponseModel<User> res =new ResponseModel(200, "User Deleted Sucessfully",usl.deleteUser(id));
        return res.res();
    }
}
