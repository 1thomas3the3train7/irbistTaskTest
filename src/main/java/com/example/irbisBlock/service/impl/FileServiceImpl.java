package com.example.irbisBlock.service.impl;

import com.example.irbisBlock.entity.News;
import com.example.irbisBlock.entity.Source;
import com.example.irbisBlock.entity.Topic;
import com.example.irbisBlock.exception.GenerateFileException;
import com.example.irbisBlock.exception.SourceNotFoundException;
import com.example.irbisBlock.repository.SourceRepository;
import com.example.irbisBlock.service.FileService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService{
    private final SourceRepository sourceRepository;
    private final ExecutorService executorService = Executors.newFixedThreadPool(4);
    private final Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);
    @Value("${filepath.generate}")
    private String filePath;


    @Override
    @Scheduled(cron = "0 * * * * *")
    //@Scheduled(fixedDelay = 6000)
    @Transactional
    public void generateFileCsv() throws Exception {
        System.out.println(filePath);
        String pathToSave = filePath;
        final List<Source> sour = sourceRepository.findAllSource();
        if (sour == null) {
            throw new SourceNotFoundException("Source not found");
        }
        final List<Source> sources = sour.stream().map(s ->
                sourceRepository.findFullSourceBySourceName(s.getSourceName())).toList();
        for (Source s : sources) {
            executorService.submit(() -> {
                try {
                    final String fileName = pathToSave + s.getSourceName() + ".csv";
                    StringBuffer stringBuffer = new StringBuffer();
                    for (Topic t : s.getTopics()) {
                        for (News n : t.getNews()) {
                            stringBuffer.append(s.getSourceName());
                            stringBuffer.append(";");
                            stringBuffer.append(t.getTopicName());
                            stringBuffer.append(";");
                            stringBuffer.append(n.getDescription());
                            stringBuffer.append("\n");
                        }
                    }

                    FileOutputStream stream = new FileOutputStream(fileName);
                    stream.write(stringBuffer.toString().getBytes());
                    stream.close();
                    logger.info("Create file: " + fileName);
                } catch (Exception e) {
                    throw new GenerateFileException(e.getMessage());
                }
            });
        }
    }

    @Override
    @Transactional
    public void parseFile(String filePath) throws Exception {
        Map<String, Map<String, List<String>>> map = new HashMap<>();
        FileInputStream stream = new FileInputStream(filePath);
        BufferedReader b = new BufferedReader(new InputStreamReader(stream));
        String l;
        while ((l = b.readLine()) != null) {
            String[] line = l.split(";");
            if (map.containsKey(line[0])) {
                if (map.get(line[0]).containsKey(line[1])) {
                    List<String> s = new ArrayList<>();
                    s.add(line[2]);
                    s.addAll(map.get(line[0]).get(line[1]));
                    map.get(line[0]).put(line[1],s);
                } else {
                    map.get(line[0]).put(line[1],List.of(line[2]));
                }
            } else {
                Map<String, List<String>> topics = new HashMap<>();
                topics.put(line[1], List.of(line[2]));
                map.put(line[0], topics);
            }
        }
        stream.close();
        b.close();
        for (Map.Entry<String, Map<String, List<String>>> mapEntry : map.entrySet()) {
            Source source = sourceRepository.findFullSourceBySourceName(mapEntry.getKey());
            List<Topic> topics = new ArrayList<>();
            for (Map.Entry<String, List<String>> topicNews : mapEntry.getValue().entrySet()) {
                Topic topic = new Topic(topicNews.getKey());
                List<News> news = topicNews.getValue().stream().map(News::new).toList();
                topic.setNews(news);
                topics.add(topic);
            }
            if (source == null) {
                source = new Source(mapEntry.getKey());
                source.setTopics(topics);
                sourceRepository.save(source);
            } else {
                source.getTopics().addAll(topics);
                sourceRepository.save(source);
            }
        }
    }

}
