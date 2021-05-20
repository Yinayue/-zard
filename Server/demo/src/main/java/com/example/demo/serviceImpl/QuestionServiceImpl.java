package com.example.demo.serviceImpl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.demo.entity.Preference;
import com.example.demo.entity.Question;
import com.example.demo.mapper.PreferenceMapper;
import com.example.demo.mapper.QuestionMapper;
import com.example.demo.service.IPreferenceService;
import com.example.demo.service.IQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl  extends ServiceImpl<QuestionMapper, Question> implements IQuestionService {

    @Autowired
    QuestionMapper questionMapper;

    @Override
    public List<Question> selectByUid(Question question) {
        return questionMapper.selectByUid(question);
    }
}
