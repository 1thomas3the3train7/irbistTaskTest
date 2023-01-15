package com.example.irbisBlock.service;

import com.example.irbisBlock.entity.Source;

public interface FileService {
    void generateFileCsv() throws Exception;
    void parseFile(String filePath) throws Exception;
}
