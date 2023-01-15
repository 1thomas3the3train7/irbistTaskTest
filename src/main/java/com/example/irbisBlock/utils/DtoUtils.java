package com.example.irbisBlock.utils;

import com.example.irbisBlock.dto.NewsDto;
import com.example.irbisBlock.dto.SourceDto;
import com.example.irbisBlock.dto.TopicDto;
import com.example.irbisBlock.entity.News;
import com.example.irbisBlock.entity.Source;
import com.example.irbisBlock.entity.Topic;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class DtoUtils {
    public NewsDto fromNews(News news) {
        return NewsDto.builder()
                .id(String.valueOf(news.getId()))
                .description(news.getDescription())
                .build();
    }
    public TopicDto fromTopic(Topic topic) {
        final TopicDto topicDto = TopicDto.builder()
                .id(String.valueOf(topic.getId()))
                .topicName(topic.getTopicName())
                .build();
        if (topic.getNews() != null && topic.getNews().size() > 0) {
            topicDto.setNews(topic.getNews().stream().map(this::fromNews).
                    collect(Collectors.toList()));
        }
        return topicDto;
    }
    public SourceDto fromSource(Source source) {
        final SourceDto sourceDto = SourceDto.builder()
                .id(String.valueOf(source.getId()))
                .sourceName(source.getSourceName())
                .build();
        if (source.getTopics() != null && source.getTopics().size() > 0) {
            sourceDto.setTopics(source.getTopics().stream().map(this::fromTopic)
                    .collect(Collectors.toList()));
        }
        return sourceDto;
    }
}
