package com.example.irbisBlock.service;

import com.example.irbisBlock.dto.SourceDto;

import java.util.List;

public interface SourceService {
    List<SourceDto> getAllSource(int page);
}
