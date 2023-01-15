package com.example.irbisBlock.dto;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
public class JwtDto {
    private String accessToken;
    private String refreshToken;
}
