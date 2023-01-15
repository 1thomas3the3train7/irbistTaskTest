package com.example.irbisBlock.repository;

import com.example.irbisBlock.entity.Topic;

import java.util.List;

public interface TopicRepository {
    void save(Topic topic);
    void delete(Topic topic);
    List<Topic> findFullAllTopic(int page);
}
