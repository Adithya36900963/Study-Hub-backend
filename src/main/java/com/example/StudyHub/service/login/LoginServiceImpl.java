package com.example.StudyHub.service.login;


import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.example.StudyHub.entity.User;
import com.example.StudyHub.model.LoginResponse;
import com.example.StudyHub.model.UserModel;
import com.example.StudyHub.repository.UserRepository;
import com.example.StudyHub.security.JWTUtil;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class LoginServiceImpl implements LoginService {

    private AuthenticationManager authenticationManager;
    private JWTUtil jwtUtil;
    private UserRepository ur;

    public LoginServiceImpl(
        AuthenticationManager authenticationManager,
        JWTUtil jwtUtil,
        UserRepository ur)
    {
        this.authenticationManager=authenticationManager;
        this.jwtUtil=jwtUtil;
        this.ur=ur;
    }
    
    @Override
    public LoginResponse login(UserModel userModel,HttpServletResponse response) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userModel.getName(), userModel.getPassword()));


        String token = jwtUtil.generateToken(auth.getName());
        Cookie cookie = new Cookie("jwt", token);
        cookie.setHttpOnly(true);
        cookie.setSecure(false);      
        cookie.setPath("/");          
        cookie.setMaxAge(60 * 60);   

        response.addCookie(cookie);
        
        User user=ur.findByName(userModel.getName());
        
        LoginResponse loginResponse = LoginResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .role(user.getRole().name()) // Replace with actual role
                .build();

        return loginResponse;
    }
    
}
