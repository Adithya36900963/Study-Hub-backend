package com.example.StudyHub.controller;


import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.StudyHub.model.LoginResponse;
import com.example.StudyHub.model.ResponseModel;
import com.example.StudyHub.model.UserModel;
import com.example.StudyHub.service.login.LoginService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    private LoginService ls;

    public AuthenticationController(
        LoginService ls)
    {
        this.ls=ls;
    }

    @PostMapping("/login")
public ResponseEntity<?> login(@RequestBody UserModel req,HttpServletResponse response) {


    try {
        ResponseModel<LoginResponse> res=new ResponseModel<>(200, "Logined sucessflly",ls.login(req, response));
        return res.res();
    } catch (Exception e) {
        e.printStackTrace(); // ✅ prints real cause
        return ResponseEntity.status(401).body(Map.of(
                "error", e.getClass().getSimpleName(),
                "message", e.getMessage()
        ));
    }
}

    @GetMapping("/logout")
    public ResponseEntity<?> logout(HttpServletResponse res)
    {
        Cookie cookie = new Cookie("jwt", null);
        cookie.setHttpOnly(true);
        cookie.setSecure(false);      
        cookie.setPath("/");          
        cookie.setMaxAge(0);   

        res.addCookie(cookie);
        return ResponseEntity.ok(Map.of("message", "Logout Successful"));
    }

}
