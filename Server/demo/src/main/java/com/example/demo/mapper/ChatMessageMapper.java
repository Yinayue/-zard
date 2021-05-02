package com.example.demo.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.example.demo.entity.ChatMessage;
import com.example.demo.entity.Users;

import java.util.List;

public interface ChatMessageMapper extends BaseMapper<ChatMessage> {
    public List<ChatMessage> selectByUser(Users users);
}
