package com.example.irbisBlock.service.impl;

import com.example.irbisBlock.dto.SourceDto;
import com.example.irbisBlock.repository.SourceRepository;
import com.example.irbisBlock.service.SourceService;
import com.example.irbisBlock.utils.DtoUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SourceServiceImpl implements SourceService {
    private final SourceRepository sourceRepository;
    private final DtoUtils dtoUtils;
    @Override
    @Transactional
    public List<SourceDto> getAllSource(int page) {
        return sourceRepository.findAllSource(page).stream()
                .map(s -> dtoUtils.fromSource(sourceRepository.findFullSourceBySourceName(s.getSourceName()))).toList();
    }
}
