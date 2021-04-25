package com.example.demo.serviceImpl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.demo.entity.Mark;
import com.example.demo.mapper.MarkMapper;
import com.example.demo.service.IMarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarkServiceImpl extends ServiceImpl<MarkMapper, Mark> implements IMarkService {

    @Autowired
    MarkMapper markMapper;

    @Override
    public List<Mark> select(Mark mark) {
        return markMapper.select(mark);
    }
}
