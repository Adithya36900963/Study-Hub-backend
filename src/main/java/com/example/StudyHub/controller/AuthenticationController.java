package com.example.StudyHub.controller;

import org.springframework.security.core.Authentication;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



import com.example.StudyHub.model.UserModel;
import com.example.StudyHub.security.JWTUtil;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    private AuthenticationManager authenticationManager;
    private JWTUtil jwtUtil;

    public AuthenticationController(
        AuthenticationManager authenticationManager,
        JWTUtil jwtUtil)
    {
        this.authenticationManager=authenticationManager;
        this.jwtUtil=jwtUtil;
    }

    @PostMapping("/login")
public ResponseEntity<?> login(@RequestBody UserModel req,HttpServletResponse response) {

    try {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.getName(), req.getPassword())
        );


        String token = jwtUtil.generateToken(auth.getName());
        Cookie cookie = new Cookie("jwt", token);
        cookie.setHttpOnly(true);
        cookie.setSecure(false);      
        cookie.setPath("/");          
        cookie.setMaxAge(60 * 60);   

        response.addCookie(cookie);
        return ResponseEntity.ok(Map.of("message", "Login successful"));

    } catch (Exception e) {
        e.printStackTrace(); // ✅ prints real cause
        return ResponseEntity.status(401).body(Map.of(
                "error", e.getClass().getSimpleName(),
                "message", e.getMessage()
        ));
    }
}

    @GetMapping("/auth/logout")
    public ResponseEntity<?> logout(HttpServletResponse res)
    {
        Cookie cookie = new Cookie("jwt", null);
        cookie.setHttpOnly(true);
        cookie.setSecure(false);      
        cookie.setPath("/");          
        cookie.setMaxAge(0);   

        res.addCookie(cookie);
        return ResponseEntity.ok(Map.of("message", "Logoutsuccessful"));
    }

}
