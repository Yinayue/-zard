package com.example.demo.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.example.demo.entity.Mark;

import java.util.List;

public interface MarkMapper extends BaseMapper<Mark> {

    public List<Mark> select(Mark mark);

}
