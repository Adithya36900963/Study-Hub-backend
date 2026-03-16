package com.example.StudyHub.service.login;

import com.example.StudyHub.model.LoginResponse;
import com.example.StudyHub.model.UserModel;

import jakarta.servlet.http.HttpServletResponse;

public interface LoginService {
    public LoginResponse login(UserModel req,HttpServletResponse response);
}
