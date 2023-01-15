package com.example.irbisBlock.service.impl;

import com.example.irbisBlock.dto.JwtDto;
import com.example.irbisBlock.dto.RoleDto;
import com.example.irbisBlock.dto.UserDto;
import com.example.irbisBlock.exception.IncorrectJwtTokenException;
import com.example.irbisBlock.jwt.JwtUtils;
import com.example.irbisBlock.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final JwtUtils jwtUtils;
    @Override
    public JwtDto login(UserDto userDto) {
        userDto.setRoles(Collections.singletonList(new RoleDto("ROLE_USER")));

        final String accessToken = jwtUtils.generateAccessToken(userDto);
        final String refreshToken = jwtUtils.generateRefreshToken(userDto);
        return JwtDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    @Override
    public JwtDto updateAccessToken(String refreshToken) {
        if (jwtUtils.validateRefreshToken(refreshToken)) {
            final UserDto userDto = jwtUtils.parseRefreshToken(refreshToken);
            final String accessToken = jwtUtils.generateAccessToken(userDto);
            return JwtDto.builder()
                    .accessToken(accessToken)
                    .build();
        }
        throw new IncorrectJwtTokenException();
    }
}
