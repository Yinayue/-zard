package com.example.demo.service;

import com.baomidou.mybatisplus.service.IService;
import com.example.demo.entity.Mark;

import java.util.List;

public interface IMarkService extends IService<Mark> {

    public List<Mark> select(Mark mark);

}
