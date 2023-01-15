package com.example.irbisBlock.dto;

import lombok.*;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class NewsDto {
    private String id;
    private String description;

    public NewsDto(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NewsDto newsDto = (NewsDto) o;
        return description.equals(newsDto.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description);
    }
}
