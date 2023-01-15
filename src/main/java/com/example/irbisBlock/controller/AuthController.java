package com.example.irbisBlock.controller;

import com.example.irbisBlock.dto.JwtDto;
import com.example.irbisBlock.dto.UserDto;
import com.example.irbisBlock.exception.NotValidRequestException;
import com.example.irbisBlock.service.AuthService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<JwtDto> getTokens(@RequestBody UserDto userDto, HttpServletResponse response) {
        final JwtDto jwtDto = authService.login(userDto);
        Cookie cookie = new Cookie("refreshToken", jwtDto.getRefreshToken());
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
        jwtDto.setRefreshToken(null);
        return new ResponseEntity<>(jwtDto, HttpStatus.OK);
    }
    @RequestMapping(value = "/accesstoken")
    public ResponseEntity<JwtDto> updateToken(HttpServletRequest request) {
        final Cookie[] cookies = request.getCookies();
        if (cookies != null){
            for (Cookie c : cookies) {
                if (c.getName().equals("refreshToken")) {
                    final String refreshToken = c.getValue();
                    return new ResponseEntity<>(authService.updateAccessToken(refreshToken), HttpStatus.OK);
                }
            }
        }
        throw new NotValidRequestException();
    }
}
