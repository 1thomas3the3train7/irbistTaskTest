package com.example.irbisBlock.controller;

import com.example.irbisBlock.dto.SourceDto;
import com.example.irbisBlock.service.SourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/source")
@RequiredArgsConstructor
public class SourceController {
    private final SourceService sourceService;
    @RequestMapping(value = "/page/{page}", method = RequestMethod.GET)
    public ResponseEntity<List<SourceDto>> getAll(@PathVariable("page") int page) {
        return new ResponseEntity<>(sourceService.getAllSource(page), HttpStatus.OK);
    }
}
