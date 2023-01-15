package com.example.irbisBlock;

import com.example.irbisBlock.dto.TopicDto;
import com.example.irbisBlock.entity.Topic;
import com.example.irbisBlock.repository.TopicRepository;
import com.example.irbisBlock.service.NewsService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;


import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static com.example.irbisBlock.dto.dtoTypeToken.topicDtoList;

@SpringBootTest
class IrbisBlockApplicationTests {
	@Autowired
	private TopicRepository topicRepository;
	@Autowired
	private NewsService newsService;
	private final Gson gson = new Gson();

	@Test
	void contextLoads() {
	}
	@Test
	@Transactional
	void test1() {
		Topic topic = new Topic();
		topic.setTopicName("name");
		topicRepository.save(topic);
		System.out.println(topicRepository.findFullAllTopic(1));
		TopicDto topicDto = TopicDto.builder().topicName("topicName").build();
		TopicDto topicDto1 = TopicDto.builder().topicName("topic2").build();
		List<TopicDto> t = List.of(topicDto1,topicDto);
		System.out.println(gson.toJson(t));
		Type typeToken = new TypeToken<ArrayList<TopicDto>>(){}.getType();
		List<TopicDto> t1 = gson.fromJson(gson.toJson(t), topicDtoList);
		System.out.println(t1);
	}
	@Test
	void test2() {
		System.out.println(newsService.getAllNews(1));
	}

}
