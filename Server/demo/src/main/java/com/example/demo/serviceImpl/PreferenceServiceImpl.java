package com.example.demo.serviceImpl;

import com.baomidou.mybatisplus.service.IService;
import com.example.demo.entity.Preference;
import com.example.demo.mapper.PreferenceMapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.demo.service.IPreferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Ye Suyuan
 * @since 2021-03-04
 */
@Service
public class PreferenceServiceImpl extends ServiceImpl<PreferenceMapper, Preference> implements IPreferenceService {

    @Autowired
    PreferenceMapper preferenceMapper;

    @Override
    public List<Preference> select(Preference preference){
        return preferenceMapper.select(preference);
    }

}
