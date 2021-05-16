package com.example.demo.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.example.demo.entity.Friends;
import com.example.demo.entity.Users;

import java.util.List;

public interface FriendsMapper extends BaseMapper<Friends> {
    public List<Friends> selectByUser(Users users);
}
