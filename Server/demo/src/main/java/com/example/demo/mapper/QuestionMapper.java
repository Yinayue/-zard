package com.example.demo.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.example.demo.entity.Question;

import java.util.List;

public interface QuestionMapper extends BaseMapper<Question> {

    public List<Question> selectByUid(Question question);

}
