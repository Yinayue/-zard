package com.example.demo.service;

import com.baomidou.mybatisplus.service.IService;
import com.example.demo.entity.ChatMessage;
import com.example.demo.entity.Users;

import java.util.List;

public interface IChatMessageService extends IService<ChatMessage> {
    public List<ChatMessage> selectByUser(Users users);
}
