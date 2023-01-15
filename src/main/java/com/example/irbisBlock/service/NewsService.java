package com.example.irbisBlock.service;

import com.example.irbisBlock.dto.NewsDto;

import java.util.List;

public interface NewsService {
    List<NewsDto> getAllNews(int page);
    List<NewsDto> getNewsByTopicName(String topicName, int page);
    List<NewsDto> getNewsBySourceName(String sourceName, int page);
}
