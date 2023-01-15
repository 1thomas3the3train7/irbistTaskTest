package com.example.irbisBlock.dto;

import lombok.*;

import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Builder
@ToString
public class TopicDto {
    private String id;
    private String topicName;
    private List<NewsDto> news;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TopicDto topicDto = (TopicDto) o;
        return topicName.equals(topicDto.topicName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(topicName);
    }
}
