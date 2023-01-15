package com.example.irbisBlock.dto;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public final class dtoTypeToken {
    public static final Type topicDtoList = new TypeToken<ArrayList<TopicDto>>(){}.getType();
    public static final Type roleDtoList = new TypeToken<ArrayList<RoleDto>>(){}.getType();
    public static final Type newsDtoList = new TypeToken<ArrayList<NewsDto>>(){}.getType();

}
