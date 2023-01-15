package com.example.irbisBlock.dto;

import lombok.*;

import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SourceDto {
    private String id;
    private String sourceName;
    private List<TopicDto> topics;

    public SourceDto(String sourceName) {
        this.sourceName = sourceName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SourceDto sourceDto = (SourceDto) o;
        return sourceName.equals(sourceDto.sourceName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sourceName);
    }
}
