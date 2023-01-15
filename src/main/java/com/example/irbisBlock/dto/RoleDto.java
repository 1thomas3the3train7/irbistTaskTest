package com.example.irbisBlock.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class RoleDto {
    private String id;
    private String name;

    public RoleDto(String name) {
        this.name = name;
    }
}
