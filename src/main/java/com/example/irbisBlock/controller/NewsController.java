package com.example.irbisBlock.controller;

import com.example.irbisBlock.dto.NewsDto;
import com.example.irbisBlock.dto.SourceDto;
import com.example.irbisBlock.dto.TopicDto;
import com.example.irbisBlock.service.NewsService;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/news")
@RequiredArgsConstructor
public class NewsController {
    private final NewsService newsService;
    @RequestMapping(value = "/page/{page}", method = RequestMethod.GET)
    public ResponseEntity<List<NewsDto>> getAll(@PathVariable("page") int page) {
        return new ResponseEntity<>(newsService.getAllNews(page), HttpStatus.OK);
    }
    @RequestMapping(value = "/bysource/{page}", method = RequestMethod.POST)
    public ResponseEntity<List<NewsDto>> getBySourceName(@PathVariable("page") int page, @RequestBody SourceDto sd) {
        return new ResponseEntity<>(
                newsService.getNewsBySourceName(sd.getSourceName(), page), HttpStatus.OK);
    }
    @RequestMapping(value = "/bytopic/{page}", method = RequestMethod.POST)
    public ResponseEntity<List<NewsDto>> getByTopicName(@PathVariable("page") int page, @RequestBody TopicDto td) {
        return new ResponseEntity<>(
                newsService.getNewsByTopicName(td.getTopicName(), page), HttpStatus.OK);
    }
}
