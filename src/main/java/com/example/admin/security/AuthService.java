package com.example.admin.security;

import com.example.admin.DTO.LoginDTO;

public interface AuthService {
    String login(LoginDTO loginDto);
}
