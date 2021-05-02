package com.example.demo.serviceImpl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.demo.entity.ChatMessage;
import com.example.demo.entity.Users;
import com.example.demo.mapper.ChatMessageMapper;
import com.example.demo.service.IChatMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ChatMessageServiceImpl extends ServiceImpl<ChatMessageMapper, ChatMessage> implements IChatMessageService {
    @Autowired
    ChatMessageMapper chatMessageMapper;

    public List<ChatMessage> selectByUser(Users users){
        return chatMessageMapper.selectByUser(users);
    }
}
