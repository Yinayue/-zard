package com.example.demo.serviceImpl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.demo.entity.Friends;
import com.example.demo.entity.Users;
import com.example.demo.mapper.FriendsMapper;
import com.example.demo.service.IFriendsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendsServiceImpl extends ServiceImpl<FriendsMapper, Friends> implements IFriendsService {

    @Autowired
    FriendsMapper friendsMapper;

    @Override
    public List<Friends> selectByUser(Users users) {
        return friendsMapper.selectByUser(users);
    }
}
