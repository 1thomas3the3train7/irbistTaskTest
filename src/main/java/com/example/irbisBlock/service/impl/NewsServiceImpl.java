package com.example.irbisBlock.service.impl;

import com.example.irbisBlock.dto.NewsDto;
import com.example.irbisBlock.entity.News;
import com.example.irbisBlock.exception.NotValidRequestException;
import com.example.irbisBlock.repository.NewsRepository;
import com.example.irbisBlock.service.NewsService;
import com.example.irbisBlock.utils.DtoUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService {
    private final NewsRepository newsRepository;
    private final DtoUtils dtoUtils;
    @Override
    @Transactional
    public List<NewsDto> getAllNews(int page) {
        return newsRepository.findAllNews(page).stream().map(dtoUtils::fromNews)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<NewsDto> getNewsByTopicName(String topicName, int page) {
        if (topicName == null) {
            throw new NotValidRequestException();
        }
        return newsRepository.findNewsByTopicName(topicName, page).stream()
                .map(dtoUtils::fromNews).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<NewsDto> getNewsBySourceName(String sourceName, int page) {
        if (sourceName == null) {
            throw new NotValidRequestException();
        }
        return newsRepository.findNewsBySourceName(sourceName, page).stream()
                .map(dtoUtils::fromNews).collect(Collectors.toList());
    }
}
