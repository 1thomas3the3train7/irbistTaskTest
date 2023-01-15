package com.example.irbisBlock.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class UserDto {
    private String id;
    private String email;
    private String password;
    private List<RoleDto> roles;


    public UserDto(String email, List<RoleDto> roles) {
        this.email = email;
        this.roles = roles;
    }
}
