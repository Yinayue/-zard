package com.example.demo.service;

import com.baomidou.mybatisplus.service.IService;
import com.example.demo.entity.Friends;
import com.example.demo.entity.Users;

import java.util.List;


public interface IFriendsService extends IService<Friends> {
    public List<Friends> selectByUser(Users users);
}
