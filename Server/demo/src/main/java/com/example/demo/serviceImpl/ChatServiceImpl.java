package com.example.demo.serviceImpl;

import com.example.demo.entity.Chat;
import com.example.demo.mapper.ChatMapper;
import com.example.demo.service.IChatService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Ye Suyuan
 * @since 2021-03-30
 */
@Service
public class ChatServiceImpl extends ServiceImpl<ChatMapper, Chat> implements IChatService {
	
}
