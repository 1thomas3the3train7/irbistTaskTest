package com.example.irbisBlock.service.impl;

import com.example.irbisBlock.dto.TopicDto;
import com.example.irbisBlock.repository.TopicRepository;
import com.example.irbisBlock.service.TopicService;
import com.example.irbisBlock.utils.DtoUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TopicServiceImpl implements TopicService {
    private final TopicRepository topicRepository;
    private final DtoUtils dtoUtils;

    @Override
    public List<TopicDto> getAllTopic(int page) {
        return topicRepository.findFullAllTopic(page).stream()
                .map(dtoUtils::fromTopic).collect(Collectors.toList());
    }
}
