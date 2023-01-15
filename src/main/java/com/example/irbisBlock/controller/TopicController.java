package com.example.irbisBlock.controller;

import com.example.irbisBlock.dto.TopicDto;
import com.example.irbisBlock.service.TopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/topic")
@RequiredArgsConstructor
public class TopicController {
    private final TopicService topicService;
    @RequestMapping(value = "/page/{page}", method = RequestMethod.GET)
    public ResponseEntity<List<TopicDto>> getAll(@PathVariable("page") int page) {
        return new ResponseEntity<>(topicService.getAllTopic(page), HttpStatus.OK);
    }
}
