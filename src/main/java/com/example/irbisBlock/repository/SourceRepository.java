package com.example.irbisBlock.repository;

import com.example.irbisBlock.entity.Source;

import java.util.List;

public interface SourceRepository {
    void save(Source source);
    void delete(Source source);
    List<Source> findAllSource(int page);
    List<Source> findAllSource();
    Source findFullSourceBySourceName(String sourceName);
}
