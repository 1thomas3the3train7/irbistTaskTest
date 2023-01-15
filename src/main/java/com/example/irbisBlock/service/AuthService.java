package com.example.irbisBlock.service;

import com.example.irbisBlock.dto.JwtDto;
import com.example.irbisBlock.dto.UserDto;

public interface AuthService {
    JwtDto login(UserDto userDto);
    JwtDto updateAccessToken(String refreshToken);
}
