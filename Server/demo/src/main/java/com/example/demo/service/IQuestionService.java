package com.example.demo.service;

import com.baomidou.mybatisplus.service.IService;
import com.example.demo.entity.Question;

import java.util.List;


public interface IQuestionService extends IService<Question> {

    public List<Question> selectByUid(Question question);

}
