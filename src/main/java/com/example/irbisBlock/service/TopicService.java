package com.example.irbisBlock.service;

import com.example.irbisBlock.dto.TopicDto;

import java.util.List;

public interface TopicService {
    List<TopicDto> getAllTopic(int page);
}
