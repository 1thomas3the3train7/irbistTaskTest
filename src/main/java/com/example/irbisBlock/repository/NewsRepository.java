package com.example.irbisBlock.repository;

import com.example.irbisBlock.entity.News;

import java.util.List;

public interface NewsRepository {
    void save(News news);
    void delete(News news);
    List<News> findAllNews(int page);
    List<News> findNewsByTopicName(String topicName, int page);
    List<News> findNewsBySourceName(String sourceName, int page);

}
